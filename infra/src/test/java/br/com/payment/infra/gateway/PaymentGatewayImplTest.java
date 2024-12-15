package br.com.payment.infra.gateway;

import br.com.payment.domain.core.domain.entities.internal.Payment;
import br.com.payment.domain.core.domain.entities.internal.StatusPayment;
import br.com.payment.infra.entity.PaymentEntity;
import br.com.payment.infra.exception.PaymentNotFoundException;
import br.com.payment.infra.gateways.PaymentGatewayImpl;
import br.com.payment.infra.mapper.PaymentMapper;
import br.com.payment.infra.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PaymentGatewayImplTest {

    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private PaymentMapper mapper;

    @InjectMocks
    private PaymentGatewayImpl paymentGateway;

    private Payment payment;
    private PaymentEntity paymentEntity;

    @BeforeEach
    void setUp() {
        payment = Payment.builder()
                .id("123")
                .amount(BigDecimal.valueOf(10.0))
                .order("1")
                .status(StatusPayment.PENDING)
                .qrCode("qrcode")
                .date(LocalDateTime.now())
                .build();


        paymentEntity = PaymentEntity.builder()
                .id("123")
                .amount(BigDecimal.valueOf(10.0))
                .order("1")
                .status(StatusPayment.PENDING)
                .paymentAt(LocalDateTime.now())
                .build();
    }

    @Test
    void testSave() {
        when(paymentRepository.save(Mockito.any())).thenReturn(paymentEntity);

        Optional<Payment> result = paymentGateway.save(payment);

        assertTrue(result.isPresent());
        assertEquals(payment.getId(), result.get().getId());
    }

    @Test
    void testUpdate() {
        when(paymentRepository.save(Mockito.any())).thenReturn(paymentEntity);

        Optional<Payment> result = paymentGateway.update(payment);

        assertTrue(result.isPresent());
        assertEquals(payment.getId(), result.get().getId());
        assertEquals(StatusPayment.PENDING, payment.getStatus());
    }

    @Test
    void testFindById() {
        String id = "payment-id";
        when(paymentRepository.findById(id)).thenReturn(Optional.of(paymentEntity));

        Optional<Payment> result = paymentGateway.findById(id);

        assertTrue(result.isPresent());
        assertEquals(payment.getId(), result.get().getId());
        verify(paymentRepository).findById(id);
    }

    @Test
    void testFindById_PaymentNotFoundException() {
        String id = "payment-id";
        when(paymentRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(PaymentNotFoundException.class, () -> paymentGateway.findById(id));
        verify(paymentRepository).findById(id);
    }

    @Test
    void testFindAll() {
        when(paymentRepository.findAll()).thenReturn(List.of(paymentEntity));

        Collection<Payment> result = paymentGateway.findAll();

        assertEquals(1, result.size());
        assertFalse(result.contains(payment));
        verify(paymentRepository).findAll();
    }
}

