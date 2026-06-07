package com.routineroom.common.code;

import org.springframework.data.redis.core.StringRedisTemplate;

public class RedisIO {

    private final StringRedisTemplate redisTemplate;

    public RedisIO(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void setValue(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public String getValue(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public void deleteValue(String key) {
        redisTemplate.delete(key);
    }
}
