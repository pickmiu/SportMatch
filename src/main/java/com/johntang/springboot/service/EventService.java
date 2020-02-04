package com.johntang.springboot.service;

import java.util.List;

import com.johntang.springboot.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.johntang.springboot.mapper.EventMapper;
import com.johntang.springboot.pojo.Event;
import com.johntang.springboot.util.BackFrontPage;

/**
 * @Description 处理赛事有关的事务
 * @Author JohnTang
 * @LatestChangeDate 2020年2月1日
 */

@Service
public class EventService {

	@Autowired
	private EventMapper eventMapper;

	@Autowired
	private RoleMapper roleMapper;

	//创建赛事
	public int createEvent(Event event) {
		if(event.getCreateDate().before(event.getSignUpBeginDate())) {
			event.setStatus(-1);
		}else {
			event.setStatus(0);
		}
		
		//判断available值的合法性
		if(event.getAvailable() != -1 && event.getAvailable() != 0) {
			event.setAvailable(-1);
		}

		return eventMapper.createEvent(event);
	}
	
	//获取审核中赛事列表
	public BackFrontPage getUnAvailableEventByPage(Integer currentPageNum,Integer pageSize) {
		Integer totalPageNum; //总页数
		Integer totalNum = eventMapper.getTotalUnAvailableEventNum(); //UnAvailableEvent总数
		
		if (totalNum % pageSize == 0) {
			totalPageNum = totalNum / pageSize;
		}else {
			totalPageNum = totalNum / pageSize + 1;
		}
		
		//如果请求页数大于总页数返回空
		if(currentPageNum > totalPageNum) {
			return new BackFrontPage(pageSize, totalPageNum, currentPageNum, null);
		}
		
		List<Event> currentPageList = eventMapper.getUnAvailableEventByPage(currentPageNum, pageSize);
		
		return new BackFrontPage(pageSize, totalPageNum, currentPageNum, currentPageList);
	}
	
	//审核赛事
	public int reviewEvent(Integer eventId,Integer available) {
		
		//检验available合法性
		if(available != -1&& available != 1) return 0;
		
		if (eventMapper.setEventAvailableById(eventId, available) == 1) {
			//如果赛事通过则设置赛事管理员权限
			roleMapper.addUserIdAndRoleId(eventMapper.findEventByEventId(eventId).getCreatorUid(),4);
			return 1;
		}else {
			return 0;
		}
		
	}
}
