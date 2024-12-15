package br.com.payment.domain.core.domain.identifier;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CPFTest {

    @Test
    public void testGetValue() {
        String validCpf = "12345678901";

        CPF cpf = new CPF(validCpf);

        assertEquals(validCpf, cpf.getValue(), "O valor do CPF retornado não é o esperado.");
    }
}

