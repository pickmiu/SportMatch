package com.johntang.springboot.util;

/**
 * @Description 用户登录操作时返回给前端的json
 * @Author JohnTang
 * @LatestChangeDate 2020年2月1日
 */

public class BackFrontMessage {
	private int status;
	private String msg;
	private Object object;
	
	public BackFrontMessage(int status,String msg,Object object) {
		this.status=status;
		this.msg=msg;
		this.setObject(object);
	}
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}
	
}
