<%@ page language="java" import="java.util.*,com.demo.pojo.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>个人中心</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	  <link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
  <script src="//code.jquery.com/jquery-1.9.1.js"></script>
  <script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
  <link rel="stylesheet" href="jqueryui/style.css">
<style type="text/css">
	.right{width:300px; float:right; text-align:right; line-height:60px}
	.center{margin: 0 auto;text-align:center;}
	div#users-contain { width: 550px;margin:0 auto;}
	div#users-contain table { margin: 1em 0; border-collapse: collapse; width: 100%;}
	div#users-contain table td, div#users-contain table th { border: 1px solid #eee; padding: .6em 10px; text-align: left; }
	.ui-menu { width: 109.5px; }
</style>
  </head>
  
  <body>
<% 
response.setContentType("text/html;charset=UTF-8");
    User user=new User();
    int isLogin=-1;
	if ((user=(User)session.getAttribute("user"))==null)
	{
		response.getWriter().print("您还没有登录，请登录后再试");
		response.setHeader("refresh", "2;url=login.jsp");
	}
	else
	{
%>
	<h1>欢迎, <%=user.getUsername()%></h1>
	
	<img src='<%="upload/"+user.getUId()+".jpg"%>'/>
	<ul id="menu">
		    <li>
		        <a href="javascript:void(0)">购物车</a>
		        <ul>
				    <li><button class="hehe printCart" id=<%=user.getUId()%>  >显示您的购物车</button></li>
		    	</ul>
		    </li>
		    <li>
		        <a href="javascript:void(0)">头像</a>
		        <ul>
			    	<li><button class="hehe uploadImg" id=<%=user.getUId()%> >上传头像</button></li>
		    	</ul>
		    </li>
		    <li>
		    	<a href="javascript:void(0)">简介</a>
		    	<ul>
			    	<li><button class="hehe addProfile" id=<%=user.getUId()%> >添加简介</button></li>
			    	<li><button class="hehe updateProfile" id=<%=user.getUId()%> >更改简介</button></li>			  
			    	<li><button class="hehe removeProfile" id=<%=user.getUId()%> >删除简介</button></li>
		    	</ul>
		    </li>
		    </ul>
 
    <div id='profile'></div>
    <div id='users-contain' class='ui-widget queryResultLi' style='text-align:center'></div>
<%
    }
%>
  <script>
  $(document).ready(function()
  {
  		var UId=<%=user.getUId()%>;
    	$("form").remove();
    	$("p.info").remove();
    	$("#profile").empty();
    	$("div#users-contain").empty();
    	$.ajax({ 	
            url: "userA!queryProfile.action?UId="+UId,
            type: 'post',
            dataType:'text',
            data: "",
            cache:false,
            success: function(msg) {
            	var html="<p>个人简介： "+msg+"</p>";
            	$("#profile").append(html);
            },
            error:function(e) {
            	alert("hehe");
            }
            
    	});
  });
  </script>
  </body>
    <script type="text/javascript" src="bookstore.js?ver=18"></script>
</html>
