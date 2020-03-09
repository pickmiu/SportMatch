package com.johntang.springboot.pojo.rabbitmq;

import java.io.Serializable;

/**
 * @ClassName MailMessage
 * @Description RabbitMq中发送给mail服务器的信息
 * @Author JohnTang
 * @LastChangeDate 2020/3/8 16:06
 * @Version v1.0
 **/

public class MailMessage implements Serializable {
    private String operationType; //操作类型
    private String destMailAddress; //目的邮箱地址
    private String nickname;   //用户昵称
    private String information;  //信息
    
    public MailMessage() {
        
    }
    
    public MailMessage(String operationType, String destMailAddress, String nickname, String information) {
        this.operationType = operationType;
        this.destMailAddress = destMailAddress;
        this.nickname = nickname;
        this.information = information;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public String getDestMailAddress() {
        return destMailAddress;
    }

    public void setDestMailAddress(String destMailAddress) {
        this.destMailAddress = destMailAddress;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }
}
