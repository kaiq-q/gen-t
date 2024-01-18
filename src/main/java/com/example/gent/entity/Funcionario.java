package com.example.gent.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "Funcionario", schema = "KaiqueTraining")
public class Funcionario extends Pessoa{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Date dataAdmissao;

    private Date dataDesligamento;
    @ManyToOne
    @JoinColumn(name = "cargo_id")
    private Cargo cargo;

    public Funcionario(String nome, String sobrenome, Date dataNascimento, String cpf, String rg, String endereco, String cidade, String estado, Boolean status) {
        super(nome, sobrenome, dataNascimento, cpf, rg, endereco, cidade, estado, status);
    }

    public Funcionario(){
        super();
    }
    @Override
    public Integer getId() {
        return id;
    }

    public Date getDataAdmissao() {
        return dataAdmissao;
    }

    public void setDataAdmissao(Date dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }

    public Date getDataDesligamento() {
        return dataDesligamento;
    }

    public void setDataDesligamento(Date dataDesligamento) {
        this.dataDesligamento = dataDesligamento;
    }

    public Cargo getCargo(){
        return cargo;
    }

    public void setCargo(Cargo cargo){
        this.cargo = cargo;
    }
}
