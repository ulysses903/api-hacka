package br.com.payment.application.usecase;

import br.com.payment.domain.core.domain.entities.internal.Payment;
import br.com.payment.domain.core.domain.entities.internal.StatusPayment;
import br.com.payment.domain.gateway.PaymentGateway;
import br.com.payment.application.usecase.payment.GetByIdPaymentUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class GetByIdPaymentUseCaseTest {

    @InjectMocks
    private GetByIdPaymentUseCase getByIdPaymentUseCase;

    @Mock
    private PaymentGateway paymentGateway;

    private Payment mockPayment;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        mockPayment = Payment.builder()
                .id("123")
                .amount(BigDecimal.valueOf(10.0))
                .order("1")
                .status(StatusPayment.PENDING)
                .qrCode("qrcode")
                .date(LocalDateTime.now())
                .build();
    }

    @Test
    void testExecuteWithPaymentFound() {
        when(paymentGateway.findById(anyString())).thenReturn(Optional.of(mockPayment));

        Optional<Payment> result = getByIdPaymentUseCase.execute("123");

        assertTrue(result.isPresent(), "Payment should be present");
        assertEquals(mockPayment, result.get(), "Returned payment should match the mock payment");

        verify(paymentGateway, times(1)).findById(anyString());
    }

    @Test
    void testExecuteWithPaymentNotFound() {
        when(paymentGateway.findById(anyString())).thenReturn(Optional.empty());

        Optional<Payment> result = getByIdPaymentUseCase.execute("123");

        assertFalse(result.isPresent(), "Payment should not be present");
        verify(paymentGateway, times(1)).findById(anyString());
    }
}
