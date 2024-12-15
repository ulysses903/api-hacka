package br.com.payment.application.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import br.com.payment.infra.exception.PaymentNotFoundException;
import org.junit.jupiter.api.Test;

public class ApiErrorExceptionTest {

    @Test
    void testPaymentNotFoundExceptionCode() {
        PaymentNotFoundException exception = new PaymentNotFoundException("Payment with ID 123 not found.");
        assertEquals("Payment with ID 123 not found.", exception.getMessage());
    }

    @Test
    void testPaymentNotFoundExceptionMessage() {
        String expectedMessage = "Payment with ID 123 not found.";

        PaymentNotFoundException exception = new PaymentNotFoundException(expectedMessage);

        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void testPaymentNotFoundExceptionIsThrown() {
        String expectedMessage = "Payment with ID 123 not found.";
        PaymentNotFoundException exception = assertThrows(PaymentNotFoundException.class, () -> {
            throw new PaymentNotFoundException(expectedMessage);
        });

        assertEquals(expectedMessage, exception.getMessage());
    }
}

