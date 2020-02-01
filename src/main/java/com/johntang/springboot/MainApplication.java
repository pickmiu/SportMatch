package com.johntang.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @程序的入口
 * @title 赛事系统
 * @author JohnTang
 * @issue1 EventService、UserService、各种前端发来的json都没有进行各项数据的合法性校验，现在默认是前端实现，希望后期添加后端实现 
 */

@SpringBootApplication
public class MainApplication {

	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);
	}

}
