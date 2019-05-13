<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport"content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
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
				<h1>Registered account</h1>
				<form class="form" id="entry_form">
				<input type="text" placeholder="AccountNumber" id="entry_userName"name="userName">
				<input type="password"  placeholder="Password" id="entry_passWord" name="passWord">
				<input type="password" placeholder="Once again" >
				<input type="text" placeholder="Name" id="entry_name" name="name">
				<input type="text" placeholder="department" id="entry_department"name="department">
                <input type="text" placeholder="grade"id="entry_grade" name="grade"> 
                <input type="text"placeholder="profession" id="entry_profession" name="profession">
					<a href="logstudent.jsp"><button type="button" id="entry_btn">Enrol</button></a>
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
           
              //ajax去服务器端校验
               var userName =$.trim($("#entry_userName").val());
            	var passWord =$.trim($("#entry_passWord").val());
            	var name =$.trim($("#entry_name").val());
            	var grade =$.trim($("#entry_grade").val());
            	var department =$.trim($("#entry_department").val());
            	var profession =$.trim($("#entry_profession").val());
            	 var data= {action:"adduser",name:name,userName:userName,passWord:passWord,name:name,grade:grade,profession:profession,department:department};
        	       	  $.ajax({
        	                 type:"POST",
        	                 url:"${ctx}/LoginController",
        	                 data:data,
        	                 dataType : "json",
        	                 success:function(){
        	                	 alert('Submitted successfully');
        	                	location.href='${ctx}/log/logstudent.jsp';
        	                	
        	                 }
        	       	  }); 
      });   
</script>
</body>
</html>