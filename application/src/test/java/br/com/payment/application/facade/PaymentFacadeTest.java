package br.com.payment.application.facade;

import br.com.payment.application.exception.NoResourceFoundException;
import br.com.payment.application.inout.input.FilterInput;
import br.com.payment.application.inout.input.PaymentInput;
import br.com.payment.application.inout.input.PaymentUpdateInput;
import br.com.payment.application.inout.output.PaymentBalanceOutput;
import br.com.payment.application.inout.output.PaymentOutput;
import br.com.payment.application.usecase.payment.CreatePaymentUseCase;
import br.com.payment.application.usecase.payment.FilterPaymentUseCase;
import br.com.payment.application.usecase.payment.GetByIdPaymentUseCase;
import br.com.payment.application.usecase.payment.UpdateProcessPaymentCase;
import br.com.payment.domain.core.domain.entities.internal.Payment;
import br.com.payment.domain.core.domain.entities.internal.StatusPayment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PaymentFacadeTest {

    @InjectMocks
    private PaymentFacade paymentFacade;

    @Mock
    private CreatePaymentUseCase createPaymentUseCase;

    @Mock
    private FilterPaymentUseCase filterPaymentUseCase;

    @Mock
    private GetByIdPaymentUseCase getByIdPaymentUseCase;

    @Mock
    private UpdateProcessPaymentCase updateProcessPaymentCase;

    @Mock
    private RabbitTemplate rabbitTemplate;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreate() {
        PaymentInput paymentInput = new PaymentInput(1L, BigDecimal.valueOf(10), null);

        when(createPaymentUseCase.execute(any(PaymentInput.class)))
                .thenReturn(Optional.of(Payment.builder()
                        .id("123")
                        .amount(BigDecimal.valueOf(10.0))
                        .order("1")
                        .status(StatusPayment.PENDING)
                        .qrCode("qrcode")
                        .date(LocalDateTime.now())
                        .build()));

        PaymentOutput result = paymentFacade.create(paymentInput);

        assertNotNull(result);
        assertEquals("123", result.id());
        assertEquals(StatusPayment.PENDING, result.status());

        verify(createPaymentUseCase, times(1)).execute(any(PaymentInput.class));
    }

    @Test
    void testUpdateProcessPayment() {
        PaymentUpdateInput paymentUpdateInput = new PaymentUpdateInput("123", StatusPayment.PAID);

        when(updateProcessPaymentCase.execute(any(PaymentUpdateInput.class)))
                .thenReturn(Optional.of(Payment.builder()
                        .id("123")
                        .amount(BigDecimal.valueOf(10.0))
                        .order("1")
                        .status(StatusPayment.PAID)
                        .qrCode("qrcode")
                        .date(LocalDateTime.now())
                        .build()));

        PaymentOutput result = paymentFacade.updateProcessPayment(paymentUpdateInput);

        assertNotNull(result);
        assertEquals("123", result.id());
        assertEquals(StatusPayment.PAID, result.status());

        verify(updateProcessPaymentCase, times(1)).execute(any(PaymentUpdateInput.class));
        verify(rabbitTemplate, times(1)).convertAndSend(anyString(), anyString(), anyString());
    }

    @Test
    void testGetPayment() {

        when(getByIdPaymentUseCase.execute(Mockito.anyString()))
                .thenReturn(Optional.of(Payment.builder()
                        .id("123")
                        .amount(BigDecimal.valueOf(10.0))
                        .order("1")
                        .status(StatusPayment.PAID)
                        .qrCode("qrcode")
                        .date(LocalDateTime.now())
                        .build()));

        PaymentBalanceOutput result = paymentFacade.getPayment("123");

        assertNotNull(result);
        assertEquals("123", result.id());
        assertEquals(StatusPayment.PAID, result.status());

        verify(getByIdPaymentUseCase, times(1)).execute(Mockito.anyString());
    }

    @Test
    void testGetPaymentThrowsExceptionWhenNoResourceFound() {

        when(getByIdPaymentUseCase.execute(Mockito.anyString()))
                .thenReturn(Optional.empty());

        assertThrows(NoResourceFoundException.class, () -> paymentFacade.getPayment("123"));

        verify(getByIdPaymentUseCase, times(1)).execute(Mockito.anyString());
    }

    @Test
    void testFilterWhenNoPaymentsFound() {
        FilterInput filterInput = new FilterInput(Map.of("status", "EXPIRED"));

        when(filterPaymentUseCase.execute(any(FilterInput.class))).thenReturn(Optional.of(Page.empty()));

        Page<PaymentBalanceOutput> result = paymentFacade.filter(filterInput);

        assertNotNull(result);
        assertTrue(result.getContent().isEmpty());

        verify(filterPaymentUseCase, times(1)).execute(any(FilterInput.class));
    }
}
