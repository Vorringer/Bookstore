package com.mystore.dao;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.demo.pojo.Book;
import com.demo.pojo.Orderinfo;

public class OrderinfoDAO extends HibernateDaoSupport {
	public void addOrderinfo(String orderId,String bookSet)
	{
		
		JSONArray json=JSONArray.fromObject(bookSet);
		 Book[] bookArr = new Book[json.size()];  
		for (int i=0;i<json.size();i++)
		{	
			JSONObject jsonObject=json.getJSONObject(i);
			 bookArr[i] = (Book)JSONObject.toBean(jsonObject, Book.class); 
			this.getHibernateTemplate().save
			(new Orderinfo(orderId,bookArr[i].getBId(),bookArr[i].getAmount()));
		}
	}
}
