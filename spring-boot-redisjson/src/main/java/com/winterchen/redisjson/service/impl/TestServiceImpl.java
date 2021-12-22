package com.winterchen.redisjson.service.impl;

import com.google.gson.Gson;
import com.redislabs.modules.rejson.JReJSON;
import com.winterchen.redisjson.entity.User;
import com.winterchen.redisjson.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * @author CENTURY
 * @version 1.0
 * @date 2021/12/22 16:08
 * @description TODO
 **/
@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private JReJSON jReJSON;

    @Override
    public void add(User user) {
        Gson gson = new Gson();
        jReJSON.set(user.getId(), gson.toJson(user));
    }

    @Override
    public void update(User user) {
        Gson gson = new Gson();
        String old = jReJSON.get(user.getId());
        Assert.notNull(old, "entity is not exist!");
        jReJSON.set(user.getId(), gson.toJson(user));
    }

    @Override
    public User findById(String id) {
        Object o = jReJSON.get(id);
        if (o == null) {
            return null;
        }
        Gson gson = new Gson();
        return gson.fromJson((String) o, User.class);
    }


}