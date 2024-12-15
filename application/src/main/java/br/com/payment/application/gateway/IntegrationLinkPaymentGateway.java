package br.com.payment.application.gateway;

import br.com.payment.domain.core.domain.entities.external.QrCode;
import br.com.payment.infra.feign.presenter.request.PaymentRequest;

public interface IntegrationLinkPaymentGateway {

    QrCode generatedQrCode(PaymentRequest payment);

}
