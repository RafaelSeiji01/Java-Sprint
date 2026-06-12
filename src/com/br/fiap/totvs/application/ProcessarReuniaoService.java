package com.br.fiap.totvs.application;

import com.br.fiap.totvs.infrastruture.ia.IaAgentClient;
import com.br.fiap.totvs.model.domain.contract.IaCliente;
import com.br.fiap.totvs.model.domain.entity.*;
import com.br.fiap.totvs.model.domain.enumeration.NivelAlerta;
import com.br.fiap.totvs.model.domain.enumeration.TipoInsight;
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

        // conta quantos insights de churn foram detectados
        int totalChurns = (int) reuniao.getInsights().stream().filter(i -> i.getTipo() == TipoInsight.CHURN).count();

        RelatorioVendas relatorio = new RelatorioVendas("Mensal");
        relatorio.gerar(totalChurns);

        // se houver churn, dispara o alerta usando o método enviar() que já existe
        if (totalChurns > 0) {
            Alerta alerta = new Alerta(
                    NivelAlerta.CRITICO,
                    "Cliente '" + nomeCliente + "' com risco de churn detectado!",
                    "diretor.comercial@totvs.com"
            );
            alerta.enviar(); // já imprime no console
        }


        RelatorioReuniaoDTO dto = new RelatorioReuniaoDTO(reuniao.getId(), reuniao.getNomeCliente());

        // Não esqueça de manter o laço dos insights antes do return dto
        for (InsightComercial insight : reuniao.getInsights()) {
            dto.adicionarInsight(insight.getTipo().toString(), insight.getDescricao());
        }
        return dto;
    }
}
