package com.johntang.springboot.pojo;

import org.springframework.security.core.GrantedAuthority;

/**
 * @Description 用户角色实体
 * @Author JohnTang
 * @LatestChangeDate 2020年2月1日
 */

public class Role implements GrantedAuthority{
	

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String name;
	
	@Override
	public String getAuthority() {
		return name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
