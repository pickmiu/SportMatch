package com.johntang.springboot.service;

import com.johntang.springboot.pojo.BackFrontMessage;
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

		//确保用户名和昵称不存在
		if(userMapper.findUserByUsername(username) != null || userMapper.findUserByNickname(nickname) != null){
			return 0;
		}

		//密码加密，构造函数的参数是密码加密迭代的次数 默认为10
		BCryptPasswordEncoder encoding = new BCryptPasswordEncoder();
		System.out.println(password);
		password = encoding.encode(password);
		System.out.println(password);
		
		//添加进数据库
		return userMapper.addUser(username, password, nickname);
	}
	
	/**
	 *    通过用户名修改密码
	 * @param username
	 * @param newpassword
	 */
	public int changePassword(String username,String newpassword) {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		newpassword=bCryptPasswordEncoder.encode(newpassword);
		return userMapper.changePassword(username, newpassword);
	}

	/**
	 *    判断存在
	 * @param key
	 * @param value
	 * @return
	 */
	public BackFrontMessage exist(String key,String value){
		if(key.equals("username")){
			if(userMapper.findUserByUsername(value) == null){
				return new BackFrontMessage(200,"不存在",null);
			}else {
				return new BackFrontMessage(200,"已存在",null);
			}
		}else if (key.equals("nickname")){
			if(userMapper.findUserByNickname(value) == null){
				return new BackFrontMessage(200,"不存在",null);
			}else {
				return new BackFrontMessage(200,"已存在",null);
			}
		}else {
			return new BackFrontMessage(500,"查找失败",null);
		}
	}



}
