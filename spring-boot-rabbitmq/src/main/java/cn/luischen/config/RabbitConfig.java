package cn.luischen.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Donghua.Chen on 2018/4/27.
 */
@Configuration
public class RabbitConfig {

    @Bean
    public Queue helloQueue(){
        return new Queue("hello");
    }

    @Bean
    public Queue neoQueue(){
        return new Queue("neo");
    }

    @Bean
    public Queue objectQueue(){
        return new Queue("object");
    }
}
