package br.com.payment.infra.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PaymentNotFoundExceptionTest {

    @Test
    public void testConstructor_WithMessage() {
        String expectedMessage = "Pagamento não encontrado.";

        PaymentNotFoundException exception = new PaymentNotFoundException(expectedMessage);
        assertEquals(expectedMessage, exception.getMessage(), "A mensagem da exceção não corresponde.");
    }

    @Test
    public void testThrowException_WithMessage() {
        String expectedMessage = "Pagamento não encontrado.";

        PaymentNotFoundException exception = assertThrows(PaymentNotFoundException.class, () -> {
            throw new PaymentNotFoundException(expectedMessage);
        });
        assertEquals(expectedMessage, exception.getMessage(), "A mensagem da exceção não corresponde.");
    }
}
