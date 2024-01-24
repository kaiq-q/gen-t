package com.example.gent.entity;

import com.example.gent.entity.auxiliary.CargaFuncionario;
import com.example.gent.enums.StatusCarga;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Carga", schema = "KaiqueTraining")
public class Carga {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Integer id;
        @ManyToOne
        @JoinColumn(name = "endereco_origem_id", referencedColumnName = "id")
        private Endereco enderecoOrigem;

        @ManyToOne
        @JoinColumn(name = "endereco_destino_id", referencedColumnName = "id")
        private Endereco enderecoDestino;

        @ManyToOne
        @JoinColumn(name = "cliente_id")
        private Cliente cliente;

        @ManyToOne
        @JoinColumn(name = "funcionario_motorista_id",referencedColumnName = "id")
        private Funcionario motorista;

        @OneToMany
        @JoinTable(name = "CargaFuncionario",
                   joinColumns = @JoinColumn(name = "id"),
                   inverseJoinColumns = @JoinColumn(name = "funcionario_id")
        )
        private List<CargaFuncionario> funcionarios;

        @Enumerated(EnumType.STRING)
        private StatusCarga statusCarga;
        private double  cargaValor;
        private Date    dataEntrega;

        public Carga() {
                this.funcionarios = new ArrayList<>();
        }

}

