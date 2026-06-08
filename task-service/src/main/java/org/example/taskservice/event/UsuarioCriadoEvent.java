package org.example.taskservice.event;

import java.io.Serializable;

public class UsuarioCriadoEvent
        implements Serializable {

    private Long usuarioId;
    private String nome;

    public UsuarioCriadoEvent() {
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
