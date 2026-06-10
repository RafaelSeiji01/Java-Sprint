package com.br.fiap.totvs.model.domain.entity;

import com.br.fiap.totvs.model.domain.enumeration.TipoReuniao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Reuniao {

    private String id;
    private LocalDateTime dataHora;
    private String nomeCliente;
    private TipoReuniao tipoReuniao;
    private Transcricao transcricao;  // composição 1..1
    private List<InsightComercial> insights;

    public Reuniao(String id, String nomeCliente, TipoReuniao tipo) {
        this.id = id;
        this.nomeCliente = nomeCliente;
        this.tipoReuniao = tipo;
        this.dataHora = LocalDateTime.now();
        this.insights = new ArrayList<>();
    }

    public Reuniao(String id, LocalDateTime now, String s, String vendas, Transcricao transcricao) {
    }

    public String getId() { return id; }
    public String getNomeCliente() { return nomeCliente; }
    public TipoReuniao getTipoReuniao() { return tipoReuniao; }

    public void setTranscricao(Transcricao t) { this.transcricao = t; }
    public Transcricao getTranscricao() { return transcricao; }

    public void adicionarInsight(InsightComercial i) { this.insights.add(i); }
    public List<InsightComercial> getInsights() { return Collections.unmodifiableList(insights); }
}
