package com.dhu.autooptimizationvctdatabase.utils;

import org.springframework.data.redis.core.StringRedisTemplate;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class IntervalTime {
    private IntervalTime(){};
    private  volatile static  IntervalTime intervalTime;
    public static IntervalTime getIntervalTime(){
        if (intervalTime==null){
            synchronized (IntervalTime.class){
                if (intervalTime==null){
                    intervalTime = new IntervalTime();
                }
            }
        }
        return intervalTime;
    }
    public long getInterval(StringRedisTemplate stringRedisTemplate){
        // 每个消息的处理时间
        int dealTime = 60;
        // 获取当前时间
        LocalDateTime currentTime = LocalDateTime.now();
        // 定义日期格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        // 将日期按照指定格式转化为字符串
        String formattedDate = currentTime.format(formatter);

        // 设置明天的时间为 1:00
        LocalDateTime tomorrowTime = currentTime.plusDays(1).withHour(1).withMinute(0).withSecond(0).withNano(0);
        // 计算时间差并输出毫秒数
        Duration duration = Duration.between(currentTime, tomorrowTime);
        long id = new DistriIDs(stringRedisTemplate,formattedDate).generateId();
        long timeDifferenceInMillis = id * dealTime;
        System.out.println("id"+ id + ": time :" +
                timeDifferenceInMillis+"for:"+formattedDate);
        long dl = duration.toMillis() + timeDifferenceInMillis;
        return dl;
    }
}
