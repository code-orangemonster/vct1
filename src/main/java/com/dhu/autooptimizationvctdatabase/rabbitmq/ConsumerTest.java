package com.dhu.autooptimizationvctdatabase.rabbitmq;

import jakarta.annotation.Resource;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ConsumerTest {
    @RabbitListener(queues = "my-queue")
    public void receiveMessage(String message) {
        System.out.println("Received message: " + message);
    }
}

