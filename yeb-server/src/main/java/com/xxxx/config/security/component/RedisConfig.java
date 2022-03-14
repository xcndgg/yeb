package com.xxxx.config.security.component;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author xcdgg
 * @description
 * @date 2022/2/3 22:47
 */
@Configuration
public class RedisConfig {
    //配置redis
    @Bean
    public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory connectionFactory){
        RedisTemplate<String,Object> redisTemplate = new RedisTemplate<>();
        //String 类型 key 序列器
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        //String 类型 value 序列器
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        //Hash 类型 key 序列器
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        //Hash 类型 key 序列器
        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setConnectionFactory(connectionFactory);
        return redisTemplate;

    }
}