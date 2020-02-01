package com.johntang.springboot.handle;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.johntang.springboot.util.BackFrontMessage;

/**
 * @Description 用户权限不足的处理类，用于处理用户权限不足时，服务端返回的内容
 * @Apply 应用在config/MyWebSecurityConfigurerAdapter.java
 * @Author JohnTang
 * @LatestChangeDate 2020年2月1日
 */

@Component
public class MyAuthenticationAccessDeniedHandler implements AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
		BackFrontMessage authMessage = new BackFrontMessage(301, "权限不足", null);
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonData = objectMapper.writeValueAsString(authMessage);
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.write(jsonData);
		out.flush();
		out.close();
	}

}
