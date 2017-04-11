<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加功能菜单</title>
<link rel="stylesheet" type="text/css" href="themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="themes/icon.css">
<script type="text/javascript" src="js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/easyui-lang-zh_CN.js"></script>

<style type="text/css">
<!--
.panel-margin-buttom {
	margin-bottom: 20px;
}

#searchpanel a.pushBtn {
    -moz-user-select: none;
    background: url("images/button/btnout_bg_left.gif") no-repeat scroll left top transparent;
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
    background: url("images/button/btnout_bg_right.gif") no-repeat scroll right top transparent;
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
    background: url("images/button/btnover_bg_left.gif") no-repeat scroll left top transparent;
    text-decoration: none;
}
#searchpanel a.pushBtn:hover b {
    background: url("images/button/btnover_bg_right.gif") no-repeat scroll right top transparent;
    color: #114477;
    font-size: 12px;
}
#div1 td{
	height: 35px;
}
-->
</style>
</head>
<body style="background: #DFE8F6;">
	<div id="searchpanel"class="easyui-panel panel-margin-buttom" title="app用户查询"
		style="height: 80px; padding: 10px;">	
		
			<table>
			<tr style="Valign: center; text-decoration: none; line-heigth: 30px; height: 30px; margin-left: 50px;">
				<td>手机号码：</td>
				<td><input type="text" id="phoneNumber" /></td>
				<td>归属地：</td>
				<td><select id="address" ><option id="address1" value=''></option></select></td>
				<td>开始时间</td>
				<td><input type="text" id="startTime" class="easyui-datebox" editable="false"
					style="width: 95px;" /></td>
				<td>结束时间</td>
				<td><input type="text" id="endTime" class="easyui-datebox" editable="false"
					style="width: 95px;" /></td>
				<td><input type="checkbox" id="noOrder">未交易</td>
				<td><a id="search" class="pushBtn" href="javascript:void(0);" hidefocus="true">
				<img width="20" height="20" src="images/icon/search.gif">
				<b>查询</b>
			</a></td><td><a id="clear" class="pushBtn" href="javascript:void(0);" hidefocus="true">
				<b>条件清空</b>
			</a></td>
			</tr>
		</table>
	</div>
	<table id="dg" class="easyui-datagrid" title="app用户列表"
		data-options="rownumbers:true,singleSelect:false,pagination:true,
				pageSize:20,remoteSort:false,fitColumns:true,
				multiSort:true">
		<thead>
			<tr>
				<!-- <th data-options="field:'ck',checkbox:true"></th> -->
				<th data-options="field:'phoneNumber',width:60,sortable:true">手机号码</th>
				<!--<th data-options="field:'userName',width:50">用户名称</th>
				<th data-options="field:'birthDay',width:50">生日</th>
				<th data-options="field:'sex',width:20">性别</th>
				<th data-options="field:'qqNumber',width:50">QQ</th>
				<th data-options="field:'email',width:100">邮箱</th>-->
				<th data-options="field:'allScore',width:40,sortable:true">积分</th>
				<th data-options="field:'placeName',width:50">归属地</th>
				<th data-options="field:'registerTime',width:100">注册时间</th>
				<th data-options="field:'lastTradeTime',width:100">最后交易时间</th>
				<th data-options="field:'os',width:50">注册手机系统</th>
				<th data-options="field:'client',width:100">注册手机型号</th>
				<th data-options="field:'fromWayDetail',width:100">注册渠道</th>
			</tr>
		</thead>
	</table>
	
	<script type="text/javascript">
		String.prototype.trim = function() {
		  return this.replace(/^\s\s*/, '').replace(/\s\s*$/, '');
		};
		function pagerFilter(data) {
			var dg = $("#dg");
			var opts = dg.datagrid('options');
			var pager = dg.datagrid('getPager');
			if(typeof data.originalRows== 'undefined'){
				data = {
						total : data.listSize,
						rows : data.rows
					};
					pager.pagination({
						onSelectPage : function(pageNum, pageSize) {
							opts.pageNumber = pageNum;
							opts.pageSize = pageSize;
							pager.pagination('refresh', {
								pageNumber : pageNum,
								pageSize : pageSize
							});
							dg.datagrid('loadData', data);
						}
					});
					data.originalRows = (data.rows);
					return data;
			}else{
				var phoneNumber = $("#phoneNumber").val().trim();
				var address = $("#address").val().trim();
				var startTime =  $("#startTime").datebox('getValue'); 
				var endTime =  $("#endTime").datebox('getValue'); 
				var noOrder = $("#noOrder").is(':checked');
				var newData = getData(phoneNumber,address,startTime,endTime,noOrder);
				data.total = newData.listSize;
				data.rows = newData.rows;
				pager.pagination({
					onSelectPage : function(pageNum, pageSize) {
						opts.pageNumber = pageNum;
						opts.pageSize = pageSize;
						pager.pagination('refresh', {
							pageNumber : pageNum,
							pageSize : pageSize
						});
						dg.datagrid('loadData', data);
					}
				});
				data.originalRows = (data.rows);
				return data;
			}	
		}
		function getData(phoneNumber,address,startTime,endTime,noOrder) {
			var rows = null;
			var dg = $("#dg");
			var opts = dg.datagrid('options');
			var pageSize = opts.pageSize;
			var pageIndex = opts.pageNumber;
			$.ajax({
				url : "getAppUserInfo.action",
				async : false,
				data : "pageSize=" + pageSize + "&pageIndex=" + pageIndex
						+ "&phoneNumber=" + phoneNumber + "&address=" + address+ "&startTime=" + startTime
						+ "&endTime=" + endTime + "&noOrder=" + noOrder,
				dataType : "json",
				type : "post",
				success : function(data) {
					rows = data;
				},
				error : function(data) {
					alert("error 后台出现错误！");
				}
			});
			return rows;
		}
		$(function() {
			var width = $(document).width();
			var height = $(document).height();
			var phoneNumber = $("#phoneNumber").val();
			var address = $("#address").val();
			var startTime =  $("#startTime").datebox('getValue');
			var endTime =  $("#endTime").datebox('getValue');
			var noOrder = $("#noOrder").is(':checked');
			$('#dg').datagrid({
				loadFilter : pagerFilter,
				width:width-18,
				height:height-120
			}).datagrid('loadData', getData(phoneNumber,address,startTime,endTime,noOrder));
			
			$("#searchpanel").panel({width:width-18});
			$("#search").click(function() {
				phoneNumber = $("#phoneNumber").val().trim();
				address = $("#address").val();
				startTime =  $("#startTime").datebox('getValue');
				endTime =  $("#endTime").datebox('getValue');
				noOrder = $("#noOrder").is(':checked');
				if((startTime!="") && noOrder==true){
					alert("时间和未交易只能二选一");
					return false;
				}
				if(startTime==""&&endTime!=""){
					alert("请输入开始时间");
					return false;
				}
				if(startTime!=""&&endTime==""){
					alert("请输入结束时间");
					return false;
				}
				$('#dg').datagrid({
					loadFilter : pagerFilter,
					pageNumber : 1
				}).datagrid('loadData', getData(phoneNumber,address,startTime,endTime,noOrder));
			});
			$("#clear").click(function(){
				$("#phoneNumber").val('');
				$("#address1").attr('selected',true);
				$("#startTime").datebox('setValue', '');
				$("#endTime").datebox('setValue', '');
				$('#noOrder').attr("checked", false);
			});
			
			$.ajax({
				url : "getPlaceCodeName.action",
				data : "",
				dataType : "json",
				type : "post",
				success : function(data) {
					var code=data.code.split(",");
					var name=data.name.split(",");
					var tr="";
					for(var i=0;i<code.length-1;i++){
						var op="<option value='"+code[i]+"'>"+name[i]+"</option>";
						tr=tr+op;
					}
					$("#address1").after(tr);
				},
				error : function(data) {
					alert("error 后台出现错误！");
				}
			});
			
			
		});
	</script>
</body>
</html>