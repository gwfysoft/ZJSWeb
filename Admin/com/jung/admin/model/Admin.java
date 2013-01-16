package com.jung.admin.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.jung.user.model.User;

@Entity
@Table(name = "Admin")
@PrimaryKeyJoinColumn(name="adminID")
public class Admin extends User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static String REF = "Admin";
//	private int adminID;
	private String name;
	private String passWord;
	/**
	 * @return the adminID
	 */
//	@Id
//	@GeneratedValue(strategy =GenerationType.AUTO)
//	@Column(name = "adminID", unique = true, nullable = false)
//	public int getAdminID() {
//		return adminID;
//	}
//	/**
//	 * @param adminID the adminID to set
//	 */
//	public void setAdminID(int adminID) {
//		this.adminID = adminID;
//	}
	/**
	 * @return the name
	 */
	@Column(length = 255)
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the passWord
	 */
	@Column(length = 255)
	public String getPassWord() {
		return passWord;
	}
	/**
	 * @param passWord the passWord to set
	 */
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
}
