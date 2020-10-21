package com.winterchen.hadoopdemo.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class HiveJdbcConfiguration {

    @Bean("hiveJdbcTemplate")
    @Qualifier("hiveJdbcTemplate")
    public JdbcTemplate jdbcTemplate(@Qualifier("hiveDruidDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

}
