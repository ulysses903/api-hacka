package br.com.payment.application.usecase;

import br.com.payment.application.gateway.IntegrationLinkPaymentGateway;
import br.com.payment.application.inout.input.PaymentInput;
import br.com.payment.application.usecase.payment.CreatePaymentUseCase;
import br.com.payment.domain.core.domain.entities.external.PointOfInteraction;
import br.com.payment.domain.core.domain.entities.external.QrCode;
import br.com.payment.domain.core.domain.entities.external.TransactionData;
import br.com.payment.domain.core.domain.entities.internal.Payment;
import br.com.payment.domain.gateway.PaymentGateway;
import br.com.payment.infra.feign.presenter.request.PaymentRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class CreatePaymentUseCaseTest {

    @InjectMocks
    private CreatePaymentUseCase createPaymentUseCase;

    @Mock
    private IntegrationLinkPaymentGateway integrationLinkPaymentGateway;

    @Mock
    private PaymentGateway paymentGateway;

    private QrCode qrCode;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        qrCode = QrCode.builder()
                .status("PENDING")
                .pointOfInteraction(PointOfInteraction.builder()
                        .transactionData(TransactionData.builder()
                                .qrCode("iVBORw0KGgoAAAANSUhEUgAABWQAAAVkAQAAAAB79iscAAAOTklEQVR4Xu3XS5YbOw5F0ZiB5z9L")
                                .build())
                        .build())
                .build();
    }

    @Test
    void testExecuteWithIdentification() {
        PaymentInput paymentInput = new PaymentInput(1L, BigDecimal.valueOf(10), "mail@mail.com");

        when(integrationLinkPaymentGateway.generatedQrCode(any(PaymentRequest.class)))
                .thenReturn(qrCode);

        Payment mockPayment = mock(Payment.class);
        when(paymentGateway.save(any(Payment.class)))
                .thenReturn(Optional.of(mockPayment));

        Optional<Payment> result = createPaymentUseCase.execute(paymentInput);

        assertTrue(result.isPresent());
        assertEquals(mockPayment, result.get());

        verify(integrationLinkPaymentGateway, times(1)).generatedQrCode(any(PaymentRequest.class));
        verify(paymentGateway, times(1)).save(any(Payment.class));
    }

    @Test
    void testExecuteWithoutIdentification() {
        PaymentInput paymentInput = new PaymentInput(1L, BigDecimal.valueOf(10), null);

        when(integrationLinkPaymentGateway.generatedQrCode(any(PaymentRequest.class)))
                .thenReturn(qrCode);

        Payment mockPayment = mock(Payment.class);
        when(paymentGateway.save(any(Payment.class)))
                .thenReturn(Optional.of(mockPayment));

        Optional<Payment> result = createPaymentUseCase.execute(paymentInput);

        assertTrue(result.isPresent());
        assertEquals(mockPayment, result.get());

        verify(integrationLinkPaymentGateway, times(1)).generatedQrCode(any(PaymentRequest.class));
        verify(paymentGateway, times(1)).save(any(Payment.class));
    }

    @Test
    void testExecuteWithFailedPaymentSave() {
        PaymentInput paymentInput = new PaymentInput(1L, BigDecimal.valueOf(10), null);

        when(integrationLinkPaymentGateway.generatedQrCode(any(PaymentRequest.class)))
                .thenReturn(qrCode);

        when(paymentGateway.save(any(Payment.class)))
                .thenReturn(Optional.empty());

        Optional<Payment> result = createPaymentUseCase.execute(paymentInput);

        assertFalse(result.isPresent());

        verify(integrationLinkPaymentGateway, times(1)).generatedQrCode(any(PaymentRequest.class));
        verify(paymentGateway, times(1)).save(any(Payment.class));
    }
}

