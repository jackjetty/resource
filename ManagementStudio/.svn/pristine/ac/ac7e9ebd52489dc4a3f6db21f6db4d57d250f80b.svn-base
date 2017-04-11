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
	margin-bottom: 10px;
}
-->
</style>
</head>
<body style="background: #DFE8F6;">
	<div id="searchpanel" class="easyui-panel panel-margin-buttom"
		title="用户信息详细" style="padding: 10px;">
		<table height="100%">
			<tr	style="Valign: center; text-decoration: none; line-heigth: 30px; height: 30px; margin-left: 10px;">
				<td align="right" width="12.5%">手机号码：</td>
				<td align="left" width="12.5%">${user.phoneNumber}</td>
				<td align="right" width="12.5%">归属地：</td>
				<td align="left" width="12.5%">${user.address}</td>
				<td align="right" width="12.5%">注册时间：</td>
				<td align="left" width="12.5%">${user.registerTime}</td>
				<td align="right" width="12.5%">现有积分：</td>
				<td align="left" width="12.5%">${user.allScore}</td>				
			</tr>
			<tr	style="Valign: center; text-decoration: none; line-heigth: 30px; height: 30px; margin-left: 10px;">
				<td align="right" width="12.5%">总交易笔数：</td>
				<td align="left" width="12.5%">${user.allOrder}</td>
				<td align="right" width="12.5%">成功充值金额：</td>
				<td align="left" width="12.5%">${user.payMoney}</td>
				<td align="right" width="12.5%">成功交易笔数：</td>
				<td align="left" width="12.5%">${user.successOrder}</td>
				<td align="right" width="12.5%">失败交易笔数：</td>
				<td align="left" width="12.5%">${user.failedOrder}</td>
			</tr>
		</table>
	</div>
	<div id="tt" class="easyui-tabs" >
    	<div title="交易记录" style="border: 0;padding: 0;">
        	<table id ='dg' border="0"></table>
    	</div>
    	<div title="签到记录" >
        	<table id = 'dg1' border="0"></table>
    	</div>
    	<div title="抽奖信息" >
        	<table id = 'dg2' border="0"></table>
    	</div>
    	<div title="Q币包月扣费信息">
        	<table id = 'dg3' border="0"></table>
    	</div>
	</div>
	<script type="text/javascript">
	var phoneNumber = '${phoneNumber}';
	var link = '';
	var datagrid = '';
		function getData(phoneNumber,link) {
			var dg = $("#"+datagrid);
			var opts = dg.datagrid('options');
			var pageSize = opts.pageSize;
			var pageIndex = opts.pageNumber;
			$.ajax({
				url : link,
				async : false,
				data : "pageSize=" + pageSize + "&pageIndex=" + pageIndex+"&phoneNumber="+phoneNumber,
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
			var dg = $("#"+datagrid);
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
				var newData = getData(phoneNumber,link);
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
			
			$("#searchpanel").panel({
				width : width - 18
			});
			
			$("#tt").tabs({
				width:width-18,
				height:height-145,
				onSelect : function(title,index){
					if(title =='交易记录'){
						link = "getUserOrderInfo.action";
						datagrid = "dg";
						$('#'+datagrid).datagrid({
							loadFilter : pagerFilter,
							pagination : true,
							rownumbers : true,
							fitColumns : true,
							pageSize:20,
							singleSelect : true,
							width : width - 20,
							height : height - 176,
							columns:[[
							          {field:'orderTime',title:'充值时间',width:200},
							          {field:'productName',title:'产品名称',width:150},
							          {field:'targetNumber',title:'充值号码',width:200},
							          {field:'payStatus',title:'交易结果',width:100},
							          {field:'payMoney',title:'支付金额',width:100},
							          {field:'sendScore',title:'赠送积分',width:100},
							          {field:'payReturnCode',title:'返回码',width:100}
							      ]]
						}).datagrid('loadData', getData(phoneNumber,link));
					}else if(title =='签到记录'){
						link = "getUserCheckInInfo.action";
						datagrid = "dg1";
						$('#'+datagrid).datagrid({
							loadFilter : pagerFilter,
							pagination : true,
							rownumbers : true,
							fitColumns : true,
							pageSize:20,
							singleSelect : true,
							width : width - 20,
							height : height - 176,
							columns:[[
							          {field:'checkinTime',title:'签到时间',width:200},
							          {field:'sendScore',title:'签到积分',width:150},
							      ]]
						}).datagrid('loadData', getData(phoneNumber,link));
					}else if(title =='抽奖信息'){
						link = "getUserWinningInfo.action";
						datagrid = "dg2";
						$('#'+datagrid).datagrid({
							loadFilter : pagerFilter,
							pagination : true,
							rownumbers : true,
							fitColumns : true,
							pageSize:20,
							singleSelect : true,
							width : width - 20,
							height : height - 176,
							columns:[[
							          {field:'name',title:'奖品名称',width:200},
							          {field:'winTime',title:'中奖时间',width:150},
							          {field:'describe',title:'奖品描述',width:200},
							          {field:'detail',title:'是否已发送短信',width:200}
							      ]]
						}).datagrid('loadData', getData(phoneNumber,link));
					}else if(title =='Q币包月扣费信息'){
						link = "getUserQQPerMonthInfo.action";
						datagrid = "dg3";
						$('#'+datagrid).datagrid({
							loadFilter : pagerFilter,
							pagination : true,
							rownumbers : true,
							fitColumns : true,
							pageSize:20,
							singleSelect : true,
							width : width - 20,
							height : height - 176,
							columns:[[
							          {field:'QQNumber',title:'QQ号码',width:100},
							          {field:'checkTime',title:'扫描时间',width:100},
							          {field:'status',title:'扣费状态',width:100},
							          {field:'sendScore',title:'赠送积分',width:100},
							          {field:'month',title:'扣费月',width:100},
							          {field:'sendScore',title:'赠送积分',width:100},
							          {field:'payMoney',title:'实际支付',width:100}
							      ]]
						}).datagrid('loadData', getData(phoneNumber,link));
					}
				}
			});						
		});
	</script>
</body>
</html>

