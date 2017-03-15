package com.mystore.service;

import java.util.List;

import com.demo.pojo.Book;
import com.demo.pojo.User;
import com.mystore.dao.UserDAO;
public class UserService {
	private UserDAO userDao;
	public List<User> queryUser(String keyword)
	{
		return this.userDao.queryUser(keyword);
	}
	public void addUser(User user) 
	{
		this.userDao.addUser(user);
	}
	public void updateUser(User user)
	{
		this.userDao.updateUser(user);
	}
	public void removeUser(User user)
	{
		this.userDao.removeUser(user);
	}
	public List<Book> countByUser(User user)
	{
		return this.userDao.countByUser(user);
	}
	public void addProfile(String UId,String profile)
	{
		this.userDao.addProfile(UId,profile);
	}
	public void updateProfile(String UId,String profile)
	{
		this.userDao.updateProfile(UId,profile);
	}
	public void removeProfile(String UId)
	{
		this.userDao.removeProfile(UId);
	}
	public String queryProfile(String UId)
	{
		return this.userDao.queryProfile(UId);
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
