<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=8" />
<title>交通事故位置信息展示</title>
<style type="text/css">
#allmap {
	width: 100%;
	height: 100%;
	overflow: hidden;
	margin: 0;
}
</style>
<script type="text/javascript" src="../js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=1.5&ak=90ebaec1467373aecb8ed95b418d5e03"></script>
</head>
<body>
<div>
	<div id="allmap"
		style="width: 1200px; height: 600px; text-align: center; margin: 0 auto;"></div>
		
	<script type="text/javascript">
	$(function() { 
        var fullWidth=$(window).width();
		var fullHeight=$(window).height();
        $('#allmap').css({'width':fullWidth}).css("height", fullHeight);
     });
		$(function() {
			var accidentId;
			var query = location.search.substring(1);
			var values = query.split("&");
			for ( var i = 0; i < values.length; i++) {
				var pos = values[i].indexOf('=');
				if (pos == -1)
					continue;
				accidentId = values[i].substring(pos + 1);
			}
			var addr = getAddress(accidentId);
			var address = addr.address;
			var map = new BMap.Map("allmap",{minZoom:12,maxZoom:20}); //创建Map实例，并设置缩放范围
			map.centerAndZoom(new BMap.Point(addr.locationX,addr.locationY), 20);
			var marker1 = new BMap.Marker(new BMap.Point(addr.locationX,addr.locationY));  // 创建标注
			map.addOverlay(marker1);              // 将标注添加到地图中
			//var point = new BMap.Point(120.121031,30.297511);    // 创建点坐标
			//map.centerAndZoom(marker1,10);                     // 初始化地图,设置中心点坐标和地图级别。
			map.enableScrollWheelZoom(); 
			map.addControl(new BMap.NavigationControl());
		    map.addControl(new BMap.ScaleControl());
		    map.addControl(new BMap.OverviewMapControl());  //启用滚轮放大缩小
			//创建信息窗口
			var infoWindow1 = new BMap.InfoWindow(address,{enableMessage:false });
			marker1.addEventListener("click", function(){this.openInfoWindow(infoWindow1);});
		});
		function getAddress(accidentId) {
			var rows = null;
			$.ajax({
				url : "getTpAddress.action",
				type : "post",
				async : false,
				data : "takingPhotosId=" + accidentId,
				dataType : "json",
				success : function(data) {
					rows = data;
				},
				error : function(data) {
					alert("error 后台出现错误！");
				}
			});
			return rows;
		}
	</script>
</body>
</html>