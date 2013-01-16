package com.jung.employee.model;

public class EmployeeVO {

	private int employeeID;
	private String employeeName;
	private String employeeMobile;
	private String password;
	private Integer employeePoints;
	private String employeeNumber;
	private Integer employeeType;// 代表类型,1->MR,2->DSM,3->PS,4->MARKET
	private String employeeTypeMapping;// 代表类型映射字段
	private String regionName;
	private String parentEmployeeName;
	/**
	 * @return the employeeID
	 */
	public int getEmployeeID() {
		return employeeID;
	}
	/**
	 * @param employeeID the employeeID to set
	 */
	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
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
	 * @return the employeeMobile
	 */
	public String getEmployeeMobile() {
		return employeeMobile;
	}
	/**
	 * @param employeeMobile the employeeMobile to set
	 */
	public void setEmployeeMobile(String employeeMobile) {
		this.employeeMobile = employeeMobile;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the employeePoints
	 */
	public Integer getEmployeePoints() {
		return employeePoints;
	}
	/**
	 * @param employeePoints the employeePoints to set
	 */
	public void setEmployeePoints(Integer employeePoints) {
		this.employeePoints = employeePoints;
	}
	/**
	 * @return the employeeNumber
	 */
	public String getEmployeeNumber() {
		return employeeNumber;
	}
	/**
	 * @param employeeNumber the employeeNumber to set
	 */
	public void setEmployeeNumber(String employeeNumber) {
		this.employeeNumber = employeeNumber;
	}
	/**
	 * @return the employeeType
	 */
	public Integer getEmployeeType() {
		return employeeType;
	}
	/**
	 * @param employeeType the employeeType to set
	 */
	public void setEmployeeType(Integer employeeType) {
		this.employeeType = employeeType;
	}
	/**
	 * @return the employeeTypeMapping
	 */
	public String getEmployeeTypeMapping() {
		switch (employeeType) {
		case 1:
			return "MR";
		case 2:
			return "DSM";
		case 3:
			return "PS";
		case 4:
			return "MARKET";
		default:
			return "";
		}
	}
	/**
	 * @param employeeTypeMapping the employeeTypeMapping to set
	 */
	public void setEmployeeTypeMapping(String employeeTypeMapping) {
		this.employeeTypeMapping = employeeTypeMapping;
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
	 * @return the parentEmployeeName
	 */
	public String getParentEmployeeName() {
		return parentEmployeeName;
	}
	/**
	 * @param parentEmployeeName the parentEmployeeName to set
	 */
	public void setParentEmployeeName(String parentEmployeeName) {
		this.parentEmployeeName = parentEmployeeName;
	}
}
