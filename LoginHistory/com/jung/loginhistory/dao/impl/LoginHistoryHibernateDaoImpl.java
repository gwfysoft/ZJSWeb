package com.jung.loginhistory.dao.impl;

import com.jung.common.HibernateEntityManagerImpl;
import com.jung.exception.SkeletonSystemException;
import com.jung.loginhistory.dao.LoginHistoryHibernateDao;
import com.jung.loginhistory.model.LoginHistory;
import com.jung.news.model.News;

public class LoginHistoryHibernateDaoImpl extends HibernateEntityManagerImpl<LoginHistory> implements LoginHistoryHibernateDao {

	@Override
	public boolean addLoginHistory(LoginHistory loginHistory) {
		try {
			super.saveOrUpdate(loginHistory);
			return true;
		} catch (SkeletonSystemException e) {
			e.printStackTrace();
		}
		return false;
		
	}

@Override
public Class<LoginHistory> getEntityType() {
	// TODO Auto-generated method stub
	return LoginHistory.class;
}



}
