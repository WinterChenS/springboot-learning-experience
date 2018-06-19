package com.winterchen.dubbo;

import com.alibaba.dubbo.config.annotation.Reference;
import com.winterchen.domain.User;
import org.springframework.stereotype.Component;

/**
 * Created by Donghua.Chen on 2018/5/29.
 */
@Component
public class UserDubboConsumerService {

    @Reference(version = "1.0.0")
    private UserDubboService userDubboService;

    public void printUser(){
        Long userId = 12312321L;
        User user = userDubboService.findUserById(userId);
        System.out.println(user);
    }
}
