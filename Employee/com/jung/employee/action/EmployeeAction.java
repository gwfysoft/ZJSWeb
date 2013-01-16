package com.jung.employee.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.jung.common.Constants;
import com.jung.common.JqueryGridAction;
import com.jung.common.PasswordUtil;
import com.jung.common.PathUtil;
import com.jung.doctor.model.Doctor;
import com.jung.doctor.service.DoctorService;
import com.jung.employee.model.Employee;
import com.jung.employee.service.EmployeeService;
import com.jung.region.model.Region;
import com.jung.region.service.RegionService;
import com.jung.user.service.UserService;

public class EmployeeAction extends JqueryGridAction {
	
	private static final long serialVersionUID = 1L;
	private static final Log logger = LogFactory.getLog(EmployeeAction.class);
	
	private Employee employee;
	private String employeeID;

	private String parentEmployeeID;
	private String newEmployeeID;//人员调度新MR的ID
	private String regionID;
	private String result;

	private EmployeeService employeeService;
	private RegionService regionService;
	private DoctorService doctorService;
	private UserService userService;

	//新增、修改都在这一个方法里,ajax
	public String addEmployee() {
		if(employeeID!=null&&!employeeID.equals("")){
			if(employee==null){
				employee=employeeService.getEmployeeById(Integer.parseInt(employeeID));
				this.result=SUCCESS;
				return SUCCESS;
			}else{
				Employee emp=employeeService.getEmployeeById(Integer.parseInt(employeeID));
				emp.setEmployeeName(employee.getEmployeeName());
				emp.setEmployeeMobile(employee.getEmployeeMobile());
				emp.setEmployeeNumber(employee.getEmployeeNumber());
				emp.setEmployeeType(employee.getEmployeeType());
				emp.setInputUser("yy");
				if(parentEmployeeID!=null&&!parentEmployeeID.equals("")){
					Employee parent=employeeService.getEmployeeById(Integer.parseInt(parentEmployeeID));
					emp.setParentEmployee(parent);
				}
				if(regionID!=null&&!regionID.equals("")){
					Region region=regionService.getRegionByID(Integer.parseInt(regionID));
					emp.setRegion(region);
				}
				if (employeeService.updateEmployee(emp)) {
					logger.info("修改员工成功");
					this.result=SUCCESS;
					return SUCCESS;
				}
			}
		}else{
			Employee emp = new Employee();
			emp.setEmployeeName(employee.getEmployeeName());
			emp.setEmployeeMobile(employee.getEmployeeMobile());
			emp.setEmployeeNumber(employee.getEmployeeNumber());
			emp.setEmployeeType(employee.getEmployeeType());
			emp.setEmployeePoints(Constants.User.POINTS);
			emp.setPassword(PasswordUtil.generatePassword());
			emp.setInputUser("yy");
			emp.setUserType(Constants.User.EMPLOYEE);
			if(parentEmployeeID!=null&&!parentEmployeeID.equals("")){
				Employee parent=employeeService.getEmployeeById(Integer.parseInt(parentEmployeeID));
				emp.setParentEmployee(parent);
			}
			if(regionID!=null&&!regionID.equals("")){
				Region region=regionService.getRegionByID(Integer.parseInt(regionID));
				emp.setRegion(region);
			}
			if (employeeService.addEmployee(emp)) {
				logger.info("添加员工成功");
				this.result=SUCCESS;
				return SUCCESS;
			}
		}
		return FAIL;
	}
	@Deprecated
	public String prepareUpdate(){
		employee=employeeService.getEmployeeById(Integer.parseInt(employeeID));
		return SUCCESS;
	}
	@Deprecated
	public String update(){
		Employee emp=employeeService.getEmployeeById(Integer.parseInt(employeeID));
		emp.setEmployeeName(employee.getEmployeeName());
		emp.setEmployeeMobile(employee.getEmployeeMobile());
		emp.setEmployeeNumber(employee.getEmployeeNumber());
		emp.setEmployeeType(employee.getEmployeeType());
		emp.setInputUser("yy");
		if(parentEmployeeID!=null&&!parentEmployeeID.equals("")){
			Employee parent=employeeService.getEmployeeById(Integer.parseInt(parentEmployeeID));
			emp.setParentEmployee(parent);
		}
		if(regionID!=null&&!regionID.equals("")){
			Region region=regionService.getRegionByID(Integer.parseInt(regionID));
			emp.setRegion(region);
		}
		if (employeeService.updateEmployee(emp)) {
			logger.info("修改员工成功");
		}
		return SUCCESS;
	}
	//调度MR
	public String transferEmployee(){
		Employee parentEmployee=employeeService.getEmployeeById(Integer.parseInt(parentEmployeeID));
		Employee currentEmployee=employeeService.getEmployeeById(Integer.parseInt(employeeID));
		Employee newMR=employeeService.getEmployeeById(Integer.parseInt(newEmployeeID));
		//将新MR上级更新为当前MR的上级
		if(currentEmployee.getParentEmployee()!=null){
			newMR.setParentEmployee(currentEmployee.getParentEmployee());
			if(employeeService.updateEmployee(newMR)){
				logger.info("更新new MR上级成功");
			}
		}
		//将当前MR拥有的医师转移到new MR旗下
		if(currentEmployee.getDoctorSet()!=null){
			for(Doctor doctor:currentEmployee.getDoctorSet()){
				Doctor doc=doctorService.getDoctorById(doctor.getUserID());
				doc.setEmployeeMR(newMR);
				if(doctorService.updateDoctor(doc)){
					logger.info("更新医师所属MR成功");
				}
			}
		}
		//更新当前MR上级
		currentEmployee.setParentEmployee(parentEmployee);
		if(employeeService.updateEmployee(currentEmployee)){
			logger.info("更新当前MR上级成功");
		}
		this.result=SUCCESS;
		return SUCCESS;
	}
	public String deleteEmployee() {
		Employee employee=employeeService.getEmployeeById(Integer.parseInt(employeeID));
		if(employee.getDoctorSet()!=null){
			for(Doctor doctor:employee.getDoctorSet()){
				if(doctorService.deleteDoctorByID(doctor.getUserID())){
					logger.info("删除员工对应医师成功");
				}
			}
		}
		if(employee.getEmployeeSet()!=null){
			for(Employee em:employee.getEmployeeSet()){
				if(em.getDoctorSet()!=null){
					for(Doctor doc:em.getDoctorSet()){
						if(doctorService.deleteDoctorByID(doc.getUserID())){
							logger.info("删除下级员工对应医师成功");
						}
						if(userService.deleteUserByUserID(doc.getUserID())){
							logger.info("删除Usesr表用户成功");
						}
					}
				}
				if(employeeService.deleteEmployeeById(em.getUserID())){
					logger.info("删除下级员工成功");
				}
				if(userService.deleteUserByUserID(em.getUserID())){
					logger.info("删除Usesr表用户成功");
				}
			}
		}
		
		if(employeeService.deleteEmployeeById(employee.getUserID())){
			logger.info("删除员工成功");
		}
		if(userService.deleteUserByUserID(employee.getUserID())){
			logger.info("删除Usesr表用户成功");
			this.result=SUCCESS;
		}
		return SUCCESS;
	}

	/**
	 * @param result
	 *            the result to set
	 */
	public void setResult(String result) {
		this.result = result;
	}

	/**
	 * @return the result
	 */
	public String getResult() {
		return result;
	}

	/**
	 * @param employee
	 *            the employee to set
	 */
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	/**
	 * @return the employee
	 */
	public Employee getEmployee() {
		return employee;
	}

	/**
	 * @param employeeService
	 *            the employeeService to set
	 */
	@Resource
	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	/**
	 * @param parentEmployeeID the parentEmployeeID to set
	 */
	public void setParentEmployeeID(String parentEmployeeID) {
		this.parentEmployeeID = parentEmployeeID;
	}

	/**
	 * @return the parentEmployeeID
	 */
	public String getParentEmployeeID() {
		return parentEmployeeID;
	}

	/**
	 * @param regionID the regionID to set
	 */
	public void setRegionID(String regionID) {
		this.regionID = regionID;
	}

	/**
	 * @return the regionID
	 */
	public String getRegionID() {
		return regionID;
	}
	/**
	 * @param regionService the regionService to set
	 */
	@Resource
	public void setRegionService(RegionService regionService) {
		this.regionService = regionService;
	}
	/**
	 * @return the employeeID
	 */
	public String getEmployeeID() {
		return employeeID;
	}

	/**
	 * @param employeeID the employeeID to set
	 */
	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}

	/**
	 * @param doctorService the doctorService to set
	 */
	@Resource
	public void setDoctorService(DoctorService doctorService) {
		this.doctorService = doctorService;
	}

	/**
	 * @param userService the userService to set
	 */
	@Resource
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	/**
	 * @param newEmployeeID the newEmployeeID to set
	 */
	public void setNewEmployeeID(String newEmployeeID) {
		this.newEmployeeID = newEmployeeID;
	}
	/**
	 * @return the newEmployeeID
	 */
	public String getNewEmployeeID() {
		return newEmployeeID;
	}
}
