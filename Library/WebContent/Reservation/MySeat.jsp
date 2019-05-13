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
    <script src="../lib/ligerUI/js/core/base.js" type="text/javascript"></script>
    <script src="../lib/ligerUI/js/plugins/ligerGrid.js" type="text/javascript"></script> 
    <script type="text/javascript">
   
    var CustomersData =null;
        var grid = null;
		$(f_initGrid);
		var manager, g;
		function f_initGrid()
		{
        $(function () {
        	 var data= {action:"first"};
	       	  $.ajax({
	                 type:"POST",
	                 url:"${ctx}/MySeatController",
	                 data:data,
	                 dataType : "json",
	                 success:function(msg){
	               	  CustomersData=msg;
	               	  
	               	  
	               	  
	               	  g = manager = grid = $("#maingrid4").ligerGrid({
	                      columns: [
	                      { display: "Floor", name: "ProductName",  },
	                      { display: "SeatNumber", name: "SeatNumber",  },
	                      { display: "Date", name: "Date",  }, 
	      				{ display: 'Operating', isSort: false, width: 160, render: function (rowdata, rowindex, value)
	      				{
	      					
	      				    var h = "";
	      				        h += "<a href='javascript:deleteRow(&quot;"+rowdata.SeatNumber+"&quot;)'>Release seat</a> "; 
	      				    return h;
	      				}
	      				}
	                      ],
	      				onSelectRow: function (rowdata, rowindex)
	      				{
	      				    $("#txtrowindex").val(rowindex);
	      				},  
	      				pageSize:10,
	      				enabledEdit: true,clickToEdit:false, isScroll: false,
	                      data: $.extend(true,{},CustomersData), 
	                      width: '100%',height:'100%'
	                  });


	                  $("#pageloading").hide();
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
		       manager.deleteRow(rowid);
		    }
		}
		
		function deleteRow(rowid)
		{
		    if (confirm('Are you sure to reserve the seat?'))
		    {
		    	 alert(rowid)
				    var data= {action:"Reserve",id:rowid};
		   	       	  $.ajax({
		   	                 type:"POST",
		   	                 url:"${ctx}/MySeatController",
		   	                 data:data,
		   	                 dataType : "json",
		   	                 success:function(){
		   	                	
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
		
    </script>
</head>
<body style="padding:6px; overflow:hidden;">
    <div id="maingrid4" style="margin:0; padding:0"></div>
  <div style="display:none;">
  <!-- g data total ttt -->
</div>
</body>
</html>