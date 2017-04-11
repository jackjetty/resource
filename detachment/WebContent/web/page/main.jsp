<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>绍兴交警微信管理平台</title>
<link rel="stylesheet" type="text/css" href="themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="themes/icon.css">
<script type="text/javascript" src="js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="js/audiojs/audio.min.js"></script>
<script type="text/javascript" src="js/menu.js"></script>
<script type="text/javascript">
	var lastListSize = 'default';
	var lastListSizeCJ='default';
	var lastListSize2 = 0;
	var lastListSizeCJ2=0;
	var close = true;
	var managerRole = '${session.UserRole.role}';
	var managerName = '${session.User.userId}';
	var passWordForm, passWordWin;
	function refresh() {
		var currTab = self.parent.$('#tabs').tabs('getSelected'); // 获得当前tab
		var url = $(currTab.panel('options').content).attr('src');
		if (url == null) {
			self.parent.$('#tabs').tabs('update', {
				tab : currTab
			});
		} else {
			self.parent.$('#tabs').tabs('update', {
				tab : currTab,
				options : {
					content : createFrame(url)
				}
			});
		}
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
							url : "web/modifyPassword.action",
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
								//alert("error 后台出现错误！");
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
			refresh();
		});
		$("#logout").click(function(){
			$.messager.confirm('注意','你确定要退出系统吗?',function(r) {
				if (r) {
					window.location.href = "${pageContext.request.contextPath}/web/logout.action";
					}
				}
			);
		});
		$("#main").click(function(){
			var url = "${pageContext.request.contextPath}/web/toMain.action";
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
		getTbTipNumber();
		window.setInterval("getTbTipNumber();", 20000);
		getTbNumber();
		window.setInterval("getTbNumber();", 5000);
	}
	
	function getTbNumber() {
		$("#voiceInfo").empty();
		$.ajax({
			url : "getAccidentNumber.action",
			async : false,
			data : "",
			dataType : "json",
			type : "post",
			success : function(data) {
				var listSize = data.listSize;
				var listSizeCJ=data.listSizeCJ;
				if(lastListSizeCJ=='default'){
					lastListSizeCJ=listSizeCJ;
				}else if(listSizeCJ>lastListSizeCJ){
					lastListSizeCJ2 = listSizeCJ;
					$("#chujing_dialog").dialog("open");
					var audi = "<audio id=\"info\"autoplay=\"autoplay\" ><source src=\"mp3/2.mp3\" type=\"audio/mpeg\"></audio>";
					$("#voiceInfo").append(audi);
					audiojs.events.ready(function() {
						audiojs.createAll();
					});
					return false;
				}else{
					lastListSizeCJ = listSizeCJ;
				}
				
				if(lastListSize =='default'){
					lastListSize = listSize;
				}else if(listSize > lastListSize ){
					lastListSize2 = listSize;
					$("#remind_dialog").dialog("open");
					var audi = "<audio id=\"info\"autoplay=\"autoplay\" ><source src=\"mp3/1.mp3\" type=\"audio/mpeg\"></audio>";
					$("#voiceInfo").append(audi);
					audiojs.events.ready(function() {
						audiojs.createAll();
					});
				}else{
					lastListSize = listSize;
				}
			},
			error : function(data) {
				//alert("与服务端连接失败！");
			}
		});
	}
	
	function closeMp3(){
		lastListSize = lastListSize2;
		$("#remind_dialog").dialog("close");
		addTab("交通事故查询","doAccident.action");
	}
	function closeChuJingMp3(){
		lastListSizeCJ = lastListSizeCJ2;
		$("#chujing_dialog").dialog("close");
		addTab("交通事故查询","doAccident.action");
	}
	
	
	
	function getTbTipNumber() {
		$.ajax({
			url : "getTbTipRecordNum.action",
			async : false,
			data : "",
			dataType : "json",
			type : "post",
			success : function(data) {
				/*var num=data.num;
				if(num!=0){
					var textContent="<a href='javascript:tbTipRecordNum()' id='testdiv'><marquee id='testDiv' width='100%' onmouseover='this.stop()' onmouseout='this.start()' scrollamount='5' behavior='scroll'>有"+num+"条事故记录已重新上报,请点击查看;</marquee></a>";
					$(".newtime").html(textContent);
				}*/
				if(data.code==0){
					var recordNo=data.recordNo;
					$("#tipName").html("有事故编号为:"+recordNo+"的事故,重新上报");
					$("#tip_dialog").dialog("open");
				}
				
				
			},
			error : function(data) {
				//alert("与服务端连接失败！");
			}
		});
	}
	/*function tbTipRecordNum(){
		$.ajax({
			url : "updateTbTipRecordNum.action",
			async : false,
			data : "",
			dataType : "json",
			type : "post",
			success : function(data) {
				var codeNum=data.code;
				if(codeNum==1){
					addTab("交通事故查询","doAccident.action");
					$(".newtime").html('');
				}else{
					alert("网络繁忙!请刷新重试!");
				}
			},
			error : function(data) {
				//alert("与服务端连接失败！");
			}
		});
		
	}*/
	
	function closeTip(){
		$("#tip_dialog").dialog("close");
		addTab("交通事故查询","doAccident.action");
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
	background: url(image/main/header3.jpg) repeat-x;
}



.cs-north-right {
	position:absolute;width:177px;height:51px;float:right;right:7px;top:5px;
}

.newtime {
	font-family:"宋体";
	font-size:13px;
	color:#003366;
	font-weight:bold;
	text-align:center;
	margin-top:20px;
	width:20%;
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
	background: url(image/main/home.png) no-repeat 0 0;
}

.cs-north-right ul li.password {
	background: url(image/main/password.png) no-repeat 0 0;
	margin-left: 15px;
}

.cs-north-right ul li.refresh {
	background: url(image/main/refresh.png) no-repeat 0 0;
	margin-left: 15px;
}

.cs-north-right ul li.exit {
	background: url(image/main/exit.png) no-repeat 0 0;
	margin-left: 15px;
}

.cs-north-right ul li:hover{background-position:-33px 0;}
.cs-north-right ul li:hover span{color:#f98a3a;}

.cs-west {
	width: 200px;
	padding: 0px;
	
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
	cursor:pointer;
}

.easyui-accordion .panel-body {
	overflow:auto;
}

.panel-body{
overflow: hidden;
}
.cs-north-logo {
	height: 60px;
	width: 25%;
	display: inline-block;
	color: #000000;
	font-size: 22px;
	font-weight: bold;
	text-decoration: none;
	margin-left: 180px;
	margin-top: 20px;
}
</style>

</head>
<body class="easyui-layout" id="tt" onload="startClock()">
	<div id="voiceInfo"></div>
	<div id="voiceInfo1"></div>
	<div region="north" border="false" class="cs-north">
		<div class="cs-north-bg">
			<div class="cs-north-logo">绍兴交警微信管理平台</div>
		<div class="newtime" >
		
		
		
		</div>
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
	<div id="west" region="west" split="true" title=" " class="cs-west">
		<div id="accordion" class="easyui-accordion" fit="true" border="false"></div>
	</div>
	<div id="mainPanle" region="center" border="true"
		style="background: #DFE8F6;" >
		<div id="tabs" class="easyui-tabs" fit="true" border="false" >
			<div title="主页">
				<div class="cs-home-remark" align="center"
					style="margin-left: auto; margin-right: auto">
					 <img alt="" src="image/main/zhuye.jpg" style="width: 500px; height: 500px;"> 
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
		<center>Copyright © 杭州冉思科技有限公司</center>
	</div>
	<div id="remind_dialog" class="easyui-dialog" title="新事故提醒" style="width: 400px;height: 200px;" closed="true" closable="false">
		<div align="center">
			<table style="margin-top: 40px;">
				<tr style="font-size: 16px;"><td height="20px;">有新事故产生，请注意查看处理！</td></tr>
			</table>
		</div>
		<div align="center" style="margin-top: 20px;"><input type="button" value="确定" onclick="closeMp3()" style="font-size:20px;width:100px;height:50px;"/> </div>
	</div> 
	
	<div id="chujing_dialog" class="easyui-dialog" title="出警提醒" style="width: 400px;height: 200px;" closed="true" closable="false">
		<img alt="" src="image/jingbao1.gif" style="width:50px;height:50px;float:left;">
		<img alt="" src="image/jingbao1.gif" style="width:50px;height:50px;float:right;">
		<div align="center">
			<table style="margin-top: 20px;">
				<tr style="font-size: 16px;"><td height="20px;" style="border: 0px solid red;" align="center"></td></tr>
				<tr style="font-size: 16px;"><td height="20px;" style="border: 0px solid red;">有事故需出警，请注意查看处理！</td></tr>
			</table>
		</div>
		<div align="center" style="margin-top: 20px;"><input type="button" value="确定" onclick="closeChuJingMp3()" style="font-size:20px;width:100px;height:50px;"/> </div>
	</div> 
	
	
	
	<div id="tip_dialog" class="easyui-dialog" title="记录重新上报提醒" style="width: 400px;height: 200px;" closed="true" closable="false">
		<div align="center">
			<table style="margin-top: 40px;">
				<tr style="font-size: 16px;"><td height="20px;" id="tipName"></td></tr>
			</table>
		</div>
		<div align="center" style="margin-top: 20px;"><input type="button" value="确定" onclick="closeTip()" style="font-size:20px;width:100px;height:50px;"/> </div>
	</div> 
	
</body>
</html>