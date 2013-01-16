package com.jung.admin.service;

import java.util.List;

import com.jung.admin.model.Admin;
import com.jung.common.JqueryGridService;

public interface AdminService extends JqueryGridService {
	public boolean addAdmin(Admin admin);
	public boolean deleteAdminByID(int adminID);
	public boolean updateAdmin(Admin admin);
	public Admin getAdminByID(int adminID);
	public List<Admin> getAdmins(String hql);
}
