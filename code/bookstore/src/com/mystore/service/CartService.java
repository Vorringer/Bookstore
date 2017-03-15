package com.mystore.service;

import java.util.List;

import com.demo.pojo.*;
import com.mystore.dao.CartDAO;

public class CartService {
	private CartDAO cartDao;
	public CartDAO getCartDao()
	{
		return cartDao;
	}
	public void setCartDao(CartDAO cartDao)
	{
		this.cartDao=cartDao;
	}
	public void addToCart(Cart cart)
	{
		this.cartDao.addCart(cart);
	}
	public List<Book> queryCart(Cart cart)
	{
		return this.cartDao.queryCart(cart);
	}
	public List<Book> queryCartBySelects(int UId, String selects)
	{
		return this.cartDao.queryCartBySelects(UId,selects);
	}
}
