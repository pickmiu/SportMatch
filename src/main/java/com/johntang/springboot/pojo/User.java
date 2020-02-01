package com.johntang.springboot.pojo;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @Description 用户实体
 * @Author JohnTang
 * @LatestChangeDate 2020年2月1日
 */

public class User implements UserDetails{
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	
	private String username;
	
	private String password;
	
	private String nickname;
	
	private List<Role> authorities;

	
	public void setAuthorities(List<Role> authorities) {
		this.authorities = authorities;
	}
	

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	
	
	@JsonIgnore
	@Override
	public String getPassword() {	
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}
	
	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	
	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	
	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	
	@Override
	public boolean isEnabled() {
		return true;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}	
	}
