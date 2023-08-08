package com.dhu.autooptimizationvctdatabase.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Bean
    public Queue delayQueue() { // 延时队列
        return QueueBuilder.durable("delay-queue")
                .withArgument("x-dead-letter-exchange", "dlx-exchange")
                .withArgument("x-dead-letter-routing-key", "dlx")
                .build();
    }

    @Bean
    public DirectExchange dlxExchange() {
        return new DirectExchange("dlx-exchange");
    }

    @Bean
    public Queue dlxQueue() {
        return QueueBuilder.durable("dlx-queue").build();
    }

    @Bean
    public Binding dlxBinding() {
        return BindingBuilder.bind(dlxQueue()).to(dlxExchange()).with("dlx");
    }

    @Bean
    public Queue myQueue() {
        return new Queue("my-queue");
    }

}
