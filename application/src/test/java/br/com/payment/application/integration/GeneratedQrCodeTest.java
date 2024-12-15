package br.com.payment.application.integration;

import br.com.payment.application.exception.MercadoPagoIntegrationException;
import br.com.payment.application.integration.GeneratedQrCode;
import br.com.payment.domain.core.domain.entities.external.PointOfInteraction;
import br.com.payment.domain.core.domain.entities.external.QrCode;
import br.com.payment.domain.core.domain.entities.external.TransactionData;
import br.com.payment.domain.core.domain.entities.internal.StatusPayment;
import br.com.payment.infra.feign.client.MercadoPagoQrCodeClient;
import br.com.payment.infra.feign.presenter.request.PaymentRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class GeneratedQrCodeTest {

    @InjectMocks
    private GeneratedQrCode generatedQrCode;

    @Mock
    private MercadoPagoQrCodeClient client;

    private PaymentRequest mockPaymentRequest;
    private QrCode mockQrCode;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        mockPaymentRequest = PaymentRequest.builder()
                .transactionAmount(BigDecimal.valueOf(100))
                .build();

        mockQrCode = QrCode.builder()
                .status("PENDING")
                .pointOfInteraction(PointOfInteraction.builder()
                        .transactionData(TransactionData.builder()
                                .qrCode("iVBORw0KGgoAAAANSUhEUgAABWQAAAVkAQAAAAB79iscAAAOTklEQVR4Xu3XS5YbOw5F0ZiB5z9L")
                                .build())
                        .build())
                .build();
    }

    @Test
    void testGeneratedQrCodeSuccess() {
        when(client.generatedQrCode(any(PaymentRequest.class))).thenReturn(mockQrCode);

        QrCode result = generatedQrCode.generatedQrCode(mockPaymentRequest);

        assertNotNull(result, "QrCode should not be null");
        assertEquals(StatusPayment.PENDING.name(), result.getStatus(), "QrCode status should be SUCCESS");

        verify(client, times(1)).generatedQrCode(any(PaymentRequest.class));
    }

    @Test
    void testGeneratedQrCodeFailure() {
        when(client.generatedQrCode(any(PaymentRequest.class))).thenThrow(new RuntimeException("Integration failed"));

        MercadoPagoIntegrationException exception = assertThrows(MercadoPagoIntegrationException.class, () -> {
            generatedQrCode.generatedQrCode(mockPaymentRequest);
        });

        assertEquals("Integração com mercado pago falhou", exception.getMessage(), "Exception message should match");

        verify(client, times(1)).generatedQrCode(any(PaymentRequest.class));
    }
}

