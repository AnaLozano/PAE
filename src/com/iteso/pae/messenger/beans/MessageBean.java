package com.iteso.pae.messenger.beans;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="message")
@XmlAccessorType(XmlAccessType.FIELD)
public class MessageBean {
	private String id;
	private String message;
	private String createDate;
	private String updateDate;

	public MessageBean(){
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		this.createDate =  dateFormat.format(date); //2014/08/06 15:59:48
	}

	
	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	@Override
	public String toString() {
		return "MessageBean [id=" + id + ", message=" + message + "]";
	}
	
	
	
}
