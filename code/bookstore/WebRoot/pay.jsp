<%@ page language="java" import="java.util.*,com.demo.pojo.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>支付</title>
    
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
</style>
  </head>
  
  <body>
  <h1>订单支付</h1>
<% 
	response.setContentType("text/html;charset=UTF-8");
    User user=new User();
    String selectstr=request.getParameter("selects").toString();
%>
    <input id="arr" type="hidden"  value="<%=selectstr%>">
<%
    int isLogin=-1;
	if ((user=(User)session.getAttribute("user"))==null)
	{
		response.getWriter().print("您还没有登录，请登录后再试");
		response.setHeader("refresh", "2;url=login.jsp");
	}
	else
	{
%>
	<div id='users-contain' class='ui-widget queryResultLi' style='text-align:center'></div>
<%
	}
 %>
 <script>
    $(document).ready(function()
    { 
         var selectss = document.getElementById("arr").value;
         var UId=<%=user.getUId()%>;
         var jsonStr="{\"selects\":\""+selectss+"\",\"UId\":"+UId+"}";
         var jsonObj=JSON.parse(jsonStr);
         //alert(jsonObj.UId);
         $.ajax({ 	
                url: "cartA!queryCartBySelects.action",
                type: 'post',
                dataType:'json',
                data: jsonObj,
                cache:false,
                success: function(msg) {
                    $("div.queryResultLi").empty(); 
                    var html="<h2>订单包含以下商品： </h2>\
							  <table id='users' class='ui-widget ui-widget-content' style='text-align:center'>\
		      			   	  <tr><td>书籍名称</td><td>单价(元)</td><td>数量</td><td>总价(元)</td></tr>";
		      	    var stotal=0;
		      	    var total=0;
		      	    var jsonStr=JSON.stringify(msg);
		      	    for(var i=0;i<msg.length;i++)
            		{
		            	var name=  msg[i].name;
		            	var price = msg[i].price+"";
		            	var amount=msg[i].amount+"";
		            	stotal=msg[i].price*msg[i].amount;
		            	total+=stotal;
		            	html+="<tr><td>"+name+
		            	"</td><td>"+price+"</td><td>"+amount+
		            	"</td><td>"+stotal+"</td></tr>";
            		}
            		html+="<tr><td colspan=5 align='right' style='padding-right:10px;'>总金额： "+total+"元</td></tr>";
            		html+="<tr>\
					            <td colspan=5 style='text-align:center'>\
					            <form id='addOrderForm'>\
					                     收货地址: <input name='address' type='text' id='address'/>\
					                     <input type='button' onclick=addOrder(this.id) id="+UId+"^"+jsonStr+" value='确认支付' />\
					            </form>\
					            </td>\
					       </tr></table>";
				    $("div.queryResultLi").append(html);
                },
                error:function(e) {
                	alert("hehe");
                }
            });
           
	}); 
	function addOrder(th)
	{
		var sta=new Array();
		sta=th.split("^");
		var UId=sta[0];
		var jsonStr=sta[1];
		jsonStr=jsonStr.replace(/\"/g,"\\\"");
		var formStr=JSON.stringify($("#addOrderForm").serializeArray());
		formStr=formStr.substring(formStr.indexOf("value")+8,formStr.length-3);
		var json="{\"order.UId\":"+UId+",\"order.address\":\""+formStr+"\",\"info\":\""+jsonStr+"\"}";
		var jsonObj=JSON.parse(json);
		$.ajax({ 	
                url: "orderA!addOrder.action",
                type: 'post',
                dataType:'text',
                data: jsonObj,
                cache:false,
                success: function(msg) {
                    alert("下单成功!");
                    location.href="usercenter.jsp";
                },
                error:function(e) {
                	alert("hehe");
                }
            });
            
	}
    </script>
  </body>
  
</html>
