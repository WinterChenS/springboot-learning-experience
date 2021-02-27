package com.winterchen.sentry.configuration;

import lombok.extern.slf4j.Slf4j;
import org.apache.sentry.api.service.thrift.SentryPolicyServiceClient;
import org.apache.sentry.service.thrift.SentryServiceClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URL;

/**
 * @author winterchen
 * @version 1.0
 * @date 2021/1/22 10:54 上午
 * @description sentry配置
 **/
@Slf4j
@Configuration
public class SentryConfiguration {


    @Bean
    public SentryPolicyServiceClient sentryPolicyServiceClient() {
        SentryPolicyServiceClient sentryPolicyServiceClient = null;
        try {
            URL resource = this.getClass().getClassLoader().getResource("sentry-site.xml");
            org.apache.hadoop.conf.Configuration conf = new org.apache.hadoop.conf.Configuration();
            conf.addResource(resource);
            sentryPolicyServiceClient = SentryServiceClientFactory.create(conf);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return sentryPolicyServiceClient;
    }
}
