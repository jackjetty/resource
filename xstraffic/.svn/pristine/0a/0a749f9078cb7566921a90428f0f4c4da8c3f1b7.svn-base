<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=8" />
<title>添加功能菜单</title>
<link rel="stylesheet" type="text/css" href="themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="themes/icon.css">
<link rel="stylesheet" href="themes/default/default.css" />
<script charset="utf-8" src="js/kindeditor-min.js"></script>
<script charset="utf-8" src="js/zh_CN.js"></script>
<script type="text/javascript" src="js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
	
	function getData(organizationName) {
		var rows = null;
		var dg = $("#dg");
		var opts = dg.datagrid('options');
		var pageSize = opts.pageSize;
		var pageIndex = opts.pageNumber;
		$.ajax({
			url : "getOrganizationInfo.action",
			async : false,
			data : "pageSize=" + pageSize + "&pageIndex=" + pageIndex
					+ "&organizationName=" + organizationName,
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
			var organizationName=$("#organizationName").val();
			var newData = getData(organizationName);
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
		var organizationName=$("#organizationName").val();
		$('#dg').datagrid({
			loadFilter : pagerFilter,
			width : width - 20,
			height : height - 45,
		}).datagrid('loadData', getData(organizationName));
		$("#searchpanel").panel({
			width : width - 20
		});
		$("#search").click(function() {
			var organizationName=$("#organizationName").val();
			$('#dg').datagrid({
				loadFilter : pagerFilter,
				pageNumber : 1,
			}).datagrid('loadData', getData(organizationName));

		});
		$("#clear").click(function(){
			$("#organizationName").val('');
		});
		
		$("#addOrganizationInfo").click(function() {
			var organizationName=$("#add_organizationName").val();
			if(organizationName==''){
				alert("请填写机构名称");
				return false;
			}
			var address=$("#add_address").val();
			if (address == '') {
				alert("请填写机构地址");
				return false;
			}
			var alarmPhone=$("#add_alarmPhone").val();
			var complaintPhone=$("#add_complaintPhone").val();
			$.ajax({
				url : "addOrganizationInfo.action",
				data : "organizationName=" + organizationName + "&address=" + address +"&alarmPhone="+alarmPhone
						+"&complaintPhone="+complaintPhone,
				dataType : "json",
				type : "post",
				success : function(data) {
					if(data.respCode==0){
						alert(data.respInfo);
						$('#dlg').dialog('close');
						var organizationName=$("#organizationName").val();
						$('#dg').datagrid({
							loadFilter : pagerFilter
						}).datagrid('loadData', getData(organizationName));
					}else{
						alert(data.respInfo);
					}
					
				},
				error : function(data) {
					alert("error 后台出现错误！");
				}
			});
		});
		
		
		$("#updateOrganizationInfo").click(function() {
			var organizationName=$("#edit_organizationName").val();
			if(organizationName==''){
				alert("请填写机构名称");
				return false;
			}
			var address=$("#edit_address").val();
			if (address == '') {
				alert("请填写机构地址");
				return false;
			}
			var alarmPhone=$("#edit_alarmPhone").val();
			var complaintPhone=$("#edit_complaintPhone").val();
			$.ajax({
				url : "updateOrganizationInfo.action",
				data : "organizationName=" + organizationName + "&address=" + address +"&alarmPhone="+alarmPhone
						+"&complaintPhone="+complaintPhone+"&id="+$("#edit_id").val(),
				dataType : "json",
				type : "post",
				success : function(data) {
					if(data.respCode==0){
						alert(data.respInfo);
						$('#edit-dialog').dialog('close');
						var organizationName=$("#organizationName").val();
						$('#dg').datagrid({
							loadFilter : pagerFilter
						}).datagrid('loadData', getData(organizationName));
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
	
	
	var toolbar = [ {
		text : '添加',
		iconCls : 'icon-add',
		handler : function() {
			$('#dlg').dialog('open');
			$("#add_organizationName").val('');
			$("#add_address").val('');
			$("#add_alarmPhone").val('');
			$("#add_complaintPhone").val('');
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
				$('#edit-dialog').dialog('open');
				$("#edit_id").val(row.id);
				$("#edit_organizationName").val(row.organizationName);
				$("#edit_address").val(row.address);
				$("#edit_alarmPhone").val(row.alarmPhone);
				$("#edit_complaintPhone").val(row.complaintPhone);
				
			}
		}

	}, '-', {
		text : '删除',
		iconCls : 'icon-remove',
		handler : function() {
			var rows = $('#dg').datagrid('getSelections');
			if (rows.length > 0) {
				if (window.confirm("确认删除这" + rows.length + "记录？")) {
					var ids = '';
					for ( var i = 0; i < rows.length; i++) {
						ids = ids + "," + rows[i].id;
					}
					$.ajax({
						url : "removeOrganizationInfo.action",
						data : "ids="+ ids,
						dataType : "json",
						type : "post",
						success : function(data) {
							alert(data.respInfo);
							var organizationName=$("#organizationName").val();
							$('#dg').datagrid({
								loadFilter : pagerFilter
							}).datagrid('loadData', getData(organizationName));
						},
						error : function(data) {
							alert("error 后台出现错误！");
						}
					});
				} else {
					return false;
				}
			} else {
				alert("请选择数据！");
				return false;
			}

		}
	} ];
	
	
	
	
	
	
	
	
	
	

	
</script>
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

#div1 td {
	text-align: center;
	height: 25px;
}

-->
</style>
</head>
<body style="background: #DFE8F6;" id="tt">
	<div id="searchpanel" class="easyui-panel panel-margin-buttom"
		data-options="collapsible:true,collapsed:true" title="查询条件" style="height: 80px; padding: 10px;vertical-align: center;">
		<table height="100%">
			<tr
				style="Valign: center; text-decoration: none; line-heigth: 30px; height: 30px; margin-left: 10px;">
				
				<td>机构名称：</td>
				<td><input type="text" id="organizationName" style="width: 90px;" /></td>
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
	<table id="dg" class="easyui-datagrid" title="信息列表"
		data-options="rownumbers:true,singleSelect:true,toolbar:toolbar,pagination:true,
				pageSize:20,remoteSort:false,fitColumns:true,
				multiSort:true">
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<!-- th data-options="field:'feedBackId',width:150,align:'center'">反馈ID</th> -->
				<th data-options="field:'organizationName',width:150,align:'center'">机构名称</th>
				<th data-options="field:'address',width:150,align:'center'">机构地址</th>
				<th data-options="field:'alarmPhone',width:150,align:'center'">报警电话</th>
				<th data-options="field:'complaintPhone',width:150,align:'center'">联系电话</th>
			</tr>
		</thead>
	</table>
	<div id="dlg" class="easyui-dialog" title="添加信息"
		data-options="iconCls:'icon-save',closed:true"
		style="width: 450px; height: 250px; padding: 5px;">
		<div id="div1" style="margin-left:10px;margin-top:10px;">
			<table align="center" style="width: 400px;">
				
				<tr>
					<td>机构名称：</td>
					<td style="text-align: left;"><input type="text" id="add_organizationName" name="organizationName" style="width: 99%;" /></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
				</tr>
				<tr>
					<td>机构地址：</td>
					<td style="text-align: left;"><input type="text" id="add_address" name="address" style="width: 99%;" /></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
				</tr>
				<tr>
					<td>报警电话：</td>
					<td style="text-align: left;"><input type="text" id="add_alarmPhone" name="alarmPhone" style="width: 99%;" /></td>
					<td><font>&nbsp;*&nbsp;选填</font></td>
				</tr>
				<tr>
					<td>联系电话：</td>
					<td style="text-align: left;"><input type="text" id="add_complaintPhone" name="complaintPhone" style="width: 99%;" /></td>
					<td><font>&nbsp;*&nbsp;选填</font></td>
				</tr>
				
			</table>
		</div>
		<div align="center" style="margin-top:20px;">
			<input type="button" value="完成" id="addOrganizationInfo" />
		</div>
	</div>
	
	
	
	<div id="edit-dialog" class="easyui-dialog" title="修改信息"
		data-options="iconCls:'icon-save',closed:true"
		style="width: 450px; height: 250px; padding: 5px;">
		<div id="div2" style="margin-left:10px;margin-top:10px;">
		<input type="hidden" id="edit_id" name="id" />
			<table align="center" style="width: 400px;">
				
				<tr>
					<td>机构名称：</td>
					<td style="text-align: left;"><input type="text" id="edit_organizationName" name="organizationName" style="width: 99%;" /></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
				</tr>
				<tr>
					<td>机构地址：</td>
					<td style="text-align: left;"><input type="text" id="edit_address" name="address" style="width: 99%;" /></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
				</tr>
				<tr>
					<td>报警电话：</td>
					<td style="text-align: left;"><input type="text" id="edit_alarmPhone" name="alarmPhone" style="width: 99%;" /></td>
					<td><font>&nbsp;*&nbsp;选填</font></td>
				</tr>
				<tr>
					<td>联系电话：</td>
					<td style="text-align: left;"><input type="text" id="edit_complaintPhone" name="complaintPhone" style="width: 99%;" /></td>
					<td><font>&nbsp;*&nbsp;选填</font></td>
				</tr>
				
			</table>
		</div>
		<div align="center" style="margin-top:20px;">
			<input type="button" value="保存" id="updateOrganizationInfo" />
		</div>
	</div>
</body>
</html>

