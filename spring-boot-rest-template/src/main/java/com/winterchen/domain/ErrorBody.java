package com.winterchen.domain;

import java.io.Serializable;

/**
 * Created by Donghua.Chen on 2018/6/20.
 */
public class ErrorBody implements Serializable{
    private static final long serialVersionUID = 8655851615965363473L;
    private Integer code;
    private String message;
    private long timestamp = System.currentTimeMillis();

    // get set
    @Override
    public String toString() {
        return "ErrorBody{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
