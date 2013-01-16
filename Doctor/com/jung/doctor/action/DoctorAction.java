package com.jung.doctor.action;


import java.util.Date;
import javax.annotation.Resource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.jung.common.Constants;
import com.jung.common.JqueryGridAction;
import com.jung.doctor.model.Doctor;
import com.jung.doctor.service.DoctorService;
import com.jung.employee.model.Employee;
import com.jung.employee.service.EmployeeService;
import com.jung.region.model.Region;
import com.jung.region.service.RegionService;
import com.jung.user.service.UserService;

public class DoctorAction extends JqueryGridAction {

	private static final long serialVersionUID = 1L;
	private static final Log logger = LogFactory.getLog(DoctorAction.class);
	
	private Doctor doctor;
	private Integer doctorID;
	private Integer regionID;
	private Integer employeeID;
	private String result;
	private String activeOption;
	
	private DoctorService doctorService;
	private EmployeeService employeeService;
	private RegionService regionService;
	private UserService userService;
	//新增、修改都在这一个方法里,ajax
	public String addDoctor(){
		if(doctorID!=null){
			if(doctor==null){
				//doctor=doctorService.getDoctorById(doctorID);
				return INPUT;
			}else{
				Doctor doc=doctorService.getDoctorById(doctorID);
				doc.setDoctorName(doctor.getDoctorName());
				doc.setDoctorMobile(doctor.getDoctorMobile());
				doc.setDoctorTargetDept(doctor.getDoctorTargetDept());
				doc.setDoctorJobTitle(doctor.getDoctorJobTitle());
				doc.setInputUser("yy");
				doc.setLastDate(new Date());
				if(employeeID!=null){
					Employee employeeMR=employeeService.getEmployeeById(employeeID);
					doc.setEmployeeMR(employeeMR);
				}
				if(regionID!=null){
					Region region=regionService.getRegionByID(regionID);
					doc.setRegion(region);
				}
				if(doctorService.updateDoctor(doc)){
					logger.info("修改医师成功");
					this.result=SUCCESS;
					return SUCCESS;
				}
			}
		}else{
			/*Doctor doc=new Doctor();
			doc.setDoctorName(doctor.getDoctorName());
			doc.setDoctorMobile(doctor.getDoctorMobile());
			doc.setDoctorTargetDept(doctor.getDoctorTargetDept());
			doc.setDoctorJobTitle(doctor.getDoctorJobTitle());*/
			doctor.setDoctorStatus(Constants.Doctor.NONACTIVATED);
			doctor.setDoctorPoints(Constants.Doctor.POINTS);
			doctor.setInputUser("yy");
			doctor.setDoctorRegisterDate(new Date());
			doctor.setUserType(Constants.User.DOCTOR);
			
			if(employeeID!=null){
				Employee employeeMR=employeeService.getEmployeeById(employeeID);
				doctor.setEmployeeMR(employeeMR);
			}
			if(regionID!=null){
				Region region=regionService.getRegionByID(regionID);
				doctor.setRegion(region);
			}
			if(doctorService.addDoctor(doctor)){
				logger.info("添加医师成功");
				this.result=SUCCESS;
				return SUCCESS;
			}
		}
		return FAIL;
	}

	public String prepareUpdate(){
		doctor=doctorService.getDoctorById(doctorID);
		return SUCCESS;
	}
	public String update(){
		Doctor doc=doctorService.getDoctorById(doctorID);
		doc.setDoctorName(doctor.getDoctorName());
		doc.setDoctorMobile(doctor.getDoctorMobile());
		doc.setDoctorTargetDept(doctor.getDoctorTargetDept());
		doc.setDoctorJobTitle(doctor.getDoctorJobTitle());
		doc.setInputUser("yy");
		doc.setLastDate(new Date());
		if(employeeID!=null&&!employeeID.equals("")){
			Employee employeeMR=employeeService.getEmployeeById(employeeID);
			doc.setEmployeeMR(employeeMR);
		}
		if(regionID!=null&&!regionID.equals("")){
			Region region=regionService.getRegionByID(regionID);
			doc.setRegion(region);
		}
		if(doctorService.updateDoctor(doc)){
			logger.info("修改医师成功");
		}
		return SUCCESS;
	}
	public String activeDoctor(){
		 doctorService.activeDoctor(doctorID, activeOption);		
         this.result=SUCCESS;
		return SUCCESS;
	}
	public String deleteDoctor(){
		if(doctorService.deleteDoctorByID(doctorID)){
			logger.info("删除医师成功");
		}
		if(userService.deleteUserByUserID(doctorID)){
			logger.info("删除用户成功");
			this.result=SUCCESS;
		}
		return SUCCESS;
	}
	/**
	 * @param doctorService the doctorService to set
	 */
	@Resource
	public void setDoctorService(DoctorService doctorService) {
		this.doctorService = doctorService;
	}
	public String getResult() {
		return result;
	}
	
	public void setResult(String result) {
		this.result = result;
	}
	/**
	 * @param doctor the doctor to set
	 */
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	/**
	 * @return the doctor
	 */
	public Doctor getDoctor() {
		return doctor;
	}
	/**
	 * @param employeeID the employeeID to set
	 */
	public void setEmployeeID(Integer employeeID) {
		this.employeeID = employeeID;
	}
	/**
	 * @return the employeeID
	 */
	public Integer getEmployeeID() {
		return employeeID;
	}
	/**
	 * @param employeeService the employeeService to set
	 */
	@Resource
	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	/**
	 * @param regionID the regionID to set
	 */
	public void setRegionID(Integer regionID) {
		this.regionID = regionID;
	}
	/**
	 * @return the regionID
	 */
	public Integer getRegionID() {
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
	 * @param doctorID the doctorID to set
	 */
	public void setDoctorID(Integer doctorID) {
		this.doctorID = doctorID;
	}
	/**
	 * @return the doctorID
	 */
	public Integer getDoctorID() {
		return doctorID;
	}
	@Resource
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setActiveOption(String activeOption) {
		this.activeOption = activeOption;
	}

	public String getActiveOption() {
		return activeOption;
	}
}
