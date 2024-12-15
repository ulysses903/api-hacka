package br.com.payment.application.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class MercadoPagoIntegrationExceptionTest {

    @Test
    void testMercadoPagoIntegrationExceptionMessage() {
        String expectedMessage = "Erro na integração com o MercadoPago.";

        MercadoPagoIntegrationException exception = new MercadoPagoIntegrationException(expectedMessage);

        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void testMercadoPagoIntegrationExceptionIsThrown() {
        String expectedMessage = "Erro na integração com o MercadoPago.";

        MercadoPagoIntegrationException exception = assertThrows(MercadoPagoIntegrationException.class, () -> {
            throw new MercadoPagoIntegrationException(expectedMessage);
        });

        assertEquals(expectedMessage, exception.getMessage());
    }
}

