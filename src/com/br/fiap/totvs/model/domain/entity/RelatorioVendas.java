package com.br.fiap.totvs.model.domain.entity;

import com.br.fiap.totvs.model.domain.enumeration.NivelAlerta;
import com.br.fiap.totvs.model.domain.enumeration.TipoInsight;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RelatorioVendas {
    private String periodo;
    private int totalReuniao;
    private double taxaChurn;
    private List<String> oportunidadesUpsell;
    private int quantidadeChurns;

    public RelatorioVendas(String periodo) {
        this.periodo = periodo;
        this.oportunidadesUpsell = new ArrayList<>();
        this.totalReuniao = 1; // Exemplodo mês
        this.quantidadeChurns = 0;
    }

    public RelatorioVendas gerar(int totalChurnsMapeados) {
        // Agora o relatório usa exatamente o número que veio da análise
        this.totalReuniao = 12;

        // Calcula a taxa com base no número real
        if (this.totalReuniao > 0) {
            this.taxaChurn = (double) totalChurnsMapeados / this.totalReuniao;
        }

        return this;
    }

}