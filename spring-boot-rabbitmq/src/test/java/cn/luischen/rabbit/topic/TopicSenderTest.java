package cn.luischen.rabbit.topic;

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
public class TopicSenderTest {

    @Autowired
    private TopicSender topicSender;

    @Test
    public void send() throws Exception {
        topicSender.send();
    }

    @Test
    public void send1() throws Exception {
        topicSender.send1();
    }

    @Test
    public void send2() throws Exception {
        topicSender.send2();
    }

}