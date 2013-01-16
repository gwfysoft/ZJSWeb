package com.ufo.jpa.modal;

import java.io.Serializable;
import java.util.Date;
import java.util.ResourceBundle;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.apache.struts2.json.annotations.JSON;

import com.jung.common.ConvertUtil;
import com.jung.employee.model.Employee;
import com.jung.region.model.Region;
import com.jung.user.model.User;

@Entity
@Table(name = "TestModal1")
// @PrimaryKeyJoinColumn(name = "doctorID")
@Access(AccessType.FIELD)
public class TestModal implements Serializable {

	public enum EmployeeType {
		FULL_TIME_EMPLOYEE, PART_TIME_EMPLOYEE, CONTRACT_EMPLOYEE
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static String REF = "TestModal";
	@Id
	// @GeneratedValue(strategy = GenerationType.TABLE)
	@TableGenerator(name = "UFO_Gen", table = "ID_GEN", pkColumnName = "GEN_NAME", valueColumnName = "GEN_VAL", pkColumnValue = "UFO_Gen", initialValue = 10000, allocationSize = 100)
	@GeneratedValue(generator = "UFO_Gen")
	private int doctorID;// 主键
	private String doctorTargetDept;// 目标科室
	private String doctorName;// 医师姓名
	private String doctorJobTitle;// 医师职称
	private Integer doctorPoints;// 医师积分

	private String doctorMobile;// 医师手机
	private Date doctorRegisterDate;// 注册日期
	private Date lastDate;// 修改日期
	private java.sql.Date lastDate2;
	private java.sql.Time lastDate3;
	private java.sql.Timestamp lastDate4;
	private java.util.Calendar lastDate5;
	@Temporal(TemporalType.DATE)
	@Column(name = "lastDate6cal")
	private java.util.Calendar lastDate6;
	private Date doctorActivateDate;// 激活日期
	private Integer doctorStatus;// 医师状态,0-未激活,1-激活,2-不活动
	private String doctorActivateOption;// 激活选项,A、C、F
	private String inputUser;// 录入人员
	private Employee employeeMR;// 关联MR代表ID
	private Region region;// 所在地区,地区里面包含医院编号
	public String doctorStatusMapping;// 状态映射字段
	private String phoneNum2;// 没写getset因为上面是@Access(AccessType.FIELD)
	@Transient
	private String phoneNum4;
	private byte[] picture;
	@Enumerated(EnumType.STRING)
	// 为什么不用ORDINAL?因为ordinal是序列.如果我在enum里面随意吃插入一个新类型.
	// 有可能在中间插入.完了.以前存的数据都被打乱了.序列对不上.(如果规定必须在屁股后面加,那倒是没问题)那么用string便搞定.当然.如果我把enum的字段名字都改掉.那...
	// 不会吧.
	private EmployeeType type;
	public EmployeeType getType() {
		return type;
	}

	public void setType(EmployeeType type) {
		this.type = type;
	}

	transient private String translatedName;// 这里没有用注解.这样可以让这个字段从一个VM序列号到另外一个VM时,将会重新初始化以适应相应的VM设置.如果要跨VM序列化.应该使用注解代替修饰符.

	public String toString() {
		if (translatedName == null) {
			translatedName = ResourceBundle.getBundle("TestModalResouces").getString("TestModal");
		}
		return translatedName + ":" + " " + doctorName;
	}

	@Lob
	@Column(name = "pic")
	@Basic(fetch = FetchType.LAZY)
	@Access(AccessType.PROPERTY)
	public byte[] getPicture() {
		return picture;
	}

	public void setPicture(byte[] picture) {
		this.picture = picture;
	}

	@Access(AccessType.PROPERTY)
	@Column(name = "phoneNum")
	// 这里是我是希望在phoneNum4字段上面加入一些逻辑.而且数据库字段希望是phoneNum.因为class上面是@Access(AccessType.FIELD),如果不在field上加入transient的话会创建两个数据库字段.
	public String getPhoneNum4aa() {
		return phoneNum4 + "ufo";
	}

	public void setPhoneNum4aa(String phoneNum4) {
		this.phoneNum4 = phoneNum4 + "ufo";
	}

	public String getPhoneNum() {
		return phoneNum2;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum2 = phoneNum;
	}

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

	@Column(name = "DOCTORNAMEAAbb", length = 255)
	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	@Column(name = "doctorJobTitle", length = 122)
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
			doctorRegisterDate = ConvertUtil.timestampToDate(doctorRegisterDate);
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
			doctorActivateDate = ConvertUtil.timestampToDate(doctorActivateDate);
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

	@ManyToOne(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
	@JoinColumn(name = "mrID")
	public Employee getEmployeeMR() {
		return employeeMR;
	}

	public void setInputUser(String inputUser) {
		this.inputUser = inputUser;
	}

	@Column(name = "inputUser", length = 255)
	public String getInputUser() {
		return inputUser;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	@Basic(fetch = FetchType.LAZY)
	@ManyToOne(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
	// 多对一
	@JoinColumn(name = "hospitalID")
	// 外键名称
	public Region getRegion() {
		return region;
	}

	@JSON(serialize = false)
	@Transient
	public String getDoctorStatusMapping() {
		switch (doctorStatus) {
		case 1:
			return "激活";
		case 2:
			return "不活动";
		default:
			return "未激活";
		}
	}

	public void setDoctorStatusMapping(String doctorStatusMapping) {
		this.doctorStatusMapping = doctorStatusMapping;
	}
}
