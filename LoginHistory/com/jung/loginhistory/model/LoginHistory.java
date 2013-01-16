package com.jung.loginhistory.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.jung.common.ConvertUtil;
@Entity
@Table(name = "LoginHistory")
public class LoginHistory implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static String REF = "LoginHistory";
	
	private int loginHistoryID;
	private String name;//登录名
	private Integer type;//登录人类型  0 MR 1医师
	private Date date;//登录日期
	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	@Column(name = "loginHistoryID", unique = true, nullable = false)
	public int getLoginHistoryID() {
		return loginHistoryID;
	}
	public void setLoginHistoryID(int loginHistoryID) {
		this.loginHistoryID = loginHistoryID;
	}
	@Column(name="name",length=255)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name="type",length=11)
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	@Temporal(TemporalType.TIMESTAMP)
	public Date getDate() {
		if(date!=null){
			date=ConvertUtil.timestampToDate(date);
		}
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
}
