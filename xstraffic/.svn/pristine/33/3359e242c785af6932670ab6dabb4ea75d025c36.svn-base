<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=8" />
<title>添加功能菜单</title>
<link rel="stylesheet" type="text/css" href="themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="themes/icon.css">
<link rel="stylesheet" href="themes/default/default.css" />
<link rel="stylesheet" type="text/css" href="css/css11.css" />
<script charset="utf-8" src="js/kindeditor-min.js"></script>
<script charset="utf-8" src="js/zh_CN.js"></script>
<script type="text/javascript" src="js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
	var editor1;
	var editor2;
   String.prototype.trim = function() {
	  return this.replace(/^\s\s*/, '').replace(/\s\s*$/, '');
	};
	KindEditor.ready(function(K) {
		editor1 = K.create('#add_publicInfo', {
			resizeType : 2,
			width : '300px',
			minWidth : '100px',
			height:'360px',
			minHeight:'100px',
			uploadJson : 'fileUpload.action', 
			allowFileManager : true,
			items:[
			         'source','emoticons','image'
			        
			],
			afterBlur:function(){
				this.sync();
			},
			
		//取编辑器的值之前的操作
	    });
		
	})
	
	
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
				/*while(contentInfo.indexOf("width=\"200\"")!=-1){
					contentInfo=contentInfo.replace("width=\"200\"","class=\"contentPic\"");
				}
				while(contentInfo.indexOf("<img alt=\"\" src=\"/xstraffic")!=-1){
					contentInfo=contentInfo.replace("<img alt=\"\" src=\"/xstraffic","<br /><img alt=\"\" src=\"/xstraffic");
				}
				while(contentInfo.indexOf("<img class=\"contentPic\"")!=-1){
					contentInfo=contentInfo.replace("<img class=\"contentPic\"","<br /><img class=\"contentPic\"");
				}
				while(contentInfo.indexOf("<img src=\"/xstraffic")!=-1){
					contentInfo=contentInfo.replace("<img src=\"/xstraffic","<br /><img src=\"/xstraffic");
				}
				
				while(contentInfo.indexOf("&lt;img src=\"http:")!=-1){
					contentInfo=contentInfo.replace("&lt;img src=\"http:","<img src=\"http:");
				}
				while(contentInfo.indexOf("&lt;p")!=-1){
					contentInfo=contentInfo.replace("&lt;p","<p");
				}
				while(contentInfo.indexOf("&lt;/p")!=-1){
					contentInfo=contentInfo.replace("&lt;/p","</p");
				}
				while(contentInfo.indexOf("&gt;")!=-1){
					contentInfo=contentInfo.replace("&gt;",">");
				}*/
				var publictime=rows[i].publicTime.split("T");
				if(clock.trim()==publictime[0].trim()){
					content+="<div class='show' style='width:90%;margin:auto;'><img src='images/police5.jpg' class='letfPic'/><div class='show1'><p>象山交警: #"+contentInfo+"</p><h3>今天  "+publictime[1]+"<span style='float:right;'><a href=\"javascript:toDelete('"+rows[i].id+"')\">删除</a></span></h3></div></div>";
				}else{
					content+="<div class='show' style='width:90%;margin:auto;'><img src='images/police5.jpg' class='letfPic'/><div class='show1'><p>象山交警: #"+contentInfo+"</p><h3>"+publictime[0]+" "+publictime[1]+"<span style='float:right;'><a href=\"javascript:toDelete('"+rows[i].id+"')\">删除</a></span></h3></div></div>";
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
	
	
	function toggle(showId, hideId)
	{
	    $('#'+hideId).hide();
	    $('#'+showId).show();
	}
	
	
	$(function() {
		getWb();
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
	    			var content="";
	    			for(var i=0;i<rows.length;i++){
	    				//alert("haaa");
	    				var contentInfo=rows[i].publicInfo;
	    				var publictime=rows[i].publicTime.split("T");
	    				if(clock.trim()==publictime[0].trim()){
	    					content+="<div class='show' style='width:90%;margin:auto;'><img src='images/police5.jpg' class='letfPic'/><div class='show1'><p>象山交警: #"+contentInfo+"</p><h3>今天  "+publictime[1]+"<span style='float:right;'><a href=\"javascript:toDelete('"+rows[i].id+"')\">删除</a></span></h3></div></div>";
	    				}else{
	    					content+="<div class='show' style='width:90%;margin:auto;'><img src='images/police5.jpg' class='letfPic'/><div class='show1'><p>象山交警: #"+contentInfo+"</p><h3>"+publictime[0]+" "+publictime[1]+"<span style='float:right;'><a href=\"javascript:toDelete('"+rows[i].id+"')\">删除</a></span></h3></div></div>";
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
	    
	    $("#addWB").click(function() {
			var publicInfo = editor1.html();
			if (editor1.isEmpty()) {
		        alert('播报内容不能为空！');
		        return false;
		    }
			publicInfo=encodeURIComponent(publicInfo);
			$.ajax({
				url : "addPublicInfo.action",
				data : "publicInfo=" + publicInfo + "&title=&publicType=WB&description=",
				dataType : "json",
				type : "post",
				success : function(data) {
					if(data.respCode==0){
						editor1.html('');
						getWb();
					}else{
						alert(data.respInfo);
					}
					
				},
				error : function(data) {
					alert("error 后台出现错误！");
				}
			});
		});
	    
	    
		
	});
	function toDelete(aa){
		if (window.confirm("确认删除这信息吗？")) {
			var ids ="," + aa;
			$.ajax({
				url : "removePublicInfo.action",
				data : "ids="+ ids,
				dataType : "json",
				type : "post",
				success : function(data) {
					getWb();
				},
				error : function(data) {
					alert("error 后台出现错误！");
				}
			});
		} else {
			return false;
		}
	}
	
</script>
<style type="text/css">
<!--
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
.contentPic{
	margin-top:5px;
	width:120px;
	border:1px solid #d2d2d2;
}
.main .show1 {
	width:78%;
	margin-left:10px;;
	float:left;
}
.main .show1 p{
	display:block;
	width:100%;
	min-height:30px;
	font-size:14px;
	line-height:22px;
	color:#4A708B;
}

.main .show1 h3{
	float:left;
	width:100%;
	font-size:13px;
	line-height:35px;
	color:#b3b3b3;
	font-weight:normal;
}
a{
text-decoration:none;
}
.subscribe{
	width:100%; 
	line-height:40px ;
	height:40px;
	FILTER: progid:DXImageTransform.Microsoft.Gradient(gradientType=0,startColorStr=#6495ED,endColorStr=#96CDCD); /*IE 6 7 8*/ 
	background: -ms-linear-gradient(top, #6495ED,  #96CDCD);        /* IE 10 */
	background:-moz-linear-gradient(top,#6495ED,  #96CDCD);/*火狐*/ 
	background:-webkit-gradient(linear, 0% 0%, 0% 100%,from(#6495ED), to(#96CDCD));/*谷歌*/ 
	background: -webkit-gradient(linear, 0% 0%, 0% 100%, from(#6495ED), to(#96CDCD));      /* Safari 4-5, Chrome 1-9*/
	background: -webkit-linear-gradient(top, #6495ED, #96CDCD);   /*Safari5.1 Chrome 10+*/
	background: -o-linear-gradient(top, #6495ED, #96CDCD);  /*Opera 11.10+*/
	border:1px solid #528B8B;
	color:#FFFAFA;
	font-size:20px;
	text-align:center;
}
	
-->
</style>
</head>
<body style="" id="tt">
<table style="margin-top:20px;margin-left:80px;">
<tr>
	<td>
		<div id="searchpanel" class="easyui-panel panel-margin-buttom"
		 title="发布信息" style="height: 500px;width: 330px; padding: 10px;vertical-align: center;">
		<table height="100%">
			<tr
				style="Valign: center; text-decoration: none; line-heigth: 30px; ">
				<td>
				<div>
				<textarea  id="add_publicInfo" name="publicInfo1" style=""></textarea>
				</div>
				</td>
				
			</tr>
			<tr
				style=" line-heigth: 30px;">
				<td style="" align="center">
				<a id="addWB"  href="javascript:void(0);"> 
						<div class="subscribe">
							发布
						</div>
				</a></td>
				
			</tr>
		</table>
	</div>
	</td>

	<td style="width:700px;" align="center">
	
		<div id="searchpanel" class="easyui-panel panel-margin-buttom"
		 title="历史播报" style="margin-letf:30px;height: 500px;width: 500px; padding: 10px;vertical-align: center;">
			
		<div class="main" id="content">
         <!--<div class="show" style="width:90%;margin:auto;">
            <img src="images/police5.jpg" class="letfPic"/>
            <div class="show1" style="">
                <span style="padding: 3px 0px 0px 10px">象山交警: #傲视九等哈空间山东矿机啊哈SD卡就阿訇是空间大哈斯空间和打卡机山东矿机暗红色的空间哈斯柯达京哈开始和打卡实践活动 </span>
                <br /><img src="images/xiuxian.jpg" class="contentPic"/>
                <h3>今天:09:55<span style="float:right;"><a href="javascript:delete1('qqq')">删除</a></span></h3>
            </div>
        </div>
        <div class="show" style="width:90%;margin:auto;">
            <img src="images/police5.jpg" class="letfPic"/>
            <div class="show1">
                <p>象山交警: #市区道路拥堵情况播报:市区大部分道路通畅~万松东路和罗阳大道交叉口缓慢通行</p>
                <img src="images/xiuxian.jpg" class="contentPic"/>
                <h3>今天:09:55</h3>
            </div>
        </div>
        <div class="show" style="width:90%;margin:auto;">
            <img src="images/police5.jpg" class="letfPic"/>
            <div class="show1">
                <p>象山交警: #市区道路拥堵情况播报:现正值下班高峰期,市区大部分道路通行缓慢~请司机朋友保持耐心~稳步前进</p>
                <p><img src="images/xiuxian.jpg" class="contentPic"/></p>
                <h3>今天:09:55</h3>
            </div>
        </div> -->
	</div>	
			
			
		<div class="cz_wrapper">
   			 <a id="load_more" class="load_more" href="javascript:void(0);"><span id="to_loading" class="to_loading">点击查看更多</span><span id="loading" class="loading" style="display: none;">正在加载...</span></a>
		</div>	
	</div>
	
	</td>
</tr>

</table>


	
	
	
	
</body>
</html>

