package br.com.payment.infra.entity;

import br.com.payment.domain.core.domain.entities.internal.StatusPayment;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Document(collection = "payments")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentEntity {

    @Id
    private String id;

    private BigDecimal amount;

    private String identification;

    private String qrCode;

    private StatusPayment status;

    @Indexed(unique = true)
    private String order;

    private LocalDateTime paymentAt;

}
