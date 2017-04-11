
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html lang="en">
	<head>
		<meta charset="utf-8" />
		<meta name="author" content="www.frebsite.nl" />
		<meta name="viewport" content="width=device-width initial-scale=1.0 maximum-scale=1.0 user-scalable=yes" /> 
		<title>电子警察</title>
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/elePolice.css" />
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery.mmenu.all.css" /> 
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/js/tencent/css/style.css" /> 
		<style type="text/css">
		    
			#container
			{
			padding: 0px 0px 0px 0px; 
			}
			 
			#titleDiv {
					    background-color: #179F00;
					    background-image: -moz-linear-gradient(center bottom , #179F00 0%, #5DD300 100%);
					    box-shadow: 0px 0px 0px #94E700 inset, 0px 1px 2px rgba(0, 0, 0, 0.5);
					    color: #FFF;
					    width: 100%;
					    line-height: 40px;
					    margin: 0px auto;
					    text-align: center;
					}
			 .btnMenu{
				background: center center no-repeat transparent;
				background-image: url(../image/button/menu.png);
				display: block;
				width: 40px;
				height: 40px;
				position: absolute;
				top: 0;
				left: 10px;
			}
			 
		</style>

		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.2.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.mmenu.min.all.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/tencent/msgbox.js"></script>
		<script charset="utf-8" src="http://map.qq.com/api/js?v=2.exp&key=QV5BZ-OLTHG-NKMQF-I4KIQ-7A356-HVFQM"></script>
		<script type="text/javascript">
			$(function() {
				ZENG.msgbox.show("加载中...", 6);
			});
			$(function() {
				var $menu = $('nav#menu'),
					$html = $('html, body');

				$menu
					.mmenu()
					.find( 'a' )
					.on( 'click',
						function()
						{
							var href = $(this).attr( 'href' );
							$menu.one(
								'closed.mm',
								function()
								{
									setTimeout(
										function()
										{
											$html.animate({
												scrollTop: $( href ).offset().top
											});	
										}, 10
									);	
								}
							);
						}
					)
					.end()
					.find( 'li' )
					.first()
					.trigger( 'setSelected' );
			});
			
			
			

			/***************地图相关js代码***************/
			var map, markersArray = [], infoWinArray = [];
			var initFlag=true;
			var beforeNorthEastLat=0;
			var beforeNorthEastLng=0;
			$(function() {
				$("#container").css("height", $(window).height() - $("#titleDiv").height());
				$("#container").css("width", $(window).width());
				
				var center = new qq.maps.LatLng(30.00325, 120.5800953);
				map = new qq.maps.Map(document.getElementById('container'), {
			        center: center,
			        zoomControl: false,
			        zoom:17 
			    });
				   
			 	 
				   qq.maps.event.addListener(map,'mousemove',function(event) {
					   
					   
				    });
				 /* qq.maps.event.addListener(map,'zoom_changed',function() {
					 alert("1234");

				    });*/
				 
				//
				     /* qq.maps.event.addListener(map, 'click', function() {
					 		
					 		//alert("123");
					    });*/
								    
				    qq.maps.event.addListener(map,'bounds_changed',function() {
				    	ZENG.msgbox._hide();
				    	var bounds = map.getBounds();
				    	var northEastLat= bounds.getNorthEast().getLat().toFixed(5);
				    	var northEastLng= bounds.getNorthEast().getLng().toFixed(5);
				    	if(Math.abs(beforeNorthEastLat-northEastLat)>0.0025
				    			||Math.abs(beforeNorthEastLng-northEastLng)>0.0025) {
				    		beforeNorthEastLat=northEastLat;
				    		beforeNorthEastLng=northEastLng; 
				    		getMonitorList( $("#monitorType").val(), $("#position").val());
				    	}  
				     }); 
				 
			});
			
			function showMarker(map, icon, location, text,imgPath) {
				
				   var marker = new qq.maps.Marker({
					  icon: icon, 
					  position: location ,
				        map: map
				    });

				    var info = new qq.maps.InfoWindow({
				        map: map
				    });
                    
				    qq.maps.event.addListener(marker, 'click', function() {
				    	clearInfoWin(); 
				    	info.open(); 
				        info.setContent('<div style="text-align:center; white-space:nowrap; margin:3px;"><img src="'+imgPath+'">&nbsp&nbsp' +  text + '</div>');
				        info.setPosition(marker); 
				    });
				
				
				 
				
				markersArray.push(marker);
				infoWinArray.push(info);
			}
			
			function clearMarker() {
				if (markersArray) {
			        for (i in markersArray) {
			            markersArray[i].setMap(null);
			        }
			    }
				if (infoWinArray) {
			        for (i in infoWinArray) {
			        	
			        	 
			        	infoWinArray[i].setMap(null);
			        }
			    }
				 markersArray = [];
				 infoWinArray = [];
				 
			}
			
			function clearInfoWin() {
				if (infoWinArray) {
			        for (i in infoWinArray) {
			        	
			        	 
			        	infoWinArray[i].close();
			        }
			    }
			}
			/******************************************/
	$(function() { 
		
		// getMonitorList("电子警察", "");
	});
	$(function(){
		
		$("#seacher").click(function(){
			getMonitorList( $("#monitorType").val(), $("#position").val());
		}) ;
			
		 
	});		

	function getMonitorList(monitorType, position) { 
		 clearMarker();  
		 var bounds = map.getBounds();
		 
		 $.ajax({
    		url : "getMonitorList.action",
    		async : true,
    		data : {
    			monitorType : monitorType,
    			position : position,
    			bigLat: bounds.getNorthEast().getLat().toFixed(5),
    			smallLat: bounds.getSouthWest().getLat().toFixed(5),
    			bigLng: bounds.getNorthEast().getLng().toFixed(5),
    			smallLng: bounds.getSouthWest().getLng().toFixed(5)
    		},
    		dataType : "json",
    		type : "post",
    		success : function(data) { 
    			if (data.respCode == 0) {
    				// 标记
    				var anchor = new qq.maps.Point(6, 6);
    		        size = new qq.maps.Size(40, 40); 
    		        var origin = new qq.maps.Point(0, 0);
    		        var iconPath="${pageContext.request.contextPath}/image/map/";
    				var monitor;
    				var icon; 
    				
    				 $(data.monitorList).each(function(ind){ 
    					monitor=data.monitorList[ind];
    					if(ind==0){
    						 switch(true)
    						 {
    						 case monitor.monitorTypeName == "电子警察":
    							 iconPath+="elepolice/";
    						   break;
    						 case monitor.monitorTypeName == "智能卡口":
    							 iconPath+="intcard/";
    						   break;
    						 case monitor.monitorTypeName == "带云台抢机":
    							 iconPath+="ptzgrab/";
       						   break;
    						 case monitor.monitorTypeName == "球机":
    							 iconPath+="ballmachine/";
       						   break;
    						 default:
    						   break;
    						 
    						 }
    						 //monitor.direction
    						 icon=new qq.maps.MarkerImage(iconPath +'icon.png',  size, 
    			    		            origin,
    			    		            anchor);
    						 
    						  
        					 
    						 
    						
    					   } 
    					showMarker(map, icon, new qq.maps.LatLng(monitor.latitude, monitor.longitude), monitor.position,iconPath+monitor.direction+'.png');
    				    
    					
    					
                        
          		    }); 
    			}
    		},
    		error : function(data) {
    			alert("error 后台出现错误！");
    		}
    	}); 
	}
	 </script>
	</head>
	<body>
	<!--  class="header FixedTop"  --> 
		<div id="page">
			<div id="titleDiv" >
				<a class="btnMenu" href="#menu"></a>
				电子警察
			</div>
			 
			<div id="container" > 
			</div>
			 
		</div>
		<nav id="menu" class=".mm-menu.mm-white">
			<ul>
			     <li>
			                   <span style="font-size:16px;color:black;margin:5px 0px;font-weight：bloder;">卡口类型:</span>
								<select id="monitorType">
									<option value="电子警察" checked>电子警察</option>
									<option value="智能卡口">智能卡口</option>
									<option value="带云台抢机">带云台抢机</option>
									<option value="球机">球机</option>
								</select>
				 </li>
				 <li>
				        <span style="font-size:16px;color:black;margin:5px 0px;font-weight：bloder;">路段名称关键字:</span>
				     
						<input id="position" type='text' />
				 </li>    
				 
				<li><br>  <a  href="#page" id="seacher" class="button">查询</a> </li>
			</ul>
		</nav>
	</body>
</html>