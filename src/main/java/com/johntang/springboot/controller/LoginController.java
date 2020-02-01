package com.johntang.springboot.controller;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.johntang.springboot.service.MailService;
import com.johntang.springboot.service.UserService;

/**
 * @Description 登录控制器，提供给前端的get，post接口，实现用户登录，注册，修改密码等功能
 * @Author JohnTang
 * @LatestChangeDate 2020年2月1日
 */

@RestController
public class LoginController {
	
	@Autowired 
	UserService userService;
	
	@Autowired
	MailService mailService;
	
	//需要改进返回值
	@PostMapping("/register")
	public Map<String, Integer> register(@RequestParam("username") String username,
            			   @RequestParam("password") String password,
            			   @RequestParam("nickname") String nickname,
            			   @RequestParam("code") int code,
            			   HttpSession session) {
		
		Map<String, Integer> map = new HashMap<>();
		try {
			
			String s=session.getAttribute("code").toString();
			int sessionCode=Integer.parseInt(s);
			
			if(sessionCode == code) {
					if(userService.addUser(username, password, nickname)==1) map.put("message", 3);
					else map.put("message", 2);
			}
			else map.put("message", 1);
			
		} catch (Exception e) {
			
			map.put("message", 0);
			
		}
		
		return map;
	}
	
	//没写完
	@PostMapping("/forgetpassword")
	public Map<String, Integer> forgetPassword(@RequestParam("username") String username,
							@RequestParam("password") String password,
							@RequestParam("code") int code,
							HttpSession session){
		Map<String, Integer> map = new HashMap<String, Integer>();
		return map;
	}
	
	//需要改进
	@PostMapping("/mail")
	public Map<String, Integer> mail(@RequestParam("email") String mailAddress,
						@RequestParam("operation") String operation,HttpSession session) {
		
		int code = (int)((Math.random()*9+1)*100000);
		
		session.setAttribute("code",code);
		
		mailService.sendIdentityCode(mailAddress, operation, code);
		
		Map<String, Integer> map = new HashMap<>();
		map.put("message", 4);
		
		return map;
	}
}
