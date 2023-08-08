package com.dhu.autooptimizationvctdatabase.rabbitMQdelay;

import com.dhu.autooptimizationvctdatabase.entity.GraphMsg;
import com.dhu.autooptimizationvctdatabase.utils.IntervalTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RabbitMQResourceDelay {

    @Autowired
    RabbitMQservice rabbitMQservice;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @GetMapping("/delay")
    public void testDelay(){
        for (int i = 0; i < 10; i++) {
            long beginTime = IntervalTime.getIntervalTime().getInterval(stringRedisTemplate);
            GraphMsg graphMsg = new GraphMsg(String.valueOf(9), 100, 9);
            rabbitMQservice.sendMsg(graphMsg,beginTime);
        }
    }
}
