package com.jung.doctor.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import org.apache.struts2.json.annotations.JSON;
import com.jung.common.ConvertUtil;
import com.jung.employee.model.Employee;
import com.jung.region.model.Region;
import com.jung.user.model.User;

@Entity
@Table(name = "Doctor")
@PrimaryKeyJoinColumn(name="doctorID")
public class Doctor extends User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static String REF = "Doctor";
	
	//private int doctorID;// 主键
	private String doctorTargetDept;// 目标科室
	private String doctorName;// 医师姓名
	private String doctorJobTitle;// 医师职称
	private Integer doctorPoints;// 医师积分
	private String doctorMobile;// 医师手机
	private Date doctorRegisterDate;// 注册日期
	private Date lastDate;// 修改日期
	private Date doctorActivateDate;// 激活日期
	private Integer doctorStatus;// 医师状态,0-未激活,1-激活,2-不活动
	private String doctorActivateOption;// 激活选项,A、C、F
	private String inputUser;//录入人员
	private Employee employeeMR;// 关联MR代表ID
	private Region region;//所在地区,地区里面包含医院编号
	public String doctorStatusMapping;//状态映射字段
//	/**
//	 * @return the doctorID
//	 */
//	@Column(name = "doctorID", unique = true)
//	public int getDoctorID() {
//		return doctorID;
//	}
//	/**
//	 * @param doctorID
//	 *            the doctorID to set
//	 */
//	public void setDoctorID(int doctorID) {
//		this.doctorID = doctorID;
//	}

	/**
	 * @return the doctorTargetDept
	 */
	@Column(name = "doctorTargetDept", length = 255)
	public String getDoctorTargetDept() {
		return doctorTargetDept;
	}
	public void setDoctorTargetDept(String doctorTargetDept) {
		this.doctorTargetDept = doctorTargetDept;
	}
	@Column(name = "doctorName", length = 255)
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	@Column(name = "doctorJobTitle", length = 255)
	public String getDoctorJobTitle() {
		return doctorJobTitle;
	}
	public void setDoctorJobTitle(String doctorJobTitle) {
		this.doctorJobTitle = doctorJobTitle;
	}
	@Column(name = "doctorPoints")
	public Integer getDoctorPoints() {
		return doctorPoints;
	}
	public void setDoctorPoints(Integer doctorPoints) {
		this.doctorPoints = doctorPoints;
	}
	@Column(name = "doctorMobile", length = 255)
	public String getDoctorMobile() {
		return doctorMobile;
	}
	public void setDoctorMobile(String doctorMobile) {
		this.doctorMobile = doctorMobile;
	}
	@Temporal(TemporalType.TIMESTAMP)
	public Date getDoctorRegisterDate() {
		if (doctorRegisterDate != null) {
			doctorRegisterDate = ConvertUtil
					.timestampToDate(doctorRegisterDate);
		}
		return doctorRegisterDate;
	}
	public void setDoctorRegisterDate(Date doctorRegisterDate) {
		this.doctorRegisterDate = doctorRegisterDate;
	}
	@Temporal(TemporalType.TIMESTAMP)
	public Date getLastDate() {
		if (lastDate != null) {
			lastDate = ConvertUtil.timestampToDate(lastDate);
		}
		return lastDate;
	}
	public void setLastDate(Date lastDate) {
		this.lastDate = lastDate;
	}
	@Temporal(TemporalType.TIMESTAMP)
	public Date getDoctorActivateDate() {
		if (doctorActivateDate != null) {
			doctorActivateDate = ConvertUtil
					.timestampToDate(doctorActivateDate);
		}
		return doctorActivateDate;
	}
	public void setDoctorActivateDate(Date doctorActivateDate) {
		this.doctorActivateDate = doctorActivateDate;
	}
	@Column(name = "doctorStatus", length = 11)
	public Integer getDoctorStatus() {
		return doctorStatus;
	}
	public void setDoctorStatus(Integer doctorStatus) {
		this.doctorStatus = doctorStatus;
	}
	@Column(name = "doctorActivateOption", length = 255)
	public String getDoctorActivateOption() {
		return doctorActivateOption;
	}
	public void setDoctorActivateOption(String doctorActivateOption) {
		this.doctorActivateOption = doctorActivateOption;
	}
	public void setEmployeeMR(Employee employeeMR) {
		this.employeeMR = employeeMR;
	}
	@ManyToOne(cascade = { CascadeType.ALL },fetch = FetchType.LAZY)
	@JoinColumn(name = "mrID")
	public Employee getEmployeeMR() {
		return employeeMR;
	}
	public void setInputUser(String inputUser) {
		this.inputUser = inputUser;
	}
	@Column(name="inputUser",length=255)
	public String getInputUser() {
		return inputUser;
	}
	public void setRegion(Region region) {
		this.region = region;
	}
	@ManyToOne(cascade={CascadeType.ALL},fetch=FetchType.LAZY)//多对一
	@JoinColumn(name="hospitalID")//外键名称
	public Region getRegion() {
		return region;
	}
	@JSON(serialize=false)
	@Transient
	public String getDoctorStatusMapping() {
		switch (doctorStatus){
		case 1 : return "激活";
		case 2 : return "不活动";
		default : return "未激活";
		}
	}
	public void setDoctorStatusMapping(String doctorStatusMapping) {
		this.doctorStatusMapping=doctorStatusMapping;
	}
}
