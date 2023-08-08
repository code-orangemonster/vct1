package com.dhu.autooptimizationvctdatabase.rabbitMQdelay;

import com.dhu.autooptimizationvctdatabase.entity.GraphMsg;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

@Component
public class RabbitMQConsumerDelay {

    private static final Logger logger = LogManager.getLogger(RabbitMQConsumerDelay.class);
    @Autowired
    private ExecutorService executorService; // 注入线程池

    @RabbitListener(queues = "dlx-queue")
    public void receiveDelayedMessage(GraphMsg message) {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            try {
                System.out.println("Received delayed message: " + message);
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, executorService);

        future.exceptionally(ex -> {
            logger.info("消费异常,请及时处理");
            return null;
        });

    }
}
