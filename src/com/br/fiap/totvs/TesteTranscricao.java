package com.br.fiap.totvs;

import com.br.fiap.totvs.model.domain.entity.Transcricao;

public class TesteTranscricao {
    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("🧪 LABORATÓRIO DE TESTE: PAPEL DA TRANSCRIÇÃO");
        System.out.println("==================================================\n");

        // ----------------------------------------------------------------------
        // CENÁRIO 1: Reunião com Falha Crônica de Áudio (Vazia ou nula)
        // ----------------------------------------------------------------------
        System.out.println("--- [Cenário 1] Áudio com defeito (Sem conteúdo) ---");
        Transcricao transFalha = new Transcricao("TR-ERR", "   ", 600); // 10 min de silêncio
        System.out.println("Métricas extraídas: " + transFalha.getMetricas());
        System.out.println("Resultado: " + (transFalha.getQualidadeAudio() == 0.0 ? "✅ SUCESSO (Bloqueou envio)" : "❌ FALHA"));
        System.out.println();


        // ----------------------------------------------------------------------
        // CENÁRIO 2: Reunião Muito Curta / Notas Subjetivas (Baixa Qualidade)
        // ----------------------------------------------------------------------
        System.out.println("--- [Cenário 2] Reunião superficial (Poucas palavras) ---");
        // Uma frase com exatamente 10 palavras
        String textoSuperficial = "Bom dia cliente gostou do Protheus mas achou caro tchau.";
        Transcricao transBaixa = new Transcricao("TR-BAIXA", textoSuperficial, 1200); // 20 min de reunião

        System.out.println("Métricas extraídas: " + transBaixa.getMetricas());
        // Deve dar exatamente 10 / 100 = 0.1 (10%)
        System.out.println("Resultado: " + (transBaixa.getQualidadeAudio() == 0.1 ? "✅ SUCESSO (Qualidade de 10% calculada)" : "❌ FALHA"));
        System.out.println();


        // ----------------------------------------------------------------------
        // CENÁRIO 3: Reunião Produtiva / "Ouro Invisível" Completo (Qualidade Máxima)
        // ----------------------------------------------------------------------
        System.out.println("--- [Cenário 3] Reunião Produtiva (Conteúdo robusto) ---");

        // Vamos simular uma conversa real que ultrapasse 100 palavras para bater o teto de 1.0
        StringBuilder textoRobusto = new StringBuilder();
        textoRobusto.append("Olá João, estamos avaliando a migração do nosso sistema de faturamento. ");
        textoRobusto.append("Atualmente o Protheus nos atende nas filiais, mas a matriz está rodando a folha de pagamento de forma manual e isso gera um gargalo gigantesco. ");
        textoRobusto.append("Nossa equipe de recursos humanos assistiu a uma demonstração da Senior Sistemas e achou a interface deles muito intuitiva. ");
        textoRobusto.append("Contudo, eu pessoalmente prefiro centralizar toda a nossa operação na TOTVS, desde que o módulo RM venha totalmente integrado e sem dores de cabeça no suporte. ");
        textoRobusto.append("O diretor financeiro separou um orçamento anual de aproximadamente cinquenta mil reais para essa melhoria, mas ele está travado olhando o retorno sobre o investimento do trimestre. ");
        textoRobusto.append("Se vocês conseguirem cobrir a proposta comercial deles e garantir o prazo de implantação até o final do semestre, nós fechamos o contrato de upsell na próxima segunda-feira sem falta.");

        Transcricao transTop = new Transcricao("TR-TOP", textoRobusto.toString(), 2700); // 45 min
        Transcricao t1 = new Transcricao("1","textoTetse", 150);

        System.out.println("Métricas extraídas: " + transTop.getMetricas());
        System.out.println("Resultado: " + (transTop.getQualidadeAudio() == 1.0 ? "✅ SUCESSO (Bateu o teto de 100% de qualidade)" : "❌ FALHA"));
        System.out.println();

        System.out.println("==================================================");
        System.out.println("🧪 FIM DOS TESTES: TRANSCRIÇÃO APROVADA!");
        System.out.println("==================================================");

        System.out.println("meu teste");
        System.out.println("metricas: " + t1.getMetricas());
        System.out.println("Conteudo: " + t1.getConteudo());
        System.out.println("duração: " + t1.getDuracao());
        System.out.println("Qualidade audio: " +t1.getQualidadeAudio());
        System.out.println(t1.calcularQualidade());

    }
}
