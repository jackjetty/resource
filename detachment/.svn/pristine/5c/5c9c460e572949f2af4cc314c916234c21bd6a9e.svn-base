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
<input type="hidden" id="editRoleId" />
<div id="searchpanel" data-options="collapsible:true,collapsed:true"
	class="easyui-panel panel-margin-buttom" title="查询条件"
	style="height:90px; padding: 10px; overflow: hidden;">
	<table height="100%">
		<tr
			style="Valign: center; text-decoration: none; line-heigth: 30px; height: 30px; margin-left: 10px;">
			<td>预约类型：</td>
			<td><select id="appointmentTypeId" style="width: 120px;">
			</select></td>
			<td>状态：</td>
			<td><select id="appointmentState" style="width: 120px;">
					<option value="开放">开放</option>
					<option value="关闭">关闭</option>
					<option value="">全部</option>
			</select></td>
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
	data-options="rownumbers:true,singleSelect:false,toolbar:toolbar,pagination:true,
			pageSize:20,remoteSort:false,fitColumns:true,
			multiSort:true">
	<thead>
		<tr>
			<th data-options="field:'ck',checkbox:true"></th>
			<th data-options="field:'tbAppointmentTypeName',width:100,align:'center'">预约类型</th>
			<th data-options="field:'appointmentStartTime',width:100,align:'center'">开始时间</th>
			<th data-options="field:'appointmentEndTime',width:100,align:'center'">开始时间</th>
			<th data-options="field:'appointmentTheme',width:150,align:'center'">主题</th>
			<th data-options="field:'appointmentDesc',width:250,align:'center'">描述</th>
			<th data-options="field:'appointmentAddress',width:200,align:'center'">地址</th>
			<th data-options="field:'appointmentSum',width:50,align:'center'">总人数</th>
			<th data-options="field:'appointmentCount',width:50,align:'center'">已预约人数</th>
			<th data-options="field:'appointmentState',width:100,align:'center'">状态</th>
		</tr>
	</thead>
</table>
<div id="detail_dialog" class="easyui-dialog" title="详情"
	data-options="closed:true"
	style="width: 515px; height: 220px; padding: 10px; height: auto !important; min-height: 120px;">
	<div id="div1" style="height: 200px;">
		<table id="table" cellspacing="0" style="width: 480px;">
			<tr style="height: 25px;">
				<td
					style="width: 80px; text-align: right; border: 1px solid #CCCCCC; background-color: #EDEDED;">预约类型：</td>
				<td id="detail_tbAppointmentTypeName"
					style="width: 80px; text-align: left; border: 1px solid #CCCCCC; border-left: 0px;"></td>
				<td
					style="width: 80px; text-align: right; border: 1px solid #CCCCCC; border-left: 0px; background-color: #EDEDED;">开始时间：</td>
				<td id="detail_appointmentStartTime"
					style="width: 80px; text-align: left; border: 1px solid #CCCCCC; border-left: 0px;"></td>
				<td
					style="width: 80px; text-align: right; border: 1px solid #CCCCCC; border-left: 0px; background-color: #EDEDED;">结束时间：</td>
				<td id="detail_appointmentEndTime"
					style="width: 80px; text-align: left; border: 1px solid #CCCCCC; border-left: 0px;"></td>
			</tr>
			<tr style="height: 25px;">
				<td
					style="width: 80px; text-align: right; border: 1px solid #CCCCCC; border-top: 0px; background-color: #EDEDED;">总人数：</td>
				<td id="detail_appointmentSum"
					style="width: 100px; text-align: left; border: 1px solid #CCCCCC; border-left: 0px; border-top: 0px;"></td>
				<td
					style="width: 80px; text-align: right; border: 1px solid #CCCCCC; border-left: 0px; border-top: 0px; background-color: #EDEDED;">预约人数：</td>
				<td id="detail_appointmentCount"
					style="width: 80px; text-align: left; border: 1px solid #CCCCCC; border-left: 0px; border-top: 0px;"></td>
				<td
					style="width: 80px; text-align: right; border: 1px solid #CCCCCC; border-top: 0px; border-left: 0px; background-color: #EDEDED;">状态：</td>
				<td id="detail_appointmentState"
					style="width: 80px; text-align: left; border: 1px solid #CCCCCC; border-top: 0px; border-left: 0px;"></td>
			</tr>
			<tr style="height: 25px;">
				<td
					style="width: 80px; text-align: right; border: 1px solid #CCCCCC; border-top: 0px; background-color: #EDEDED;">主题：</td>
				<td id="detail_appointmentTheme" colspan="5"
					style="width: 400px; text-align: left; border: 1px solid #CCCCCC; border-left: 0px; border-top: 0px;"></td>
			</tr>
			<tr style="height: 25px;">
				<td
					style="width: 80px; text-align: right; border: 1px solid #CCCCCC; border-top: 0px; background-color: #EDEDED;">描述：</td>
				<td id="detail_appointmentDesc" colspan="5"
					style="width: 400px; text-align: left; border: 1px solid #CCCCCC; border: 1px solid #CCCCCC; border-left: 0px; border-top: 0px;"></td>
			</tr>
			<tr style="height: 25px;">
				<td
					style="width: 80px; text-align: right; border: 1px solid #CCCCCC; border-top: 0px; background-color: #EDEDED;">地址：</td>
				<td id="detail_appointmentAddress" colspan="5"
					style="width: 400px; text-align: left; border: 1px solid #CCCCCC; border: 1px solid #CCCCCC; border-left: 0px; border-top: 0px;"></td>
			</tr>
			<tr style="height: 25px;">
				<td
					style="width: 80px; text-align: right; border: 1px solid #CCCCCC; border-top: 0px; background-color: #EDEDED;">备注：</td>
				<td id="detail_appointmentRemark" colspan="5"
					style="width: 400px; text-align: left; border: 1px solid #CCCCCC; border: 1px solid #CCCCCC; border-left: 0px; border-top: 0px;"></td>
			</tr>
		</table>
	</div>

</div>



<div id="dlg" class="easyui-dialog" title="添加"
	data-options="closed:true"
	style="width: 515px; height: 240px; padding: 10px; height: auto !important; min-height: 120px;">
	<div id="div1" style="height: 190px;">
		<table id="table" cellspacing="0" style="width: 480px;">
			<tr style="height: 25px;">
				<td
					style="width: 100px; text-align: right; border: 1px solid #CCCCCC; background-color: #EDEDED;">预约类型：</td>
				<td style="width: 140px; text-align: left; border: 1px solid #CCCCCC; border-left: 0px;">
					<select style="width:100%;" id="add_appointmentTypeId"></select>
					</td>
				<td
					style="width: 100px; text-align: right; border: 1px solid #CCCCCC; border-left: 0px; background-color: #EDEDED;">总人数：</td>
				<td style="width: 140px; text-align: left; border: 1px solid #CCCCCC; border-left: 0px;">
					<input type="text" style="width:140px" id="add_appointmentSum"/>	
				</td>
			</tr>
			<tr style="height: 25px;">
				<td
					style="width: 100px; text-align: right; border: 1px solid #CCCCCC; border-top: 0px; background-color: #EDEDED;">开始时间：</td>
				<td style="width: 140px; text-align: left; border: 1px solid #CCCCCC; border-left: 0px; border-top: 0px;">
					<input type="text" style="width:140px" class="easyui-datebox" id="add_appointmentStartTime"/>	
				</td>
				<td
					style="width: 100px; text-align: right; border: 1px solid #CCCCCC; border-left: 0px; border-top: 0px; background-color: #EDEDED;">结束时间：</td>
				<td style="width: 140px; text-align: left; border: 1px solid #CCCCCC; border-left: 0px; border-top: 0px;">
					<input type="text" style="width:140px;" class="easyui-datebox" id="add_appointmentEndTime"/>
				</td>
			</tr>
			<tr style="height: 25px;">
				<td
					style="width: 100px; text-align: right; border: 1px solid #CCCCCC; border-top: 0px; background-color: #EDEDED;">主题：</td>
				<td colspan="3" style="width: 380px; text-align: left; border: 1px solid #CCCCCC; border-left: 0px; border-top: 0px;">
					<input type="text" style="width:99%;" id="add_appointmentTheme"/>
				</td>
			</tr>
			<tr style="height: 25px;">
				<td
					style="width: 100px; text-align: right; border: 1px solid #CCCCCC; border-top: 0px; background-color: #EDEDED;">描述：</td>
				<td colspan="3"
					style="width: 380px; text-align: left; border: 1px solid #CCCCCC; border: 1px solid #CCCCCC; border-left: 0px; border-top: 0px;">
					<textarea rows="1" cols="30" style="width:99%;height:30px;font-size:13px;" id="add_appointmentDesc"></textarea>
					</td>
			</tr>
			<tr style="height: 25px;">
				<td
					style="width: 100px; text-align: right; border: 1px solid #CCCCCC; border-top: 0px; background-color: #EDEDED;">地址：</td>
				<td colspan="3"
					style="width: 380px; text-align: left; border: 1px solid #CCCCCC; border: 1px solid #CCCCCC; border-left: 0px; border-top: 0px;">
					<input type="text" style="width:99%;" id="add_appointmentAddress"/>
				</td>
			</tr>
			<tr style="height: 25px;">
				<td
					style="width: 100px; text-align: right; border: 1px solid #CCCCCC; border-top: 0px; background-color: #EDEDED;">备注：</td>
				<td colspan="3"
					style="width: 380px; text-align: left; border: 1px solid #CCCCCC; border: 1px solid #CCCCCC; border-left: 0px; border-top: 0px;">
					<input type="text" style="width:99%;" id="add_appointmentRemark"/>
				</td>
			</tr>
		</table>
	</div>
	<div align="center">
		<input type="button" value="添加" id="addAppointment" style="height:40px;width:60px;"/>
	</div>
</div>
<div id="edit-dialog" class="easyui-dialog" title="编辑" data-options="closed:true"
	style="width: 515px; height: 240px; padding: 10px; height: auto !important; min-height: 120px;">
	<input type="hidden"  id="edit_appointmentId"/>
	<div id="div1" style="height: 190px;">
		<table id="table" cellspacing="0" style="width: 480px;">
			<tr style="height: 25px;">
				<td
					style="width: 100px; text-align: right; border: 1px solid #CCCCCC; background-color: #EDEDED;">预约类型：</td>
				<td style="width: 140px; text-align: left; border: 1px solid #CCCCCC; border-left: 0px;">
					<select style="width:100%;" id="edit_appointmentTypeId"></select>
					</td>
				<td
					style="width: 100px; text-align: right; border: 1px solid #CCCCCC; border-left: 0px; background-color: #EDEDED;">总人数：</td>
				<td style="width: 140px; text-align: left; border: 1px solid #CCCCCC; border-left: 0px;">
					<input type="text" style="width:140px" id="edit_appointmentSum"/>	
				</td>
			</tr>
			<tr style="height: 25px;">
				<td
					style="width: 100px; text-align: right; border: 1px solid #CCCCCC; border-top: 0px; background-color: #EDEDED;">开始时间：</td>
				<td style="width: 140px; text-align: left; border: 1px solid #CCCCCC; border-left: 0px; border-top: 0px;">
					<input type="text" style="width:140px" class="easyui-datebox" id="edit_appointmentStartTime"/>	
				</td>
				<td
					style="width: 100px; text-align: right; border: 1px solid #CCCCCC; border-left: 0px; border-top: 0px; background-color: #EDEDED;">结束时间：</td>
				<td style="width: 140px; text-align: left; border: 1px solid #CCCCCC; border-left: 0px; border-top: 0px;">
					<input type="text" style="width:140px;" class="easyui-datebox" id="edit_appointmentEndTime"/>
				</td>
			</tr>
			<tr style="height: 25px;">
				<td
					style="width: 100px; text-align: right; border: 1px solid #CCCCCC; border-top: 0px; background-color: #EDEDED;">主题：</td>
				<td colspan="3" style="width: 380px; text-align: left; border: 1px solid #CCCCCC; border-left: 0px; border-top: 0px;">
					<input type="text" style="width:99%;" id="edit_appointmentTheme"/>
				</td>
			</tr>
			<tr style="height: 25px;">
				<td
					style="width: 100px; text-align: right; border: 1px solid #CCCCCC; border-top: 0px; background-color: #EDEDED;">描述：</td>
				<td colspan="3"
					style="width: 380px; text-align: left; border: 1px solid #CCCCCC; border: 1px solid #CCCCCC; border-left: 0px; border-top: 0px;">
					<textarea rows="1" cols="30" style="width:99%;height:30px;font-size:13px;" id="edit_appointmentDesc"></textarea>
					</td>
			</tr>
			<tr style="height: 25px;">
				<td
					style="width: 100px; text-align: right; border: 1px solid #CCCCCC; border-top: 0px; background-color: #EDEDED;">地址：</td>
				<td colspan="3"
					style="width: 380px; text-align: left; border: 1px solid #CCCCCC; border: 1px solid #CCCCCC; border-left: 0px; border-top: 0px;">
					<input type="text" style="width:99%;" id="edit_appointmentAddress"/>
				</td>
			</tr>
			<tr style="height: 25px;">
				<td
					style="width: 100px; text-align: right; border: 1px solid #CCCCCC; border-top: 0px; background-color: #EDEDED;">备注：</td>
				<td colspan="3"
					style="width: 380px; text-align: left; border: 1px solid #CCCCCC; border: 1px solid #CCCCCC; border-left: 0px; border-top: 0px;">
					<input type="text" style="width:99%;" id="edit_appointmentRemark"/>
				</td>
			</tr>
		</table>
	</div>
	<div align="center">
		<input type="button" value="保存" id="updateAppointment" style="height:40px;width:60px;"/>
	</div>
</div>
<script type="text/javascript">
	var toolbar = ['-', {
		text : '添加',
		iconCls : 'icon-add',
		handler : function() {
			$("#add_appointmentTypeId").empty();
			$("#add_appointmentSum").val("");
			$("#add_appointmentStartTime").datebox('setValue', '');
			$("#add_appointmentEndTime").datebox('setValue', '');
			$("#add_appointmentTheme").val("");
			$("#add_appointmentDesc").val("");
			$("#add_appointmentAddress").val("");
			$("#add_appointmentRemark").val("");
			$.ajax({
				url : "getTbAppointmentTypeIdAndName.action",
				async : false,
				data : "",
				dataType : "json",
				type : "post",
				success : function(data) {
					var ids=data.ids.split("=");
					var names=data.names.split("=");
					var content="<option value=''>请选择</option>";
					for(var i=0;i<ids.length-1;i++){
						content+="<option value='"+ids[i]+"'>"+names[i]+"</option>";
					}
					$("#add_appointmentTypeId").html(content);
					$('#dlg').dialog('open');
				},
				error : function(data) {
					alert("网络繁忙!请稍后再试!");
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
				$.ajax({
					url : "getTbAppointmentTypeIdAndName.action",
					async : false,
					data : "",
					dataType : "json",
					type : "post",
					success : function(data) {
						var ids=data.ids.split("=");
						var names=data.names.split("=");
						var content="<option value=''>请选择</option>";
						for(var i=0;i<ids.length-1;i++){
							if(names[i]==row.tbAppointmentTypeName){
								content+="<option value='"+ids[i]+"' selected='selected'>"+names[i]+"</option>";
							}else{
								content+="<option value='"+ids[i]+"'>"+names[i]+"</option>";
							}
						}
						$("#edit_appointmentTypeId").html(content);
					},
					error : function(data) {
						alert("网络繁忙!请稍后再试!");
					}
				});
				$("#edit_appointmentId").val(row.appointmentId);
				$("#edit_appointmentStartTime").datebox('setValue', row.appointmentStartTime);;
				$("#edit_appointmentEndTime").datebox('setValue', row.appointmentEndTime);
				$("#edit_appointmentTheme").val(row.appointmentTheme);
				$("#edit_appointmentDesc").val(row.appointmentDesc);
				$("#edit_appointmentAddress").val(row.appointmentAddress);
				$("#edit_appointmentSum").val(row.appointmentSum);
				$("#edit_appointmentRemark").val(row.appointmentRemark);
				$('#edit-dialog').dialog('open');
			}
		}

	}, '-', {
		text : '关闭',
		iconCls : 'icon-remove',
		handler : function() {
			var rows = $('#dg').datagrid('getSelections');
			if (rows.length == 0) {
				alert("请选择要关闭的活动！");
				return false;
			}else {
				if (window.confirm("确认要关闭这" + rows.length + "条记录？")) {
					var appointmentIds = '';
					for ( var i = 0; i < rows.length; i++) {
						appointmentIds = appointmentIds + "," + rows[i].appointmentId;
					}
					$.ajax({
						url : "updateAppointmentState.action",
						data : "appointmentIds=" + appointmentIds,
						dataType : "json",
						async : false,
						type : "post",
						success : function(data) {
							alert(data.respInfo);
							$('#dlg').dialog('close');
							var appointmentTypeId = $("#appointmentTypeId").val();
							var appointmentState = $("#appointmentState").val();
							$('#dg').datagrid({
								loadFilter : pagerFilter,
								pageNumber : 1
							}).datagrid('loadData', getData(appointmentTypeId,appointmentState));
							
						},
						error : function(data) {
							alert("网络繁忙!请稍后再试!");
						}
					});
				} else {
					return false;
				}
			}
			
		}
	} ];

	$(function() {
		$.ajax({
			url : "getTbAppointmentTypeIdAndName.action",
			async : false,
			data : "",
			dataType : "json",
			type : "post",
			success : function(data) {
				var ids=data.ids.split("=");
				var names=data.names.split("=");
				var content="<option value=''>请选择</option>";
				for(var i=0;i<ids.length-1;i++){
					content+="<option value='"+ids[i]+"'>"+names[i]+"</option>";
				}
				$("#appointmentTypeId").html(content);
			},
			error : function(data) {
				alert("网络繁忙!请稍后再试!");
			}
		});
		var width = $(document).width();
		var height = $(document).height();
		var appointmentTypeId = $("#appointmentTypeId").val();
		var appointmentState = $("#appointmentState").val();
		$('#dg').datagrid({
			loadFilter : pagerFilter,
			width : width - 20,
			height : height - 45,
			onDblClickRow:onDblClickRow
		}).datagrid('loadData', getData(appointmentTypeId,appointmentState));
		
		$("#searchpanel").panel({width:width-20});
		$("#search").click(function() {
			var appointmentTypeId = $("#appointmentTypeId").val();
			var appointmentState = $("#appointmentState").val();
			$('#dg').datagrid({
				loadFilter : pagerFilter,
				pageNumber : 1
			}).datagrid('loadData', getData(appointmentTypeId,appointmentState));
		});
		$("#clear").click(function(){
			$("#appointmentTypeId").val('');
			$("#appointmentState").val('');
		});
		$("#addAppointment").click(function() {
			var appointmentTypeId=$("#add_appointmentTypeId").val().trim();
			var appointmentSum=$("#add_appointmentSum").val().trim();
			var appointmentStartTime=$("#add_appointmentStartTime").datebox('getValue');
			var appointmentEndTime=$("#add_appointmentEndTime").datebox('getValue');
			var appointmentTheme=$("#add_appointmentTheme").val().trim();
			var appointmentDesc=$("#add_appointmentDesc").val().trim();
			var appointmentAddress=$("#add_appointmentAddress").val().trim();
			var appointmentRemark=$("#add_appointmentRemark").val().trim();
			if(appointmentTypeId==""){alert("请选择预约类型!");return false;}
			if(appointmentSum==""){alert("请填写总人数!");return false;}
			if(appointmentStartTime==""){alert("请选择开始时间!");return false;}
			if(appointmentEndTime==""){alert("请选择结束时间!");return false;}
			if(appointmentTheme==""){alert("请填写主题!");return false;}
			if(appointmentAddress==""){alert("请填写地址!");return false;}
			var data="appointmentTypeId="+appointmentTypeId+"&appointmentSum="+appointmentSum
			+"&appointmentStartTime="+appointmentStartTime+"&appointmentEndTime="+appointmentEndTime
			+"&appointmentTheme="+appointmentTheme+"&appointmentDesc="+appointmentDesc
			+"&appointmentAddress="+appointmentAddress+"&appointmentRemark="+appointmentRemark;
			$.ajax({
				url : "addAppointment.action",
				async : false,
				data : data,
				dataType : "json",
				type : "post",
				success : function(data) {
					if(data.respCode==0){
						alert(data.respInfo);
						$('#dlg').dialog('close');
						var appointmentTypeId = $("#appointmentTypeId").val();
						var appointmentState = $("#appointmentState").val();
						$('#dg').datagrid({
							loadFilter : pagerFilter,
							pageNumber : 1
						}).datagrid('loadData', getData(appointmentTypeId,appointmentState));
					}else{
						alert(data.respInfo);
					}
					
				},
				error : function(data) {
					alert("error 后台出现错误！");
				}
			});
		});
		$("#updateAppointment").click(function() {
			var appointmentId=$("#edit_appointmentId").val().trim();
			var appointmentTypeId=$("#edit_appointmentTypeId").val().trim();
			var appointmentSum=$("#edit_appointmentSum").val().trim();
			var appointmentStartTime=$("#edit_appointmentStartTime").datebox('getValue');
			var appointmentEndTime=$("#edit_appointmentEndTime").datebox('getValue');
			var appointmentTheme=$("#edit_appointmentTheme").val().trim();
			var appointmentDesc=$("#edit_appointmentDesc").val().trim();
			var appointmentAddress=$("#edit_appointmentAddress").val().trim();
			var appointmentRemark=$("#edit_appointmentRemark").val().trim();
			if(appointmentTypeId==""){alert("请选择预约类型!");return false;}
			if(appointmentSum==""){alert("请填写总人数!");return false;}
			if(appointmentStartTime==""){alert("请选择开始时间!");return false;}
			if(appointmentEndTime==""){alert("请选择结束时间!");return false;}
			if(appointmentTheme==""){alert("请填写主题!");return false;}
			if(appointmentAddress==""){alert("请填写地址!");return false;}
			var data="appointmentTypeId="+appointmentTypeId+"&appointmentSum="+appointmentSum
			+"&appointmentStartTime="+appointmentStartTime+"&appointmentEndTime="+appointmentEndTime
			+"&appointmentTheme="+appointmentTheme+"&appointmentDesc="+appointmentDesc
			+"&appointmentAddress="+appointmentAddress+"&appointmentRemark="+appointmentRemark
			+"&appointmentId="+appointmentId;
			$.ajax({
				url : "updateAppointment.action",
				async : false,
				data : data,
				dataType : "json",
				type : "post",
				success : function(data) {
					if(data.respCode==0){
						alert(data.respInfo);
						$('#edit-dialog').dialog('close');
						var appointmentTypeId = $("#appointmentTypeId").val();
						var appointmentState = $("#appointmentState").val();
						$('#dg').datagrid({
							loadFilter : pagerFilter,
							pageNumber : 1
						}).datagrid('loadData', getData(appointmentTypeId,appointmentState));
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
	
	function getData(appointmentTypeId,appointmentState) {
		var rows = null;
		var dg = $("#dg");
		var opts = dg.datagrid('options');
		var pageSize = opts.pageSize;
		var pageIndex = opts.pageNumber;
		$.ajax({
			url : "getAppointment.action",
			async : false,
			data : "pageSize=" + pageSize + "&pageIndex=" + pageIndex
					+ "&appointmentTypeId=" + appointmentTypeId+"&appointmentState="+appointmentState,
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
			var appointmentTypeId = $("#appointmentTypeId").val();
			var appointmentState = $("#appointmentState").val();
			var newData = getData(appointmentTypeId,appointmentState);
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
	
	function onDblClickRow(rowIndex, rowData){
		//alert(rowData.appointmentId);
		$.ajax({
			url : "getTbAppointmentById.action",
			async : false,
			data : "appointmentId="+rowData.appointmentId,
			dataType : "json",
			type : "post",
			success : function(data) {
				$("#detail_tbAppointmentTypeName").html(data.tbAppointmentTypeName);
				$("#detail_appointmentStartTime").html(data.appointmentStartTime);
				$("#detail_appointmentEndTime").html(data.appointmentEndTime);
				$("#detail_appointmentTheme").html(data.appointmentTheme);
				$("#detail_appointmentDesc").html(data.appointmentDesc);
				$("#detail_appointmentAddress").html(data.appointmentAddress);
				$("#detail_appointmentSum").html(data.appointmentSum);
				$("#detail_appointmentCount").html(data.appointmentCount);
				$("#detail_appointmentState").html(data.appointmentState);
				$("#detail_appointmentRemark").html(data.appointmentRemark);
				$('#detail_dialog').dialog('open');
			},
			error : function(data) {
				alert("网络繁忙!请稍后再试!");
			}
		});
	}
	
	String.prototype.trim = function() {
		return this.replace(/^\s\s*/, '').replace(/\s\s*$/, '');
	};
</script>
</body>
</html>