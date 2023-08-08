package com.dhu.autooptimizationvctdatabase;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class AutoOptimizationVctDatabaseApplicationTests {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Test
    void contextLoads() {
        String queueName="queue1";
        String msg="hello,spring amqpp";
        rabbitTemplate.convertAndSend(queueName,msg);
    }

}
