package com.example.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class OrdemServico {

    @Id
    private String data;

    private String diaSemana;

    private String diaDescricao;
    
    @ManyToOne
    @JoinColumn(name = "id_resolvedor")
    private Resolvedor resolvedor;
    
    
    public String getDiaDescricao() {
        return diaDescricao;
    }

    public void setDiaDescricao(String diaDescricao) {
        this.diaDescricao = diaDescricao;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(String diaSemana) {
        this.diaSemana = diaSemana;
    }

    public Resolvedor getResolvedor() {
        return resolvedor;
    }

    public void setResolvedor(Resolvedor resolvedor) {
        this.resolvedor = resolvedor;
    }
    
    
}
