package com.mystore.service;

import com.demo.pojo.User;
import com.mystore.dao.UserDAO;

public class LoginService {
	private UserDAO userDao;
	public User login(User user)
	{
		return this.userDao.checkUser(user);
	}
	public UserDAO getUserDao()
	{
		return userDao;
	}
	public void setUserDao(UserDAO userDao)
	{
		this.userDao=userDao;
	}
}
