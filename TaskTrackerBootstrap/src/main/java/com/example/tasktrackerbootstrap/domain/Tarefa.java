package com.example.tasktrackerbootstrap.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dataInicio;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dataLimite;

    private boolean concluida;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    @JsonBackReference
    private Usuario usuario;

    public Tarefa() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataLimite() {
        return dataLimite;
    }

    public void setDataLimite(LocalDate dataLimite) {
        this.dataLimite = dataLimite;
    }

    public boolean isConcluida() {
        return concluida;
    }

    public void setConcluida(boolean concluida) {
        this.concluida = concluida;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}