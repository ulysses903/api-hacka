package br.com.payment.application.inout.input;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;


public record PaymentInput(

        @NotNull(message = "order must be provided")
        @Min(value = 1, message = "order must be greater than 0")
        Long order,

        @NotNull(message = "amount must be provided")
        @DecimalMin(value = "0.01", message = "amount must be greater than 0")
        BigDecimal amount,

        String identification
){}
