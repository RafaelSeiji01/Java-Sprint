package com.br.fiap.totvs.model.domain.entity;

import java.util.HashMap;
import java.util.Map;

public class Transcricao {

    //Audio passa pelo speck to text, esse texto entra primeiramente nessa classe

    private String id;
    private String conteudoTexto;
    private int duracao; // em segundos
    private double qualidadeAudio;

    public Transcricao(String id, String conteudoTexto, int duracao) {
        this.id = id;
        this.conteudoTexto = conteudoTexto;
        this.duracao = duracao;
        this.qualidadeAudio = calcularQualidade();
    }

    public String getConteudo() { return conteudoTexto; }

    public int getDuracao() { return duracao; }

    public double getQualidadeAudio() {
        return qualidadeAudio;
    }

    public String getId() {
        return id;
    }

    public double calcularQualidade() {

        //Conteudo veio ""
        if (conteudoTexto == null || conteudoTexto.isBlank()) return 0.0;

        // Conta as palavras separando por espaços em branco
        int qtdPalavras = conteudoTexto.split("\\s+").length;

        // divide por 100.0. Se der mais de 1.0, o Math.min limita o teto em 1.0 (100%)
        return Math.min(qtdPalavras / 100.0, 1.0);
    }

    public Map<String, Object> getMetricas() {
        Map<String, Object> m = new HashMap<>();
        m.put("palavras", conteudoTexto.split("\\s+").length);
        m.put("duracao_seg", duracao);
        m.put("qualidade", qualidadeAudio);
        return m;
    }
}
