<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title id="titleName">实时路况</title>
<meta name="viewport" content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<link href="css/paxy.css" rel="stylesheet" type="text/css">
<script src="js/jquery.js" type="text/javascript"></script>
<script type="text/javascript" src="js/zepto.min.js"></script>
<script type="text/javascript">
	function toggle(showId, hideId)
	{
	    $('#'+hideId).hide();
	    $('#'+showId).show();
	}


	$(function(){
		$.ajax({
			url : "getRealTraffic.action",
			async : false,
			data : "",
			dataType : "json",
			type : "post",
			success : function(data) {
				var allurl=data.allUrl;
				var allname=data.allName;
				var urlLength=allurl.length;
				var nameLength=allname.length;
				if(urlLength==nameLength){
					if(urlLength==0){
						$("#content").append("<ul class='round' style='background-color:#F2F2F2;'><li class='nob'><div style='width:100%;text-align:center;'>没有相关数据</div></li></ul>");
					}else{
						var content="";
						for(var i=0;i<urlLength;i++){
							content += "<ul class='round' style='background-color:#F2F2F2;'><a href='"+allurl[i]+"'><li class='nob'><table width='100%' border='0' cellspacing='0' cellpadding='0' class='kuang' style='margin-top:5px;'><tbody><tr><th id='th_bbxm' style='width:60px;text-align:center;'>道路:</th><td style='height:30px;color:#030303;'>"+allname[i]+"</td></tr></tbody></table></li></a></ul>";
						}
						$("#content").append(content);
					}
					
				}else{
					$("#content").append("<ul class='round' style='background-color:#F2F2F2;'><li class='nob'><div style='width:100%;text-align:center;'>没有相关数据</div></li></ul>");
				}
				
				//if(urlLength<15){
					$('#load_more').hide();
				//}
			},
			error : function(data) {
				alert("网络繁忙!请稍后再试!");
			}
		});
		
		
		
		
	    var page = 2;
	    $('#load_more').click(function(){
	        toggle('loading', 'to_loading');
	        
	        var eleType=$("#eleType").val();
			var eleAddress=$("#eleAddress").val();
	       $.ajax({
	    		url : "getElePolices.action",
	    		async : false,
	    		data : "pageSize=10&pageIndex="+page+"&eleType="+eleType+"&eleAddress="+eleAddress,
	    		dataType : "json",
	    		type : "post",
	    		success : function(data) {
	    			var rows=data;
					content="";
					for(var i=0;i<rows.length;i++){
						content += "<ul class='round' style='background-color:#F2F2F2;'><li class='nob'><table width='100%' border='0' cellspacing='0' cellpadding='0' class='kuang'><tbody><tr><th id='th_bbxm'>卡口类型:</th><td style='height:40px;color:#030303;'>"+rows[i].eleType+"</td></tr><tr><th id='th_bbxm' style='vertical-align:top'>具体位置:</th><td style='height:20px;color:#030303;vertical-align:top;'>"+rows[i].eleAddress+"</td></tr></tbody></table></li></ul>";
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
</script>
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
</style>

</head>
<body id="img-content">
<div style="width:100%;" >
	<div id="titleDiv" >
		<span style="font-size:18px;color:#FFFFFF;">实时路况</span>
	</div>
</div>
		
		<div class="cardexplain" id="content">
		
			
		</div>
<div style="text-align:center;margin-bottom:10px;" >
    <a id="load_more"  href="javascript:void(0);"><span id="to_loading" >点击查看更多</span><span id="loading"  style="display: none;">正在加载...</span></a>
</div>



</body>
</html>