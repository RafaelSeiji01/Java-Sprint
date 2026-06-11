package com.br.fiap.totvs;

import com.br.fiap.totvs.model.domain.entity.InsightComercial;
import com.br.fiap.totvs.model.domain.entity.Reuniao;
import com.br.fiap.totvs.model.domain.entity.Transcricao;
import com.br.fiap.totvs.model.domain.enumeration.TipoInsight;
import com.br.fiap.totvs.model.domain.enumeration.TipoReuniao;
import com.sun.security.jgss.GSSUtil;

public class TesteInsight {

    public static void main(String[] args) {

        Reuniao r1 = new Reuniao("r1","rafael", TipoReuniao.VENDAS);

        InsightComercial concoorente = new InsightComercial(
                TipoInsight.CONCORRENTE,0.0,"Venus","Cliente nn gostou"
        );

        InsightComercial expancao = new InsightComercial(
                TipoInsight.CROSS_SELL, 15.0,"","adrou outros produtos"
        );

        Transcricao t1 = new Transcricao("PAQUETA","textao do zap", 180);

        r1.adicionarInsight(concoorente);
        r1.adicionarInsight(expancao);

        System.out.println(t1.getId());
        System.out.println("Quantidade de insight: " + r1.getInsights().size());
        System.out.println("ID: " + r1.getId());
        System.out.println("Transcrição: " + r1.getTranscricao());
        System.out.println();

        System.out.println("------------");

        for (InsightComercial insight : r1.getInsights()) {
            System.out.println("-> Tipo: " + insight.getTipo() + " | Valor: R$" + insight.getValorOportunidade() + " | Detalhe: " + insight.getDescricao());
        }
    }
}
