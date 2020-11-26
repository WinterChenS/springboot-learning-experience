package com.winterchen.hadoopdemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author winterchen
 * @version 1.0
 * @date 2020/11/19 7:21 下午
 * @description
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class OozieConfig {

    @Value("${namenode}")
    private String nameNode;

    @Value("${job-tracker}")
    private String jobTracker;

    @Value("${resourcemanager}")
    private String resourceManager;

    @Value("${queuename}")
    private String queueName;

    @Value("${oozie.url}")
    private String url;

    @Value("${oozie.wf.application.path}")
    private String oozieApplicationPath;

    @Value("${oozie.libpath}")
    private String oozieLibPath;

    @Value("${oozie.use.system.libpath}")
    private boolean oozieSystemLibPath;

    @Value("${jdbc.url}")
    private String jdbcUrl;

    @Value("${jdbc.password}")
    private String password;

    @Value("${callback.url}")
    private String callbackUrl;

}
