package br.com.payment.application.controllers;

import br.com.payment.application.facade.PaymentFacade;
import br.com.payment.application.inout.input.FilterInput;
import br.com.payment.application.inout.input.PaymentUpdateInput;
import br.com.payment.application.inout.output.PaymentBalanceOutput;
import br.com.payment.application.inout.output.PaymentOutput;
import br.com.payment.domain.core.domain.entities.internal.StatusPayment;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PaymentControllerTest {

    private MockMvc mockMvc;

    @Mock
    private PaymentFacade paymentFacade;

    @InjectMocks
    private PaymentController paymentController;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(paymentController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void shouldUpdatePayment() throws Exception {

        String paymentUpdateInput = new String(Files.readAllBytes(Paths.get(
                new ClassPathResource("update_payment_input_mock.json")
                        .getURI())));

        String paymentOutput = new String(Files.readAllBytes(Paths.get(
                new ClassPathResource("update_output_payment_mock.json")
                        .getURI())));

        PaymentOutput expectedPaymentOutput = objectMapper.readValue(paymentOutput, PaymentOutput.class);

        when(paymentFacade.updateProcessPayment(any(PaymentUpdateInput.class))).thenReturn(expectedPaymentOutput);

        mockMvc.perform(put("/api/v1/payment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(paymentUpdateInput))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("507f191e810c19729de860ea"))
                .andExpect(jsonPath("$.amount").value(15.0))
                .andExpect(jsonPath("$.status").value(StatusPayment.PAID.name()));
    }

    @Test
    void shouldGetPayment() throws Exception {

        String paymentBalanceOutput = new String(Files.readAllBytes(Paths.get(
                new ClassPathResource("get_output_payment_mock.json")
                        .getURI())));

        PaymentBalanceOutput expectedPaymentOutput = objectMapper.readValue(paymentBalanceOutput, PaymentBalanceOutput.class);

        when(paymentFacade.getPayment(anyString())).thenReturn(expectedPaymentOutput);

        mockMvc.perform(get("/api/v1/payment/{identifierPayment}", "12345"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("671d9e96f44cc326000548b3"))
                .andExpect(jsonPath("$.amount").value(10.0))
                .andExpect(jsonPath("$.status").value(StatusPayment.REJECT.name()));
    }

    @Test
    void shouldReturnListOfPayments() throws Exception {

        PaymentBalanceOutput payment = new PaymentBalanceOutput("12", BigDecimal.TEN, null, StatusPayment.PENDING, null, "1"); // Substitua com a instância correta
        List<PaymentBalanceOutput> payments = Collections.singletonList(payment);
        Page<PaymentBalanceOutput> paymentPage = new PageImpl<>(payments, PageRequest.of(0, 10), 1);

        when(paymentFacade.filter(any(FilterInput.class))).thenReturn(paymentPage);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/payment")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content.length()").value(1));
    }
}
