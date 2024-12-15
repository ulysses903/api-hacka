package br.com.payment.domain.core.domain.entities.internal;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@EqualsAndHashCode
public class Payment  implements Serializable {

    private String id;
    private BigDecimal amount;
    private String identification;
    private StatusPayment status;
    private String qrCode;
    private String order;
    private LocalDateTime date;

    public void setStatusPending() {
        this.date = LocalDateTime.now();
        this.status = StatusPayment.PENDING;
    }

    public void setProcessPayment(StatusPayment updateStatus) {
        this.status = updateStatus;
        this.date = LocalDateTime.now();
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }
}
