package com.winterchen;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

/**
 * 我的第一个springboot程序
 * 其中 @RestController 等同于 （@Controller 与 @ResponseBody）
 */
@RestController
@SpringBootApplication
public class SpringBootStartApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootStartApplication.class, args);
	}

	@GetMapping("/demo1")
	public String demo1() {
		return "Hello Luis";
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		// 目的是
		return args -> {
			System.out.println("来看看 SpringBoot 默认为我们提供的 Bean：");
			String[] beanNames = ctx.getBeanDefinitionNames();
			Arrays.sort(beanNames);
			Arrays.stream(beanNames).forEach(System.out::println);
		};
	}
}
