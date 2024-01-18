package com.example.gent.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "Carga", schema = "KaiqueTraining")
public class Carga {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Integer cardaId;

        private String  cargaDestino;

        private String  cargaOrigem;

        private double  cargaValor;

        private Date    dataEntrega;

    public Carga() {

    }

    public Integer getCardaId() {
        return cardaId;
    }

    public void setCardaId(Integer cardaId) {
        this.cardaId = cardaId;
    }

    public String getCargaDestino() {
        return cargaDestino;
    }

    public void setCargaDestino(String cargaDestino) {
        this.cargaDestino = cargaDestino;
    }

    public String getCargaOrigem() {
        return cargaOrigem;
    }

    public void setCargaOrigem(String cargaOrigen) {
        this.cargaOrigem = cargaOrigen;
    }

    public double getCargaValor() {
        return cargaValor;
    }

    public void setCargaValor(double cargaValor) {
        this.cargaValor = cargaValor;
    }

    public Date getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(Date dataEntrega) {
        this.dataEntrega = dataEntrega;
    }
}

