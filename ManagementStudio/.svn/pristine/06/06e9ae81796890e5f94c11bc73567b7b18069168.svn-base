<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加功能菜单</title>
<link rel="stylesheet" type="text/css" href="themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="themes/icon.css">
<script type="text/javascript" src="js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/easyui-lang-zh_CN.js"></script>

<style type="text/css">
<!--

-->
</style>
</head>
<body style="background: #DFE8F6;">
	
	<table border="0px">
		<tr>
			<td><div class="easyui-panel panel-margin-buttom" title="部门结构图" style="margin-bottom:0px; width:300px;float:left;">
			</div></td>
			<td>
			<div class="easyui-panel panel-margin-buttom" title="员工列表" style="margin-bottom:0px; width:780px;float:left;">
			</div>
			</td>
		</tr>
		<tr height="500px">
			<td>
			<div style="width:298px;height:500px;border:1px solid green;background-color:white;">
				
					<div class="easyui-panel" 
						style="height: 500px; width: 298px; padding: 8px;">
						<ul id="tree" class="easyui-tree"
							data-options="animate:true,lines:true">
						</ul>
					</div>
			</div>
			</td>
			<td>
				
				<div id="div1" style="min-width:780px;height:500px;">
				<table id="dg" class="easyui-datagrid" style="height:500px;" data-options="rownumbers:true,singleSelect:false,pagination:true,toolbar:toolbar,
					pageSize:10,remoteSort:false,
					multiSort:true,width:780">
				<thead >
				<tr>
					<th data-options="field:'ck',checkbox:true"></th>
					<th data-options="field:'employeeName',width:100">员工姓名</th>
					<th data-options="field:'employeeNo',width:150">员工编号</th>
					<th data-options="field:'departmentId',width:150,sortable:true">部门</th>
					<th data-options="field:'describe',width:320">员工介绍</th>
				</tr>
					</thead>
					</table>
				</div>
			</td>
		</tr>
	</table>
<script type="text/javascript">
	String.prototype.trim = function() {
		  return this.replace(/^\s\s*/, '').replace(/\s\s*$/, '');
		};
		
		var toolbar = [ {
			text : '添加部门',
			iconCls : 'icon-add',
			handler : function() {
				$('#dlg').dialog('open');
				$("#add_departmentName").val('');
				$("#add_superId").empty();
				$("#add_departmentNo").val('');
				$("#add_ddescribe").val('');
				$("#add_superId").append("<option value=''>请选择</option>");
				$.ajax({
					url : "getDepartmentNameAndNo.action",
					data : "",
					dataType : "json",
					type : "post",
					success : function(data) {
						var departmentNo=data.departmentNo.split(",");
						var departmentName=data.departmentName.split(",");
						for(var i=0;i<departmentNo.length-1;i++){
							var option="<option value='"+departmentNo[i]+"'>"+departmentName[i]+"</option>";
							$("#add_superId").append(option);
						}
					},
					error : function(data) {
						alert("error 后台出现错误！");
					}
				});
			}
		},'-', {
			text : '编辑部门',
			iconCls : 'icon-edit',
			handler : function() {
				var node = $('#tree').tree('getSelected');
				var superid;
				if(node==null){
					alert("请选择部门");
				}else{
					$("#edit_superId").empty();
					$.ajax({
						url : "getDepartmentByName.action",
						async : false,
						data : "departmentName="+node.text,
						dataType : "json",
						async : false,
						type : "post",
						success : function(data) {
							var department=data.department;
							$("#edit_departmentName").val(department.departmentName);
							$("#edit_departmentNo").val(department.departmentNo);
							$("#edit_ddescribe").val(department.describe);
							$("#departmentId").val(department.id);
							superid=department.superId;
						},
						error : function(data) {
							alert("error 后台出现错误！");
						}
					});
					$.ajax({
						url : "getDepartmentNameAndNo.action",
						data : "",
						dataType : "json",
						type : "post",
						success : function(data) {
							var departmentNo=data.departmentNo.split(",");
							var departmentName=data.departmentName.split(",");
							for(var i=0;i<departmentNo.length-1;i++){
								var option;
								if(superid==departmentNo[i]){
									option="<option value='"+departmentNo[i]+"' selected='true'>"+departmentName[i]+"</option>";
								}else{
									option="<option value='"+departmentNo[i]+"'>"+departmentName[i]+"</option>";
								}
								$("#edit_superId").append(option);
							}
						},
						error : function(data) {
							alert("error 后台出现错误！");
						}
					});
					$('#edit-dialog').dialog('open');
				}
				
				
				
			}

		}, '-', {
			text : '删除部门',
			iconCls : 'icon-remove',
			handler : function() {
				var node = $('#tree').tree('getSelected');
				if(window.confirm("确认删除"+node.text+"？")){
					var departmentNo=node.id;
					$.ajax({
						url : "removeDepartment.action",
						data : "departmentNo=" + departmentNo,
						dataType : "json",
						type : "post",
						success : function(data) {
							if(data.respCode==0){
								alert(data.respInfo);
								var url = "getAllDepartment.action";
								$('#tree').tree({
									url : url
								});
							}else{
								alert(data.respInfo);
							}
							
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
			text : '    ',
			iconCls : '',
			handler : function() {
				
			}
		}, '-', {
			text : '  添加员工',
			iconCls : 'icon-add',
			handler : function() {
				$('#add-employee').dialog('open');
				$("#add_employeeName").val('');
				$("#add_departmentId").empty();
				$("#add_employeeNo").val('');
				$("#add_edescribe").val('');
				var node = $('#tree').tree('getSelected');
				var depNo;
				if(node!=null){
					depNo=node.id;
				}
				$("#add_departmentId").append("<option value=''>请选择</option>");
				$.ajax({
					url : "getDepartmentNameAndNo.action",
					data : "",
					dataType : "json",
					type : "post",
					success : function(data) {
						var departmentNo=data.departmentNo.split(",");
						var departmentName=data.departmentName.split(",");
						for(var i=0;i<departmentNo.length-1;i++){
							if(departmentNo[i]!='NO.000'){
								var option;
								if(depNo==departmentNo[i]){
									option="<option value='"+departmentNo[i]+"' selected='true'>"+departmentName[i]+"</option>";
								}else{
									option="<option value='"+departmentNo[i]+"'>"+departmentName[i]+"</option>";
								}
								
								$("#add_departmentId").append(option);
							}
							
						}
					},
					error : function(data) {
						alert("error 后台出现错误！");
					}
				});
				
			}
		}, '-', {
			text : '编辑员工',
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
					$('#edit-employee').dialog('open');
					$("#edit_employeeName").val(row.employeeName);
					$("#edit_departmentId").empty();
					$("#edit_employeeNo").val(row.employeeNo);
					$("#edit_edescribe").val(row.describe);
					$("#edit_employeeId").val(row.id);
					$.ajax({
						url : "getDepartmentNameAndNo.action",
						data : "",
						dataType : "json",
						type : "post",
						success : function(data) {
							var departmentNo=data.departmentNo.split(",");
							var departmentName=data.departmentName.split(",");
							for(var i=0;i<departmentNo.length-1;i++){
								var option;
								if(departmentNo[i]!='NO.000'){
									if(row.departmentId==departmentName[i]){
										option="<option value='"+departmentNo[i]+"' selected='true'>"+departmentName[i]+"</option>";
									}else{
										option="<option value='"+departmentNo[i]+"'>"+departmentName[i]+"</option>";
									}
									$("#edit_departmentId").append(option);
								}
								
							}
						},
						error : function(data) {
							alert("error 后台出现错误！");
						}
					});
					
				}
			}

		}, '-', {
			text : '删除员工',
			iconCls : 'icon-remove',
			handler : function() {
				var rows = $('#dg').datagrid('getSelections');
				if(window.confirm("确认删除这"+rows.length+"记录？")){
					var employeeIds = '';
					for(var i=0;i<rows.length;i++){
						employeeIds = employeeIds + ","+rows[i].id;
					}
					$.ajax({
						url : "removeEmployee.action",
						data : "employeeIds="+ employeeIds,
						dataType : "json",
						type : "post",
						success : function(data) {
							alert(data.respInfo);
							var width = $("#div1").width();
							var height = $("#div1").height();
							var node = $('#tree').tree('getSelected');
							var departmentNo=node.id;
							$('#dg').datagrid({
								loadFilter : pagerFilter,
								width:width,
								height:height
							}).datagrid('loadData', getData(departmentNo));
						},
						error : function(data) {
							alert("error 后台出现错误！");
						}
					});
				}else{
					return false;
				}
			}
		}  ];
		
		function getData(departmentNo) {
			var rows = null;
			var dg = $("#dg");
			var opts = dg.datagrid('options');
			var pageSize = opts.pageSize;
			var pageIndex = opts.pageNumber;
			$.ajax({
				url : "getEmployee.action",
				async : false,
				data : "pageSize=" + pageSize + "&pageIndex=" + pageIndex
						+ "&departmentNo=" + departmentNo,
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
				var departmentNo = node.id;
				var newData = getData(departmentNo);
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
			var url = "getAllDepartment.action";
			var shuaxin;
			$('#tree').tree({
				url : url,
				onClick:function(node){
					//alert('you click '+node.id);
					var width = $("#div1").width();
					var height = $("#div1").height();
					var departmentNo = node.id;
					shuaxin=node.id;
					$('#dg').datagrid({
						loadFilter : pagerFilter,
						width:width,
						height:height
					}).datagrid('loadData', getData(departmentNo));
					
				}
			});
			$("#addDepartment").click(function(){
				var departmentName=$("#add_departmentName").val();
				var superId=$("#add_superId").val();
				var departmentNo=$("#add_departmentNo").val();
				var describe=$("#add_ddescribe").val();
				if(departmentName==''){
					alert("部门名称不能为空");
					return false;
				}
				if(superId==''){
					alert("上级部门不能为空");
					return false;
				}
				if(departmentNo==''){
					alert("部门编号不能为空");
					return false;
				}
				$.ajax({
					url : "addDepartment.action",
					data : "departmentName=" + departmentName + "&superId=" + superId
							+ "&describe=" + describe +"&departmentNo="+departmentNo,
					dataType : "json",
					type : "post",
					success : function(data) {
						alert(data.respInfo);
						$('#dlg').dialog('close');
						$('#tree').tree({
							url : url
						});
					},
					error : function(data) {
						alert("error 后台出现错误！");
					}
				});
			});
			$("#updateDepartment").click(function(){
				var departmentName=$("#edit_departmentName").val();
				var superId=$("#edit_superId").val();
				var departmentNo=$("#edit_departmentNo").val();
				var describe=$("#edit_ddescribe").val();
				if(departmentName==''){
					alert("部门名称不能为空");
					return false;
				}
				if(superId==''){
					alert("上级部门不能为空");
					return false;
				}
				$.ajax({
					url : "updateDepartment.action",
					data : "departmentName=" + departmentName + "&superId=" + superId
							+ "&describe=" + describe +"&departmentNo="+departmentNo+"&id="+$("#departmentId").val(),
					dataType : "json",
					type : "post",
					success : function(data) {
						alert(data.respInfo);
						$('#edit-dialog').dialog('close');
						$('#tree').tree({
							url : url
						});
					},
					error : function(data) {
						alert("error 后台出现错误！");
					}
				});
			});
		
			$("#addEmployee").click(function(){
				var employeeName=$("#add_employeeName").val();
				var departmentId=$("#add_departmentId").val();
				var employeeNo=$("#add_employeeNo").val();
				var describe=$("#add_edescribe").val();
				if(employeeName==''){
					alert("员工姓名不能为空");
					return false;
				}
				if(departmentId==''){
					alert("所在部门不能为空");
					return false;
				}
				if(employeeNo==''){
					alert("员工编号不能为空");
					return false;
				}
				$.ajax({
					url : "addEmployee.action",
					data : "employeeName=" + employeeName + "&departmentId=" + departmentId
							+ "&describe=" + describe +"&employeeNo="+employeeNo,
					dataType : "json",
					type : "post",
					success : function(data) {
						alert(data.respInfo);
						$('#add-employee').dialog('close');
						var width = $("#div1").width();
						var height = $("#div1").height();
						$('#dg').datagrid({
							loadFilter : pagerFilter,
							width:width,
							height:height
						}).datagrid('loadData', getData(shuaxin));
						
					},
					error : function(data) {
						alert("error 后台出现错误！");
					}
				});
			});
			
			$("#updateEmployee").click(function(){
				var employeeName=$("#edit_employeeName").val();
				var departmentId=$("#edit_departmentId").val();
				var employeeNo=$("#edit_employeeNo").val();
				var describe=$("#edit_edescribe").val();
				if(employeeName==''){
					alert("员工姓名不能为空");
					return false;
				}
				if(departmentId==''){
					alert("所在部门不能为空");
					return false;
				}
				if(employeeNo==''){
					alert("员工编号不能为空");
					return false;
				}
				$.ajax({
					url : "updateEmployee.action",
					data : "employeeName=" + employeeName + "&departmentId=" + departmentId
							+ "&describe=" + describe +"&employeeNo="+employeeNo+"&id="+$("#edit_employeeId").val(),
					dataType : "json",
					type : "post",
					success : function(data) {
						alert(data.respInfo);
						$('#edit-employee').dialog('close');
						var width = $("#div1").width();
						var height = $("#div1").height();
						$('#dg').datagrid({
							loadFilter : pagerFilter,
							width:width,
							height:height
						}).datagrid('loadData', getData(shuaxin));
					},
					error : function(data) {
						alert("error 后台出现错误！");
					}
				});
			});
			
			
		});

	</script>
	
	<div id="dlg" class="easyui-dialog" title="添加部门"
		data-options="iconCls:'icon-save',closed:true"
		style="width: 420px; height: 315px; padding: 10px; background: #DFE8F6;">
		<div id="div1" style="margin-left:10px;margin-top:20px;">
			<table align="center">
				<tr>
					<td>部门名称：</td>
					<td><input type="text" id="add_departmentName" style="width:180px;" /></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
				</tr>
				<tr>
					<td>上级部门：</td>
					<td><select id="add_superId"  style="width: 183px;"></select></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
				</tr>
				<tr>
					<td>部门编号：</td>
					<td><input type="text" id="add_departmentNo" style="width:180px;"/></td>
					<td><font color="red">&nbsp;*&nbsp;必填(NO.00X)</font></td>
				</tr>
				<tr>
					<td>部门描述：</td>
					<td><textarea rows="6" cols="19" id="add_ddescribe" style="width:178px;"></textarea></td>
					<td><font>&nbsp;*&nbsp;选填</font></td>
				</tr>
			</table>
			
		</div>
		<div align="center" style="margin-top:10px;">
			<input type="button" value="添加" id="addDepartment" />
		</div>
	</div>
	
	<div id="edit-dialog" class="easyui-dialog" title="修改部门信息"
		data-options="iconCls:'icon-edit',closed:true"
		style="width: 420px; height: 315px; padding: 10px; background: #DFE8F6;">
		<div id="div1" style="margin-left:10px;margin-top:20px;">
		<input type="hidden" id="departmentId" />
			<table align="center">
				<tr>
					<td>部门名称：</td>
					<td><input type="text" id="edit_departmentName" style="width:180px;" /></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
				</tr>
				<tr>
					<td>上级部门：</td>
					<td><select id="edit_superId"  style="width: 183px;"></select></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
				</tr>
				<tr>
					<td>部门编号：</td>
					<td><input type="text" id="edit_departmentNo" style="width:180px;" readonly="true"/></td>
					<td><font color="red">&nbsp;*&nbsp;必填(NO.00X)</font></td>
				</tr>
				<tr>
					<td>部门描述：</td>
					<td><textarea rows="6" cols="19" id="edit_ddescribe" style="width:178px;"></textarea></td>
					<td><font>&nbsp;*&nbsp;选填</font></td>
				</tr>
			</table>
			
		</div>
		<div align="center" style="margin-top:10px;">
			<input type="button" value="修改" id="updateDepartment" />
		</div>
	</div>
	
	
	<div id="add-employee" class="easyui-dialog" title="添加员工"
		data-options="iconCls:'icon-save',closed:true"
		style="width: 420px; height: 315px; padding: 10px; background: #DFE8F6;">
		<div id="div1" style="margin-left:10px;margin-top:20px;">
			<table align="center">
				<tr>
					<td>员工名称：</td>
					<td><input type="text" id="add_employeeName" style="width:180px;" /></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
				</tr>
				<tr>
					<td>所在部门：</td>
					<td><select id="add_departmentId"  style="width: 183px;"></select></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
				</tr>
				<tr>
					<td>员工编号：</td>
					<td><input type="text" id="add_employeeNo" style="width:180px;"/></td>
					<td><font color="red">&nbsp;*&nbsp;必填(NO.00X)</font></td>
				</tr>
				<tr>
					<td>员工描述：</td>
					<td><textarea rows="6" cols="19" id="add_edescribe" style="width:178px;"></textarea></td>
					<td><font>&nbsp;*&nbsp;选填</font></td>
				</tr>
			</table>
			
		</div>
		<div align="center" style="margin-top:10px;">
			<input type="button" value="添加" id="addEmployee" />
		</div>
	</div>
	<div id="edit-employee" class="easyui-dialog" title="修改员工信息"
		data-options="iconCls:'icon-edit',closed:true"
		style="width: 420px; height: 315px; padding: 10px; background: #DFE8F6;">
		<div id="div1" style="margin-left:10px;margin-top:20px;">
			<input type="hidden" id="edit_employeeId" />
			<table align="center">
				<tr>
					<td>员工名称：</td>
					<td><input type="text" id="edit_employeeName" style="width:180px;" /></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
				</tr>
				<tr>
					<td>所在部门：</td>
					<td><select id="edit_departmentId"  style="width: 183px;"></select></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
				</tr>
				<tr>
					<td>员工编号：</td>
					<td><input type="text" id="edit_employeeNo" style="width:180px;"/></td>
					<td><font color="red">&nbsp;*&nbsp;必填(NO.00X)</font></td>
				</tr>
				<tr>
					<td>员工描述：</td>
					<td><textarea rows="6" cols="19" id="edit_edescribe" style="width:178px;"></textarea></td>
					<td><font>&nbsp;*&nbsp;选填</font></td>
				</tr>
			</table>
			
		</div>
		<div align="center" style="margin-top:10px;">
			<input type="button" value="修改" id="updateEmployee" />
		</div>
	</div>
	
</body>
</html>