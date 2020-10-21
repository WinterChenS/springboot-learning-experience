package com.winterchen.hadoopdemo.properties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@ConfigurationProperties(prefix = "spring.datasource.hive", ignoreUnknownFields = false)
public class HiveJdbcProperties {

    private String url;

    private String type;

    private String username;

    private String password;

    private String driverClassName;

}
