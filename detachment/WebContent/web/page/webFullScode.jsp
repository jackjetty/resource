<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=8" />
<title>满分学员管理</title>
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
		style="height: 80px; padding: 10px; overflow: hidden;">
		<table style="height: 100%">
			<tr
				style="Valign: center; text-decoration: none; line-heigth: 30px; height: 30px; margin-left: 10px;">
				 
				<td>身份证号：</td>
				<td><input type="text" id="identityCard"
					style="width: 100px;" /></td>
				<td  >档案编号：</td>
				<td><input type="text" id="fileNum" style="width: 120px;" /></td>
				<td>累计学时：</td>
				<td><input type="text" id="totalHour" style="width: 120px;" /></td>
				<td>当前状态：</td>
				<td><select id="state">
					<option value="学习中">学习中</option>
					<option value="预约中">预约中</option>
					<option value="已预约">已预约</option>
					<option value="抵扣成功">抵扣成功</option>
				</select></td> 
				<td>
					<a id="search" class="pushBtn" href="javascript:void(0);" hidefocus="true">
						<img width="20" height="20" src="image/icon/search.gif">
						<b>查询</b>
					</a>
				</td>
				<td><a id="clear" class="pushBtn" href="javascript:void(0);"
					hidefocus="true"> <b>条件清空</b>
				</a></td>
			</tr>
		</table>
	</div>
	<table id="dg" class="easyui-datagrid" title="满分学习列表"  ></table>
	<script type="text/javascript"> 
		String.prototype.trim = function() {
			return this.replace(/^\s\s*/, '').replace(/\s\s*$/, '');
		}; 
		$(function(){
			var width = $(document).width();
			var height = $(document).height();
			$('#dg').datagrid({ 
		        width : width - 20,
				height : height - 45,
		        nowrap: true,
		        autoRowHeight: false,
		        striped: true,
		        collapsible:false,
		        rownumbers:false,
		        pageList:[20,50,100,200], 
		        url:'getFullScode.action', 
		        queryParams:{identityCard:"",fileNum:"",totalHour:"",state:"学习中"}, 
		        sortName: 'firstStudyTime',
		        sortOrder: 'desc',
		        remoteSort: true,
		        idField:'identityCard',
		        singleSelect:true, 
		        frozenColumns:[[ 
		                {title:'身份证编号',field:'identityCard',align:'center',width:150} 
		            ]],
		        columns:[
		            [
		                {field:'fileNum',title:'档案编号',align:'center',width:120},
		                {field:'firstStudyTime',title:'开始学习时间',align:'center',width:100}, 
		                {field:'userName',title:'用户姓名',align:'center',width:100},
		                {field:'phoneNumber',title:'联系号码',align:'center',width:100},
		                {field:'nickname',title:'微信昵称',align:'center',width:100},
		                {field:'totalHour',title:'累计学时',align:'center',width:100},
		                {field:'state',title:'当前状态',align:'center',width:100},
		                {title:'抵扣学时',field:'op1',align:'center',width:100,formatter:operate2} 
		            ]],
		        pagination:true, 
		        rowStyler:function(index,row){  
		        	 
		        },
		        onClickRow:function(rowIndex){ 
		                
		        },
		        onDblClickRow: function(rowIndex, field, value) { 
		        	 
		        }
		    });
			
		    var p = $('#dg').datagrid('getPager');
		    $(p).pagination({
		        displayMsg:'当前显示从{from}到{to}共{total}记录',
		        onBeforeRefresh:function(pageNumber, pageSize){
		            $(this).pagination('loading'); 
		            $(this).pagination('loaded');
		        } 
		    });
		    
		});
		
		
		function operate2(value, row, index) {
			if(row.totalHour>11 && row.state=="学习中"){
		    	return "<a href=\'javascript:updateState(\""+row.fullScodeId+"\");\'>确定</a>"; 
		    }else if(row.totalHour>11 && row.state=="已预约"){
			    	return "<a href=\'javascript:fnDeduct(\""+row.fullScodeId+"\");\'>抵扣</a>"; 
			}else{
			    	return "";
			}
				
		} 
		 
		 function fnDeduct(fullScodeId) {
			   if(!confirm("您确定将其学时进行抵扣 ?" )) {
				   return   ;
			   }
			   $.ajax({
					url : "deductFullScode.action",
					async : false,
					data : "fullScodeId=" + fullScodeId ,
					dataType : "json",
					type : "post",
					success : function(data) { 
						 alert(data.respInfo);
						 if (data.respCode == 0) { 
							$("#dg").datagrid('reload');
						    $('#dg').datagrid('clearSelections'); 
							return false;
						 }   
					},
					error : function(data) {
						alert("error 后台出现错误！");
					}
				});	
		 } 
		 
		 function updateState(fullScodeId){
			 $.ajax({
					url : "updateFullScodeState.action",
					async : false,
					data : "fullScodeId=" + fullScodeId ,
					dataType : "json",
					type : "post",
					success : function(data) { 
						 if (data.respCode == 0) { 
							 alert("回复成功!");
							$("#dg").datagrid('reload');
						    $('#dg').datagrid('clearSelections'); 
							return false;
						 } else{
							 alert("回复失败~!");
						 }  
					},
					error : function(data) {
						alert("error 后台出现错误！");
					}
				});	
		 }
		 
		 
		 
		  
 
		$(function() {
			 
			var width = $(document).width();
			var height = $(document).height(); 
			$("#searchpanel").panel({
				width : width - 20
			});
			$("#search").click(
					function() {
						var identityCard = $("#identityCard").val().trim();
						var fileNum = $("#fileNum").val().trim();
						var totalHour = $("#totalHour").val().trim();
						var state = $("#state").val().trim();
						
						var  query={identityCard:identityCard, 
								fileNum:fileNum,
								totalHour:totalHour,
								state:state}; //把查询条件拼接成JSONv
				        $("#dg").datagrid('options').queryParams=query; //把查询条件赋值给datagrid内部变量
				        $("#dg").datagrid('load');
				        $('#dg').datagrid('clearSelections');  
					});
			 

			$("#clear").click(function() {
				 
				$("#identityCard").val("");
				$("#fileNum").val("");
				$("#totalHour").val("");
				$("#state").val("学习中");
			}); 
		});
	</script>
	 
</body>
</html>