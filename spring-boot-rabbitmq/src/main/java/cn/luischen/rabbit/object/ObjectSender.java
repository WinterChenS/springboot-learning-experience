package cn.luischen.rabbit.object;

import cn.luischen.model.User;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Donghua.Chen on 2018/4/27.
 */
@Component
public class ObjectSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void sendObject(User user){
        System.out.println("user = [" + user.toString() + "]");

        rabbitTemplate.convertAndSend("object", user);
    }
}
