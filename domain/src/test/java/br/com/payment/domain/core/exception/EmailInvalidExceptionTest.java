package br.com.payment.domain.core.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EmailInvalidExceptionTest {

    @Test
    void testExceptionMessage() {
        EmailInvalidException exception = new EmailInvalidException();

        assertEquals("Email is invalid", exception.getMessage());
    }
}

