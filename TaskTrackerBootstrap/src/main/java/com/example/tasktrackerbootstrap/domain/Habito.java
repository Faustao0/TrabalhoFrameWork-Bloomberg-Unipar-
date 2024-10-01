package com.example.tasktrackerbootstrap.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Habito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;

    @OneToMany(mappedBy = "habito", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<HabitoHistorico> historicos;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    @JsonBackReference
    private Usuario usuario;

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

    public List<HabitoHistorico> getHistoricos() {
        return historicos;
    }

    public void setHistoricos(List<HabitoHistorico> historicos) {
        this.historicos = historicos;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
