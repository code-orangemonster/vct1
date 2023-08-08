package com.dhu.autooptimizationvctdatabase.rabbitMQdelay;

import com.dhu.autooptimizationvctdatabase.entity.GraphMsg;
import jakarta.annotation.Resource;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQProducerDelay {

    @Resource
    private RabbitTemplate rabbitTemplate;

    public void sendDelayedMessage(GraphMsg message, long delayInMillis) {
        rabbitTemplate.convertAndSend("delay-queue", message, messagePostProcessor -> {
            messagePostProcessor.getMessageProperties().setExpiration(String.valueOf(delayInMillis));
            return messagePostProcessor;
        });
        System.out.println("Sent delayed message: " + message);
    }
}
