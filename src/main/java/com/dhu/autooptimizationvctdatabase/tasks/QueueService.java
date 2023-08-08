package com.dhu.autooptimizationvctdatabase.tasks;

import jakarta.annotation.Resource;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;


@Service
public class QueueService {

    @Resource
    private RabbitTemplate rabbitTemplate;

    public void clearQueue(String queueName) {
        rabbitTemplate.execute(channel -> {
            channel.queuePurge(queueName);
            return null;
        });
        System.out.println("Queue '" + queueName + "' has been cleared.");
    }
}
