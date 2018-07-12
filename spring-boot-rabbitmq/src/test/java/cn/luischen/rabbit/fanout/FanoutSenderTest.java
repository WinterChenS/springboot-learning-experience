package cn.luischen.rabbit.fanout;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by Donghua.Chen on 2018/4/27.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FanoutSenderTest {

    @Autowired
    private FanoutSender fanoutSender;

    @Test
    public void send() throws Exception {

            fanoutSender.send();
    }

}