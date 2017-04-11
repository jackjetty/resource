<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0" />
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<title>简介</title>
<link href="${pageContext.request.contextPath}/css/vote/Introduce.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
</head>

<body>


<div class="page">
	<h1>姓名：<s:property value='#request.result["elector"].name' /></h1>
    <h2>单位：<s:property value='#request.result["elector"].workUnit' /></h2>
	<div class="Introduce" style="display:none">
    	<h3>参评情况简介</h3>
    	<p><s:property value='#request.result["elector"].introduce' />
    </div>
   <div class="Introduce">
    	<h3>参评事迹</h3>
    	<p><s:property value='#request.result["elector"].achievement' /></p>
    </div>
     

</div>
</body>
</html>

<script type="text/javascript">


//

wx.config({
    debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
    appId: "<s:property value='#request.result["WxAppId"]' />", // 必填，公众号的唯一标识
    timestamp:"<s:property value='#request.result["WxTimestamp"]' />", // 必填，生成签名的时间戳
    nonceStr: "<s:property value='#request.result["WxNoncestr"]' />", // 必填，生成签名的随机串
    signature: "<s:property value='#request.result["WxSignature"]' />",// 必填，签名，见附录1
    jsApiList: ["onMenuShareTimeline","onMenuShareAppMessage","onMenuShareAppMessage","onMenuShareQQ","onMenuShareWeibo","onMenuShareQZone"]
});


wx.ready(function(){
	
	wx.onMenuShareTimeline({
	    title: '文明象山.最美驾驶人', 
	    link: 'https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxadab35d48d9e4266&redirect_uri=http%3a%2f%2fwww.weiximen.com%2fxstraffic%2fwap%2fvoteHome.action%3fvoteId%3dbd2389ac6d3f4c14b30d9b56fbbe3f8b&response_type=code&scope=snsapi_base&state=1#wechat_redirect', // 分享链接
	    imgUrl: "http://www.weiximen.com/xstraffic/image/vote/360x200.png", // 分享图标
	    success: function () { 
	        // 用户确认分享后执行的回调函数
	    },
	    cancel: function () { 
	        // 用户取消分享后执行的回调函数
	    }
	});
	
	wx.onMenuShareAppMessage({
	    title: '文明象山.最美驾驶人', // 分享标题
	    desc: '文明象山.最美驾驶人', // 分享描述
	    link:'https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxadab35d48d9e4266&redirect_uri=http%3a%2f%2fwww.weiximen.com%2fxstraffic%2fwap%2fvoteHome.action%3fvoteId%3dbd2389ac6d3f4c14b30d9b56fbbe3f8b&response_type=code&scope=snsapi_base&state=1#wechat_redirect',
	    imgUrl: "http://www.weiximen.com/xstraffic/image/vote/360x200.png", // 分享图标
	    type: 'link', // 分享类型,music、video或link，不填默认为link
	    dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
	    success: function () { 
	        // 用户确认分享后执行的回调函数
	    },
	    cancel: function () { 
	        // 用户取消分享后执行的回调函数
	    }
	});
	
	wx.onMenuShareQQ({
		title: '文明象山.最美驾驶人', // 分享标题
	    desc: '文明象山.最美驾驶人', // 分享描述
	    link:'https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxadab35d48d9e4266&redirect_uri=http%3a%2f%2fwww.weiximen.com%2fxstraffic%2fwap%2fvoteHome.action%3fvoteId%3dbd2389ac6d3f4c14b30d9b56fbbe3f8b&response_type=code&scope=snsapi_base&state=1#wechat_redirect',
	    imgUrl: "http://www.weiximen.com/xstraffic/image/vote/360x200.png", // 分享图标
	    success: function () { 
	       // 用户确认分享后执行的回调函数
	    },
	    cancel: function () { 
	       // 用户取消分享后执行的回调函数
	    }
	});
	
	
	wx.onMenuShareWeibo({
		title: '文明象山.最美驾驶人', // 分享标题
	    desc: '文明象山.最美驾驶人', // 分享描述
	    link:'https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxadab35d48d9e4266&redirect_uri=http%3a%2f%2fwww.weiximen.com%2fxstraffic%2fwap%2fvoteHome.action%3fvoteId%3dbd2389ac6d3f4c14b30d9b56fbbe3f8b&response_type=code&scope=snsapi_base&state=1#wechat_redirect',
	    imgUrl: "http://www.weiximen.com/xstraffic/image/vote/360x200.png", // 分享图标
	    success: function () { 
	       // 用户确认分享后执行的回调函数
	    },
	    cancel: function () { 
	        // 用户取消分享后执行的回调函数
	    }
	});
	
	wx.onMenuShareQZone({
		title: '文明象山.最美驾驶人', // 分享标题
	    desc: '文明象山.最美驾驶人', // 分享描述
	    link:'https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxadab35d48d9e4266&redirect_uri=http%3a%2f%2fwww.weiximen.com%2fxstraffic%2fwap%2fvoteHome.action%3fvoteId%3dbd2389ac6d3f4c14b30d9b56fbbe3f8b&response_type=code&scope=snsapi_base&state=1#wechat_redirect',
	    imgUrl: 'http://www.weiximen.com/xstraffic/image/vote/360x200.png', // 分享图标
	    success: function () { 
	       // 用户确认分享后执行的回调函数
	    },
	    cancel: function () { 
	        // 用户取消分享后执行的回调函数
	    }
	});
	
    
});
wx.error(function(res){
    // config信息验证失败会执行error函数，如签名过期导致验证失败，具体错误信息可以打开config的debug模式查看，也可以在返回的res参数中查看，对于SPA可以在这里更新签名。
});
</script>


