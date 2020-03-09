package com.johntang.springboot.service;

import java.util.Iterator;
import java.util.List;

import com.johntang.springboot.mapper.RoleMapper;
import com.johntang.springboot.pojo.*;
import com.johntang.springboot.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.johntang.springboot.mapper.EventMapper;

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
	public BackFrontMessage createEvent(Event event) {
		if(event.getCreateDate().before(event.getSignUpBeginDate())) {
			event.setStatus(-1);
		}else {
			event.setStatus(0);
		}

		//设置赛事为待审核状态
		event.setAvailable(-1);

		if(eventMapper.createEvent(event) == 1) {
			return new BackFrontMessage(200, "赛事添加成功", null);
		}else {
			return new BackFrontMessage(500, "赛事添加失败", null);
		}
	}


	//获取审核中赛事列表
	public BackFrontMessage getUnAvailableEventByPage(Integer currentPageNum,Integer pageSize) {

		List<Event> currentPageList = eventMapper.getUnReviewEventByPage(currentPageNum, pageSize);
		return PageUtils.generateMessage(eventMapper.getTotalUnReviewEventNum(),currentPageNum,pageSize,currentPageList);
	}


	//审核赛事
	public int reviewEvent(Integer eventId,Integer available) {
		
		//检验available合法性
		if(available != 1 && available != 0) return 0;
		
		if (eventMapper.setEventAvailableById(eventId, available) == 1) {

			//如果赛事通过则设置赛事管理员权限
			Event currentEvent = eventMapper.findEventByEventId(eventId);
			roleMapper.addUserIdAndRoleId(currentEvent.getCreatorUid(),4);
			//将创建者添加进赛事管理员表
			EAdmin newEAdmin = new EAdmin(currentEvent.getCreatorUid(),eventId,null);
			eventMapper.createEventAdmin(newEAdmin);
			return 1;
		}else {
			return 0;
		}
	}


	//主界面获取相应状态的赛事列表
	public BackFrontMessage getEventByPage(Integer status,Integer currentPageNum,Integer pageSize){

		if(status == 0){

			List<Event> currentPageList = eventMapper.getRegisteringEventByPage(currentPageNum,pageSize);
			//把数据交给自定义的分页工具类处理
			return PageUtils.generateMessage(eventMapper.getTotalRegisteringEventNum(),currentPageNum,pageSize,currentPageList);
		}else if(status == 1){

			List<Event> currentPageList = eventMapper.getUnStartEventByPage(currentPageNum,pageSize);
			//把数据交给自定义的分页工具类处理
			return PageUtils.generateMessage(eventMapper.getTotalUnStartEventNum(),currentPageNum,pageSize,currentPageList);
		}else if(status == 2){

			List<Event> currentPageList = eventMapper.getStartEventByPage(currentPageNum,pageSize);
			return PageUtils.generateMessage(eventMapper.getTotalStartEventNum(),currentPageNum,pageSize,currentPageList);
		}else{
			//非法status处理
			return new BackFrontMessage(500,"参数错误：status非法",null);
		}

	}


	//用户赛事报名
	public BackFrontMessage eventRegister(Integer eventId,Integer registerType,String password,String identifyMsg){
		Event currentEvent = eventMapper.findEventByEventId(eventId);

		//判断赛事是否存在
		if(currentEvent == null){
			return new BackFrontMessage(500,"赛事不存在",null);
		}

		//不在报名时间
		if(currentEvent.getStatus() != 0){
			return new BackFrontMessage(500,"不在比赛报名时间内",null);
		}

		//数据非法
		if(registerType != 0 && registerType != 1){
			return new BackFrontMessage(500,"参数错误：registerType非法",null);
		}

		User currentUser = UserAuthenticationUtils.getCurrentUser(); //获取当前用户信息

		//裁判必须要人工审核
		//Date都为null数据库里自动生成
		int flag;  //添加是否成功的标志
		if(registerType == 1){  //注册的是裁判

			Referee newReferee = new Referee(eventId,currentUser.getId(),-1,identifyMsg,null);
			flag = eventMapper.createReferee(newReferee);
		}else{  //注册的是选手

			Player newPlayer;
			if(currentEvent.getIdentifyType() == 0){  //不需要验证

				if(currentEvent.getExpenses() == 0) {  //参赛费用为0
					newPlayer = new Player(eventId,currentUser.getId(),1,1,0,identifyMsg,null);
				}else{
					newPlayer = new Player(eventId,currentUser.getId(),1,0,0,identifyMsg,null);
				}
			}else if(currentEvent.getIdentifyType() == 1){ //需要密码

				if(currentEvent.getPassword().equals(password)){ //密码正确

					if(currentEvent.getExpenses() == 0) {  //参赛费用为0
						newPlayer = new Player(eventId,currentUser.getId(),1,1,0,identifyMsg,null);
					}else{
						newPlayer = new Player(eventId,currentUser.getId(),1,0,0,identifyMsg,null);
					}
				}else {
					return new BackFrontMessage(500,"赛事密码错误",null);
				}

			}else {  //需要人工审核
				newPlayer = new Player(eventId,currentUser.getId(),-1,0,0,identifyMsg,null);
			}

			flag = eventMapper.createPlayer(newPlayer);
		}

		if(flag == 1){
			return new BackFrontMessage(200,"添加成功",null);
		}else {
			return new BackFrontMessage(500,"添加失败：异常！数据库添加失败",null);
		}
	}


	//赛事管理员报名赛事未审核的人列表
	public BackFrontMessage getUnAvailablePerson(Integer eventId, Integer registerType, Integer currentPageNum, Integer pageSize){
		Event currentEvent = eventMapper.findEventByEventId(eventId);

		//数据非法
		if(registerType != 0 && registerType != 1){
			return new BackFrontMessage(500,"参数错误：registerType非法",null);
		}

		//判断赛事是否存在
		if(currentEvent == null){
			return new BackFrontMessage(500,"赛事不存在",null);
		}

		//权限 判断当前操作者是否为当前赛事管理员
		List<EAdmin> currentEventEAdminList = eventMapper.getEAdminByEventId(eventId);
		User currentUser = UserAuthenticationUtils.getCurrentUser();
		Iterator iterator = currentEventEAdminList.iterator();
		boolean flag = false;
		while(iterator.hasNext()){
			EAdmin eAdmin = (EAdmin)(iterator.next());
			if(eAdmin.getAdminUid().equals(currentUser.getId())){
				flag = true;
				break;
			}
		}
		if(!flag){
			return new BackFrontMessage(301,"权限不足：用户没有当前赛事管理员权限",null);
		}

		if(registerType == 0){ //获取的是未审核的选手

			List<Player> currentPageList = eventMapper.getUnReviewPlayerByPage(currentPageNum,pageSize);
			return PageUtils.generateMessage(eventMapper.getTotalUnReviewPlayerNum(),currentPageNum,pageSize,currentPageList);
		}else{ //裁判

			List<Referee> currentPageList = eventMapper.getUnReviewRefereeByPage(currentPageNum,pageSize);
			return PageUtils.generateMessage(eventMapper.getTotalUnReviewRefereeNum(),currentPageNum,pageSize,currentPageList);
		}
	}


	//赛事管理员审核未审核选手
	public BackFrontMessage reviewPlayer(Integer eventId,Integer playerUid,Integer isPassed, Integer isPayed){
		//数据校验未完成

		//权限
		List<EAdmin> currentEventEAdminList = eventMapper.getEAdminByEventId(eventId);
		User currentUser = UserAuthenticationUtils.getCurrentUser();
		Iterator iterator = currentEventEAdminList.iterator();
		boolean flag = false;
		while(iterator.hasNext()){
			EAdmin eAdmin = (EAdmin)(iterator.next());
			if(eAdmin.getAdminUid().equals(currentUser.getId())){
				flag = true;
				break;
			}
		}
		if(!flag){
			return new BackFrontMessage(301,"权限不足：用户没有当前赛事管理员权限",null);
		}

		eventMapper.setIsPassedByEventIdAndPlayerUid(eventId,playerUid,isPassed);
		eventMapper.setIsPayedByEventIdAndPlayerUid(eventId,playerUid,isPayed);

		return new BackFrontMessage(200,"修改成功",null);
	}


	//赛事管理员审核未审核裁判
	public BackFrontMessage reviewReferee(Integer eventId, Integer refereeUid, Integer isPassed){

		//数据校验未完成

		//权限
		List<EAdmin> currentEventEAdminList = eventMapper.getEAdminByEventId(eventId);
		User currentUser = UserAuthenticationUtils.getCurrentUser();
		Iterator iterator = currentEventEAdminList.iterator();
		boolean flag = false;
		while(iterator.hasNext()){
			EAdmin eAdmin = (EAdmin)(iterator.next());
			if(eAdmin.getAdminUid().equals(currentUser.getId())){
				flag = true;
				break;
			}
		}
		if(!flag){
			return new BackFrontMessage(301,"权限不足：用户没有当前赛事管理员权限",null);
		}

		//遗留问题不知道数据库会不会出现添加不成功的问题
		eventMapper.setIsPassedByEventIdAndPlayerUid(eventId,refereeUid,isPassed);

		return new BackFrontMessage(200,"修改成功",null);
	}

}
