package com.br.fiap.totvs.apresentation;

import com.br.fiap.totvs.model.domain.entity.*;
import com.br.fiap.totvs.model.domain.enumeration.TipoReuniao;

import java.util.List;


public class Teste {
    public static void main(String[] args) {
        System.out.println("=== INICIANDO TESTE DO SISTEMA - TOTVS O OURO INVISÍVEL ===\n");

        // 1. Simular o texto bruto da reunião (Exemplo do "Ouro Invisível")
        String textoSimulado = "Bom dia. O nosso Protheus atende bem o backoffice, mas o time de RH está "
                + "sofrendo com a folha manual. Vimos uma demo da Senior e gostamos, mas prefiro consolidar "
                + "tudo na TOTVS se o RM for realmente integrado. O valor de R$ 50 mil está dentro do nosso budget.";

        // 2. Instanciar a Transcricao usando o SEU construtor: (id, conteudoTexto, duracao)
        // Note que sua classe calcula a qualidade automaticamente no construtor! Muito bom.
        Transcricao transcricao = new Transcricao("TR-001", textoSimulado, 2700); // 2700 segundos = 45 min

        // 3. Instanciar a Reuniao usando o SEU construtor original: (id, nomeCliente, tipoReuniao)
        // Substitua 'TipoReuniao.VENDAS' pelo valor real que você tiver no seu enum
        Reuniao reuniao = new Reuniao("REU-100", "Cliente Exemplo S/A", TipoReuniao.VENDAS);

        // Associando a transcrição à reunião via setter (composição 1..1)
        reuniao.setTranscricao(transcricao);

        // Imprimindo métricas calculadas pela sua classe Transcricao
        System.out.println("Métricas da Transcrição calculadas automaticamente:");
        System.out.println(transcricao.getMetricas());
        System.out.println();

        // 4. Instanciar o AnalisadorIA
        AnalisadorIA analisador = new AnalisadorIA();

        // ----------------------------------------------------
        // TESTE 1: Regras locais (detectarChurn e detectarUpsell)
        // ----------------------------------------------------
        System.out.println("--- Testando Regras Locais (Busca por palavras-chave) ---");

        boolean temRiscoChurn = analisador.detectarChurn(transcricao);
        boolean temOportunidadeUpsell = analisador.detectarUpsell(transcricao);

        System.out.println("Risco de Churn detectado localmente? " + (temRiscoChurn ? "SIM (Atenção!)" : "NÃO"));
        System.out.println("Oportunidade de Upsell detectada localmente? " + (temOportunidadeUpsell ? "SIM (Ótimo!)" : "NÃO"));
        System.out.println();

        // ----------------------------------------------------
        // TESTE 2: Integração com a API do Gemini
        // ----------------------------------------------------
        System.out.println("--- Testando Chamada à API do Gemini ---");
        System.out.println("Aviso: Certifique-se de que a variável GEMINI_API_KEY está configurada no seu ambiente.");

        try {
            // Chama a API do Gemini passando o objeto transcricao
            List<InsightComercial> insights = analisador.analisar(transcricao);

            // Adiciona os insights gerados à reunião usando o seu método adicionarInsight
            for (InsightComercial insight : insights) {
                reuniao.adicionarInsight(insight);
            }

            System.out.println("Sucesso! Quantidade de insights na reunião após análise da IA: " + reuniao.getInsights().size());

        } catch (Exception e) {
            System.err.println("Erro ao testar a chamada da IA: " + e.getMessage());
        }
    }

}
