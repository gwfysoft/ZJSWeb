package com.jung.admin.action;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.jung.admin.model.Admin;
import com.jung.admin.service.AdminService;
import com.jung.common.Constants;
import com.jung.common.JqueryGridAction;
import com.jung.user.service.UserService;

public class AdminAction extends JqueryGridAction {

	private static final long serialVersionUID = 1L;
	private static final Log logger = LogFactory.getLog(AdminAction.class);
	
	private Admin admin;
	private String adminID;
	private String result;
	
	private AdminService adminService;
	private UserService userService;
	//新增、修改都在这一个方法里,ajax
	public String add(){
		if(adminID!=null&&!adminID.equals("")){
			if(admin==null){
				admin=adminService.getAdminByID(Integer.parseInt(adminID));
				this.result = SUCCESS;
				return SUCCESS;
			}else{
				Admin ad=adminService.getAdminByID(Integer.parseInt(adminID));
				ad.setName(admin.getName());
				ad.setPassWord(admin.getPassWord());
				if(adminService.updateAdmin(ad)){
					logger.info("更新管理员成功");
					this.result=SUCCESS;
					return SUCCESS;
				}
			}
		}else{
			admin.setUserType(Constants.User.ADMIN);
			adminService.addAdmin(admin);
			logger.info("添加管理员成功");
			this.result=SUCCESS;
			return SUCCESS;
		}
		return FAIL;
	}
	@Deprecated
	public String prepareUpdate(){
		admin=adminService.getAdminByID(Integer.parseInt(adminID));
		return SUCCESS;
	}
	@Deprecated
	public String update(){
		Admin ad=adminService.getAdminByID(Integer.parseInt(adminID));
		ad.setName(admin.getName());
		ad.setPassWord(admin.getPassWord());
		if(adminService.updateAdmin(ad)){
			logger.info("更新管理员成功");
			return SUCCESS;
		}
		return null;
	}
	public String delete(){
		if(adminService.deleteAdminByID(Integer.parseInt(adminID))){
			logger.info("删除管理员成功");
		}
		if(userService.deleteUserByUserID(Integer.parseInt(adminID))){
			logger.info("删除管理员用户成功");
			this.result=SUCCESS;
			return SUCCESS;
		}
		return null;
	}
	/**
	 * @param adminService the adminService to set
	 */
	@Resource
	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}
	/**
	 * @param adminID the adminID to set
	 */
	public void setAdminID(String adminID) {
		this.adminID = adminID;
	}
	/**
	 * @return the adminID
	 */
	public String getAdminID() {
		return adminID;
	}
	/**
	 * @param result the result to set
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
	 * @return the admin
	 */
	public Admin getAdmin() {
		return admin;
	}
	/**
	 * @param admin the admin to set
	 */
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	/**
	 * @param userService the userService to set
	 */
	@Resource
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
