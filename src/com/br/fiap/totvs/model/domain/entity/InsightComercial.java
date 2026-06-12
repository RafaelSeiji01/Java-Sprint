package com.br.fiap.totvs.model.domain.entity;

import com.br.fiap.totvs.model.domain.enumeration.TipoInsight;

public class InsightComercial {

    //Resultado apos a analise da IA

    private TipoInsight tipo;  //o enum
    private String descricao;
    private String concorrente;    // null se não houver
    private double valorOportunidade;


    public InsightComercial(TipoInsight tipo, double valorOportunidade, String concorrente, String descricao) {
        this.tipo = tipo;
        this.valorOportunidade = valorOportunidade;
        this.concorrente = concorrente;
        this.descricao = descricao;
    }

    public TipoInsight getTipo() { return tipo; }

    public String getDescricao() { return descricao; }

    public double getValor() { return valorOportunidade; }

    public String getConcorrente() { return concorrente; }

    public double getValorOportunidade() {
        return valorOportunidade;
    }

    @Override
    public String toString() {
        return "Insight[" + tipo + " | Detalhe: " + descricao +
                " | Concorrente: '" + concorrente + "' | Valor: R$" + valorOportunidade + "]";
    }
}
