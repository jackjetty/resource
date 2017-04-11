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
#div1 td{
	height: 35px;
	text-align:right;
}
#div2 td{
	height: 35px;
	text-align:right;
}
#add_payMoneyType{
	width: 145px;
	border:1px solid #8FBC8F;
	margin-left:1px;
}
#edit_payMoneyType{
	width: 145px;
	border:1px solid #8FBC8F;
	margin-left:1px;
}
-->
</style>
</head>
<body style="background: #DFE8F6;">
	<div id="searchpanel" class="easyui-panel panel-margin-buttom"
		title="活动奖品查询" style="height: 80px; padding: 10px;overflow: hidden;">
		<table height="100%">
			<tr	style="Valign: center; text-decoration: none; line-heigth: 30px; height: 30px; margin-left: 50px;">
				<td>活动名称：</td>
				<td><select id="activityId" style="width: 95px;" ></select></td>
				<td>奖品名称：</td>
				<td><select id="prizeId" style="width: 95px;"></select></td>
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
	<table id="dg" class="easyui-datagrid" title="活动奖品列表"
		data-options="rownumbers:true,singleSelect:false,toolbar:toolbar,pagination:true,
				pageSize:20,remoteSort:false,fitColumns:true,
				multiSort:true">
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field:'activityName',width:60">活动名称</th>
				<th data-options="field:'prizeName',width:60,sortable:true">奖品名称</th>
				<th data-options="field:'prizeNumber',width:50,sortable:true">奖品数量</th>
				<th data-options="field:'userLimit',width:60,align:'center'">地区范围</th>
				<th data-options="field:'payMoneyTime',width:80,align:'center'">充值时间</th>
				<th data-options="field:'payMoneyLimit',width:80,align:'center'">充值金额</th>
				<th data-options="field:'payMoneyType',width:150,align:'center'">充值类型</th>
				<th data-options="field:'frequency',width:60,align:'center'">中奖频率</th>
				<th data-options="field:'frequencyNumber',width:60,align:'center'">中奖次数</th>
				<th data-options="field:'rate',width:50,align:'center'">中奖概率</th>
				<th data-options="field:'userFrequency',width:70,align:'center'">同用户中奖时间限制</th>
				<th data-options="field:'userFrequencyNumber',width:70,align:'center'">用户中奖次数</th>
				<th data-options="field:'show',width:200,align:'center'">提示信息</th>
				<th data-options="field:'needMessage',width:60,align:'center'">发送短信</th>
				<th data-options="field:'needCode',width:60,align:'center'">需要验证码</th>
				<th data-options="field:'oneToMany',width:30,align:'center'">1：N</th>
			</tr>
		</thead>
	</table>
	
	
	<script type="text/javascript">
	String.prototype.trim = function() {
		  return this.replace(/^\s\s*/, '').replace(/\s\s*$/, '');
		};
		var toolbar = [ {
			text : '添加',
			iconCls : 'icon-add',
			handler : function() {
				$("#add_activityId").empty();
				$("#add_prizeId").empty();
				$("#add_prizeNumber").val('');
				$("#add_userLimit").empty();
				$('#dlg').dialog('open');
				$("#add_payMoneyType").empty();
				$("#add_payMoneyLimit").val('');
				$("#add_frequency").val('');
				$("#add_rate").val('');
				$("#add_show").val('');
				$("#add_needMessage").val("no");
				$("#add_needCode").val("no");
				$("#add_oneToMany").val(1);
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
						$("#add_activityId").append("<option value='null'></option>");
						for(var i=0;i<sweepId.length-1;i++){
							var option="<option value='"+sweepId[i]+"'>"+sweepName[i]+"</option>";
							$("#add_activityId").append(option);
						}
						$("#add_prizeId").append("<option value='null'></option>");
						for(var i=0;i<prizeId.length-1;i++){
							var option="<option value='"+prizeId[i]+"'>"+prizeName[i]+"</option>";
							$("#add_prizeId").append(option);
						}
						
					},
					error : function(data) {
						alert("error 后台出现错误！");
					}
				});
				$.ajax({
					url : "getPlaceCodeName.action",
					data : "",
					dataType : "json",
					type : "post",
					success : function(data) {
						var placeCode=data.code.split(",");
						var placeName=data.name.split(",");
						$("#add_userLimit").append("<option value=''>全省</option>");
						for(var i=0;i<placeCode.length-1;i++){
							var option="<option value='"+placeCode[i]+"'>"+placeName[i]+"</option>";
							$("#add_userLimit").append(option);
						}
						
					},
					error : function(data) {
						alert("error 后台出现错误！");
					}
				});
				$.ajax({
					url : "getBusAndBtype.action",
					data : "",
					dataType : "json",
					type : "post",
					success : function(data) {
						var busId=data.busId.split(",");
						var busName=data.Btype.split(",");
						for(var i=0;i<busId.length-1;i++){
							var option="<input type='checkbox' value='"+busId[i]+"' />"+busName[i]+" ";
							$("#add_payMoneyType").append(option);
						}
					},
					error : function(data) {
						alert("error 后台出现错误！");
					}
				});
				
			}
		}, '-', {
			text : '编辑',
			iconCls : 'icon-edit',
			handler : function() {
				var rows = $('#dg').datagrid('getSelections');
				if (rows.length == 0) {
					alert("请选择需要编辑的数据！");
					return false;
				} else if (rows.length > 1) {
					alert("一次只能编辑一行！");
				} else{
					var row = $("#dg").datagrid('getSelected');
					$('#edit-dialog').dialog('open');
					$("#edit_id").val(row.id);
					$("#edit_prizeNumber").val(row.prizeNumber);
					$("#edit_activityId").empty();
					$("#edit_prizeId").empty();
					$("#edit_userLimit").empty();
					$("#edit_payMoneyTime").val(row.payMoneyTimeCode);
					$("#edit_payMoneyType").empty();
					$("#edit_payMoneyLimit").val(row.payMoneyLimit);
					$("#edit_frequency").val(row.frequencyCode);
					$("#edit_frequencyNumber").val(row.frequencyNumber);
					$("#edit_show").val(row.show);
					$("#edit_needMessage").val(row.needMessage);
					$("#edit_needCode").val(row.needCode);
					$("#edit_oneToMany").val(row.oneToMany);
					if(row.rate.length==2){
						var gailv=(row.rate.substr(0,1))/100;
						$("#edit_rate").val(gailv);
					}else{
						var gailv=(row.rate.substr(0,2))/100;
						$("#edit_rate").val(gailv);
					}
					$("#edit_userFrequency").val(row.userFrequencyCode);
					$("#edit_userFrequencyNumber").val(row.userFrequencyNumber);
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
							for(var i=0;i<sweepId.length-1;i++){
								var option;
								if(row.activityName==sweepName[i]){
									option="<option value='"+sweepId[i]+"' selected='true' >"+sweepName[i]+"</option>";
								}else{
									option="<option value='"+sweepId[i]+"'>"+sweepName[i]+"</option>";
								}
								$("#edit_activityId").append(option);
							}
							for(var i=0;i<prizeId.length-1;i++){
								var option;
								if(prizeName[i]==row.prizeName){
									option="<option value='"+prizeId[i]+"' selected='true' >"+prizeName[i]+"</option>";
								}else{
									option="<option value='"+prizeId[i]+"'>"+prizeName[i]+"</option>";
								}
								$("#edit_prizeId").append(option);
							}
						},
						error : function(data) {
							alert("error 后台出现错误！");
						}
					});
					$.ajax({
						url : "getPlaceCodeName.action",
						data : "",
						dataType : "json",
						type : "post",
						success : function(data) {
							var placeCode=data.code.split(",");
							var placeName=data.name.split(",");
							$("#edit_userLimit").append("<option value=''>全省</option>");
							for(var i=0;i<placeCode.length-1;i++){
								var option;
								if(placeName[i]==row.userLimit){
									option="<option value='"+placeCode[i]+"' selected='true' >"+placeName[i]+"</option>";
								}else{
									option="<option value='"+placeCode[i]+"'>"+placeName[i]+"</option>";
								}
								$("#edit_userLimit").append(option);
							}
						},
						error : function(data) {
							alert("error 后台出现错误！");
						}
					});
					var payMoneyType=row.payMoneyType;
					$.ajax({
						url : "getBusAndBtype.action",
						data : "",
						dataType : "json",
						type : "post",
						success : function(data) {
							var busId=data.busId.split(",");
							var busName=data.Btype.split(",");
							for(var i=0;i<busId.length-1;i++){
								var option;
								if(payMoneyType.indexOf(busName[i])!=-1){
									option="<input type='checkbox' value='"+busId[i]+"' Checked = 'true' />"+busName[i]+" ";
								}else{
									option="<input type='checkbox' value='"+busId[i]+"' />"+busName[i]+" ";
								}
								$("#edit_payMoneyType").append(option);
							}
						},
						error : function(data) {
							alert("error 后台出现错误！");
						}
					});
					
				}
			}
		}, '-', {
			text : '删除',
			iconCls : 'icon-remove',
			handler : function() {
				var rows = $('#dg').datagrid('getSelections');
				if(rows.length==0){
					alert("请选择要删除的数据！");
					return false;
				}
				if(window.confirm("确认删除这"+rows.length+"记录？")){
					var ids = '';
					for(var i=0;i<rows.length;i++){
						ids = ids + ","+rows[i].id;
					}
					$.ajax({
						url : "removeSweepStakesPrize.action",
						data : "ids="+ ids,
						dataType : "json",
						type : "post",
						success : function(data) {
							alert(data.respInfo);
							var activityId = $("#activityId").val();
							var prizeId = $("#prizeId").val();
							$('#dg').datagrid({
								loadFilter : pagerFilter
							}).datagrid('loadData', getData(activityId, prizeId));
						},
						error : function(data) {
							alert("error 后台出现错误！");
						}
					});
				}else{
					return false;
				}
			}
		}];
		
		function getData(activityId, prizeId) {
			var rows = null;
			var dg = $("#dg");
			var opts = dg.datagrid('options');
			var pageSize = opts.pageSize;
			var pageIndex = opts.pageNumber;
			$.ajax({
				url : "getSweepStakesPrize.action",
				async : false,
				data : "pageSize=" + pageSize + "&pageIndex=" + pageIndex
						+ "&activityId=" + activityId + "&prizeId=" + prizeId,
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
				var activityId = $("#activityId").val();
				var prizeId = $("#prizeId").val();
				var newData = getData(activityId, prizeId);
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
					$("#activityId").append("<option id='act1' value='null'></option>");
					for(var i=0;i<sweepId.length-1;i++){
						var option="<option value='"+sweepId[i]+"'>"+sweepName[i]+"</option>";
						$("#activityId").append(option);
					}
					$("#prizeId").append("<option id='act2' value='null'></option>");
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
			var activityId = $("#activityId").val();
			var prizeId = $("#prizeId").val();
			$('#dg').datagrid({
				loadFilter : pagerFilter,
				width:width-18,
				height:height-120
			}).datagrid('loadData', getData(activityId, prizeId));
			$("#searchpanel").panel({width:width-18});
			$("#search").click(function() {
				activityId = $("#activityId").val();
				prizeId = $("#prizeId").val();
				$('#dg').datagrid({
					loadFilter : pagerFilter,
					pageNumber : 1
				}).datagrid('loadData', getData(activityId, prizeId));
			});
			$("#clear").click(function(){
				$("#act1").attr('selected',true);
				$("#act2").attr('selected',true);
				
			});
			$("#addSweepStakesPrize").click(function(){
				var activityId=$("#add_activityId").val();
				var prizeId=$("#add_prizeId").val();
				var prizeNumber=$("#add_prizeNumber").val();
				var userLimit=$("#add_userLimit").val();
				var payMoneyTime = $("#add_payMoneyTime").val();
				var type=$("input:checked");
				var payMoneyType="";
				for(var i=0;i<type.length;i++){
					if(i==type.length-1){
						payMoneyType=payMoneyType+type[i].value;
					}else{
						payMoneyType=payMoneyType+type[i].value+"&";
					}
				}
				var payMoneyLimit = $("#add_payMoneyLimit").val();
				var rate = $("#add_rate").val();
				var frequency = $("#add_frequency").val();
				var frequencyNumber = $("#add_frequencyNumber").val();
				var userFrequency = $("#add_userFrequency").val();
				var userFrequencyNumber = $("#add_userFrequencyNumber").val();
				var show=$("#add_show").val();
				var needMessage = $("#add_needMessage").val();
				var needCode = $("#add_needCode").val();
				var oneToMany = $("#add_oneToMany").val();
				payMoneyType = encodeURIComponent(payMoneyType);
				if(activityId=="null"){alert("请选择活动");return false;}
				if(prizeId=="null"){alert("请选择奖品");return false;}
				if(prizeNumber==""){alert("请输入奖品数量");return false;}
				if(payMoneyTime==""){alert("请选择充值时间");return false;}
				if(payMoneyType==""){alert("请选择充值类型");return false;}
				if(payMoneyLimit==""){alert("请输入充值时间内最低金额限制");return false;}
				if(rate==""){alert("请输入中奖概率");return false;}
				if(rate.length>0){
					var re = /^(-?\d+)(\.\d+)$/;
					if(!re.test(rate)){
						alert("请输入正确的中奖概率(0-1)如0.5");
						return false;
					}else 
						if(!(parseFloat(rate)>=0 && parseFloat(rate)<=1)){
						alert("请输入正确的中奖概率(0-1)如0.5");
						return false;
					}
				}
				var rateSpit=rate.split(".")[1];
				if(rateSpit.length>2){
					alert("请输入中奖概率小数点后只能有两位");
					return false;
				}
				if(oneToMany==""){alert("请输入验证码比例！");return false;}
				$.ajax({
					url : "addSweepStakesPrize.action",
					data : "activityId=" + activityId + "&prizeId=" + prizeId+"&prizeNumber="+prizeNumber+
							"&userLimit="+userLimit+"&payMoneyLimit="+payMoneyLimit+"&frequency="+frequency
							+"&rate="+rate+"&userFrequency="+userFrequency+"&payMoneyType="+payMoneyType
							+"&show="+show+"&needMessage="+needMessage+"&needCode="+needCode+"&oneToMany="+oneToMany+"&payMoneyTime="+payMoneyTime+"&frequencyNumber="+frequencyNumber+"&userFrequencyNumber="+userFrequencyNumber,
					dataType : "json",
					type : "post",
					success : function(data) {
						if(data.respCode==0){
							alert(data.respInfo);
							$('#dlg').dialog('close');
							activityId = $("#activityId").val();
							prizeId = $("#prizeId").val();
							$('#dg').datagrid({
								loadFilter : pagerFilter
							}).datagrid('loadData', getData(activityId, prizeId));
						}else{
							alert(data.respInfo);
						}
						
					},
					error : function(data) {
						alert("error 后台出现错误！");
					}
				});
				
				
			});
			$("#updateSweepStakesPrize").click(function(){
				var id = $("#edit_id").val();
				var activityId=$("#edit_activityId").val();
				var prizeId=$("#edit_prizeId").val();
				var prizeNumber=$("#edit_prizeNumber").val();
				var userLimit=$("#edit_userLimit").val();
				var payMoneyTime = $("#edit_payMoneyTime").val();
				var type=$("input:checked");
				var payMoneyType="";
				for(var i=0;i<type.length;i++){
					if(i==type.length-1){
						payMoneyType=payMoneyType+type[i].value;
					}else{
						payMoneyType=payMoneyType+type[i].value+"&";
					}
				}
				var payMoneyLimit = $("#edit_payMoneyLimit").val();
				var rate = $("#edit_rate").val();
				var frequency = $("#edit_frequency").val();
				var frequencyNumber = $("#edit_frequencyNumber").val();
				var userFrequency = $("#edit_userFrequency").val();
				var userFrequencyNumber = $("#edit_userFrequencyNumber").val();
				var show=$("#edit_show").val();
				var needMessage = $("#edit_needMessage").val();
				var needCode = $("#edit_needCode").val();
				var oneToMany = $("#edit_oneToMany").val();
				payMoneyType = encodeURIComponent(payMoneyType);
				if(activityId=="null"){alert("请选择活动");return false;}
				if(prizeId=="null"){alert("请选择奖品");return false;}
				if(prizeNumber==""){alert("请输入奖品数量");return false;}
				if(payMoneyTime==""){alert("请选择充值时间");return false;}
				if(payMoneyType==""){alert("请选择充值类型");return false;}
				if(payMoneyLimit==""){alert("请输入充值时间内最低金额限制");return false;}
				if(rate==""){alert("请输入中奖概率");return false;}
				if(rate.length>0){
					var re = /^(-?\d+)(\.\d+)$/;
					if(!re.test(rate)){
						alert("请输入正确的中奖概率(0-1)如0.5");
						return false;
					}else 
						if(!(parseFloat(rate)>=0 && parseFloat(rate)<=1)){
						alert("请输入正确的中奖概率(0-1)如0.5");
						return false;
					}
				}
				var rateSpit=rate.split(".")[1];
				if(rateSpit.length>2){
					alert("请输入中奖概率小数点后只能有两位");
					return false;
				}
				if(oneToMany==""){alert("请输入验证码比例！");return false;}
				$.ajax({
					url : "updateSweepStakesPrize.action",
					data : "id="+id+"&activityId=" + activityId + "&prizeId=" + prizeId+"&prizeNumber="+prizeNumber+
					"&userLimit="+userLimit+"&payMoneyLimit="+payMoneyLimit+"&frequency="+frequency
					+"&rate="+rate+"&userFrequency="+userFrequency+"&payMoneyType="+payMoneyType
					+"&show="+show+"&needMessage="+needMessage+"&needCode="+needCode+"&oneToMany="+oneToMany+"&payMoneyTime="+payMoneyTime+"&frequencyNumber="+frequencyNumber+"&userFrequencyNumber="+userFrequencyNumber,
					dataType : "json",
					type : "post",
					success : function(data) {
						if(data.respCode==0){
							alert(data.respInfo);
							$('#edit-dialog').dialog('close');
							activityId = $("#activityId").val();
							prizeId = $("#prizeId").val();
							$('#dg').datagrid({
								loadFilter : pagerFilter
							}).datagrid('loadData', getData(activityId, prizeId));
						}else{
							alert(data.respInfo);
						}
					},
					error : function(data) {
						alert("error 后台出现错误！");
					}
				});
				
			});
		});
	</script>
	
	<div id="dlg" class="easyui-dialog" title="添加抽奖活动奖品信息"
		data-options="iconCls:'icon-save',closed:true"
		style="width: 740px; height: 500px; padding: 10px; background: #DFE8F6;">
		<div id="div1" style="margin-left:10px;">
			<table style="margin-left:10px;margin-top: 20px;">
				<tr>
					<td>活动名称：</td>
					<td><select id="add_activityId" style="width: 150px;" ></select></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
					<td>奖品名称：</td>
					<td><select id="add_prizeId" style="width: 150px;" ></select></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
				</tr>
				
				<tr>
					<td>奖品数量：</td>
					<td><input type="text" id="add_prizeNumber" style="width: 145px;" /></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
					<td>地区范围：</td>
					<td><select id="add_userLimit" style="width: 150px;" ></select></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
				</tr>
				<tr>
					<td>充值时间：</td>
					<td><select id="add_payMoneyTime" style="width: 150px;" >
							<option value="">请选择</option>
							<option value="AllTime">总计</option>
							<option value="ThisYear">当年</option>
							<option value="ThisMonth">当月</option>
							<option value="ThisWeek">当周</option>
							<option value="ThisDay">当天</option>
						</select></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
					<td>充值类型：</td>
					<td><div id="add_payMoneyType">
					</div></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
				</tr>
				<tr>
					<td>充值金额：</td>
					<td><input type="text" id="add_payMoneyLimit" style="width: 145px;" /></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
					<td>中奖概率：</td>
					<td><input type="text" id="add_rate" style="width: 145px;" /></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
				</tr>
				<tr>
					<td>中出时间：</td>
					<td><select id="add_frequency" style="width: 150px;" >
							<option value="">请选择</option>
							<option value="PerYear">每年</option>
							<option value="PerMonth">每月</option>
							<option value="PerWeek">每周</option>
							<option value="PerDay">每天</option>
						</select>
					</td>
					<td><font>&nbsp;*&nbsp;选填</font></td>
					<td>中奖次数：</td>
					<td><input type="text" id="add_frequencyNumber" style="width: 145px;" /></td>
					<td><font>&nbsp;*&nbsp;选填</font></td>
				</tr>
				<tr>
					<td>同用户中奖时间：</td>
					<td><select id="add_userFrequency" style="width: 150px;" >
							<option value="">请选择</option>
							<option value="PerYear">每年</option>
							<option value="PerMonth">每月</option>
							<option value="PerWeek">每周</option>
							<option value="PerDay">每天</option>
						</select>
					</td>
					<td><font>&nbsp;*&nbsp;选填</font></td>
					<td>用户中奖次数：</td>
					<td><input type="text" id="add_userFrequencyNumber" style="width: 145px;" /></td>
					<td><font>&nbsp;*&nbsp;选填</font></td>	
				</tr>
				<tr>
					<td>提示信息：</td>
					<td><input type="text" id="add_show" style="width: 145px;" /></td>
					<td><font>&nbsp;*&nbsp;选填</font></td>	
					<td>发送短信：</td>
					<td><select id="add_needMessage" style="width: 150px;" >
						<option value="no">no</option>
						<option value="yes">yes</option>
						</select></td>
					<td><font>&nbsp;*&nbsp;选填</font></td>
				</tr>
				<tr>	
					<td>需要验证码：</td>
					<td><select id="add_needCode" style="width: 150px;" >
						<option value="no">no</option>
						<option value="yes">yes</option>
					</select></td>
					<td><font>&nbsp;*&nbsp;选填</font></td>
					<td>1：N ：</td>
					<td><input type="text" id="add_oneToMany" style="width: 145px;" /></td>
					<td><font>&nbsp;*&nbsp;选填</font></td>		
				</tr>
			</table>
		</div>
		<div align="center" style="margin-top:10px;margin-bottom: 10px;">
			<input type="button" value="添加" id="addSweepStakesPrize" />
		</div>
	</div>
	
	
	<div id="edit-dialog" class="easyui-dialog" title="修改抽奖活动奖品信息"
		data-options="iconCls:'icon-edit',closed:true"
		style="width: 740px; height: 500px; padding: 10px; background: #DFE8F6;">
		<div id="div2" style="margin-left:10px;">
			<input type="hidden" id="edit_id" />
			<table style="margin-left:10px;margin-top: 20px;">
				<tr>
					<td>活动名称：</td>
					<td><select id="edit_activityId" style="width: 150px;" ></select></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
					<td>奖品名称：</td>
					<td><select id="edit_prizeId" style="width: 150px;" ></select></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
				</tr>
				
				<tr>
					<td>奖品数量：</td>
					<td><input type="text" id="edit_prizeNumber" style="width: 145px;" /></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
					<td>地区范围：</td>
					<td><select id="edit_userLimit" style="width: 150px;" ></select></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
				</tr>
				<tr>
					<td>充值时间：</td>
					<td><select id="edit_payMoneyTime" style="width: 150px;" >
							<option value="">请选择</option>
							<option value="AllTime">总计</option>
							<option value="ThisYear">当年</option>
							<option value="ThisMonth">当月</option>
							<option value="ThisWeek">当周</option>
							<option value="ThisDay">当天</option>
						</select></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
					<td>充值类型：</td>
					<td><div id="edit_payMoneyType">
					</div></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
				</tr>
				<tr>
					<td>充值金额：</td>
					<td><input type="text" id="edit_payMoneyLimit" style="width: 145px;" /></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
					<td>中奖概率：</td>
					<td><input type="text" id="edit_rate" style="width: 145px;" /></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
				</tr>
				<tr>
					<td>中出时间：</td>
					<td><select id="edit_frequency" style="width: 150px;" >
							<option value="">请选择</option>
							<option value="PerYear">每年</option>
							<option value="PerMonth">每月</option>
							<option value="PerWeek">每周</option>
							<option value="PerDay">每天</option>
						</select>
					</td>
					<td><font>&nbsp;*&nbsp;选填</font></td>
					<td>中奖次数：</td>
					<td><input type="text" id="edit_frequencyNumber" style="width: 145px;" /></td>
					<td><font>&nbsp;*&nbsp;选填</font></td>
				</tr>
				<tr>
					<td>同用户中奖时间：</td>
					<td><select id="edit_userFrequency" style="width: 150px;" >
							<option value="">请选择</option>
							<option value="PerYear">每年</option>
							<option value="PerMonth">每月</option>
							<option value="PerWeek">每周</option>
							<option value="PerDay">每天</option>
						</select>
					</td>
					<td><font>&nbsp;*&nbsp;选填</font></td>
					<td>用户中奖次数：</td>
					<td><input type="text" id="edit_userFrequencyNumber" style="width: 145px;" /></td>
					<td><font>&nbsp;*&nbsp;选填</font></td>	
				</tr>
				<tr>
					<td>提示信息：</td>
					<td><input type="text" id="edit_show" style="width: 145px;" /></td>
					<td><font>&nbsp;*&nbsp;选填</font></td>	
					<td>发送短信：</td>
					<td><select id="edit_needMessage" style="width: 150px;" >
						<option value="no">no</option>
						<option value="yes">yes</option>
						</select></td>
					<td><font>&nbsp;*&nbsp;选填</font></td>
				</tr>
				<tr>	
					<td>需要验证码：</td>
					<td><select id="edit_needCode" style="width: 150px;" >
						<option value="no">no</option>
						<option value="yes">yes</option>
					</select></td>
					<td><font>&nbsp;*&nbsp;选填</font></td>
					<td>1：N ：</td>
					<td><input type="text" id="edit_oneToMany" style="width: 145px;" /></td>
					<td><font>&nbsp;*&nbsp;选填</font></td>		
				</tr>
			</table>
		</div>
		<div align="center" style="margin-top:10px;margin-bottom:10px;">
			<input type="button" value="修改" id="updateSweepStakesPrize" />
		</div>
	</div>
	
	
</body>
</html>