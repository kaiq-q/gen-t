package com.example.gent.entity;

import jakarta.persistence.*;


@Entity
@Table(name = "Cargo", schema = "KaiqueTraining")
public class Cargo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(nullable = false)
    private String info;
    @Column(columnDefinition = "bit default 0")
    private Boolean status;

    public Cargo(){

    }

    public Integer getId() {
        return id;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
