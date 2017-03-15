<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>注册</title>
    
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
     <div align="center"> <font size=" 2" color="#FF6633">用户注册</font>
     <s:form action="registerA">   
     <s:textfield name="user.username" label="用户名"></s:textfield>   
     <s:password name="user.password" label="密码"></s:password>
     <s:password name="pwd2" label="确认密码"></s:password>
     <s:textfield name="user.email" label="电子邮箱"></s:textfield>  
      <s:submit></s:submit>    
      </s:form>
      </div>
  </body>
</html>
