package com.mystore.service;

import java.sql.Timestamp;
import java.util.List;

import com.demo.pojo.Order;
import com.mystore.dao.OrderDAO;

public class OrderService {
	private OrderDAO orderDao;
	public OrderDAO getOrderDao()
	{
		return orderDao;
	}
	public void setOrderDao(OrderDAO orderDao)
	{
		this.orderDao=orderDao;
	}
	public void addOrder(Order order)
	{
		this.orderDao.addOrder(order);
	}
	public List<Order>countByTime(int mode, Timestamp fromTime,Timestamp toTime)
	{
		return this.orderDao.countByTime(mode, fromTime, toTime);
	}
}
