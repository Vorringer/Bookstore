package com.mystore.dao;

import java.util.List;

import org.bson.Document;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.demo.pojo.Book;
import com.demo.pojo.User;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
public class UserDAO extends HibernateDaoSupport {
	public void addUser(User user) 
	{   
		this.getHibernateTemplate().save(user); 
	}
	@SuppressWarnings("unchecked")
	public List<User> queryUser(String keyword)
	{
		return this.getHibernateTemplate()
				.find("from User where username like '%"+keyword+"%'");
		
	}
	public void updateUser(User user)
	{
		this.getHibernateTemplate().update(user);
		
	}
	public void removeUser(User user)
	{
		this.getHibernateTemplate().delete(user);
	}
	@SuppressWarnings("unchecked")
	public User checkUser(User user)
	{
		List<User>result=this.getHibernateTemplate()
				.find("from User where username='"+user.getUsername()+"'");
		if (result!=null &&
		 user.getPassword().equals(result.get(0).getPassword()))
		 {
			return result.get(0);
		 }
		else return null;
	}
	@SuppressWarnings("unchecked")
	public int checkDup(User user)
	{
		List<User>result=this.getHibernateTemplate()
				.find("from User where username='"+user.getUsername()+"'");
		if (result.isEmpty())
			return -1;
		else return 1;
	}
	@SuppressWarnings({ "unchecked" })
	public List<Book> countByUser(User user)
	{
		String username=user.getUsername();
		
		String hql="select new Book(b.category,sum(b.price*oi.amount)) "+
	              "from Order as o, Orderinfo as oi, Book as b,User as u "+
				 "where o.UId=u.UId and u.username='"+username+"' and o.orderId=oi.orderId and b.BId=oi.BId "+
	              "group by b.category";
	              
		
		List<Book> result=this.getHibernateTemplate().find(hql);
		return result;
	}
	public void addProfile(String UId,String profile)
	{
		MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
		MongoDatabase mongoDatabase = mongoClient.getDatabase("userProfile"); 
		MongoCollection<Document> collection = mongoDatabase.getCollection("profile");
		Document d=new Document("UId",UId).append("profile", profile);
		collection.insertOne(d);
	}
	public void updateProfile(String UId,String profile)
	{
		MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
		MongoDatabase mongoDatabase = mongoClient.getDatabase("userProfile"); 
		MongoCollection<Document> collection = mongoDatabase.getCollection("profile");
		collection.updateMany(Filters.eq("UId", UId), new Document("$set",new Document("profile",profile)));  
	}
	public void  removeProfile(String UId)
	{
		MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
		MongoDatabase mongoDatabase = mongoClient.getDatabase("userProfile"); 
		MongoCollection<Document> collection = mongoDatabase.getCollection("profile");
		collection.deleteOne(Filters.eq("UId", UId));   
	}
	@SuppressWarnings("rawtypes")
	public String queryProfile(String UId)
	{
		MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
		MongoDatabase mongoDatabase = mongoClient.getDatabase("userProfile"); 
		MongoCollection<Document> collection = mongoDatabase.getCollection("profile");
		BasicDBObject dbObject = new BasicDBObject();
		dbObject.put("UId",UId);
		FindIterable iterate = collection.find(dbObject).limit(1);
		Document d=(Document)iterate.iterator().tryNext();
		return d.getString("profile");
	}
	
}
