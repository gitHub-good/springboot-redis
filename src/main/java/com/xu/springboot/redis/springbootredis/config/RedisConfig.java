package com.xu.springboot.redis.springbootredis.config;

import com.xu.springboot.redis.springbootredis.pojo.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.net.UnknownHostException;

/**
 * spring boot 2.x版本的Redis配置类
 */
@Configuration
public class RedisConfig {

    /**
     * redis RedisTemplate配置
     * @param redisConnectionFactory
     * @return
     * @throws UnknownHostException
     */
    @Bean
    public RedisTemplate<Object,User> userRedisTemplate(RedisConnectionFactory redisConnectionFactory)throws UnknownHostException {
        RedisTemplate<Object,User> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        Jackson2JsonRedisSerializer<User> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<User>(User.class);
        template.setDefaultSerializer(jackson2JsonRedisSerializer);
        return template;
    }


    /**
     * serializeKeysWith() 修改key的序列化规则，这里采用的是StringRedisSerializer()
     * serializeValuesWith() 修改value的序列化规则，这里采用的是Jackson2JsonRedisSerializer<Employee>(Employee.class)
     * @return
     */
    @Bean
    public RedisCacheManager employeeCacheManager(RedisConnectionFactory redisConnectionFactory) {

        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
//                .entryTtl(Duration.ofMinutes(10))/*设置10分钟超时，在Redis自动删除*/
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new Jackson2JsonRedisSerializer<User>(User.class)));

        RedisCacheManager cacheManager = RedisCacheManager.builder(redisConnectionFactory).cacheDefaults(config).build();

        return cacheManager;
    }

}

