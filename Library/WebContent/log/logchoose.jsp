<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
			<form class="form" id="entry_form">
				<a href="logmanager.jsp"><button type="button" id="entry_btn">Administrator login</button></a>
				<a href="logstudent.jsp"><button type="button" id="entry_btn">Student login</button></a>
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
                     $.ligerDialog.error('Incorrect username or password！');
                     break;
           
             }
         }

    
</script>
</body>
</html>