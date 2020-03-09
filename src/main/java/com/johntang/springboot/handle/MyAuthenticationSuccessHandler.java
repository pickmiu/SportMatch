package com.johntang.springboot.handle;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.johntang.springboot.pojo.User;
import com.johntang.springboot.pojo.BackFrontMessage;

/** 
 * @Description 用户认证（登录）成功时的处理类，用于处理用户登录成功时，服务端返回的内容
 * @Apply 应用在config/MyWebSecurityConfigurerAdapter.java
 * @Author JohnTang
 * @LatestChangeDate 2020年2月1日
 */

@Component
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler{
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		User currentUser = (User)authentication.getPrincipal();
		BackFrontMessage authMessage = new BackFrontMessage(200, "登录成功", currentUser);
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonData = objectMapper.writeValueAsString(authMessage);
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();		
		out.write(jsonData);
		out.flush();
		out.close();
	}
}
