<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="format-detection" content="telephone=no">
<title>实时路况</title>
<link rel="stylesheet" type="text/css" href="css/css11.css" />
<script type="text/javascript" src="js/zepto.min.js"></script>
<style type="text/css">
*{
	margin:0;
	padding:0;
}
a{
	text-decoration:none;
}
li{
	list-style:none;
}
.main{
	width:100%;
	margin-top:5px;
}
.main a:hover{
	display:block;
	background-color:#f6f6f6;
}
.main .show{
	padding:15px 10px 5px 15px;
	border-bottom:1px dashed #d8d8d8;
	overflow:hidden;
	border-top:1px solid #d2d2d2;
	border-left:1px solid #d2d2d2;
	border-right:1px solid #d2d2d2;
	border-radius:5px;
}
.letfPic{
	float:left;
	width:40px;
	height:40px;
	border:1px solid #d2d2d2;
}
.main .show1 {
	width:78%;
	margin-left:10px;;
	float:left;
}
.main .show1 span{
	display:block;
	width:100%;
	min-height:30px;
	font-size:14px;
	line-height:22px;
	color:#4A708B;
}
.contentPic{
	margin-top:5px;
	width:120px;
	border:1px solid #d2d2d2;
}
.main .show1 h3{
	float:left;
	width:100%;
	font-size:13px;
	line-height:35px;
	color:#b3b3b3;
	font-weight:normal;
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
</style>

</head>
<body>
<div id="titleDiv" >
		<span style="font-size:18px;color:#FFFFFF;margin-right:10px;" id="addressName">实时路况播报</span>
	</div>
<div class="main" id="content">
        <!-- <div class="show" style="width:90%;margin:auto;">
            <img src="images/police5.jpg" class="letfPic"/>
            <div class="show1">
                <span>象山交警: #市区道路拥堵情况播报</span>
                <img src="images/xiuxian.jpg" class="contentPic"/>
                <h3>今天:09:55</h3>
            </div>
        </div>
        <div class="show" style="width:90%;margin:auto;">
            <img src="images/police5.jpg" class="letfPic"/>
            <div class="show1">
                <span>象山交警: #市区道路拥堵情况播报:市区大部分道路通畅~万松东路和罗阳大道交叉口缓慢通行</span>
                <img src="images/xiuxian.jpg" class="contentPic"/>
                <h3>今天:09:55</h3>
            </div>
        </div>
        <div class="show" style="width:90%;margin:auto;">
            <img src="images/police5.jpg" class="letfPic"/>
            <div class="show1">
                <span>象山交警: #市区道路拥堵情况播报:现正值下班高峰期,市区大部分道路通行缓慢~请司机朋友保持耐心~稳步前进</span>
                <img src="images/xiuxian.jpg" class="contentPic"/>
                <h3>今天:09:55</h3>
            </div>
        </div> -->
</div>
<div class="cz_wrapper">
    <a id="load_more" class="load_more" href="javascript:void(0);"><span id="to_loading" class="to_loading">点击查看更多</span><span id="loading" class="loading" style="display: none;">正在加载...</span></a>
</div>

<script type="text/javascript">
function toggle(showId, hideId)
{
    $('#'+hideId).hide();
    $('#'+showId).show();
}

var clock;

$(function(){
	getWb();
	window.setInterval("getWb();", 60000);
    var page = 2;
    $('#load_more').click(function(){
        toggle('loading', 'to_loading');
        $.ajax({
    		url : "getPublicInfoWB.action",
    		async : false,
    		data : "pageSize=10&pageIndex="+page,
    		dataType : "json",
    		type : "post",
    		success : function(data) {
    			var rows=data.rows;
    			var content = '';
    			for(var i=0;i<rows.length;i++){
    				//alert("haaa");
    				var contentInfo=rows[i].publicInfo;
    				
    				var publictime=rows[i].publicTime.split("T");
    				if(clock.trim()==publictime[0].trim()){
    					content+="<div class='show' style='width:90%;margin:auto;'><img src='images/police5.jpg' class='letfPic'/><div class='show1'><span>象山交警: #"+contentInfo+"</span><h3>今天  "+publictime[1]+"</h3></div></div>";
    				}else{
    					content+="<div class='show' style='width:90%;margin:auto;'><img src='images/police5.jpg' class='letfPic'/><div class='show1'><span>象山交警: #"+contentInfo+"</span><h3>"+publictime[0]+" "+publictime[1]+"</h3></div></div>";
    				}
    				
    				//var publicTime=rows[i].publicTime.replace("T"," ");
    				//content += '<li><a href="getCodeInfoJsp.action?id='+rows[i].id+'" target="_self" class="title"><p><strong>'+rows[i].title+'</strong></p><span class="time">'+publicTime+'</span></a></li>';
    			}
    			$("#content").append(content);
    			if((rows.length)<10){
    				$('#load_more').hide();
                    return;
    			}
    			page++;
    			toggle('to_loading', 'loading');
    		},
    		error : function(data) {
    			alert("error 后台出现错误！");
    		}
    	});
        
 
    });
});

function getWb(){
	var now = new Date();
    var year = now.getFullYear();
    var month = now.getMonth() + 1;
    var day = now.getDate();
    clock = year + "-";
    if(month < 10)
        clock += "0";
    clock += month + "-";
    if(day < 10)
        clock += "0";
    clock += day + " ";
	$.ajax({
		url : "getPublicInfoWB.action",
		async : false,
		data : "pageSize=10&pageIndex=1",
		dataType : "json",
		type : "post",
		success : function(data) {
			var rows=data.rows;
			var content="";
			for(var i=0;i<rows.length;i++){
				var contentInfo=rows[i].publicInfo;
				var publictime=rows[i].publicTime.split("T");
				if(clock.trim()==publictime[0].trim()){
					content+="<div class='show' style='width:90%;margin:auto;'><img src='images/police5.jpg' class='letfPic'/><div class='show1'><span>象山交警: #"+contentInfo+"</span><h3>今天  "+publictime[1]+"</h3></div></div>";
				}else{
					content+="<div class='show' style='width:90%;margin:auto;'><img src='images/police5.jpg' class='letfPic'/><div class='show1'><span>象山交警: #"+contentInfo+"</span><h3>"+publictime[0]+" "+publictime[1]+"</h3></div></div>";
				}
				
				//var publicTime=rows[i].publicTime.replace("T"," ");
				//content += '<li><a href="getCodeInfoJsp.action?id='+rows[i].id+'" target="_self" class="title"><p><strong>'+rows[i].title+'</strong></p><span class="time">'+publicTime+'</span></a></li>';
			}
			$("#content").empty();
			$("#content").append(content);
			if((rows.length)<10){
				$('#load_more').hide();
			}
		},
		error : function(data) {
			alert("error 后台出现错误！");
		}
	});
}
function lookpic(src){
	var srcHref=src.replace("viewImages","trafficWbPic")
	window.location.href=srcHref;
}

</script>
</body>
</html>
	