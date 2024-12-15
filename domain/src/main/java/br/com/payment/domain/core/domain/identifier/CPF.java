package br.com.payment.domain.core.domain.identifier;

import java.io.Serializable;

public class CPF implements Serializable {
    private final String cpf;

    public CPF(String cpf){
        this.cpf = cpf;
    }

    public String getValue() {
        return cpf;
    }
}