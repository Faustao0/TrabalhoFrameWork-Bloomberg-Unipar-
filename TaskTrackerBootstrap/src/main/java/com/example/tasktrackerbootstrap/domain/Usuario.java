package com.example.tasktrackerbootstrap.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    private String senha;
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Tarefa> tarefas = new ArrayList<>();
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Habito> habitos = new ArrayList<>();

    public Usuario() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<Tarefa> getTarefas() {
        return tarefas;
    }

    public void setTarefas(List<Tarefa> tarefas) {
        this.tarefas = tarefas;
    }

    public List<Habito> getHabitos() {
        return habitos;
    }

    public void setHabitos(List<Habito> habitos) {
        this.habitos = habitos;
    }

    public void adicionarTarefa(Tarefa tarefa) {
        tarefa.setUsuario(this);
        this.tarefas.add(tarefa);
    }
}
