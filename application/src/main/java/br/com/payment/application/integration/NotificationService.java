package br.com.payment.application.integration;

import br.com.payment.application.facade.PaymentFacade;
import br.com.payment.application.infra.RabbitMQConfig;
import br.com.payment.application.inout.input.OrderRabbitInput;
import br.com.payment.application.inout.input.PaymentInput;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final PaymentFacade paymentFacade;

    @RabbitListener(queues = RabbitMQConfig.ORDER_QUEUE_NAME)
    public void receiveMessage(OrderRabbitInput orderRabbitInput) {
        paymentFacade.create(new PaymentInput(orderRabbitInput.id(), orderRabbitInput.price(), ""));
    }
}
