package cn.luischen.rabbit.many;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Donghua.Chen on 2018/4/27.
 */
@Component
public class NeoSender2 {

    @Autowired
    private AmqpTemplate rabbitTemplate;


    public void neoSender(int index){
        String msg = "2222 neo queue : ****** " + index;
        rabbitTemplate.convertAndSend("neo", msg);
    }
}
