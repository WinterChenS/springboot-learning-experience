package com.winterchen;

import com.winterchen.domain.User;
import com.winterchen.dubbo.UserDubboConsumerService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringbootDubboClientApplication {

	public static void main(String[] args) {
		// 程序启动入口
		// 启动嵌入式的 Tomcat 并初始化 Spring 环境及其各 Spring 组件
		ConfigurableApplicationContext run = SpringApplication.run(SpringbootDubboClientApplication.class, args);
		UserDubboConsumerService userService = run.getBean(UserDubboConsumerService.class);
		userService.printUser();
	}
}
