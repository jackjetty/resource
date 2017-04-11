<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>促销信息管理</title>
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
#dlg td{
	border-bottom:1px solid #CCCCCC;
	background-color:#F5F7F7;
}
#edit-dialog td{
	border-bottom:1px solid #CCCCCC;
	background-color:#F5F7F7;
}
-->
</style>
</head>
<body style="background: #DFE8F6;">
	<input type="hidden" id="editMenuId" />
	<div id="searchpanel"class="easyui-panel panel-margin-buttom" title="促销信息查询"
		style="height: 80px; padding: 10px;overflow: hidden;">	
		<table>
			<tr style="Valign: center; text-decoration: none; line-heigth: 30px; height: 30px; margin-left: 50px;">
				<td>活动名称：</td>
				<td><input type="text" id="actName" style="width: 95px;" /></td>
				<td>开始时间：</td>
				<td><input type="text" id="actStartTime" class="easyui-datebox" editable="false" style="width: 95px;" /></td>
				<td>结束时间：</td>
				<td><input type="text" id="actEndTime" class="easyui-datebox" editable="false" style="width: 95px;" /></td>
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
	<table id="dg" class="easyui-datagrid" title="促销信息列表"
		data-options="rownumbers:true,singleSelect:false,toolbar:toolbar,pagination:true,
				pageSize:10,remoteSort:false,fitColumns:true,
				multiSort:true">
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field:'productId',width:80">产品ID</th>
				<!-- <th data-options="field:'merchantId',width:100,sortable:true">商业ID</th> 
				<th data-options="field:'actTopic',width:100">活动主题</th>-->
				<th data-options="field:'actName',width:100">活动名称</th>
				<th data-options="field:'actContent',width:140">活动内容</th>
				<th data-options="field:'actStartTime',width:100">开始时间</th>
				<th data-options="field:'actEndTime',width:100">结束时间</th>
				<th data-options="field:'sendCost',width:100">活动奖品</th>
				<th data-options="field:'sortCode',width:100,sortable:true">排序编码</th>
				<th data-options="field:'imgName',width:100">图片名称</th>
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
				$('#dlg').dialog('open');
				$("#add_busId").empty();
				$("#add_merchantId").empty();
				$("#add_productId").empty();
				$("#add_actName").val('');
				$("#add_actTopic").val('');
				$("#add_actContent").val('');
				$("#add_actStartTime").datebox('setValue','');
				$("#add_actEndTime").datebox('setValue','');
				$("#add_sortCode").val('');
				$("#add_disCount").val('');
				$("#add_sendCost").val('');
				$("#add_open").val('');
				$("#add_image1").val('');
				$("#add_image2").val('');
				$("#add_image3").val('');
				$("#add_imgName").val('');
				$("#add_busId").append("<option value=''>请选择</option>");
				$.ajax({
					url : "getBusAndBtype.action",
					data : "",
					dataType : "json",
					type : "post",
					success : function(data) {
						var busId=data.busId.split(",");
						var Btype=data.Btype.split(",");
						for(var i=0;i<busId.length-1;i++){
							var option="<option value='"+busId[i]+"'>"+Btype[i]+"</option>";
							$("#add_busId").append(option);
						}
					},
					error : function(data) {
						alert("error 后台出现错误！");
					}
				});
				//获得merchantId
				$("#add_merchantId").append("<option value=''>请选择</option>");
				$.ajax({
					url : "getMerchant.action",
					data : "",
					dataType : "json",
					type : "post",
					success : function(data) {
						var merchantId=data.merchantId.split(",");
						var merchantName=data.merchantName.split(",");
						for(var i=0;i<merchantId.length-1;i++){
							var option="<option value='"+merchantId[i]+"'>"+merchantName[i]+"</option>";
							$("#add_merchantId").append(option);
						}
					},
					error : function(data) {
						alert("error 后台出现错误！");
					}
				});
				//获得productId
				$("#add_productId").append("<option value=''>请选择</option>");
				$.ajax({
					url : "getProductByStatus.action",
					data : "",
					dataType : "json",
					type : "post",
					success : function(data) {
						var productId=data.productId.split(",");
						var pn=data.pn.split(",");
						for(var i=0;i<productId.length-1;i++){
							var option="<option value='"+productId[i]+"'>"+pn[i]+"</option>";
							$("#add_productId").append(option);
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
					$("#edit_busId").empty();
					$("#edit_merchantId").empty();
					$("#edit_productId").empty();
					$.ajax({
						url : "getBusAndBtype.action",
						data : "",
						dataType : "json",
						type : "post",
						success : function(data) {
							var busId=data.busId.split(",");
							var Btype=data.Btype.split(",");
							for(var i=0;i<busId.length-1;i++){
								if(busId[i]==row.busId){
									var option="<option value='"+busId[i]+"' selected='true'>"+Btype[i]+"</option>"
								}else{
									var option="<option value='"+busId[i]+"'>"+Btype[i]+"</option>";
								}
								$("#edit_busId").append(option);
							}
						},
						error : function(data) {
							alert("error 后台出现错误！");
						}
					});
					//获得merchantId
					$.ajax({
						url : "getMerchant.action",
						data : "",
						dataType : "json",
						type : "post",
						success : function(data) {
							var merchantId=data.merchantId.split(",");
							var merchantName=data.merchantName.split(",");
							for(var i=0;i<merchantId.length-1;i++){
								if(merchantId[i]==row.merchantId){
									var option="<option value='"+merchantId[i]+"' selected='true'>"+merchantName[i]+"</option>"
								}else{
									var option="<option value='"+merchantId[i]+"'>"+merchantName[i]+"</option>";
								}
								$("#edit_merchantId").append(option);
							}
						},
						error : function(data) {
							alert("error 后台出现错误！");
						}
					});
					//获得productId
					$("#edit_productId").append("<option value=''>请选择</option>");
					$.ajax({
						url : "getProductByStatus.action",
						data : "",
						dataType : "json",
						type : "post",
						success : function(data) {
							var productId=data.productId.split(",");
							var pn=data.pn.split(",");
							for(var i=0;i<productId.length-1;i++){
								if(productId[i]==row.productId){
									var option="<option value='"+productId[i]+"' selected='true'>"+pn[i]+"</option>";
								}else{
									var option="<option value='"+productId[i]+"'>"+pn[i]+"</option>";
								}
								
								$("#edit_productId").append(option);
							}
						},
						error : function(data) {
							alert("error 后台出现错误！");
						}
					});
					$("#edit_actName").val(row.actName);
					$("#edit_actTopic").val(row.actTopic);
					$("#edit_actContent").val(row.actContent);
					$("#edit_actStartTime").datebox('setValue',row.actStartTime);
					$("#edit_actEndTime").datebox('setValue',row.actEndTime);
					$("#edit_sortCode").val(row.sortCode);
					$("#edit_disCount").val(row.disCount);
					$("#edit_sendCost").val(row.sendCost);
					$("#edit_open").val(row.open);
					$("#edit_imgName").val(row.imgName.split("_")[0]);
					$("#edit_inforId").val(row.inforId);
				}
			}
		}, '-', {
			text : '删除',
			iconCls : 'icon-remove',
			handler : function() {
				var rows = $('#dg').datagrid('getSelections');
				if(window.confirm("确认删除这"+rows.length+"记录？")){
					var inforIds = '';
					for(var i=0;i<rows.length;i++){
						inforIds = inforIds + ","+rows[i].inforId;
					}
					$.ajax({
						url : "removeSalesInformation.action",
						data : "inforIds="+ inforIds,
						dataType : "json",
						type : "post",
						success : function(data) {
							alert(data.respInfo);
							var actName=$("#actName").val();
							var actStartTime=$("#actStartTime").datebox('getValue');
							var actEndTime=$("#actEndTime").datebox('getValue');
							$('#dg').datagrid({
								loadFilter : pagerFilter,
							}).datagrid('loadData', getData(actName, actStartTime, actEndTime));
						},
						error : function(data) {
							alert("error 后台出现错误！");
						}
					});
					
				}else{
					return false;
				}
			}
		}, '-', {
			text : '更换位置',
			iconCls : 'icon-reload',
			handler : function() {
				var rows = $('#dg').datagrid('getSelections');
				if(rows.length==2){
					if(window.confirm("确定更换这"+rows.length+"条记录？")){
						var sale1=rows[0].inforId+","+rows[0].sortCode;
						var sale2=rows[1].inforId+","+rows[1].sortCode;
						$.ajax({
							url : "changeSortCode.action",
							data : "sale1="+ sale1+"&sale2="+sale2,
							dataType : "json",
							type : "post",
							success : function(data) {
								alert(data.respInfo);
								var actName=$("#actName").val();
								var actStartTime=$("#actStartTime").datebox('getValue');
								var actEndTime=$("#actEndTime").datebox('getValue');
								$('#dg').datagrid({
									loadFilter : pagerFilter,
								}).datagrid('loadData', getData(actName, actStartTime, actEndTime));
							},
							error : function(data) {
								alert("error 后台出现错误！");
							}
						});
					}else{
						return false;
					}
				}else{
					alert("请选择要更换的2条记录");
					return false;
				}
			}
		}];
		
		function getData(actName, actStartTime, actEndTime) {
			var rows = null;
			var dg = $("#dg");
			var opts = dg.datagrid('options');
			var pageSize = opts.pageSize;
			var pageIndex = opts.pageNumber;
			$.ajax({
				url : "getSalesInformation.action",
				async : false,
				data : "pageSize=" + pageSize + "&pageIndex=" + pageIndex
						+ "&actName=" + actName + "&actStartTime=" + actStartTime+"&actEndTime="+actEndTime,
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
				var actName = $("#actName").val().trim();
				var actStartTime = $("#actStartTime").datebox('getValue');
				var actEndTime = $("#actEndTime").datebox('getValue');
				var newData = getData(actName, actStartTime, actEndTime);
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
			var actName = $("#actName").val().trim();
			var actStartTime = $("#actStartTime").datebox('getValue');
			var actEndTime = $("#actEndTime").datebox('getValue');
			$('#dg').datagrid({
				loadFilter : pagerFilter,
				width:width-18,
				height:height-120
			}).datagrid('loadData', getData(actName, actStartTime, actEndTime));
			$("#searchpanel").panel({width:width-18});
			$("#search").click(function() {
				actName = $("#actName").val().trim();
				actStartTime =  $("#actStartTime").datebox('getValue');
				actEndTime = $("#actEndTime").datebox('getValue');
				$('#dg').datagrid({
					loadFilter : pagerFilter,
					pageNumber : 1
				}).datagrid('loadData', getData(actName, actStartTime, actEndTime));
			});
			$("#clear").click(function(){
				$("#actName").val('');
				$("#actStartTime").datebox('setValue','');
				$("#actEndTime").datebox('setValue','');
				
			});
			
			$("#addSale").click(
					function() {
						var busId = $("#add_busId").val();
						var merchantId = $("#add_merchantId").val();
						var productId=$("#add_productId").val();
						var actName=$("#add_actName").val();
						var actTopic=$("#add_actTopic").val();
						var actContent=$("#add_actContent").val();
						var actStartTime=$("#add_actStartTime").datebox('getValue');
						var actEndTime=$("#add_actEndTime").datebox('getValue');
						var disCount=$("#add_disCount").val();
						var sendCost=$("#add_sendCost").val();
						var open=$("#add_open").val();
						var image1=$("#add_image1").val();
						var image2=$("#add_image2").val();
						var image3=$("#add_image3").val();
						var imgName=$("#add_imgName").val();
						if (busId == '') {alert('请选择金币类型！'); return false;}
						if(merchantId==''){alert('请选择商业ID'); return false;}
						if(actName==''){alert('请输入活动名称'); return false;}
						if(actTopic==''){alert('请输入活动主题'); return false;}
						if(actContent==''){alert('请输入活动内容'); return false;}
						if(actStartTime==''){alert('请输入开始时间'); return false;}
						if(actEndTime==''){alert('请输入结束时间'); return false;}
						if(image1==''){alert('请选择图片(320像素)'); return false;}
						if(image2==''){alert('请选择图片(480像素)'); return false;}
						if(image3==''){alert('请选择图片(720像素)'); return false;}
						if(imgName==''){alert('请输入图片名称'); return false;}
						if(disCount.length>0){
							var re = /^(-?\d+)(\.\d+)$/;
							if(!re.test(disCount)){
								alert("请输入正确的折扣(0-1)如0.75");
								return false;
							}else 
								if(!(parseFloat(disCount)>=0 && parseFloat(disCount)<=1)){
								alert("请输入正确的折扣0-1)如0.75");
								return false;
							}
						}
						var options = {
							dataType : "json",
							error : function() {
								alert("后台错误！");
							},
							success : function(data) { 
								alert(data.respInfo);
								if (data.respCode == 0) {
									$("#dlg").dialog("close");
									actName=$("#actName").val();
									actStartTime=$("#actStartTime").datebox('getValue');
									actEndTime=$("#actEndTime").datebox('getValue');
									$('#dg').datagrid({
										loadFilter : pagerFilter,
									}).datagrid('loadData', getData(actName, actStartTime, actEndTime));
								}
							}
						};
						$("#addForm").ajaxSubmit(options);
					});
					
			$("#updateSale").click(function(){
				var busId = $("#edit_busId").val();
				var merchantId = $("#edit_merchantId").val();
				var actName=$("#edit_actName").val();
				var actTopic=$("#edit_actTopic").val();
				var actContent=$("#edit_actContent").val();
				var actStartTime=$("#edit_actStartTime").datebox('getValue');
				var actEndTime=$("#edit_actEndTime").datebox('getValue');
				var disCount=$("#edit_disCount").val();
				var imgName=$("#edit_imgName").val();
				var sortCode=$("#edit_sortCode").val();
				if (busId == '') {alert('请选择金币类型！'); return false;}
				if(merchantId==''){alert('请选择商业ID'); return false;}
				if(actName==''){alert('请输入活动名称'); return false;}
				if(actTopic==''){alert('请输入活动主题'); return false;}
				if(actContent==''){alert('请输入活动内容'); return false;}
				if(actStartTime==''){alert('请输入开始时间'); return false;}
				if(actEndTime==''){alert('请输入结束时间'); return false;}
				if(imgName==''){alert('请输入图片名称'); return false;}
				if(disCount.length>0){
					var re = /^(-?\d+)(\.\d+)$/;
					if(!re.test(disCount)){
						alert("请输入正确的折扣(0-1)如0.75");
						return false;
					}else 
						if(!(parseFloat(disCount)>=0 && parseFloat(disCount)<=1)){
						alert("请输入正确的折扣0-1)如0.75");
						return false;
					}
				}
				var options = {
						dataType : "json",
						error : function() {
							alert("后台错误！");
						},
						success : function(data) { 
							alert(data.respInfo);
							if (data.respCode == 0) {
								$("#edit-dialog").dialog("close");
								actName=$("#actName").val();
								actStartTime=$("#actStartTime").datebox('getValue');
								actEndTime=$("#actEndTime").datebox('getValue');
								$('#dg').datagrid({
									loadFilter : pagerFilter,
								}).datagrid('loadData', getData(actName, actStartTime, actEndTime));
							}
						}
					};
					$("#updateForm").ajaxSubmit(options);
			});
				
		});

	</script>
	
	<div id="dlg" class="easyui-dialog" title="添加促销活动"
		data-options="iconCls:'icon-save',closed:true"
		style="width: 640px; height: 410px; padding: 10px;background-color:white;">
		<div id="div1" style="margin-top:10px;">
		<form action="addSalesInformation.action" method="post" enctype="multipart/form-data" id="addForm">
			<table  style="text-align:right;border:1px solid #CCCCCC; " cellspacing="0px" align="center">
				<tr >
					<td>金币类型：</td>
					<td style="text-align:left;"><select id="add_busId" name="busId" style="width: 155px;"></select></td>
					<td style="text-align:left;border-right:1px solid #CCCCCC; width:50px;"><font color="red">*&nbsp;必填</font></td>
					<td style=" width:80px;">活动名称：</td>
					<td style="text-align:left;"><input type="text" id="add_actName" name="actName" style="width: 150px;"/></td>
					<td style="text-align:left;"><font color="red">*&nbsp;必填</font></td>
				</tr>
				<tr>
					<td>商业&nbsp;I&nbsp;D：</td>
					<td style="text-align:left;"><select id="add_merchantId" name="merchantId" style="width: 155px;"></select></td>
					<td style="text-align:left; border-right:1px solid #CCCCCC;"><font color="red">*&nbsp;必填</font></td>
					<td>活动主题：</td>
					<td style="text-align:left;"><input type="text" id="add_actTopic" name="actTopic" style="width: 150px;"/></td>
					<td style="text-align:left;"><font color="red">*&nbsp;必填</font></td>	
				</tr>
				<tr>
					<td>活动内容：</td>
					<td style="text-align:left;"><input type="text" id="add_actContent" name="actContent" style="width: 150px;"/></td>
					<td style="text-align:left;border-right:1px solid #CCCCCC;"><font color="red">*&nbsp;必填</font></td>
					<td>活动类型：</td>
					<td style="text-align:left;"><select id="add_productId" name="productId" style="width: 155px;"></select></td>
					<td></td>
				</tr>
				<tr>
					<td>开始时间：</td>
					<td style="text-align:left;"><input type="text" id="add_actStartTime" name="actStartTime" class="easyui-datebox" editable="false" /></td>
					<td style="text-align:left;border-right:1px solid #CCCCCC;"><font color="red">*&nbsp;必填</font></td>
					<td>活动奖励：</td>
					<td style="text-align:left;"><input type="text" id="add_sendCost" name="sendCost" style="width: 150px;"/></td>
					<td></td>
				</tr>
				<tr>
					<td>结束时间：</td>
					<td style="text-align:left;"><input type="text" id="add_actEndTime" name="actEndTime" style="width: 155px;" class="easyui-datebox" editable="false"/></td>
					<td style="text-align:left;border-right:1px solid #CCCCCC;"><font color="red">*&nbsp;必填</font></td>
					<td>活动折扣：</td>
					<td style="text-align:left;"><input type="text" id="add_disCount" name="disCount" style="width: 150px;"/></td>
					<td></td>
				</tr>
				<tr>
					<td>活动图片1(320)：</td>
					<td style="text-align:left;"><input type="file" id="add_image1" name="image1" style="width:155px;"/></td>
					<td style="text-align:left;border-right:1px solid #CCCCCC;"><font color="red">*&nbsp;必填</font></td>
					<td>活动方式：</td>
					<td style="text-align:left;"><input type="text" id="add_open" name="open" style="width: 150px;"/></td>
					<td></td>
				</tr>
				<tr>
					<td>活动图片2(480)：</td>
					<td style="text-align:left;"><input type="file" id="add_image2" name="image2" style="width:155px;"/></td>
					<td style="text-align:left;border-right:1px solid #CCCCCC;"><font color="red">*&nbsp;必填</font></td>
					<td>图片名称：</td>
					<td style="text-align:left;"><input type="text" id="add_imgName" name="imgName" style="width: 150px;"/></td>
					<td style="text-align:left;"><font color="red">*&nbsp;必填</font></td>
				</tr>
				<tr>
					<td style="border-bottom:0px;">活动图片3(720)：</td>
					<td style="text-align:left;border-bottom:0px;"><input type="file" id="add_image3" name="image3" style="width:155px;"/></td>
					<td style="text-align:left;border-right:1px solid #CCCCCC;border-bottom:0px;"><font color="red">*&nbsp;必填</font></td>
				</tr>
			</table>
			</form>
		</div>
		<div align="center">
			<input type="button" value="添加" id="addSale" style="margin-top:10px;" />
		</div>
	</div>
	
	<div id="edit-dialog" class="easyui-dialog" title="修改促销信息"
		data-options="iconCls:'icon-edit',closed:true"
		style="width: 640px; height: 410px; padding: 10px; background-color:white;">
		<div id="div2" style="margin-top:10px;" >
			<form action="updateSalesInformation.action" method="post" enctype="multipart/form-data" id="updateForm">
			<input type="hidden" id="edit_inforId" name="inforId" value="" />
			<input type="hidden" id="edit_sortCode" name="sortCode" style="width: 150px;"/>
			<table cellspacing="0px" style="border:1px solid #CCCCCC;text-align:right;" align="center">
				<tr>
					<td>金币类型：</td>
					<td style="text-align:left;"><select id="edit_busId" name="busId" style="width: 155px;"></select></td>
					<td style="text-align:left; width:50px;border-right:1px solid #CCCCCC;"><font color="red">*&nbsp;必填</font></td>
					<td style=" width:80px;">活动名称：</td>
					<td style="text-align:left;"><input type="text" id="edit_actName" name="actName" style="width: 150px;"/></td>
					<td style="text-align:left;"><font color="red">*&nbsp;必填</font></td>
				</tr>
				<tr>
					<td>商业&nbsp;I&nbsp;D：</td>
					<td style="text-align:left;"><select id="edit_merchantId" name="merchantId" style="width: 155px;"></select></td>
					<td style="text-align:left;border-right:1px solid #CCCCCC;"><font color="red">*&nbsp;必填</font></td>
					<td>活动主题：</td>
					<td style="text-align:left;"><input type="text" id="edit_actTopic" name="actTopic" style="width: 150px;"/></td>
					<td style="text-align:left;"><font color="red">*&nbsp;必填</font></td>
				</tr>
				<tr>
					<td>活动内容：</td>
					<td style="text-align:left;"><input type="text" id="edit_actContent" name="actContent" style="width: 150px;"/></td>
					<td style="text-align:left;border-right:1px solid #CCCCCC;"><font color="red">*&nbsp;必填</font></td>
					<td>活动类型：</td>
					<td style="text-align:left;"><select id="edit_productId" name="productId" style="width: 155px;"></select></td>
					<td></td>
				</tr>
				 <tr>
					<td>开始时间：</td>
					<td style="text-align:left;"><input type="text" id="edit_actStartTime" name="actStartTime" class="easyui-datebox" editable="false" /></td>
					<td style="text-align:left;border-right:1px solid #CCCCCC;"><font color="red">*&nbsp;必填</font></td>
					<td>活动奖励：</td>
					<td style="text-align:left;"><input type="text" id="edit_sendCost" name="sendCost" style="width: 150px;"/></td>
					<td></td>
				</tr>
				<tr>
					<td>结束时间：</td>
					<td style="text-align:left;"><input type="text" id="edit_actEndTime" name="actEndTime" style="width: 155px;" class="easyui-datebox" editable="false"/></td>
					<td style="text-align:left;border-right:1px solid #CCCCCC;"><font color="red">*&nbsp;必填</font></td>
					<td>活动折扣：</td>
					<td style="text-align:left;"><input type="text" id="edit_disCount" name="disCount" style="width: 150px;"/></td>
					<td></td>
				</tr>
				<tr>
					<td>活动图片1(320)：</td>
					<td style="text-align:left;"><input type="file" id="add_image1" name="image1" style="width:155px;"/></td>
					<td style="text-align:left;border-right:1px solid #CCCCCC;"><font color="red">*&nbsp;必填</font></td>
					<td>活动方式：</td>
					<td style="text-align:left;"><input type="text" id="edit_open" name="open" style="width: 150px;"/></td>
					<td></td>
				</tr>
				<tr>
					<td>活动图片2(480)：</td>
					<td style="text-align:left;"><input type="file" id="add_image2" name="image2" style="width:155px;"/></td>
					<td style="text-align:left;border-right:1px solid #CCCCCC;"><font color="red">*&nbsp;必填</font></td>
					<td>图片名称：</td>
					<td style="text-align:left;"><input type="text" id="edit_imgName" name="imgName" style="width: 150px;"/></td>
					<td style="text-align:left;"><font color="red">*&nbsp;必填</font></td>
				</tr>
				<tr>
					<td>活动图片3(720)：</td>
					<td style="text-align:left;"><input type="file" id="add_image3" name="image3" style="width:155px;"/></td>
					<td style="text-align:left;border-right:1px solid #CCCCCC;"><font color="red">*&nbsp;必填</font></td>
				</tr>
			</table>
			</form>
		</div>
		<div align="center">
			<input type="button" value="更新" id="updateSale" style="margin-top:10px;"/>
		</div>
	</div>
	
</body>
</html>