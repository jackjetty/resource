<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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
</script>
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
	margin: 2px 0 2px 6px;
	border: 0;
}

.pushBtn1:hover {
	background: url("image/button/btnover_bg_left.gif") no-repeat scroll
		left top transparent;
	text-decoration: none;
}
td{
	padding:0px 2px 0px 2px;
}
-->
</style>
</head>
<body style="background: #DFE8F6;" id="tt">
	<div id="searchpanel" class="easyui-panel panel-margin-buttom"
		data-options="collapsible:true,collapsed:true" title="查询条件"
		style="height: 80px; padding: 10px; vertical-align: center;">
		<table height="100%">
			<tr style="align: center; text-decoration: none; line-heigth: 30px; height: 30px; margin-left: 10px;">
				<td>开始时间：</td>
				<td><input type="text" id="startTime" class="easyui-datebox"
					editable="false" style="width: 90px;" value="<s:property value='#request.startTime'/>" /></td>
				<td>结束时间：</td>
				<td><input type="text" id="endTime" class="easyui-datebox"
					editable="false" style="width: 90px;" value="<s:property value='#request.endTime' />" /> </td>
				<td>记录状态：</td>
				<td> 
				      <select id="doAnswerState" style="width: 120px;">
							<option value="">请选择</option>
							<option value="未回复">未回复</option>
							<option value="已回复">已回复</option>
				      </select>
				 </td>	
				<td><a id="search" class="pushBtn" href="javascript:void(0);"
					hidefocus="true"> <img width="20" height="20" src="image/icon/search.gif"> <b>查询</b>
				</a></td>
				<td><a id="clear" class="pushBtn" href="javascript:void(0);"
					hidefocus="true"> <b>条件清空</b>
				</a></td>
			</tr>
		</table>
	</div>
	<table id="dg" class="easyui-datagrid" title="百姓事解答信息列表"  ></table>
	 
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
			<div style="float:right;margin:0px 8px 2px 0px; " >	
				<input  type="button" onclick="huifu()" value="发送" class="pushBtn1" style="width: 70px;" />
			</div>
		</div>
	</div>
	
	
</body>
</html>


<script type="text/javascript">
        $(function(){
        	var width = $(document).width();
			var height = $(document).height();
			$('#dg').datagrid({  
		        width : width -32,
				height : height - 45,
		        nowrap: true,
		        autoRowHeight: false,
		        striped: true,
		        collapsible:false,
		        rownumbers:false,
		        pageList:[20,50,100,200], 
		        url:'getDoAnswer.action', 
		        queryParams:{startTime:$("#startTime").datebox('getValue'),
		        	endTime:$("#endTime").datebox('getValue'),
		        	doAnswerState:$("#doAnswerState").val()}, 
		        sortName: 'answerTime',
		        sortOrder: 'desc',
		        remoteSort: true,
		        idField:'doAnswerId',
		        singleSelect:true, 
		        toolbar:'#toolbar',
		        frozenColumns:[[ 
		                {title:'编号',field:'doAnswerId',hidden:true,align:'center',width:150} 
		            ]],
		        columns:[
		            [
		                {field:'nickName',title:'微信昵称',align:'center',width:150},
		                {field:'phoneNumber',title:'手机号码',align:'center',width:150}, 
		                {field:'answerTime',title:'反馈时间',align:'center',width:150},
		                {field:'answerText',title:'反馈内容',align:'center',width:550},  
		                {title:'操作',field:'op1',align:'center',width:150,formatter:rowformater} 
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
        
        
        function rowformater(value, row, index) {
    		return "<a href=\"javascript:;\" onclick=\"view('"
    				+ row.doAnswerId + "')\">"+row.op1+"</a>";
    	}
        
        
        function view(doAnswerId) {
    		$("#tablehf").empty();
    		$("#btnHuifu").val('');
    		$("#backId").val(doAnswerId);
    		$('#detail_dialog').dialog('open');
    		$('#dg').datagrid('clearSelections');
    		$.post(
					"getUserDaInfo.action",
					{ doAnswerId : doAnswerId  },
					function(data, textStatus) {
						if (textStatus == "success") {
							 
							fnAddRows(data.rows);

						} else
							alert("访问出错");
					}, 'json');
          }

          function fnAddRows(rows){
        	  var tab1;
        	  $(rows).each(
						function(index) {
							row = rows[index];
							 tab1="";
			    			if(row.type=="1"){
			    				tab1 += "<tr align=\"left\"><td><div style=\"margin-left:40px;\">"
			    					+ row.answerTime
			    					+ "</div><div style=\"float:left;margin-top:10px;\"><img src=\"image/touxiang.jpg\" /></div><div id=\"haha\" class=\"triangle-right left\" style=\"float:left\" >"
			    					+ row.answerText + "</div></td></tr> ";
			    			}else{
			    				tab1 += "<tr align=\"right\"><td><div style=\"margin-right:40px;\">"
			    					+ row.answerTime
			    					+ "</div><div style=\"float:right;margin-top:10px;\"><img src=\"image/touxiang.jpg\" /><br>"+row.nickName+"</div><div id=\"haha\" class=\"triangle-right right\" style=\"float:right\" >"
			    					+ row.answerText + "</div></td></tr> ";	
			    			} 
			    			$("#tablehf").append(tab1);
							
			 });
			 $("#textDJ").click();
			 $('#detail_close').attr({
	    			disabled : false
	    	 });
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
      				var doAnswerState = $("#doAnswerState").val();
      				if (window.confirm("确认导出" + startTime + "到" + endTime
      						+ "期间的记录？")) {
      					var canshu = "startTime=" + startTime + "&endTime="
      							+ endTime+ "&doAnswerState="+ doAnswerState;
      					var lianjie = "exportDaInfo.action?" + canshu;
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
        			url : "addAnswerCustRes.action",
        			async : false,
        			data : "doAnswerId=" + $("#backId").val()+"&content="+content,
        			dataType : "json",
        			type : "post",
        			success : function(data) {
        				if(data.respCode==0){
        					alert("回复成功！");
        					var tab="<tr align='right'><td style=''><div style='margin-right:40px;'>"+data.time+"</div><div style='float:right;margin-top:10px;'><img src='image/touxiang.jpg' /></div><div id='haha' class='triangle-right right' style='float:right' >"+content+"</div></td></tr>";
        					$("#tablehf").append(tab);
        					$("#textDJ").click();
        					$("#btnHuifu").val('');
        				}else {
        					alert(data.respInfo);
        				} 
        			},
        			error : function(data) {
        				alert("网络繁忙!请稍后再试!");
        			}
        		});
        		
        	}
		
		$(function(){
			$("#clear").click(function() {
				 var  startTime="<s:property value='#request.startTime'/>";
				 var  endTime="<s:property value='#request.endTime'/>";
				 $("#startTime").datebox('setValue', startTime);
				 $("#endTime").datebox('setValue', endTime);
				 $("#doAnswerState").val(""); 
			});
			$("#search").click(function() {
						var startTime = $("#startTime").datebox('getValue');
		  				var endTime = $("#endTime").datebox('getValue');
		  				var doAnswerState = $("#doAnswerState").val();
						
						var  query={startTime:startTime, 
								endTime:endTime,
								doAnswerState:doAnswerState }; //把查询条件拼接成JSONv
				        $("#dg").datagrid('options').queryParams=query; //把查询条件赋值给datagrid内部变量
				        $("#dg").datagrid('load');
				        $('#dg').datagrid('clearSelections');  
					});
	    });
	 
 
</script>


