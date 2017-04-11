<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title id="titleName">事故记录</title>
<meta name="viewport" content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<link href="css/paxy.css" rel="stylesheet" type="text/css">
<script src="js/jquery.js" type="text/javascript"></script>
<script type="text/javascript" src="js/zepto.min.js"></script>
<script type="text/javascript">
	var openId = '${openId}';
	function toggle(showId, hideId)
	{
	    $('#'+hideId).hide();
	    $('#'+showId).show();
	}


	$(function(){
		$.ajax({
			url : "getRecordsByOpenId.action",
			async : false,
			data : "pageSize=10&pageIndex=1&openId="+openId,
			dataType : "json",
			type : "post",
			success : function(data) {
				var rows=data;
				if(rows.length==0){
					$("#content").append("没有事故记录");
				}else{
					content="";
					for(var i=0;i<rows.length;i++){
						if(rows[i].accidentState=="上报"){
							content += "<ul class='round' style='background-color:#F2F2F2;'><li class='nob'><table width='100%' border='0' cellspacing='0' cellpadding='0' class='kuang'><tbody><tr><th id='th_bbxm'>事故编号:</th><td style='height:40px;color:#030303;'>"+rows[i].formlAccidentId+"</td></tr><tr><th id='th_bbxm'>事故地点:</th><td style='height:40px;color:#030303;'>"+rows[i].address+"</td></tr><tr><th id='th_bbxm'>事故时间:</th><td style='height:40px;color:#030303;'>"+rows[i].reportTime+"</td></tr><tr><th id='th_bbxm'>事故状态:</th><td style='height:40px;color:#0000EE;'>"+rows[i].accidentState+"</td></tr></tbody></table></li></ul>";
						}else if(rows[i].accidentState=="审核通过"){
							content += "<ul class='round' style='background-color:#F2F2F2;'><li class='nob'><table width='100%' border='0' cellspacing='0' cellpadding='0' class='kuang'><tbody><tr><th id='th_bbxm'>事故编号:</th><td style='height:40px;color:#030303;'>"+rows[i].formlAccidentId+"</td></tr><tr><th id='th_bbxm'>事故地点:</th><td style='height:40px;color:#030303;'>"+rows[i].address+"</td></tr><tr><th id='th_bbxm'>事故时间:</th><td style='height:40px;color:#030303;'>"+rows[i].reportTime+"</td></tr><tr><th id='th_bbxm'>事故状态:</th><td style='height:40px;color:#54FF9F;'>"+rows[i].accidentState+"</td></tr></tbody></table></li></ul>";
						}else{
							content += "<ul class='round' style='background-color:#F2F2F2;'><li class='nob'><table width='100%' border='0' cellspacing='0' cellpadding='0' class='kuang'><tbody><tr><th id='th_bbxm'>事故编号:</th><td style='height:40px;color:#030303;'>"+rows[i].formlAccidentId+"</td></tr><tr><th id='th_bbxm'>事故地点:</th><td style='height:40px;color:#030303;'>"+rows[i].address+"</td></tr><tr><th id='th_bbxm'>事故时间:</th><td style='height:40px;color:#030303;'>"+rows[i].reportTime+"</td></tr><tr><th id='th_bbxm'>事故状态:</th><td style='height:40px;color:#030303;'>"+rows[i].accidentState+"</td></tr></tbody></table></li></ul>";
						}
						
					}
					$("#content").append(content);
				}
				
				if((rows.length)<10){
					$('#load_more').hide();
				}
			},
			error : function(data) {
				alert("error 后台出现错误！");
			}
		});
		
		
		
		
	    var page = 2;
	    $('#load_more').click(function(){
	        toggle('loading', 'to_loading');
	        
	        
	       $.ajax({
	    		url : "getRecordsByOpenId.action",
	    		async : false,
	    		data : "pageSize=10&pageIndex="+page+"&openId="+openId,
	    		dataType : "json",
	    		type : "post",
	    		success : function(data) {
	    			var rows=data;
					content="";
					for(var i=0;i<rows.length;i++){
						if(rows[i].accidentState=="上报"){
							content += "<ul class='round' style='background-color:#F2F2F2;'><li class='nob'><table width='100%' border='0' cellspacing='0' cellpadding='0' class='kuang'><tbody><tr><th id='th_bbxm'>事故编号:</th><td style='height:40px;color:#030303;'>"+rows[i].formlAccidentId+"</td></tr><tr><th id='th_bbxm'>事故地点:</th><td style='height:40px;color:#030303;'>"+rows[i].address+"</td></tr><tr><th id='th_bbxm'>事故时间:</th><td style='height:40px;color:#030303;'>"+rows[i].reportTime+"</td></tr><tr><th id='th_bbxm'>事故状态:</th><td style='height:40px;color:#0000EE;'>"+rows[i].accidentState+"</td></tr></tbody></table></li></ul>";
						}else if(rows[i].accidentState=="审核通过"){
							content += "<ul class='round' style='background-color:#F2F2F2;'><li class='nob'><table width='100%' border='0' cellspacing='0' cellpadding='0' class='kuang'><tbody><tr><th id='th_bbxm'>事故编号:</th><td style='height:40px;color:#030303;'>"+rows[i].formlAccidentId+"</td></tr><tr><th id='th_bbxm'>事故地点:</th><td style='height:40px;color:#030303;'>"+rows[i].address+"</td></tr><tr><th id='th_bbxm'>事故时间:</th><td style='height:40px;color:#030303;'>"+rows[i].reportTime+"</td></tr><tr><th id='th_bbxm'>事故状态:</th><td style='height:40px;color:#54FF9F;'>"+rows[i].accidentState+"</td></tr></tbody></table></li></ul>";
						}else{
							content += "<ul class='round' style='background-color:#F2F2F2;'><li class='nob'><table width='100%' border='0' cellspacing='0' cellpadding='0' class='kuang'><tbody><tr><th id='th_bbxm'>事故编号:</th><td style='height:40px;color:#030303;'>"+rows[i].formlAccidentId+"</td></tr><tr><th id='th_bbxm'>事故地点:</th><td style='height:40px;color:#030303;'>"+rows[i].address+"</td></tr><tr><th id='th_bbxm'>事故时间:</th><td style='height:40px;color:#030303;'>"+rows[i].reportTime+"</td></tr><tr><th id='th_bbxm'>事故状态:</th><td style='height:40px;color:#030303;'>"+rows[i].accidentState+"</td></tr></tbody></table></li></ul>";
						}
						
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
</style>

</head>
<body id="img-content">
<div style="width:100%;margin-top:2px;" >
	<div style="width:98%;height:40px;background-color:#6495ED;border-radius:5px;margin:0 auto;text-align:center;">
		<span style="font-size:25px;color:#030303;">历史事故记录</span>
	</div>
</div>

		<div class="cardexplain" id="content">
			
			
			
		</div>
<div style="text-align:center;margin-bottom:10px;" >
    <a id="load_more"  href="javascript:void(0);"><span id="to_loading" >点击查看更多</span><span id="loading"  style="display: none;">正在加载...</span></a>
</div>



</body>
</html>