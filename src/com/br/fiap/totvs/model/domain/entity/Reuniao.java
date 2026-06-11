package com.br.fiap.totvs.model.domain.entity;

import com.br.fiap.totvs.model.domain.enumeration.TipoReuniao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Reuniao {

    //classe principal com o intuito de guardar informações principais e tudo girar sobre essas informaçoes

    private String id;
    private LocalDateTime dataHora;
    private String nomeCliente;
    private TipoReuniao tipoReuniao;
    private Transcricao transcricao;
    private List<InsightComercial> insights;

    public Reuniao(String id, String nomeCliente, TipoReuniao tipo) {
        this.id = id;
        this.nomeCliente = nomeCliente;
        this.tipoReuniao = tipo;
        this.dataHora = LocalDateTime.now();
        this.insights = new ArrayList<>();
    }

    public String getId() { return id; }

    public String getNomeCliente() { return nomeCliente; }

    public TipoReuniao getTipoReuniao() { return tipoReuniao; }

    //essa transcricao é de outra claasse, é o objeto gerado a partir do construtor da classe trnacricao,
    //entao todo objeto é trnasformado para essa classe
    public void setTranscricao(Transcricao trans) {
        this.transcricao = trans;
    }

    public Transcricao getTranscricao() { return transcricao; }

    public void adicionarInsight(InsightComercial insight) {
        if (insight != null){
            this.insights.add(insight);
        }
    }

    public List<InsightComercial> getInsights() { return Collections.unmodifiableList(insights); }
}
