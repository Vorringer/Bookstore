package com.mystore.service;

import com.mystore.dao.OrderinfoDAO;

public class OrderinfoService {
	private OrderinfoDAO orderinfoDao;
	public OrderinfoDAO getOrderinfoDao(){return this.orderinfoDao;}
	public void setOrderinfoDao(OrderinfoDAO orderinfoDao){this.orderinfoDao=orderinfoDao;}
	public void addOrderinfo(String orderId,String bookSet)
	{
		this.orderinfoDao.addOrderinfo(orderId,bookSet);
	}
}
