package com.johntang.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.johntang.springboot.mapper.UserMapper;

/**
 * @Description 用于注册和修改密码
 * @Author JohnTang
 * @LatestChangeDate 2020年2月1日
 */

@Service
public class UserService {
	
	@Autowired
	private UserMapper userMapper;
	
	public int addUser(String username,String password,String nickname) {
		
		/**
		 * 密码加密，构造函数的参数是密码加密迭代的次数 默认为10
		 */
		
		BCryptPasswordEncoder encoding = new BCryptPasswordEncoder();
		System.out.println(password);
		password = encoding.encode(password);
		System.out.println(password);
		
		/**
		 * 添加进数据库
		 */
		return userMapper.addUser(username, password, nickname);
	}
	
	/**
	 *    通过用户名修改密码
	 * @param username
	 * @param password
	 */
	
	public int changePassword(String username,String password) {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		password=bCryptPasswordEncoder.encode(password);
		return userMapper.changePassword(username, password);
	}
}
