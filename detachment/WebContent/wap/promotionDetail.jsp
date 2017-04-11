<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<title >活动详情</title>
<meta name="viewport" content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<link href="../web/css/paxy.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="../web/css/information.css"/>
<script src="../web/js/jquery.js" type="text/javascript"></script>  
<style> 
.redfont{
		color: red;
	}
.liTitle{
	background-color: #5DD300;
    background-image: -moz-linear-gradient(center bottom , #5DD300 0%, #5DD300 100%);
    margin-bottom: 4px;
}
.xk{
color:#FFFFFF;font-size:18px;
}
#titleDiv{
    background-color: #179F00;
    background-image: -moz-linear-gradient(center bottom , #179F00 0%, #5DD300 100%);
    
    box-shadow: 0 0px 0 #94E700 inset, 0 1px 2px rgba(0, 0, 0, 0.5);
    color: #FFFFFF;
    width:100%;
    line-height:40px;
    margin:0 auto;
    text-align:center;
}
.nav-back {
    display: inline-block;
    height: 26px;
    padding: 7px 0px 7px 6px;
    color: rgb(255, 255, 255);
    position: absolute;
    top: 0px;
    left: 0px;
    overflow: hidden;
}
a {
    color: rgb(59, 89, 152);
    outline: medium none;
    text-decoration: none;
}
.nav-back .arrow-left {
    float: left;
    width: 12px;
    height: 26px;
    background-position: 50% 50%; 
}
.icon-arrow {
    background: url("../web/image/button/bg_arrow.png")  no-repeat;
	background-size:80px auto;  
}

.nav-back span {
    float: left;
    padding: 5px 5px 5px 2px;
    background: none repeat scroll 0% 0%  #8BE553;
    font-size: 14px;
    line-height: 16px;
}

</style>
<body id="img-content" style="width:100%;background:#ffffff">
<div style="width:100%;" >
	<div id="titleDiv" >
	<a class="nav-back" onclick="javascript:history.go(-1);"><i class="icon-arrow arrow-left"> </i><span>
                            返回
          </span>
          </a>
		<span style="font-size:18px;color:#FFFFFF;">推广有礼，好礼大放送啦！</span>
	</div>
	<div class="cardexplain" id="content">
	   <ul class="round">
				<li class="title mb"><span class="none">活动简介</span></li>
				<li class="nob"> 
					<table width="100%" border="0" cellspacing="0" cellpadding="0"
						class="kuang">
						<tbody>
							<tr>
								<th>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp绍兴交警官方微信平台自运营以来，其强大的功能（查询车辆违法行为，出行攻略及道路交通安全知识，交通事故微信快处理，自助移车，满12分学习，出行攻略及知识等）让小伙伴们惊叹不已，虽然我们自己憋足了劲，但毛爷爷说了，人多力量大，还是好多小伙伴无法知晓这一居家旅行必备法宝。所以呢，我们官方微信将组织第一次活动——推广有礼。<br>
								活动时间：8月8日至8月28日<br>
								<h4>活动一：卖力的汗水，丰厚的礼品</h4> 
								&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp微友通过“警民互动”——“我的推广”获得各自推广的二维码，一位好友通过扫你的推广二维码关注“绍兴交警官方微信”，便算成功推广一次，至于如何让朋友关注，嘿嘿，那就要看你们各显神通了。我们将会根据后台统计的数据，在活动结束后，最终确定推广人数最多的三位微友。如此辛苦的推广，当然有丰厚的奖品，奖品就是行车记录仪，是不是很心动啊，心动的话还不赶快行动。
								<h4>活动二：推广一人，就可能有礼品</h4> 
								&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp微友通过“我的推广”获得各自推广的二维码，只要成功推荐一位好友通过扫你的推广二维码关注“绍兴交警官方微信”，就有可能获得礼品。推广活动结束后，我们将在成功介绍一人的微友中随机选取10名幸运儿，奖品为可爱的杯子与鼠标垫。</th> 
							</tr>
						</tbody>
					</table>
				  </li>
		</ul>
		
		<div style="" onclick="document.getElementById('mcover').style.display='';" id="mcover">
                <img src="../web/image/guide.png">
            </div>
            <div style="margin-top:15px;">
              <div id="mess_share">
                  <div id="share_1">
                      <button onclick="document.getElementById('mcover').style.display='block';" class="button2">
                          <img width="64" height="64" src="../web/image/icon_msg.png">
                          &nbsp;发送给朋友
                      </button>
                  </div>
                  <div id="share_2">
                      <button onclick="document.getElementById('mcover').style.display='block';" class="button2">
                          <img width="64" height="64" src="../web/image/icon_timeline.png">
                          &nbsp;分享到朋友圈
                      </button>
                  </div>
                  
                  <div style="height:5px;"></div>
              </div>
            </div>
        </div>
    </div>
		 
	</div>