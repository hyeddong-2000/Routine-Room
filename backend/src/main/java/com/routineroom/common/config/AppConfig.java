package com.routineroom.common.config;

import com.routineroom.common.code.CommonCode;
import com.routineroom.common.code.RedisIO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.sql.DataSource;

@Configuration
public class AppConfig {

    @Bean
    public RedisIO redisIO(StringRedisTemplate stringRedisTemplate) {
        return new RedisIO(stringRedisTemplate);
    }

    @Bean
    @DependsOn("redisIO")
    public CommonCode commonCode(DataSource dataSource, RedisIO redisIO) {
        return new CommonCode(dataSource, redisIO);
    }
}
