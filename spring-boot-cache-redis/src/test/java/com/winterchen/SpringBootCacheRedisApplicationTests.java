package com.winterchen;

import com.winterchen.domain.User;
import com.winterchen.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootCacheRedisApplicationTests {

	private static final Logger log = LoggerFactory.getLogger(SpringBootCacheRedisApplicationTests.class);


	@Autowired
	private UserService userService;


	@Test
	public void get() {
		final User user = userService.saveOrUpdate(new User(5L, "u5", "p5"));
		log.info("[saveOrUpdate] - [{}]", user);
		final User user1 = userService.get(5L);
		log.info("[get] - [{}]", user1);
		userService.delete(5L);
	}

}
