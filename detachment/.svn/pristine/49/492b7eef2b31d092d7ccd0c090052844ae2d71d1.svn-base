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
<script type="text/javascript" src="js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
   	String.prototype.trim = function() {
	  	return this.replace(/^\s\s*/, '').replace(/\s\s*$/, '');
	};
	function getData(historyTypeId) {
		var rows = null;
		var dg = $("#dg");
		var opts = dg.datagrid('options');
		var pageSize = opts.pageSize;
		var pageIndex = opts.pageNumber;
		$.ajax({
			url : "getTbHistory.action",
			async : false,
			data : "pageSize=" + pageSize + "&pageIndex=" + pageIndex
					+ "&historyTypeId=" + historyTypeId,
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
			var historyTypeId=$("#historyTypeId").val();
			var newData = getData(historyTypeId);
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
			url : "getHistoryTypeIdName.action",
			type : "post",
			data : "",
			dataType : "json",
			success : function(data) {
				var option = $("<option value=''>请选择类型</option>");
				$("#historyTypeId").append(option);
				var typeIds=data.HistoryTypeId.split(",");
				var typeNames=data.HistoryTypeName.split(",");
				for(var i=0;i<typeIds.length-1;i++){
					option = $("<option value='"+typeIds[i]+"'>"+typeNames[i]+"</option>");
					$("#historyTypeId").append(option);
				}
			},
			error : function(data) {
				alert("error 后台出现错误！");
			}
		});
		
		var width = $(document).width();
		var height = $(document).height();
		var historyTypeId=$("#historyTypeId").val();
		$('#dg').datagrid({
			loadFilter : pagerFilter,
			width : width - 20,
			height : height - 45
		}).datagrid('loadData', getData(historyTypeId));
		$("#searchpanel").panel({
			width : width - 20
		});
		$("#search").click(function() {
			var historyTypeId=$("#historyTypeId").val();
			$('#dg').datagrid({
				loadFilter : pagerFilter,
				pageNumber : 1,
			}).datagrid('loadData', getData(historyTypeId));

		});
		$("#clear").click(function(){
			$("#historyTypeId").val('');
		});
		
		$("#changeType").click(function(){
			var rows = $('#dg').datagrid('getSelections');
			var historyTypeId = $("#textTab1 input[type='radio']:checked").val();
			if(historyTypeId==null){
				alert("请选择");
				return false;
			}
			if (rows.length > 0) {
				if (window.confirm("确认设置这" + rows.length + "条记录")) {
					var ids = '';
					for ( var i = 0; i < rows.length; i++) {
						ids = ids + "," + rows[i].historyId;
					}
					$.ajax({
						url : "changeHistoryType.action",
						data : "ids="+ ids +"&historyTypeId="+historyTypeId,
						dataType : "json",
						type : "post",
						success : function(data) {
							alert(data.respInfo);
							$('#case_dialog').dialog('close');
							var historyTypeId=$("#historyTypeId").val();
							$('#dg').datagrid({
								loadFilter : pagerFilter,
								pageNumber : 1,
							}).datagrid('loadData', getData(historyTypeId));
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
			
		});
		
		
		
		

		
		
		
		
	});
	
	
	var toolbar = [ {
		text : '分配类型',
		iconCls : 'icon-add',
		handler : function() {
			var rows = $('#dg').datagrid('getSelections');
			if (rows.length == 0) {
				alert("请选择要分配类型的数据！");
				return false;
			} else {
				//var row = $("#dg").datagrid('getSelected');
				$.ajax({
					url : "getHistoryTypeIdName.action",
					type : "post",
					async : false,
					data : "",
					dataType : "json",
					success : function(data) {
						$("#textTab1").html("");
						var typeIds=data.HistoryTypeId.split(",");
						var typeNames=data.HistoryTypeName.split(",");
						for(var i=0;i<typeIds.length-1;i++){
							var tr="<tr><td style='font-size:15px;height:20px;'><input type='radio' name='type' value='"+typeIds[i]+"' />"+typeNames[i]+"</td></tr>";
							$("#textTab1").append(tr);
						}
					},
					error : function(data) {
						alert("error 后台出现错误！");
					}
				});
				
				$('#case_dialog').dialog('open');
				
				
			}
		}
	}, '-', {
		text : '启用',
		iconCls : 'icon-edit',
		handler : function() {
			var rows = $('#dg').datagrid('getSelections');
			if (rows.length == 0) {
				alert("请选择启用的数据！");
				return false;
			} else {
				if (window.confirm("确认启用这" + rows.length + "条记录")) {
					var ids = '';
					for ( var i = 0; i < rows.length; i++) {
						ids = ids + "," + rows[i].historyId;
					}
					$.ajax({
						url : "changeValid.action",
						data : "ids="+ ids +"&valid=true",
						dataType : "json",
						type : "post",
						success : function(data) {
							alert(data.respInfo);
							var historyTypeId=$("#historyTypeId").val();
							$('#dg').datagrid({
								loadFilter : pagerFilter,
								pageNumber : 1,
							}).datagrid('loadData', getData(historyTypeId));
						},
						error : function(data) {
							alert("error 后台出现错误！");
						}
					});
				} else {
					return false;
				}
				
				
			}
		}

	} , '-', {
		text : '禁用',
		iconCls : 'icon-edit',
		handler : function() {
			var rows = $('#dg').datagrid('getSelections');
			if (rows.length == 0) {
				alert("请选择启用的数据！");
				return false;
			} else {
				if (window.confirm("确认启用这" + rows.length + "条记录")) {
					var ids = '';
					for ( var i = 0; i < rows.length; i++) {
						ids = ids + "," + rows[i].historyId;
					}
					$.ajax({
						url : "changeValid.action",
						data : "ids="+ ids +"&valid=false",
						dataType : "json",
						type : "post",
						success : function(data) {
							alert(data.respInfo);
							var historyTypeId=$("#historyTypeId").val();
							$('#dg').datagrid({
								loadFilter : pagerFilter,
								pageNumber : 1,
							}).datagrid('loadData', getData(historyTypeId));
						},
						error : function(data) {
							alert("error 后台出现错误！");
						}
					});
				} else {
					return false;
				}
				
				
			}
		}

	}, '-', {
		text : '刷新',
		iconCls : 'icon-reload',
		handler : function() {
				if (window.confirm("确认要刷新获取记录")) {
					$.ajax({
						url : "reloadHistory.action",
						data : "",
						dataType : "json",
						type : "post",
						success : function(data) {
							if(data.errcode==0){
								alert(data.errmsg);
								var historyTypeId=$("#historyTypeId").val();
								$('#dg').datagrid({
									loadFilter : pagerFilter,
									pageNumber : 1,
								}).datagrid('loadData', getData(historyTypeId));
							}else{
								alert(data.errmsg);
							}
							
						},
						error : function(data) {
							alert("error 后台出现错误！");
						}
					});
				} else {
					return false;
				}
				
				
			
		}

	}];
	
	function detail() {
		var row = $("#dg").datagrid('getSelected');
		window.open("getHtmlInfo.action?htmlId=" + row.htmlId, "");
	}
	function operate(value, row, index) {
		if (row.valid == '启用')
			return "<a href='javascript:exchange();'><font color='red'>禁用</font></a>";
		else if (row.valid == '禁用')
			return "<a href='javascript:exchange();'>启用</a>";
	}
	
	function closeCase(){
		$('#case_dialog').dialog('close');
	}
	
	
	
	
	

	
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


a{
text-decoration:none;
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
				<td>类型：</td>
				<td><select id="historyTypeId">
				
				</select>
				<!--  <option value="ElectronicPolice">电警介绍</option></select></td>-->
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
				<!-- th data-options="field:'feedBackId',width:150,align:'center'">反馈ID</th> -->
				<th data-options="field:'historyTypeName',width:50,align:'center'">类型</th>
				<th data-options="field:'historyTitle',width:150,align:'center'">标题</th>
				<th data-options="field:'historyDes',width:150,align:'center'">描述</th>
				<th data-options="field:'valid',width:50,align:'center'">状态</th>
				<th data-options="field:'picUrl',width:150,align:'center'">图片地址</th>
				
			</tr>
		</thead>
	</table>

	<div id="case_dialog" class="easyui-dialog" title="请选择类型"
		data-options="closed:true"
		style="width: 300px;  padding: 10px;height: 250px; ">
		<div id="div1" style="margin-left: 40px;width:200px; overflow-y:auto; max-height:150px;">
			<table id="textTab1" cellspacing="0" style="width: 150px; ">
			
			</table>
		</div>
		<div align="center" style="margin-top: 10px;">
				<input style="width: 80px; height: 30px;" type="button" value="确定"
					id="changeType" /> <input style="width: 80px; height: 30px;"
					type="button" value="取消" onclick="closeCase()" />
			</div>
	</div>

	
	

	
	
	
	
</body>
</html>

