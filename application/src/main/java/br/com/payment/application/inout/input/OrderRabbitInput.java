package br.com.payment.application.inout.input;

import java.math.BigDecimal;

public record OrderRabbitInput(Long id, BigDecimal price) {
}
