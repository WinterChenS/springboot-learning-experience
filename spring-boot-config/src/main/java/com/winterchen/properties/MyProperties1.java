package com.winterchen.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by Donghua.Chen on 2018/6/1.
 */
@Component
@ConfigurationProperties(prefix = "my1")
public class MyProperties1 {

    private int age;
    private String name;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "MyProperties1{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}