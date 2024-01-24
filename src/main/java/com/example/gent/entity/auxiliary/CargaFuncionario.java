package com.example.gent.entity.auxiliary;

import com.example.gent.entity.Carga;
import com.example.gent.entity.Funcionario;
import jakarta.persistence.*;

@Entity
@Table(name = "CargaFuncionario", schema = "KaiqueTraining")
public class CargaFuncionario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "carga_id")
    private Carga carga;

    @OneToOne
    @JoinColumn(name = "motorista_id", referencedColumnName = "id")
    private Funcionario motorista;

    @ManyToOne
    @JoinColumn(name = "ajudante_id", referencedColumnName = "id")
    private Funcionario ajudante;

    public CargaFuncionario(){

    }

}
