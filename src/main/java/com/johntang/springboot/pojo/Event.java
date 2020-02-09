package com.johntang.springboot.pojo;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @Description 赛事实体
 * @Author JohnTang
 * @LatestChangeDate 2020年2月1日
 */

public class Event{
	
	private Integer id;
	private Integer type;       //赛事种类
	private Integer available;  //是否通过审核 -1提交待审核 0未通过审核 1通过审核
	private Integer status;    // -1未开始报名 0开始报名中 1报名结束未开始比赛 2已开始比赛 3比赛已结束
	private Integer creatorUid;

	private Integer identifyType; //0不需要验证 1需要密码 2需要人工审核
	private String password; //验证密码
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createDate;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date signUpBeginDate;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date signUpEndDate;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date startDate;
	
	private String name;
	private String rule;
	private float expenses;
	private Integer maxPlayer;
	private String hostName;
	private String hostAddress;
	
	private String bonusRule;
	private String eligibility; //参赛资格
	private String remark;	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCreatorUid() {
		return creatorUid;
	}
	public void setCreatorUid(Integer creatorUid) {
		this.creatorUid = creatorUid;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getSignUpBeginDate() {
		return signUpBeginDate;
	}
	public void setSignUpBeginDate(Date signUpBeginDate) {
		this.signUpBeginDate = signUpBeginDate;
	}
	public Date getSignUpEndDate() {
		return signUpEndDate;
	}
	public void setSignUpEndDate(Date signUpEndDate) {
		this.signUpEndDate = signUpEndDate;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRule() {
		return rule;
	}
	public void setRule(String rule) {
		this.rule = rule;
	}
	public float getExpenses() {
		return expenses;
	}
	public void setExpenses(float expenses) {
		this.expenses = expenses;
	}
	public Integer getMaxPlayer() {
		return maxPlayer;
	}
	public void setMaxPlayer(Integer maxPlayer) {
		this.maxPlayer = maxPlayer;
	}
	public String getHostName() {
		return hostName;
	}
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	public String getHostAddress() {
		return hostAddress;
	}
	public void setHostAddress(String hostAddress) {
		this.hostAddress = hostAddress;
	}
	public String getBonusRule() {
		return bonusRule;
	}
	public void setBonusRule(String bonusRule) {
		this.bonusRule = bonusRule;
	}
	public String getEligibility() {
		return eligibility;
	}
	public void setEligibility(String eligibility) {
		this.eligibility = eligibility;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getAvailable() {
		return available;
	}
	public void setAvailable(Integer available) {
		this.available = available;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@JsonIgnore
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getIdentifyType() {
		return identifyType;
	}

	public void setIdentifyType(Integer identifyType) {
		this.identifyType = identifyType;
	}
}
