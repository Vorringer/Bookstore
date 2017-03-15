package com.mystore.action;
import com.opensymphony.xwork2.ActionSupport;
import com.demo.pojo.User;
import com.mystore.service.RegisterService;
public class RegisterAction extends ActionSupport{
	private static final long serialVersionUID = 1L;  
	private User user;   
	private RegisterService registerService;
	private String pwd2;
	public User getUser() {   return user;  }   
	public void setUser(User user) {   this.user = user;  }    
	public String getPwd2(){return pwd2;}
	public void setPwd2(String pwd2){this.pwd2=pwd2;}
	public RegisterService getRegisterService() {   return registerService;  }   
	public void setRegisterService(RegisterService registerService) 
	{   this.registerService = registerService;  }   
	public String execute() throws Exception
	{   
		//System.out.print(pwd2);
		if (!pwd2.equals(this.user.getPassword()))
			return "FAILED";
		this.user.setUsergroup("user");
		if (this.registerService.register(this.user)==1) return "SUCCESS";  
		else return "FAILED";
	} 
}
