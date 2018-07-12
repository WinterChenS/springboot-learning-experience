package com.winterchen.model;

import java.io.Serializable;

/**
 * Created by Donghua.Chen on 2018/7/12.
 */
public class Book implements Serializable {

    private static final long serialVersionUID = -2164058270260403154L;

    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
