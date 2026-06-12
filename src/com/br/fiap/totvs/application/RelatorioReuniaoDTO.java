package com.br.fiap.totvs.application;

import java.util.ArrayList;
import java.util.List;

public class RelatorioReuniaoDTO {
    private final String idReuniao;
    private final String nomeCliente;
    private final List<String> insightsFormatados = new ArrayList<>();

    private String alertaNivel;
    private String alertaMensagem;
    private int totalReunioesRelatorio;
    private double taxaChurnRelatorio;

    public RelatorioReuniaoDTO(String idReuniao, String nomeCliente) {
        this.idReuniao = idReuniao;
        this.nomeCliente = nomeCliente;
    }

    public void adicionarInsight(String tipo, String descricao) {
        this.insightsFormatados.add("[" + tipo + "] -> " + descricao);
    }

    // Getters simples para a apresentação ler
    public String getIdReuniao() { return idReuniao; }
    public String getNomeCliente() { return nomeCliente; }
    public List<String> getInsightsFormatados() { return insightsFormatados; }

    public void setDadosRelatorioVendas(int total, double taxa) {
        this.totalReunioesRelatorio = total;
        this.taxaChurnRelatorio = taxa;
    }

    public void setAlerta(String nivel, String mensagem) {
        this.alertaNivel = nivel;
        this.alertaMensagem = mensagem;
    }


}