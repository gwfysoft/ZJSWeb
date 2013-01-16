package com.jung.admin.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.util.StringUtils;

import com.hp.util.ListPage;
import com.hp.util.Page;
import com.hp.util.PageContext;
import com.hp.xquery.Restrictions;
import com.hp.xquery.SimpleQuery;
import com.jung.admin.dao.AdminDao;
import com.jung.admin.dao.AdminHibernateDao;
import com.jung.admin.model.Admin;
import com.jung.admin.model.AdminVO;
import com.jung.admin.service.AdminService;
import com.jung.doctor.model.Doctor;
import com.jung.doctor.model.DoctorVO;

public class AdminServiceImpl implements AdminService {
	
	private AdminDao adminDao;
	private AdminHibernateDao adminHibernateDao;

	@Override
	public PageContext getEntityPage(PageContext pageContext,
			Map<String, String> queryConditions, String orderProperty,
			String orderMode) {
		// TODO Auto-generated method stub
//		SimpleQuery query = adminDao.getSimpleQuery();
//		if(queryConditions!=null){
//			String doctorName = queryConditions.get("name").trim();
//			if(StringUtils.hasLength(doctorName)){
//				query.addFilter(Restrictions.like("name", "%" + doctorName
//						+ "%"));
//			}
//		}
//		if (orderProperty != null && !"".equals(orderProperty)) {
//			if (orderMode.equals("asc")) {
//				query.addOrder(orderProperty, true);
//			} else if (orderMode.equals("desc")) {
//				query.addOrder(orderProperty, false);
//			}
//		}
//		Page page = adminDao.find(query, pageContext.getPageNumber(),
//				pageContext.getPageSize());
//		pageContext.setPage(page);
//		return pageContext;
		StringBuffer hql = new StringBuffer();
		hql.append("from Admin as admin ");
		if(queryConditions!=null){
			String name = queryConditions.get("name");
			hql.append(" where ");
			hql.append(" admin.name like '%" + name + "%'");
		}
		List<Admin> admins=adminHibernateDao.getAdmins(hql.toString());
		List<AdminVO> vos=new ArrayList<AdminVO>();
		if(admins!=null){
			for(Admin admin:admins){
				AdminVO adminVO=new AdminVO();
				adminVO.setAdminID(admin.getUserID());
				adminVO.setName(admin.getName());
				vos.add(adminVO);
			}
		}
		Page page = new ListPage(vos, pageContext.getPageNumber(), pageContext.getPageSize());
		pageContext.setPage(page);
		return pageContext;
		
	}

	@Override
	public boolean support(String entityName) {
		// TODO Auto-generated method stub
		if (entityName != null && entityName.trim().length() != 0) {
			if (entityName.equals(Admin.REF)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @param adminDao the adminDao to set
	 */
	@Resource
	public void setAdminDao(AdminDao adminDao) {
		this.adminDao = adminDao;
	}

	/**
	 * @param adminHibernateDao the adminHibernateDao to set
	 */
	@Resource
	public void setAdminHibernateDao(AdminHibernateDao adminHibernateDao) {
		this.adminHibernateDao = adminHibernateDao;
	}

	public boolean addAdmin(Admin admin) {
		// TODO Auto-generated method stub
		return adminHibernateDao.addAdmin(admin);
	}

	public boolean deleteAdminByID(int adminID) {
		// TODO Auto-generated method stub
		return adminHibernateDao.deleteAdminByID(adminID);
	}

	public boolean updateAdmin(Admin admin) {
		// TODO Auto-generated method stub
		return adminHibernateDao.updateAdmin(admin);
	}

	@Override
	public Admin getAdminByID(int adminID) {
		// TODO Auto-generated method stub
		return adminHibernateDao.getAdminByID(adminID);
	}

	@Override
	public List<Admin> getAdmins(String hql) {
		// TODO Auto-generated method stub
		return null;
	}

}
