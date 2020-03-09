package com.johntang.springboot.config;

import com.johntang.springboot.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.johntang.springboot.handle.MyAuthenticationAccessDeniedHandler;
import com.johntang.springboot.handle.MyAuthenticationFailureHandler;
import com.johntang.springboot.handle.MyAuthenticationSuccessHandler;
import com.johntang.springboot.handle.MyLogoutSuccessHandler;

/**
 * @Description web安全配置类，用于用户认证和拦截没有权限的网页
 * @Author JohnTang
 * @LatestChangeDate 2020年2月1日
 */

@Configuration
public class MyWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
	
	
	@Autowired
	UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Autowired
	MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;
	
	@Autowired
	MyAuthenticationFailureHandler myAuthenticationFailureHandler;
	
	@Autowired
	MyAuthenticationAccessDeniedHandler myAuthenticationAccessDeniedHandler;
	
	@Autowired
	MyLogoutSuccessHandler myLogoutSuccessHandler;
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	//注意数据库中的权限表中的权限名称必须是ROLE_****的形式，不然无法自动匹配
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/register**","/static/**","/mail/**","/openApi/**")
			.permitAll()
			.antMatchers("/user/**")
			.access("hasAnyRole('USER','PLAYER','REFEREE','E_ADMIN','FE_ADMIN','S_ADMIN')")
			.antMatchers("/player/**")
			.hasRole("PLAYER")
			.antMatchers("/referee/**")
			.hasRole("REFEREE")
			.antMatchers("/e_admin/**")
			.hasRole("E_ADMIN")
			.antMatchers("/fe_admin/**")
			.hasRole("FE_ADMIN")
			.antMatchers("/s_admin/**")
			.hasRole("S_ADMIN")
			.anyRequest()
			.authenticated()
		.and()
			.formLogin()
			.loginProcessingUrl("/login")
			.usernameParameter("username")
			.passwordParameter("password")
			.successHandler(myAuthenticationSuccessHandler)
			.failureHandler(myAuthenticationFailureHandler)
			.permitAll()
		.and()
			.exceptionHandling().accessDeniedHandler(myAuthenticationAccessDeniedHandler)
		.and()
            .csrf().disable();
	http.logout().logoutSuccessHandler(myLogoutSuccessHandler).permitAll();
	}
}
