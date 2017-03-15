package com.mystore.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.demo.pojo.*;

public class CartDAO extends HibernateDaoSupport {
	public void addCart(Cart cart)
	{
		this.getHibernateTemplate().save(cart);
	}
	@SuppressWarnings("unchecked")
	public List<Book> queryCart(Cart cart)
	{
		//Session session = this.getHibernateTemplate().getSessionFactory().openSession();
		Integer u_id=cart.getUId();
		String hql="select new Book(b.BId,b.name,b.author,b.category,b.price,c.amount) "+
	               "from Book as b,Cart as c "+
				   "where c.UId="+u_id+" and c.BId=b.BId";
		List<Book>result=this.getHibernateTemplate().find(hql);
		return result;
	}
	@SuppressWarnings("unchecked")
	public List<Book> queryCartBySelects(int UId,String selects)
	{
		String[] selectArray=selects.split("mmm");
		int array[] = new int[selectArray.length];  
		String bookSet="";
		for(int i=0;i<selectArray.length;i++)
		{
		    array[i]=Integer.parseInt(selectArray[i]);
		    bookSet+=array[i]+",";
		}
		bookSet=bookSet.substring(0,bookSet.length()-1);
		String hql="select new Book(b.BId,b.name,b.author,b.category,b.price,c.amount) "+
	               "from Book as b,Cart as c "+
				   "where c.UId="+UId+" and c.BId in ("+bookSet+") and c.BId=b.BId";
		List<Book>result=this.getHibernateTemplate().find(hql);
		return result;
		
	}
}
