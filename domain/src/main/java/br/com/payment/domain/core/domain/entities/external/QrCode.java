package br.com.payment.domain.core.domain.entities.external;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class QrCode {

    @JsonProperty("status")
    private String status;

    @JsonProperty("point_of_interaction")
    private PointOfInteraction pointOfInteraction;


    public String getQrCode() {
        return this.getPointOfInteraction().getTransactionData().getQrCode();
    }

}
