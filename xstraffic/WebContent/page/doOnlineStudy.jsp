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
<script type="text/javascript" src="js/jquery.form.js"></script>
<script type="text/javascript">
	
   String.prototype.trim = function() {
	  return this.replace(/^\s\s*/, '').replace(/\s\s*$/, '');
	};
	function getData(problem) {
		var rows = null;
		var dg = $("#dg");
		var opts = dg.datagrid('options');
		var pageSize = opts.pageSize;
		var pageIndex = opts.pageNumber;
		$.ajax({
			url : "getProblems.action",
			async : false,
			data : "pageSize=" + pageSize + "&pageIndex=" + pageIndex
					+ "&problem=" + problem,
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
			var problem=$("#problem").val();
			var newData = getData(problem);
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
		var problem=$("#problem").val();
		$('#dg').datagrid({
			loadFilter : pagerFilter,
			width : width - 20,
			height : height - 45
		}).datagrid('loadData', getData(problem));
		$("#searchpanel").panel({
			width : width - 20
		});
		$("#search").click(function() {
			var problem=$("#problem").val();
			$('#dg').datagrid({
				loadFilter : pagerFilter,
				pageNumber : 1,
			}).datagrid('loadData', getData(problem));

		});
		$("#clear").click(function(){
			$("#problem").val('');
		});
		$("#addProblemFile").click(function(){
			var problemFile=$("#problemFile").val();
			if (problemFile == '') {
				alert("请选择要上传的xls文件!");
				return false;
			} else if (problemFile.split(".")[1].toLowerCase() != 'xls') {
				alert("上传文件格式错误,请重新选择文件!");
				return false;
			}
			var options = {
				dataType : "json",//数据类型 
				async : false,
				error : function() {
					alert("后台错误！");
				},
				success : function(data) {//调用Action后返回过来的数据 
					alert(data.respInfo);
					if (data.respCode == 0) {
						$("#dlg").dialog("close");
						var problem=$("#problem").val();
						$('#dg').datagrid({
							loadFilter : pagerFilter,
							pageNumber : 1,
						}).datagrid('loadData', getData(problem));
					}
				}
			};
			$("#addForm").ajaxSubmit(options);
			
			
			
		});

		
	});
	

	
	var toolbar = [ {
		text : '导入题库',
		iconCls : 'icon-add',
		handler : function() {
			$('#dlg').dialog('open');
			
		}
	},{
		text : '导出题库',
		iconCls : 'icon-add',
		handler : function() {
			var rows = $('#dg').datagrid('getSelections');
			if (rows.length != 0) {
				if (window.confirm("确认导出这" + rows.length + "个题目？")) {
					var problemIds = '';
					for ( var i = 0; i < rows.length; i++) {
						problemIds = problemIds + "," + rows[i].id;
					}
					var canshu = "problemIds=" + problemIds;
					var lianjie = "exportProblem.action?" + canshu;
					window.location.href = lianjie;
				} else {
					return false;
				}
			} else {
				if (window.confirm("确认导出全部题目？")) {
					var lianjie = "exportProblem.action";
					window.location.href = lianjie;
				} else {
					return false;
				}
			}
		}
	}];
	
	
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
				<td>问题：</td>
				<td><input type="text" id="problem" style="width: 190px;" /></td>
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
				<th data-options="field:'problem',width:250,align:'center'">问题</th>
				<th data-options="field:'resultA',width:80,align:'center'">答案A</th>
				<th data-options="field:'resultB',width:80,align:'center'">答案B</th>
				<th data-options="field:'resultC',width:80,align:'center'">答案C</th>
				<th data-options="field:'resultD',width:80,align:'center'">答案D</th>
				<th data-options="field:'answer',width:50,align:'center'">正确答案</th>
				<th data-options="field:'imageUrl',width:100,align:'center'">图片地址</th>
			</tr>
		</thead>
	</table>

	
	
	
	<div id="dlg" class="easyui-dialog" title="导入题库信息"
		data-options="iconCls:'icon-save',closed:true"
		style="width: 300px; height: 200px; padding: 20px;text-align:center;"  >
		<div style="margin-top:20px;">
		<form action="addProblemFile.action" method="post" enctype="application/x-www-form-urlencoded" id="addForm">
			
				<input type="file" id="problemFile" name="problemFile" style="width:180px;"/>
		</form>
		</div>
		<div align="center" style="margin-top:30px;">
			<input type="button" value="添加" id="addProblemFile" style="margin-top: 10px;" />
		</div>
	</div>
	
	
	
	
</body>
</html>

