package br.com.payment.application.usecase.payment;

import br.com.payment.application.inout.input.PaymentUpdateInput;
import br.com.payment.application.usecase.UseCase;
import br.com.payment.domain.core.domain.entities.internal.Payment;
import br.com.payment.domain.core.domain.entities.internal.StatusPayment;
import br.com.payment.domain.gateway.PaymentGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UpdateProcessPaymentCase implements UseCase<PaymentUpdateInput, Payment>  {

    private final GetByIdPaymentUseCase getByIdPaymentUseCase;
    private final PaymentGateway paymentGateway;

    @Override
    public Optional<Payment> execute(PaymentUpdateInput paymentUpdateInput) {
        return getByIdPaymentUseCase.execute(paymentUpdateInput.id())
                .map(payment -> updatePaymentStatus(payment, paymentUpdateInput.status()))
                .flatMap(paymentGateway::update);
    }

    private Payment updatePaymentStatus(Payment payment, StatusPayment status) {
        payment.setProcessPayment(status);
        return payment;
    }

}
