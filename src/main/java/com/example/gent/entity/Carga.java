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

        public Integer getId() {
                return id;
        }

        public void setId(Integer id) {
                this.id = id;
        }

        public Endereco getEnderecoOrigem() {
                return enderecoOrigem;
        }

        public void setEnderecoOrigem(Endereco enderecoOrigem) {
                this.enderecoOrigem = enderecoOrigem;
        }

        public Endereco getEnderecoDestino() {
                return enderecoDestino;
        }

        public void setEnderecoDestino(Endereco enderecoDestino) {
                this.enderecoDestino = enderecoDestino;
        }

        public Cliente getCliente() {
                return cliente;
        }

        public void setCliente(Cliente cliente) {
                this.cliente = cliente;
        }

        public List<CargaFuncionario> getFuncionarios() {
                return funcionarios;
        }

        public void setFuncionarios(List<CargaFuncionario> funcionarios) {
                this.funcionarios = funcionarios;
        }

        public StatusCarga getStatusCarga() {
                return statusCarga;
        }

        public void setStatusCarga(StatusCarga statusCarga) {
                this.statusCarga = statusCarga;
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

