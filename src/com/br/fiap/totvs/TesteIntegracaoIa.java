package com.br.fiap.totvs;

import com.br.fiap.totvs.infrastruture.ia.GemineClient;
import com.br.fiap.totvs.model.domain.entity.AnalisadorService;
import com.br.fiap.totvs.model.domain.entity.InsightComercial;
import com.br.fiap.totvs.model.domain.entity.Reuniao;
import com.br.fiap.totvs.model.domain.entity.Transcricao;
import com.br.fiap.totvs.model.domain.enumeration.TipoReuniao;

public class TesteIntegracaoIa {
    public static void main(String[] args) {
        System.out.println("Criando com IA");

        Reuniao r3 = new Reuniao("r3","paqueta", TipoReuniao.VENDAS);
        String conteudoTexto = "sou bobo vou cancelar de novo, outro sistema, não sei";
        Transcricao t3 = new Transcricao("t3",conteudoTexto, 827);

        GemineClient clienteGemine = new GemineClient();

        AnalisadorService a3 = new AnalisadorService(clienteGemine);

        r3.setTranscricao(t3);
        a3.executarAnaliseLocal(r3);

        clienteGemine.extrairInsghtComercial(t3);

        System.out.println("\nAnálise Concluída! Total de Insights na Reunião: " + r3.getInsights().size());

        for (InsightComercial i : r3.getInsights()) {
            System.out.println("➔ [" + i.getTipo() + "] Detalhe: " + i.getDescricao());
        }

    }


}
