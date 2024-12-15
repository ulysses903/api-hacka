package br.com.payment.domain.core.domain.entities.external;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QrCodeTest {

    @Test
    void testQrCodeGetQrCodeMethod() {
        TransactionData transactionData = TransactionData.builder()
                .qrCode("sample-qr-code")
                .build();

        PointOfInteraction pointOfInteraction = PointOfInteraction.builder()
                .transactionData(transactionData)
                .build();

        QrCode qrCode = QrCode.builder()
                .status("ACTIVE")
                .pointOfInteraction(pointOfInteraction)
                .build();

        assertEquals("sample-qr-code", qrCode.getQrCode());
    }

    @Test
    void testQrCodeStatus() {
        QrCode qrCode = new QrCode();
        qrCode.setStatus("INACTIVE");

        assertEquals("INACTIVE", qrCode.getStatus());
    }

    @Test
    void testTransactionDataQrCode() {
        TransactionData transactionData = new TransactionData();
        transactionData.setQrCode("test-qr-code");

        assertEquals("test-qr-code", transactionData.getQrCode());
    }

    @Test
    void testPointOfInteractionTransactionData() {
        TransactionData transactionData = TransactionData.builder()
                .qrCode("transaction-qr-code")
                .build();

        PointOfInteraction pointOfInteraction = PointOfInteraction.builder()
                .transactionData(transactionData)
                .build();

        assertEquals("transaction-qr-code", pointOfInteraction.getTransactionData().getQrCode());
    }

    @Test
    void testQrCodeToString() {
        QrCode qrCode = QrCode.builder()
                .status("ACTIVE")
                .pointOfInteraction(null)
                .build();

        assertTrue(qrCode.toString().contains("ACTIVE"));
    }
}

