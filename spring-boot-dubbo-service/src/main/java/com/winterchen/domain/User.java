package com.winterchen.domain;

import java.io.Serializable;

/**
 * Created by Donghua.Chen on 2018/5/29.
 */
public class User implements Serializable{

    private static final long serialVersionUID = -1L;

    private Long userId;

    private String userName;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
