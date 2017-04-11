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
#div1 td{
	height: 35px;
}
-->
</style>
</head>
<body style="background: #DFE8F6;">
	<div id="searchpanel"class="easyui-panel panel-margin-buttom" title="短信文字查询"
		style="height: 80px; padding: 10px;">	
		<table>
			<tr	style="Valign: center; text-decoration: none; line-heigth: 30px; height: 30px; margin-left: 50px;">
				<td>奖品名称：</td>
				<td><select id="prizeId" style="width: 115px; height: 23px;">
				</select></td>
				<td>地区：</td>
				<td><select id="place" style="width: 115px; height: 23px;">					
				</select></td>
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
	<table id="dg" class="easyui-datagrid" title="短息文字列表"
		data-options="rownumbers:true,singleSelect:false,toolbar:toolbar,pagination:true,
				pageSize:20,remoteSort:false,fitColumns:true,
				multiSort:true">
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field:'prizeName',width:30,sortable:true">奖品名称</th>
				<th data-options="field:'placeName',width:30,sortable:true">地区</th>
				<th data-options="field:'content',width:100">短信内容模版</th>
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
				$.ajax({
					url : "getPrizeInfo.action",
					async : false,
					data : "",
					dataType : "json",
					type : "post",
					success : function(data) {
						option = $("<option id='number1' value='null'>请选择抽奖奖品</option>");
						$("#add_prizeId").append(option);
						for(var i=0;i<data.length;i++){
							option = $("<option value='"+data[i].prizeId+"'>"+data[i].name+"</option>");
							$("#add_prizeId").append(option);
						}
					},
					error : function(data) {
						alert("error 后台出现错误！");
					}
				});
				$.ajax({
					url : "getPlaceInfo.action",
					async : false,
					data : "",
					dataType : "json",
					type : "post",
					success : function(data) {
						option = $("<option id='number1' value='null'>全省</option>");
						$("#add_place").append(option);
						for(var i=0;i<data.length;i++){
							option = $("<option value='"+data[i].code+"'>"+data[i].name+"</option>");
							$("#add_place").append(option);
						}
					},
					error : function(data) {
						alert("error 后台出现错误！");
					}
				});
				$("#add_prizeId").val('null');
				$("#add_place").val('null');
				$("#add_content").val('');
				$('#dlg').dialog('open');
				
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
					$.ajax({
						url : "getPrizeInfo.action",
						async : false,
						data : "",
						dataType : "json",
						type : "post",
						success : function(data) {
							option = $("<option id='number1' value='null'>请选择抽奖奖品</option>");
							$("#edit_prizeId").append(option);
							for(var i=0;i<data.length;i++){
								option = $("<option value='"+data[i].prizeId+"'>"+data[i].name+"</option>");
								$("#edit_prizeId").append(option);
							}
						},
						error : function(data) {
							alert("error 后台出现错误！");
						}
					});
					$.ajax({
						url : "getPlaceInfo.action",
						async : false,
						data : "",
						dataType : "json",
						type : "post",
						success : function(data) {
							option = $("<option id='number1' value='null'>全省</option>");
							$("#edit_place").append(option);
							for(var i=0;i<data.length;i++){
								option = $("<option value='"+data[i].code+"'>"+data[i].name+"</option>");
								$("#edit_place").append(option);
							}
						},
						error : function(data) {
							alert("error 后台出现错误！");
						}
					});
					var row = $("#dg").datagrid('getSelected');
					$("#edit_id").val(row.id);
					$('#edit-dialog').dialog('open');
					$("#edit_prizeId").val(row.prizeId);
					$("#edit_place").val(row.place);
					$("#edit_content").val(row.content);
					
				}
			}

		}, '-', {
			text : '删除',
			iconCls : 'icon-remove',
			handler : function() {
				var rows = $('#dg').datagrid('getSelections');
				if(rows.length==0){
					alert("请选择要删除的数据！");
					return false;
				}
				if(window.confirm("确认删除这"+rows.length+"记录？")){
					var ids = '';
					for(var i=0;i<rows.length;i++){
						ids = ids + ","+rows[i].id;
					}
					$.ajax({
						url : "removePrizeShortMessage.action",
						data : "ids="+ ids,
						dataType : "json",
						type : "post",
						success : function(data) {
							alert(data.respInfo);
							var prizeId = $("#prizeId").val();
							var place = $("#place").val();
							$('#dg').datagrid({
								loadFilter : pagerFilter
							}).datagrid('loadData', getData(prizeId, place));
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
				var prizeId = $("#prizeId").val().trim();
				var place = $("#place").val().trim();
				var newData = getData(prizeId,place);
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
		function getData(prizeId, place) {
			if(prizeId=='null'){
				prizeId = "";
			}
			if(place=='null'){
				place = "";
			}
			var rows = null;
			var dg = $("#dg");
			var opts = dg.datagrid('options');
			var pageSize = opts.pageSize;
			var pageIndex = opts.pageNumber;
			$.ajax({
				url : "getPrizeShortMessage.action",
				async : false,
				data : "pageSize=" + pageSize + "&pageIndex=" + pageIndex
						+ "&prizeId=" + prizeId + "&place=" + place,
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
				url : "getPrizeInfo.action",
				async : false,
				data : "",
				dataType : "json",
				type : "post",
				success : function(data) {
					option = $("<option id='number1' value='null'>请选择抽奖奖品</option>");
					$("#prizeId").append(option);
					for(var i=0;i<data.length;i++){
						option = $("<option value='"+data[i].prizeId+"'>"+data[i].name+"</option>");
						$("#prizeId").append(option);
					}
				},
				error : function(data) {
					alert("error 后台出现错误！");
				}
			});
			$.ajax({
				url : "getPlaceInfo.action",
				async : false,
				data : "",
				dataType : "json",
				type : "post",
				success : function(data) {
					option = $("<option id='number1' value='null'>请选择地区信息</option>");
					$("#place").append(option);
					for(var i=0;i<data.length;i++){
						if(data[i].code == place){
							option = $("<option value='"+data[i].code+"' selected>"+data[i].name+"</option>");
						}else{
							option = $("<option value='"+data[i].code+"'>"+data[i].name+"</option>");
						}
						$("#place").append(option);
					}
				},
				error : function(data) {
					alert("error 后台出现错误！");
				}
			});
			var width = $(document).width();
			var height = $(document).height();
			var prizeId = $("#prizeId").val();
			var place = $("#place").val();
			$('#dg').datagrid({
				loadFilter : pagerFilter,
				width:width-18,
				height:height-120
			}).datagrid('loadData', getData(prizeId, place));
			
			$("#searchpanel").panel({width:width-18});
			$("#search").click(function() {
				var prizeId = $("#prizeId").val();
				var place = $("#place").val();
				$('#dg').datagrid({
					loadFilter : pagerFilter,
					pageNumber : 1
				}).datagrid('loadData', getData(prizeId, place));
			});
			$("#clear").click(function(){
				$("#prizeId").val('null');
				$("#place").val('null');
			});
			$("#addPrizeShortMessage").click(
					function() {
						var prizeId = $("#add_prizeId").val();
						var place = $("#add_place").val();
						var content = $("#add_content").val();
						if (prizeId == 'null') {
							alert('请选择奖品种类！');
							return false;
						}
						if(content==''){
							alert('请输入短信模版内容！');
							return false;
						}
						$.ajax({
							url : "addPrizeShortMessage.action",
							data : "prizeId=" + prizeId + "&content=" + content
									+ "&place=" + place,
							dataType : "json",
							type : "post",
							success : function(data) {
								if(data.respCode==0){
									alert(data.respInfo);
									$('#dlg').dialog('close');
									var prizeId = $("#prizeId").val();
									var place = $("#place").val();
									$('#dg').datagrid({
										loadFilter : pagerFilter
									}).datagrid('loadData', getData(prizeId, place));
								}else{
									alert(data.respInfo);
								}
								
							},
							error : function(data) {
								alert("error 后台出现错误！");
							}
						});
					});
			$("#updatePrizeShortMessage").click(
					function() {
						var prizeId  = $("#edit_prizeId").val();
						var content = $("#edit_content").val();
						var place =$("#edit_place").val();
						if (prizeId == 'null') {
							alert('请选择奖品种类！');
							return false;
						}
						if(content==''){
							alert('请输入短信模版内容！');
							return false;
						}
						
						$.ajax({
							url : "updatePrizeShortMessage.action",
							data : "prizeId=" + prizeId + "&content=" + content
							+ "&place=" + place +"&id=" +$("#edit_id").val(),
							dataType : "json",
							type : "post",
							success : function(data) {
								if(data.respCode==0){
									alert(data.respInfo);
									$('#edit-dialog').dialog('close');
									var prizeId = $("#prizeId").val();
									var place = $("#place").val();
									$('#dg').datagrid({
										loadFilter : pagerFilter
									}).datagrid('loadData', getData(prizeId, place));
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
		

	</script>
	
	<div id="dlg" class="easyui-dialog" title="添加短信信息"
		data-options="iconCls:'icon-save',closed:true"
		style="width: 420px; height: 350px; padding: 10px; background: #DFE8F6;">
		<div id="div1" style="margin-left:10px;margin-top:20px;">
			<table align="center">
				<tr>
					<td>奖品种类：</td>
					<td><select id="add_prizeId" style="width: 180px; height: 23px;"></select></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
				</tr>
				<tr>
					<td>选择地区：</td>
					<td><select id="add_place" style="width:180px;"></select></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
				</tr>
				<tr>
					<td>内容模版：</td>
					<td><textarea rows="6" cols="19" id="add_content" style="width:180px;"></textarea></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
				</tr>
			</table>
			
		</div>
		<div align="center" style="margin-top:10px;">
			<input type="button" value="添加" id="addPrizeShortMessage" />
		</div>
	</div>
	
	
	<div id="edit-dialog" class="easyui-dialog" title="修改短信信息"
		data-options="iconCls:'icon-edit',closed:true"
		style="width: 420px; height: 315px; padding: 10px; background: #DFE8F6;">
		<div id="div2" style="margin-left:10px;margin-top:20px;">
			<input type="hidden" id="edit_id" />
			<table align="center">
				<tr>
					<td>奖品种类：</td>
					<td><select id="edit_prizeId" style="width:180px;"></select></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
				</tr>
				<tr>
					<td>选择地区：</td>
					<td><select id="edit_place" style="width:180px;"></select></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
				</tr>
				<tr>
					<td>内容模版：</td>
					<td><textarea rows="6" cols="19" id="edit_content" style="width:180px;"></textarea></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
				</tr>
			</table>
			
		</div>
		<div align="center" style="margin-top:10px;">
			<input type="button" value="更新" id="updatePrizeShortMessage" />
		</div>
	</div>
	
</body>
</html>