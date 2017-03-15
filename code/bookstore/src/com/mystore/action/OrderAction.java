package com.mystore.action;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;

import com.demo.pojo.*;
import com.mystore.service.OrderService;
import com.mystore.service.OrderinfoService;
import com.opensymphony.xwork2.ActionSupport;

public class OrderAction extends ActionSupport {
	private static final long serialVersionUID = 1L;  
	private OrderService orderService;
	private OrderinfoService orderinfoService;
	private Order order;
	private Orderinfo orderinfo;
	private String info;
	public String getInfo(){return info;}
	public void setInfo(String info){this.info=info;}
	public Orderinfo getOrderinfo(){return orderinfo;}
	public void setOrderinfo(Orderinfo orderinfo){this.orderinfo=orderinfo;}
	public Order getOrder(){return order;}
	public void setOrder(Order order){this.order=order;}
	public OrderinfoService getOrderinfoService(){return orderinfoService;}
	public void setOrderinfoService(OrderinfoService orderinfoService){this.orderinfoService=orderinfoService;}
	public OrderService getOrderService(){return orderService;}
	public void setOrderService(OrderService orderService){this.orderService=orderService;}
	public String addOrder() throws IOException
	{
		HttpServletResponse response = ServletActionContext.getResponse();
		Date d=new Date();
		this.order.setTime(new Timestamp(d.getTime()));
		this.order.setOrderId(d.getTime()+"");
		this.orderService.addOrder(this.order);
		this.orderinfoService.addOrderinfo(d.getTime()+"", info);
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print("OK");
		response.getWriter().flush();
		response.getWriter().close();
		return "SUCCESS";
	}
	public void countByTime() throws IOException
	{
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		int mode=Integer.parseInt(request.getParameter("mode"));
		String fromTime=request.getParameter("from");
		String toTime=request.getParameter("to");
		String[] sta=fromTime.split("/");
		fromTime=sta[2]+"-"+sta[0]+"-"+sta[1]+" 00:00:00";
		sta=toTime.split("/");
		toTime=sta[2]+"-"+sta[0]+"-"+sta[1]+" 00:00:00";
		List<Order>result=this.orderService.countByTime(mode,Timestamp.valueOf(fromTime),Timestamp.valueOf(toTime));
		JSONArray arr = JSONArray.fromObject(result);
		String returnStr = arr.toString();
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(returnStr);
		response.getWriter().flush();
		response.getWriter().close();
	
	}
}
