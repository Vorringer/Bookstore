package com.mystore.service;
import com.mystore.dao.UserDAO;
import com.demo.pojo.User;
public class RegisterService {
	private UserDAO userDao;
	public int register(User user)
	{
		if (this.userDao.checkDup(user)==1) return -1;
		this.userDao.addUser(user);
		return 1;
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
