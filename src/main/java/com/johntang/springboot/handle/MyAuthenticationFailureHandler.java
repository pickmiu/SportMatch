package com.johntang.springboot.handle;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.johntang.springboot.util.BackFrontMessage;

/** 
 * @Description 用户认证（登录）失败的处理类，用于处理用户登录失败时，服务端返回的内容
 * @Apply 应用在config/MyWebSecurityConfigurerAdapter.java
 * @Author JohnTang
 * @LatestChangeDate 2020年2月1日
 */

@Component
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		BackFrontMessage authMessage = new BackFrontMessage(500, "登录失败", null);
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonData = objectMapper.writeValueAsString(authMessage);
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();		
		out.write(jsonData);
		out.flush();
		out.close();
	}
}
