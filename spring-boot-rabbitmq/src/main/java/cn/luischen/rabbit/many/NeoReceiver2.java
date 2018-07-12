package cn.luischen.rabbit.many;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by Donghua.Chen on 2018/4/27.
 */
@Component
@RabbitListener(queues = "neo")
public class NeoReceiver2 {

    @RabbitHandler
    public void process(String neo){
        System.out.println("NeoReceiver2: [" + neo + "]");
    }
}
