package br.com.payment.application.inout.output;

import br.com.payment.domain.core.domain.entities.internal.StatusPayment;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record PaymentBalanceOutput(

        String id,

        BigDecimal amount,

        String identification,

        StatusPayment status,

        String paymentAt,
        String order){}
