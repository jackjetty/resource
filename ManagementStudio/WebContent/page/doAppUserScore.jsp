<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>  
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>	
<!DOCTYPE html PUBLIC "-//W3C//Dth HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dth">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加功能菜单</title>
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
-->
</style>
</head>
<body style="background: #DFE8F6;">
	
	<table id="display" class="easyui-datagrid" title="App用户积分段及人数统计" style="width: 700px; height: 250px"
		data-options="rownumbers:true,singleSelect:true">
		<thead>
		<c:forEach items="${listMap}" var="item2" begin="0"  step="6" varStatus="status2">
		<c:if test="${(status2.index)%6==0}">
			<tr>
			<th>积分段</th>
			<c:forEach items="${listMap}" var="item" begin="${status2.index}"  step="1" end='${status2.index+5}' varStatus="status">
			<th data-options="field:'listprice',width:150,align:'center'">${item.IntegralPart}</th>
			</c:forEach>
			</tr>
			<tr>
			<th>用户数</th>
			<c:forEach items="${listMap}" var="item" begin="${status2.index}"  step="1" end='${status2.index+5}' varStatus="status">
			<th><a href="#" onclick="view('${creditsLink}?min=${item.min}&max=${item.max}','${item.IntegralPart}')">${item.Credits}</a></th>
			</c:forEach>
			</tr>
		</c:if>
		</c:forEach>	
		</thead>
				 
	</table>
	<script type="text/javascript">
	var creditsLink =  '${creditsLink}';
	function view(link,place){
		parent.window.addTab(place+"详细列表",link);
	}
		$(function() {
			var width = $(document).width();
			var height = $(document).height();
			$("#display").datagrid({
				width:width-18,
				height:height-20
			});
		});
	</script>
</body>
</html>