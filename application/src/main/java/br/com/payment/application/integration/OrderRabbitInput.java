package br.com.videoprocessing.application.integration;

import java.math.BigDecimal;

public record OrderRabbitInput(Long id, BigDecimal price) {
}
