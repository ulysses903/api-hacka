package br.com.payment.application.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class ResourceNotFoundTest {

    @Test
    void testResourceNotFoundMessage() {
        String expectedMessage = "Resource not found.";

        ResourceNotFound exception = new ResourceNotFound(expectedMessage);
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void testResourceNotFoundDefaultMessage() {
        ResourceNotFound exception = new ResourceNotFound();

        assertEquals(null, exception.getMessage());
    }

    @Test
    void testResourceNotFoundIsThrown() {
        String expectedMessage = "Resource not found";
        ResourceNotFound exception = assertThrows(ResourceNotFound.class, () -> {
            throw new ResourceNotFound(expectedMessage);
        });

        assertEquals(expectedMessage, exception.getMessage());
    }
}

