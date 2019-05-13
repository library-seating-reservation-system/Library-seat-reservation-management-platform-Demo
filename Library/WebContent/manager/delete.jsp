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
    <script src="../lib/jquery/jquery-1.9.0.min.js" type="text/javascript"></script>
    <script src="../lib/json2.js" type="text/javascript"></script>
    <script src="../lib/ligerUI/js/core/base.js" type="text/javascript"></script>
    <script src="../lib/ligerUI/js/plugins/ligerDialog.js" type="text/javascript"></script>
    <script src="../lib/ligerUI/js/plugins/ligerTextBox.js" type="text/javascript"></script>
    <script src="../lib/ligerUI/js/plugins/ligerCheckBox.js" type="text/javascript"></script>
    <script src="../lib/ligerUI/js/plugins/ligerComboBox.js" type="text/javascript"></script>
    <script src="../lib/ligerUI/js/plugins/ligerGrid.js" type="text/javascript"></script>
    <script src="../lib/ligerUI/js/plugins/ligerDateEditor.js" type="text/javascript"></script>
    <script src="../lib/ligerUI/js/plugins/ligerSpinner.js" type="text/javascript"></script>
    <style>
    	
    </style>
   
    <script type="text/javascript">
    var CustomersData =null;
        $(f_initGrid);
        var manager, g;
        function f_initGrid()
        {
        	 $(function () {
             	 var data= {action:"first"};
     	       	  $.ajax({
     	                 type:"POST",
     	                 url:"${ctx}/UserReviewController",
     	                 data:data,
     	                 dataType : "json",
     	                 success:function(msg){
     	                	CustomersData=msg;
        	
            g = manager = $("#maingrid").ligerGrid({
                columns: [
                { display: 'Name', name: 'name', width: 120 },
                { display: 'AccountNumber', name: 'eduNumber', width: 160 }, 
                { display: 'Password', name: 'password', width: 120 }, 
                { display: 'Department', name: 'Department', width:220},
                { display: 'Grade', name: 'grade', width: 120},
                { display: 'Profession', name: 'profession' },
                { display: 'RegistrationTime', name: 'RegistrationTime', width:120}, 
                { display: 'Operating', isSort: false, width: 160, render: function (rowdata, rowindex, value)
                {
                	
                	
                    var h = "";
                        h += "<a href='#' onclick='deleteuser(&quot;"+rowdata.eduNumber+"&quot;)'>Delete users</a> "; 
                  
                    return h;
                }
                }
                ],
                onSelectRow: function (rowdata, rowindex)
                {
                    $("#txtrowindex").val(rowindex);
                },
                enabledEdit: true,clickToEdit:false, isScroll: false,
                data: $.extend(true,{},CustomersData),
                width: '100%'
            });  
      	                }
       	         })
          	 
        	 
        	 
        	 
        	 
        	 });
         }
        function beginEdit(rowid) { 
            manager.beginEdit(rowid);
        }
        function cancelEdit(rowid) { 
            manager.cancelEdit(rowid);
        }
        function endEdit(rowid)
        {
        	if (confirm('confirm submission?'))
            {
               manager.endEdit(rowid);
               manager.deleteRow(rowid,rowdata);
            }
        }

        
        
        
        function deleteuser(rowid){
            if (confirm('Confirm delete user?'))
            {
            	
            	 var data= {action:"remove",id:rowid};
    	       	  $.ajax({
    	                 type:"POST",
    	                 url:"${ctx}/UserReviewController",
    	                 data:data,
    	                 dataType : "json",
    	                 success:function(){
    	                	
    	                	 history.go(0);
    	                 }
    	                 
    	       	  
    	       	  })
              
            }
        }
        
        function deleteRow(rowid,rowdata)
        {
            if (confirm('Confirm delete user?'))
            {
            	alert(rowdata.eduNumber);
            	alert(rowid);
            	 var data= {action:"remove",id:rowid};
    	       	  $.ajax({
    	                 type:"POST",
    	                 url:"${ctx}/UserReviewController",
    	                 data:data,
    	                 dataType : "json",
    	                 success:function(msg){
    	                	
    	                	 history.go(0);
    	                 }
    	                 
    	       	  
    	       	  })
              
            }
        }
        var newrowid = 100;
        function addNewRow()
        {
            manager.addEditRow();
        } 
         
        function getSelected()
        { 
            var row = manager.getSelectedRow();
            if (!row) { alert('Please select the line'); return; }
            alert(JSON.stringify(row));
        }
        function getData()
        { 
            var data = manager.getData();
            alert(JSON.stringify(data));
        } 
		function picture(){
			var win=document.getElementById('win');
		 	win.style.display="block";
		}	
    </script>
</head>
<body  style="padding:10px">  

    <div id="maingrid" style="margin-top:10px"></div>
  <div style="display:none;">
  <!-- g data total ttt -->
</div>

</body>
</html>
