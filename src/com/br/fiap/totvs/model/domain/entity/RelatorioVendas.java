package com.br.fiap.totvs.model.domain.entity;

import com.br.fiap.totvs.model.domain.enumeration.TipoInsight;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RelatorioVendas {

    private String periodo;
    private int totalReunioes;
    private double taxaChurn;
    private List<InsightComercial> oportunidadesUpsell;

    public RelatorioVendas(String periodo) {
        this.periodo = periodo;
        this.oportunidadesUpsell = new ArrayList<>();
    }

    public void adicionarInsight(InsightComercial i) {
        oportunidadesUpsell.add(i);
    }

    public RelatorioVendas gerar(List<Reuniao> reunioes) {
        this.totalReunioes = reunioes.size();
        long churns = reunioes.stream()
                .flatMap(r -> r.getInsights().stream())
                .filter(i -> i.getTipo() == TipoInsight.CHURN)
                .count();
        this.taxaChurn = (double) churns / totalReunioes;
        return this;
    }

    public double getTaxaChurn() { return taxaChurn; }
    public List<InsightComercial> getInsights() {
        return Collections.unmodifiableList(oportunidadesUpsell);
    }

    public void exportar() {
        System.out.printf("Relatório %s | Reuniões: %d | Churn: %.1f%%%n",
                periodo, totalReunioes, taxaChurn * 100);
    }
}
