package com.winterchen.redisjson.service;

import com.winterchen.redisjson.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.Assert;

import java.util.UUID;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class TestServiceTest {

    @Autowired
    private TestService testService;

    @Test
    public void testService() {
        User user = User.builder()
                .id(UUID.randomUUID().toString().replace("-", ""))
                .age(28)
                .name("winterchen").build();
        testService.add(user);
        User byId = testService.findById(user.getId());
        Assert.notNull(byId,"not exist");
        user.setAge(29);
        testService.update(user);
        User byId1 = testService.findById(user.getId());
        Assert.isTrue(byId1.getAge().equals(user.getAge()),"update failed");
    }

}