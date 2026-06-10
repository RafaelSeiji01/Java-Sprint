package com.br.fiap.totvs.model.domain.entity;

import java.util.HashMap;
import java.util.Map;

public class Transcricao {
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

    public double calcularQualidade() {
        // Lógica simples: textos maiores = mais completos
        if (conteudoTexto == null || conteudoTexto.isBlank()) return 0.0;
        return Math.min(conteudoTexto.split("\\s+").length / 100.0, 1.0);
    }

    public Map<String, Object> getMetricas() {
        Map<String, Object> m = new HashMap<>();
        m.put("palavras", conteudoTexto.split("\\s+").length);
        m.put("duracao_seg", duracao);
        m.put("qualidade", qualidadeAudio);
        return m;
    }
}
