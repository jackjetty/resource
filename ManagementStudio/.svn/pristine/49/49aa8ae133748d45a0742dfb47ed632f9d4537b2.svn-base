<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link rel="stylesheet" type="text/css" href="themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="themes/icon.css">
<link rel="stylesheet" href="themes/default/default.css" />
<script charset="utf-8" src="js/kindeditor-min.js"></script>
<script charset="utf-8" src="js/zh_CN.js"></script>
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
blockquote {
top:0;
buttom:0;
display:inline;
}

-->
</style>
</head>
<body style="background: #DFE8F6;">
	<input type="hidden" id="editProductId" />
	<table id="dg" class="easyui-datagrid" title="公告信息管理"
		data-options="rownumbers:true,singleSelect:false,toolbar:toolbar,pagination:true,
				pageSize:20,remoteSort:false,fitColumns:true,
				multiSort:true">
		<thead>
			<tr>
				   <th data-options="field:'ck',checkbox:true"></th>
					<!--  th data-options="field:'id',width:150">数据库自增长标识</th>-->
					<th id="productId" data-options="field:'publicInfo',width:200">公告内容</th>
					<th id="title"data-options="field:'title',width:50">公告标题</th>
					<th id="time"data-options="field:'time',width:50">公告时间</th>
			</tr>
		</thead>
	</table>
	<script type="text/javascript">
	var editor1;
	var editor2;
	function getData() {
		var rows = null;
		var dg = $("#dg");
		var opts = dg.datagrid('options');
		var pageSize = opts.pageSize;
		var pageIndex = opts.pageNumber;
		$.ajax({
			url : "getPublicInfoList.action",
			async : false,
			data : "pageSize=" + pageSize + "&pageIndex=" + pageIndex ,
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
			var newData = getData();
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
	KindEditor.ready(function(K) {
		editor1 = KindEditor.create('textarea[name="publicInfo1"]', {
			allowFileManager : true,
			items:[
			        'source', '|', 'undo', 'redo', '|', 'cut', 'copy', 'paste',
			        'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright',
			        'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 
			        'clearhtml', 'quickformat', 'selectall', '|', 'fullscreen', '/',
			        'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
			        'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|', 'image',
			        'table', 'emoticons', 
			],
			afterBlur:function(){
				this.sync();
			}
		//取编辑器的值之前的操作
	    });
		editor2 = KindEditor.create('textarea[name="publicInfo2"]', {
			allowFileManager : true,
			items:[
			        'source', '|', 'undo', 'redo', '|', 'cut', 'copy', 'paste',
			        'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright',
			        'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 
			        'clearhtml', 'quickformat', 'selectall', '|', 'fullscreen', '/',
			        'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
			        'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|', 'image',
			        'table', 'emoticons'
			],
			afterBlur:function(){
				this.sync();
			}
		//取编辑器的值之前的操作
	    });
	 });
	
	$(function() {
		var width = $(document).width();
		var height = $(document).height();
		$('#dg').datagrid({
			loadFilter : pagerFilter,
			width : width - 18,
			height : height - 20
		}).datagrid(
				'loadData',
				getData());
		$("#searchpanel").panel({
			width : width - 18
		});
	$("#addPublicInfo").click(function() {
		var publicInfo = editor1.html();
		if (editor1.isEmpty()) {
	        alert('公告信息不能为空！');
	        return false;
	    }
		var title = $("#add_title").val().trim();
		if (title == '') {
			alert("公告标题不能为空！");
			return false;
		}
		var time = $("#add_time").datebox('getValue');
		if(time==''){
			alert("公告时间不能为空");
			return false;
		}
		var width = $(document).width();
		var height = $(document).height();
		$.ajax({
			url : "addPublicInfo.action",
			data : "publicInfo=" + publicInfo + "&title=" + title
			    	+ "&time=" + time,
			dataType : "json",
			type : "post",
			success : function(data) {
				if(data.respCode==0){
					alert(data.respInfo);
					$('#dlg').dialog('close');
					$('#dg').datagrid({
						loadFilter : pagerFilter
					}).datagrid('loadData', getData());
				}else{
					alert(data.respInfo);
				}
				
			},
			error : function(data) {
				alert("error 后台出现错误！");
			}
		});
	});

$("#updatePublicInfo").click(function() {
	var publicInfo = editor2.html();
	if (editor2.isEmpty()) {
        alert('公告信息不能为空！');
        return false;
    }
	var title = $("#edit_title").val().trim();
	if (title == '') {
		alert("公告标题不能为空！");
		return false;
	}
	var time = $("#edit_time").datebox('getValue');
	if(time==''){
		alert("公告时间不能为空");
		return false;
	}
	var width = $(document).width();
	var height = $(document).height();
	$.ajax({
		url : "updatePublicInfo.action",
		async : false,
		data :"publicInfo=" + publicInfo + "&title=" + title
    	+ "&time=" + time +"&id="+$("#edit_id").val(),
		dataType : "json",
		type : "post",
		success : function(data) {
			alert(data.respInfo);
			if (data.respCode == 0) {
				$("#edit-dialog").dialog("close");
				$('#dg').datagrid({
					loadFilter : pagerFilter,
					width : width - 18,
					height : height - 20
				}).datagrid('loadData', getData());
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
			$("#dlg").dialog("open");
			editor1.html('');
			$("#add_title").val('');
			$("#add_time").datebox('setValue','');
		
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
				return false;
			} else {
				var row = $("#dg").datagrid('getSelected');
				$('#edit-dialog').dialog('open');
				$("#edit_id").val(row.id);
				editor2.html(row.publicInfo);
				$("#edit_title").val(row.title);
				$("#edit_time").datebox('setValue',row.time);
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
					url : "removePublicInfo.action",
					data : "ids="+ ids,
					dataType : "json",
					type : "post",
					success : function(data) {
						alert(data.respInfo);
						$('#dg').datagrid({
							loadFilter : pagerFilter,
						}).datagrid('loadData', getData());
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
	String.prototype.trim = function() {
		  return this.replace(/^\s\s*/, '').replace(/\s\s*$/, '');
	};
	</script>
	<div id="dlg" class="easyui-dialog" title="添加公告信息"
		data-options="iconCls:'icon-save',closed:true"
		style="width: 900px; height: 500px; padding: 10px; background: #DFE8F6;">
		<div id="div1" style="margin-left:10px;margin-top:10px;">
			<table align="center">
				<tr>
					<td>公告标题：</td>
					<td><input type="text" id="add_title" name="title" style="width: 120px;" /></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
				</tr>
				<tr>
					<td>公告内容：</td>
					<td><textarea  id="add_publicInfo" name="publicInfo1" style="width:120px;height:300px;"></textarea></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
				</tr>
				<tr>
					<td>公告时间：</td>
					<td><input type="text" id="add_time" class="easyui-datebox" editable="false" name="time" style="width: 120px;" /></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
				</tr>
			</table>
		</div>
		<div align="center" style="margin-top:20px;">
			<input type="button" value="添加" id="addPublicInfo" />
		</div>
	</div>
	
	
	<div id="edit-dialog" class="easyui-dialog" title="修改公告信息"
		data-options="iconCls:'icon-edit',closed:true"
		style="width: 900px; height: 500px; padding: 10px; background: #DFE8F6;">
		<div id="div2" style="margin-left:10px;margin-top:20px;">
			<input type="hidden" id="edit_id" name="id" />
			<table align="center">
				<tr>
					<td>公告标题：</td>
					<td><input type="text" id="edit_title" style="width: 120px;" /></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
				</tr>
				<tr>
					<td>公告内容：</td>
					<td><textarea rows="6" cols="19" id="edit_publicInfo" name="publicInfo2" style="width:120px;height:300px;"></textarea></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
				</tr>
				<tr>
					<td>公告时间：</td>
					<td><input type="text" id="edit_time" class="easyui-datebox" editable="false" style="width: 120px;" /></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
				</tr>
			</table>
		</div>
		<div align="center" style="margin-top:10px;">
			<input type="button" value="修改" id="updatePublicInfo" />
		</div>
	</div>
	
</body>
</html>

