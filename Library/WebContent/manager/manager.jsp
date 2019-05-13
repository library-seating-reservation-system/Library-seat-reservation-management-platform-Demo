<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="../lib/ligerUI/skins/Aqua/css/ligerui-all.css"
	rel="stylesheet" type="text/css" />
<script src="../lib/jquery/jquery-1.9.0.min.js" type="text/javascript"></script>
<script src="../lib/json2.js" type="text/javascript"></script>
<script src="../lib/ligerUI/js/core/base.js" type="text/javascript"></script>
<script src="../lib/ligerUI/js/plugins/ligerDialog.js"
	type="text/javascript"></script>
<script src="../lib/ligerUI/js/plugins/ligerTextBox.js"
	type="text/javascript"></script>
<script src="../lib/ligerUI/js/plugins/ligerCheckBox.js"
	type="text/javascript"></script>
<script src="../lib/ligerUI/js/plugins/ligerComboBox.js"
	type="text/javascript"></script>
<script src="../lib/ligerUI/js/plugins/ligerGrid.js"
	type="text/javascript"></script>
<script src="../lib/ligerUI/js/plugins/ligerDateEditor.js"
	type="text/javascript"></script>
<script src="../lib/ligerUI/js/plugins/ligerSpinner.js"
	type="text/javascript"></script>
<script type="text/javascript">
	var EmployeeData = null;
	$(f_initGrid);
	var manager, g;
	function f_initGrid() {
		$(function() {
			var data = {
				action : "first"
			};
			$.ajax({
				type : "POST",
				url : "${ctx}/SeatManagerController",
				data : data,
				dataType : "json",
				success : function(msg) {
					EmployeeData = msg;

					g = manager = $("#maingrid").ligerGrid({
						columns : [ {
							display : 'Floor',
							name : 'Floor',
							width : 220,
							type : 'text',
							editor : {
								type : 'text'
							}
						}, {
							display : 'SeatNumber',
							name : 'Seat',
							type : 'text',
							
						}, {
							display : 'Date',
							width : 220,
							name : 'data',
							type : 'date',
							editor : {
								type : 'date'
							}
						}, {
							display : 'Operator',
							name : 'Operator',
							width : 220,
							type : 'text',
							editor : {
								type : 'text'
							},
						} ],
						onSelectRow : function(rowdata, rowindex) {
							$("#txtrowindex").val(rowindex);
						},
						enabledEdit : true,
						clickToEdit : false,
						isScroll : false,
						data : EmployeeData,
						width : '100%'
					});
				}
			})
		});
	}

	function beginEdit() {
		var row = manager.getSelectedRow();
		if (!row) {
			alert('Please select the line');
			return;
		}

		manager.beginEdit(row);
	}
	function cancelEdit() {
		var row = manager.getSelectedRow();
		if (!row) {
			alert('Please select the line');
			return;
		}
		manager.cancelEdit(row);
	}
	function cancelAllEdit() {
		manager.cancelEdit();
	}
	function endEdit() {
		var row = manager.getSelectedRow();
		if (!row) {
			alert('Please select the line');
			return;
		}
		manager.endEdit(row);
	}
	function endAllEdit() {
		var row = manager.getSelectedRow();
		var s = manager.getSelected();
		if (!row) {
			f_alert('warn');
			return;
		}
		manager.endEdit(row);
		var params = manager.getUpdated(row);
		var cata= {action:"edit",s:JSON.stringify(s)};
		$.ajax({
			contentType : "application/x-www-form-urlencoded",
			type : "POST",
			dataType : "json",
			url : '${ctx}/SeatManagerController',
			data : cata,
			success : function(data) {
				if (data.value == 1)
					alert("提交成功");
				history.go(0); 
			}
		});

	}
	function deleteRow()

	{

		manager.deleteSelectedRow();
	}
	var newrowid = 100;
	function addNewRow() {
		manager.addEditRow();
	}

	function getSelected() {
		var row = manager.getSelectedRow();
		if (!row) {
			alert('Please select the line');
			return;
		}
		alert(JSON.stringify(row));
	}
	function getData() {
		var data = manager.getData();
		alert(JSON.stringify(data));
	}
</script>
</head>
<body style="padding: 10px">
	<a class="l-button" style="width: 80px; float: left; margin-left: 6px;"
		onclick="beginEdit()">modification</a>
	<a class="l-button" style="width: 80px; float: left; margin-left: 6px;"
		onclick="endAllEdit()">confirm</a>
	<a class="l-button" style="width: 80px; float: left; margin-left: 6px;"
		onclick="cancelAllEdit()">cancel</a>


	<div class="l-clear"></div>
	<div id="maingrid" style="margin-top: 20px"></div>
	<div style="display: none;">
		<!-- g data total ttt -->
	</div>
</body>
</html>