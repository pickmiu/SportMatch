package com.johntang.springboot.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.johntang.springboot.pojo.User;
import org.springframework.stereotype.Component;

/**
 * @Description mybatis框架下用户信息相关功能的mysql数据库持久化接口
 * @Author JohnTang
 * @LatestChangeDate 2020年2月1日
 */

@Component
@Mapper
public interface UserMapper {
	
	int addUser(String username,String password,String nickname);

	User findUserByUsername(String username);
	
	int changePassword(String username,String password);

	User findUserByNickname(String nickname);
}
