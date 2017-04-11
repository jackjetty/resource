<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>浙江号百168充值宝管理系统</title>
<link rel="stylesheet" type="text/css" href="themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="themes/icon.css">
<script type="text/javascript" src="js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="js/menu.js"></script>
<script type="text/javascript">
	var managerRole = '${session.ManagerRole.role}';
	var managerName = '${session.Manager.name}';
	var passWordForm, passWordWin;
	function refresh() {
		var currTab = self.parent.$('#tabs').tabs('getSelected'); // 获得当前tab
		var url = $(currTab.panel('options').content).attr('src');
		self.parent.$('#tabs').tabs('update', {
			tab : currTab,
			options : {
				content : createFrame(url)
			}
		});
	}
	$(function() {
		var tabsId = 'tabs';//tabs页签Id
		var tab_rightmenuId = 'tab_rightmenu';//tabs右键菜单Id
		
		//绑定tabs的右键菜单
		$("#"+tabsId).tabs({
			onContextMenu:function(e,title){//这时去掉 tabsId所在的div的这个属性：class="easyui-tabs"，否则会加载2次
			  e.preventDefault();
			  $('#'+tab_rightmenuId).menu('show',{  
				left: e.pageX,  
				top: e.pageY  
			  }).data("tabTitle",title);
			}
		});
		
		//实例化menu的onClick事件
		$("#"+tab_rightmenuId).menu({
			onClick:function(item){
			  CloseTab(tabsId,tab_rightmenuId,item.name);
			}
		});
		$('#main_modifyForm').dialog({
				title : '修改密码',
				modal : true,
				resizable : true,
				buttons : [{
					text : '保存',
					iconCls : 'icon-save',
					handler : function() {
						var password = $("#oldpwd").val();
						var newPassword = $("#newpwd").val();
						$.ajax({
							url : "modifyPassword.action",
							data : "password=" + password+"&newPassword="+newPassword,
							dataType : "json",
							type : "post",
							success : function(data) {
								if (data.respCode == 0) {
									$.messager.alert('状态管理', '修改成功!', 'info');
									$('#main_modifyForm').dialog('close');
								}else{
									$.messager.alert('状态管理', data.respInfo, 'info');
								}
							},
							error : function(data) {
								alert("error 后台出现错误！");
							}

						});
					}
				},
				{
					text : '取消',
					iconCls : 'icon-cancel',
					handler : function() {
						$('#main_modifyForm').dialog('close');
						}
				}
			]
		}).dialog('close');
		
		$.messager.defaults = {
				ok : "确定",
				cancel : "取消"
			};
		$("#refresh").click(function(){
			location.reload();
		});
		$("#logout").click(function(){
			$.messager.confirm('注意','你确定要退出系统吗?',function(r) {
				if (r) {
					window.location.href = "${pageContext.request.contextPath}/logout.action";
					}
				}
			);
		});
		$("#main").click(function(){
			var url = "${pageContext.request.contextPath}/toMain.action";
			window.location.href = url;
		});
		$("#changepassword").click(function(){
			$('#main_modifyForm').dialog('open');
			$("#oldpwd").val("");
			$("#newpwd").val("");
			$("#rePassword").val("");
			$('#oldpwd').validatebox({
				required : true
			});
		});
	});
	/**
	tab关闭事件
	@param	tabId		tab组件Id
	@param	tabMenuId	tab组件右键菜单Id
	@param	type		tab组件右键菜单div中的name属性值
*/
function CloseTab(tabId,tabMenuId,type){
	//tab组件对象
	var tabs = $('#' + tabId);
	//tab组件右键菜单对象
	var tab_menu = $('#' + tabMenuId);
	
	//获取当前tab的标题
	var curTabTitle = tab_menu.data('tabTitle');
	
	//关闭当前tab
	if(type === 'tab_menu-tabclose'){
		//通过标题关闭tab
		tabs.tabs("close",curTabTitle);
	}
	
	//关闭全部tab
	else if(type === 'tab_menu-tabcloseall'){
		//获取所有关闭的tab对象
		var closeTabsTitle = getAllTabObj(tabs);
		//循环删除要关闭的tab
		$.each(closeTabsTitle,function(){
			var title = this;
			tabs.tabs('close',title);
		});
	}
	
	//关闭其他tab
	else if(type === 'tab_menu-tabcloseother'){
		//获取所有关闭的tab对象
		var closeTabsTitle = getAllTabObj(tabs);
		//循环删除要关闭的tab
		$.each(closeTabsTitle,function(){
			var title = this;
			if(title != curTabTitle){
				tabs.tabs('close',title);
			}
		});
	}
	
	//关闭当前左侧tab
	else if(type === 'tab_menu-tabcloseleft'){
		//获取所有关闭的tab对象
		var closeTabsTitle = getLeftToCurrTabObj(tabs,curTabTitle);
		//循环删除要关闭的tab
		$.each(closeTabsTitle,function(){
			var title = this;
			tabs.tabs('close',title);
		});
	}
	
	//关闭当前右侧tab
	else if(type === 'tab_menu-tabcloseright'){
		//获取所有关闭的tab对象
		var closeTabsTitle = getRightToCurrTabObj(tabs,curTabTitle);
		//循环删除要关闭的tab
		$.each(closeTabsTitle,function(){
			var title = this;
			tabs.tabs('close',title);
		});
	}
}

/**
	获取所有关闭的tab对象
	@param	tabs	tab组件
*/
function getAllTabObj(tabs){
	//存放所有tab标题
	var closeTabsTitle = [];
	//所有所有tab对象
	var allTabs = tabs.tabs('tabs');
	$.each(allTabs,function(){
		var tab = this;
		var opt = tab.panel('options');
		//获取标题
		var title = opt.title;
		//是否可关闭 ture:会显示一个关闭按钮，点击该按钮将关闭选项卡
		var closable = opt.closable;
		if(closable){
			closeTabsTitle.push(title);
		}
	});
	return closeTabsTitle;
}

/**
	获取左侧第一个到当前的tab
	@param	tabs		tab组件
	@param	curTabTitle	到当前的tab
*/
function getLeftToCurrTabObj(tabs,curTabTitle){
	//存放所有tab标题
	var closeTabsTitle = [];
	//所有所有tab对象
	var allTabs = tabs.tabs('tabs');
	for(var i=0;i<allTabs.length;i++){
		var tab = allTabs[i];
		var opt = tab.panel('options');
		//获取标题
		var title = opt.title;
		//是否可关闭 ture:会显示一个关闭按钮，点击该按钮将关闭选项卡
		var closable = opt.closable;
		if(closable){
			//alert('title' + title + '  curTabTitle:' + curTabTitle);
			if(title == curTabTitle){
				return closeTabsTitle;
			}
			closeTabsTitle.push(title);
		}
	}
	return closeTabsTitle;
}

/**
	获取当前到右侧最后一个的tab
	@param	tabs		tab组件
	@param	curTabTitle	到当前的tab
*/
function getRightToCurrTabObj(tabs,curTabTitle){
	//存放所有tab标题
	var closeTabsTitle = [];
	//所有所有tab对象
	var allTabs = tabs.tabs('tabs');
	for(var i=(allTabs.length - 1);i >= 0;i--){
		var tab = allTabs[i];
		var opt = tab.panel('options');
		//获取标题
		var title = opt.title;
		//是否可关闭 ture:会显示一个关闭按钮，点击该按钮将关闭选项卡
		var closable = opt.closable;
		if(closable){
			//alert('title' + title + '  curTabTitle:' + curTabTitle);
			if(title == curTabTitle){
				return closeTabsTitle;
			}
			closeTabsTitle.push(title);
		}
	}
	return closeTabsTitle;
}
	function vidatepassword1() {
		var oldpwd = $("#oldpwd").val();
		var newpwd = $("#newpwd").val();
		if (oldpwd == newpwd) {
			$.messager.alert('系统提示', '新密码不能与原密码相同,请重新输入！', 'warning');
			$("#newpwd").val('');
		}
	}
	function vidatepassword2() {
		var oldpwd = $("#newpwd").val();
		var newpwd = $("#rePassword").val();
		if (oldpwd != newpwd) {
			$.messager.alert('系统提示', '两次密码不匹配,请重新输入！', 'warning');
			$("#rePassword").val('');
		}
	}
	function menuHandler(item){
		alert(item.name);
	}
	
	
	function showTime(){
		var now=new Date();
		$("#newTime").html(now.toLocaleTimeString());
	}
	var timer;
	function startClock(){
		 //timer = window.setInterval(showTime,1000);
		//showTime();
		//window.setInterval("showTime();",1000);
	}

</script>
<style type="text/css">
body {
	font: 12px/20px "微软雅黑", "宋体", Arial, sans-serif, Verdana, Tahoma;
	padding: 0;
	margin: 0;
}

a:link {
	text-decoration: none;
}

a:visited {
	text-decoration: none;
}

a:hover {
	text-decoration: underline;
}

a:active {
	text-decoration: none;
}

.cs-north {
	height: 60px;
	background: #DFE8F6;
}

.cs-north-bg {
	width: 100%;
	height: 100%;
	overflow: hidden;
	background: url(images/main/head_bg1.jpg) repeat-x;
}

.cs-north-logo {
	height: 60px;
	width: 291px;
	display: inline-block;
	color: #000000;
	font-size: 22px;
	font-weight: bold;
	text-decoration: none;
	background: url(images/main/head_bg.jpg) no-repeat;
}

.cs-north-right {
	position:absolute;width:177px;height:51px;float:right;right:7px;top:5px;
}

.newtime {
	font-size:20px;
	color:#003366;
	font-weight:bold;
	text-align:center;
	margin-top:20px;
	width:60%;
	align:center;
	display: inline-block;
	position:absolute;
}

.cs-north-right ul {
	height: 51px;
	margin: 0;
	padding: 0;
	list-style: none;
}

.cs-north-right ul li {
	width: 33px;
	height: 51px;
	padding-top: 33px;
	text-align: center;
	float: left;
	cursor: pointer;
}

.cs-north-right ul li span {
	height: 18px;
	line-height: 18px;
	color: #000;
}

.cs-north-right ul li.home {
	background: url(images/main/home.gif) no-repeat 0 0;
}

.cs-north-right ul li.password {
	background: url(images/main/password.gif) no-repeat 0 0;
	margin-left: 15px;
}

.cs-north-right ul li.refresh {
	background: url(images/main/refresh.gif) no-repeat 0 0;
	margin-left: 15px;
}

.cs-north-right ul li.exit {
	background: url(images/main/exit.gif) no-repeat 0 0;
	margin-left: 15px;
}

.cs-north-right ul li:hover{background-position:-33px 0;}
.cs-north-right ul li:hover span{color:#f98a3a;}

.cs-west {
	width: 200px;
	padding: 0px;
	border-width: 0px 1px 0px !important;
}
.cs-navi-tab {
	padding: 5px;
}

.cs-tab-menu {
	width: 120px;
}

.cs-home-remark {
	padding: 10px;
	width: 800px;
	height: 500px;
}

.wrapper {
	float: right;
	height: 30px;
	margin-left: 10px;
}

.easyui-accordion ul {
	margin: 0;
	padding: 0;
	list-style: none;
	width: 100%;
}

.easyui-accordion ul li {
	width: 100%;
	height: 30px;
	line-height: 30px;
}

.easyui-accordion ul li:hover {
	background: #AEEEEE;
}

.easyui-accordion .panel-body {
	overflow:auto;
}

.panel-body{
overflow: hidden;
}
</style>

</head>
<body class="easyui-layout" id="tt" onload="startClock()">
	<div region="north" border="false" class="cs-north">
		<div class="cs-north-bg">
			<div class="cs-north-logo"></div>
		<div class="newtime"><font id="newTime"></font></div>
			<div class="cs-north-right">
				<ul>
					<li class="home" title="首页" id="main"><span>首页</span></li>
					<li class="password" title="密码" id="changepassword"><span>密码</span></li>
					<li class="refresh" title="刷新" id="refresh"><span>刷新</span></li>
					<li class="exit" title="退出" id="logout"><span>退出</span></li>
				</ul>
			</div>
		</div>
	</div>
	<div id="west" region="west" border="true" split="true" title=" " class="cs-west">
		<div id="accordion" class="easyui-accordion" fit="true" border="false"></div>
	</div>
	<div id="mainPanle" region="center" border="true"
		style="background: #DFE8F6;" >
		<div id="tabs" class="easyui-tabs" fit="true" border="false" >
			<div title="Home">
				<div class="cs-home-remark" align="center"
					style="margin-left: auto; margin-right: auto">
					<img alt="" src="images/main/home_bg.jpg">
				</div>
			</div>
		</div>
	</div>
	<div id="main_modifypwd">
		<form id="main_modifyForm" method="post">
			<table>
				<tr>
					<th>原密码</th>
					<td><input id="oldpwd" name="password"
						class="easyui-validatebox" type="password" /></td>
				</tr>
				<tr>
					<th>新密码</th>
					<td><input id="newpwd" name="newPassword"
						onBlur="vidatepassword1()" class="easyui-validatebox"
						type="password"
						data-options="required:true,missingMessage:'新密码不能为空',validType:'length[6,16]'" /></td>
				</tr>
				<tr>
					<th>确认新密码</th>
					<td><input id="rePassword" name="rePassword"
						onBlur="vidatepassword2()" class="easyui-validatebox"
						type="password"
						data-options="required:true,missingMessage:'新密码不能为空'" /></td>
				</tr>
			</table>
		</form>
	</div>
	
	<div id="tab_rightmenu" class="easyui-menu" style="width:150px;">
		<!-- 	<div name="tab_menu-tabclose">关闭</div> -->
		<div name="tab_menu-tabcloseall">关闭全部标签</div>
		<div name="tab_menu-tabcloseother">关闭其他标签</div>
		<div class="menu-sep"></div>
		<div name="tab_menu-tabcloseright">关闭右侧标签</div>
		<div name="tab_menu-tabcloseleft">关闭左侧标签</div>
	</div>


	<div region="south" border="false" id="south"
		style="background: #DFE8F6;">
		
	</div>
</body>
</html>