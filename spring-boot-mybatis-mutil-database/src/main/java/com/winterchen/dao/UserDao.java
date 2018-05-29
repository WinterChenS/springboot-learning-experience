package com.winterchen.dao;

import com.winterchen.model.UserDomain;

import java.util.List;

/**
 * Created by Donghua.Chen on 2018/5/29.
 */
public interface UserDao {

    int insert(UserDomain record);



    List<UserDomain> selectUsers();
}
