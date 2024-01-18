package com.example.gent.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "Pessoa", schema = "KaiqueTraining")
@Inheritance(strategy = InheritanceType.JOINED)
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String nome;
    private String sobrenome;
    private Date dataNascimento;
    private String cpf;
    private String rg;
    private String endereco;
    private String cidade;
    private String estado;

    private Boolean status;



    public Pessoa(String nome, String sobrenome, Date dataNascimento, String cpf, String rg, String endereco, String cidade, String estado, Boolean status) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
        this.rg = rg;
        this.endereco = endereco;
        this.cidade = cidade;
        this.estado = estado;
        this.status = status;
    }

    public Pessoa() {

    }

    public Integer getId() {
        return id;
    }
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Boolean getStatus(){
        return status;
    }

    public void setStatus(Boolean status){
        this.status = status;
    }

    public String getFormattedCpf(){
        if (cpf == null){
            return null;
        }else{
            return cpf;
        }
    }

    public String getFormattedRg(){
        if(rg == null){
            return null;
        }else{
            return rg;
        }
    }
}
