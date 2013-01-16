package com.jung.loginhistory.dao;

import com.jung.loginhistory.model.LoginHistory;

public interface LoginHistoryHibernateDao {
      public boolean addLoginHistory(LoginHistory loginHistory);
}
