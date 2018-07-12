package com.winterchen.dubbo.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.winterchen.domain.User;
import com.winterchen.dubbo.UserDubboService;

/**
 * dubbo 服务
 * Created by Donghua.Chen on 2018/5/29.
 */
@Service(version = "1.0.0")
public class UserDubboServiceImpl implements UserDubboService {

    @Override
    public User findUserById(Long userId) {
        System.out.println("调用远程服务-------------");
        User user = new User();
        user.setUserId(userId);
        user.setUserName("Luis");
        return user;
    }
}
