package com.winterchen.redisjson.config;

import com.redislabs.modules.rejson.JReJSON;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author winterchen.com
 * @version 1.0
 * @date 2021/12/22 16:03
 * @description TODO
 **/
@Configuration
public class RedisJsonConfiguration {

    @Value("${jrejson.host}")
    private String host;

    @Value("${jrejson.port}")
    private Integer port;


    @Bean
    public JReJSON jReJSON() {
        return new JReJSON(host, port);
    }

}