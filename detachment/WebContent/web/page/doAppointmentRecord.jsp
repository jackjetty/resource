<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=8" />
<title></title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/web/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/web/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/web/js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/web/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/web/js/easyui-lang-zh_CN.js"></script>
<style type="text/css">
<!--
.panel-margin-buttom {
	margin-bottom: 20px;
}

#searchpanel a.pushBtn {
    -moz-user-select: none;
    background: url("image/button/btnout_bg_left.gif") no-repeat scroll left top transparent;
    float: left;
    height: 24px;
    margin: 2px 5px 0 0;
    outline: medium none;
    text-decoration: none;
    
}
#searchpanel a.pushBtn img {
    float: left;
    margin: 2px 0 4px 6px;
    border:0;
}
#searchpanel a.pushBtn b {
    background: url("image/button/btnout_bg_right.gif") no-repeat scroll right top transparent;
    color: #666666;
    cursor: pointer;
    display: inline-block;
    font-size: 12px;
    font-weight: normal;
    height: 24px;
    line-height: 24px;
    padding: 0 7px 0 5px;
    white-space: nowrap;
}
#searchpanel a.pushBtn:hover {
    background: url("image/button/btnover_bg_left.gif") no-repeat scroll left top transparent;
    text-decoration: none;
}
#searchpanel a.pushBtn:hover b {
    background: url("image/button/btnover_bg_right.gif") no-repeat scroll right top transparent;
    color: #114477;
    font-size: 12px;
}

-->
</style>
</head>
<body style="background: #DFE8F6;">
<input type="hidden" id="editRoleId" />
<div id="searchpanel" data-options="collapsible:true,collapsed:true"
		class="easyui-panel panel-margin-buttom" title="查询条件"
		style="height:90px; padding: 10px; overflow: hidden;">
	<table style="height: 100%;">
		<tr style="valign: center; text-decoration: none; line-heigth: 30px; height: 30px; margin-left: 10px;">
			<td>预约类型：</td>
			<td>
				<select id="appointmentTypeId" style="width: 120px;"></select>
			</td>
			<td>主题：</td>
			<td>
				<select id="appointmentTheme" style="width: 120px;"></select>
			</td>
			<td>预约时间：</td>
			<td>
				<input type="text" style="width: 120px" class="easyui-datebox" id="appointmentTime"/>	
			</td>
			<td>
				<a id="search" class="pushBtn" href="javascript:void(0);" hidefocus="true">
					<img width="20" height="20" src="${pageContext.request.contextPath}/web/image/icon/search.gif">
					<b>查询</b>
				</a>
			</td>
			<td>
				<a id="clear" class="pushBtn" href="javascript:void(0);" hidefocus="true">
					<b>条件清空</b>
				</a>
			</td>
		</tr>
	</table>
</div>
<table id="dg"></table>
<script type="text/javascript">
	$(function() {
		initPage();
		
		var width = $(document).width();
		var height = $(document).height();
		
		$("#searchpanel").panel({width:width-20});
		
		$("#dg").datagrid({
			width : width - 20,
			height : height - 45,
			columns : [[
				{field : "appointmentTypeName", title : "预约类型", width : 100, align : "center"},
				{field : "appointmentTheme", title : "主题", width : 100, align : "center"},
				{field : "appointmentTime", title : "预约时间", width : 100, align : "center"},
				{field : "identityCard", title : "身份证", width : 100, align : "center"},
				{field : "userName", title : "姓名", width : 100, align : "center"},
				{field : "fileNum", title : "档案号", width : 100, align : "center"},
				{field : "phoneNumber", title : "联系电话", width : 100, align : "center"},
				{field : "nickName", title : "微信昵称", width : 100, align : "center"}
			]],
			pageList : [20, 50, 100, 200],
			frozenColumns : {},
			fitColumns : true,
			resizeHandle : "right",
			autoRowHeight : false,
			toolbar : null,
			striped : false,
			method : "post",
			nowrap : true,
			idField : null,
			url : "getAppointmentRecord.action",
			data : null,
			loadMsg : "正在加载,请稍后...",
			pagination : true,
			rownumbers : true,
			singleSelect : false,
			checkOnSelect : true,
			selectOnCheck : true,
			pagePosition : "bottom",
			queryParams : {
				appointmentTime : "",
				appointmentTheme : "",
				appointmentTypeId : ""
			},
			sortName : null,
			sortOrder : "asc",
			multiSort : false,
			remoteSort : true,
			showHeader : true,
			showFooter : false,
			scrollbarSize : 18,
			rowStyler : function(index, row) {
				
			},
			loadFilter : function(data) {
				return data;
			},
			onClickRow:function(rowIndex, rowData) { 
                
	        },
	        onDblClickRow: function(rowIndex, rowData) { 
	        	 
	        },
	        onDblClickCell : function(rowIndex, field, value) {
	        	
	        }
	    });
		
		// 分页相关内容
		var p = $('#dg').datagrid('getPager');
		$(p).pagination({
			displayMsg : '当前显示从{from}到{to}共{total}记录',
			onBeforeRefresh : function(pageNumber, pageSize) {
				$(this).pagination('loading'); 
				$(this).pagination('loaded');
			} 
		});
		
		$("#search").click(function() {
			var appointmentTypeId = $("#appointmentTypeId").val();
			var appointmentTheme = $("#appointmentTheme").val();
			var appointmentTime = $("#appointmentTime").datebox('getValue');
			
			var query = {
				appointmentTypeId : appointmentTypeId, 
				appointmentTime : appointmentTime,
				appointmentTheme : appointmentTheme
			}; //把查询条件拼接成JSONv
	        $("#dg").datagrid('options').queryParams = query; //把查询条件赋值给datagrid内部变量
	        $("#dg").datagrid('load');
	        $('#dg').datagrid('clearSelections');  
		});
		
		$("#clear").click(function(){
			$("#appointmentTypeId").val('');
			var content = "<option value=''>请选择预约类型</option>";
			$("#appointmentTheme").html(content);
			$("#appointmentTime").datebox('setValue', '');
		});
	});
	
	function initPage() {
		$.ajax({
			url : "getTbAppointmentTypeIdAndName.action",
			async : false,
			data : "",
			dataType : "json",
			type : "post",
			success : function(data) {
				var ids=data.ids.split("=");
				var names=data.names.split("=");
				var content="<option value=''>请选择</option>";
				for(var i=0;i<ids.length-1;i++){
					content+="<option value='"+ids[i]+"'>"+names[i]+"</option>";
				}
				$("#appointmentTypeId").html(content);
			},
			error : function(data) {
				alert("网络繁忙!请稍后再试!");
			}
		});
		
		$("#appointmentTypeId").change(function() {
			if ($("#appointmentTypeId").val() == "") {
				var content = "<option value=''>请选择预约类型</option>";
				$("#appointmentTheme").html(content);
			}
			$.ajax({
				url : "getTbAppointmentTheme.action",
				async : false,
				data : {
					"appointmentTypeId" : $("#appointmentTypeId").val()
				},
				dataType : "json",
				type : "post",
				success : function(data) {
					var themeList = data.themeList;
					var content = "<option value=''>不限主题</option>";
					for (var i = 0; i < themeList.length; i++) {
						content += "<option value='" + themeList[i] + "'>" + themeList[i] + "</option>";
					}
					$("#appointmentTheme").html(content);
				},
				error : function(data) {
					alert("网络繁忙!请稍后再试!");
				}
			});
		});
		
		var content = "<option value=''>请选择预约类型</option>";
		$("#appointmentTheme").html(content);
	}
</script>
</body>
</html>