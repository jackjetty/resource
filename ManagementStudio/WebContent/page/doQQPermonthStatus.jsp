<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
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
    margin: 4px 5px 0 0;
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
-->
</style>
</head>
<body style="background: #DFE8F6;">

		<div id="searchpanel" class="easyui-panel panel-margin-buttom"
		title="用户Q币包月状态查询" style="height: 80px; padding: 10px;overflow: hidden;">
		<table>
			<tr
				style="Valign: center; text-decoration: none; line-heigth: 30px; height: 30px; margin-left: 50px;">
				<td>支付号码：</td>
				<td><input type="text" id="phoneNumber" style="width: 95px;" /></td>
				<td>当前状态：</td>
				<td><select id="status" style="width: 95px;">
					<option value='1'>已开通</option>
					<option value='11'>已开通2</option>
					<option value='2'>已取消</option>
					<option value='22'>已取消2</option>
					<option value='3'>当月扣费成功</option>
					<option value='33'>当月扣费成功2</option>
					<option value='4'>当月暂时未成功</option>
					<option value='44'>当月暂时未成功2</option>
					</select></td>
				<td><a id="search" class="pushBtn" href="javascript:void(0);" hidefocus="true">
				<img width="20" height="20" src="images/icon/search.gif">
				<b>查询</b>
			</a></td>
			<td><a id="clear" class="pushBtn" href="javascript:void(0);" hidefocus="true">
				<b>条件清空</b>
			</a></td>
			</tr>
		</table>
	</div>
	<input type="hidden" id="editQQPermonthRecode" />
	<table id="dg" class="easyui-datagrid" title="用户Q币包月状态列表"
		data-options="rownumbers:true,singleSelect:false,pagination:true,
				pageSize:20,remoteSort:false,fitColumns:true,
				multiSort:true">
		<thead>
			<tr>
				<!--  <th data-options="field:'id',width:100">主键ID</th>-->
				<th data-options="field:'phoneNumber',width:100">支付号码</th>
				<th data-options="field:'qqNumber',width:100">QQ号码</th>
				<th data-options="field:'fee',width:100">当前月费</th>
				<th data-options="field:'status',width:100">当前状态</th>
				<th data-options="field:'regDate',width:100">包月注册时间</th>
				<th data-options="field:'applyDate',width:100">最后申请时间</th>
				<th data-options="field:'cancelDate',width:100">包月取消时间</th>
				<th data-options="field:'month',width:100">最近扣费成功月</th>
			</tr>
		</thead>
	</table>
	<script type="text/javascript">
	function getData(phoneNumber,status) {
		var rows = null;
		var dg = $("#dg");
		var opts = dg.datagrid('options');
		var pageSize = opts.pageSize;
		var pageIndex = opts.pageNumber;
		$.ajax({
			url : "getQQPermonthStatus.action",
			async : false,
			data : "pageSize=" + pageSize + "&pageIndex=" + pageIndex
					+ "&phoneNumber="+phoneNumber+ "&status="+status,
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
			var phoneNumber = $("#phoneNumber").val();
			var status = $("#status").val();
			var newData = getData(phoneNumber,status);
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
		var width = $(document).width();
		var height = $(document).height();
		var phoneNumber = $("#phoneNumber").val();
		var status = $("#status").val();
		$('#dg').datagrid({
			loadFilter : pagerFilter,
			width : width - 18,
			height : height - 120
		}).datagrid(
				'loadData',
				getData(phoneNumber,status));
		$("#searchpanel").panel({
			width : width - 18
		});
		$("#search").click(function() {
			var phoneNumber = $("#phoneNumber").val().trim();
			var status = $("#status").val();
					$('#dg').datagrid({
						loadFilter : pagerFilter,
						pageNumber : 1
					}).datagrid('loadData',getData(phoneNumber,status));
				});
		$("#clear").click(function(){
			$("#phoneNumber").val('');		
			 $("#status").val("null");
		});

});
	String.prototype.trim = function() {
		  return this.replace(/^\s\s*/, '').replace(/\s\s*$/, '');
		};
	</script>
</body>
</html>

