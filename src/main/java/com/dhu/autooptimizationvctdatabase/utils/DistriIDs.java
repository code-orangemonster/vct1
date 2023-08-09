package com.dhu.autooptimizationvctdatabase.utils;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Service;

import java.util.Collections;


public class DistriIDs {

    private volatile static DistriIDs distriIDs;
    private StringRedisTemplate stringRedisTemplate;
    private String ID_KEY;

    public DistriIDs(StringRedisTemplate stringRedisTemplate, String id_key){
        this.stringRedisTemplate = stringRedisTemplate;
        ID_KEY = id_key;
    };

    private static final String LUA_SCRIPT = "local current = redis.call('GET', KEYS[1])\n" +
            "if not current then\n" +
            "    redis.call('SET', KEYS[1], ARGV[1])\n" +
            "    return tonumber(ARGV[1])\n" +
            "end\n" +
            "return redis.call('INCR', KEYS[1])";

    public long generateId() {
        RedisScript<Long> script = new DefaultRedisScript<>(LUA_SCRIPT, Long.class);
        Long id = stringRedisTemplate.execute(script, Collections.singletonList(ID_KEY), "1");
        return id;
    }
}
