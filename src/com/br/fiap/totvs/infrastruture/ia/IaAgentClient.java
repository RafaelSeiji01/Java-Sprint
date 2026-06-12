package com.br.fiap.totvs.infrastruture.ia;

import com.br.fiap.totvs.model.domain.contract.IaCliente;
import com.br.fiap.totvs.model.domain.entity.InsightComercial;
import com.br.fiap.totvs.model.domain.entity.Transcricao;
import com.br.fiap.totvs.model.domain.enumeration.TipoInsight;

import java.util.ArrayList;
import java.util.List;

public class IaAgentClient implements IaCliente {


    //simulaçãoo

    @Override
    public List<InsightComercial> extrairInsghtComercial(Transcricao transcricao) {
        System.out.println("[Infraestrutura] Conectando ao servidor do Google Gemini para analisar o texto...");

        List<InsightComercial> insightsIdentificados = new ArrayList<>();
        String texto = transcricao.getConteudo().toLowerCase();


        if (texto.contains("não sei") || texto.contains("acho que")) {
            insightsIdentificados.add(new InsightComercial(
                    TipoInsight.SENTIMENTO,
                    0.0,
                    "Nenhum",
                    "Gemini AI: O cliente demonstra um tom de indecisão ou incerteza sobre o produto."
            ));
        }

        if (texto.contains("concorrência") || texto.contains("outro sistema")) {
            insightsIdentificados.add(new InsightComercial(
                    TipoInsight.CONCORRENTE,
                    0.0,
                    "Análise Requerida",
                    "Gemini AI: Alerta de menção à concorrência detectado no fluxo do diálogo."
            ));
        }

        return insightsIdentificados;
    }
}
