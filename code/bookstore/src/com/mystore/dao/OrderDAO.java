package com.mystore.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;


import com.demo.pojo.Order;

public class OrderDAO extends HibernateDaoSupport {
	public void addOrder(Order order)
	{
		this.getHibernateTemplate().save(order);
	}
	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<Order> countByTime(int mode,Timestamp fromTime,Timestamp toTime)
	{
		String[] fill={"%Y","%Y-%m","%Y-%m-%d"};

		String hql="select o.time,sum(b.price*oi.amount) "
				+"from Order as o, Orderinfo as oi, Book as b "
				+"where o.orderId=oi.orderId and oi.BId=b.BId "
				+"and o.time between '"+fromTime+"' and '"+toTime+"' "
				+"group by date_format(o.time,'"+fill[mode]+"')";
		List<Object[]>result=this.getHibernateTemplate().find(hql);
		List<Order> re=new ArrayList<Order>();
		for (Object[] object: result)
		{
			String year=((Timestamp)object[0]).getYear()+1900+"";
			String month=((Timestamp)object[0]).getMonth()+1+"";
			String date=((Timestamp)object[0]).getDate()+"";
			String[] timeS={year,year+"."+month,year+"."+month+"."+date};
			re.add(new Order((Double)object[1],timeS[mode]));
		}
		return re;
	}
}
