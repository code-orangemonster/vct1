package com.dhu.autooptimizationvctdatabase.tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class QueueClearScheduler {

    @Autowired
    private QueueService queueService;

    /*
    * 如何保证任务准时执行
    * 1. 如果高负载情况下确实难以保证
    * 2. 分布式任务，将任务分配到多个节点
    * 3. 如果有多个任务，设置为任务设置优先级
    * 4. 监控，异步执行耗时任务，负载测试
    * */
    @Scheduled(cron = "0 22 20 * * *")
    public void clearQueue() {
        String queueName = "dlx-queue";
        queueService.clearQueue(queueName);
    }
}

