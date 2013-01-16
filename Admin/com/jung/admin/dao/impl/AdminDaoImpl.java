package com.jung.admin.dao.impl;

import java.util.List;

import com.hp.util.Page;
import com.hp.util.dao.hibernate.HibernateBaseDao;
import com.hp.xquery.SimpleQuery;
import com.jung.admin.dao.AdminDao;
import com.jung.admin.model.Admin;

public class AdminDaoImpl extends HibernateBaseDao implements AdminDao {

	@Override
	public Class getEntityType() {
		// TODO Auto-generated method stub
		return Admin.class;
	}


}
