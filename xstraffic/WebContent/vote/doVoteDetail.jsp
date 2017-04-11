<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=8" />
<title></title>
<link rel="stylesheet" type="text/css" href="themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="themes/icon.css">
<script type="text/javascript" src="js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="js/baseUtil.js"></script>
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
	<div region="north" border="false" split="true">
		<div id="searchpanel" data-options="fit:true"
			class="easyui-panel panel-margin-buttom" title="投票活动查询"
			style="height:10px; padding: 10px;">
			<table height="100%">
				<tr
					style="Valign: center; text-decoration: none; line-heigth: 30px; height: 30px; margin-left: 10px;">
					<td>候选人：</td>
					<td><input type="text" id="name" style="width:120px;" /></td>
					<td>openId：</td>
					<td><input type="text" id="openId" style="width:120px;" /></td>
					<td>昵称：</td>
					<td><input type="text" id="nickName" style="width:120px;" /></td>
					<td>开始时间：</td>
					<td><input type="text" id="startTime" class="easyui-datebox" style="width: 100px;" /></td>
					<td>结束时间：</td>
					<td><input type="text" id="endTime" class="easyui-datebox" style="width: 100px;" /></td>			
					<td><a id="search" class="pushBtn" href="javascript:void(0);"
						hidefocus="true"> <img width="20" height="20"
							src="image/icon/search.gif"> <b>查询</b>
					</a></td>
					<td><a id="clear" class="pushBtn" href="javascript:void(0);"
						hidefocus="true"> <b>条件清空</b>
					</a></td>
				</tr>
			</table>
		</div>
	</div>
	<table id="dg" class="easyui-datagrid" title="投票详情"
		data-options="rownumbers:true,singleSelect:false,pagination:true,
				pageSize:20,remoteSort:false,fitColumns:true,
				multiSort:true">
		<thead>
			<tr>
				
				<th data-options="field:'name',width:100,sortable:true,align:'center'">候选人信息</th>
				<th data-options="field:'headimgurl',width:150,sortable:true,formatter:getImg">投票人图像</th>
				<th data-options="field:'openId',width:200,sortable:true">投票人openId</th>
				<th data-options="field:'nickName',width:200,sortable:true">投票人昵称</th>
				<th data-options="field:'city',width:200,sortable:true">投票人城市</th>
				<th data-options="field:'subscribeTime',width:200,sortable:true">投票人关注时间</th>
			 	<th data-options="field:'createTime',width:150,sortable:true">投票时间</th>
				<th data-options="field:'id',width:150,sortable:true,formatter:success">状态</th>
				
			</tr>
		</thead>
	</table>
	<script type="text/javascript">
	String.prototype.trim = function() {
		  return this.replace(/^\s\s*/, '').replace(/\s\s*$/, '');
		};
		
		var voteId = "${voteId}";
		
		
		function success(){
				
			return '<span>成功</span>';
		}
		
		function operate(value, row, index) {
			return new Date(value).toCommonCase();
		}
		
		function getImg(value, row, index) {
			return "<img src='"+row.headimgurl+"' style='width:20px;height:20px'/>";
		}

		function getData(name,openId, nickName,startTime,endTime) {
			var rows = null;
			var dg = $("#dg");
			var opts = dg.datagrid('options');
			var pageSize = opts.pageSize;
			var pageIndex = opts.pageNumber;
			$.ajax({
				url : "getTenderByPage.action",
				async : false,
				data : "voteId=" + voteId + "&name=" + name + "&openId=" + openId+ "&nickName=" + nickName+ "&startDate=" + startTime+ "&endDate=" + endTime + "&pageSize=" + pageSize + "&pageIndex=" + pageIndex,
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
				var name = $("#name").val().trim();
				var openId = $("#openId").val().trim();
				var nickName = $("#nickName").val().trim();
				var startTime = $("#startTime").datebox("getValue");
				var endTime = $("#endTime").datebox("getValue");
				var newData = getData(name,openId, nickName,startTime,endTime);
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
		$(function() {
			var name = $("#name").val().trim();
			var openId = $("#openId").val().trim();
			var nickName = $("#nickName").val().trim();
			var startTime = $("#startTime").datebox("getValue");
			var endTime = $("#endTime").datebox("getValue");
			var width = $(document).width();
			var height = $(document).height();
			$('#dg').datagrid({
				loadFilter : pagerFilter,
				width : width - 20,
				height : height - 45
			}).datagrid('loadData', getData(name,openId, nickName,startTime,endTime));
			
			$("#searchpanel").panel({width:width-20});
			
			$("#search").click(
				function() {
					var name = $("#name").val().trim();
					openId = $("#openId").val().trim();
					nickName = $("#nickName").val().trim();
					startTime = $("#startTime").datebox("getValue");
					endTime = $("#endTime").datebox("getValue");
					$('#dg').datagrid({
						loadFilter : pagerFilter,
						pageNumber : 1
					}).datagrid('loadData',
							getData(name,openId, nickName,startTime,endTime));
				});

		$("#clear").click(function() {
			$("#name").val('');
			$("#openId").val('');
			$("#nickName").val('');
			$("#startTime").datebox("setValue",""); 
			$("#endTime").datebox("setValue","");
		});
			
		});
		
	</script>
	
</body>
</html>