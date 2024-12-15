package br.com.payment.infra.feign.presenter.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class IdentificationRequest {

    @JsonProperty("type")
    private final String type = "CPF";

    @JsonProperty("number")
    private String number;

}
