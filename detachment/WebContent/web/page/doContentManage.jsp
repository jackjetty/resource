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
	function getData(startTime, endTime, htmlType) {
		var rows = null;
		var dg = $("#dg");
		var opts = dg.datagrid('options');
		var pageSize = opts.pageSize;
		var pageIndex = opts.pageNumber;
		$.ajax({
			url : "getTbHtmls.action",
			async : false,
			data : "pageSize=" + pageSize + "&pageIndex=" + pageIndex
					+ "&startTime=" + startTime + "&endTime=" + endTime + "&htmlType="+htmlType,
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
			var startTime = $("#startTime").datebox('getValue');
			var endTime = $("#endTime").datebox('getValue');
			var htmlType=$("#htmlType").val();
			var newData = getData(startTime, endTime, htmlType);
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
			url : "getHtmlTypeIdName.action",
			type : "post",
			data : "",
			dataType : "json",
			success : function(data) {
				var option = $("<option value=''>请选择类型</option>");
				$("#htmlType").append(option);
				var typeIds=data.htmlTypeId.split(",");
				var typeNames=data.htmlTypeName.split(",");
				for(var i=0;i<typeIds.length-1;i++){
					option = $("<option value='"+typeIds[i]+"'>"+typeNames[i]+"</option>");
					$("#htmlType").append(option);
				}
			},
			error : function(data) {
				alert("error 后台出现错误！");
			}
		});
		
		
		
		var today = new Date();
		var year = today.getFullYear();
		var month = today.getMonth() + 1;
		var day = today.getDate();
		if (month < 10) {
			month = "0" + month;
		}
		if (day < 10) {
			day = "0" + day;
		}
		var enddate = year + "-" + month + "-" + day;
		today.setDate((today.getDate() -6));
		var year = today.getFullYear();
		var month = today.getMonth() + 1;
		var day = today.getDate();
		if (month < 10) {
			month = "0" + month;
		}
		if (day < 10) {
			day = "0" + day;
		}
		var startdate = year + "-" + month + "-" +day;
		$("#startTime").datebox('setValue', startdate);
		$("#endTime").datebox('setValue', enddate);
		var width = $(document).width();
		var height = $(document).height();
		var startTime = $("#startTime").datebox('getValue');
		var endTime = $("#endTime").datebox('getValue');
		var htmlType=$("#htmlType").val();
		$('#dg').datagrid({
			loadFilter : pagerFilter,
			width : width - 20,
			height : height - 45
		}).datagrid('loadData', getData(startTime, endTime,htmlType));
		$("#searchpanel").panel({
			width : width - 20
		});
		$("#search").click(function() {
			var startTime = $("#startTime").datebox('getValue');
			var endTime = $("#endTime").datebox('getValue');
			var htmlType=$("#htmlType").val();
			$('#dg').datagrid({
				loadFilter : pagerFilter,
				pageNumber : 1,
			}).datagrid('loadData', getData(startTime, endTime, htmlType));

		});
		$("#clear").click(function(){
			var today = new Date();
			var year = today.getFullYear();
			var month = today.getMonth() + 1;
			var day = today.getDate();
			if (month < 10) {
				month = "0" + month;
			}
			if (day < 10) {
				day = "0" + day;
			}
			var enddate = year + "-" + month + "-" + day;
			today.setDate((today.getDate() -6));
			var year = today.getFullYear();
			var month = today.getMonth() + 1;
			var day = today.getDate();
			if (month < 10) {
				month = "0" + month;
			}
			if (day < 10) {
				day = "0" + day;
			}
			var startdate = year + "-" + month + "-" +day;
			$("#startTime").datebox('setValue', startdate);
			$("#endTime").datebox('setValue', enddate);
			$("#htmlType").val('');
		});
		
		
		
		
		
		

		
		
		
		
	});
	
	
	var toolbar = [ {
		text : '添加',
		iconCls : 'icon-add',
		handler : function() {
			window.open("getAddHtml.action", "");
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
				window.open("getUpdateHtml.action?htmlId=" + row.htmlId, "");
				
				
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
						ids = ids + "," + rows[i].htmlId;
					}
					$.ajax({
						url : "removeHtml.action",
						data : "ids="+ ids,
						dataType : "json",
						type : "post",
						success : function(data) {
							alert(data.respInfo);
							var startTime = $("#startTime").datebox('getValue');
							var endTime = $("#endTime").datebox('getValue');
							var htmlType=$("#htmlType").val();
							$('#dg').datagrid({
								loadFilter : pagerFilter,
								pageNumber : 1,
							}).datagrid('loadData', getData(startTime, endTime, htmlType));
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
	
	function detail() {
		var row = $("#dg").datagrid('getSelected');
		window.open("getHtmlInfo.action?htmlId=" + row.htmlId, "");
	}
	/*function operate(value, row, index) {
		if (row.status == '启用')
			return "<a href='javascript:exchange();'><font color='red'>禁用</font></a>";
		else if (row.status == '禁用')
			return "<a href='javascript:exchange();'>启用</a>";
	}*/
	
	
	
	
	
	
	

	
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
	width: 400px;
	text-align: center;
	height: 25px;
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
				<td><select id="htmlType">
				
				</select>
				<!--  <option value="ElectronicPolice">电警介绍</option></select></td>-->
				<td>开始时间：</td>
				<td><input type="text" id="startTime" class="easyui-datebox"
					editable="false" style="width: 90px;" /></td>
				<td>结束时间：</td>
				<td><input type="text" id="endTime" class="easyui-datebox"
					editable="false" style="width: 90px;" /></td>
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
				<th data-options="field:'htmlTypeName',width:150,align:'center'">类型</th>
				<th data-options="field:'htmlTitle',width:150,align:'center'">标题</th>
				<th data-options="field:'htmlDes',width:150,align:'center'">描述</th>
				<th data-options="field:'publishTime',width:150,align:'center'">发布时间</th>
				<th data-options="field:'userName',width:150,align:'center'">发布人</th>
				<th	data-options="field:'opt',align:'center',width:80,formatter:function(){return '<a href=\'javascript:detail();\'>查看</a>'}">操作</th>
			</tr>
		</thead>
	</table>

	

	
	

	
	
	
	
</body>
</html>

