package com.example.gent.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "Cliente", schema = "KaiqueTraining")
public class Cliente extends Pessoa{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Date ultimoServico;

    private Date dataCadastro;

    public Cliente(String nome, String sobrenome, Date dataNascimento, String cpf, String rg, String endereco, String cidade, String estado, Boolean status) {
        super(nome, sobrenome, dataNascimento, cpf, rg, endereco, cidade, estado, status);
    }

    public Cliente() {
        super();
    }

    @Override
    public Integer getId() {
        return id;
    }


    public Date getUltimoServico() {
        return ultimoServico;
    }

    public void setUltimoServico(Date ultimoServico) {
        this.ultimoServico = ultimoServico;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
}
