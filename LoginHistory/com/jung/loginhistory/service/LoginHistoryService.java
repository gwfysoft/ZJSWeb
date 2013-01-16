package com.jung.loginhistory.service;

import com.jung.common.JqueryGridService;
import com.jung.loginhistory.model.LoginHistory;

public interface LoginHistoryService extends JqueryGridService {
	 public boolean addLoginHistory(LoginHistory loginHistory);
}
