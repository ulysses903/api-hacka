package br.com.payment.application.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class NoInputPresentTest {

    @Test
    void testNoInputPresentMessage() {
        NoInputPresent exception = new NoInputPresent();
        assertEquals(NoInputPresent.MESSAGE, exception.getMessage());
    }

    @Test
    void testNoInputPresentIsThrown() {
        NoInputPresent exception = assertThrows(NoInputPresent.class, () -> {
            throw new NoInputPresent();
        });

        assertEquals(NoInputPresent.MESSAGE, exception.getMessage());
    }
}

