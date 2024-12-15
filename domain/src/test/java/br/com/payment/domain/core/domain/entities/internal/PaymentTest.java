package br.com.payment.domain.core.domain.entities.internal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {

    private Payment payment;

    @BeforeEach
    void setUp() {
        payment = Payment.builder()
                .id("12345")
                .amount(new BigDecimal("100.00"))
                .identification("ID123")
                .status(StatusPayment.PAID)
                .qrCode("sample-qr-code")
                .order("order-123")
                .date(LocalDateTime.of(2023, 1, 1, 12, 0))
                .build();
    }

    @Test
    void testPaymentCreation() {
        assertEquals("12345", payment.getId());
        assertEquals(new BigDecimal("100.00"), payment.getAmount());
        assertEquals("ID123", payment.getIdentification());
        assertEquals(StatusPayment.PAID, payment.getStatus());
        assertEquals("sample-qr-code", payment.getQrCode());
        assertEquals("order-123", payment.getOrder());
        assertEquals(LocalDateTime.of(2023, 1, 1, 12, 0), payment.getDate());
    }

    @Test
    void testSetStatusPending() {
        payment.setStatusPending();

        assertEquals(StatusPayment.PENDING, payment.getStatus());
        assertNotNull(payment.getDate());
    }

    @Test
    void testSetProcessPayment() {
        payment.setProcessPayment(StatusPayment.REJECT);

        assertEquals(StatusPayment.REJECT, payment.getStatus());
        assertNotNull(payment.getDate());
        assertTrue(LocalDateTime.now().isAfter(payment.getDate()) || LocalDateTime.now().isEqual(payment.getDate()));
    }

    @Test
    void testSetQrCode() {
        payment.setQrCode("new-qr-code");

        assertEquals("new-qr-code", payment.getQrCode());
    }

    @Test
    void testEqualsAndHashCode() {
        Payment samePayment = Payment.builder()
                .id("12345")
                .amount(new BigDecimal("100.00"))
                .identification("ID123")
                .status(StatusPayment.PAID)
                .qrCode("sample-qr-code")
                .order("order-123")
                .date(LocalDateTime.of(2023, 1, 1, 12, 0))
                .build();

        assertEquals(payment, samePayment);
        assertEquals(payment.hashCode(), samePayment.hashCode());
    }
}
