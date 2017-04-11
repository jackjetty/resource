<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
	function getData(startTime,endTime,feedBackState) {
		var rows = null;
		var dg = $("#dg");
		var opts = dg.datagrid('options');
		var pageSize = opts.pageSize;
		var pageIndex = opts.pageNumber;
		$.ajax({
			url : "getFeedBack.action",
			async : false,
			data : "pageSize=" + pageSize + "&pageIndex=" + pageIndex
					+ "&startTime=" + startTime + "&endTime=" + endTime
					+ "&feedBackState=" + feedBackState,
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
		if (typeof data.originalRows == 'undefined') {
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
		} else {
			var startTime = $("#startTime").datebox('getValue');
			var endTime = $("#endTime").datebox('getValue');
			var feedBackState = $("#feedBackState").val().trim();
			var newData = getData(startTime, endTime,feedBackState);
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
		today.setDate((today.getDate() - 6));
		var year = today.getFullYear();
		var month = today.getMonth() + 1;
		var day = today.getDate();
		if (month < 10) {
			month = "0" + month;
		}
		if (day < 10) {
			day = "0" + day;
		}
		var startdate = year + "-" + month + "-" + day;
		$("#startTime").datebox('setValue', startdate);
		$("#endTime").datebox('setValue', enddate);
		var width = $(document).width();
		var height = $(document).height();
		var startTime = $("#startTime").datebox('getValue');
		var endTime = $("#endTime").datebox('getValue');
		var feedBackState = $("#feedBackState").val();
		$('#dg').datagrid({
			loadFilter : pagerFilter,
			width : width - 20,
			height : height - 45
		}).datagrid('loadData', getData(startTime, endTime,feedBackState));
		$("#searchpanel").panel({
			width : width - 20
		});
		$("#search").click(function() {
			var startTime = $("#startTime").datebox('getValue');
			var endTime = $("#endTime").datebox('getValue');
			var feedBackState=$("#feedBackState").val();
			$('#dg').datagrid({
				loadFilter : pagerFilter,
				pageNumber : 1,
			}).datagrid('loadData', getData(startTime, endTime,feedBackState));

		});
		$("#clear").click(function() {
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
			today.setDate((today.getDate() - 6));
			var year = today.getFullYear();
			var month = today.getMonth() + 1;
			var day = today.getDate();
			if (month < 10) {
				month = "0" + month;
			}
			if (day < 10) {
				day = "0" + day;
			}
			var startdate = year + "-" + month + "-" + day;
			$("#startTime").datebox('setValue', startdate);
			$("#endTime").datebox('setValue', enddate);
			$("#feedBackState").val("");
		});

	});
	function rowformater(value, row, index) {
		return "<a href=\"javascript:;\" onclick=\"view('"
				+ row.feedBackId + "')\">"+row.op1+"</a>";
	}
	function view(id) {
		$("#tablehf").empty();
		$("#btnHuifu").val('');
		$("#backId").val(id);
		$('#detail_dialog').dialog('open');
		$('#dg').datagrid('clearSelections');
		var json = getView(id);
		for ( var i = 0; i < json.length; i++) {
			var tab1="";
			if(json[i].type=="1"){
				tab1 += "<tr align=\"left\"><td><div style=\"margin-left:40px;\">"
					+ json[i].feedTime
					+ "</div><div style=\"float:left;margin-top:10px;\"><img src=\"image/touxiang.jpg\" /></div><div id=\"haha\" class=\"triangle-right left\" style=\"float:left\" >"
					+ json[i].feedText + "</div></td></tr> ";
			}else{
				tab1 += "<tr align=\"right\"><td><div style=\"margin-right:40px;\">"
					+ json[i].feedTime
					+ "</div><div style=\"float:right;margin-top:10px;\"><img src=\"image/touxiang.jpg\" /><br>"+json[i].nickName+"</div><div id=\"haha\" class=\"triangle-right right\" style=\"float:right\" >"
					+ json[i].feedText + "</div></td></tr> ";	
			}
			
			$("#tablehf").append(tab1);
			$("#textDJ").click();
			
			 
		}

		
		$('#detail_close').attr({
			disabled : false
		});
	}
	function getView(id) {
		var json = null;
		$.ajax({
			url : "getUserFbInfo.action",
			async : false,
			data : "feedBackId=" + id,
			dataType : "json",
			type : "post",
			success : function(data) {
				json = data.rows;

			},
			error : function(data) {
				alert("error 后台出现错误！");
			}
		});
		return json;
	}
	function detail_close() {
		$('#detail_dialog').dialog('close');
	}
	
	$(function(){
	    $('#export').linkbutton({
	        text:'导出数据',
	        iconCls:"icon-add",
	        plain:true
	    }); 
	    $('#export').click(function(){ 
	    	 
				var startTime = $("#startTime").datebox('getValue');
				var endTime = $("#endTime").datebox('getValue');
				var feedBackState = $("#feedBackState").val();
				if (window.confirm("确认导出" + startTime + "到" + endTime
						+ "期间的记录？")) {
					var canshu = "startTime=" + startTime + "&endTime="
							+ endTime+ "&feedBackState="+ feedBackState;
					var lianjie = "exportFbInfo.action?" + canshu;
					window.location.href = lianjie;
				} else {
					return false;
				}
			 
	    });
	});

	 
	
function huifu(){
	var content=$("#btnHuifu").val().trim();
	if(content==""){
		alert("请输入回复内容");
		return;
	}
	$.ajax({
		url : "addFeedCustRes.action",
		async : false,
		data : "feedBackId=" + $("#backId").val()+"&content="+content,
		dataType : "json",
		type : "post",
		success : function(data) {
			if(data.error==0){
				alert("回复成功！");
				var tab="<tr align='right'><td style=''><div style='margin-right:40px;'>"+data.time+"</div><div style='float:right;margin-top:10px;'><img src='image/touxiang.jpg' /></div><div id='haha' class='triangle-right right' style='float:right' >"+content+"</div></td></tr>";
				$("#tablehf").append(tab);
				$("#textDJ").click();
				$("#btnHuifu").val('');
			}else if(data.error==1){
				alert("保存成功！但发送失败！");
			}else{
				alert("网络繁忙!请稍后再试!");
			}
		},
		error : function(data) {
			alert("网络繁忙!请稍后再试!");
		}
	});
	
}
</script>
<style type="text/css">
<!--
*{margin:0;padding:0;}
.panel-margin-buttom {
	margin-bottom: 20px;
}

#searchpanel a.pushBtn {
	-moz-user-select: none;
	background: url("image/button/btnout_bg_left.gif") no-repeat scroll left
		top transparent;
	float: left;
	height: 24px;
	margin: 2px 5px 0 0;
	outline: medium none;
	text-decoration: none;
}

#searchpanel a.pushBtn img {
	float: left;
	margin: 2px 0 4px 6px;
	border: 0;
}

#searchpanel a.pushBtn b {
	background: url("image/button/btnout_bg_right.gif") no-repeat scroll
		right top transparent;
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
	background: url("image/button/btnover_bg_left.gif") no-repeat scroll
		left top transparent;
	text-decoration: none;
}

#searchpanel a.pushBtn:hover b {
	background: url("image/button/btnover_bg_right.gif") no-repeat scroll
		right top transparent;
	color: #114477;
	font-size: 12px;
}

.triangle-right {
	text-align: center;
	width: auto;
	max-width: 200px;
	padding: 0 0 50px;
	margin: 0 auto;
	position: relative;
	padding: 10px;
	margin: 0 0 1em;
	color: #1A1A1A;
	background: #075698;
	/* css3 */
	background: -webkit-gradient(linear, 0 0, 0 100%, from(#2e88c4),
		to(#075698));
	background: -moz-linear-gradient(#2e88c4, #075698);
	background: -o-linear-gradient(#2e88c4, #075698);
	background: linear-gradient(#2e88c4, #075698);
	-webkit-border-radius: 10px;
	-moz-border-radius: 10px;
	-moz-box-shadow: 1px 1px 5px #909090; /*firefox*/
	-webkit-box-shadow: 1px 1px 5px #909090; /*safari或chrome*/
	box-shadow: 1px 1px 5px #909090; /*opera或ie9*/
	border-radius: 10px;
}

.triangle-right.left {
	margin-left: 20px;
	background: #E6E6FA;
}

.triangle-right.right {
	margin-right: 20px;
	background: #9ACD32;
}

.triangle-right:after {
	content: "";
	position: absolute;
	bottom: -20px; /* value = - border-top-width - border-bottom-width */
	left: 50px; /* controls horizontal position */
	border-width: 10px 0 0 10px;
	/* vary these values to change the angle of the vertex */
	border-style: solid;
	border-color: #075698 transparent;
	display: block;
	width: 0;
}

.triangle-right.left:after {
	top: 10px;
	left: -10px; /* value = - border-left-width - border-right-width */
	bottom: auto;
	border-width: 15px 15px 0 0;
	/* vary these values to change the angle of the vertex */
	border-color: transparent #E6E6FA;
}

.triangle-right.right:after {
	top: 10px;
	right: -10px; /* value = - border-left-width - border-right-width */
	bottom: auto;
	left: auto;
	border-width: 15px 0 0 15px;
	/* vary these values to change the angle of the vertex */
	border-color: transparent #9ACD32;
}

#tablehf {
	border-radius: 10px;
	width:100%;
}

.pushBtn1 {
	-moz-user-select: none;
	background: url("image/button/btnout_bg_left.gif") no-repeat scroll
		left top transparent;
	outline: medium none;
	text-decoration: none;
}

.pushBtn1 img {
	float: left;
	margin: 2px 0 4px 6px;
	border: 0;
}

.pushBtn1:hover {
	background: url("image/button/btnover_bg_left.gif") no-repeat scroll
		left top transparent;
	text-decoration: none;
}
td{
	
}
-->
</style>
</head>
<body style="background: #DFE8F6;" id="tt">
	<div id="searchpanel" class="easyui-panel panel-margin-buttom"
		data-options="collapsible:true,collapsed:true" title="查询条件"
		style="height: 80px; padding: 10px; vertical-align: center;">
		<table height="100%">
			<tr
				style="Valign: center; text-decoration: none; line-heigth: 30px; height: 30px; margin-left: 10px;">
				<td>开始时间：</td>
				<td><input type="text" id="startTime" class="easyui-datebox"
					editable="false" style="width: 90px;" /></td>
				<td>结束时间：</td>
				<td><input type="text" id="endTime" class="easyui-datebox"
					editable="false" style="width: 90px;" /></td>
				 <td>&nbsp;&nbsp;记录状态：</td>
				 <td>&nbsp;&nbsp;
				      <select id="feedBackState" style="width: 120px; ">
							<option value="">请选择</option>
							<option value="未回复">未回复</option>
							<option value="已回复">已回复</option>
				      </select>
				 </td>	
					
					
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
	
	
	
	<table id="dg" class="easyui-datagrid" title="百姓说交通信息列表"
		data-options="rownumbers:true,singleSelect:false,toolbar:'#toolbar',pagination:true,
				pageSize:20,remoteSort:false,fitColumns:true,
				multiSort:true">
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<!-- th data-options="field:'feedBackId',width:150,align:'center'">反馈ID</th> -->
				<th data-options="field:'nickName',width:150">微信昵称</th>
				<th data-options="field:'phoneNumber',width:150">手机号码</th>
				<th data-options="field:'feedTime',width:150">反馈时间</th>
				<th data-options="field:'feedText',width:400">反馈内容</th>
				<th data-options="field:'op1',align:'center',width:80,formatter:rowformater">操作
				</th>
			</tr>
		</thead>
	</table>
    <div id="toolbar" style=" height:auto; " width="100%">
	     <a href="#" id="export"></a>
	     <div  style="float:right; margin:5px 10px 5px 0px;font-size:12px">反馈回复有效时间为：48小时</div>
	</div>
	<div id="detail_dialog" class="easyui-dialog" title="详情"
		data-options="closed:true"
		style="width: 430px; height: 450px; padding: 10px 0px 10px 0px;">
		<input type="hidden" id="backId" value="" />
		<div id="div1"
			style="margin-left: 10px; margin-right: 10px; margin-top: 10px; height: 300px; overflow: auto; overflow-x: hidden;">
			
			<table cellspacing="0"  id="tablehf" >
			</table>
			<a name="top" id="top"></a>
			<a  href="#top"><span id="textDJ" style="display:none">底部</span></a>
		</div>
		<div align="center"
			style="position: absolute; bottom: 6px; width: 97%; height:90px; background-image: url(image/bg_reply.png);">
			<div style="margin:8px 0px 8px 0px;"> 
				<textarea   id="btnHuifu" style="font-size:12px;  border: 2px solid #CCCCCC; border-radius: 5px; width:95%;height:45px;"   ></textarea>
			</div>	
			<div style="float:right;margin:0px 8px 2px 0px" >	
				<input  type="button" onclick="huifu()" value="发送" class="pushBtn1" style="width: 70px;" />
			</div>
		</div>
	</div>
</body>
</html>

