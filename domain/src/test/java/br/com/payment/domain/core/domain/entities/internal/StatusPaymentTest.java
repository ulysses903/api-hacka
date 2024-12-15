package br.com.payment.domain.core.domain.entities.internal;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.EnumSet;

import static org.junit.jupiter.api.Assertions.*;

class StatusPaymentTest {

    @Test
    void testEnumValues() {
        assertEquals(4, StatusPayment.values().length);
        assertTrue(EnumSet.of(StatusPayment.PENDING, StatusPayment.REJECT, StatusPayment.PAID, StatusPayment.EXPIRED).containsAll(Arrays.asList(StatusPayment.values())));
    }

    @Test
    void testEnumValueOf() {
        assertEquals(StatusPayment.PENDING, StatusPayment.valueOf("PENDING"));
        assertEquals(StatusPayment.REJECT, StatusPayment.valueOf("REJECT"));
        assertEquals(StatusPayment.PAID, StatusPayment.valueOf("PAID"));
        assertEquals(StatusPayment.EXPIRED, StatusPayment.valueOf("EXPIRED"));
    }

    @Test
    void testEnumOrdinal() {
        assertEquals(0, StatusPayment.PENDING.ordinal());
        assertEquals(1, StatusPayment.REJECT.ordinal());
        assertEquals(2, StatusPayment.PAID.ordinal());
        assertEquals(3, StatusPayment.EXPIRED.ordinal());
    }
}

