package com.mystore.service;
import java.util.List;

import com.demo.pojo.Book;
import com.demo.pojo.Order;
import com.mystore.dao.BookDAO;
public class BookService {
	private BookDAO bookDao;
	public void addBook(Book book)
	{
		this.bookDao.addBook(book);
	}
	public List<Book> queryBook(String keyword)
	{
		return this.bookDao.queryBook(keyword);
	}
	public List<Book> queryBookByID(Book book)
	{
		return this.bookDao.queryBookByID(book);
	}
	public void updateBook(Book book)
	{
		this.bookDao.updateBook(book);
	}
	public void removeBook(Book book)
	{
		this.bookDao.removeBook(book);
	}
	public List<Order> countByBook(Book book)
	{
		return this.bookDao.countByBook(book);
	}
	public List<Book> countByCategory(Book book)
	{
		return this.bookDao.countByCategory(book);
	}
	public BookDAO getBookDao()
	{
		return bookDao;
	}
	public void setBookDao(BookDAO bookDao)
	{
		this.bookDao=bookDao;
	}
}
