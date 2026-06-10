package com.br.fiap.totvs.model.domain.entity;

import com.br.fiap.totvs.model.domain.enumeration.TipoInsight;

public class InsightComercial {
    private TipoInsight tipo;
    private String descricao;
    private String concorrente;    // null se não houver
    private double valorOportunidade;

    public InsightComercial(TipoInsight tipo, String descricao, double valor) {
        this.tipo = tipo;
        this.descricao = descricao;
        this.valorOportunidade = valor;
    }

    public TipoInsight getTipo() { return tipo; }
    public String getDescricao() { return descricao; }
    public double getValor() { return valorOportunidade; }

    public void setConcorrente(String c) { this.concorrente = c; }
    public String getConcorrente() { return concorrente; }
}
