package com.johntang.springboot.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

/**
 * @ClassName Referee
 * @Description 裁判实体
 * @Author JohnTang
 * @LastChangeDate 2020/2/7 16:05
 * @Version v1.0
 **/

public class Referee {
    private Integer eventId;
    private Integer refereeUid;
    private Integer isPassed;
    private String identifyMsg;
    private Date submitDate;

    public Referee(Integer eventId, Integer refereeUid, Integer isPassed, String identifyMsg, Date submitDate) {
        this.eventId = eventId;
        this.refereeUid = refereeUid;
        this.isPassed = isPassed;
        this.identifyMsg = identifyMsg;
        this.submitDate = submitDate;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public Integer getRefereeUid() {
        return refereeUid;
    }

    public void setRefereeUid(Integer refereeUid) {
        this.refereeUid = refereeUid;
    }

    public Integer getIsPassed() {
        return isPassed;
    }

    public void setIsPassed(Integer isPassed) {
        this.isPassed = isPassed;
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
