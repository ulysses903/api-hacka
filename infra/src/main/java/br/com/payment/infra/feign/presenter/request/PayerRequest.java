package br.com.payment.infra.feign.presenter.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class PayerRequest {

    @JsonProperty("identification")
    IdentificationRequest identificationRequest;

}
