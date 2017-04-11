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
<input type="hidden" id="editcarMoveId" />
	<div id="searchpanel" data-options="collapsible:true,collapsed:true"
		class="easyui-panel panel-margin-buttom" title="查询条件"
		style="height: 80px; padding: 10px; overflow: hidden;">
		<table height="100%">
			<tr
				style="Valign: center; text-decoration: none; line-heigth: 30px; height: 30px; margin-left: 10px;">
				<td>开始时间：</td>
				<td><input type="text" id="startTime" class="easyui-datebox"
					editable="false" style="width: 90px;" /></td>
				<td>结束时间：</td>
				<td><input type="text" id="endTime" class="easyui-datebox"
					editable="false" style="width: 90px;" /></td>
				<td>报案手机号：</td>
				<td><input type="text" id="reportPhoneNumber"
					style="width: 100px;" /></td>
				<td style="display:none;">车牌号：</td>
				<td><input type="text" id="carNumber" style="width: 120px;display:none;" /></td>
				<td>处理状态：</td>
				<td><select id="carMoveState" style="width: 120px;">
						<option value="">请选择</option>
						<option value="上报">上报</option>
						<option value="待定">待定</option>
						<option value="审核通过">审核通过</option>
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
	<table id="dg" class="easyui-datagrid" title="自助移车列表"
		style="height: 423px"
		data-options="rownumbers:true,singleSelect:true,pagination:true,
				pageSize:20,remoteSort:false,fitColumns:true,
				multiSort:true">
		<thead>
			<tr>
				<th
					data-options="field:'carMoveId',width:80,sortable:true,align:'center'">移车编号</th>
				<th
					data-options="field:'reportTime',width:80,sortable:true,align:'center'">上报时间</th>
				<!--  <th
					data-options="field:'acceptTime',width:80,sortable:true,align:'center'">受理时间</th>-->
				<th data-options="field:'userName',width:70,align:'center'">用户姓名</th>
				<th data-options="field:'nickName',width:70,align:'center'">微信昵称</th>
				<th
					data-options="field:'reportPhoneNumber',width:100,sortable:true,align:'center'">上报人手机</th>
				<!-- <th
					data-options="field:'carNumber',width:95,sortable:true,align:'center'">车牌号</th> -->
				<th
					data-options="field:'picInfo',align:'center',width:80,formatter:operate2">图片信息</th>
				<th
					data-options="field:'locationX',align:'center',width:80,formatter:operate1">位置信息</th>
				<th data-options="field:'address',width:400,align:'center'">车辆地址</th>
				<th data-options="field:'carMoveState',width:80,align:'center'">处理状态</th>
				<!--<th data-options="field:'accepter',width:70,align:'center'">受理人</th>
				 <th data-options="field:'locationX',width:100">事发纬度</th>-->

				<!-- <th data-options="field:'remark',width:80,align:'center'">备注</th> -->
				
				
				<th
					data-options="field:'oprat',align:'center',width:80,formatter:operate3">操作
				</th>
			</tr>
		</thead>
	</table>
	
	
	
	
	<div id="detail_dialog" class="easyui-dialog" title="移车详情"
		data-options="closed:true"
		style="width: 635px; height: 240px; padding: 10px; height: auto !important; min-height: 220px;">
		<div id="div1" style="height: 200px;">
			<table id="table" cellspacing="0" style="width: 600px;">
				<tr style="height: 25px;">
					<td
						style="width: 100px; text-align: right; border: 1px solid #CCCCCC; background-color: #EDEDED;">移车编号：</td>
					<td id="detail_carMoveId"
						style="width: 100px; text-align: left; border: 1px solid #CCCCCC; border-left: 0px;"></td>
					<td
						style="width: 100px; text-align: right; border: 1px solid #CCCCCC; border-left: 0px; background-color: #EDEDED;">用户姓名：</td>
					<td id="detail_userName"
						style="width: 100px; text-align: left; border: 1px solid #CCCCCC; border-left: 0px;"></td>
					<td
						style="width: 100px; text-align: right; border: 1px solid #CCCCCC; border-left: 0px; background-color: #EDEDED;">微信昵称：</td>
					<td id="nick_name"
						style="width: 100px; text-align: left; border: 1px solid #CCCCCC; border-left: 0px;"></td>
				</tr>
				<tr style="height: 25px;">
					<td
						style="width: 100px; text-align: right; border: 1px solid #CCCCCC; border-top: 0px; background-color: #EDEDED;">上报时间：</td>
					<td id="detail_reportTime"
						style="width: 100px; text-align: left; border: 1px solid #CCCCCC; border-left: 0px; border-top: 0px;"></td>
					<td
						style="width: 100px; text-align: right; border: 1px solid #CCCCCC; border-left: 0px; border-top: 0px; background-color: #EDEDED;">处理状态：</td>
					<td id="detail_carMoveState"
						style="width: 100px; text-align: left; border: 1px solid #CCCCCC; border-left: 0px; border-top: 0px;"></td>
					<td
						style="width: 100px; text-align: right; border: 1px solid #CCCCCC; border-top: 0px; border-left: 0px; background-color: #EDEDED;">上报人手机：</td>
					<td id="detail_reportPhoneNumber"
						style="width: 100px; text-align: left; border: 1px solid #CCCCCC; border-top: 0px; border-left: 0px;"></td>
				</tr>
				<tr style="height: 25px;">
					<td
						style="width: 100px; text-align: right; border: 1px solid #CCCCCC; border-top: 0px; background-color: #EDEDED;">车辆地址：</td>
					<td id="detail_address" colspan="5"
						style="width: 500px; text-align: left; border: 1px solid #CCCCCC; border: 1px solid #CCCCCC; border-left: 0px; border-top: 0px;"></td>
				</tr>
			</table>
			<div align="center" id="shenhe" style="margin-top: 30px;display:block;">
				<input style="width: 140px; height: 50px;" type="button" value="审核通过"
					id="edit_save" /> <input style="width: 140px; height: 50px;"
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
	var managerName = '${session.Manager.userId}';
	var managerRole = '${session.ManagerRole.role}';
		String.prototype.trim = function() {
			return this.replace(/^\s\s*/, '').replace(/\s\s*$/, '');
		};
		
		function operate1(value, row, index) {
			if (value != null) {
				return '<a href=\'javascript:display1();\'>查看</a>';
			}
		}
		function operate2(value, row, index) {
			if (value != null) {
				return '<a href=\'javascript:display2();\'>查看</a>';
			}
		}
		function operate3(value, row, index) {
			if(row.carMoveState =='审核通过')
				return '<a href=\'javascript:display3();\'>查看</a>';
			else
				return '<a href=\'javascript:display3();\'>审核</a>';
		
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
			window.open("page/moveCarAddressInfo.jsp?carMoveId=" + row.carMoveId, "");
		}
		 function display2() {
				var row = $("#dg").datagrid('getSelected');
				window.open("page/TbPicInfo.jsp?carMoveId=" + row.carMoveId, "");
			}
		 function display3(){
			// detail_dialog
			$("#detail_carMoveId").html("");
			$("#detail_reportTime").html("");
			$("#detail_userName").html("");
			$("#detail_reportPhoneNumber").html("");
			$("#detail_address").html("");
			$("#detail_carMoveState").html("");
			$("#nick_name").html("");
			var row = $("#dg").datagrid('getSelected');
			$.ajax({
				url : "getMoveCarByCarMoveId.action",
				async : false,
				data : "carMoveId=" + row.carMoveId,
				dataType : "json",
				type : "post",
				success : function(data) {
					$("#detail_carMoveId").html(data.carMoveId);
					$("#detail_reportTime").html(data.reportTime);
					$("#detail_userName").html(data.userName);
					$("#detail_reportPhoneNumber").html(data.reportPhoneNumber);
					$("#detail_address").html(data.address);
					$("#detail_carMoveState").html(data.carMoveState);
					$("#nick_name").html(data.nickName);
					if(data.carMoveState=="审核通过"){
						$("#shenhe").css("display","none");
					}else{
						$("#shenhe").css("display","block");
					}
					$("#detail_accepter").html(data.accepter);
				},
				error : function(data) {
					alert("网络繁忙!请稍后再试!");
				}
			});
			$('#detail_dialog').dialog('open');
			$("#editcarMoveId").val(row.carMoveId);
			$('#detail_close').attr({
				disabled : false
			});
		 }
		 
		 
		 
		 var procedureDate = new Array();
			function showCase() {
				$('#case_dialog').dialog('open');
				$.ajax({
					url : "getSelectCaseByMoveCar.action",
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
		 
		 
		 
		 
		 
		 
		
		function getData(startTime, endTime, reportPhoneNumber, carNumber,
				carMoveState) {
			var rows = null;
			var dg = $("#dg");
			var opts = dg.datagrid('options');
			var pageSize = opts.pageSize;
			var pageIndex = opts.pageNumber;
			$.ajax({
				url : "getCarMove.action",
				async : false,
				data : "pageSize=" + pageSize + "&pageIndex=" + pageIndex
						+ "&startTime=" + startTime + "&endTime=" + endTime
						+ "&reportPhoneNumber=" + reportPhoneNumber
						+ "&carNumber=" + carNumber + "&carMoveState="
						+ carMoveState,
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
				var carNumber = $("#carNumber").val().trim();
				var reportPhoneNumber = $("#reportPhoneNumber").val().trim();
				var carMoveState = $("#carMoveState").val().trim();
				var startTime = $("#startTime").datebox('getValue');
				var endTime = $("#endTime").datebox('getValue');
				var newData = getData(startTime, endTime, reportPhoneNumber,
						carNumber, carMoveState);
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
			var carNumber = $("#carNumber").val().trim();
			var reportPhoneNumber = $("#reportPhoneNumber").val().trim();
			var carMoveState = $("#carMoveState").val().trim();
			var startTime = $("#startTime").datebox('getValue');
			var endTime = $("#endTime").datebox('getValue');
			$('#dg').datagrid({
				loadFilter : pagerFilter,
				width : width - 20,
				height : height - 45
			}).datagrid(
					'loadData',
					getData(startTime, endTime, reportPhoneNumber, carNumber,
							carMoveState));
			$("#searchpanel").panel({
				width : width - 20
			});
			$("#search").click(
					function() {
						var carNumber = $("#carNumber").val().trim();
						var reportPhoneNumber = $("#reportPhoneNumber").val()
								.trim();
						var carMoveState = $("#carMoveState").val().trim();
						var startTime = $("#startTime").datebox('getValue');
						var endTime = $("#endTime").datebox('getValue');
						$('#dg').datagrid({
							loadFilter : pagerFilter,
							pageNumber : 1
						}).datagrid(
								'loadData',
								getData(startTime, endTime, reportPhoneNumber,
										carNumber, carMoveState));
					});
			$("#fresh").click(function() {
				$('#dg').datagrid({
					loadFilter : pagerFilter,
					width : width - 20,
					height : height - 45
				}).datagrid('loadData', getData(startTime, endTime, reportPhoneNumber,
						carNumber, carMoveState));
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
				$("#carNumber").val("");
				$("#carMoveState").val("");
			});
			$("#edit_save").click(
					function() {
						var row = $("#dg").datagrid('getSelected');
						var carMoveId = row.carMoveId;
						$.ajax({
							url : "saveProcedureMessageMove.action",
							data : "carMoveId=" + carMoveId
									+ "&managerName=" + managerName,
							dataType : "json",
							type : "post",
							success : function(data) {
								if (data.respCode == 0) {
									alert("审核完毕!回复成功!");
									$('#detail_dialog').dialog('close');
									var carNumber = $("#carNumber").val().trim();
									var reportPhoneNumber = $("#reportPhoneNumber").val()
											.trim();
									var carMoveState = $("#carMoveState").val().trim();
									var startTime = $("#startTime").datebox('getValue');
									var endTime = $("#endTime").datebox('getValue');
									$('#dg').datagrid({
										loadFilter : pagerFilter,
										pageNumber : 1
									}).datagrid(
											'loadData',
											getData(startTime, endTime, reportPhoneNumber,
													carNumber, carMoveState));
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
							url : "responseWeiMoveCar.action",
							data : "procedureIds=" + procedureIds + "&custResText="+custResText
									+ "&carMoveId=" + $("#editcarMoveId").val(),
							dataType : "json",
							type : "post",
							success : function(data) {
								if (data.respCode == 0) {
									alert("审核完毕!回复成功!");
									$('#case_dialog').dialog('close');
									$('#detail_dialog').dialog('close');
									var carNumber = $("#carNumber").val().trim();
									var reportPhoneNumber = $("#reportPhoneNumber").val()
											.trim();
									var carMoveState = $("#carMoveState").val().trim();
									var startTime = $("#startTime").datebox('getValue');
									var endTime = $("#endTime").datebox('getValue');
									$('#dg').datagrid({
										loadFilter : pagerFilter,
										pageNumber : 1
									}).datagrid(
											'loadData',
											getData(startTime, endTime, reportPhoneNumber,
													carNumber, carMoveState));
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