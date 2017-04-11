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
		title="交易记录查询" style="height:110px;padding: 10px;overflow: hidden;">
		<table height="100%">
			<tr	style="Valign: center; text-decoration: none; line-heigth: 30px; height: 30px; margin-left: 10px;">
				<td>手机号码：</td>
				<td><input type="text" id="phoneNumber" style="width:85px;" /></td>
				<td>充值号码：</td>
				<td><input type="text" id="targetNumber" style="width:85px;" /></td>
				<td>返回码：</td>
				<td><input type="text" id="payReturnCode" style="width:60px;" /></td>
				<td>开始时间：</td>
				<td><input type="text" id="startTime" class="easyui-datebox" editable="false" style="width:90px;" /></td>
				<td>结束时间：</td>
				<td><input type="text" id="endTime" class="easyui-datebox" editable="false" style="width:90px;" /></td>
			</tr>
			<tr	style="Valign: center; text-decoration: none; line-heigth: 30px; height: 30px; margin-left: 10px;">
				<td>业务种类：</td>
				<td><select id="busId" style="height: 23px;width:90px;"></select></td>
				<td>产品种类：</td>
				<td><select id="productId" style="height: 23px;width: 90px;"></select></td>
				<td>手机系统：</td>
				<td><select id="os" style="height: 23px;width: 64px;">
				<option value=""></option><option value="android">安卓</option><option value="ios">苹果</option>
				</select></td>
				<td>手机型号：</td>
				<td><input type="text" id="client" style="width:85px;" /></td>
				<td><input type="checkbox" id="success" />成功</td>
				<td><input type="checkbox" id="failed" />失败</td>
				<td><a id="search" class="pushBtn" href="javascript:void(0);" hidefocus="true">
				<img width="20" height="20" src="images/icon/search.gif">
				<b>查询</b>
			</a></td><td><a id="clear" class="pushBtn" href="javascript:void(0);" hidefocus="true">
				<b>条件清空</b>
			</a></td>
			</tr>
		</table>
	</div>
	<table id="dg" class="easyui-datagrid" title="交易记录列表"
		data-options="rownumbers:true,singleSelect:false,toolbar:toolbar,pagination:true,
				pageSize:20,remoteSort:false,fitColumns:true,
				multiSort:true">
		<thead>
			<tr>
				<th data-options="field:'orderTime',width:180">充值时间</th>
				<th data-options="field:'phoneNumber',width:120,hide:'true'">支付号码</th>
				<th data-options="field:'productName',width:150,sortable:true">产品名称</th>
				<th data-options="field:'targetNumber',width:100,sortable:true">充值号码</th>
				<th data-options="field:'payStatus',width:100,sortable:true">交易结果</th>				
				<th data-options="field:'payMoney',width:70">支付金额</th>
				<th data-options="field:'sendScore',width:60">赠送积分</th>
				<th data-options="field:'payReturnCode',width:60">返回码</th>
				<th data-options="field:'os',width:100">手机系统</th>
				<th data-options="field:'client',width:180">手机型号</th>
				<th data-options="field:'version',width:100">系统版本</th>
			</tr>
		</thead>
	</table>
	<script type="text/javascript">
	String.prototype.trim = function() {
		  return this.replace(/^\s\s*/, '').replace(/\s\s*$/, '');
		};
		
		var toolbar = [ {
			text : '导出数据',
			iconCls : 'icon-add',
			handler : function() {
				var phoneNumber = $("#phoneNumber").val().trim();
				var busId = $("#busId").val();
				var productId = $("#productId").val();
				var success = $('#success').is(':checked');
				var failed = $('#failed').is(':checked');
				var startTime = $("#startTime").datebox('getValue');
				var endTime = $("#endTime").datebox('getValue');
				var payReturnCode = $("#payReturnCode").val();
				var targetNumber = $("#targetNumber").val().trim();
				var os=$("#os").val();
				var client=$("#client").val();
				var canshu="phoneNumber=" + phoneNumber + "&busId=" + busId
					+ "&productId=" + productId + "&success=" + success
					+ "&failed=" + failed + "&startTime=" + startTime
					+ "&endTime=" + endTime+"&payReturnCode="+payReturnCode+"&targetNumber="+targetNumber
					+ "&os="+os+"&client="+client;
				var lianjie="toDownloadExcle.action?"+canshu;
				window.location.href=lianjie;
			}
		}];
		
		function getData(phoneNumber, busId, productId, success, failed,
				startTime, endTime,payReturnCode,targetNumber,os,client) {
			var rows = null;
			var dg = $("#dg");
			var opts = dg.datagrid('options');
			var pageSize = opts.pageSize;
			var pageIndex = opts.pageNumber;
			$.ajax({
				url : "getOrderInfo.action",
				async : false,
				data : "pageSize=" + pageSize + "&pageIndex=" + pageIndex
						+ "&phoneNumber=" + phoneNumber + "&busId=" + busId
						+ "&productId=" + productId + "&success=" + success
						+ "&failed=" + failed + "&startTime=" + startTime
						+ "&endTime=" + endTime+"&payReturnCode="+payReturnCode
						+"&targetNumber="+targetNumber+"&os="+os+"&client="+client,
				dataType : "json",
				type : "post",
				success : function(data) {
					if(data.respCode==-1){
						alert(data.respInfo);
						return false;
					}else{
						rows = data;
					}
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
				var phoneNumber = $("#phoneNumber").val().trim();
				var busId = $("#busId").val();
				var productId = $("#productId").val();
				var success = $('#success').is(':checked');
				var failed = $('#failed').is(':checked');
				var startTime = $("#startTime").datebox('getValue');
				var endTime = $("#endTime").datebox('getValue');
				var payReturnCode = $("#payReturnCode").val();
				var targetNumber = $("#targetNumber").val().trim();
				var os=$("#os").val();
				var client=$("#client").val();
				var newData = getData(phoneNumber, busId, productId, success,
						failed, startTime, endTime,payReturnCode,targetNumber,os,client);
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
			var pbusId = "null";
			var today = new Date();
			var year = today.getFullYear();
			var month = today.getMonth() + 1;
			var day = today.getDate();
			if (month<10){month="0"+month;}
			if (day<10){day="0"+day;}
			var startdate = year+"-"+month+"-01";
			var enddate = year+"-"+month+"-"+day;
			$("#startTime").datebox('setValue', startdate);
			$("#endTime").datebox('setValue', enddate);
			var width = $(document).width();
			var height = $(document).height();
			var phoneNumber = $("#phoneNumber").val().trim();
			var busId = $("#busId").val();
			var productId = $("#productId").val();
			var success = $('success').is(':checked');
			var failed = $('failed').is(':checked');
			var startTime = $("#startTime").datebox('getValue');
			var endTime = $("#endTime").datebox('getValue');
			var payReturnCode = $("#payReturnCode").val();
			var targetNumber = $("#targetNumber").val().trim();
			var os=$("#os").val();
			var client=$("#client").val();
			$('#dg').datagrid({
				loadFilter : pagerFilter,
				width : width - 18,
				height : height - 150
			}).datagrid(
					'loadData',
					getData(phoneNumber, busId, productId, success, failed,
							startTime, endTime,payReturnCode,targetNumber,os,client));
			$("#searchpanel").panel({
				width : width - 18
			});
			$.ajax({
				url : "getBusinessType.action",
				type : "post",
				data : "",
				dataType : "json",
				success : function(data) {
					var option = $("<option value='null'></option>");
					$("#busId").append(option);
					for(var i=0;i<data.length;i++){
						option = $("<option value='"+data[i].busId+"'>"+data[i].btype+"</option>");
						$("#busId").append(option);
					}
				},
				error : function(data) {
					alert("error 后台出现错误！");
				}
			});
			$("#search").click(function() {
				var phoneNumber = $("#phoneNumber").val().trim();
				var busId = $("#busId").val();
				var productId = $("#productId").val();
				var success = $('#success').is(':checked');
				var failed = $('#failed').is(':checked');
				var startTime = $("#startTime").datebox('getValue');
				startTime = startTime.trim();
				var endTime = $("#endTime").datebox('getValue');
				endTime = endTime.trim();
				var payReturnCode = $("#payReturnCode").val();
				var targetNumber = $("#targetNumber").val().trim();
				var os=$("#os").val();
				var client=$("#client").val();
				$('#dg').datagrid({
						loadFilter : pagerFilter,
						pageNumber : 1
				}).datagrid('loadData',getData(phoneNumber, busId, productId, success,failed, startTime, endTime,payReturnCode,targetNumber,os,client));
			});
			
			$("#clear").click(function(){
				var today = new Date();
				var year = today.getFullYear();
				var month = today.getMonth() + 1;
				var day = today.getDate();
				if (month<10){month="0"+month;}
				if (day<10){day="0"+day;}
				var startdate = year+"-"+month+"-01";
				var enddate = year+"-"+month+"-"+day;
				$("#startTime").datebox('setValue', startdate);
				$("#endTime").datebox('setValue', enddate);
				$("#busId").val("null");
				$("#phoneNumber").val("");
				$("#productId").html("");
				$('#success').attr("checked", false);
				$('#failed').attr("checked", false);
				$("#payReturnCode").val("");
				pbusId = "null";
				$("#targetNumber").val("");
				$("#os").val("");
				$("#client").val("");
			});
			
			$("#busId").change(function(){
				var id = $(this).val();
				if(pbusId == id || id=='null' ){
					if(id=='null'){
						$("#productId").html("");
					}
					pbusId = id;
					return false;
				}else{
					pbusId = id;
					$.ajax({
						url : "getProduct.action",
						type : "post",
						data : "busId="+id,
						dataType : "json",
						success : function(data) {
							$("#productId").html("");
							var option = $("<option value='null'></option>");
							$("#productId").append(option);
							for(var i=0;i<data.length;i++){
								option = $("<option value='"+data[i].productId+"'>"+data[i].productDescribe+"</option>");
								$("#productId").append(option);
							}
						},
						error : function(data) {
							alert("error 后台出现错误！");
						}
					});
				}
			});
		});
	</script>
</body>
</html>