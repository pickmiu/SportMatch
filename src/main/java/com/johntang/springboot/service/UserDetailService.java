package com.johntang.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.johntang.springboot.mapper.RoleMapper;
import com.johntang.springboot.mapper.UserMapper;
import com.johntang.springboot.pojo.Role;
import com.johntang.springboot.pojo.User;

/**
 * @Description 用户认证服务
 * @Author JohnTang
 * @LatestChangeDate 2020年2月1日
 */

@Service
public class UserDetailService implements UserDetailsService{
	
	@Autowired
	private UserMapper UserMapper;
	
	@Autowired
	private RoleMapper RoleMapper;
	
	/**
	 * 用户详细加载类
	 * @param username
	 * @return user
	 */
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = UserMapper.findUserByUsername(username);
		
		if(user == null) throw new UsernameNotFoundException("用户名不存在");
		
		List<Role> roles = RoleMapper.findRolesByUserId(user.getId());
		user.setAuthorities(roles);
		
		return user;
	}

}
