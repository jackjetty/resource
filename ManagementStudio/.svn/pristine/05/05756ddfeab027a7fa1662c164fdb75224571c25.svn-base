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

-->
#dg th{
	width:100px;
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
</style>
</head>
<body style="background: #DFE8F6;overflow:hidden;">
	<div id="searchpanel" class="easyui-panel panel-margin-buttom"
		title="中奖用户信息查询" style="height: 85px; padding: 10px;overflow: hidden;">
		<table>
			<tr	style="Valign: center; text-decoration: none; line-heigth: 30px; height: 30px; margin-left: 50px;">
				<td style="font-size: 12px;">选择地区：</td>
				<td><select id="place" name="place" style="width: 95px;" ></select></td>
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
	<table id="dg" class="easyui-datagrid" title="中奖用户信息"
		data-options="rownumbers:true,singleSelect:false,pagination:true,
				pageSize:20,remoteSort:false,fitColumns:true,
				multiSort:true">
				<thead>
			<tr>
				<!--  th data-options="field:'id',width:150">数据库自增长标识</th>-->
				<th data-options="field:'sortDetail',width:100">中奖期</th>
				<th data-options="field:'phoneNumber',width:150">中奖号码</th>
				<th data-options="field:'prize',width:150">奖品</th>
				<th data-options="field:'place',width:150">所在地区</th>
				<th data-options="field:'winTime',width:150">抽奖时间</th>
				<th data-options="field:'manager',width:150">抽奖人</th>
				<th	data-options="field:'opt', width:150,formatter:operate">操作</th>
			</tr>
		</thead>
	</table>
	<script type="text/javascript">
	function operate(value, row, index) {
			return '<a href=\'javascript:deleteRow();\'><font color=\'red\'>删除</font></a>';
	}
	function deleteRow() {
		if(window.confirm("确认要删除该获奖者信息?")){
			var row = $("#dg").datagrid('getSelected');
			$.ajax({
				url : "deleteWinnerList.action",
				async : false,
				data : "id=" + row.id,
				dataType : "json",
				type : "post",
				success : function(data) {
					alert(data.respInfo);
					$("#fresh").click();
				},
				error : function(data) {
					alert("error 后台出现错误！");
				}
			});
		}		
	}
	String.prototype.trim = function() {
		  return this.replace(/^\s\s*/, '').replace(/\s\s*$/, '');
		};
	function getData(place) {
		var rows = null;
		var dg = $("#dg");
		var opts = dg.datagrid('options');
		var pageSize = opts.pageSize;
		var pageIndex = opts.pageNumber;
		$.ajax({
			url : "getWinnerListByPlace.action",
			async : false,
			data : "pageSize=" + pageSize + "&pageIndex=" + pageIndex+ "&place=" + place,
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
			var place = $("#place").val();
			if(place != "null"){
				place = $("#place").text().trim();
			}
			var newData = getData(place);
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
	var place = '${place}';
		$(function() {
			$.ajax({
				url : "getPlaceInfo.action",
				async : false,
				data : "",
				dataType : "json",
				type : "post",
				success : function(data) {
					option = $("<option id='address1' value='null'>请选择地区</option>");
					$("#place").append(option);
					for(var i=0;i<data.length;i++){
						option = $("<option value='"+data[i].code+"'>"+data[i].name+"</option>");
						$("#place").append(option);
					}
					$("#place").val(place);
				},
				error : function(data) {
					alert("error 后台出现错误！");
				}
			});
			var width = $(document).width();
			var height = $(document).height();
			$('#dg').datagrid({
				loadFilter : pagerFilter,
				width : width - 20,
				height : height - 125
			}).datagrid('loadData',getData(place));
			$("#searchpanel").panel({
				width : width - 20
			});
			
			$("#fresh").click(function() {
				var place = $("#place").val();
				if(place != "null"){
					place = $("#place").text().trim();
				}
				$('#dg').datagrid({
					loadFilter : pagerFilter,
					width : width - 23,
					height : height - 20
				}).datagrid('loadData', getData(place));
			});
			$("#search").click(function() {
					var place = $("#place").val();
					if(place != "null"){
						place = $("#place option:selected").text();
					}
					$('#dg').datagrid('loadData',getData(place));
			});
			$("#clear").click(function(){
				$("#address1").attr('selected',true);
				
			});
			$('#choujiang').toggle(function(){
				timer=setInterval(random,10);
				$('#choujiang').val('停止抽奖');
				$("#save").attr({disabled:true});
			},function(){
				window.clearInterval(timer);
				$("#save").attr({disabled:false});
				$('#choujiang').val('抽奖结束！');
				$('#choujiang').attr({disabled:true});
			});
			$("#save").click(function(){
				var phoneNumber=$("#zjhm").val();
				if(phoneNumber==''){
					alert("无中奖号码");
					return false;
				}
				$.ajax({
					url : "addWinner.action",
					data : "phoneNumber=" +phoneNumber,
					dataType : "json",
					type : "post",
					success : function(data) {
						alert(data.respInfo);
						$("#zjhm").val('');
					},
					error : function(data) {
						alert("error 后台出现错误！");
					}
				});

			});
		});		
		function random(){
			var number = (Math.random()*(tenInfo.length-1)).toFixed(0);
			var phonenumber=tenInfo[number].phoneNumber;
			$("#zjhm").val(phonenumber);
		}

	</script>
<button id="fresh" style="display: none;"></button>	
</body>
</html>