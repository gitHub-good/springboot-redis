package com.xu.springboot.redis.springbootredis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Repository;

@SpringBootApplication(scanBasePackages = "com.xu.springboot.redis.springbootredis", exclude = DataSourceAutoConfiguration.class)
@MapperScan(basePackages = "com.xu.springboot.redis.springbootredis", annotationClass = Repository.class)
//@SpringBootConfiguration
//@MapperScan(value = "com.xu.springboot.redis.springbootredis.mapper")
@EnableCaching/*启动缓存注解*/
public class SpringbootRedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootRedisApplication.class, args);
    }
}
