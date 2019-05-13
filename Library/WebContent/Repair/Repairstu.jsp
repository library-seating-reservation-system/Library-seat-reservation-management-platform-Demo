<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
    <link href="../lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
    <link href="../lib/ligerUI/skins/Gray/css/all.css" rel="stylesheet" />
    <script src="../lib/jquery/jquery-1.9.0.min.js" type="text/javascript"></script>
    <script src="../lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script> 
</head>
<body style="padding: 10px">
   <div id="form1" class="liger-form">
        <div class="fields">
            <input type="text" data-label="Floor" name="Title" />
            <input type="text" data-label="Seat number" name="addDate"/> 
            <input type="date" data-label="Date" name="no"> 
			
        </div> 
       <div class="buttons">
           <input data-text="submit" data-click="f_save" data-width="60"/>
       </div>
    </div> 

    <script>
        function f_save()
        {
        	var name = $("input[name=Title]").val();
        	var Seatnumber =$("input[name=addDate]").val();
        	var date = $("input[name=no]").val();
        	  var data= {action:"adderr",name:name,Seatnumber:Seatnumber,date:date};
   	       	  $.ajax({
   	                 type:"POST",
   	                 url:"${ctx}/SpareSeatController",
   	                 data:data,
   	                 dataType : "json",
   	                 success:function(){
   	                	 alert('Submitted successfully');
   	                	 history.go(0);
   	                 }
   	       	  })
        }
    </script>
</body>
</html>
