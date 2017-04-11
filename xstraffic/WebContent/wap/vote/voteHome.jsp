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
<title>活动规则</title>
<link href="${pageContext.request.contextPath}/css/vote/rule.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>


</head>

<body>





<div class="page">
	<img    src="${pageContext.request.contextPath}/viewImages.action?picPath=<s:property value='#request.result["tbVote"].image' />"  class="banner"/>
    <div class="content">
   	  <div class="box">
        	<h1 style="border-left:5px solid #23A9FF;">活动介绍</h1>
            <p>  <s:property value="#request.result['tbVote'].content"/></p>
        </div>
        
        
        <div class="box">
        	<h1 style="border-left:5px solid #FF9A23;">活动主题</h1>
            <p> <s:property value="#request.result['tbVote'].title"/></p>
      </div>
        <div class="box">
        	<h1 style="border-left:5px solid #42D287;">活动时间</h1>
            <p> 
            7月20日9:00到8月10日24:00 
            </p>
        </div>
        <div class="box">
        	<h1 style="border-left:5px solid #E95B5B;">投票规则</h1>
            <p>1.进入象山交警微信公众号并关注。</p>
            <p>2.每个微信号每天只能投一次，一次可投20人。</p>
            <p>3.从45名候选人中选出20人(按票数排名)。</p>
        </div>
        <div class="box">
        	<h1 style="border-left:5px solid #7A5EF9;">颁奖表彰</h1>
            <p>举办电视发布会，为象山“最美驾驶人”颁发荣誉证书和纪念品。</p>
        </div>
    </div>
    <div class="footer">
        <span>象山交警  版权所有</span>
    </div>
</div>


<div class="Focus"

 
 <s:if test="#request.result['subscribe']" > style="display:none;"</s:if><s:else>style="display:block;"</s:else>  
>
    <div class="box">
           <span>长按二维码关注<b>“象山交警”</b></span>
           <div class="orcode">
           		<img src="${pageContext.request.contextPath}/image/vote/qrcode.jpeg" alt="扫描二维码"/>
           </div>
    </div>
</div>

<div class="pop">
	<a class="vote" href="${pageContext.request.contextPath}/wap/voteElectors.action?voteId=<s:property value='#request.result["tbVote"].id' />">
    	<img src="${pageContext.request.contextPath}/image/vote/vote.png"/>
        <span>去投票</span>
    </a>
    <a class="vote" href="${pageContext.request.contextPath}/wap/voteRank.action?voteId=<s:property value='#request.result["tbVote"].id' />">
    	<img src="${pageContext.request.contextPath}/image/vote/list.png"/>
        <span>排行榜</span>
    </a>
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

