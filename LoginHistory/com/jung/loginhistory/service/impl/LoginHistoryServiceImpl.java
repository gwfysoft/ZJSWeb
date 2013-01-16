package com.jung.loginhistory.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import com.hp.util.PageContext;
import com.jung.loginhistory.dao.LoginHistoryDao;
import com.jung.loginhistory.dao.LoginHistoryHibernateDao;
import com.jung.loginhistory.model.LoginHistory;
import com.jung.loginhistory.service.LoginHistoryService;

public class LoginHistoryServiceImpl implements LoginHistoryService {

	
	private LoginHistoryHibernateDao loginHistoryHibernateDao;
	private LoginHistoryDao loginHistoryDao;
	@Override
	public PageContext getEntityPage(PageContext pageContext,
			Map<String, String> queryConditions, String orderProperty,
			String orderMode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean support(String entityName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addLoginHistory(LoginHistory loginHistory) {
		// TODO Auto-generated method stub
		return loginHistoryHibernateDao.addLoginHistory(loginHistory);
	}
@Resource
	public void setLoginHistoryHibernateDao(LoginHistoryHibernateDao loginHistoryHibernateDao) {
		this.loginHistoryHibernateDao = loginHistoryHibernateDao;
	}
@Resource
public void setLoginHistoryDao(LoginHistoryDao loginHistoryDao) {
	this.loginHistoryDao = loginHistoryDao;
}

}
