package br.com.payment.application.usecase;

import br.com.payment.application.inout.input.FilterInput;
import br.com.payment.application.usecase.payment.FilterPaymentUseCase;
import br.com.payment.domain.core.domain.entities.internal.Payment;
import br.com.payment.domain.core.domain.entities.internal.StatusPayment;
import br.com.payment.domain.gateway.PaymentGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class FilterPaymentUseCaseTest {

    @InjectMocks
    private FilterPaymentUseCase filterPaymentUseCase;

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
    void testExecuteWithPaymentsFound() {
        List<Payment> paymentList = Arrays.asList(mockPayment);
        //Page<Payment> page = new PageImpl<>(paymentList);

        when(paymentGateway.findAll()).thenReturn(paymentList);

        FilterInput filterInput = new FilterInput(Collections.emptyMap());

        Optional<Page<Payment>> result = filterPaymentUseCase.execute(filterInput);

        assertTrue(result.isPresent(), "The result should be present");
        assertEquals(1, result.get().getTotalElements(), "The result page should contain 1 payment");
        assertEquals(mockPayment, result.get().getContent().get(0), "The payment should match the mock payment");

        verify(paymentGateway, times(1)).findAll();
    }

    @Test
    void testExecuteWithNoPaymentsFound() {
        when(paymentGateway.findAll()).thenReturn(Collections.emptyList());

        FilterInput filterInput = new FilterInput(Collections.emptyMap());

        Optional<Page<Payment>> result = filterPaymentUseCase.execute(filterInput);

        assertTrue(result.isPresent(), "The result should be present");
        assertEquals(0, result.get().getTotalElements(), "The result page should be empty");

        verify(paymentGateway, times(1)).findAll();
    }
}

