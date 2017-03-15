package com.mystore.action;

import java.util.Map;

import com.demo.pojo.User;
import com.mystore.service.LoginService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ActionContext;
public class LoginAction extends ActionSupport {
	private static final long serialVersionUID = 1L;  
	private User user;   
	private LoginService loginService;
	public User getUser() {   return user;  }   
	public void setUser(User user) {   this.user = user;  }    
	public LoginService getLoginService() {   return loginService;  }   
	public void setLoginService(LoginService loginService) 
	{   this.loginService = loginService;  }  
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String execute() throws Exception
	{   
		User user2=new User();
		if ((user2=this.loginService.login(user))!=null)
		{
			ActionContext actionContext = ActionContext.getContext();
	        Map session = actionContext.getSession();
	        session.put("user",user2);
			return "SUCCESS";
		}
		else return "FAILED";
	} 
}
