<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>交通事故图片信息展示</title>
<style type="text/css">
* {
	margin: 0px;
	padding: 0px;
}
</style>
<script type="text/javascript" src="../js/jquery-1.7.2.js"></script>
</head>
<body>
		<div id="focus" style="text-align:center;margin:0 auto;">
		</div>
	<script type="text/javascript">
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
			var div = "<div>";
				div += "<img id=\"detail_Url1\" src=\"viewImages.action?picPath="
						+ accidentId
						+ "\" alt=\"照片\" />";
			div += "</div>";
			$("#focus").append(div);

		});
	</script>
</body>
</html>