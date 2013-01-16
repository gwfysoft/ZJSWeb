package com.jung.user.service;

import com.jung.common.JqueryGridService;
import com.jung.user.model.User;


public interface UserService extends JqueryGridService{
	public boolean validateUserByUserNameAndPassword(User user);
	public User getUserByUserName(String userName);
	public boolean deleteUserByUserID(int userID);
}
