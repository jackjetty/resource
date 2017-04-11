<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<title>微调查</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0" />
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<script src="${pageContext.request.contextPath}/js/jquery-1.7.2.js"></script>
<script src="${pageContext.request.contextPath}/js/locallib.js"></script>
<script src="${pageContext.request.contextPath}/js/msgbox.js"></script>
<%-- <script src="${pageContext.request.contextPath}/js/lhgdialog/lhgdialog.min.js?skin=iblue" type="text/javascript"></script>  --%>
<style>
body{ margin:0; padding:0; background-color:#f1f0f0;}
a{ text-decoration:none; display:block;}
.header{ height:40px; background-color:#03cf03; width:100%; position:relative;position:fixed;
		top:0px;
		_position: absolute;
		_top: expression_r(documentElement.scrollTop + "px");}
.header h2{ margin:0;color:#ffffff; font-size:18px;line-height:40px; text-align:center; font-weight:bold;font-family:“微软雅黑”; }
.content{ width:96%; margin:50px 2%;}
.content h5{ font-family:“微软雅黑”; color:#02a3ff; font-size:16px; margin-top:15px; margin-bottom:0;}
.content p{ font-family:“黑体”; font-size:17px; margin-top:10px; margin-bottom:5px;}
select{ width:85%; height:25px;border:1px #d3d3d3 solid;border-radius:3px; -moz-border-radius:3px; -webkit-border-radius:3px; margin-left:3em;}
.IMG1{float:left; width:18px; height:18px;  margin-top:2px; margin-left:2em; }

.word{font-size:15px;line-height:22px;color:grey; 
margin-left:5px;font-family:“黑体”; float:left;}
.password{ float:left;width:50%; overflow:hidden;}
.password1{ float:left;width:100%; overflow:hidden;}
.img{ width:96%; margin:2% auto;}
.img img{ width:100%;}
.button{ width:96%; margin:0 auto; background-color:#00e700; height:35px; line-height:35px; text-align:center; color:#ffffff;border:1px #00e700 solid;border-radius:3px; -moz-border-radius:3px; -webkit-border-radius:3px; margin-top:50px;}
</style>
</head>
<body>
<div class="header">
	<h2>微调查</h2>
	<a style='display:none;' href="http://www.baidu.com" onmousedown="mouseDown()" onmouseup="mouseUp()"><img id="tu" src="${pageContext.request.contextPath}/image/investigate/tu.png" style=" position:absolute; right: 10px; top: 5px;"></a>
</div>
<div class="content">
	<h5>一、个人信息</h5>
	<p style="text-indent: 2em;">1、选择子女就读学校</p>
	<s:select id="school" list="schoolList" headerKey="" headerValue="--请选择--"></s:select>
	
	<p style="text-indent: 2em;">2、问卷人性别</p>
	<div style="overflow: hidden;">
		<div class="password">
			<img src="${pageContext.request.contextPath}/image/investigate/on_ago.png" name="gender" class="IMG1" alt="1"/>
			<span class="word">男</span>
		</div>
		<div class="password">
			<img src="${pageContext.request.contextPath}/image/investigate/on_ago.png" name="gender" class="IMG1" alt="0"/>
			<span class="word">女</span>
		</div>
	</div>
	
	<p style="text-indent: 2em;">3、家长年龄段：</p>
	<div style="overflow: hidden;">
		<div class="password">
			<img src="${pageContext.request.contextPath}/image/investigate/on_ago.png" name="ageGroup" class="IMG1" alt="lower20"/>
			<span class="word">20岁以下</span>
		</div>
		<div class="password">
			<img src="${pageContext.request.contextPath}/image/investigate/on_ago.png" name="ageGroup" class="IMG1" alt="20to30"/>
			<span class="word">20岁至30岁</span>
		</div>
		<div class="password">
			<img src="${pageContext.request.contextPath}/image/investigate/on_ago.png" name="ageGroup" class="IMG1" alt="30to40"/>
			<span class="word">30岁至40岁</span>
		</div>
		<div class="password">
			<img src="${pageContext.request.contextPath}/image/investigate/on_ago.png" name="ageGroup" class="IMG1" alt="40to50"/>
			<span class="word">40岁至50岁</span>
		</div>
		<div class="password">
			<img src="${pageContext.request.contextPath}/image/investigate/on_ago.png" name="ageGroup" class="IMG1" alt="upper50"/>
			<span class="word">50岁以上</span>
		</div>
	</div>
	
	<p style="text-indent: 2em;">4、是否有机动车驾驶证：</p>
	<div style="overflow: hidden;">
		<div class="password">
			<img src="${pageContext.request.contextPath}/image/investigate/on_ago.png" name="hasDriverLicense" class="IMG1"  alt="1"/>
			<span class="word">有</span>
		</div>
		<div class="password">
			<img src="${pageContext.request.contextPath}/image/investigate/on_ago.png" name="hasDriverLicense" class="IMG1"  alt="0"/>
			<span class="word">无</span>
		</div>
	</div>
	
	<p style="text-indent: 2em;">5、自身是否接送过孩子上下学：</p>
	<div style="overflow: hidden;">
		<div class="password">
			<img src="${pageContext.request.contextPath}/image/investigate/on_ago.png" name="everDelieverChildren" class="IMG1"  alt="1"/>
			<span class="word">有</span>
		</div>
		<div class="password">
			<img src="${pageContext.request.contextPath}/image/investigate/on_ago.png" name="everDelieverChildren" class="IMG1"  alt="0"/>
			<span class="word">无</span>
		</div>
	</div>

	<p style="text-indent: 2em;">6、家里主要是谁接送孩子：</p>
	<div style="overflow: hidden;">
		<div class="password">
			<img src="${pageContext.request.contextPath}/image/investigate/on_ago.png" name="mainDelieverChildren" class="IMG1"  alt="1"/>
			<span class="word">自己</span>
		</div>
		<div class="password">
			<img src="${pageContext.request.contextPath}/image/investigate/on_ago.png" name="mainDelieverChildren" class="IMG1"  alt="2"/>
			<span class="word">配偶</span>
		</div>
		<div class="password">
			<img src="${pageContext.request.contextPath}/image/investigate/on_ago.png" name="mainDelieverChildren" class="IMG1"  alt="3"/>
			<span class="word">父母</span>
		</div>
		<div class="password">
			<img src="${pageContext.request.contextPath}/image/investigate/on_ago.png" name="mainDelieverChildren" class="IMG1"  alt="4"/>
			<span class="word">孩子自己上学</span>
		</div>
		<div class="password">
			<img src="${pageContext.request.contextPath}/image/investigate/on_ago.png" name="mainDelieverChildren" class="IMG1"  alt="5"/>
			<span class="word">其他</span>
		</div>
	</div>

	<p style="text-indent: 2em;">7、家里接送孩子的方式：</p>
	<div style="overflow: hidden;">
		<div class="password">
			<img src="${pageContext.request.contextPath}/image/investigate/on_ago.png" name="delieverChildrenType" class="IMG1"  alt="1"/>
			<span class="word">轿车</span>
		</div>
		<div class="password">
			<img src="${pageContext.request.contextPath}/image/investigate/on_ago.png" name="delieverChildrenType" class="IMG1"  alt="2"/>
			<span class="word">电瓶车、自行车</span>
		</div>
		<div class="password">
			<img src="${pageContext.request.contextPath}/image/investigate/on_ago.png" name="delieverChildrenType" class="IMG1"  alt="3"/>
			<span class="word">公交车</span>
		</div>
		<div class="password">
			<img src="${pageContext.request.contextPath}/image/investigate/on_ago.png" name="delieverChildrenType" class="IMG1"  alt="4"/>
			<span class="word">步行</span>
		</div>
		<div class="password">
			<img src="${pageContext.request.contextPath}/image/investigate/on_ago.png" name="delieverChildrenType" class="IMG1"  alt="5"/>
			<span class="word">孩子自己上学</span>
		</div>
	</div>

	<p style="text-indent: 2em;">8、放心孩子自己上下学吗？</p>
	<div style="overflow: hidden;">
		<div class="password">
			<img src="${pageContext.request.contextPath}/image/investigate/on_ago.png" name="childrenAlone" class="IMG1"  alt="0"/>
			<span class="word">放心</span>
		</div>
		<div class="password">
			<img src="${pageContext.request.contextPath}/image/investigate/on_ago.png" name="childrenAlone" class="IMG1"  alt="1"/>
			<span class="word">不放心</span>
		</div>
	</div>

	<p style="text-indent: 2em;">9、学校周边上下学期间的交通秩序：</p>
	<div style="overflow: hidden;">
		<div class="password">
			<img src="${pageContext.request.contextPath}/image/investigate/on_ago.png" name="schoolTrafficOrder" class="IMG1"  alt="1"/>
			<span class="word">好</span>
		</div>
		<div class="password">
			<img src="${pageContext.request.contextPath}/image/investigate/on_ago.png" name="schoolTrafficOrder" class="IMG1"  alt="2"/>
			<span class="word">一般</span>
		</div>
		<div class="password">
			<img src="${pageContext.request.contextPath}/image/investigate/on_ago.png" name="schoolTrafficOrder" class="IMG1"  alt="3"/>
			<span class="word">不好</span>
		</div>
	</div>

	<p style="text-indent: 2em;">10、学校周边上下学期间最突出的交通违法问题：</p>
	<div style="overflow: hidden;">
		<div class="password1">
			<img src="${pageContext.request.contextPath}/image/investigate/on_ago.png" name="trafficIllegalProblem" class="IMG1"  alt="2"/>
			<span style="width: 60%" class="word">机动车未礼让斑马线上的行人</span>
		</div>
		<div class="password1">
			<img src="${pageContext.request.contextPath}/image/investigate/on_ago.png" name="trafficIllegalProblem" class="IMG1"  alt="3"/>
			<span style="width: 60%" class="word">非机动车逆向行驶</span>
		</div>
		<div class="password">
			<img src="${pageContext.request.contextPath}/image/investigate/on_ago.png" name="trafficIllegalProblem" class="IMG1"  alt="1"/>
			<span class="word">违法停车</span>
		</div>
		<div class="password">
			<img src="${pageContext.request.contextPath}/image/investigate/on_ago.png" name="trafficIllegalProblem" class="IMG1"  alt="4"/>
			<span class="word">其他</span>
		</div>
	</div>
	<div id="submitAnswer" class="button">交通安全知识题</div>
</div>
<form id="form1" method="post">
	<input  type="hidden" id="schoolId" name="baseInfo.schoolId">
	<input  type="hidden" id="gender" name="baseInfo.gender">
	<input type="hidden" id="ageGroup" name="baseInfo.ageGroup">
	<input type="hidden" id="hasDriverLicense" name="baseInfo.hasDriverLicense">
	<input type="hidden" id="everDelieverChildren" name="baseInfo.everDelieverChildren">
	<input type="hidden" id="mainDelieverChildren" name="baseInfo.mainDelieverChildren">
	<input type="hidden" id="delieverChildrenType" name="baseInfo.delieverChildrenType">
	<input type="hidden" id="childrenAlone" name="baseInfo.childrenAlone">
	<input type="hidden" id="schoolTrafficOrder" name="baseInfo.schoolTrafficOrder">
	<input type="hidden" id="trafficIllegalProblem" name="baseInfo.trafficIllegalProblem">
</form>
<script>
	var openId = '<%=request.getParameter("openId")%>';
	
	$(function() {
		var baseInfo = new Object();
		
		// 点击每个选项的前面的框框 就对应的点击该选项
		$(".word").click(function() {
			$(this).prev().click();
		});
		
		// 点击每个选项时 将每个选项的值赋给baseInfo对应的属性
		$(".IMG1").click(function() {
			$("[name=" + $(this).attr("name") + "]").each(function() {
				$(this).next().css("color", "grey"); // 所有字体变灰
				$(this).attr("src", "${pageContext.request.contextPath}/image/investigate/on_ago.png");
			});
			$(this).next().css("color", "black"); // 选中字体变黑
			$(this).attr("src", "${pageContext.request.contextPath}/image/investigate/on_letter.png");
			baseInfo[$(this).attr("name")] = $(this).attr("alt");
		});
		
		$("#school").change(function() {
			baseInfo["schoolId"] = $("#school").val();
		});
		
		$("#submitAnswer").click(function() {
			if (openId == "null") {
				$.dialog.alert("温馨提示", "请登陆微信在填写本表单");
				//ZENG.msgbox.show('请登陆微信在填写本表单', 1, 3000);
				//getLocal().alert({ text : '请登陆微信在填写本表单'});
				/* $.dialog.alert("请登陆微信在填写本表单", function() {
					
				}); */
				return false;
			}
			
			var s = 0;
			
			for (var i in baseInfo) {
				s++;
				$("#" + i).val(baseInfo[i]);
			}
			
			if (s != 10 || baseInfo["schoolId"] == "") {
				$.dialog.alert("温馨提示", "请填写完整");
				//ZENG.msgbox.show('请填写完整', 1, 3000);
				//getLocal().alert({ text : '请填写完整'});
				/* $.dialog.alert("请填写完整", function() {
					
				}); */
				return false;
			}
			
			$("#form1").attr("action", "${pageContext.request.contextPath}/web/doExamine2.action?openId=" + openId);
			
			$("#form1").submit();
		});
	});
	
	function mouseDown() {
		$("#tu").attr("src", "../image/investigate/tu1.png");
	}

	function mouseUp() {
		$("#tu").attr("src", "../image/investigate/tu.png");
	}
</script>
</body>
</html>