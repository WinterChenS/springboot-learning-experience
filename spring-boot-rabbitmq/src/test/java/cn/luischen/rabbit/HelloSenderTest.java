package cn.luischen.rabbit;

import cn.luischen.model.User;
import cn.luischen.rabbit.hello.HelloSender;
import cn.luischen.rabbit.many.NeoSender;
import cn.luischen.rabbit.many.NeoSender2;
import cn.luischen.rabbit.object.ObjectSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Donghua.Chen on 2018/4/27.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class HelloSenderTest {

    @Autowired
    private HelloSender helloSender;

    @Autowired
    private NeoSender neoSender;

    @Autowired
    private NeoSender2 neoSender2;

    @Autowired
    private ObjectSender objectSender;

    @Test
    public void hello() throws Exception {
        helloSender.send();
    }

    @Test
    public void oneToMany(){
        for (int i = 0; i < 100; i++) {
            neoSender.neoSend(i);
        }
    }

    @Test
    public void manyToMany(){
        for (int i = 0; i < 100; i++) {
            neoSender.neoSend(i);
            neoSender2.neoSender(i);
        }
    }

    @Test
    public void objectSend(){
        User user = new User();
        user.setUserName("新垣结衣");
        user.setAge(18);
        objectSender.sendObject(user);
    }

}