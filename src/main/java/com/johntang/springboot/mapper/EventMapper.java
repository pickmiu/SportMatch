package com.johntang.springboot.mapper;


import java.util.List;

import com.johntang.springboot.pojo.EAdmin;
import com.johntang.springboot.pojo.Player;
import com.johntang.springboot.pojo.Referee;
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

	int createEventAdmin(EAdmin eAdmin);
	
	int getTotalUnReviewEventNum();
	
	List<Event> getUnReviewEventByPage(Integer currentPageNum,Integer pageSize);
	
	int setEventAvailableById(Integer id ,Integer available);
	
	Event findEventByEventId(int eventId);


	List<Event> getRegisteringEventByPage(Integer currentPageNum,Integer pageSize);

	int getTotalRegisteringEventNum();

	List<Event> getUnStartEventByPage(Integer currentPageNum,Integer pageSize);

	int getTotalUnStartEventNum();

	List<Event> getStartEventByPage(Integer currentPageNum,Integer pageSize);

	int getTotalStartEventNum();


	int createPlayer(Player player);

	int createReferee(Referee referee);


	List<Player> getUnReviewPlayerByPage(Integer currentPageNum,Integer pageSize);

	int getTotalUnReviewPlayerNum();

	List<Referee> getUnReviewRefereeByPage(Integer currentPageNum,Integer pageSize);

	int getTotalUnReviewRefereeNum();

	List<EAdmin> getEAdminByEventId(Integer eventId);


	int setIsPassedByEventIdAndPlayerUid(Integer eventId,Integer playerUid,Integer isPassed);

	int setIsPayedByEventIdAndPlayerUid(Integer eventId,Integer playerUid,Integer isPayed);

	int setIsPassedByEventIdAndRefereeUid(Integer eventId,Integer refereeUid,Integer isPassed);

	int setIsPayedByEventIdAndRefereeUid(Integer eventId,Integer refereeUid,Integer isPayed);
}
