$(function() {
    $('#queryAbstract').click(function() {
        $.ajax({
            url: "bookA!queryBook.action",
            type: 'post',
            dataType:'json',
            data: $("#queryBookForm").serializeArray(),
            cache:false,
            success: function(msg) {
            	$("div.queryResultLi").empty(); 
            	var html=" <h2>书籍查询结果： </h2>\
		<table id='users' class='ui-widget ui-widget-content' bgcolor='#FFFFFF' bordercolor='#808080'>\
		       <tr><td>ID</td><td>书籍名称</td><td>书籍封面</td>\
			<td>价格</td><td></td></tr>";
            	
            	for(var i=0;i<msg.length;i++)
            	{
            		var BId=msg[i].BId+"";
	            	var name=  msg[i].name;
	            	var price = msg[i].price+"";
	            	
	            	html+="<tr><td>"+BId+"</td><td>"+name+
	            	"</td><td>"+"<div style='float: left;'><img class='bookImg' id='"+BId+"'  src='uploadFile/"+BId+".jpg' onerror=\"this.src='uploadFile/default.jpg'\" /></div>"+
	            	"<div id='"+BId+"' class='bookInfo' ></div></td><td>"+price+"</td>"+
	            	"<td class='addToCartTd' id='"+BId+"'><button  class='addToCart' onclick='addToCartButton(this.id)' id="+u_id+"^"+JSON.stringify(msg[i])+"  >添加到购物车</button></td>"+
	                "</tr>";
            	}
            	html+="</table>";
            	$("div.queryResultLi").append(html);
            	
            },
            error:function(e) {
            	alert("hehe");
            }
        });
    });
});

function addToCartButton(th)
{
	var sta=new Array();
	sta=th.split("^");
	var u_id=sta[0];
	var jsonstr=sta[1];
	var jsonobj=JSON.parse(jsonstr);
	var b_id=jsonobj.BId;
	
	var html="<div style='width:100px'><br><form class='amountForm' method='post' action='cartA!addToCart.action?b_id="+b_id+"&u_id="+u_id+"'>\
       购买数量： <input name='cart.amount' type='number' id='amount'/>\
        <input type='submit' name='Submit'   value='确认' />\
        </form></div>";
	$("td#"+b_id+".addToCartTd").append(html);
	
}

$(document).on('mouseover','.bookImg',function(){
			  var BId=$(this).attr("id");
			  var jsonStr="{\"book.BId\":"+BId+"}";
			  var jsonobj=JSON.parse(jsonStr);
			  $.ajax({ 	
		            url: "bookA!queryBookByID.action",
		            type: 'post',
		            dataType:'json',
		            data: jsonobj,
		            cache:false,
		            success: function(msg) {
		            	var html="";
		            	var BId=msg[0].BId+"";
		            	for(var i=0;i<msg.length;i++)
		            	{
			            	var author=msg[i].author;
			            	var category=msg[i].category;
			            	var amount=msg[i].amount+"";
			            	
			            	html+=" 作者： "+author+"<br> 类别： "+category+"<br> 库存： "+amount;
		            	}
		            	$("div#"+BId+".bookInfo").html(html);
		            	
		            },
		            error:function(e) {
		            	alert("hehe");
		            }
			  });
}
		  
);

function queryBookByAdmin()
{
        $.ajax({ 	
            url: "bookA!queryBook.action",
            type: 'post',
            dataType:'json',
            data: $("#queryBookFormByAdmin").serializeArray(),
            cache:false,
            success: function(msg) {
            	$("div.queryResultLi").empty(); 
            	var html=" <h2>书籍查询结果： </h2>\
		<table id='users' class='ui-widget ui-widget-content' bgcolor='#FFFFFF' bordercolor='#808080'>\
		       <tr><td>ID</td><td>书籍名称</td><td>作者</td>\
			<td>分类</td><td>价格</td><td>数量</td><td></td><td></td></tr>";
            	
            	for(var i=0;i<msg.length;i++)
            	{
            		var BId=msg[i].BId+"";
	            	var name=  msg[i].name;
	            	var author=msg[i].author;
	            	var category=msg[i].category;
	            	var price = msg[i].price+"";
	            	var amount=msg[i].amount+"";
	            	
	            	html+="<tr><td>"+BId+"</td><td>"+name+"</td><td>"+author+
	            	"</td><td>"+category+"</td><td>"+price+"</td><td>"+amount+
	            	"</td><td><button  class='update' onclick='updateBookButton(this.id)' id="+JSON.stringify(msg[i])+"  >修改</button></td>"+
                    "<td><button class='remove "+BId+"' onclick='removeBookButton(this.id)' id="+JSON.stringify(msg[i])+"  >下架</button></td>"+
	            	"</tr>";
            	}
            	html+="</table>";
            	$("div.queryResultLi").append(html);
            },
            error:function(e) {
            	alert("hehe");
            }
});
}
function addBook()
{
        $.ajax({ 	
            url: "bookA!addBook.action",
            type: 'post',
            dataType:'text',
            data: $("#addBookForm").serializeArray(),
            cache:false,
            success: function(msg) {
            	alert(msg);
            },
            error:function(e) {
            	alert("hehe");
            }
        });
}
function updateBook()
{
        $.ajax({ 	
            url: "bookA!updateBook.action",
            type: 'post',
            dataType:'text',
            data: $("#updateBookForm").serializeArray(),
            cache:false,
            success: function(msg) {
            	alert(msg);
            },
            error:function(e) {
            	alert("hehe");
            }
        });
}
$(function() {
    $( "#menu" ).menu();
  });
$(function() {
    $( "button.hehe" )
      .button();
  });
$(function(){
    $("button#querybook").click(function(){
    	$("form").remove();
    	$("p.info").remove();
    	$("#dialog").empty();
    	$("div#users-contain").empty();
        var html ="<form id='queryBookFormByAdmin'>\
      	   书籍关键字: <input name='keyword' type='text' id='keyword'/>\
    	  <input type='button' id='submitByAdmin' onclick=queryBookByAdmin() value='查询'/>\
    	  </form>";
        $("#dialog").append(html); 
   
    });
});
$(function(){
    $("button#addbook").click(function(){
    	$("form").remove();
    	$("p.info").remove();
    	$("#dialog").empty();
    	$("div#users-contain").empty();
    	var html =" <form id='addBookForm'>\
            <table width='335' border='0' align='center'>\
            <tr>\
              <td width='120'>书籍名称：</td>\
              <td width='250'><label>\
                <input name='book.name' type='text' id='bookname' />\
              </label></td>\
            </tr>\
             <tr>\
              <td width='120'>书籍作者：</td>\
              <td width='250'><label>\
                 <input name='book.author' type='text' id='author' />\
               </label></td>\
             </tr>\
             <tr>\
            	<td width='120'>书籍分类：</td>\
            	<td width='250'><label>\
            	<input name='book.category' type='text' id='category' />\
             </label></td>\
            </tr>\
        	<tr>\
            <td width='120'>书籍数量：</td>\
            <td width='250'><label>\
              <input name='book.amount' type='number' id='bookamount' />\
            </label></td>\
            </tr>\
        	<tr>\
            <td width='120'>书籍价格：</td>\
            <td width='250'><label>\
              <input name='book.price' type='number' id='bookprice' />\
            </label></td>\
           </tr>\
            <tr>\
              <td><label>\
                <input type='button' onclick=addBook()  value='添加' />\
              </label></td>\
            </tr>\
        </table>\
        <p>&nbsp;</p>\
        </form>";
        $("#dialog").append(html); 
    });
});
function updateBookButton(th)
{
    	$("form").remove();
    	$("p.info").remove();
    	$("#dialog").empty();
    	$("div#users-contain").empty();
    	var json=JSON.parse(th);
    	var b_id=json.BId;
    	var name=json.name;
    	var author=json.author;
    	var category=json.category;
    	var amount=json.amount;
    	var price=json.price;
        var html =" <form id='updateBookForm'>\
        <table width='335' border='0' align='center'>\
        <tr>\
          <td width='120'>ID：</td>\
          <td width='250'><label>\
            <input name='book.BId' type='text' id='b_id' readonly=true value="+b_id+" />\
          </label></td>\
        </tr>\
        <tr>\
          <td width='120'>书籍名称：</td>\
          <td width='250'><label>\
            <input name='book.name' type='text' id='bookname' value="+name+" />\
          </label></td>\
        </tr>\
         <tr>\
          <td width='120'>书籍作者：</td>\
          <td width='250'><label>\
             <input name='book.author' type='text' id='author' value="+author+" />\
           </label></td>\
         </tr>\
         <tr>\
        	<td width='120'>书籍分类：</td>\
        	<td width='250'><label>\
        	<input name='book.category' type='text' id='category' value="+category+" />\
         </label></td>\
        </tr>\
    	<tr>\
        <td width='120'>书籍数量：</td>\
        <td width='250'><label>\
          <input name='book.amount' type='number' id='bookamount' value="+amount+" />\
        </label></td>\
        </tr>\
    	<tr>\
        <td width='120'>书籍价格：</td>\
        <td width='250'><label>\
          <input name='book.price' type='number' id='bookprice' value="+price+" />\
        </label></td>\
       </tr>\
        <tr>\
          <td><label>\
            <input type='button' onclick=updateBook() value='修改' />\
          </label></td>\
        </tr>\
	    </table>\
	    <p>&nbsp;</p>\
	    </form>";
        html+="<form action='bookA!upload.action?ID="+b_id+"' method='post' enctype='multipart/form-data'>\
            <input type='file' name='file'>\
            <input type='submit' name='btnUpload' value='上传图片'>\
            </form>";
        $("#dialog").append(html); 
}
function removeBookButton(th)
{
	    var jsonobj1=JSON.parse(th);
	    var BId=jsonobj1.BId;
	    var jsonstr=th.replace("name\":","book.name\":")
	    .replace("BId\":","book.BId\":")
	    .replace("author\":","book.author\":")
	    .replace("category\":","book.category\":")
	    .replace("price\":","book.price\":")
	    .replace("amount\":","book.amount\":");
	    var jsonobj2=JSON.parse(jsonstr);
    	if (confirm("是否下架该图书？"))
    	{
    		$.ajax({ 	
                url: "bookA!removeBook.action",
                type: 'post',
                dataType:'text',
                data: jsonobj2,
                cache:false,
                success: function(msg) {
                	$("."+BId).parent().parent().remove();
                	alert(msg);
                },
                error:function(e) {
                	alert("hehe");
                }
            });
    	}
}
$(function(){
    $("button.queryUser").click(function(){
    	$("form").remove();
    	$("#dialog").empty();
    	$("div#users-contain").empty();
    	var html =" <form  id='queryUserForm'>\
        <table width='375' border='0' align='center'>\
        <tr>\
          <td width='120'>用户名关键词：</td>\
          <td width='250'><label>\
            <input name='keyword' type='text' id='keyword'/>\
          </label></td>\
        </tr>\
        <tr>\
          <td><label>\
            <input type='button' onclick=queryUser() value='查询' />\
          </label></td>\
        </tr>\
    </table>\
    <p>&nbsp;</p>\
    </form>";
   $("#dialog").append(html); 
    });
});
function queryUser()
{
        $.ajax({ 	
            url: "userA!queryUser.action",
            type: 'post',
            dataType:'json',
            data: $("#queryUserForm").serializeArray(),
            cache:false,
            success: function(msg) {
            	$("div.queryResultLi").empty(); 
            	var html= "<h2>用户查询结果： </h2>\
			    <table id='users' class='ui-widget ui-widget-content'>\
	            <tr>\
		            <td>ID</td>\
		            <td>用户名</td>\
		            <td>用户组</td>\
		   		    <td>电子邮箱</td>\
		   		    <td></td>\
		   		    <td></td>\
	    	    </tr>";
            	
     
            	for(var i=0;i<msg.length;i++)
            	{
            		var UId=msg[i].UId+"";
	            	var username=msg[i].username;
	            	var usergroup=msg[i].usergroup;
	            	var email=msg[i].email;
	            	
	            	html+="<tr><td>"+UId+"</td><td>"+username+"</td><td>"+usergroup+
	            	"</td><td>"+email+"</td><td><button  class='update' onclick='updateUserButton(this.id)' id="+JSON.stringify(msg[i])+"  >修改</button></td>"+
                    "<td><button class='remove "+UId+"' onclick='removeUserButton(this.id)' id="+JSON.stringify(msg[i])+"  >删除</button></td>"+
	            	"</tr>";
	     
            	}
            	
            	html+="</table>";
            	$("div.queryResultLi").append(html);
            	
            },
            error:function(e) {
            	alert("hehe");
            }
            
});
}
$(function(){
    $("button.addUser").click(function(){
    	$("form").remove();
    	$("p.info").remove();
    	$("#dialog").empty();
    	$("div#users-contain").empty();
    	var html =" <form id='addUserForm'>\
            <table width='335' border='0' align='center'>\
            <tr>\
              <td width='120'>用户名：</td>\
              <td width='250'><label>\
                <input name='user.username' type='text' id='username' />\
              </label></td>\
            </tr>\
             <tr>\
              <td width='120'>密码：</td>\
              <td width='250'><label>\
                 <input name='user.password' type='password' id='password' />\
               </label></td>\
             </tr>\
             <tr>\
            	<td width='120'>用户组：</td>\
            	<td width='250'><label>\
            	<input name='user.usergroup' type='text' id='usergroup' />\
             </label></td>\
            </tr>\
        	<tr>\
            <td width='120'>电子邮箱：</td>\
            <td width='250'><label>\
              <input name='user.email' type='text' id='email' />\
            </label></td>\
            </tr>\
            <tr>\
              <td><label>\
                <input type='button' onclick=addUser()  value='添加' />\
              </label></td>\
            </tr>\
        </table>\
        <p>&nbsp;</p>\
        </form>";
        $("#dialog").append(html); 
    });
});
function addUser()
{
        $.ajax({ 	
            url: "userA!addUser.action",
            type: 'post',
            dataType:'text',
            data: $("#addUserForm").serializeArray(),
            cache:false,
            success: function(msg) {
            	alert(msg);
            },
            error:function(e) {
            	alert("hehe");
            }
        });
}
function updateUserButton(th)
{
    	$("form").remove();
    	$("p.info").remove();
    	$("#dialog").empty();
    	$("div#users-contain").empty();
    	var json=JSON.parse(th);
    	var u_id=json.UId;
    	var username=json.username;
    	var usergroup=json.usergroup;
    	var email=json.email;
        var html =" <form id='updateUserForm'>\
        <table width='335' border='0' align='center'>\
        <tr>\
          <td width='120'>ID：</td>\
          <td width='250'><label>\
            <input name='user.UId' type='text' id='u_id' readonly=true value="+u_id+" />\
          </label></td>\
        </tr>\
        <tr>\
          <td width='120'>用户名：</td>\
          <td width='250'><label>\
            <input name='user.username' type='text' id='username' readonly=true value="+username+" />\
          </label></td>\
        </tr>\
         <tr>\
          <td width='120'>用户组：</td>\
          <td width='250'><label>\
             <input name='user.usergroup' type='text' id='usergroup' value="+usergroup+" />\
           </label></td>\
         </tr>\
    	<tr>\
        <td width='120'>电子邮箱：</td>\
        <td width='250'><label>\
          <input name='user.email' type='text' id='email' value="+email+" />\
        </label></td>\
        </tr>\
        <tr>\
          <td><label>\
            <input type='button' onclick=updateUser() value='修改' />\
          </label></td>\
        </tr>\
    </table>\
    <p>&nbsp;</p>\
    </form>";
        $("#dialog").append(html); 
}
function updateUser()
{
	 $.ajax({ 	
         url: "userA!updateUser.action",
         type: 'post',
         dataType:'text',
         data: $("#updateUserForm").serializeArray(),
         cache:false,
         success: function(msg) {
         	alert(msg);
         },
         error:function(e) {
         	alert("hehe");
         }
     });
}
function removeUserButton(th)
{
	    var jsonobj1=JSON.parse(th);
	    var UId=jsonobj1.UId;
	    var jsonstr=th.replace("username\":","user.username\":")
	    .replace("UId\":","user.UId\":")
	    .replace("usergroup\":","user.usergroup\":")
	    .replace("email\":","user.email\":");
	    var jsonobj2=JSON.parse(jsonstr);
    	if (confirm("是否永久删除该用户？"))
    	{
    		$.ajax({ 	
                url: "userA!removeUser.action",
                type: 'post',
                dataType:'text',
                data: jsonobj2,
                cache:false,
                success: function(msg) {
                	$("."+UId).parent().parent().remove();
                	alert(msg);
                },
                error:function(e) {
                	alert("hehe");
                }
            });
    	}
}
$(function() {
    $("button.printCart").click(function() {
    	var u_id=$(this).attr("id");
    	var jsonStr="{\"cart.UId\":"+u_id+"}";
    	var jsonobj=JSON.parse(jsonStr);
        $.ajax({
            url: "cartA!queryCart.action",
            type: 'post',
            dataType:'json',
            data: jsonobj,
            cache:false,
            success: function(msg) {
            	$("div.queryResultLi").empty(); 
            	var html=" <h2>你的购物车： </h2>\
            			<form method='post' action='cartA!goToPay.action'>\
		<table id='users' class='ui-widget ui-widget-content' bgcolor='#FFFFFF' bordercolor='#808080'>\
		       <tr><td>书籍名称</td><td>作者</td>\
    			<td>分类</td><td>价格</td><td>数量</td><td>是否购买？</td></tr>";
            	
            	for(var i=0;i<msg.length;i++)
            	{
            		var BId=msg[i].BId;
	            	var name=  msg[i].name;
	            	var author=msg[i].author;
	            	var category=msg[i].category;
	            	var price = msg[i].price+"";
	            	var amount=msg[i].amount+"";
	            	
	            	html+="<tr><td>"+name+
	            	"</td><td>"+author+"</td><td>"+category+"</td><td>"+price+"</td><td>"+amount+
	            	"</td><td><input name='selects' type='checkbox' value="+BId+" /></td></tr>";
            	}
            	html+="<tr><td colspan='6' style='text-align:center;'><input type='submit' value='购买' /></td></tr>";
            	html+="</table></form>";
            	$("div.queryResultLi").append(html);
            	
            },
            error:function(e) {
            	alert("hehe");
            }
        });
    });
});
$(function() {
    $("button.uploadImg").click(function() {
    	$(".queryResultLi").empty();
    	var UId=$(this).attr("id");
    	var html="<form action='userA!upload.action?ID="+UId+"' method='post' enctype='multipart/form-data'>\
        <input type='file' name='file'>\
        <input type='submit' name='btnUpload' value='上传头像'>\
        </form>";
    	$(".queryResultLi").append(html);

    });
});


$(function() {
    $("button.countByUser").click(function() {
    	$("form").remove();
    	$("#dialog").empty();
    	$("div#users-contain").empty();
    	var html =" <form  id='countByUserForm'>\
        <table width='375' border='0' align='center'>\
        <tr>\
          <td width='120'>用户名：</td>\
          <td width='250'><label>\
            <input name='user.username' type='text' id='keyword'/>\
          </label></td>\
        </tr>\
        <tr>\
          <td><label>\
            <input type='button' onclick=countByUser() value='统计' />\
          </label></td>\
        </tr>\
    </table>\
    <p>&nbsp;</p>\
    </form>";
   $("#dialog").append(html); 

    });
});
$(function() {
    $("button.countByBook").click(function() {
    	$("form").remove();
    	$("#dialog").empty();
    	$("div#users-contain").empty();
    	var html =" <form  id='countByBookForm'>\
        <table width='375' border='0' align='center'>\
        <tr>\
          <td width='120'>书籍名称：</td>\
          <td width='250'><label>\
            <input name='book.name' type='text' id='keyword'/>\
          </label></td>\
        </tr>\
        <tr>\
          <td><label>\
            <input type='button' onclick=countByBook() value='统计' />\
          </label></td>\
        </tr>\
    </table>\
    <p>&nbsp;</p>\
    </form>";
   $("#dialog").append(html); 

    });
});
$(function() {
    $("button.countByCategory").click(function() {
    	$("form").remove();
    	$("#dialog").empty();
    	$("div#users-contain").empty();
    	var html =" <form  id='countByCategoryForm'>\
        <table width='375' border='0' align='center'>\
        <tr>\
          <td width='120'>书籍类别：</td>\
          <td width='250'><label>\
            <input name='book.category' type='text' id='keyword'/>\
          </label></td>\
        </tr>\
        <tr>\
          <td><label>\
            <input type='button' onclick=countByCategory() value='统计' />\
          </label></td>\
        </tr>\
    </table>\
    <p>&nbsp;</p>\
    </form>";
   $("#dialog").append(html); 

    });
});
$(function() {
    $("button.countByTime").click(function() {
    	$("form").remove();
    	$("#dialog").empty();
    	$("div#users-contain").empty();
    	var html =
    		" <form  id='countByTimeForm'>\
				从<input type='text' id='from' name='from'>\
				到<input type='text' id='to' name='to'>\
				<br><br>按年份<input type='radio' name='mode' value='0'> \
    		     按月份<input type='radio' name='mode' value='1'> \
    		      按天<input type='radio' name='mode' value='2'>	\
    		    <br><br><input type='button' onclick=countByTime() value='统计' />\
    <p>&nbsp;</p>\
    </form>";
   $("#dialog").append(html); 
   $("body").delegate("#from", "focusin", function(){
	   $(this).datepicker({
		      defaultDate: "+1w",
		      changeMonth: true,
		      numberOfMonths: 1,
		      onClose: function( selectedDate ) {
		        $( "#to" ).datepicker( "option", "minDate", selectedDate );
		      }
		    });
	   });
   $("body").delegate("#to", "focusin", function(){
	   $(this).datepicker({
		      defaultDate: "+1w",
		      changeMonth: true,
		      numberOfMonths: 1,
		      onClose: function( selectedDate ) {
		        $( "#from" ).datepicker( "option", "maxDate", selectedDate );
		      }
		    });

    });
});
});
function printStatistics(top,xaxis,yaxis)
{
	$("svg").remove();
	//画布大小
    var width = 1300;
    var height = 500;

    //在 body 里添加一个 SVG 画布
    var svg = d3.select("body")
        .append("svg")
        .attr("width", width)
        .attr("height", height);

    //画布周边的空白
    var padding = { left: 450, right: 450, top: 50, bottom: 50 };

    //定义一个数组
    //x轴的比例尺
    var xScale = d3.scale.ordinal()
        .domain(xaxis)
        .rangeRoundBands([0, width - padding.left - padding.right]);

    //y轴的比例尺
    var yScale = d3.scale.linear()
        .domain([0, Math.max.apply(null, yaxis)])
        .range([height - padding.top - padding.bottom, 0]);

    //定义x轴
    var xAxis = d3.svg.axis()
        .scale(xScale)
        .orient("bottom");

    //定义y轴
    var yAxis = d3.svg.axis()
        .scale(yScale)
        .orient("left");

    //矩形之间的空白
    var rectPadding = 10;

    //添加矩形元素
    var rects = svg.selectAll(".MyRect")
        .data(yaxis)
        .enter()
        .append("rect")
        .attr("class", "MyRect")
        .attr("transform", "translate(" + padding.left + "," + padding.top + ")")
        .attr("x", function (d, i) {
            return xScale(xaxis[i]) + rectPadding / 2;
        })
        .attr("width", xScale.rangeBand() - rectPadding)
        .attr("y", function (d) {
            var min = yScale.domain()[0];
            return yScale(min);
        })
        .attr("height", function (d) {
            return 0;
        })
        .transition()
        .delay(function (d, i) {
            return i * 200;
        })
        .duration(2000)
        .ease("bounce")
        .attr("y", function (d,i) {
            return yScale(d);
        })
        .attr("height", function (d,i) {
            return height - padding.top - padding.bottom - yScale(d);
        });
    //添加文字元素
    var texts = svg.selectAll(".MyText")
        .data(yaxis)
        .enter()
        .append("text")
        .attr("class", "MyText")
        .attr("transform", "translate(" + padding.left + "," + padding.top + ")")
        .attr("x", function (d, i) {
            return xScale(xaxis[i]) + rectPadding / 2;
        })
        .attr("dx", function () {
            return (xScale.rangeBand() - rectPadding) / 2;
        })
        .attr("dy", function (d) {
            return 20;
        })
        .text(function (d) {
            return d;
        })
        .attr("y", function (d) {
            var min = yScale.domain()[0];
            return yScale(min);
        })
        .transition()
        .delay(function (d, i) {
            return i * 200;
        })
        .duration(2000)
        .ease("bounce")
        .attr("y", function (d,i) {
            return yScale(d);
        });
    //添加x轴
    svg.append("g")
        .attr("class", "axis")
        .attr("transform", "translate(" + padding.left + "," + (height - padding.bottom) + ")")
        .call(xAxis);

    //添加y轴
    svg.append("g")
        .attr("class", "axis")
        .attr("transform", "translate(" + padding.left + "," + padding.top + ")")
        .call(yAxis);
    var topTxt = svg.append("text")
            .attr("x", 560)
            .attr("y", 50)
            .attr("font-size", 20)
            .attr("font-family", "Microsoft YaHei UI")
            .text(top);
    var leftTxt = svg.append("text")
            .attr("x", 400)
            .attr("y", 400)
            .attr("font-size", 20)
            .attr("transform","rotate(-90,400,400)")
            .attr("font-family", "Microsoft YaHei UI")
            .text("");
}

function countByUser()
{
	$.ajax({ 	
        url: "userA!countByUser.action",
        type: 'post',
        dataType:'json',
        data: $("#countByUserForm").serializeArray(),
        cache:false,
        success: function(msg) {
        	var xaxis=new Array();
        	var yaxis=new Array();
        	for (var i=0;i<msg.length;i++)
        	{
        		xaxis[i]=msg[i].category;
        		yaxis[i]=msg[i].price;
        	}
        	printStatistics("购买总金额",xaxis,yaxis);
        },
        error:function(e) {
        	alert("hehe");
        }
        
	});
}
function countByBook()
{
	$.ajax({ 	
        url: "bookA!countByBook.action",
        type: 'post',
        dataType:'json',
        data: $("#countByBookForm").serializeArray(),
        cache:false,
        success: function(msg) {
        	var xaxis=new Array();
        	var yaxis=new Array();
        	for (var i=0;i<msg.length;i++)
        	{
        		xaxis[i]=msg[i].address;
        		yaxis[i]=msg[i].UId;
        	}
        	printStatistics("销售总量",xaxis,yaxis);
        },
        error:function(e) {
        	alert("hehe");
        }
        
	});
}
function countByCategory()
{
	$.ajax({ 	
        url: "bookA!countByCategory.action",
        type: 'post',
        dataType:'json',
        data: $("#countByCategoryForm").serializeArray(),
        cache:false,
        success: function(msg) {
        	var xaxis=new Array();
        	var yaxis=new Array();
        	for (var i=0;i<msg.length;i++)
        	{
        		xaxis[i]=msg[i].category;
        		yaxis[i]=Math.round(msg[i].price);
        	}
        	printStatistics("销售总量",xaxis,yaxis);
        },
        error:function(e) {
        	alert("hehe");
        }
        
	});
}
function countByTime()
{
	$.ajax({ 	
        url: "orderA!countByTime.action",
        type: 'post',
        dataType:'json',
        data: $("#countByTimeForm").serializeArray(),
        cache:false,
        success: function(msg) {
        	var xaxis=new Array();
        	var yaxis=new Array();
        	for (var i=0;i<msg.length;i++)
        	{
        		xaxis[i]=msg[i].address;
        		yaxis[i]=msg[i].UId;
        	}
        	printStatistics("销售总金额",xaxis,yaxis);
        },
        error:function(e) {
        	alert("hehe");
        }
        
	});
}
$(function(){
    $("button.addProfile").click(function(){
    	$("form").remove();
    	$("p.info").remove();
    	$(".queryResultLi").empty();
    	$("div#users-contain").empty();
    	var UId=$(this).attr("id");
        var html ="<form id='addProfileForm'>\
      	   简介: <input name='profile' type='text' id='keyword'/>\
    	  <input type='button' onclick=addProfile(this.id) id='"+UId+"' value='添加'/>\
    	  </form>";
        $(".queryResultLi").append(html); 
   
    });
});
function addProfile(th)
{
	var UId=th;
	$.ajax({ 	
        url: "userA!addProfile.action?UId="+UId,
        type: 'post',
        dataType:'text',
        data: $("#addProfileForm").serializeArray(),
        cache:false,
        success: function(msg) {
        	alert("添加成功");
        },
        error:function(e) {
        	alert("hehe");
        }
        
	});
}
$(function(){
    $("button.updateProfile").click(function(){
    	$("form").remove();
    	$("p.info").remove();
    	$(".queryResultLi").empty();
    	$("div#users-contain").empty();
    	var UId=$(this).attr("id");
        var html ="<form id='updateProfileForm'>\
      	   简介: <input name='profile' type='text' id='keyword'/>\
    	  <input type='button' onclick=updateProfile(this.id) id='"+UId+"' value='修改'/>\
    	  </form>";
        $(".queryResultLi").append(html); 
   
    });
});
function updateProfile(th)
{
	var UId=th;
	$.ajax({ 	
        url: "userA!updateProfile.action?UId="+UId,
        type: 'post',
        dataType:'text',
        data: $("#updateProfileForm").serializeArray(),
        cache:false,
        success: function(msg) {
        	alert("修改成功");
        },
        error:function(e) {
        	alert("hehe");
        }
        
	});
}
$(function(){
    $("button.removeProfile").click(function(){
    	if (confirm("是否删除简介？"))
    	{
	    	var UId=$(this).attr("id");
	    	$.ajax({ 	
	            url: "userA!removeProfile.action?UId="+UId,
	            type: 'post',
	            dataType:'text',
	            data: "",
	            cache:false,
	            success: function(msg) {
	            	alert("删除成功");
	            },
	            error:function(e) {
	            	alert("hehe");
	            }
	            
	    	});
    	}
    });
});

