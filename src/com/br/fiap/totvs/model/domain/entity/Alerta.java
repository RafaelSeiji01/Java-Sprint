package com.br.fiap.totvs.model.domain.entity;

import com.br.fiap.totvs.model.domain.enumeration.NivelAlerta;

import java.util.UUID;

public class Alerta {

    // SIstema para algo critico que acontecer

    private String id;
    private NivelAlerta nivel;
    private String mensagem;
    private String enviadoPara;

    public Alerta(NivelAlerta nivel, String mensagem, String destinatario) {
        this.id = UUID.randomUUID().toString();
        this.nivel = nivel;
        this.mensagem = mensagem;
        this.enviadoPara = destinatario;
    }

    public NivelAlerta getNivel() { return nivel; }

    public void enviar() {
        System.out.printf("[ALERTA %s] Para: %s | %s%n", nivel, enviadoPara, mensagem);

    }
}
