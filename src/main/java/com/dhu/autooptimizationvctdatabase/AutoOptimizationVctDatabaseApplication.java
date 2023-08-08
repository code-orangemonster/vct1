package com.dhu.autooptimizationvctdatabase;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableRabbit
@EnableScheduling
public class AutoOptimizationVctDatabaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(AutoOptimizationVctDatabaseApplication.class, args);
    }

}
