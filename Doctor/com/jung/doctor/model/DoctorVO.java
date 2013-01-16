package com.jung.doctor.model;

public class DoctorVO {
	private int doctorID;// 主键
	private String doctorTargetDept;// 目标科室
	private String doctorName;// 医师姓名
	private String doctorJobTitle;// 医师职称
	private Integer doctorPoints;// 医师积分
	private String doctorMobile;// 医师手机
	private Integer doctorStatus;// 医师状态,0-未激活,1-激活,2-不活动
    private String employeeName;
    private String regionName;
    public String doctorStatusMapping;//状态映射字段
	/**
	 * @return the doctorID
	 */
	public int getDoctorID() {
		return doctorID;
	}
	/**
	 * @param doctorID the doctorID to set
	 */
	public void setDoctorID(int doctorID) {
		this.doctorID = doctorID;
	}
	/**
	 * @return the doctorTargetDept
	 */
	public String getDoctorTargetDept() {
		return doctorTargetDept;
	}
	/**
	 * @param doctorTargetDept the doctorTargetDept to set
	 */
	public void setDoctorTargetDept(String doctorTargetDept) {
		this.doctorTargetDept = doctorTargetDept;
	}
	/**
	 * @return the doctorName
	 */
	public String getDoctorName() {
		return doctorName;
	}
	/**
	 * @param doctorName the doctorName to set
	 */
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	/**
	 * @return the doctorJobTitle
	 */
	public String getDoctorJobTitle() {
		return doctorJobTitle;
	}
	/**
	 * @param doctorJobTitle the doctorJobTitle to set
	 */
	public void setDoctorJobTitle(String doctorJobTitle) {
		this.doctorJobTitle = doctorJobTitle;
	}
	/**
	 * @return the doctorPoints
	 */
	public Integer getDoctorPoints() {
		return doctorPoints;
	}
	/**
	 * @param doctorPoints the doctorPoints to set
	 */
	public void setDoctorPoints(Integer doctorPoints) {
		this.doctorPoints = doctorPoints;
	}
	/**
	 * @return the doctorMobile
	 */
	public String getDoctorMobile() {
		return doctorMobile;
	}
	/**
	 * @param doctorMobile the doctorMobile to set
	 */
	public void setDoctorMobile(String doctorMobile) {
		this.doctorMobile = doctorMobile;
	}
	/**
	 * @return the doctorStatus
	 */
	public Integer getDoctorStatus() {
		return doctorStatus;
	}
	/**
	 * @param doctorStatus the doctorStatus to set
	 */
	public void setDoctorStatus(Integer doctorStatus) {
		this.doctorStatus = doctorStatus;
	}
	/**
	 * @return the employeeName
	 */
	public String getEmployeeName() {
		return employeeName;
	}
	/**
	 * @param employeeName the employeeName to set
	 */
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	/**
	 * @return the regionName
	 */
	public String getRegionName() {
		return regionName;
	}
	/**
	 * @param regionName the regionName to set
	 */
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	/**
	 * @return the doctorStatusMapping
	 */
	public String getDoctorStatusMapping() {
		switch (doctorStatus){
		case 1 : return "激活";
		case 2 : return "不活动";
		default : return "未激活";
	}
	}
	/**
	 * @param doctorStatusMapping the doctorStatusMapping to set
	 */
	public void setDoctorStatusMapping(String doctorStatusMapping) {
		this.doctorStatusMapping = doctorStatusMapping;
	}
}
