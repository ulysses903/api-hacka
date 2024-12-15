package br.com.payment.infra.feign.interceptor;

import feign.RequestTemplate;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class MercadoPagoInterceptorTest {

    @Test
    public void testApplyHeaders() {
        MercadoPagoInterceptor interceptor = new MercadoPagoInterceptor();

        RequestTemplate requestTemplate = Mockito.mock(RequestTemplate.class);

        interceptor.requestInterceptor().apply(requestTemplate);

        Mockito.verify(requestTemplate).header("Authorization", "Bearer TEST-2769775558422450-070119-8182adad088f0912cfd131f6c2d0ba2a-354315215");
        Mockito.verify(requestTemplate).header("X-Idempotency-Key", "0d5020ed-1af6-469c-ae06-c3bec19954bb");
        Mockito.verify(requestTemplate).header("accept", "application/json");
        Mockito.verify(requestTemplate).header("content-type", "application/json");
    }
}
