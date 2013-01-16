package com.jung.admin.dao;

import java.util.List;

import com.jung.admin.model.Admin;

public interface AdminHibernateDao {

	public boolean addAdmin(Admin admin);
	public boolean deleteAdminByID(int adminID);
	public boolean updateAdmin(Admin admin);
	public Admin getAdminByID(int adminID);
	public List<Admin> getAdmins(String hql);

}
