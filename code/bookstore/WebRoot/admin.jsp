<%@ page language="java" import="java.util.*,com.demo.pojo.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>书店后台管理</title>
    
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
        .axis path,
        .axis line {
            fill: none;
            stroke: black;
            shape-rendering: crispEdges;
        }

        .axis text {
            font-family: sans-serif;
            font-size: 11px;
        }

        .MyRect {
            fill: steelblue;
        }

        .MyText {
            fill: white;
            text-anchor: middle;
            font-family:'Microsoft YaHei UI';
        }
    .center{margin: 0 auto;text-align:center;}
	div#users-contain { width: 600px;margin:0 auto;}
	div#users-contain table { margin: 1em 0; border-collapse: collapse; width: 100%;}
	div#users-contain table td, div#users-contain table th { border: 1px solid #eee; padding: .6em 10px; text-align: left; }
    .ui-menu { width: 109.5px; }
</style>
<script>
$(function() {
    $( "#from" ).datepicker({
      defaultDate: "+1w",
      changeMonth: true,
      numberOfMonths: 3,
      onClose: function( selectedDate ) {
        $( "#to" ).datepicker( "option", "minDate", selectedDate );
      }
    });
    $( "#to" ).datepicker({
      defaultDate: "+1w",
      changeMonth: true,
      numberOfMonths: 3,
      onClose: function( selectedDate ) {
        $( "#from" ).datepicker( "option", "maxDate", selectedDate );
      }
    });
});
</script>
  </head>
  
  <body>
    <%
    response.setContentType("text/html;charset=UTF-8");
    User user=new User();
	if ((user=(User)session.getAttribute("user"))!=null)
	{
		if (!user.getUsergroup().equals("admin")){
%>
	    	<h1>您所在的用户组权限不足,2秒后跳转首页</h1>
<%
			response.setHeader("refresh", "2;url=index.jsp");
		}
		else
		{
%>
		<h1>你好，管理员</h1>
		    <ul id="menu">
		    <li>
		        <a href="javascript:void(0)">图书管理</a>
		        <ul>
				    <li><button id='querybook' class="hehe">查询图书</button></li>
				    <li><button id='addbook' class="hehe">添加图书</button></li>
		    	</ul>
		    </li>
		    <li>
		        <a href="javascript:void(0)">用户管理</a>
		        <ul>
			    	<li><button class="hehe queryUser">查询用户</button></li>
			    	<li><button class="hehe addUser">添加用户</button></li>
		    	</ul>
		    </li>
		    <li>
		    	<a href="javascript:void(0)">统计</a>
		    	<ul>
			    	<li><button class="hehe countByUser">根据用户</button></li>
			    	<li><button class="hehe countByBook">根据书籍</button></li>
			    	<li><button class="hehe countByCategory">根据类别</button></li>			  
			    	<li><button class="hehe countByTime">根据日期</button></li>
		    	</ul>
		    </li>
		    </ul>
		    <div id='dialog' class='center'></div><br>
		    <div id='users-contain' class='ui-widget queryResultLi' style='text-align:center'></div>
<%
	     }
	 }
	 else
	 {
	 %>
    	<h1>您还没有登录,2秒后跳转登录页面</h1>
<%
    	response.setHeader("refresh", "2;url=login.jsp");
    }
%>

  </body>
    <script type="text/javascript" src="http://d3js.org/d3.v3.min.js" charset="utf-8"></script>
  <script type="text/javascript" src="bookstore.js?ver=16"></script>
</html>
