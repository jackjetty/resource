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
<script charset="utf-8" src="js/kindeditor-min.js"></script>
<script charset="utf-8" src="js/zh_CN.js"></script>
<script type="text/javascript" src="js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
	var editor1;
	var editor2;
   String.prototype.trim = function() {
	  return this.replace(/^\s\s*/, '').replace(/\s\s*$/, '');
	};
	KindEditor.ready(function(K) {
		editor1 = K.create('#add_publicInfo', {
			resizeType : 2,
			uploadJson : 'fileUpload.action', 
			allowFileManager : true,
			items:[
			        'source', '|', 'undo', 'redo', '|', 'justifyleft', 'justifycenter', 'justifyright',
			        'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 
			         'selectall', '|', 'fullscreen', 
			        'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
			        'italic', 'underline', 'strikethrough', 'image',
			        
			],
			afterBlur:function(){
				this.sync();
			},
			
		//取编辑器的值之前的操作
	    });
		editor2 = K.create('#edit_publicInfo', {
			resizeType : 2,
			uploadJson : 'fileUpload.action', 
			allowFileManager : true,
			items:[
			        'source', '|', 'undo', 'redo', '|', 'justifyleft', 'justifycenter', 'justifyright',
			        'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 
			         'selectall', '|', 'fullscreen', 
			        'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
			        'italic', 'underline', 'strikethrough', 'image',
			        
			],
			afterBlur:function(){
				this.sync();
			},
			
		//取编辑器的值之前的操作
	    });
	})
	function getData(startTime, endTime, publicType) {
		var rows = null;
		var dg = $("#dg");
		var opts = dg.datagrid('options');
		var pageSize = opts.pageSize;
		var pageIndex = opts.pageNumber;
		$.ajax({
			url : "getPublicInfo.action",
			async : false,
			data : "pageSize=" + pageSize + "&pageIndex=" + pageIndex
					+ "&startTime=" + startTime + "&endTime=" + endTime + "&publicType="+publicType,
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
			var publicType=$("#publicType").val();
			var newData = getData(startTime, endTime, publicType);
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
		var publicType=$("#publicType").val();
		$('#dg').datagrid({
			loadFilter : pagerFilter,
			width : width - 20,
			height : height - 45,
			onDblClickRow:onDblClickRow
		}).datagrid('loadData', getData(startTime, endTime,publicType));
		$("#searchpanel").panel({
			width : width - 20
		});
		$("#search").click(function() {
			var startTime = $("#startTime").datebox('getValue');
			var endTime = $("#endTime").datebox('getValue');
			var publicType=$("#publicType").val();
			$('#dg').datagrid({
				loadFilter : pagerFilter,
				pageNumber : 1,
			}).datagrid('loadData', getData(startTime, endTime, publicType));

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
			$("#publicType").val('null');
		});
		
		$("#addPublicInfo").click(function() {
			var publicType=$("#add_publicType").val();
			if(publicType==''){
				alert("请选择信息类型");
				return false;
			}
			var publicInfo = editor1.html();
			if (editor1.isEmpty()) {
		        alert('公告内容不能为空！');
		        return false;
		    }
			publicInfo=encodeURIComponent(publicInfo);
			var title = $("#add_title").val().trim();
			if (title == '') {
				alert("公告标题不能为空！");
				return false;
			}
			var description=$("#add_description").val();
			//var radio=$('input[name="toSend"]:checked').val();
			$.ajax({
				url : "addPublicInfo.action",
				data : "publicInfo=" + publicInfo + "&title=" + title +"&publicType="+publicType
						+"&description="+description,
				dataType : "json",
				type : "post",
				success : function(data) {
					if(data.respCode==0){
						alert(data.respInfo);
						$('#dlg').dialog('close');
						var startTime = $("#startTime").datebox('getValue');
						var endTime = $("#endTime").datebox('getValue');
						var publicType=$("#publicType").val();
						$('#dg').datagrid({
							loadFilter : pagerFilter
						}).datagrid('loadData', getData(startTime,endTime,publicType));
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
			var publicType=$("#edit_publicType").val();
			if(publicType==''){
				alert("请选择信息类型");
				return false;
			}
			var publicInfo = editor2.html();
			if (editor2.isEmpty()) {
		        alert('公告内容不能为空！');
		        return false;
		    }
			publicInfo=encodeURIComponent(publicInfo);
			var title = $("#edit_title").val().trim();
			if (title == '') {
				alert("公告标题不能为空！");
				return false;
			}
			var description=$("#edit_description").val();
			$.ajax({
				url : "updatePublicInfo.action",
				data : "publicInfo=" + publicInfo + "&title=" + title+ "&id=" + $("#edit_id").val()
						+"&publicType="+publicType+"&description="+description,
				dataType : "json",
				type : "post",
				success : function(data) {
					if(data.respCode==0){
						alert(data.respInfo);
						$('#edit-dialog').dialog('close');
						var startTime = $("#startTime").datebox('getValue');
						var endTime = $("#endTime").datebox('getValue');
						var publicType=$("#publicType").val();
						$('#dg').datagrid({
							loadFilter : pagerFilter
						}).datagrid('loadData', getData(startTime,endTime,publicType));
					}else{
						alert(data.respInfo);
					}
					
				},
				error : function(data) {
					alert("error 后台出现错误！");
				}
			});
		});
		
		
		
		
		$("#testOk").click(function() {
			
			$.ajax({
				url : "getTestInfo.action",
				data : "",
				dataType : "json",
				type : "post",
				success : function(data) {
						alert(data.rows);
					
				},
				error : function(data) {
					alert("error 后台出现错误！");
				}
			});
		});
		
		
		
		
		
	});
	function onDblClickRow(rowIndex, rowData){
		var row = $("#dg").datagrid('getSelected');
		$('#edit-dialog').dialog('open');
		$("#edit_id").val(row.id);
		var content=row.publicInfo;
		while(content.indexOf("&lt;")!=-1){
			content=content.replace("&lt;","<");
		}
		while(content.indexOf("&gt;")!=-1){
			content=content.replace("&gt;",">");
		}
		editor2.html(content);
		$("#edit_title").val(row.title);
	}
	function detail() {	
		$('#detail_dialog').dialog('open');	
		var row = $("#dg").datagrid('getSelected');
		$("#detail_nickName").html(row.nickName);
		$("#detail_phoneNumber").html(row.phoneNumber);
		$("#detail_fbtime").html(row.feedTime);
		$("#detail_fbcontent").html(row.feedText);
		$('#detail_dialog input').attr({disabled:true});
		$('#detail_dialog textarea').attr({disabled:true});	
		$('#detail_close').attr({disabled:false});
	}
	function detail_close() {
		$('#detail_dialog').dialog('close');
	}
	
	var toolbar = [ {
		text : '添加',
		iconCls : 'icon-add',
		handler : function() {
			$('#dlg').dialog('open');
			editor1.html('');
			$("#add_title").val('');
			$("#add_time").datebox('setValue','');
			$("#add_publicType").val('');
			$("#add_description").val('');
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
				$('#edit-dialog').dialog('open');
				$("#edit_id").val(row.id);
				var content=row.publicInfo;
				while(content.indexOf("&lt;")!=-1){
					content=content.replace("&lt;","<");
				}
				while(content.indexOf("&gt;")!=-1){
					content=content.replace("&gt;",">");
				}
				editor2.html(content);
				$("#edit_title").val(row.title);
				$("#edit_publicType").val(row.publicType);
				$("#edit_description").val(row.description);
				
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
						ids = ids + "," + rows[i].id;
					}
					$.ajax({
						url : "removePublicInfo.action",
						data : "ids="+ ids,
						dataType : "json",
						type : "post",
						success : function(data) {
							alert(data.respInfo);
							var startTime = $("#startTime").datebox('getValue');
							var endTime = $("#endTime").datebox('getValue');
							var publicType=$("#publicType").val();
							$('#dg').datagrid({
								loadFilter : pagerFilter
							}).datagrid('loadData', getData(startTime,endTime,publicType));
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
		window.open("page/codeInfo.jsp?codeId=" + row.id, "");
	}
	function operate(value, row, index) {
		if (row.status == '启用')
			return "<a href='javascript:exchange();'><font color='red'>禁用</font></a>";
		else if (row.status == '禁用')
			return "<a href='javascript:exchange();'>启用</a>";
	}
	function operate1(value, row, index) {
		if (row.publicType == 'Notice')
			return '<font>交通公告</font>';
		else if (row.publicType == 'Propaganda')
			return '<font>交通宣传</font>';
		/*else if (row.publicType == 'ElectronicPolice')
			return '<font>电警介绍</font>';*/
	}
	function operate3(value, row, index) {
		var newtime=value.replace("T"," ");
		return newtime;
	}
	
	function operate2(value, row, index) {
		return "<a href='javascript:toUp("+index+");' style='float:left;'>向上</a><a href='javascript:toDown("+index+");'style='float:right;'>向下</a>";
	}
	
	function toUp(index){
		if(index==0){
			alert("此信息已经在最上方了");
			return;
		}
		var arr=$("#dg").datagrid('getRows');
		var thisId=arr[index].id;
		var thisIndex=arr[index].publicIndex;
		var nextId=arr[index-1].id;
		var nextIndex=arr[index-1].publicIndex;
		$.ajax({
			url : "publicInfoUpOrDown.action",
			async : false,
			data : "thisId=" + thisId +"&nextId=" + nextId + "&thisIndex=" + thisIndex + "&nextIndex=" + nextIndex,
			dataType : "json",
			type : "post",
			success : function(data) {
				if(data.respCode==0){
					alert(data.respInfo);
					var startTime = $("#startTime").datebox('getValue');
					var endTime = $("#endTime").datebox('getValue');
					var publicType=$("#publicType").val();
					$('#dg').datagrid({
						loadFilter : pagerFilter
					}).datagrid('loadData', getData(startTime,endTime,publicType));
				}else{
					alert(data.respInfo);
				}
				
			},
			error : function(data) {
				alert("error 后台出现错误！");
			}
		});
	}
	
	function toDown(index){
		var arr=$("#dg").datagrid('getRows');
		if(index==(arr.length-1)){
			alert("此信息已经在最下方了");
			return;
		}
		var thisId=arr[index].id;
		var thisIndex=arr[index].publicIndex;
		var nextId=arr[index+1].id;
		var nextIndex=arr[index+1].publicIndex;
		$.ajax({
			url : "publicInfoUpOrDown.action",
			async : false,
			data : "thisId=" + thisId +"&nextId=" + nextId + "&thisIndex=" + thisIndex + "&nextIndex=" + nextIndex,
			dataType : "json",
			type : "post",
			success : function(data) {
				if(data.respCode==0){
					alert(data.respInfo);
					var startTime = $("#startTime").datebox('getValue');
					var endTime = $("#endTime").datebox('getValue');
					var publicType=$("#publicType").val();
					$('#dg').datagrid({
						loadFilter : pagerFilter
					}).datagrid('loadData', getData(startTime,endTime,publicType));
				}else{
					alert(data.respInfo);
				}
				
			},
			error : function(data) {
				alert("error 后台出现错误！");
			}
		});
	}
	function exchange(index){
		var row = $("#dg").datagrid('getSelected');
		$.ajax({
			url : "publicInfoStatus.action",
			async : false,
			data : "id=" + row.id,
			dataType : "json",
			type : "post",
			success : function(data) {
				if(data.respCode==0){
					alert(data.respInfo);
					var startTime = $("#startTime").datebox('getValue');
					var endTime = $("#endTime").datebox('getValue');
					var publicType=$("#publicType").val();
					$('#dg').datagrid({
						loadFilter : pagerFilter
					}).datagrid('loadData', getData(startTime,endTime,publicType));
				}else{
					alert(data.respInfo);
				}
				
			},
			error : function(data) {
				alert("error 后台出现错误！");
			}
		});
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
				<td>类型：</td>
				<td><select id="publicType"><option value="">请选择类型</option>
				<option value="Propaganda">交通宣传</option>
				<option value="Notice">交通公告</option>
				  <!--<option value="ElectronicPolice">电警介绍</option></select></td>-->
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
				<th data-options="field:'publicType',width:150,formatter:operate1,align:'center'">类型</th>
				<th data-options="field:'title',width:150,align:'center'">标题</th>
				<th data-options="field:'description',width:150,align:'center'">描述</th>
				<th data-options="field:'publicTime',width:150,align:'center',formatter:operate3">发布时间</th>
				<th data-options="field:'status',width:150,formatter:operate,align:'center'">操作</th>
				<th data-options="field:'oprat',width:80,formatter:operate2,align:'center'">操作</th>
				<th	data-options="field:'opt',align:'center',width:80,formatter:function(){return '<a href=\'javascript:detail();\'>查看</a>'}">操作</th>
			</tr>
		</thead>
	</table>

	
	<div id="dlg" class="easyui-dialog" title="添加信息"
		data-options="iconCls:'icon-save',closed:true"
		style="width: 900px; height: 500px; padding: 10px;">
		<div id="div1" style="margin-left:10px;margin-top:10px;">
			<table align="center">
				<tr>
					<td>信息类型：</td>
					<td style="text-align: left;"><select id="add_publicType" style="width: 100%;">
					<option value="">请选择类型</option>
					<option value="Notice">交通公告</option>
					<option value="Propaganda">交通宣传</option>
					<!-- <option value="ElectronicPolice">电警介绍</option></select></td> -->
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
				</tr>
				<tr>
					<td>信息标题：</td>
					<td style="text-align: left;"><input type="text" id="add_title" name="title" style="width: 99%;" /></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
				</tr>
				<tr>
					<td>描述信息：</td>
					<td style="text-align: left;"><input type="text" id="add_description" name="title" style="width: 99%;" /></td>
					<td><font>&nbsp;*&nbsp;选填</font></td>
				</tr>
				<tr>
					<td>信息内容：</td>
					<td><textarea  id="add_publicInfo" name="publicInfo1" style="width:120px;height:300px;"></textarea></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
				</tr>
				<!-- <tr>
					<td>是否群发：</td>
					<td style="text-align: left;">
					<label><input name="toSend" type="radio" value="false" checked="true"/>否</label>
					<label><input name="toSend" type="radio" value="true"/>是</label>
					</td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
				</tr> -->
			</table>
		</div>
		<div align="center" style="margin-top:20px;">
			<input type="button" value="完成" id="addPublicInfo" />
		</div>
	</div>
	
	
	
	<div id="edit-dialog" class="easyui-dialog" title="修改信息"
		data-options="iconCls:'icon-save',closed:true"
		style="width: 900px; height: 500px; padding: 10px;">
		<div id="div1" style="margin-left:10px;margin-top:10px;">
		<input type="hidden" id="edit_id" name="id" />
			<table align="center">
				<tr>
					<td>信息类型：</td>
					<td style="text-align: left;"><select id="edit_publicType" style="width: 100%;">
					<option value="">请选择类型</option>
					<option value="Notice">交通公告</option>
					<option value="Propaganda">交通宣传</option>
					<!-- <option value="ElectronicPolice">电警介绍</option></select></td> -->
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
				</tr>
				<tr>
					<td>信息标题：</td>
					<td style="text-align: left;"><input type="text" id="edit_title" name="title" style="width: 99%;" /></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
				</tr>
				<tr>
					<td>描述信息：</td>
					<td style="text-align: left;"><input type="text" id="edit_description" name="title" style="width: 99%;" /></td>
					<td><font>&nbsp;*&nbsp;选填</font></td>
				</tr>
				<tr>
					<td>信息内容：</td>
					<td><textarea  id="edit_publicInfo" name="publicInfo2" style="width:120px;height:300px;"></textarea></td>
					<td><font color="red">&nbsp;*&nbsp;必填</font></td>
				</tr>
			</table>
		</div>
		<div align="center" style="margin-top:20px;">
			<input type="button" value="保存" id="updatePublicInfo" />
		</div>
	</div>
	
	
	
	
</body>
</html>

