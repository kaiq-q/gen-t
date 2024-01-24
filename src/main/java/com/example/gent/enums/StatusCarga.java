package com.example.gent.enums;

public enum StatusCarga {
    ENTREGUE(1, "Entregue"),
    CANCELADA(2, "Cancelada"),
    TRANSITO(3, "Em transito"),
    FURTADA(4, "Furtada"),
    PENDENTE(5, "Pendente"),
    CARREGAMENTO(6, "Carregamento");

    private final int value;
    private final String description;

    StatusCarga(int value, String description) {
        this.value = value;
        this.description = description;
    }
    public int getValue() {
        return value;
    }

    public String getDescription(){
        return description;
    }
}
