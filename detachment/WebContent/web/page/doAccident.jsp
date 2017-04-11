<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
<script language="javascript" src="js/LodopFuncs.js"></script>
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
<!-- <body class="easyui-layout"
	style="background: #DFE8F6; margin-top: 4px; margin-left: 4px; margin-right: 4px; width: auto;"> -->
<body style="background: #DFE8F6;">
	<input type="hidden" id="editAccidentId" />
	<input type="hidden" id="editDepartmentId" />
	<div id="searchpanel" data-options="collapsible:true,collapsed:true"
		class="easyui-panel panel-margin-buttom" title="查询条件"
		style="height:110px; padding: 10px; overflow: hidden;">
		<table height="100%">
			<tr
				style="Valign: center; text-decoration: none; line-heigth: 30px; height: 30px; margin-left: 10px;">
				<td>手机号码：</td>
				<td><input type="text" id="reportPhoneNumber"
					style="width: 100px;" /></td>
				<td>微信昵称：</td>
				<td><input type="text" id="nickName" style="width: 120px;" /></td>
				<td>出警情况：</td>
				<td><select id="emergencyCall" style="width: 80px;">
						<option value="">请选择</option>
						<option value="1">需要出警</option>
						<option value="0">不需要</option>
				</select></td>
				<td>事故状态：</td>
				<td><select id="accidentState" style="width: 80px;">
						<option value="">全部</option>
						<option value="上报">上报</option>
						<option value="待定">待定</option>
						<option value="审核通过">审核通过</option>
						<option value="恶意上报">恶意上报</option>
						<option value="现场核实">现场核实</option>
						<option value="其它">其它</option>
				</select></td>
				<td>事故所属大队：</td>
				<td><select id="departmentId" style="width: 120px;"></select></td>
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
 
	 
	<table id="dg" class="easyui-datagrid" title="事故信息列表"
		style="height: 423px"
		data-options="rownumbers:true,singleSelect:true,pagination:true,
				pageSize:20,remoteSort:false,fitColumns:true,
				multiSort:true">
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<th
					data-options="field:'formlAccidentId',width:110,sortable:true,align:'center'">事故编号</th>
				<!--<th data-options="field:'reportOpenId',width:100">微信识别号</th> -->
				<th
					data-options="field:'reportTimeString',width:150,sortable:true,align:'center'">事故时间</th>
				<!--<th
					data-options="field:'reporterType',width:80,sortable:true,align:'center'">用户类别</th>-->
				<!--<th
					data-options="field:'realName',width:80,sortable:true,align:'center'">用户名称</th>-->
				<th
					data-options="field:'nickname',width:80,sortable:true,align:'center'">微信昵称</th>

				<th
					data-options="field:'reportPhoneNumber',width:100,align:'center'">手机号</th>
				<th data-options="field:'address',width:260,align:'center'">事发地址</th>
				<th data-options="field:'department',width:100,align:'center',">归属大队</th>
				<th data-options="field:'emergencyCallString',width:100,align:'center',">出警情况</th>
				
				<th data-options="field:'accidentState',width:80,align:'center'">处理状态</th>
				<!--<th
					data-options="field:'textInfo',width:100,align:'center',formatter:operate4">文本信息</th> -->
				<th
					data-options="field:'locationX',width:100,align:'center',width:60,formatter:operate2">位置信息</th>
				<th
					data-options="field:'picInfo',width:100,align:'center',width:60,formatter:operate3">图片信息</th>
				<!-- <th
					data-options="field:'voiceInfo',width:100,align:'center',width:100,formatter:operate5">语音信息</th> -->
				<th
					data-options="field:'oprat',align:'center',width:130,formatter:operate1">操作
				</th>
			</tr>
		</thead>
	</table>
	 
	<div region="south" split="true" border="false"
		style="background: #DFE8F6;"></div>
	<div id="detail_dialog" class="easyui-dialog" title="事故详情"
		data-options="closed:true"
		style="width: 635px; height: 220px; padding: 10px; height: auto !important; min-height: 120px;">
		<input type="hidden" id="ceditformlAccidentIds">
		<div id="div1" style="height: 200px;">
			<table id="table" cellspacing="0" style="width: 600px;">
				<tr style="height: 25px;">
					<td
						style="width: 100px; text-align: right; border: 1px solid #CCCCCC; background-color: #EDEDED;">事故编号：</td>
					<td id="detail_accidentId"
						style="width: 100px; text-align: left; border: 1px solid #CCCCCC; border-left: 0px;"></td>
					<td
						style="width: 100px; text-align: right; border: 1px solid #CCCCCC; border-left: 0px; background-color: #EDEDED;">用户名称：</td>
					<td id="detail_userName"
						style="width: 100px; text-align: left; border: 1px solid #CCCCCC; border-left: 0px;"></td>
					<td
						style="width: 100px; text-align: right; border: 1px solid #CCCCCC; border-left: 0px; background-color: #EDEDED;">微信昵称：</td>
					<td id="nick_name"
						style="width: 100px; text-align: left; border: 1px solid #CCCCCC; border-left: 0px;"></td>
				</tr>
				<tr style="height: 25px;">

					<td
						style="width: 100px; text-align: right; border: 1px solid #CCCCCC; border-top: 0px; background-color: #EDEDED;">用户类别：</td>
					<td id="detail_reporterType"
						style="width: 100px; text-align: left; border: 1px solid #CCCCCC; border-left: 0px; border-top: 0px;"></td>
					<td
						style="width: 100px; text-align: right; border: 1px solid #CCCCCC; border-left: 0px; border-top: 0px; background-color: #EDEDED;">处理状态：</td>
					<td id="detail_accidentState"
						style="width: 100px; text-align: left; border: 1px solid #CCCCCC; border-left: 0px; border-top: 0px;"></td>
					<td
						style="width: 100px; text-align: right; border: 1px solid #CCCCCC; border-top: 0px; border-left: 0px; background-color: #EDEDED;">手机号：</td>
					<td id="detail_reportPhoneNumber"
						style="width: 100px; text-align: left; border: 1px solid #CCCCCC; border-top: 0px; border-left: 0px;"></td>
				</tr>
				<tr style="height: 25px;">
					<td
						style="width: 100px; text-align: right; border: 1px solid #CCCCCC; border-top: 0px; background-color: #EDEDED;">事故时间：</td>
					<td id="detail_reportTime" colspan="5"
						style="width: 500px; text-align: left; border: 1px solid #CCCCCC; border-left: 0px; border-top: 0px;"></td>


				</tr>
				<tr style="height: 25px;">
					<td
						style="width: 100px; text-align: right; border: 1px solid #CCCCCC; border-top: 0px; background-color: #EDEDED;">事故地址：</td>
					<td id="detail_address" colspan="5"
						style="width: 500px; text-align: left; border: 1px solid #CCCCCC; border: 1px solid #CCCCCC; border-left: 0px; border-top: 0px;"></td>
				</tr>
				
			</table>
			<div align="center" id="shenhe" style="margin-top: 30px;display:block;">
				<input style="width: 125px; height: 30px;" type="button" value="审核通过"
					id="edit_save" /> <input style="width: 125px; height: 30px;"
					type="button" value="审核未通过" onclick="showCase()" id="detail_close" />
					<input style="width: 125px; height: 30px;"
					type="button" value="恶意上报"  id="Malicious_save" />
			</div>
		</div>

	</div>
	<div id="text_dialog" class="easyui-dialog" title="文本信息"
		data-options="closed:true"
		style="width: 600px; height: 300px; padding: 20px;">
		<div id="div1" style="margin-left: 70px; height: 200px;">
			<table id="textTab" cellspacing="0" style="width: 400px;">
			</table>
		</div>
	</div>
	<div id="case_dialog" class="easyui-dialog" title="填写审核不通过原因"
		data-options="closed:true"
		style="width: 550px;  padding: 20px;height: auto !important; min-height: 330px;">
		<div id="div1" style="margin-left: 70px; height: 200px;">
			<div style="width: 300px;border-bottom: 1px dashed #303030;height:25px;margin-bottom:5px;">
			<input type="checkbox" id="ToChujing" value="1" onclick="addThiss()"><font size="3">出警核实现场</font>
			</div>
			
			<table id="textTab1" cellspacing="0" style="width: 400px;">
			
			</table>
		</div>
		<div align="center" style="margin-top: 105px;">
				<input style="width: 80px; height: 30px;" type="button" value="确定"
					id="edit_save2" /> <input style="width: 80px; height: 30px;"
					type="button" value="取消" onclick="closeCase()" />
			</div>
	</div>
	
	<div id="change_dialog" class="easyui-dialog" title="事故处理转发"
		data-options="closed:true"
		style="width: 373px;  padding: 20px;height: auto !important; min-height: 330px;">
		<div class="easyui-panel" title="事故编号确认"
			style="height: 340px; width: 320px; padding: 8px;">
			<table id="textTab2" cellspacing="0" style="width: 300px;">
			</table>
			<table id="textTab3" cellspacing="0" style="width: 300px;">
				<tr style="height: 25px;"><td id="textTime" style="text-align: center;vertical-align:middle; border: 1px solid #CCCCCC; background-color: #FFFFFF;"valign="top">选择转发大队：</td>
				<td id="textTime" style="text-align: center;vertical-align:middle; border: 1px solid #CCCCCC; background-color: #FFFFFF;"valign="top"><select id="change_departmentId"></select></td></tr>
			</table>
		</div>
		<div align="center" style="heigth: 50px; line-height: 50px;">
			<input type="button" value="确定" id="develop" /> &nbsp;&nbsp;&nbsp;<input
				type="button" value="取消" id="cancel" />
		</div>
	</div>
	

	<script type="text/javascript">
		var managerName = '${session.User.userId}';
		var managerRole = '${session.UserRole.role}';
		var managerRoleId = '${session.UserRole.roleId}';
		 var LODOP;
		String.prototype.trim = function() {
			return this.replace(/^\s\s*/, '').replace(/\s\s*$/, '');
		};
		var departmentMap;
		var procedureDate = new Array();
		function showCase() {
			$('#case_dialog').dialog('open');
			$.ajax({
				url : "getSelectCase.action",
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
		function operate1(value, row, index) {
			if(managerRoleId!="R_Insurance"){
				if(managerRoleId=="R_Manager"){
					if(row.accidentState =='审核通过'){
						return "<a href='javascript:detail();'>查看</a> | <a href='javascript:toOther(0);'>归为其它</a>";
					}else if(row.accidentState =='其它'){
						return "<a href='javascript:detail();'>查看</a> | <a href='javascript:toOther(1);'>归为通过</a>";
					}else if(row.accidentState =='恶意上报' || row.accidentState =='现场核实'){
						return '<a href=\'javascript:detail();\'>查看</a>';
					}else{
						return '<a href=\'javascript:detail();\'>审核</a>';
					}
						
				}else{
					if(row.accidentState =='审核通过' || row.accidentState =='恶意上报' || row.accidentState =='其它' || row.accidentState =='现场核实')
						return '<a href=\'javascript:detail();\'>查看</a>';
					else
						return '<a href=\'javascript:detail();\'>审核</a>';
					
					
				}
				
				
			}else{
				return '';
			}
			
		}
		function toOther(num){
			var accidentState;
			if(num==0){
				accidentState="其它";
			}else{
				accidentState="审核通过";
			}
			var row = $("#dg").datagrid('getSelected');
			$.ajax({
				url : "updateAccidentStateOther.action",
				async : false,
				data : "accidentState=" + accidentState+"&accidentId=" + row.formlAccidentId,
				dataType : "json",
				type : "post",
				success : function(data) {
					if (data.respCode==0) {
						alert(data.respInfo);
						var nickName = $("#nickName").val().trim();
						var reportPhoneNumber = $(
								"#reportPhoneNumber").val().trim();
						var emergencyCall = $("#emergencyCall").val()
								.trim();
						var departmentId = $("#departmentId").val().trim();
						var startTime = $("#startTime").datebox(
								'getValue');
						var endTime = $("#endTime").datebox(
								'getValue');
						var accidentState = $("#accidentState").val().trim();
						$('#dg').datagrid({
							loadFilter : pagerFilter
						}).datagrid(
								'loadData',
								getData(startTime, endTime,
										reportPhoneNumber,
										nickName, emergencyCall,departmentId,accidentState));
					} else {
						alert(data.respInfo);
					}
				},
				error : function(data) {
					alert("网络繁忙!请稍后再试!");
				}
			});
		}
		
		
		
		function operate2(value, row, index) {
				return '<a href=\'javascript:display1();\'>查看</a>';
		}
		function operate3(value, row, index) {
				return '<a href=\'javascript:display2();\'>查看</a>';
		}
		function operate4(value, row, index) {
				return '<a href=\'javascript:display3();\'>查看</a>';
		}
		function addThis(index){
			$("#ToChujing").attr("checked",false);
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
		function addThiss(){
			var toChujing=$("#ToChujing").attr("checked");
			if(toChujing=='checked'){
				$("#textTab1 input[type='checkbox']").attr("checked", false);
				var custResText = "您好，本起事故需要进一步核实，请保护现场，等候交警处理。";
				$("#custResText").val(custResText);
			}else{
				$("#custResText").val('');
			}
			
		}
		
		
		function detail() {
			$("#detail_accidentId").html("");
			$("#detail_reportTime").html("");
			$("#detail_userName").html("");
			$("#detail_reportPhoneNumber").html("");
			$("#detail_address").html("");
			$("#detail_accidentState").html("");
			$("#detail_accepter").html("");
			$("#nick_name").html("");
			$("#detail_reporterType").html("");
			$('#textInfo').html("");
			var row = $("#dg").datagrid('getSelected');
			$.ajax({
				url : "getAccidentByAccidentId.action",
				async : false,
				data : "accidentId=" + row.formlAccidentId,
				dataType : "json",
				type : "post",
				success : function(data) {
					$("#detail_accidentId").html(data.formlAccidentId);
					$("#editAccidentId").val(data.formlAccidentId);
					$("#editDepartmentId").val(data.departmentId);
					$("#detail_reportTime").html(data.reportTimeString);
					$("#detail_reporterType").html(data.reporterType);
					$("#detail_reportPhoneNumber").html(data.reportPhoneNumber);
					$("#nick_name").html(data.nickname);
					$("#detail_address").html(data.address);
					$("#detail_userName").html(data.realName);
					$("#detail_accidentState").html(data.accidentState);
					if(data.accidentState=="审核通过" || data.accidentState=="恶意上报" || data.accidentState=="现场核实"){
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
			$('#detail_close').attr({
				disabled : false
			});
		}

		function display1() {
			var row = $("#dg").datagrid('getSelected');
			window.open("page/TbAddressInfo.jsp?accidentId=" + row.formlAccidentId, "");
		}
		function display2() {
			var row = $("#dg").datagrid('getSelected');
			window.open("page/TbPicInfo.jsp?accidentId=" + row.formlAccidentId, "");
		}
		function display3() {
			$("#textTab").empty();
			var row = $("#dg").datagrid('getSelected');
			var data = getTbAccidentText(row.accidentId);
			var tr = "<tr style=\"height: 25px;\"><td style=\"text-align: center;vertical-align:middle; border: 1px solid #CCCCCC; width:30%;background-color: #EDEDED;;\"valign=\"top\">时间</td><td style=\"text-align: center; vertical-align:middle;border: 1px solid #CCCCCC;width:70%; background-color: #EDEDED;\"valign=\"top\">备注信息</td></tr>";
			$('#text_dialog').dialog('open');
			for ( var i = 0; i < data.length; i++) {
				var textMessage = data[i].textMessage;
				var textTime = data[i].textTimeString;
				tr += "<tr style=\"height: 25px;\"><td id=\"textTime\" style=\"text-align: center;vertical-align:middle; border: 1px solid #CCCCCC; background-color: #FFFFFF;\"valign=\"top\">"
						+ textTime
						+ "</td><td id=\"textInfo\" style=\"text-align: left;vertical-align:middle; border: 1px solid #CCCCCC; background-color: #FFFFFF;\"valign=\"top\">"
						+ textMessage + "</td></tr>";
			}
			$("#textTab").append(tr);
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
					alert("网络繁忙!请稍后再试!");
				}
			});
			return rows;
		}

		
		function getData(startTime, endTime, reportPhoneNumber, nickName,
				emergencyCall,departmentId,accidentState) {
			var rows = null;
			var dg = $("#dg");
			var opts = dg.datagrid('options');
			var pageSize = opts.pageSize;
			var pageIndex = opts.pageNumber;
			$.ajax({
				url : "getAccident.action",
				async : false,
				data : "pageSize=" + pageSize + "&pageIndex=" + pageIndex
						+ "&startTime=" + startTime + "&endTime=" + endTime
						+ "&reportPhoneNumber=" + reportPhoneNumber
						+ "&nickName=" + nickName + "&emergencyCall="
						+ emergencyCall+ "&departmentId="
						+ departmentId+"&accidentState="+accidentState,
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
					alert("网络繁忙!请稍后再试!");
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
				var reportPhoneNumber = $("#reportPhoneNumber").val().trim();
				var emergencyCall = $("#emergencyCall").val().trim();
				var departmentId = $("#departmentId").val().trim();
				var startTime = $("#startTime").datebox('getValue');
				var endTime = $("#endTime").datebox('getValue');
				var accidentState = $("#accidentState").val().trim();
				var newData = getData(startTime, endTime, reportPhoneNumber,
						nickName, emergencyCall,departmentId,accidentState);
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
			var toolbar;
			if(managerRole=='系统管理员' || managerRole == '支队管理员'){
				toolbar = [ {
					text : '导出数据',
					iconCls : 'icon-add',
					handler : function() {
						var nickName = $("#nickName").val().trim();
						var reportPhoneNumber = $("#reportPhoneNumber").val().trim();
						var emergencyCall = $("#emergencyCall").val().trim();
						var startTime = $("#startTime").datebox('getValue');
						var endTime = $("#endTime").datebox('getValue');
						var departmentId = $("#departmentId").val().trim();
						var accidentState = $("#accidentState").val().trim();
						accidentState=encodeURI(accidentState);
						accidentState=encodeURI(accidentState);
						var canshu = "nickName=" + nickName + "&reportPhoneNumber="
								+ reportPhoneNumber + "&emergencyCall=" + emergencyCall
								+ "&startTime=" + startTime + "&endTime=" + endTime
								+ "&departmentId="+departmentId+"&accidentState="+accidentState;
						var lianjie = "toExportData.action?" + canshu;
						window.location.href = lianjie;
					}
				},"-",{
					text : '事故所属大队重新分配',
					iconCls : 'icon-add',
					handler : function() {
						var rows = $('#dg').datagrid('getSelections');
						if (rows.length == 0) {
							alert("请选择要重新分配的事故！");
							return false;
						} else {
							$('#change_dialog').dialog('open');
							var formlAccidentIds = '';
							$("#textTab2").html("");
							var tr = "<tr style=\"height: 25px;\"><td id=\"textTime\" style=\"text-align: center;vertical-align:middle; border: 1px solid #CCCCCC; background-color: #FFFFFF;\"valign=\"top\">事故编号</td></tr>";
							$("#textTab2").append(tr);
							for ( var i = 0; i < rows.length; i++) {
								formlAccidentIds = formlAccidentIds + "," + rows[i].formlAccidentId;
								tr = "<tr style=\"height: 25px;\"><td id=\"textTime\" style=\"text-align: center;vertical-align:middle; border: 1px solid #CCCCCC; background-color: #FFFFFF;\"valign=\"top\">"
											+ rows[i].formlAccidentId
											+ "</td></tr>";
								$("#textTab2").append(tr);
							}
							$("#ceditformlAccidentIds").val(formlAccidentIds);
							$("#change_departmentId").html("");
							for(var i=0;i<departmentMap.length;i++){
								if(departmentMap[i].parentId !=null){
									$("#change_departmentId").append($("<option value='"+departmentMap[i].departmentId+"'>"+departmentMap[i].departmentName+"</option>"));
								}
								
							}
						}
					}
				} ,"-",{
					text : '统计',
					iconCls : 'icon-add',
					handler : function() {
						var content="";
						var startTime = $("#startTime").datebox('getValue');
						var endTime = $("#endTime").datebox('getValue');
						$.ajax({
							url : "getAccidentStatistics.action",
							async : false,
							data : "startTime="+startTime+"&endTime="+endTime,
							dataType : "json",
							type : "post",
							success : function(data) {
								content+="<style><!--#baobiao td{text-align:center;}#baobiao{width:100%;border:1px solid #CDC9C9;}#baobiao tr{height:40px;}--></style><body style='width:100%' ><table id='baobiao' cellspacing='0'  cellpadding='0' ><tr><td colspan='8' >日期:"+startTime+"—"+endTime+"</td></tr><tr style='background-color:#CDC9C9;'><td>大队名称</td><td>审核通过</td><td>现场核实</td><td>待定</td><td>上报</td><td>恶意上报</td><td>其他</td><td>总计</td></tr>";
								for(var i=0;i<data.length;i++){
									if(i%2 ==0){
										content+="<tr style='background-color:#DEDEDE;'><td>"+data[i].departmentName+"</td><td>"+data[i].pass+"</td><td>"+data[i].check+"</td><td>"+data[i].pending+"</td><td>"+data[i].report+"</td><td>"+data[i].evil+"</td><td>"+data[i].other+"</td><td>"+data[i].total+"</td></tr>";
									}else{
										content+="<tr style=''><td>"+data[i].departmentName+"</td><td>"+data[i].pass+"</td><td>"+data[i].check+"</td><td>"+data[i].pending+"</td><td>"+data[i].report+"</td><td>"+data[i].evil+"</td><td>"+data[i].other+"</td><td>"+data[i].total+"</td></tr>";
									}
									
								}
								content+="</table></body>";
							},
							error : function(data) {
								alert("网络繁忙!请稍后再试!");
							}
						});
						LODOP=getLodop();  
						LODOP.PRINT_INIT("");
						LODOP.SET_PRINT_STYLE("FontSize",18);
						LODOP.SET_PRINT_STYLE("Bold",1);
						LODOP.ADD_PRINT_TEXT(50,261,260,39,"轻微事故处理统计");
						//LODOP.ADD_PRINT_HTM(80,431,260,39,"时间");
						LODOP.ADD_PRINT_HTM(120,50,650,700,content);
						LODOP.PREVIEW();
					}
				}];
			}else{
				toolbar = [ {
					text : '导出数据',
					iconCls : 'icon-add',
					handler : function() {
						var nickName = $("#nickName").val().trim();
						var reportPhoneNumber = $("#reportPhoneNumber").val().trim();
						//var reporterType = $("#reporterType").val().trim();
						var startTime = $("#startTime").datebox('getValue');
						var endTime = $("#endTime").datebox('getValue');
						var departmentId = $("#departmentId").val().trim();
						var accidentState = $("#accidentState").val().trim();
						accidentState=encodeURI(accidentState);
						accidentState=encodeURI(accidentState);
						var canshu = "nickName=" + nickName + "&reportPhoneNumber="
								+ reportPhoneNumber 
								+ "&startTime=" + startTime + "&endTime=" + endTime
								+ "&departmentId="+departmentId+"&accidentState="+accidentState;
						var lianjie = "toExportData.action?" + canshu;
						//+ "&reporterType=" + reporterType
						window.location.href = lianjie;
					}
				}];
			}
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
			$.ajax({
				url : "getDepartmentMap.action",
				async : false,
				data : "managerRole="+managerRole,
				dataType : "json",
				type : "post",
				success : function(data) {
					departmentMap = data;
					if(data.length==1 ){
						$("#departmentId").append($("<option value='"+data[0].departmentId+"'>"+data[0].departmentName+"</option>"));
						$("#departmentId").attr("disabled",true);
					}else{
						for(var i=0;i<data.length;i++){
							$("#departmentId").append($("<option value='"+data[i].departmentId+"'>"+data[i].departmentName+"</option>"));
						}
					}
					
				},
				error : function(data) {
					alert("网络繁忙!请稍后再试!");
				}
			});
			var width = $(document).width();
			var height = $(document).height();
			var nickName = $("#nickName").val().trim();
			var reportPhoneNumber = $("#reportPhoneNumber").val().trim();
			var emergencyCall = $("#emergencyCall").val().trim();
			var departmentId = $("#departmentId").val().trim();
			var startTime = $("#startTime").datebox('getValue');
			var endTime = $("#endTime").datebox('getValue');
			var accidentState = $("#accidentState").val().trim();
			$('#dg').datagrid({
				loadFilter : pagerFilter,
				toolbar:toolbar,
				width : width - 20,
				height : height - 45
			}).datagrid(
					'loadData',
					getData(startTime, endTime, reportPhoneNumber, nickName,
							emergencyCall,departmentId,accidentState));
			$("#searchpanel").panel({
				width : width - 20
			});
			$("#develop").click(function(){
				var departmentId = $("#change_departmentId").val();
				var formlAccidentIds = $("#ceditformlAccidentIds").val();
				$.ajax({
					url : "reDevelopDepartment.action",
					async : false,
					data : "formlAccidentIds="+formlAccidentIds + "&departmentId="+departmentId,
					dataType : "json",
					type : "post",
					success : function(data) {
						if(data.respCode ==0){
							alert(data.respInfo);
							$('#change_dialog').dialog('close');
							var nickName = $("#nickName").val().trim();
							var reportPhoneNumber = $("#reportPhoneNumber").val()
									.trim();
							var emergencyCall = $("#emergencyCall").val().trim();
							var startTime = $("#startTime").datebox('getValue');
							var endTime = $("#endTime").datebox('getValue');
							var departmentId = $("#departmentId").val().trim();
							var accidentState = $("#accidentState").val().trim();
							$('#dg').datagrid({
								loadFilter : pagerFilter,
								pageNumber : 1
							}).datagrid(
									'loadData',
									getData(startTime, endTime, reportPhoneNumber,
											nickName, emergencyCall,departmentId,accidentState));
						}else{
							alert(data.respInfo);
						}
						
					},
					error : function(data) {
						alert("网络繁忙!请稍后再试!");
					}
				});
			});
			$("#search").click(
					function() {
						var nickName = $("#nickName").val().trim();
						var reportPhoneNumber = $("#reportPhoneNumber").val()
								.trim();
						var emergencyCall = $("#emergencyCall").val().trim();
						var startTime = $("#startTime").datebox('getValue');
						var endTime = $("#endTime").datebox('getValue');
						var departmentId = $("#departmentId").val().trim();
						var accidentState = $("#accidentState").val().trim();
						$('#dg').datagrid({
							loadFilter : pagerFilter,
							pageNumber : 1
						}).datagrid(
								'loadData',
								getData(startTime, endTime, reportPhoneNumber,
										nickName, emergencyCall,departmentId,accidentState));
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
			//	$("#reporterType").val("");
				$("#accidentState").val("");
			});
			$("#edit_save").click(
					function() {
						var row = $("#dg").datagrid('getSelected');
						if(row.departmentId==null){
							alert("尚未确定事故归属大队！");
							return false;
						}
						var accidentId = row.formlAccidentId;
						$.ajax({
							url : "saveProcedureMessage.action",
							data : "accidentId=" + accidentId
									+ "&managerName=" + managerName,
							dataType : "json",
							type : "post",
							success : function(data) {
								if (data.respCode == 0) {
									alert("审核完毕!回复成功!");
									$('#detail_dialog').dialog('close');
									var nickName = $("#nickName").val().trim();
									var reportPhoneNumber = $(
											"#reportPhoneNumber").val().trim();
									var emergencyCall = $("#emergencyCall").val()
											.trim();
									var departmentId = $("#departmentId").val().trim();
									var startTime = $("#startTime").datebox(
											'getValue');
									var endTime = $("#endTime").datebox(
											'getValue');
									var accidentState = $("#accidentState").val().trim();
									$('#dg').datagrid({
										loadFilter : pagerFilter
									}).datagrid(
											'loadData',
											getData(startTime, endTime,
													reportPhoneNumber,
													nickName, emergencyCall,departmentId,accidentState));
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
						var editDepartmentId = $("#editDepartmentId").val();
						if(editDepartmentId==''){
							alert("尚未确定事故归属大队！");
							return false;
						}
						
						var toChujing=$("#ToChujing").attr("checked");
						var chujingType=0;
						if(toChujing=='checked'){
							chujingType=1;
						}
						var checkBoxes = $("#textTab1 input[type='checkbox']");
						var procedureIds= '';
						
						for(var i=0;i<checkBoxes.length;i++){
							if($(checkBoxes[i]).attr("checked")=='checked'){
								procedureIds = procedureIds +"," +$(checkBoxes[i]).attr("value");
							}
						}
						if(procedureIds=='' & chujingType==0){
							alert("请选择审核不通过原因！");
							return false;
						}
						var custResText = $("#custResText").val().trim();
						$.ajax({
							url : "responseWei.action",
							data : "procedureIds=" + procedureIds + "&custResText="+custResText
									+ "&accidentId=" + $("#editAccidentId").val()+"&chujingType="+chujingType,
							dataType : "json",
							type : "post",
							success : function(data) {
								if (data.respCode == 0) {
									alert("审核完毕!回复成功!");
									$('#case_dialog').dialog('close');
									$('#detail_dialog').dialog('close');
									var nickName = $("#nickName").val().trim();
									var reportPhoneNumber = $(
											"#reportPhoneNumber").val().trim();
									var emergencyCall = $("#emergencyCall").val()
											.trim();
									var departmentId = $("#departmentId").val().trim();
									var startTime = $("#startTime").datebox(
											'getValue');
									var endTime = $("#endTime").datebox(
											'getValue');
									var accidentState = $("#accidentState").val().trim();
									$('#dg').datagrid({
										loadFilter : pagerFilter
									}).datagrid(
											'loadData',
											getData(startTime, endTime,
													reportPhoneNumber,
													nickName, emergencyCall,departmentId,accidentState));
								} else {
									alert("网络繁忙!请刷新后重新审核!");
								}

							},
							error : function(data) {
								alert("网络繁忙!请稍后再试!");
							}
						});

					});	
			
			$("#Malicious_save").click(
					function() {
						if (window.confirm("确认将此条事故设定为恶意上报吗？")) {
							var row = $("#dg").datagrid('getSelected');
							if(row.departmentId==null){
								alert("尚未确定事故归属大队！");
								return false;
							}
							var accidentId = row.formlAccidentId;
							$.ajax({
								url : "updateAccidentStateMalicious.action",
								data : "accidentId=" + accidentId
										+ "&managerName=" + managerName,
								dataType : "json",
								type : "post",
								success : function(data) {
									if (data.respCode == 0) {
										alert("审核完毕!回复成功!");
										$('#detail_dialog').dialog('close');
										var nickName = $("#nickName").val().trim();
										var reportPhoneNumber = $(
												"#reportPhoneNumber").val().trim();
										var emergencyCall = $("#emergencyCall").val()
												.trim();
										var departmentId = $("#departmentId").val().trim();
										var startTime = $("#startTime").datebox(
												'getValue');
										var endTime = $("#endTime").datebox(
												'getValue');
										var accidentState = $("#accidentState").val().trim();
										$('#dg').datagrid({
											loadFilter : pagerFilter
										}).datagrid(
												'loadData',
												getData(startTime, endTime,
														reportPhoneNumber,
														nickName, emergencyCall,departmentId,accidentState));
									} else {
										alert("网络繁忙!请刷新后重新审核!");
									}

								},
								error : function(data) {
									alert("网络繁忙!请稍后再试!");
								}
							});
						}else{
							return false;
						}
						
						

					});
		});
	</script>
</body>
</html>