package com.involves.selecao.alerta;

public enum EnumTipoAlerta {
    RUPTURA(1),
    ACIMA_ESTIPULADO(2),
    ABAIXO_ESTIPULADO(3);

    private final int value;

    EnumTipoAlerta(final int newValue){
        value = newValue;
    }

    public int getValue() {
        return value;
    }
}