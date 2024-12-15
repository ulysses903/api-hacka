package br.com.payment.application.usecase.payment;

import br.com.payment.application.gateway.IntegrationLinkPaymentGateway;
import br.com.payment.application.inout.input.PaymentInput;
import br.com.payment.application.inout.mapper.PaymentInputOutputMapper;
import br.com.payment.application.usecase.UseCase;
import br.com.payment.domain.core.domain.entities.internal.Payment;
import br.com.payment.domain.core.domain.entities.external.QrCode;
import br.com.payment.domain.gateway.PaymentGateway;
import br.com.payment.infra.feign.presenter.request.IdentificationRequest;
import br.com.payment.infra.feign.presenter.request.PayerRequest;
import br.com.payment.infra.feign.presenter.request.PaymentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CreatePaymentUseCase implements UseCase<PaymentInput, Payment> {

    private final IntegrationLinkPaymentGateway integrationLinkPaymentGateway;

    private final PaymentGateway paymentGateway;

    @Override
    public Optional<Payment> execute(final PaymentInput paymentInput) {
        Payment payment = PaymentInputOutputMapper.INSTANCE.paymentRequestToPayment(paymentInput);

        generateQrCode(payment);
        payment.setStatusPending();

        return paymentGateway.save(payment);
    }

    private void generateQrCode(Payment payment) {
        QrCode qrCode = integrationLinkPaymentGateway.generatedQrCode(
                payment.getIdentification() != null ? createPaymentRequestWithIdentification(payment) : createPaymentRequestNotIdentification(payment)
        );
        payment.setQrCode(qrCode.getQrCode());
    }

    private PaymentRequest createPaymentRequestWithIdentification(Payment payment) {
        return PaymentRequest.builder()
                .transactionAmount(payment.getAmount())
                .payerRequest(createPayerRequest(payment))
                .build();
    }

    private PaymentRequest createPaymentRequestNotIdentification(Payment payment) {
        return PaymentRequest.builder()
                .transactionAmount(payment.getAmount())
                .build();
    }

    private PayerRequest createPayerRequest(Payment payment) {
        return PayerRequest.builder()
                .identificationRequest(IdentificationRequest.builder()
                        .number(payment.getIdentification())
                        .build())
                .build();
    }
}
