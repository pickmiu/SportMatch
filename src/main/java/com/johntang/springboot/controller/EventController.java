package com.johntang.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.johntang.springboot.pojo.Event;
import com.johntang.springboot.pojo.User;
import com.johntang.springboot.service.EventService;
import com.johntang.springboot.util.BackFrontMessage;
import com.johntang.springboot.util.BackFrontPage;

/**
 * @Description 赛事控制器，提供给前端的get，post接口
 * @Author JohnTang
 * @LatestChangeDate 2020年2月1日
 */

@RestController
public class EventController {
	
	@Autowired
	EventService eventService;
	
	//用户发布赛事
	@PostMapping("/user/createEvent")
	public BackFrontMessage createEvent(Authentication authentication,@RequestBody Event event) {
		event.setCreatorUid(((User)authentication.getPrincipal()).getId());
		
		if(eventService.createEvent(event) == 1) {
			return new BackFrontMessage(200, "添加成功", null);
		}else {
			return new BackFrontMessage(200, "添加失败", null);
		}
	}
	
	//总赛事管理员获得未通过赛事的列表
	@PostMapping("/fe_admin/getUnAvailableEventByPage")
	public BackFrontPage getUnAvailableEventByPage(@RequestParam Integer currentPageNum,@RequestParam Integer pageSize) {
		return eventService.getUnAvailableEventByPage(currentPageNum, pageSize);
	}
	
	//总赛事管理员审核赛事
	@PostMapping("/fe_admin/reviewEvent")
	public BackFrontMessage reviewEvent(@RequestParam Integer eventId,Integer available,String comment) {
		if(eventService.reviewEvent(eventId, available, comment) == 1) {
			return new BackFrontMessage(200, "修改成功", null);
		}else {
			return new BackFrontMessage(200, "修改失败", null);
		}
		//邮箱提醒赛事管理员状态还未实现
	}
	
	//主界面post
	
}
