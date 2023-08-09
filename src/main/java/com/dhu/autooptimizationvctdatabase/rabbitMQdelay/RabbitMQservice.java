package com.dhu.autooptimizationvctdatabase.rabbitMQdelay;

import com.dhu.autooptimizationvctdatabase.entity.GraphMsg;
import com.dhu.autooptimizationvctdatabase.utils.IntervalTime;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;

@Service
public class RabbitMQservice {

    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    RabbitMQProducerDelay rabbitMQProducer;

    @Autowired
    ExecutorService executorService;

    public void sendMsg(GraphMsg graphMsg, long beginTime){
        executorService.execute(()->{
                // 防止空指针异常
                if (Boolean.FALSE.equals(stringRedisTemplate.opsForSet().isMember("opt", String.valueOf(9))))
                    rabbitMQProducer.sendDelayedMessage(graphMsg,beginTime);
                stringRedisTemplate.opsForSet().add("opt", String.valueOf(graphMsg.getId()));
        });
    }
}
