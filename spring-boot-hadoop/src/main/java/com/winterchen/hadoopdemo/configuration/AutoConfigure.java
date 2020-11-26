package com.winterchen.hadoopdemo.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author winterchen
 * @version 1.0
 * @date 2020/11/19 7:18 下午
 * @description TODO
 **/
@Configuration
public class AutoConfigure {
    public AutoConfigure() {

    }

    @Bean(
            name = {"hadoopAuthManager"}
    )
    @Qualifier("hadoopAuthManager")
    static  HadoopAuthenticationInitializer hadoopAuthenticationInitializer() {
        return new HadoopAuthenticationInitializer();
    }
}
