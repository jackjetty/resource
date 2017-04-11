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
	function getData(studyNumber,idCard,score) {
		var rows = null;
		var dg = $("#dg");
		var opts = dg.datagrid('options');
		var pageSize = opts.pageSize;
		var pageIndex = opts.pageNumber;
		$.ajax({
			url : "getStudyInfos.action",
			async : false,
			data : "pageSize=" + pageSize + "&pageIndex=" + pageIndex
					+ "&studyNumber=" + studyNumber + "&idCard=" + idCard +"&score=" + score,
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
			var studyNumber=$("#studyNumber").val();
			var idCard=$("#idCard").val();
			var score=$("#score").val();
			var newData = getData(studyNumber,idCard,score);
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
		var studyNumber=$("#studyNumber").val();
		var idCard=$("#idCard").val();
		var score=$("#score").val();
		$('#dg').datagrid({
			loadFilter : pagerFilter,
			width : width - 20,
			height : height - 45
		}).datagrid('loadData', getData(studyNumber,idCard,score));
		$("#searchpanel").panel({
			width : width - 20
		});
		$("#search").click(function() {
			var studyNumber=$("#studyNumber").val();
			var idCard=$("#idCard").val();
			var score=$("#score").val();
			$('#dg').datagrid({
				loadFilter : pagerFilter,
				pageNumber : 1,
			}).datagrid('loadData', getData(studyNumber,idCard,score));

		});
		$("#clear").click(function(){
			$("#studyNumber").val('');
			$("#idCard").val('');
			$("#score").val('');
		});
		

	});
	
	function operate1(value, row, index) {
		return '<a href=\'javascript:detail();\'>详情</a>';
	}
	function detail() {
		$('#detail_studyNumber').html('');
		$('#detail_idCard').html('');
		$('#detail_phoneNumber').html('');
		$('#detail_resultInfo').val('');
		$('#detail_score').val('');
		var row = $("#dg").datagrid('getSelected');
		$('#detail_studyNumber').html(row.studyNumber);
		$('#detail_idCard').html(row.idCard);
		$('#detail_phoneNumber').html(row.phoneNumber);
		$('#detail_resultInfo').val(row.resultInfo);
		$('#detail_score').html(row.score);
		$('#detail_dialog').dialog('open');
	}
	function detail_close() {
		$('#detail_dialog').dialog('close');
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
				<td>学习编号：</td>
				<td><input type="text" id="studyNumber" style="width: 190px;" /></td>
				<td>身份证号：</td>
				<td><input type="text" id="idCard" style="width: 190px;" /></td>
				<td>学习分数：</td>
				<td><select id="score">
					<option value="">请选择分数</option>
					<option value="1">90分以上</option>
					<option value="2">90分以下</option>
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
		data-options="rownumbers:true,singleSelect:true,toolbar:toolbar,pagination:true,
				pageSize:20,remoteSort:false,fitColumns:true,
				multiSort:true">
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<!-- th data-options="field:'feedBackId',width:150,align:'center'">反馈ID</th> -->
				<th data-options="field:'studyNumber',width:150,align:'center'">学习编号</th>
				<th data-options="field:'idCard',width:150,align:'center'">身份证号</th>
				<th data-options="field:'phoneNumber',width:80,align:'center'">手机号码</th>
				<th data-options="field:'resultInfo',width:200,align:'center'">学习成绩</th>
				<th data-options="field:'score',width:50,align:'center'">学习分数</th>
				<th data-options="field:'opt',align:'center',width:80,formatter:operate1">操作</th>
			</tr>
		</thead>
	</table>

	<div id="detail_dialog" class="easyui-dialog" title="学习详情"
		data-options="closed:true"
		style="width: 435px; height: 300px; padding: 10px; height: auto !important; min-height: 220px;">
		<div id="div1" style="height: 260px;">
			<table id="table" cellspacing="0" style="width: 400px;">
				<tr style="height: 25px;">
					<td
						style="width: 100px; text-align: right; border: 1px solid #CCCCCC; background-color: #EDEDED;">学习编号：</td>
					<td id="detail_studyNumber"
						style="width: 300px; text-align: left; border: 1px solid #CCCCCC; border-left: 0px;"></td>
					
				</tr>
				<tr style="height: 25px;">

					<td
						style="width: 100px; text-align: right; border: 1px solid #CCCCCC; border-top: 0px; background-color: #EDEDED;">身份证号：</td>
					<td id="detail_idCard"
						style="width: 300px; text-align: left; border: 1px solid #CCCCCC; border-left: 0px; border-top: 0px;"></td>
					
				</tr>
				<tr style="height: 25px;">
					<td
						style="width: 100px; text-align: right; border: 1px solid #CCCCCC; border-top: 0px; background-color: #EDEDED;">手机号码：</td>
					<td id="detail_phoneNumber" 
						style="width: 300px; text-align: left; border: 1px solid #CCCCCC; border-left: 0px; border-top: 0px;"></td>
				</tr>
				<tr style="height: 25px;">
					<td
						style="width: 100px; text-align: right; border: 1px solid #CCCCCC; border-top: 0px; background-color: #EDEDED;">学习分数：</td>
					<td id="detail_score" 
						style="width: 300px; text-align: left; border: 1px solid #CCCCCC; border-left: 0px; border-top: 0px;"></td>
				</tr>
				<tr style="height: 25px;">
					<td
						style="width: 100px; text-align: right; border: 1px solid #CCCCCC; border-top: 0px; background-color: #EDEDED;">学习成绩：</td>
					<td 
						style="width: 300px; text-align: left; border: 1px solid #CCCCCC; border-left: 0px; border-top: 0px;">
						<textarea  rows="2" id="detail_resultInfo"
							style="width: 98%; resize: none;"></textarea>
						
						</td>
				</tr>
				
				
			</table>
			<div align="center" style="margin-top: 30px;">
				<input style="width: 55px; height: 30px;"
					type="button" value="返回" onclick="detail_close()" id="detail_close" />
			</div>
		</div>

	</div>
	
	
	
	
	
	
	
</body>
</html>

