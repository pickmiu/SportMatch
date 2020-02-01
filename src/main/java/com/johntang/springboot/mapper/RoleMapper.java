package com.johntang.springboot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.johntang.springboot.pojo.Role;

/**
 * @Description mybatis框架下角色（权限）相关功能的mysql数据库持久化接口
 * @Author JohnTang
 * @LatestChangeDate 2020年2月1日
 */

@Mapper
public interface RoleMapper {
	
	int addUserIdAndRoleId(int userId,int roleId);
	
	List<Role> findRolesByUserId(int userId);
}
