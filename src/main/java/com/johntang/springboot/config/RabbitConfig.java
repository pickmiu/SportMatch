package com.johntang.springboot.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


/**
 * @ClassName RabbitConfig
 * @Description RabbitMQ配置类
 * @Author JohnTang
 * @LastChangeDate 2020/3/8 14:40
 * @Version v1.0
 **/

@Component
public class RabbitConfig {

    @Bean
    public Queue queue() {
        return new Queue("mail");
    }
}
