<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="format-detection" content="telephone=no">
<title id="titleType">公告</title>
<link rel="stylesheet" type="text/css" href="css/css11.css" />
<link rel="stylesheet" type="text/css" href="css/css11_append_20.css" />
<script type="text/javascript" src="js/zepto.min.js"></script>
</head>
<body>

<div class="cz_wrapper">
    <ul class="news_list"></ul>
</div>
<div class="cz_wrapper">
    <a id="load_more" class="load_more" href="javascript:void(0);"><span id="to_loading" class="to_loading">点击查看更多</span><span id="loading" class="loading" style="display: none;">正在加载...</span></a>
</div>

<script type="text/javascript">
var publicType = '${publicType}';
function toggle(showId, hideId)
{
    $('#'+hideId).hide();
    $('#'+showId).show();
}


$(function(){
	if(publicType=="Notice"){
		$("#titleType").html("交通公告");
	}else{
		$("#titleType").html("交通宣传");
	}
	$.ajax({
		url : "getAllCodeJsp.action",
		async : false,
		data : "pageSize=10&pageIndex=1&publicType="+publicType,
		dataType : "json",
		type : "post",
		success : function(data) {
			var rows=data;
			content="";
			for(var i=0;i<rows.length;i++){
				var publicTime=rows[i].publicTime.replace("T"," ");
				content += '<li><a href="getCodeInfoJsp.action?id='+rows[i].id+'" target="_self" class="title"><p><strong>'+rows[i].title+'</strong></p><span class="time">'+publicTime+'</span></a></li>';
			}
			$(".news_list").append(content);
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
    		url : "getAllCodeJsp.action",
    		async : false,
    		data : "pageSize=10&pageIndex="+page,
    		dataType : "json",
    		type : "post",
    		success : function(data) {
    			var content = '';
    			var rows=data;
    			for(var i=0;i<rows.length;i++){
    				content += '<li><a href="getCodeInfoJsp.action?id='+rows[i].id+'" target="_self" class="title"><p><strong>'+rows[i].title+'</strong></p><span class="time">'+rows[i].publicTime+'</span></a></li>';
    			}
    			$(".news_list").append(content);
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
</body>
</html>
	