package com.dhu.autooptimizationvctdatabase.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

@Configuration
public class ThreadPools {
    @Bean
    public ExecutorService threadPool() {
        int corePoolSize = 5; // 初始线程数
        int maxPoolSize = 10; // 最大线程数
        long keepAliveTime = 1; // 非核心线程的闲置超时时间
        TimeUnit timeUnit = TimeUnit.MINUTES; // 超时时间单位
        int queueCapacity = 5; // 任务队列的容量
        return new ThreadPoolExecutor(
                corePoolSize,
                maxPoolSize,
                keepAliveTime,
                timeUnit,
                new ArrayBlockingQueue<Runnable>(queueCapacity), // 使用 ArrayBlockingQueue 限制队列容量
                r -> new Thread(r, "rabbit" + Thread.currentThread().getId())
        );
    }
}

