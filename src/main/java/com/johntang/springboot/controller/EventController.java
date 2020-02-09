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
			return new BackFrontMessage(500, "添加失败", null);
		}
	}
	
	//总赛事管理员获得未通过赛事的列表
	@PostMapping("/fe_admin/getUnAvailableEvent")
	public BackFrontMessage getUnAvailableEventByPage(@RequestParam Integer currentPageNum,@RequestParam Integer pageSize) {
		return eventService.getUnAvailableEventByPage(currentPageNum, pageSize);
	}
	
	//总赛事管理员审核赛事
	@PostMapping("/fe_admin/reviewEvent")
	public BackFrontMessage reviewEvent(@RequestParam Integer eventId,Integer available,String comment) {
		if(eventService.reviewEvent(eventId, available) == 1) {
			return new BackFrontMessage(200, "修改成功", null);
		}else {
			return new BackFrontMessage(500, "修改失败", null);
		}
		//邮箱提醒赛事管理员状态还未实现
	}
	
	//主界面
	//获取指定状态的赛事
	@PostMapping("/getEvent")
	public BackFrontMessage getEvent(@RequestParam Integer status,@RequestParam Integer currentPageNum,@RequestParam Integer pageSize){
		return eventService.getEventByPage(status, currentPageNum, pageSize);
	}

	//报名阶段
	//用户报名赛事
	//registertype是指1裁判还是0选手
	@PostMapping("/user/eventRegister")
	public BackFrontMessage eventRegister(@RequestParam Integer eventId,@RequestParam Integer registerType,@RequestParam String password,@RequestParam String identifyMsg){
		return eventService.eventRegister(eventId,registerType,password,identifyMsg);
	}

	//赛事管理员获得未通过的选手、裁判
	@PostMapping("/e_admin/getUnAvailablePerson")
	public BackFrontMessage getUnAvailablePerson(@RequestParam Integer eventId,@RequestParam Integer registerType,@RequestParam Integer currentPageNum,@RequestParam Integer pageSize){
		return eventService.getUnAvailablePerson(eventId,registerType,currentPageNum,pageSize);
	}

	//赛事管理员审核未通过的选手
	@PostMapping("/e_admin/reviewPlayer")
	public BackFrontMessage reviewPlayer(@RequestParam Integer eventId,@RequestParam Integer playerUid,@RequestParam Integer isPassed,@RequestParam Integer isPayed,@RequestParam String comment) {
		return eventService.reviewPlayer(eventId,playerUid,isPassed,isPayed);
	}

	//赛事管理员审核未通过的裁判
	@PostMapping("/e_admin/reviewReferee")
	public BackFrontMessage reviewReferee(@RequestParam Integer eventId,@RequestParam Integer refereeUid,@RequestParam Integer isPassed,@RequestParam String comment){
		return eventService.reviewReferee(eventId,refereeUid,isPassed);
	}



}
