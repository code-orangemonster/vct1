package com.dhu.autooptimizationvctdatabase.rabbitmq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RabbitMQResource {
    @Autowired
    public RabbitMQProducer producer;

    // @PostMapping("/send")
    @GetMapping("/send")
    public void send(){
        producer.send("hello");
    }
}
