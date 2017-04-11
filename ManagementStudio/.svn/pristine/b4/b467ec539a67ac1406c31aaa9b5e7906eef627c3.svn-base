<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加功能菜单</title>
<link rel="stylesheet" type="text/css" href="themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="themes/icon.css">
<script type="text/javascript" src="js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
String.prototype.trim = function() {
	  return this.replace(/^\s\s*/, '').replace(/\s\s*$/, '');
	};
	
	var toolbar = [ {
		text : '回复',
		iconCls : 'icon-add',
		handler : function() {	
			var rows = $('#dg').datagrid('getSelections');
			if (rows.length == 0) {
				alert("请选择需要回复的号码！");
				return false;
			} else if (rows.length > 1) {
				alert("一次只能回复一个！");
				return false;
			} else {
				var row = $("#dg").datagrid('getSelected');
				$('#huifu_dialog').dialog('open');
				$("#toUserPhoneNumber").html(row.phoneNumber);
				$("#content").val('');
			}
			
		}
	}]
	
	
	
	function getData(phoneNumber, email) {
		var rows = null;
		var dg = $("#dg");
		var opts = dg.datagrid('options');
		var pageSize = opts.pageSize;
		var pageIndex = opts.pageNumber;
		$.ajax({
			url : "searchBackInfo.action",
			async : false,
			data : "pageSize=" + pageSize + "&pageIndex=" + pageIndex
					+ "&phoneNumber=" + phoneNumber + "&email=" + email,
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
			var phoneNumber = $("#phoneNumber").val().trim();
			var email = $("#email").val().trim();
			var newData = getData(phoneNumber, email);
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
		var phoneNumber = $("#phoneNumber").val().trim();
		var email = $("#email").val();
		$('#dg').datagrid({
			loadFilter : pagerFilter,
			width : width - 22,
			height : height - 120
		}).datagrid('loadData', getData(phoneNumber, email));
		$("#searchpanel").panel({
			width : width - 22
		});
		$("#search").click(function() {
			phoneNumber = $("#phoneNumber").val().trim();
			var email = $("#email").val().trim();
			$('#dg').datagrid({
				loadFilter : pagerFilter,
				pageNumber : 1,
			}).datagrid('loadData', getData(phoneNumber, email));

		});
		$("#clear").click(function(){
			$("#phoneNumber").val('');
			$("#email").val('');
			
		});
		
		$("#edit_save").click(function(){
			var row = $("#dg").datagrid('getSelected');
			var procedureMessage=$("#procedureMessage").val().trim();
			var fbid=row.fbid;
			$.ajax({
				url : "saveProcedureMessage.action",
				data : "procedureMessage="+ procedureMessage+"&fbid="+fbid,
				dataType : "json",
				type : "post",
				success : function(data) {
					if(data.respCode==0){
						alert(data.respInfo);
						$('#edit_dialog').dialog('close');
						phoneNumber = $("#phoneNumber").val().trim();
						email = $("#email").val();
						$('#dg').datagrid({
							loadFilter : pagerFilter
						}).datagrid('loadData', getData(phoneNumber, email));
					}else{
						alert(data.respInfo);
					}
					
				},
				error : function(data) {
					alert("error 后台出现错误！");
				}
			});
			
		});
		
		
		$("#huifu_save").click(function(){
			var row = $("#dg").datagrid('getSelected');
			var content=$("#content").val().trim();
			var toUserPhoneNumber=row.phoneNumber;
			if (content == '') {
				alert("回复内容不能为空！");
				return false;
			}
			$.ajax({
				url : "saveAnswerFeedBack.action",
				data : "content="+ content+"&toUserPhoneNumber="+toUserPhoneNumber,
				dataType : "json",
				type : "post",
				success : function(data) {
					if(data.respCode==0){
						alert(data.respInfo);
						$('#huifu_dialog').dialog('close');
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
	var Status = [ {
		status : '已处理',
		statusName : '已处理'
	}, {
		status : '未处理',
		statusName : '未处理'
	}, {
		status : '处理中',
		statusName : '处理中'
	} ];
	var href1="<a href=\'javascript:editStatus();\'>";
	var href2="</a>";
	function statusFormatter(value) {
		for ( var i = 0; i < Status.length; i++) {
			if (Status[i].status == value)
				return href1+Status[i].statusName+href2;
		}
	}
	function detail() {	
		$('#detail_dialog').dialog('open');	
		var row = $("#dg").datagrid('getSelected');
		$("#detail_phoneNumber").val(row.phoneNumber);
		$("#detail_email").val(row.email);
		$("#detail_contactnumber").val(row.contactnumber);
		$("#detail_fbtime").val(row.fbtime);
		$("#detail_fbcontent").val(row.fbcontent);
		$('#detail_dialog input').attr({disabled:true});
		$('#detail_dialog textarea').attr({disabled:true});	
		$('#detail_close').attr({disabled:false});
	}
	function detail_close() {
		$('#detail_dialog').dialog('close');
	}
	function editStatus(){
		$('#edit_dialog').dialog('open');
		var row = $("#dg").datagrid('getSelected');
		$("#procedureMessage").val(row.procedureMessage);
	}
	
	
</script>
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

#div1 td {
	width: 400px;
	text-align: center;
	height: 25px;
}
-->
</style>
</head>
<body style="background: #DFE8F6;" id="tt">
	<input type="hidden" id="editRoleId" />

	<div id="searchpanel" class="easyui-panel panel-margin-buttom"
		title="反馈信息查询" style="height: 80px; padding: 10px;vertical-align: center;">
		<div style="float:left;vertical-align: center;height:31px;line-height:31px;">
		登录号码：<input type="text" id="phoneNumber" />&nbsp;&nbsp;&nbsp;联系邮箱：<input type="text"
			id="email" /></div>
			<div style="height:100%;float:left;margin-left: 15px;">
			<a id="search" class="pushBtn" href="javascript:void(0);" hidefocus="true">
				<img width="20" height="20" src="images/icon/search.gif">
				<b>查询</b>
			</a>
			<a id="clear" class="pushBtn" href="javascript:void(0);" hidefocus="true">
				<b>条件清空</b>
			</a></div>
	</div>
	<table id="dg" class="easyui-datagrid" title="反馈信息列表"
		data-options="rownumbers:true,singleSelect:true,toolbar:toolbar,pagination:true,
				pageSize:20,remoteSort:false,fitColumns:true,idField:'phoneNumber',
				multiSort:true">
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<!-- th data-options="field:'fbid',width:150,align:'center'">反馈ID</th> -->
				<th data-options="field:'phoneNumber',width:150">登录号码</th>
				<th	data-options="field:'email',width:200,sortable:true">联系邮箱</th>
				<th	data-options="field:'contactnumber',width:150,sortable:true">联系电话</th>
				<th data-options="field:'fbcontent',width:400">反馈内容</th>
				<th data-options="field:'fbtime',width:150">反馈时间</th>
				<th	data-options="field:'opt',align:'center',width:80,formatter:function(){return '<a href=\'javascript:detail();\'>详情</a>'}">操作</th>
				<th	data-options="field:'status',align:'center',width:150,formatter:statusFormatter,
						editor:{
							type:'',
							options:{
								valueField:'status',
								data:Status,
								required:true
							}
						}">处理情况</th>
			</tr>
		</thead>
	</table>

	<div id="detail_dialog" class="easyui-dialog" title="详情"
		data-options="closed:true"
		style="width: 500px; height: 420px; padding: 10px;">
		<div id="div1"
			style="margin-left: 2px; margin-right: 2px; margin-top: 10px;">
			<table cellspacing="0">
				<tr>
					<td style="text-align:right;border:1px solid #CCCCCC;background-color:#F5F7F7;">登录号码：</td>
					<td style="text-align:left;border:1px solid #CCCCCC;border-left:0px;">&nbsp;<input id="detail_phoneNumber" style="width:200px;border:1px solid #CCCCCC;" type="text" /></td>
				</tr>
				<tr>
					<td style="text-align:right;border:1px solid #CCCCCC;border-top:0px;background-color:#F5F7F7;">联系邮箱：</td>
					<td style="text-align:left;border:1px solid #CCCCCC;border-left:0px;border-top:0px;">&nbsp;<input id="detail_email" style="width:200px;border:1px solid #CCCCCC;" type="text"  /></td>
				</tr>
				<tr>
					<td style="text-align:right;border:1px solid #CCCCCC;border-top:0px;background-color:#F5F7F7;">联系电话：</td>
					<td style="text-align:left;border:1px solid #CCCCCC;border-left:0px;border-top:0px;">&nbsp;<input id="detail_contactnumber" style="width:200px;border:1px solid #CCCCCC;" type="text" /></td>
				</tr>
				<tr>
					<td style="text-align:right;border:1px solid #CCCCCC;border-top:0px;background-color:#F5F7F7;">反馈时间：</td>
					<td style="text-align:left;border:1px solid #CCCCCC;border-left:0px;border-top:0px;">&nbsp;<input id="detail_fbtime" style="width:200px;border:1px solid #CCCCCC;" type="text"/></td>
				</tr>
				<tr>
					<td style="text-align:right;border:1px solid #CCCCCC;border-top:0px;background-color:#F5F7F7;">反馈内容：</td>
					<td style="text-align:left;border:1px solid #CCCCCC;border-left:0px;border-top:0px;">&nbsp;<textarea id="detail_fbcontent" rows="8" style="width:197px;border:1px solid #CCCCCC;"></textarea></td>
				</tr>
			</table>
		</div>
		<div align="center" style="margin-top: 15px;">
			<input style="background-image:url('images/back.jpg'); width:55px;height:30px;" type="button" value="" onclick="detail_close()" id="detail_close"/>
		</div>
	</div>
	
	<div id="edit_dialog" class="easyui-dialog" title="处理过程"
		data-options="closed:true"
		style="width: 400px; height: 270px; padding: 10px;">
		<div id="div1"
			style="margin-left: 2px; margin-right: 2px; margin-top: 20px;">
			<table cellspacing="0">
				<tr>
					<td style="text-align:right;border:1px solid #CCCCCC;background-color:#F5F7F7;" valign="top"><div align="center" style="margin-top: 10px;">处理过程：</div></td>
					<td style="text-align:left;border:1px solid #CCCCCC;border-left:0px;" ><textarea rows="6" cols="30" id="procedureMessage" style="padding-top:5px;" ></textarea></td>
				</tr>
			</table>
		</div>
		<div align="center" style="margin-top: 20px;">
			<input style="width:55px;height:30px;" type="button" value="保存"  id="edit_save"/>
		</div>
	</div>
	
	
	<div id="huifu_dialog" class="easyui-dialog" title="回复"
		data-options="closed:true"
		style="width: 400px; height: 300px; padding: 10px;">
		<div id="div1"
			style="margin-left: 2px; margin-right: 2px; margin-top: 20px;">
			<table cellspacing="0">
				<tr>
					<td style="width:30%;border:1px solid #CCCCCC;background-color:#F5F7F7;" >手机号码:</td>
					<td style="width:70%;text-align:left;border:1px solid #CCCCCC;border-left:0px;" id="toUserPhoneNumber"></td>
				</tr>
				<tr>
					<td style="width:30%;text-align:right;border:1px solid #CCCCCC;background-color:#F5F7F7;" valign="top"><div align="center" style="margin-top: 10px;">回复内容:</div></td>
					<td style="width:70%;text-align:left;border:1px solid #CCCCCC;border-left:0px;" >
					<textarea rows="6" cols="30" id="content" style="padding-top:5px;" ></textarea></td>
				</tr>
			</table>
		</div>
		<div align="center" style="margin-top: 20px;">
			<input style="width:55px;height:30px;" type="button" value="保存"  id="huifu_save"/>
		</div>
	</div>
	
</body>
</html>

