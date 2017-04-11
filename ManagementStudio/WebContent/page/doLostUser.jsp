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
	<div id="searchpanel"class="easyui-panel panel-margin-buttom" title="沉寂用户查询"
		style="height: 80px; padding: 10px;overflow: hidden;">	
			<table>
			<tr style="Valign: center; text-decoration: none; line-heigth: 30px; height: 30px; margin-left: 50px;">
				<td>手机号码：</td>
				<td><input type="text" id="phoneNumber" /></td>
				<td>沉寂天数：</td>
				<td><input type="text" id="dayNumber"></td>
				<td><input type="checkbox" id="noOrder"></td>
				<td>无交易用户</td>
				<td><a id="search" class="pushBtn" href="javascript:void(0);" hidefocus="true">
				<img width="20" height="20" src="images/icon/search.gif">
				<b>查询</b>
			</a></td><td><a id="clear" class="pushBtn" href="javascript:void(0);" hidefocus="true">
				<b>条件清空</b>
			</a></td>
			</tr>
		</table>
	</div>
	<table id="dg" class="easyui-datagrid" title="沉寂用户列表"
		data-options="rownumbers:true,singleSelect:true,pagination:true,
				pageSize:20,remoteSort:false,fitColumns:true,
				multiSort:true">
		<thead>
			<tr>
				<th data-options="field:'phoneNumber',width:60,sortable:true">手机号码</th>
				<th data-options="field:'score',width:40,sortable:true">积分</th>
				<th data-options="field:'address',width:50">归属地</th>
				<th data-options="field:'registerTime',width:100,sortable:true">注册时间</th>
				<th data-options="field:'orderTime',width:100,sortable:true">最后交易时间</th>
				<th data-options="field:'checkinTime',width:100,sortable:true">最后签到时间</th>
				<th data-options="field:'winningTime',width:100,sortable:true">最后抽奖时间</th>
				<th	data-options="field:'opt',align:'center',width:80,formatter:function(){return '<a href=\'javascript:detail();\'>查看详情</a>'}">操作</th>
			</tr>
		</thead>
	</table>
	
	<script type="text/javascript">
		String.prototype.trim = function() {
		  return this.replace(/^\s\s*/, '').replace(/\s\s*$/, '');
		};
		
		function detail() {	
			var row = $("#dg").datagrid('getSelected');
			var link = "viewUserDetail.action?phoneNumber="+row.phoneNumber;
			parent.window.addTab(row.phoneNumber+"信息详情",link);
		}
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
				var noOrder = $("#noOrder").is(':checked');
				if(!noOrder){
					phoneNumber = $("#phoneNumber").val().trim();
					dayNumber = $("#dayNumber").val().trim();
					if(phoneNumber==''){
						if(dayNumber ==''){
							alert("请填入沉寂天数！");
							return false;
						}else{
							if(isNaN(dayNumber)|| parseInt(dayNumber)<0){
								alert("请在沉寂天数填入正整数！");
								return false;
							}
						}
					}
				}else{
					phoneNumber = "";
					dayNumber = 0;
				}
				var newData = getData(phoneNumber,dayNumber,noOrder);
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
		function getData(phoneNumber,dayNumber,noOrder) {
			var rows = null;
			var dg = $("#dg");
			var opts = dg.datagrid('options');
			var pageSize = opts.pageSize;
			var pageIndex = opts.pageNumber;
			$.ajax({
				url : "getLostUser.action",
				async : false,
				data : "pageSize=" + pageSize + "&pageIndex=" + pageIndex
						+ "&phoneNumber=" + phoneNumber + "&dayNumber=" + dayNumber+ "&noOrder=" + noOrder,
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
			$('#dg').datagrid({
				width:width-18,
				height:height-120
			});
			$("#noOrder").change(function(){
				if($("#noOrder").is(':checked')){
					$("#phoneNumber").attr('disabled',true);
					$("#dayNumber").attr('disabled',true);
				}else{
					$("#phoneNumber").attr('disabled',false);
					$("#dayNumber").attr('disabled',false);
				}
				
			});
			$("#searchpanel").panel({width:width-18});
			$("#search").click(function() {
				var noOrder = $("#noOrder").is(':checked');
				if(!noOrder){
					phoneNumber = $("#phoneNumber").val().trim();
					dayNumber = $("#dayNumber").val().trim();
					if(phoneNumber==''){
						if(dayNumber ==''){
							alert("请填入沉寂天数！");
							return false;
						}else{
							if(isNaN(dayNumber)|| parseInt(dayNumber)<0){
								alert("请在沉寂天数填入正整数！");
								return false;
							}
						}
					}
				}else{
					phoneNumber = "";
					dayNumber = 0;
				}
								
				$('#dg').datagrid({
					loadFilter : pagerFilter,
					pageNumber : 1
				}).datagrid('loadData', getData(phoneNumber,dayNumber,noOrder));
			});
			$("#clear").click(function(){
				$("#phoneNumber").val('');
				$("#dayNumber").val('');
				$("#phoneNumber").attr('disabled',false);
				$("#dayNumber").attr('disabled',false);
				$("#noOrder").attr("checked", false);
			});			
		});
	</script>
</body>
</html>