package com.johntang.springboot.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.johntang.springboot.pojo.BackFrontMessage;
import com.johntang.springboot.pojo.rabbitmq.MailMessage;
import com.johntang.springboot.util.RandomUtil;
import com.johntang.springboot.util.RedisUtil;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.johntang.springboot.service.MailService;
import com.johntang.springboot.service.UserService;

/**
 * @Description 登录控制器，提供给前端的get，post接口，实现用户登录，注册，修改密码等功能
 * @Author JohnTang
 * @LatestChangeDate 2020年2月1日
 */

@RestController
public class LoginController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	MailService mailService;

	@Autowired
	RandomUtil randomUtil;

	@Autowired
	AmqpTemplate rabbitTemplate;

	@Autowired
	RedisUtil redisUtil;

	//发送验证码
	@PostMapping("/mail/sendVerifyCode")
	public BackFrontMessage sendVerifyCode(@RequestParam String operationType,@RequestParam String mailAddress) {
			//生成验证码
			String verifyCode = randomUtil.generateSixRandomCode();
			//存入redis 5分钟过期
			if( !redisUtil.set(mailAddress,verifyCode,600) ){
				return new BackFrontMessage(500,"验证码发送失败",null);
			}
			//发送消息给mailserver
			MailMessage mailMessage = new MailMessage(operationType,mailAddress,mailAddress,verifyCode);
			rabbitTemplate.convertAndSend("mail",mailMessage);
			return new BackFrontMessage(200,"验证码发送成功",null);
	}

	//验证验证码
	@PostMapping("/openApi/confirmVerifyCode")
	public BackFrontMessage confirmVerifyCode(@RequestParam String mailAddress,@RequestParam String verifyCode){
		String redisVerifyCode = (String)redisUtil.get(mailAddress);
		if (redisVerifyCode == null || !redisVerifyCode.equals(verifyCode)){
			return new BackFrontMessage(500,"验证码错误",null);
		}else{
			return new BackFrontMessage(200,"验证码正确",null);
		}
	}

	//注册
	@PostMapping("/register")
	public BackFrontMessage register(@RequestParam String username,@RequestParam String password,@RequestParam String nickname,@RequestParam String verifyCode){
		//验证验证码
		String redisVerifyCode = (String)redisUtil.get(username);
		if (redisVerifyCode == null || !redisVerifyCode.equals(verifyCode)){
			return new BackFrontMessage(500,"验证码过期",null);
		}

		//添加用户
		if(userService.addUser(username,password,nickname) == 1){
			return new BackFrontMessage(200,"注册成功",null);
		}else{
			return new BackFrontMessage(500,"注册失败",null);
		}
	}

}
