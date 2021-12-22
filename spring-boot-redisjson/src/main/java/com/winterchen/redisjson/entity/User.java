package com.winterchen.redisjson.entity;

import lombok.Builder;
import lombok.Data;

/**
 * @author winterchen.com
 * @version 1.0
 * @date 2021/12/22 16:09
 * @description TODO
 **/
@Builder
@Data
public class User {

    private String id;

    private String name;

    private Integer age;

}