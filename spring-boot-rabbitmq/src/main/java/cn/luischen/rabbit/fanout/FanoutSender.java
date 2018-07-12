package cn.luischen.rabbit.fanout;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Donghua.Chen on 2018/4/27.
 */
@Component
public class FanoutSender {

    @Autowired
    private AmqpTemplate rabbittemplate;

    public void send(){
        String context = "FanoutSender: fanout msg";
        System.out.println("now we send message");
        rabbittemplate.convertAndSend("fanoutExchange","", context);
    }
}
