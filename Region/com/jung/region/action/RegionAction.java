package com.jung.region.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.jung.common.JqueryGridAction;
import com.jung.doctor.model.Doctor;
import com.jung.doctor.service.DoctorService;
import com.jung.employee.action.EmployeeAction;
import com.jung.employee.model.Employee;
import com.jung.employee.service.EmployeeService;
import com.jung.region.model.Region;
import com.jung.region.service.RegionService;
import com.jung.user.service.UserService;

public class RegionAction extends JqueryGridAction {

	private static final long serialVersionUID = 1L;
	private static final Log logger = LogFactory.getLog(RegionAction.class);
	
	private Region region;
	private String result;
	private String parentStr = "";
	private String regionID;

	private List<Region> regions=new ArrayList<Region>();
	private RegionService regionService;
	private DoctorService doctorService;
	private EmployeeService employeeService;
	private UserService userService;

	public String add() {
		logger.info("ZJS:"+this.getClass().getName()+":"+"add");
		if (region == null) {
			region = new Region();
			region.setParentID(Integer.parseInt(parentStr));
			Region parent = new Region();
			if (region.getParentID() == 0) {
				parent.setLayer(0);
				parent.setRegionName("无");
				parent.setRegionID(0);
			} else {
				parent = regionService.getRegionByID(region.getParentID());
			}
			region.setParentRegion(parent);
			return INPUT;
		}
		if(region.getRegionID()==null){
			regionService.saveRegion(region);
		}else{
			regionService.updateRegion(region);
		}
			return SUCCESS;
	}
	public String prepareUpdate(){
		region=regionService.getRegionByID(Integer.parseInt(regionID));
		Region parent=new Region();
		parent=regionService.getRegionByID(region.getParentID());
		region.setParentRegion(parent);
		return SUCCESS;
	}
	public String update(){
		region=regionService.getRegionByID(region.getRegionID());
		Region parent=new Region();
		parent=regionService.getRegionByID(region.getParentID());
		region.setParentRegion(parent);
		region.setParentID(region.getParentID());
		regionService.updateRegion(region);
		return SUCCESS;
	}
	public String view(){
		region=regionService.getRegionByID(Integer.parseInt(regionID));
		Region parent=new Region();
		parent=regionService.getRegionByID(region.getParentID());
		region.setParentRegion(parent);
		region.setParentID(region.getParentID());
		return SUCCESS;
	}
	public String delete(){
		Doctor doct=doctorService.getDoctorByRegionID(Integer.parseInt(regionID));
		if(doctorService.deleteDoctorByRegionID(Integer.parseInt(regionID))){
			logger.info("删除区域关联医师成功");
		}
		if(doct!=null){
			if(userService.deleteUserByUserID(doct.getUserID())){
				logger.info("删除医师用户成功");
			}
		}
		List<Employee> employeeList=employeeService.getEmployeeByRegionID(Integer.parseInt(regionID));
		if(employeeList!=null){
			for(Employee employee:employeeList){
				if(employee.getDoctorSet()!=null){
					for(Doctor doctor:employee.getDoctorSet()){
						if(doctorService.deleteDoctorByID(doctor.getUserID())){
							logger.info("删除代表对应医师成功");
						}
						if(userService.deleteUserByUserID(doctor.getUserID())){
							logger.info("删除Usesr表用户成功");
						}
					}
				}
				if(employee.getEmployeeSet()!=null){
					for(Employee em:employee.getEmployeeSet()){
						if(em.getDoctorSet()!=null){
							for(Doctor doc:em.getDoctorSet()){
								if(doctorService.deleteDoctorByID(doc.getUserID())){
									logger.info("删除下级代表对应医师成功");
								}
							}
						}
						if(employeeService.deleteEmployeeById(em.getUserID())){
							logger.info("删除下级代表成功");
						}
						
						if(userService.deleteUserByUserID(em.getUserID())){
							logger.info("删除Usesr表用户成功");
						}
					}
				}
				if(employeeService.deleteEmployeeById(employee.getUserID())){
					logger.info("删除区域关联代表成功");
				}
				if(userService.deleteUserByUserID(employee.getUserID())){
					logger.info("删除Usesr表用户成功");
				}
			}
		}
		if(regionService.deleteRegionByID(Integer.parseInt(regionID))){
			logger.info("删除区域成功");
			this.result=SUCCESS;
		}
		return SUCCESS;
	}
	public String list() {
		regions = regionService.getRegions();
		this.result = SUCCESS;
		return SUCCESS;
	}

	/**
	 * @param regionService
	 *            the regionService to set
	 */
	@Resource
	public void setRegionService(RegionService regionService) {
		this.regionService = regionService;
	}

	/**
	 * @param region
	 *            the region to set
	 */
	public void setRegion(Region region) {
		this.region = region;
	}

	/**
	 * @return the region
	 */
	public Region getRegion() {
		return region;
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
	 * @param regions
	 *            the regions to set
	 */
	public void setRegions(List<Region> regions) {
		this.regions = regions;
	}

	/**
	 * @return the regions
	 */
	public List<Region> getRegions() {
		return regions;
	}

	/**
	 * @return the parentStr
	 */
	public String getParentStr() {
		return parentStr;
	}

	/**
	 * @param parentStr
	 *            the parentStr to set
	 */
	public void setParentStr(String parentStr) {
		this.parentStr = parentStr;
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
	 * @param employeeService the employeeService to set
	 */
	@Resource
	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	/**
	 * @param doctorService the doctorService to set
	 */
	@Resource
	public void setDoctorService(DoctorService doctorService) {
		this.doctorService = doctorService;
	}
	@Resource
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
