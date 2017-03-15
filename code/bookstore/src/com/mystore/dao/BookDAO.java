package com.mystore.dao;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.demo.pojo.Book;
import com.demo.pojo.Order;
public class BookDAO extends HibernateDaoSupport{
	public void addBook(Book book)
	{
		this.getHibernateTemplate().save(book);
	}
	@SuppressWarnings("unchecked")
	public List<Book> queryBook(String keyword)
	{
		return this.getHibernateTemplate()
				.find("from Book where name like '%"+keyword+"%'");
	}
	@SuppressWarnings("unchecked")
	public List<Book> queryBookByID(Book book)
	{
		return this.getHibernateTemplate()
				.find("from Book where BId="+book.getBId());
	}
	public void updateBook(Book book)
	{
		this.getHibernateTemplate().update(book);
	}
	public void removeBook(Book book)
	{
		this.getHibernateTemplate().delete(book);
	}
	@SuppressWarnings("unchecked")
	public List<Book>countByCategory(Book book)
	{
		String category=book.getCategory();
		String hql="select new Book(b.name,sum(b.price/b.price*oi.amount)) "+
		           "from Order as o, Orderinfo as oi, Book as b "+
				   "where o.orderId=oi.orderId and b.BId=oi.BId and b.category='"+category+"' "+
		           "group by b.name";
		List<Book>result=this.getHibernateTemplate().find(hql);
		return result;
	}
	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<Order>countByBook(Book book)
	{
		String name=book.getName();
		
		String hql="select o.time,sum(b.price/b.price*oi.amount)"+
		           "from Order as o, Orderinfo as oi, Book as b "+
				   "where o.orderId=oi.orderId and b.BId=oi.BId and b.name='"+name+"' "+
		           "group by date_format(o.time,'%Y-%m-%d')";
		
		List<Object[]>result=this.getHibernateTemplate().find(hql);
		List<Order> re=new ArrayList<Order>();
		for (Object[] object: result)
		{
			String year=((Timestamp)object[0]).getYear()+1900+"";
			String month=((Timestamp)object[0]).getMonth()+1+"";
			String date=((Timestamp)object[0]).getDate()+"";
			String[] timeS={year,year+"."+month,year+"."+month+"."+date};
			re.add(new Order((Double)object[1],timeS[2]));
		}
		System.out.print(result);
		return re;
	}
}
