<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0" />
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<title>排行榜</title>
<link href="${pageContext.request.contextPath}/css/vote/list.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.2.min.js"></script>
</head>

<body>
<div class="pop">
	<a class="vote" href="${pageContext.request.contextPath}/wap/voteElectors.action?voteId=<s:property value='#request.result["voteId"]' />">
    	<img src="${pageContext.request.contextPath}/image/vote/vote.png"/>
        <span>去投票</span>
    </a>
</div>

<div class="page">
	<s:iterator value="#request.result['electors']" id="tbElector"  status="L">
	<div class="list">
	 <div class="left_part">
    	<h1> <fmt:formatNumber value="${tbElector.number}"  pattern="00"/></h1>
        <div class="head">
                 <input type="hidden" name="picPath" value="<s:property value='img' />" />
                 <img src="${pageContext.request.contextPath}/image/vote/head_male.png"/>
        	
        </div>
        <ul class="info">
        	<li>姓名：<s:property value='name' /></li>
            <span>单位：<s:property value='workUnit' /></span>
        </ul>
	 </div>
        <h2><s:property value='votes' />票</h2>
    </div>
    </s:iterator> 
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
<script type="text/javascript">
    function loadPic(myIndex){

	 var picPath; 
	 $(".page .list:eq("+myIndex+")").each(function(index){

	   
	   picPath=$(this).find("input[name=picPath]").val();
	   $(this).find(".left_part .head img").attr("src","${pageContext.request.contextPath}/viewImages.action?picPath="+picPath);
	      
	   });   
   }
    
    $(function(){
		 var i;
   	 for(i=0;i<45;i++){

			 setTimeout("loadPic("+i+")", 500*i);
		 } 
		 
   	 
   	
   });
 
</script>

 
