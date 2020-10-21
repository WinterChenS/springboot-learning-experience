package com.winterchen.hadoopdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;


@EnableOpenApi
@SpringBootApplication
public class HadoopDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(HadoopDemoApplication.class, args);
    }

}
