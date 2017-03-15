package com.mystore.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;

import com.demo.pojo.*;
import com.mystore.service.CartService;
import com.opensymphony.xwork2.ActionSupport;

public class CartAction extends ActionSupport {
	private static final long serialVersionUID = 1L;  
	private CartService cartService;
	private Cart cart;
	private String selects;
	private int UId;
	public int getUId(){return UId;}
	public void setUId(int UId){this.UId=UId;}
	public String getSelects(){return this.selects;}
	public void setSelects(String selects){this.selects=selects;}
	public Cart getCart(){return cart;}
	public void setCart(Cart cart){this.cart=cart;}
	public CartService getCartService(){return cartService;}
	public void setCartService(CartService cartService){this.cartService=cartService;}
	public String addToCart() throws IOException
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setCharacterEncoding("utf-8");
		String u_id=request.getParameter("u_id");
		String b_id=request.getParameter("b_id");
		this.cart.setBId(Integer.parseInt(b_id));
		this.cart.setUId(Integer.parseInt(u_id));
		this.cartService.addToCart(cart);
		return "SUCCESS";
	}
	public void queryCart() throws IOException
	{
		HttpServletResponse response = ServletActionContext.getResponse();
		List<Book>result=this.cartService.queryCart(cart);
		JSONArray arr = JSONArray.fromObject(result);
		String returnStr = arr.toString();
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(returnStr);
		response.getWriter().flush();
		response.getWriter().close();
	}
	public void queryCartBySelects() throws IOException
	{
		HttpServletResponse response = ServletActionContext.getResponse();
		List<Book>result=this.cartService.queryCartBySelects(this.UId,this.selects);
		JSONArray arr = JSONArray.fromObject(result);
		String returnStr = arr.toString();
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(returnStr);
		response.getWriter().flush();
		response.getWriter().close();

		
	}
	public String goToPay() throws IOException, ServletException
	{	
		this.selects=this.selects.replaceAll(",", "mmm").replaceAll(" ", "");
		return "PAY";
	}
}
