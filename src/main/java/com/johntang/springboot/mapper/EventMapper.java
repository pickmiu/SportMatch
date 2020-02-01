package com.johntang.springboot.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.johntang.springboot.pojo.Event;
import org.springframework.stereotype.Component;

/**
 * @Description mybatis框架下赛事相关功能的mysql数据库持久化接口
 * @Author JohnTang
 * @LatestChangeDate 2020年2月1日
 */

@Component
@Mapper
public interface EventMapper {
	
	int createEvent(Event event);
	
	int getTotalUnAvailableEventNum();
	
	List<Event> getUnAvailableEventByPage(Integer currentPageNum,Integer pageSize);
	
	int setEventAvailableById(Integer id ,Integer available);
	
	Event findEventByEventId(int eventId);
}
