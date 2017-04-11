<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=8" />
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
	background: url("image/button/btnout_bg_left.gif") no-repeat scroll left
		top transparent;
	float: left;
	height: 24px;
	margin: 4px 5px 0 0;
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

li {
	list-style: none;
}

.clearfix:after {
	content: ".";
	display: block;
	height: 0;
	clear: both;
	visibility: hidden;
}

.clearfix {
	zoom: 1;
}
-->
</style>
</head>
<body style="background: #DFE8F6;">
	<div id="searchpanel" data-options="collapsible:true,collapsed:true"
		class="easyui-panel panel-margin-buttom" title="查询条件"
		style="height: 110px; padding: 10px; overflow: hidden;">
		<table height="100%">
			<tr
				style="Valign: center; text-decoration: none; line-heigth: 30px; height: 30px; margin-left: 10px;">
				<td>手机号码：</td>
				<td><input type="text" id="reportPhoneNumber"
					style="width: 100px;" /></td>
				<td>微信昵称：</td>
				<td><input type="text" id="nickName" style="width: 120px;" /></td>
				<td>处理状态：</td>
				<td><select id="handyPhotoState" style="width: 120px;">
						<option value="">请选择</option>
						<option value="上报">上报</option>
						<option value="待定">待定</option>
						<option value="审核通过">审核通过</option>
				</select></td>
			</tr>
			<tr
				style="Valign: center; text-decoration: none; line-heigth: 30px; height: 30px; margin-left: 10px;">
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
	<table id="dg" class="easyui-datagrid" title="随手拍照列表"
		style="height: 423px"
		data-options="rownumbers:true,singleSelect:true,pagination:true,
				pageSize:20,remoteSort:false,fitColumns:true,
				multiSort:true">
		<thead>
			<tr>
				<th
					data-options="field:'handyPhotoId',width:120,sortable:true,align:'center'">拍照编号</th>
				<th
					data-options="field:'reportTime',width:150,sortable:true,align:'center'">上报时间</th>
				<th
					data-options="field:'acceptTime',width:80,sortable:true,align:'center'">受理时间</th>
				<!-- <th
					data-options="field:'userName',width:80,sortable:true,align:'center'">用户名称</th> -->
				<th
					data-options="field:'nickName',width:80,sortable:true,align:'center'">微信昵称</th>

				<th
					data-options="field:'reportPhoneNumber',width:100,align:'center'">手机号</th>
				<th data-options="field:'address',width:260">现场地址</th>
				<th data-options="field:'handyPhotoState',width:80,align:'center'">处理状态</th>
				
				<!--<th data-options="field:'remark',width:80,align:'center'">备注</th> -->
				<th
					data-options="field:'textInfo',width:100,align:'center',formatter:operate3">文本信息</th>
				<th
					data-options="field:'locationX',width:100,align:'center',width:100,formatter:operate1">位置信息</th>
				<th
					data-options="field:'picInfo',width:100,align:'center',width:100,formatter:operate2">图片信息</th>
				<th
					data-options="field:'opta',align:'center',width:80,formatter:operate4">操作
				</th>
				<th data-options="field:'accepter',width:70,align:'center'">受理人</th>
			</tr>
		</thead>
	</table>
	<div id="text_dialog" class="easyui-dialog" title="文本信息"
		data-options="closed:true"
		style="width: 600px; height: 300px; padding: 20px;">
		<div id="div1" style="margin-left: 70px; height: 200px;">
			<table id="textTab" cellspacing="0" style="width: 400px;">
			</table>
		</div>
	</div>
	
	
	
	<div id="detail_dialog" class="easyui-dialog" title="随手拍详情"
		data-options="closed:true"
		style="width: 635px; height: 260px; padding: 10px; height: auto !important; min-height: 260px;">
		<input type="hidden" id="editHandyPhotoId" />
		<div id="div1" style="height: 240px;">
			<table id="table" cellspacing="0" style="width: 600px;">
				<tr style="height: 25px;">
					<td
						style="width: 100px; text-align: right; border: 1px solid #CCCCCC; background-color: #EDEDED;">拍照编号：</td>
					<td id="detail_handyPhotoId"
						style="width: 100px; text-align: left; border: 1px solid #CCCCCC; border-left: 0px;"></td>
					<td
						style="width: 100px; text-align: right; border: 1px solid #CCCCCC; border-left: 0px; background-color: #EDEDED;">微信昵称：</td>
					<td id="detail_nickName"
						style="width: 100px; text-align: left; border: 1px solid #CCCCCC; border-left: 0px;"></td>
					<td
						style="width: 100px; text-align: right; border: 1px solid #CCCCCC; border-left: 0px; background-color: #EDEDED;">手机号：</td>
					<td id="detail_reportPhoneNumber"
						style="width: 100px; text-align: left; border: 1px solid #CCCCCC; border-left: 0px;"></td>
				</tr>
				<tr style="height: 25px;">
					<td
						style="width: 100px; text-align: right; border: 1px solid #CCCCCC;  border-top: 0px; background-color: #EDEDED;">处理状态：</td>
					<td id="detail_handyPhotoState"
						style="width: 100px; text-align: left; border: 1px solid #CCCCCC; border-left: 0px; border-top: 0px;"></td>
					<td
						style="width: 100px; text-align: right; border: 1px solid #CCCCCC; border-left: 0px; border-top: 0px; background-color: #EDEDED;">处理人：</td>
					<td id="detail_accepter"  colspan="3"
						style="width: 100px; text-align: left; border: 1px solid #CCCCCC; border-left: 0px; border-top: 0px;"></td>
				</tr>
				<tr style="height: 25px;">
					<td
						style="width: 100px; text-align: right; border: 1px solid #CCCCCC; border-top: 0px; background-color: #EDEDED;">拍照时间：</td>
					<td id="detail_reportTime" colspan="5"
						style="width: 500px; text-align: left; border: 1px solid #CCCCCC; border-left: 0px; border-top: 0px;"></td>
				</tr>
				<tr style="height: 25px;">
					<td
						style="width: 100px; text-align: right; border: 1px solid #CCCCCC; border-top: 0px; background-color: #EDEDED;">处理时间：</td>
					<td id="detail_acceptTime" colspan="5"
						style="width: 500px; text-align: left; border: 1px solid #CCCCCC; border-left: 0px; border-top: 0px;"></td>
				</tr>
				<tr style="height: 25px;">
					<td
						style="width: 100px; text-align: right; border: 1px solid #CCCCCC; border-top: 0px; background-color: #EDEDED;">拍照地址：</td>
					<td id="detail_address" colspan="5"
						style="width: 500px; text-align: left; border: 1px solid #CCCCCC; border: 1px solid #CCCCCC; border-left: 0px; border-top: 0px;"></td>
				</tr>
			</table>
			<div align="center" id="shenhe" style="margin-top: 30px;display:block;">
				<input style="width: 100px; height: 30px;" type="button" value="审核通过"
					id="edit_save" /> <input style="width: 125px; height: 30px;"
					type="button" value="审核未通过" onclick="showCase()" id="detail_close" />
			</div>
		</div>

	</div>
	
	
	
	<div id="case_dialog" class="easyui-dialog" title="填写审核不通过原因"
		data-options="closed:true"
		style="width: 550px;  padding: 20px;height: auto !important; min-height: 330px;">
		<div id="div1" style="margin-left: 70px; height: 200px;">
			<table id="textTab1" cellspacing="0" style="width: 400px;">
			
			</table>
		</div>
		<div align="center" style="margin-top: 105px;">
				<input style="width: 80px; height: 30px;" type="button" value="确定"
					id="edit_save2" /> <input style="width: 80px; height: 30px;"
					type="button" value="取消" onclick="closeCase()" />
			</div>
	</div>
	
	<script type="text/javascript">
	var managerName = '${session.User.userId}';
		String.prototype.trim = function() {
			return this.replace(/^\s\s*/, '').replace(/\s\s*$/, '');
		};
		function detail_close() {
			$('#detail_dialog').dialog('close');
		}
		function operate1(value, row, index) {
			if (value != null && value != "") {
				return '<a href=\'javascript:display1();\'>查看</a>';
			}
		}
		function operate2(value, row, index) {
			if (value != null && value != "") {
				return '<a href=\'javascript:display2();\'>查看</a>';
			}
		}
		function operate3(value, row, index) {
			if (value != null && value != "") {
				return '<a href=\'javascript:display3();\'>查看</a>';
			}
		}
		function operate4(value, row, index) {
			var state = row.handyPhotoState;
			if(state =='审核通过')
				return '<a href=\'javascript:display4();\'>查看</a>';
			else
				return '<a href=\'javascript:display4();\'>审核</a>';
				
		}
		
		/*添加一个新选项卡面板
		self.parent.$('#tabs').tabs('add',{
		 title:'New Tab ',
		 content:'Tab Body ',
		 iconCls:'',
		 closable:true,
		 tools:[{
		 iconCls:'icon-mini-refresh',
		 handler:function(){
		 alert('refresh');
		 }
		 }]
		 });  */
		function display1() {
			var row = $("#dg").datagrid('getSelected');
			window.open("page/TpAddressInfo.jsp?handyPhotoId=" + row.handyPhotoId, "");
		}
		 function display2() {
				var row = $("#dg").datagrid('getSelected');
				window.open("page/TbPicInfo.jsp?carMoveId=" + row.handyPhotoId, "");
			}
		 function display3() {
				$("#textTab").empty();
				var row = $("#dg").datagrid('getSelected');
				var data = getTbAccidentText(row.handyPhotoId);
				var tr = "<tr style=\"height: 25px;\"><td style=\"text-align: center;vertical-align:middle; border: 1px solid #CCCCCC; width:30%;background-color: #EDEDED;;\"valign=\"top\">时间</td><td style=\"text-align: center; vertical-align:middle;border: 1px solid #CCCCCC;width:70%; background-color: #EDEDED;\"valign=\"top\">备注信息</td></tr>";
				$('#text_dialog').dialog('open');
				for ( var i = 0; i < data.length; i++) {
					var textMessage = data[i].textMessage;
					var textTime = data[i].textTime.replace("T"," ");
					tr += "<tr style=\"height: 25px;\"><td id=\"textTime\" style=\"text-align: center;vertical-align:middle; border: 1px solid #CCCCCC; background-color: #FFFFFF;\"valign=\"top\">"
							+ textTime
							+ "</td><td id=\"textInfo\" style=\"text-align: left;vertical-align:middle; border: 1px solid #CCCCCC; background-color: #FFFFFF;\"valign=\"top\">"
							+ textMessage + "</td></tr>";
				}
				$("#textTab").append(tr);
			}
		 function display4(){
			 $("#detail_handyPhotoId").html("");
			 $("#detail_nickName").html("");
			 $("#detail_reportPhoneNumber").html("");
			 $("#detail_handyPhotoState").html("");
			 $("#detail_accepter").html("");
		 	 $("#detail_acceptTime").html("");
			 $("#detail_reportTime").html("");
			 $("#detail_address").html("");
			 var row = $("#dg").datagrid('getSelected');
			 $.ajax({
					url : "getHandyPhotoByTakId.action",
					async : false,
					data : "handyPhotoId=" + row.handyPhotoId,
					dataType : "json",
					type : "post",
					success : function(data) {
						$("#detail_handyPhotoId").html(data.handyPhotoId);
						$("#detail_nickName").html(data.nickName);
						$("#detail_reportPhoneNumber").html(data.reportPhoneNumber);
						$("#detail_handyPhotoState").html(data.handyPhotoState);
						$("#detail_accepter").html(data.accepter);
					 	$("#detail_acceptTime").html(data.acceptTime);
						$("#detail_reportTime").html(data.reportTime);
						$("#detail_address").html(data.address);
						if(data.handyPhotoState=="审核通过"){
							$("#shenhe").css("display","none");
						}else{
							$("#shenhe").css("display","block");
						}

					},
					error : function(data) {
						alert("网络繁忙!请稍后再试!");
					}
				});
			 	$('#detail_dialog').dialog('open');
				$("#editHandyPhotoId").val(row.handyPhotoId);
				$('#detail_close').attr({
					disabled : false
				});
		 }
		 
		 
		 
		 
		 var procedureDate = new Array();
			function showCase() {
				$('#case_dialog').dialog('open');
				$.ajax({
					url : "getSelectCaseByHandyPhoto.action",
					async : false,
					data : "",
					dataType : "json",
					type : "post",
					success : function(data) {
						$("#textTab1").html("");
						var small;
						for(var i=0;i<data.length;i++){
							small = {};
							small.index = i;
							small.procedureId = data[i].id.procedureId;
							small.procedureName = data[i].procedureName;
							procedureDate.push(small);
							var tr = $("<tr><td><input type=\"checkbox\" id=\"checkbox"+i+"\"value='"+data[i].id.procedureId+"' onclick=\"addThis('"+i+"')\"><font size=\"3\">"+data[i].procedureName+"</font></td></tr>");
							$("#textTab1").append(tr);
						}
						var tr1 = $("<tr><td><textarea id=\"custResText\" cols=\"40\" rows=\"5\"></textarea></td></tr>");
						$("#textTab1").append(tr1);
					},
					error : function(data) {
						alert("网络繁忙!请稍后再试!");
					}
				});
			}
			function closeCase(){
				$('#case_dialog').dialog('close');
			}
			
			function addThis(index){
				var checkBoxes = $("#textTab1 input[type='checkbox']");
				var custResText = "您提交的信息有误：";
				var x = 0;
				for(var i=0;i<checkBoxes.length;i++){
					if($(checkBoxes[i]).attr("checked")=='checked'){
						if(x==0){
							custResText = custResText + procedureDate[i].procedureName;
							x = x + 1;
						}else{
							custResText = custResText +"；"+ procedureDate[i].procedureName;
							x = x + 1;
						}
						
					}
				}
				if(custResText!='您提交的信息有误：'){
					custResText = custResText + "，请根据提示再次提交。";
					$("#custResText").val(custResText);
				}else{
					$("#custResText").val("");
				}
			}
		 
		 
		 
		 
		 
		 
		 function getTbAccidentText(accidentId) {
				$.ajax({
					url : "getTbAccidentText.action",
					async : false,
					data : "accidentId=" + accidentId,
					dataType : "json",
					type : "post",
					success : function(data) {
						if (data.respCode == -1) {
							alert(data.respInfo);
							return false;
						} else {
							rows = data;

						}
					},
					error : function(data) {
						alert("error 后台出现错误！");
					}
				});
				return rows;
			}
		function getData(startTime, endTime, reportPhoneNumber, nickName,handyPhotoState) {
			var rows = null;
			var dg = $("#dg");
			var opts = dg.datagrid('options');
			var pageSize = opts.pageSize;
			var pageIndex = opts.pageNumber;
			$.ajax({
				url : "getHandyPhoto.action",
				async : false,
				data : "pageSize=" + pageSize + "&pageIndex=" + pageIndex
						+ "&startTime=" + startTime + "&endTime=" + endTime
						+ "&reportPhoneNumber=" + reportPhoneNumber +
						"&nickName=" + nickName+"&handyPhotoState="+handyPhotoState,
				dataType : "json",
				type : "post",
				success : function(data) {
					if (data.respCode == -1) {
						alert(data.respInfo);
						return false;
					} else {
						rows = data;

					}
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
				var nickName = $("#nickName").val().trim();
				var handyPhotoState = $("#handyPhotoState").val().trim();
				var reportPhoneNumber = $("#reportPhoneNumber").val().trim();
				var startTime = $("#startTime").datebox('getValue');
				var endTime = $("#endTime").datebox('getValue');
				var newData = getData(startTime, endTime, reportPhoneNumber,nickName,handyPhotoState);
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
			var handyPhotoState = $("#handyPhotoState").val().trim();
			var reportPhoneNumber = $("#reportPhoneNumber").val().trim();
			var startTime = $("#startTime").datebox('getValue');
			var endTime = $("#endTime").datebox('getValue');
			var nickName = $("#nickName").val().trim();
			$('#dg').datagrid({
				loadFilter : pagerFilter,
				width : width - 20,
				height : height - 45
			}).datagrid(
					'loadData',
					getData(startTime, endTime, reportPhoneNumber,nickName,handyPhotoState));
			$("#searchpanel").panel({
				width : width - 20
			});
			$("#search").click(
					function() {
						var nickName = $("#nickName").val().trim();
						var reportPhoneNumber = $("#reportPhoneNumber").val()
								.trim();
						var handyPhotoState = $("#handyPhotoState").val().trim();
						var startTime = $("#startTime").datebox('getValue');
						var endTime = $("#endTime").datebox('getValue');
						$('#dg').datagrid({
							loadFilter : pagerFilter,
							pageNumber : 1
						}).datagrid(
								'loadData',
								getData(startTime, endTime, reportPhoneNumber,nickName,handyPhotoState));
					});
			$("#fresh").click(function() {
				$('#dg').datagrid({
					loadFilter : pagerFilter,
					width : width - 20,
					height : height - 45
				}).datagrid('loadData', getData(startTime, endTime, reportPhoneNumber,nickName,handyPhotoState));
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
				$("#reportPhoneNumber").val("");
				$("#nickName").val("");
				$("#handyPhotoState").val("");
			});
			
			
			$("#edit_save").click(
					function() {
						var row = $("#dg").datagrid('getSelected');
						var handyPhotoId = row.handyPhotoId;
						$.ajax({
							url : "saveProcedureMessageHandyPhoto.action",
							data : "handyPhotoId=" + handyPhotoId
									+ "&managerName=" + managerName,
							dataType : "json",
							type : "post",
							success : function(data) {
								if (data.respCode == 0) {
									alert("审核完毕!回复成功!");
									$('#detail_dialog').dialog('close');
									var nickName = $("#nickName").val().trim();
									var reportPhoneNumber = $("#reportPhoneNumber").val()
											.trim();
									var handyPhotoState = $("#handyPhotoState").val().trim();
									var startTime = $("#startTime").datebox('getValue');
									var endTime = $("#endTime").datebox('getValue');
									$('#dg').datagrid({
										loadFilter : pagerFilter,
										pageNumber : 1
									}).datagrid(
											'loadData',
											getData(startTime, endTime, reportPhoneNumber,nickName,handyPhotoState));
								} else {
									alert("网络繁忙!请刷新后重新审核!");
								}

							},
							error : function(data) {
								alert("网络繁忙!请稍后再试!");
							}
						});

					});
			
			$("#edit_save2").click(
					function() {
						var checkBoxes = $("#textTab1 input[type='checkbox']");
						var procedureIds= '';
						
						for(var i=0;i<checkBoxes.length;i++){
							if($(checkBoxes[i]).attr("checked")=='checked'){
								procedureIds = procedureIds +"," +$(checkBoxes[i]).attr("value");
							}
						}
						if(procedureIds==''){
							alert("请选择审核不通过原因！");
							return false;
						}
						var custResText = $("#custResText").val().trim();
						$.ajax({
							url : "responseWeiHandyPhoto.action",
							data : "procedureIds=" + procedureIds + "&custResText="+custResText
									+ "&handyPhotoId=" + $("#editHandyPhotoId").val(),
							dataType : "json",
							type : "post",
							success : function(data) {
								if (data.respCode == 0) {
									alert("审核完毕!回复成功!");
									$('#case_dialog').dialog('close');
									$('#detail_dialog').dialog('close');
									var nickName = $("#nickName").val().trim();
									var reportPhoneNumber = $("#reportPhoneNumber").val()
											.trim();
									var handyPhotoState = $("#handyPhotoState").val().trim();
									var startTime = $("#startTime").datebox('getValue');
									var endTime = $("#endTime").datebox('getValue');
									$('#dg').datagrid({
										loadFilter : pagerFilter,
										pageNumber : 1
									}).datagrid(
											'loadData',
											getData(startTime, endTime, reportPhoneNumber,nickName,handyPhotoState));
								} else {
									alert("网络繁忙!请刷新后重新审核!");
								}

							},
							error : function(data) {
								alert("网络繁忙!请稍后再试!");
							}
						});

					});	
			
			
			
		});
	</script>
	<button id="fresh" style="display: none;"></button>
</body>
</html>