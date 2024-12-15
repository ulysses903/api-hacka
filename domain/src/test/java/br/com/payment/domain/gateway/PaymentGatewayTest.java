package br.com.payment.domain.core.domain.gateway;

import br.com.payment.domain.core.domain.entities.internal.Payment;
import br.com.payment.domain.core.domain.entities.internal.StatusPayment;
import br.com.payment.domain.gateway.PaymentGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PaymentGatewayTest {

    private PaymentGateway paymentGateway;
    private Payment payment;

    @BeforeEach
    void setUp() {
        paymentGateway = Mockito.mock(PaymentGateway.class);
        payment = Payment.builder()
                .id("123")
                .amount(BigDecimal.valueOf(10.0))
                .order("1")
                .status(StatusPayment.PENDING)
                .qrCode("qrcode")
                .date(LocalDateTime.now())
                .build();
    }

    @Test
    void testSave() {
        when(paymentGateway.save(payment)).thenReturn(Optional.of(payment));

        Optional<Payment> result = paymentGateway.save(payment);
        assertTrue(result.isPresent());
        assertEquals(payment, result.get());
    }

    @Test
    void testUpdate() {
        when(paymentGateway.update(payment)).thenReturn(Optional.of(payment));

        Optional<Payment> result = paymentGateway.update(payment);
        assertTrue(result.isPresent());
        assertEquals(payment, result.get());
    }

    @Test
    void testFindById() {
        String paymentId = "12345";
        when(paymentGateway.findById(paymentId)).thenReturn(Optional.of(payment));

        Optional<Payment> result = paymentGateway.findById(paymentId);
        assertTrue(result.isPresent());
        assertEquals(payment, result.get());
    }

    @Test
    void testFindAll() {
        Collection<Payment> payments = Arrays.asList(payment, Payment.builder()
                .id("123")
                .amount(BigDecimal.valueOf(10.0))
                .order("1")
                .status(StatusPayment.PENDING)
                .qrCode("qrcode")
                .date(LocalDateTime.now())
                .build());
        when(paymentGateway.findAll()).thenReturn(payments);

        Collection<Payment> result = paymentGateway.findAll();
        assertNotNull(result);
        assertEquals(2, result.size());
    }
}

