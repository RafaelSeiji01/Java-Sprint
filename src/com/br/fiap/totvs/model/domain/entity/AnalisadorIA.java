package com.br.fiap.totvs.model.domain.entity;


import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class AnalisadorIA {
    private final String modelo;
    private final String promptSistema;
    private final String apiKey; // Armazena a chave da API do Google AI Studio
    private List<String> historico;

    public AnalisadorIA() {
        // Atualizado para o Gemini 1.5 Pro (excelente para raciocínio complexo e JSON)
        this.modelo = "gemini-1.5-pro";
        this.historico = new ArrayList<>();

        // Pegando a API Key das variáveis de ambiente por segurança
        this.apiKey = System.getenv("GEMINI_API_KEY");

        this.promptSistema = """
            Você é um analista de reuniões comerciais da TOTVS.
            Analise a transcrição fornecida pelo usuário e retorne um objeto JSON contendo exatamente a seguinte estrutura:
            {
              "insights": [
                {
                  "tipo": "CHURN" | "UPSELL" | "CROSS_SELL" | "CONCORRENTE" | "SENTIMENTO",
                  "descricao": "string",
                  "valor": 0.0
                }
              ],
              "churn": boolean,
              "upsell": boolean,
              "concorrentes": ["string"],
              "sentimento": "POSITIVO" | "NEGATIVO" | "MISTO",
              "persona": "string"
            }
            """;
    }

    public List<InsightComercial> analisar(Transcricao transcricao) {
        String resposta = chamarAPI(transcricao.getConteudo());
        this.historico.add("Análise realizada. Resposta da API: " + resposta);
        return parsearInsights(resposta);
    }

    public boolean detectarChurn(Transcricao t) {
        String texto = t.getConteudo().toLowerCase();
        return texto.contains("cancelar") || texto.contains("concorrente")
                || texto.contains("insatisfeito") || texto.contains("avaliar outra");
    }

    public boolean detectarUpsell(Transcricao t) {
        String texto = t.getConteudo().toLowerCase();
        return texto.contains("módulo") || texto.contains("integrar")
                || texto.contains("folha") || texto.contains("expandir");
    }

    private String chamarAPI(String conteudo) {
        if (this.apiKey == null || this.apiKey.isEmpty()) {
            System.err.println("Erro: A variável de ambiente GEMINI_API_KEY não foi configurada.");
            return "{}";
        }

        try {
            HttpClient client = HttpClient.newHttpClient();

            // Montando o endpoint oficial do Google AI Studio
            String url = "https://generativelanguage.googleapis.com/v1beta/models/"
                    + this.modelo + ":generateContent?key=" + this.apiKey;

            // Escapando o conteúdo e o prompt para não quebrar o JSON do Request
            String jsonRequestBody = """
                {
                  "contents": [{
                    "parts": [{"text": "%s"}]
                  }],
                  "systemInstruction": {
                    "parts": [{"text": "%s"}]
                  },
                  "generationConfig": {
                    "responseMimeType": "application/json"
                  }
                }
                """.formatted(escaparJson(conteudo), escaparJson(this.promptSistema));

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonRequestBody))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                return response.body();
            } else {
                System.err.println("Erro na API do Gemini. Status: " + response.statusCode());
                System.err.println("Corpo da resposta: " + response.body());
                return "{}";
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "{}";
        }
    }

    private List<InsightComercial> parsearInsights(String jsonResponse) {
        // O JSON que o Gemini retorna vem envelopado em uma estrutura do Google:
        // candidates[0].content.parts[0].text
        // Você precisará usar Jackson ou Gson para extrair a String de dentro do 'text'
        // e depois mapear para a sua lista de InsightComercial.
        return new ArrayList<>();
    }

    // Método utilitário simples para evitar que quebras de linha ou aspas no texto quebrem o JSON de envio
    private String escaparJson(String texto) {
        if (texto == null) return "";
        return texto.replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\t", "\\t");
    }
}
