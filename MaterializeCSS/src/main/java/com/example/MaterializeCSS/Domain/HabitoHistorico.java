package com.example.MaterializeCSS.Domain;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class HabitoHistorico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Date data;

    @ManyToOne
    @JoinColumn(name = "id_habito")
    private Habito habito;

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Habito getHabito() {
        return habito;
    }

    public void setHabito(Habito habito) {
        this.habito = habito;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
