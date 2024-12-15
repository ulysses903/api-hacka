package br.com.payment.application.usecase;

import br.com.payment.application.inout.input.PaymentUpdateInput;
import br.com.payment.domain.core.domain.entities.internal.Payment;
import br.com.payment.domain.core.domain.entities.internal.StatusPayment;
import br.com.payment.domain.gateway.PaymentGateway;
import br.com.payment.application.usecase.payment.UpdateProcessPaymentCase;
import br.com.payment.application.usecase.payment.GetByIdPaymentUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class UpdateProcessPaymentCaseTest {

    @InjectMocks
    private UpdateProcessPaymentCase updateProcessPaymentCase;

    @Mock
    private GetByIdPaymentUseCase getByIdPaymentUseCase;

    @Mock
    private PaymentGateway paymentGateway;

    private Payment mockPayment;
    private PaymentUpdateInput mockPaymentUpdateInput;

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

        mockPaymentUpdateInput = new PaymentUpdateInput("123", StatusPayment.PAID);
    }

    @Test
    void testExecuteWithPaymentFoundAndUpdated() {
        when(getByIdPaymentUseCase.execute(anyString())).thenReturn(Optional.of(mockPayment));

        when(paymentGateway.update(any(Payment.class))).thenReturn(Optional.of(mockPayment));

        Optional<Payment> result = updateProcessPaymentCase.execute(mockPaymentUpdateInput);

        assertTrue(result.isPresent(), "Payment should be updated and present");
        assertEquals(StatusPayment.PAID, result.get().getStatus(), "Payment status should be updated to PROCESSED");

        // Verifying interaction with mocks
        verify(getByIdPaymentUseCase, times(1)).execute(anyString());
        verify(paymentGateway, times(1)).update(any(Payment.class));
    }

    @Test
    void testExecuteWithPaymentNotFound() {
        // Mocking the GetByIdPaymentUseCase to return Optional.empty()
        when(getByIdPaymentUseCase.execute(anyString())).thenReturn(Optional.empty());

        // Execute the use case
        Optional<Payment> result = updateProcessPaymentCase.execute(mockPaymentUpdateInput);

        // Verifying the behavior
        assertFalse(result.isPresent(), "Payment should not be updated, as no payment was found");

        // Verifying interaction with mocks
        verify(getByIdPaymentUseCase, times(1)).execute(anyString());
        verify(paymentGateway, times(0)).update(any(Payment.class));  // Verify that update is not called
    }

    @Test
    void testExecuteWithUpdateFailed() {
        when(getByIdPaymentUseCase.execute(anyString())).thenReturn(Optional.of(mockPayment));
        when(paymentGateway.update(any(Payment.class))).thenReturn(Optional.empty());

        Optional<Payment> result = updateProcessPaymentCase.execute(mockPaymentUpdateInput);

        assertFalse(result.isPresent(), "Payment update should fail and return Optional.empty()");

        verify(getByIdPaymentUseCase, times(1)).execute(anyString());
        verify(paymentGateway, times(1)).update(any(Payment.class));
    }
}

