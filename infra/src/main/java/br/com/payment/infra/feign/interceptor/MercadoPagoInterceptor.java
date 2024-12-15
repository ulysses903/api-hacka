package br.com.payment.infra.feign.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MercadoPagoInterceptor {

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String AUTHORIZATION_TOKEN = "Bearer TEST-2769775558422450-070119-8182adad088f0912cfd131f6c2d0ba2a-354315215";

    private static final String IDEMPOTENCY_KEY_HEADER = "X-Idempotency-Key";
    private static final String IDEMPOTENCY_KEY = "0d5020ed-1af6-469c-ae06-c3bec19954bb";

    private static final String ACCEPT_HEADER = "accept";
    private static final String ACCEPT_TYPE = "application/json";

    private static final String CONTENT_TYPE_HEADER = "content-type";
    private static final String CONTENT_TYPE = "application/json";

    @Bean
    public RequestInterceptor requestInterceptor() {
        return new RequestInterceptor() {
            @Override
            public void apply(RequestTemplate template) {
                template.header(AUTHORIZATION_HEADER, AUTHORIZATION_TOKEN);
                template.header(IDEMPOTENCY_KEY_HEADER, IDEMPOTENCY_KEY);
                template.header(ACCEPT_HEADER, ACCEPT_TYPE);
                template.header(CONTENT_TYPE_HEADER, CONTENT_TYPE);
            }
        };
    }

}
