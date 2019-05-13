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
        	 var data= {action:"errseat"};
	       	  $.ajax({
	                 type:"POST",
	                 url:"${ctx}/SeatManagerController",
	                 data:data,
	                 dataType : "json",
	                 success:function(msg){
	               	  CustomersData=msg;
	         
        	
            grid = $("#maingrid4").ligerGrid({
                columns: [
                { display: "Floor", name: "ProductName",  },
                { display: "SeatNumber", name: "SeatNumber",  },
                { display: "Date", name: "Date",  }, 
                { display: "Reporter", name: "Reporter", },
                { display: 'Operating', isSort: false, width: 160, render: function (rowdata, rowindex, value)
    				{
    				    var h = "";
    				        h += "<a href='javascript:deleteRow(&quot;"+rowdata.SeatNumber+"&quot;)'>Release seat</a> "; 
    				    return h;
    				}
    				}
                ],  pageSize:20,where : f_getWhere(),
                data: $.extend(true,{},CustomersData), 
                width: '100%',height:'100%'
            });


            $("#pageloading").hide();
	                 }
		       	 })
	        });
			}
		function deleteRow(rowid)
		{
		    if (confirm('Are you sure to reserve the seat?'))
		    {
		    var data= {action:"remove",id:rowid};
   	       	  $.ajax({
   	                 type:"POST",
   	                 url:"${ctx}/SeatManagerController",
   	                 data:data,
   	                 dataType : "json",
   	                 success:function(msg){
   	                	if(msg==1){
   	                	 history.go(0);	
   	                	}}
   	                
   	       	  })
		    }
		}
		
        function f_search()
        {
            grid.options.data = $.extend(true, {}, CustomersData);
            grid.loadData(f_getWhere());
        }
        function f_getWhere()
        {
            if (!grid) return null;
            var clause = function (rowdata, rowindex)
            {
                var key = $("#txtKey").val();
                return rowdata.ProductName.indexOf(key) > -1;
            };
            return clause; 
        }
		function picture(){
			var win=document.getElementById('win');
		 	win.style.display="block";
		}
    </script>
</head>
<body style="padding:6px; overflow:hidden;">
<div id="searchbar">
    Search:<input id="txtKey" type="text" />
    <input id="btnOK" type="button" value="Confirm" onclick="f_search()" />
</div>
    <div id="maingrid4" style="margin:0; padding:0"></div>
  <div style="display:none;">
  <!-- g data total ttt -->
</div>
</body>
</html>