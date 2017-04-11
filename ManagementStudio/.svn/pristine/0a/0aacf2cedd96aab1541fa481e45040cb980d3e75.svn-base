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
}
#div2 td{
	height: 35px;
}
-->
</style>
</head>
<body style="background: #DFE8F6;">
	<div id="searchpanel" class="easyui-panel panel-margin-buttom"
		title="限额种类查询" style="height: 80px; padding: 10px;overflow: hidden;">
		<table height="100%">
			<tr	style="Valign: center; text-decoration: none; line-heigth: 30px; height: 30px; margin-left: 50px;">
				<td>限额名称：</td>
				<td><select id="limitId" style="width: 95px;" ></select></td>
				<td>业务名称：</td>
				<td><select id="busId" style="width: 95px;"></select></td>
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
	<table id="dg" class="easyui-datagrid" title="限额种类列表"
		data-options="rownumbers:true,singleSelect:false,toolbar:toolbar,pagination:true,
				pageSize:20,remoteSort:false,fitColumns:true,
				multiSort:true">
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<!-- <th data-options="field:'id',width:100,sortable:true">编号</th> -->
				<th data-options="field:'limitName',width:180,hide:'true'">限额名称</th>
				<th data-options="field:'busName',width:150,align:'center'">业务名称</th>
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
				$("#add_limitId").empty();
				$.ajax({
					url : "getLimitAndBusInfo.action",
					data : "",
					dataType : "json",
					type : "post",
					success : function(data) {
						var busId=data.busId.split(",");
						var busName=data.Btype.split(",");
						var limitId=data.limitId.split(",");
						var limitName=data.limitName.split(",");
						$("#add_busId").append("<option value='null' selected='true'>请选择</option>");
						for(var i=0;i<busId.length-1;i++){
							var option="<option value='"+busId[i]+"'>"+busName[i]+"</option>";
							$("#add_busId").append(option);
						}
						$("#add_limitId").append("<option value='null' selected='true'>请选择</option>");
						for(var i=0;i<limitId.length-1;i++){
							var option="<option value='"+limitId[i]+"'>"+limitName[i]+"</option>";
							$("#add_limitId").append(option);
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
				} else {
					var row = $("#dg").datagrid('getSelected');
					$("#edit_id").val(row.id);
					$('#edit-dialog').dialog('open');
					$("#edit_busId").empty();
					$("#edit_limitId").empty();
					$.ajax({
						url : "getLimitAndBusInfo.action",
						data : "",
						dataType : "json",
						type : "post",
						success : function(data) {
							var busId=data.busId.split(",");
							var busName=data.Btype.split(",");
							var limitId=data.limitId.split(",");
							var limitName=data.limitName.split(",");
							for(var i=0;i<busId.length-1;i++){
								var option;
								if(row.busName==busName[i]){
									option="<option value='"+busId[i]+"' selected='true' >"+busName[i]+"</option>";
								}else{
									option="<option value='"+busId[i]+"'>"+busName[i]+"</option>";
								}
								
								$("#edit_busId").append(option);
							}
							
							for(var i=0;i<limitId.length-1;i++){
								var option;
								if(row.limitName==limitName[i]){
									option="<option value='"+limitId[i]+"' selected='true' >"+limitName[i]+"</option>";
								}else{
									option="<option value='"+limitId[i]+"'>"+limitName[i]+"</option>";
								}
								
								$("#edit_limitId").append(option);
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
				if(window.confirm("确认删除这"+rows.length+"记录？")){
					var ids = '';
					for(var i=0;i<rows.length;i++){
						ids = ids + ","+rows[i].id;
					}
					$.ajax({
						url : "removeLimitToBus.action",
						data : "ids="+ ids,
						dataType : "json",
						type : "post",
						success : function(data) {
							alert(data.respInfo);
							limitId = $("#limitId").val();
							busId = $("#busId").val();
							$('#dg').datagrid({
								loadFilter : pagerFilter
							}).datagrid('loadData', getData(limitId, busId));
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
						onSelectPage : function(pageNum,pageSize) {
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
				var limitId = $("#limitId").val();
				var busId = $("#busId").val();
				var newData = getData(limitId,busId);
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
		function getData(limitId,busId) {
			var rows = null;
			var dg = $("#dg");
			var opts = dg.datagrid('options');
			var pageSize = opts.pageSize;
			var pageIndex = opts.pageNumber;
			$.ajax({
				url : "getLimitToBusiness.action",
				async : false,
				data : "pageSize=" + pageSize + "&pageIndex=" + pageIndex
						+ "&limitId=" + limitId + "&busId=" + busId,
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
			$.ajax({
				url : "getLimitAndBusInfo.action",
				data : "",
				dataType : "json",
				type : "post",
				success : function(data) {
					var busId=data.busId.split(",");
					var busName=data.Btype.split(",");
					var limitId=data.limitId.split(",");
					var limitName=data.limitName.split(",");
					$("#busId").append("<option id='busid1' value='null'></option>");
					for(var i=0;i<busId.length-1;i++){
						var option="<option value='"+busId[i]+"'>"+busName[i]+"</option>";
						$("#busId").append(option);
					}
					$("#limitId").append("<option id='limitid1' value='null'></option>");
					for(var i=0;i<limitId.length-1;i++){
						var option="<option value='"+limitId[i]+"'>"+limitName[i]+"</option>";
						$("#limitId").append(option);
					}
				},
				error : function(data) {
					alert("error 后台出现错误！");
				}
			});
			
			var width = $(document).width();
			var height = $(document).height();
			var limitId = $("#limitId").val();
			var busId = $("#busId").val();
			$('#dg').datagrid({
				loadFilter : pagerFilter,
				width:width-18,
				height:height-120
			}).datagrid('loadData', getData(limitId,busId));
			
			$("#searchpanel").panel({width:width-18});
			$("#search").click(function() {
				limitId = $("#limitId").val();
				busId = $("#busId").val();
				$('#dg').datagrid({
					loadFilter : pagerFilter,
					pageNumber : 1
				}).datagrid('loadData', getData(limitId,busId));
			});
			
			$("#clear").click(function(){
				$("#busid1").attr('selected',true);
				$("#limitid1").attr('selected',true);
			});
			
			$("#addLimitToBus").click(function(){
				var limitId = $("#add_limitId").val();
				var busId = $("#add_busId").val();
				if(limitId=='null'){
					alert("请选择限额名称");
					return false;
				}
				if(busId=='null'){
					alert("请选择业务名称");
					return false;
				}
				$.ajax({
					url : "addLimitToBus.action",
					data : "limitId=" + limitId + "&busId=" + busId,
					dataType : "json",
					type : "post",
					success : function(data) {
						alert(data.respInfo);
						$('#dlg').dialog('close');
						limitId = $("#limitId").val();
						busId = $("#busId").val();
						$('#dg').datagrid({
							loadFilter : pagerFilter
						}).datagrid('loadData', getData(limitId, busId));
						
					},
					error : function(data) {
						alert("error 后台出现错误！");
					}
				});
				
			});
			
			$("#updateLimitToBus").click(function(){
				var limitId = $("#edit_limitId").val();
				var busId = $("#edit_busId").val();
				var id = $("#edit_id").val();
				$.ajax({
					url : "updateLimitToBus.action",
					data : "limitId=" + limitId + "&busId=" + busId+"&id="+id,
					dataType : "json",
					type : "post",
					success : function(data) {
						alert(data.respInfo);
						$('#edit-dialog').dialog('close');
						limitId = $("#limitId").val();
						busId = $("#busId").val();
						$('#dg').datagrid({
							loadFilter : pagerFilter
						}).datagrid('loadData', getData(limitId, busId));
						
					},
					error : function(data) {
						alert("error 后台出现错误！");
					}
				});
			});
			
		});
	</script>
	
	<div id="dlg" class="easyui-dialog" title="添加限额信息"
		data-options="iconCls:'icon-save',closed:true"
		style="width: 400px; height: 200px; padding: 10px; background: #DFE8F6;">
		<div id="div1" style="margin-left:10px;margin-top:10px;">
			<table align="center">
				<tr>
					<td>限额名称：</td>
					<td><select id="add_limitId" style="width: 150px;" >
					</select></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
				</tr>
				<tr>
					<td>业务名称：</td>
					<td><select id="add_busId" style="width: 150px;" >
					</select></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
				</tr>
				
			</table>
		</div>
		<div align="center" style="margin-top:20px;">
			<input type="button" value="添加" id="addLimitToBus" />
		</div>
	</div>
	
	
	<div id="edit-dialog" class="easyui-dialog" title="修改限额信息"
		data-options="iconCls:'icon-save',closed:true"
		style="width: 400px; height: 200px; padding: 10px; background: #DFE8F6;">
		<div id="div2" style="margin-left:10px;margin-top:20px;">
			<input type="hidden" id="edit_id" />
			<table align="center">
				<tr>
					<td>限额名称：</td>
					<td><select id="edit_limitId" style="width: 150px;" >
					</select></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
				</tr>
				<tr>
					<td>业务名称：</td>
					<td><select id="edit_busId" style="width: 150px;" ></select></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
				</tr>
				
			</table>
		</div>
		<div align="center" style="margin-top:10px;">
			<input type="button" value="修改" id="updateLimitToBus" />
		</div>
	</div>
	
	
</body>
</html>