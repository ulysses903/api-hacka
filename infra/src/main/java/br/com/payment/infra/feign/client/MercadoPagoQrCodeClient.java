package br.com.payment.infra.feign.client;

import br.com.payment.domain.core.domain.entities.external.QrCode;
import br.com.payment.infra.feign.interceptor.MercadoPagoInterceptor;
import br.com.payment.infra.feign.presenter.request.PaymentRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "integration-mercado-pago", url = "https://api.mercadopago.com/v1/payments", configuration = { MercadoPagoInterceptor.class })
public interface MercadoPagoQrCodeClient {

    @PostMapping
    QrCode generatedQrCode(@RequestBody PaymentRequest request);

}
