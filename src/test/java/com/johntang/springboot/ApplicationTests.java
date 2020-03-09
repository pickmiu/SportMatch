package com.johntang.springboot;

import com.johntang.springboot.util.RandomUtil;
import com.johntang.springboot.util.RedisUtil;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest
class ApplicationTests {

	@Autowired
	AmqpTemplate amqpTemplate;

	@Autowired
	RedisUtil redisUtil;

	@Autowired
	RandomUtil randomUtil;

	@Test
	void contextLoads(){
		amqpTemplate.convertAndSend("mail","hello");
	}

}
