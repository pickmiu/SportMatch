package com.johntang.springboot.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

/**
 * @ClassName Player
 * @Description 选手实体
 * @Author JohnTang
 * @LastChangeDate 2020/2/7 16:05
 * @Version v1.0
 **/

public class Player {
    private Integer eventId;
    private Integer playerUid;
    private Integer isPassed;
    private Integer isPayed;
    private Integer weight;
    private String identifyMsg;
    private Date submitDate;

    public Player(Integer eventId, Integer playerUid, Integer isPassed, Integer isPayed, Integer weight, String identifyMsg, Date submitDate) {
        this.eventId = eventId;
        this.playerUid = playerUid;
        this.isPassed = isPassed;
        this.isPayed = isPayed;
        this.weight = weight;
        this.identifyMsg = identifyMsg;
        this.submitDate = submitDate;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public Integer getPlayerUid() {
        return playerUid;
    }

    public void setPlayerUid(Integer playerUid) {
        this.playerUid = playerUid;
    }

    public Integer getIsPassed() {
        return isPassed;
    }

    public void setIsPassed(Integer isPassed) {
        this.isPassed = isPassed;
    }

    public Integer getIsPayed() {
        return isPayed;
    }

    public void setIsPayed(Integer isPayed) {
        this.isPayed = isPayed;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getIdentifyMsg() {
        return identifyMsg;
    }

    public void setIdentifyMsg(String identifyMsg) {
        this.identifyMsg = identifyMsg;
    }

    public Date getSubmitDate() {
        return submitDate;
    }

    @JsonIgnore
    public void setSubmitDate(Date submitDate) {
        this.submitDate = submitDate;
    }



}
