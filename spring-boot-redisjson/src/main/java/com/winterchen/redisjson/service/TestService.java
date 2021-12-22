package com.winterchen.redisjson.service;

import com.winterchen.redisjson.entity.User;

/**
 * @author CENTURY
 * @version 1.0
 * @date 2021/12/22 16:08
 * @description TODO
 **/
public interface TestService {


    void add(User user);

    void update(User user);

    User findById(String id);


}