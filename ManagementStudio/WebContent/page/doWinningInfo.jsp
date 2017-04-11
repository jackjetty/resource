 <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户中奖信息查询</title>
<link rel="stylesheet" type="text/css" href="themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="themes/icon.css">
<script type="text/javascript" src="js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="js/jquery.form.js"></script>

<style type="text/css">
<!--
.panel-margin-buttom {
	margin-bottom: 20px;
}

div ul {
	list-style: none;
}

div ul li {
	line-height: 40px;
	height: 40px;
}

#div1 td{
	height: 35px;
}
#div2 td{
	height: 35px;
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

-->
</style>
</head>
<body style="background: #DFE8F6;">
	<input type="hidden" id="editMenuId" />
	<div id="searchpanel"class="easyui-panel panel-margin-buttom" title="用户中奖信息查询"
		style="height: 80px; padding: 10px;overflow: hidden;">	
		<table>
			<tr style="Valign: center; text-decoration: none; line-heigth: 30px; height: 30px; margin-left: 50px;">
				<td>中奖号码：</td>
				<td><input type="text" id="phoneNumber" style="width: 95px;" /></td>
				<td>中奖时间：</td>
				<td><input type="text" id="winTime" class="easyui-datebox" editable="false" style="width: 95px;" /></td>
				<td>奖品名称：</td>
				<td><select id="prizeId" style="width: 95px;"></select></td>
				<td><a id="search" class="pushBtn" href="javascript:void(0);" hidefocus="true" style="margin-left:10px;">
				<img width="20" height="20" src="images/icon/search.gif">
				<b>查询</b>
			</a></td>
			<td><a id="clear" class="pushBtn" href="javascript:void(0);" hidefocus="true">
				<b>条件清空</b>
			</a></td>
			</tr>
		</table>
	</div>
	<table id="dg" class="easyui-datagrid" title="用户中奖信息列表"
		data-options="rownumbers:true,singleSelect:false,pagination:true,
				pageSize:20,remoteSort:false,fitColumns:true,
				multiSort:true">
		<thead>
			<tr>
				<!-- <th data-options="field:'ck',checkbox:true"></th> -->
				<th data-options="field:'phoneNumber',width:100">中奖号码</th>
				<th data-options="field:'name',width:100">奖品名称</th>
				<!-- <th data-options="field:'amount',width:100">奖品总数</th>
				<th data-options="field:'leftAmount',width:100">剩余数量</th> -->
				<th data-options="field:'winTime',width:100,sortable:true">中奖时间</th>
				<th data-options="field:'describe',width:100">奖品描述</th>
				<th data-options="field:'detail',width:100">是否已发送短信</th>
			</tr>
		</thead>
	</table>
	
	<script type="text/javascript">
	String.prototype.trim = function() {
		  return this.replace(/^\s\s*/, '').replace(/\s\s*$/, '');
		};

		function getData(phoneNumber, winTime,prizeId) {
			var rows = null;
			var dg = $("#dg");
			var opts = dg.datagrid('options');
			var pageSize = opts.pageSize;
			var pageIndex = opts.pageNumber;
			$.ajax({
				url : "getWinningInfo.action",
				async : false,
				data : "pageSize=" + pageSize + "&pageIndex=" + pageIndex
						+ "&phoneNumber=" + phoneNumber + "&winTime=" + winTime + "&prizeId=" + prizeId,
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
				var phoneNumber = $("#phoneNumber").val().trim();
				var winTime = $("#winTime").datebox('getValue');
				var prizeId = $("#prizeId").val();
				var newData = getData(phoneNumber, winTime,prizeId);
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
			$.ajax({
				url : "getPrizeInfoAndSweepInfo.action",
				data : "",
				dataType : "json",
				type : "post",
				success : function(data) {
					var prizeId=data.prizeId.split(",");
					var prizeName=data.prizeName.split(",");
					var sweepId=data.sweepId.split(",");
					var sweepName=data.sweepName.split(",");
					$("#activityId").append("<option id='act1' value='0'></option>");
					for(var i=0;i<sweepId.length-1;i++){
						var option="<option value='"+sweepId[i]+"'>"+sweepName[i]+"</option>";
						$("#activityId").append(option);
					}
					$("#prizeId").append("<option id='act2' value='0'></option>");
					for(var i=0;i<prizeId.length-1;i++){
						var option="<option value='"+prizeId[i]+"'>"+prizeName[i]+"</option>";
						$("#prizeId").append(option);
					}
					
				},
				error : function(data) {
					alert("error 后台出现错误！");
				}
			});
			var width = $(document).width();
			var height = $(document).height();
			var phoneNumber = $("#phoneNumber").val().trim();
			var winTime = $("#winTime").datebox('getValue');
			var prizeId = 0;
			$('#dg').datagrid({
				loadFilter : pagerFilter,
				width:width-18,
				height:height-120
			}).datagrid('loadData', getData(phoneNumber, winTime,prizeId));
			$("#searchpanel").panel({width:width-18});
			$("#search").click(function() {
				phoneNumber = $("#phoneNumber").val().trim();
				winTime = $("#winTime").datebox('getValue');
				prizeId = $("#prizeId").val();
				$('#dg').datagrid({
					loadFilter : pagerFilter,
					pageNumber : 1
				}).datagrid('loadData', getData(phoneNumber, winTime,prizeId));
			});
			$("#clear").click(function(){
				$("#phoneNumber").val('');
				$("#winTime").datebox('setValue','');
				$("#prizeId").val('0');
			});
				
		});
	</script>
	
	
</body>
</html>