package br.com.payment.infra.feign.presenter.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class PaymentRequest {

    @JsonProperty("description")
    private final String description = "order payment - tech challenge group 36";

    @JsonProperty("payment_method_id")
    private final String paymentMethodId = "pix";

    @JsonProperty("transaction_amount")
    private BigDecimal transactionAmount;

    @JsonProperty("payer")
    private PayerRequest payerRequest;

}
