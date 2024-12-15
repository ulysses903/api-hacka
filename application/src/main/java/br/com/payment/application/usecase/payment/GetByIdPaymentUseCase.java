package br.com.payment.application.usecase.payment;

import br.com.payment.application.usecase.UseCase;
import br.com.payment.domain.core.domain.entities.internal.Payment;
import br.com.payment.domain.gateway.PaymentGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GetByIdPaymentUseCase implements UseCase<String, Payment> {

    private final PaymentGateway paymentGateway;

    @Override
    public Optional<Payment> execute(final String id) {
        return paymentGateway.findById(id);
    }
}
