package com.jung.loginhistory.dao.impl;

import java.util.List;

import com.hp.util.Page;
import com.hp.util.dao.hibernate.HibernateBaseDao;
import com.hp.xquery.SimpleQuery;
import com.jung.loginhistory.dao.LoginHistoryDao;
import com.jung.loginhistory.model.LoginHistory;

public class LoginHistoryDaoImpl extends HibernateBaseDao  implements LoginHistoryDao {

	@Override
	public Class getEntityType() {
		// TODO Auto-generated method stub
		return LoginHistory.class;
	}


}
