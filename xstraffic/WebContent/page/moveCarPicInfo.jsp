<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>自助移车图片信息展示</title>
<style type="text/css">
body{background:#f5f5f5 url("css/bkg-pat.png") repeat scroll 0 0;}
.image-block img{border: 1px solid #87CEEB; border-radius: 4px 4px 4px 4px;background:#FFFFFF;padding:10px;}
.image-block img:hover{border: 1px solid #00CD00;box-shadow:0 0 5px #A9CF54;}
* {
	margin: 0px;
	padding: 0px;
	list-style: none;
}

img {float;left;
	width: 200px;
}
</style>
<script type="text/javascript" src="../js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="../js/fnToBig.js"></script>
<script type="text/javascript">
	window.onload = function() {
		fnToBig('animateOne');
	};
</script>
</head>
<body>
		<div id="focus" style="text-align:center;margin:0 auto; width:70%;">
		</div>
	<script type="text/javascript">
		$(function() {
			var carMoveId;
			var query = location.search.substring(1);
			var values = query.split("&");
			for ( var i = 0; i < values.length; i++) {
				var pos = values[i].indexOf('=');
				if (pos == -1)
					continue;
				carMoveId = values[i].substring(pos + 1);
			}
			var picData = getPic(carMoveId);
			var div = "<div>";
			for ( var i = 0; i < picData.length; i++) {
				div += "<img to_big=\"true\" style=\"max-width:600px;max-height:380px;\" id=\"detail_Url1\" src=\"viewImages.action?picPath="
						+ picData[i].picLocalStore
						+ "\" alt=\"照片\" />";
			}
			div += "</div>";
			$("#focus").append(div);

		});
		function getPic(carMoveId) {
			var rows = null;
			$.ajax({
				url : "getCarMovePic.action",
				type : "post",
				async : false,
				data : "carMoveId=" + carMoveId,
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