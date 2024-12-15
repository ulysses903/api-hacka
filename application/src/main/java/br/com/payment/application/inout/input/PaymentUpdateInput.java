package br.com.payment.application.inout.input;


import br.com.payment.domain.core.domain.entities.internal.StatusPayment;
import jakarta.validation.constraints.NotNull;

public record PaymentUpdateInput (

        @NotNull(message = "id payment must be provided")
        String id,

        @NotNull(message = "status must be provided")
        StatusPayment status

){}
