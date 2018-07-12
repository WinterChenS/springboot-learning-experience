package com.winterchen;

import com.didispace.swagger.EnableSwagger2Doc;
import com.winterchen.utils.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

@SpringBootApplication
@EnableSwagger2Doc
public class SpringBootRestTemplateApplication {



	public static void main(String[] args) {
		SpringApplication.run(SpringBootRestTemplateApplication.class, args);

	}


}
