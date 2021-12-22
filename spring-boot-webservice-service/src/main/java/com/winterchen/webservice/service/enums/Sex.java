package com.winterchen.webservice.service.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author winterchen.com
 * @version 1.0
 * @date 2021/12/16 10:15
 * @description TODO
 **/
@Getter
@AllArgsConstructor
public enum Sex {

    MALE("male"),
    FEMALE("female");

    String value;

    public static Sex fromValue(String v) {
        for (Sex c : Sex.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
}