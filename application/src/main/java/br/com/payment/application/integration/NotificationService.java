package br.com.videoprocessing.application.integration;

import br.com.videoprocessing.application.infra.RabbitMQConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {

    @RabbitListener(queues = RabbitMQConfig.ORDER_QUEUE_NAME)
    public void receiveMessage(OrderRabbitInput orderRabbitInput) {
//        videoprocessingFacade.create(new videoprocessingInput(orderRabbitInput.id(), orderRabbitInput.price(), ""));
    }
}
