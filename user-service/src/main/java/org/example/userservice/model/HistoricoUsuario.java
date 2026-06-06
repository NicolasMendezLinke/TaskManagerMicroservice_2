package org.example.userservice.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class HistoricoUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long usuarioId;

    private String nomeUsuario;

    private String acao;

    private LocalDateTime dataHora;

    public HistoricoUsuario() {
    }

    public HistoricoUsuario(
            Long usuarioId,
            String nomeUsuario,
            String acao,
            LocalDateTime dataHora) {

        this.usuarioId = usuarioId;
        this.nomeUsuario = nomeUsuario;
        this.acao = acao;
        this.dataHora = dataHora;
    }

    public Long getId() {
        return id;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public String getAcao() {
        return acao;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public void setAcao(String acao) {
        this.acao = acao;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }
}
