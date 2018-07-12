package cn.luischen.rabbit.object;

import cn.luischen.model.User;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by Donghua.Chen on 2018/4/27.
 */
@Component
@RabbitListener(queues = "object")
public class ObjectReceiver {

    @RabbitHandler
    public void process(User user){
        System.out.println("ObjectReceiver: [" + user.toString() + "]");
    }
}
