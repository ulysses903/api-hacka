package br.com.payment.application.usecase.payment;

import br.com.payment.application.inout.input.FilterInput;
import br.com.payment.application.usecase.UseCase;
import br.com.payment.domain.core.domain.entities.internal.Payment;
import br.com.payment.domain.gateway.PaymentGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FilterPaymentUseCase implements UseCase<FilterInput, Page<Payment>> {

    private final PaymentGateway paymentGateway;

    @Override
    public Optional<Page<Payment>> execute(final FilterInput filterInput) {
        final List<Payment> paymentList = (List<Payment>) paymentGateway.findAll();
        return Optional.of(new PageImpl<>(paymentList));
    }
}
