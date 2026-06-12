package com.br.fiap.totvs.application;

import com.br.fiap.totvs.infrastruture.ia.IaAgentClient;
import com.br.fiap.totvs.model.domain.contract.IaCliente;
import com.br.fiap.totvs.model.domain.entity.*;
import com.br.fiap.totvs.model.domain.enumeration.NivelAlerta;
import com.br.fiap.totvs.model.domain.enumeration.TipoReuniao;
import com.br.fiap.totvs.model.domain.service.AnalisadorService;

public class ProcessarReuniaoService {

    // Orquestrado de classes

    public RelatorioReuniaoDTO executar(String idReuniao, String nomeCliente, String textoTranscricao){

        //instancia a infraestrutura
        IaCliente agenteIA = new IaAgentClient();
        AnalisadorService analisadorService = new AnalisadorService(agenteIA);

        Reuniao reuniao = new Reuniao(idReuniao, nomeCliente, TipoReuniao.VENDAS);
        Transcricao transcricao = new Transcricao("T-" + idReuniao, textoTranscricao, 180);

        reuniao.setTranscricao(transcricao);

        analisadorService.executarAnaliseLocal(reuniao);

        RelatorioReuniaoDTO dto = new RelatorioReuniaoDTO(reuniao.getId(), reuniao.getNomeCliente());

        // Não esqueça de manter o laço dos insights antes do return dto
        for (InsightComercial insight : reuniao.getInsights()) {
            dto.adicionarInsight(insight.getTipo().toString(), insight.getDescricao());
        }
        return dto;
    }
}
