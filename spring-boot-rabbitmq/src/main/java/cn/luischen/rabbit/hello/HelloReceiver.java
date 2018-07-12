package cn.luischen.rabbit.hello;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 消费者
 * Created by Donghua.Chen on 2018/4/27.
 */
@Component
@RabbitListener(queues = "hello")
public class HelloReceiver {


    @RabbitHandler
    public void process(String hello){
        System.out.println("hello = [" + hello + "]");
    }
}
