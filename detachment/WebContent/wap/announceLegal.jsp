<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<title>绍兴交警网上自助缴费</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0" />
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/js/tencent/css/style.css" />
<link href="${pageContext.request.contextPath}/css/legal/page1.css"
	type="text/css" rel="stylesheet">
<style>
.content {
	padding-left: 10px;
	padding-right: 10px;
	margin-top: 55px;
	margin-bottom: 155px;
}

.title {
	margin: 0;
	line-height: 40px;
	text-align: center;
	font-family: “宋体”;
	font-size: 14px;
}

p {
	margin: 0 auto;
	padding: 0;
	font-family: “宋体”;
	font-size: 12px;
	line-height: 20px;
}

h4 {
	margin: 0;
	padding: 0;
	font-family: “宋体”;
	font-size: 12px;
	line-height: 30px;
}

.footer {
	padding-left: 10px;
	padding-right: 10px;
	margin-top: 10px;
	background-color: #f0efef;
	position: fixed;
	bottom: 0px;
	_position: absolute;
	_bottom: expression_r(documentElement.scrollTop +   "px");
}

.note {
	padding-bottom: 5px;
	padding-top: 5px;
}
</style>
</head>

<body>
	<div class="header">
		<div class="top">
			<h2>处理公告</h2>
		</div>
		 
	</div>
	<div class="content">
		<h5 class="title">关于网上自助处理交通违法行为有关规则的告知</h5>
		<h4>一、网上处理范围：</h4>
		<p style="text-indent: 2em;">
			1、绍兴市公安局交警支队管辖范围内的小型机动车与摩托车（重点监管车辆、营运车辆除外）被交通技术监控记录（高速公路违章除外），依法应处200元以下（含200元）罚款的违法行为。</p>
		<p style="text-indent: 2em;">2、网上违法处理适用于机动车所有人本人在线处理。</p>
		<p style="text-indent: 2em;">3、网上处理并缴款后，根据银行提供的信息，我们将在30分钟内对您的信息进行核销。如果10小时之内未进行缴款，登录后需重新进行处理。</p>

		<h4>二、注意事项：</h4>
		<p style="text-indent: 2em;">1、试运行期间，网上自助罚缴仅支持银联卡</p>
		<p style="text-indent: 2em;">2、网上可进行自助处理的车辆仅包括绍兴市内上牌的非营运小型车。</p>

		<h4>三、温馨提示：</h4>
		<p style="text-indent: 2em;">
			1、在交通违法网上自助处理平台上处理车辆电子警察违法行为时，您看不到电子警察记录的照片，如果您对该违法行为有异议，建议您到违法行为地交警部门接受处理。</p>

		<h4>四、重要原则和法律责任声明：</h4>
		<p style="text-indent: 2em;">
			交通违法互联网自助罚缴平台系公安机关交通管理部门为方便机动车法定所有人、管理人、驾驶人处理交通违法行为设计开发的，仅供机动车所有人、管理人、驾驶人本人或经本人授权的委托代理人按规定程序登录使用。违反上述原则进入平台操作处理的行为，均属非法行为，将由公安机关按照国家法律法规追究其责任，构成犯罪的，依法追究刑事责任！</p>
	</div>

	<div class="footer">
		<div class="note">
			<p style="color: red;">如有异议，请与交警支队联系。</p>
			<p>以上内容本人已认真阅读，并已充分了解，愿意按照上述规则进行网上自助处理交通违法行为。</p>
			<p>
				是否需要网上处理:<input type="checkbox"  checked />是
			</p>
		</div> 
		<div class="button">
		 
			<a href="${pageContext.request.contextPath}/wap/vehiclePageLegal.action">下一步</a>
		</div>
	</div>
</body>
</html>

