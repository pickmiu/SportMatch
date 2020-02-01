package com.johntang.springboot.service;

import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/**
 * @Description 处理邮件有关的事务
 * @Author JohnTang
 * @LatestChangeDate 2020年2月1日
 */

@Service
public class MailService {
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	private TemplateEngine templateEngine;
	
	/**
	 * 生成邮件
	 * @param from
	 * @param to
	 * @param subject
	 * @param content
	 */
	
	private void sendHtmlMail(String from,String to,String subject,String content) {
		try {
			MimeMessage message = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message,true);
			helper.setTo(to);
			helper.setFrom(from);
			helper.setSubject(subject);
			helper.setText(content,true);
			javaMailSender.send(message);
			System.out.println("发送到"+to+"的邮件发送成功");
		} catch (Exception e) {
			System.out.println(e.getMessage());     
		}
	}
	
	/**
	 *发送邮件 
	 * @param mailAddress
	 * @param operation
	 * @param code
	 */
	
	public void sendIdentityCode(String mailAddress,String operation,int code) {
		try {
			Context templatecContext = new Context();
			templatecContext.setVariable("operation",operation);
			templatecContext.setVariable("code", code);
			String finaMail = templateEngine.process("mail_IdentityCode", templatecContext);
			this.sendHtmlMail("hellosmartofme@163.com", mailAddress, "smartofme-验证邮件", finaMail);
			System.out.println("code="+code);
		} catch (Exception e) {
			System.out.println(e.getMessage());     
		}
	}
	
}
