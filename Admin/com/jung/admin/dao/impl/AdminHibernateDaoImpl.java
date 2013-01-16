package com.jung.admin.dao.impl;

import java.util.List;

import com.jung.admin.dao.AdminHibernateDao;
import com.jung.admin.model.Admin;
import com.jung.common.HibernateEntityManagerImpl;
import com.jung.exception.SkeletonException;
import com.jung.exception.SkeletonSystemException;

public class AdminHibernateDaoImpl extends HibernateEntityManagerImpl<Admin> implements AdminHibernateDao {

	@Override
	public Class<Admin> getEntityType() {
		// TODO Auto-generated method stub
		return Admin.class;
	}

	public boolean addAdmin(Admin admin){
		try {
			super.save(admin);
			return true;
		} catch (SkeletonSystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	public boolean deleteAdminByID(int adminID){
		String sql="delete from  admin where adminID="+adminID;
		try {
			super.executeSQLUpdate(sql);
			return true;
		} catch (SkeletonException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	public boolean updateAdmin(Admin admin){
		try {
			super.update(admin);
			return true;
		} catch (SkeletonSystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	public Admin getAdminByID(int adminID){
		String hql="from Admin as admin where adminID="+adminID;
		try {
			List adminList=super.executeHql(hql);
			if(adminList!=null&&adminList.size()>0){
				return (Admin) adminList.get(0);
			}
		} catch (SkeletonException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	public List<Admin> getAdmins(String hql)
	{
		try {
			List adminList = super.executeHql(hql);
			if (adminList != null && adminList.size() > 0) {
				return adminList;
			}
		} catch (SkeletonException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
