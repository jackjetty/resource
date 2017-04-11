<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
<title>详情</title>
<link rel="stylesheet" type="text/css" href="css/style.css"/>
<link rel="stylesheet" type="text/css" href="css/information.css"/>
<link rel="stylesheet" type="text/css" href="css/toTop.css"/>
<script type="text/javascript" src="js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="js/toTop.js"></script>
<script type="text/javascript">
var id = '${id}';
$(function (){
	$.ajax({
		url : "getCodeInfoById.action",
		type : "post",
		async : false,
		data : "id=" + id,
		dataType : "json",
		success : function(data) {
			$('#htmlTitle').html(data.title);
			$('#htmlTime').html(data.publicTime.replace("T"," ")+"  <span style='color:#5CACEE;'>象山交警</span>");
			if(data.publicInfo.indexOf("width=\"200\"")!=-1){
				$('#htmlContent').html(data.publicInfo.replace("width=\"200\"","width=\"100%\""));
			}else{
				$('#htmlContent').html(data.publicInfo);
			}
			
			
			
		},
		error : function(data) {
			alert("error 后台出现错误！");
		}
	});
	$(window).toTop({
		showHeight : 100,//设置滚动高度时显示
		speed : 500 //返回顶部的速度以毫秒为单位
	});
});
</script>
</head>
<body>
<div class="list_h">

<script type="text/javascript">
	$(".footer-nav-menu-cls-btn").bind('click',function(e){
		var menuEle = $(this).parent().find(".dropdown-menu-top").eq(0);
		var display = menuEle.css("display");
		$("#footer-nav-div .dropdown-menu-top").removeClass("show");
		if(display == "none"){
			menuEle.addClass("show");
			if($("#container").height() < menuEle.height()){
				$("#container").height(menuEle.height());
			}
		}else{
			menuEle.removeClass("show");
		}
	});
    
  
    
</script>    
<div class="list_m">
        <div class="content">
                <div class="top_bd" >  <h2 id="htmlTitle"></h2>
        <p class="news_times" id="htmlTime" style="font-size:12px;"></p></div>
        <div id="htmlContent" style="font-size:15px;"></div>
        
<div style="" onclick="document.getElementById('mcover').style.display='';" id="mcover">
                <img src="image/guide.png">
            </div>
            <div style="margin-top:15px;">
              <div id="mess_share">
                  <div id="share_1">
                      <button onclick="document.getElementById('mcover').style.display='block';" class="button2" style="margin-bottom:15px;">
                          <img width="64" height="64" src="image/icon_msg.png">
                          &nbsp;发送给朋友
                      </button>
                  </div>
                  <div id="share_2">
                      <button onclick="document.getElementById('mcover').style.display='block';" class="button2" style="margin-bottom:15px;">
                          <img width="64" height="64" src="image/icon_timeline.png">
                          &nbsp;分享到朋友圈
                      </button>
                  </div>
                  
                  <div style="height:5px;"></div>
              </div>
            </div>
        </div>
    </div>

  <div id="gotop"></div>
  
</div>
</body>
</html>