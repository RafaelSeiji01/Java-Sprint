package com.br.fiap.totvs;

import com.br.fiap.totvs.model.domain.contract.IaCliente;
import com.br.fiap.totvs.model.domain.entity.AnalisadorService;
import com.br.fiap.totvs.model.domain.entity.InsightComercial;
import com.br.fiap.totvs.model.domain.entity.Reuniao;
import com.br.fiap.totvs.model.domain.enumeration.TipoInsight;
import com.br.fiap.totvs.model.domain.entity.Transcricao;
import com.br.fiap.totvs.model.domain.enumeration.TipoReuniao;

import java.util.ArrayList;
import java.util.List;

public class TesteServiceComIa {

    public static void main(String[] args) {
        System.out.println("Testando com ia mockado");

        IaCliente iaMockado = new IaCliente() {
            @Override
            public List<InsightComercial> extrairInsghtComercial(Transcricao transcricao) {
                List<InsightComercial> simulados = new ArrayList<>();
                // Simula que a IA leu o texto e descobriu o Sentimento e o Concorrente de forma inteligente
                simulados.add(new InsightComercial(TipoInsight.SENTIMENTO, 0.0, "Nenhum", "IA determinou: Sentimento Misto."));
                simulados.add(new InsightComercial(TipoInsight.CONCORRENTE, 0.0, "Senior Sistemas", "IA detectou citação à Senior."));
                return simulados;
            }
        };

        AnalisadorService a1 = new AnalisadorService(iaMockado);
        Reuniao r1 = new Reuniao("r2","paqueta", TipoReuniao.VENDAS);

        String conteudoTexto = "nao sei oque do que oque mas vou cancelar, acho que";
        Transcricao t1 = new Transcricao("t2",conteudoTexto,68);

        r1.setTranscricao(t1); //Liga reuniao com trancrição!!!!

        iaMockado.extrairInsghtComercial(t1);
        a1.executarAnaliseLocal(r1);

        System.out.println("analise(esperase 3): " + r1.getInsights().size());

        for (InsightComercial i : r1.getInsights()) {
            System.out.println("➔ [" + i.getTipo() + "] -> " + i.getDescricao());
        }
    }
}
