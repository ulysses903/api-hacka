package br.com.payment.application.integration;

import br.com.payment.application.infra.RabbitMQConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {

    @RabbitListener(queues = RabbitMQConfig.ORDER_QUEUE_NAME)
    public void receiveMessage(OrderRabbitInput orderRabbitInput) {
//        paymentFacade.create(new PaymentInput(orderRabbitInput.id(), orderRabbitInput.price(), ""));
    }
}
