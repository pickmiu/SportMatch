package com.johntang.springboot.pojo;

import java.util.Date;

/**
 * @ClassName EventAdmin
 * @Description 赛事管理员实体类
 * @Author JohnTang
 * @LastChangeDate 2020/2/8 5:51
 * @Version v1.0
 **/

public class EAdmin {

    private Integer adminUid;
    private Integer eventId;
    private Date createDate;

    public EAdmin(Integer adminUid, Integer eventId, Date createDate) {
        this.adminUid = adminUid;
        this.eventId = eventId;
        this.createDate = createDate;
    }

    public Integer getAdminUid() {
        return adminUid;
    }

    public void setAdminUid(Integer adminUid) {
        this.adminUid = adminUid;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }



}
