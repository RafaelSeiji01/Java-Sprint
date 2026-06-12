package com.br.fiap.totvs.apresentation;

import com.br.fiap.totvs.application.ProcessarReuniaoService;
import com.br.fiap.totvs.application.RelatorioReuniaoDTO;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TerminalTotvs {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        ProcessarReuniaoService appService = new ProcessarReuniaoService();


        String RESET      = "\u001B[0m";
        String CYAN       = "\u001B[36m";
        String WHITE_BOLD = "\u001B[1;37m";
        String GRAY       = "\u001B[90m";
        String GREEN      = "\u001B[32m";

        System.out.println(CYAN + " █▓▒░ " + WHITE_BOLD + "TOTVS COGNITIVE PLATFORM" + CYAN + " ░▒▓█" + RESET);
        System.out.println(GRAY + " ──────────────────────────────────────────────────────────" + RESET);

        System.out.print("Digite o ID da reuniao: ");
        String idReuniao = input.nextLine();

        System.out.print("Digite o nome do Cliente: ");
        String cliente = input.nextLine();

        String pastaRaizProjeto = System.getProperty("user.dir");

        Path arquivoEntrada = Paths.get(pastaRaizProjeto, "src", "com", "br", "fiap", "totvs", "apresentation", "simulacao", "Reuniao.txt");
        Path arquivoSaida = Paths.get(pastaRaizProjeto, "src", "com", "br", "fiap", "totvs", "apresentation", "simulacao", "insights_relatorio.txt");

        System.out.println("[Debug] Procurando arquivo em: " + arquivoEntrada.toAbsolutePath());

        try {
            if (!Files.exists(arquivoEntrada)) {
                System.out.println("Arquivo 'reuniao.txt' não encontrado na pasta simulacao.");
                return;
            }

            String textoConversa = Files.readString(arquivoEntrada);

            // Executa e recebe APENAS o DTO de transporte de dados
            System.out.println("[Processando] Solicitando processamento à camada de aplicação...");
            RelatorioReuniaoDTO relatorio = appService.executar(idReuniao,cliente ,textoConversa);

            // Monta o arquivo txt final lendo apenas do DTO
            List<String> linhasRelatorio = new ArrayList<>();
            linhasRelatorio.add("=== RELATÓRIO DE INSIGHTS  ===");
            linhasRelatorio.add("ID Reunião: " + relatorio.getIdReuniao());
            linhasRelatorio.add("Cliente   : " + relatorio.getNomeCliente());
            linhasRelatorio.add("Total de Insights: " + relatorio.getInsightsFormatados().size());
            linhasRelatorio.add("---------------------------------------");

            for (String insightTexto : relatorio.getInsightsFormatados()) {
                linhasRelatorio.add("➔ " + insightTexto);
            }

            Files.write(arquivoSaida, linhasRelatorio);
            System.out.println("\n" + GRAY + " ──────────────────────────────────────────────────────────" + RESET);
            System.out.println(GREEN + " ✔ [SUCCESS] PIPELINE EXECUTION COMPLETED" + RESET);
            System.out.println(GRAY + " ──────────────────────────────────────────────────────────" + RESET);

        } catch (IOException e) {
            System.out.println("Erro ao ler/gravar os arquivos: " + e.getMessage());
        } finally {
            input.close();
        }
    }

}
