<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta http-equiv="content-type" content="text/html;charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>log in</title>
<link rel="stylesheet" type="text/css" href="style.css">
<script src="../lib/jquery/jquery-1.9.0.min.js" type="text/javascript"></script>
<script type="text/javascript" src="vector.js"></script>
</head>
<body>

<div id="container">
	<div id="output">
		<div class="containerT">
			<h1>Library seat reservation management platform</h1>
			<form class="form" id="entry_form" >
				<input type="text" placeholder="username" id="entry_name" >
				<input type="password" placeholder="password" id="entry_password">
				<input  type="hidden" id="level" value="1">
				<button type="button" id="entry_btn">log in</button>
				<div id="prompt" class="prompt"></div>
			</form>
		</div>
	</div>
</div>
<script type="text/javascript">
    $(function(){
        Victor("container", "output");   //登录背景函数
        $("#entry_name").focus();
        $(document).keydown(function(event){
            if(event.keyCode==13){
                $("#entry_btn").click();
            }
        });
    });
         function f_alert2(type)     //登陆验证
         {
             switch (type)
             {
                 case "success":
                     URL='../index.htm';
                     break;
                 case "error":
                     $.ligerDialog.error('Incorrect username or password!');
                     break;
             }
         }
         $("#entry_btn").click(function(){
             var username = $.trim($("#entry_name").val());
             var password = $.trim($("#entry_password").val());
             var level = $.trim($("#level").val());
              if(username == ""){
                  alert("Enter one user name");
                  return false;
              }else if(password == ""){
                  alert("Please input a password");
                  return false;
              }
              //ajax去服务器端校验
              var data= {userName:username,passWord:password,level:level,action:"login"};
              
              $.ajax({
                  type:"POST",
                  url:"${ctx}/LoginController",
                  data:data,
                  success:function(msg){
                     // alert(msg);
                      if(msg=="true"){
                         window.location.href = "${ctx}/indexmanager.jsp";   
                      }else{
                    	  alert('Incorrect username or password!');
                      }
                  }
              });
      });         
</script>
</body>
</html>