package com.br.fiap.totvs.model.domain.service;

import com.br.fiap.totvs.model.domain.contract.IaCliente;
import com.br.fiap.totvs.model.domain.entity.InsightComercial;
import com.br.fiap.totvs.model.domain.entity.Reuniao;
import com.br.fiap.totvs.model.domain.entity.Transcricao;
import com.br.fiap.totvs.model.domain.enumeration.TipoInsight;

import java.util.List;

public class AnalisadorService {

    //Regra de negocios, varredura rapida sem IA

    private final IaCliente iaCliente;

    public AnalisadorService(IaCliente iaCliente) {
        this.iaCliente = iaCliente;
    }


    public void executarAnaliseLocal(Reuniao reuniao){
        Transcricao transcricao = reuniao.getTranscricao();

        if (transcricao == null) {
            return;
        }

        if (detectarChurn(transcricao)){
            InsightComercial insightChurn = new InsightComercial(TipoInsight.CHURN,0,"Pendente","Gatilho Local: Termos de insatisfação detectados.");

            reuniao.adicionarInsight(insightChurn);
        }

        if (detectarUpsell(transcricao)){
            InsightComercial insightUpsell = new InsightComercial(TipoInsight.UPSELL,580,"Pendente","Gatilho Local: Termos de expansão detectados.");

            reuniao.adicionarInsight(insightUpsell);
        }

        List<InsightComercial> insghtDaIa = iaCliente.extrairInsghtComercial(transcricao);

        for (InsightComercial insight : insghtDaIa) {
            reuniao.adicionarInsight(insight);
        }

    }

    // no caso ira retornar se haver essas palavras
    public boolean detectarChurn(Transcricao transcricao){
        String texto = transcricao.getConteudo().toLowerCase();
        return texto.contains("cancelar") || texto.contains("concorrentes") || texto.contains("insatisfeito") || texto.contains("avaliar outra");
    }

    public boolean detectarUpsell(Transcricao transcricao){
        String texto = transcricao.getConteudo().toLowerCase();
        return texto.contains("módulo") || texto.contains("integrar") || texto.contains("folha") || texto.contains("expandir");
    }

}
