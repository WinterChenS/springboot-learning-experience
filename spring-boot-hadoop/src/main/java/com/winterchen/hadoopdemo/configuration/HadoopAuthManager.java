package com.winterchen.hadoopdemo.configuration;

import java.security.PrivilegedAction;

/**
 * @author winterchen
 * @version 1.0
 * @date 2020/11/19 7:20 下午
 * @description TODO
 **/
public interface HadoopAuthManager {
    void checkAuth();

    <T> T doPrivileged(PrivilegedAction<T> var1);
}
