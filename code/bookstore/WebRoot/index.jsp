<%@ page language="java" import="java.util.*,com.demo.pojo.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <style type="text/css">
	.right{width:300px; float:right; text-align:right; line-height:60px}
	.left{width:300px; float:left; text-align:left; line-height:60px}
	.center{margin: 0 auto;text-align:center;}
	div#users-contain { width: 1000px;margin:0 auto;}
	div#users-contain table { margin: 1em 0; border-collapse: collapse; width: 100%;}
	div#users-contain table td, div#users-contain table th { border: 1px solid #eee; padding: .6em 10px; text-align: left; }
	</style>
    <title>Vorringer的网上书店</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
  <h1>欢迎来到Vorringer的网上书店</h1>
    <div class='right'>
<%
    response.setContentType("text/html;charset=UTF-8");
    User user=new User();
    int isLogin=-1;
	if ((user=(User)session.getAttribute("user"))!=null)
	{
%>
		<a href='usercenter.jsp?username=<%=user.getUsername()%> '><%=user.getUsername()%>的个人中心</a> 
<%
        isLogin=1;
		if (user.getUsergroup().equals("admin")){
%>
		<a href='admin.jsp'>后台管理</a>
<%} %>
		<a href='logout.jsp'>注销</a>
<%
	}
	else{
	%>
		<a href='login.jsp'>登录</a>
		<a href='register.jsp'>注册</a><%
	}%>
	</div>
<%
	if (isLogin==1)
    {
%>
<script> 
   var u_id = <%=user.getUId()%>; 
</script> 
		  <div class='left'>
		  <form id='queryBookForm'>
      	   书籍关键字: <input name='keyword' type='text' id='keyword'/>
    	  <input type='button' id='queryAbstract' value='查询' />
    	  </form>
    	  </div>
<%  
    }
%>
    <div id='users-contain' class='ui-widget queryResultLi' style='text-align:center'></div>
  </body>
  <script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
  <script type="text/javascript" src="bookstore.js?ver=16"></script>
</html>
