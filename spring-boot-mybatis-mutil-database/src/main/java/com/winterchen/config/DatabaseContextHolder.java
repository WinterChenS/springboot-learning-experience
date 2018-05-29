package com.winterchen.config;

/**
 * 保存一个线程安全的DatabaseType容器
 * Created by Donghua.Chen on 2018/5/29.
 */
public class DatabaseContextHolder {

    private static final ThreadLocal<DatabaseType> contextHolder = new ThreadLocal<>();

    public static void setDatabaseType(DatabaseType type) {
        contextHolder.set(type);
    }

    public static DatabaseType getDatabaseType() {
        return contextHolder.get();
    }
}
