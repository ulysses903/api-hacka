package br.com.payment.infra.feign.utils;

import java.time.Duration;
import java.time.LocalDateTime;

public class MercadoPagoUtils {

    public static LocalDateTime expirationQrCode() {
        return LocalDateTime.now().plusHours(2);
    }

    public static void validateQrCodeExpiration(LocalDateTime dateTime) {
        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(now, dateTime);

        if (duration.toMinutes() > 120) {
            throw new IllegalArgumentException("Pagamento expirado.");
        }
    }

}
