package br.com.payment.application.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class NoResourceFoundExceptionTest {

    @Test
    void testNoResourceFoundExceptionCode() {
        NoResourceFoundException exception = new NoResourceFoundException("Custom resource not found.");
        assertEquals(404, exception.getCode());
    }

    @Test
    void testNoResourceFoundExceptionMessage() {
        String expectedMessage = "Custom resource not found.";

        NoResourceFoundException exception = new NoResourceFoundException(expectedMessage);
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void testNoResourceFoundExceptionDefaultMessage() {
        NoResourceFoundException exception = new NoResourceFoundException();
        assertEquals("Not Found", exception.getMessage());
    }

    @Test
    void testNoResourceFoundExceptionIsThrown() {
        String expectedMessage = "Resource not found";
        NoResourceFoundException exception = assertThrows(NoResourceFoundException.class, () -> {
            throw new NoResourceFoundException(expectedMessage);
        });

        assertEquals(expectedMessage, exception.getMessage());
    }
}

