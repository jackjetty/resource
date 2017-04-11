<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>查看详情</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0" />
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<script src="${pageContext.request.contextPath}/js/jquery-1.7.2.min.js" type="text/javascript"></script> 
<script src="${pageContext.request.contextPath}/js/lhgdialog/lhgdialog.min.js?skin=iblue" type="text/javascript"></script> 
<script src="${pageContext.request.contextPath}/js/jquery.rotate.min.js" type="text/javascript"></script> 
<script src="${pageContext.request.contextPath}/js/toTop.js" type="text/javascript"></script> 
<script charset="utf-8" src="http://map.qq.com/api/js?v=2.exp&key=QV5BZ-OLTHG-NKMQF-I4KIQ-7A356-HVFQM"></script>
<link rel="stylesheet"  href="${pageContext.request.contextPath}/css/tip.css"  /> 
<link rel="stylesheet"  href="${pageContext.request.contextPath}/css/toTop.css"  /> 
<link rel="stylesheet"  href="${pageContext.request.contextPath}/css/accidentDetail.css"  /> 
<script type="text/javascript">
$(document).ready(function() {
	
	<s:if test="!#request.result['locationX'].equalsIgnoreCase('')">
		 var center = new qq.maps.LatLng(<s:property value="#request.result['locationX']"/>,<s:property value="#request.result['locationY']"/>);
	 	 var map = new qq.maps.Map(document.getElementById('show-part'),{
	         center: center,
	         zoom: 13
	     });
	 	 
	     var marker = new qq.maps.Marker({
	         position: center,
	         map: map
	     });
	</s:if>
   
});
</script>
</head>
<body>
 
<div class="content">
	<div class="content1">
		<div class="Ctitle">
			<div class="button">
				<div id="btnReturn"><img src="${pageContext.request.contextPath}/image/button/return.png" style="float:left;"><input class="input" type="button" value="返回" style="width:55px; height:24px; background-color:#369be9; color:#FFF; border:1px #b5c5fd solid; margin-right:50px; float:left;cursor:pointer;" onmouseover="this.style.backgroundColor='#1c8fe7'" onmouseout="this.style.backgroundColor='#369be9'"></div>   
				<div id="btnExport"><img src="${pageContext.request.contextPath}/image/button/export.png" style="float:left;"><input class="input" type="button" value="导出" style="width:55px; height:24px; background-color:#369be9; color:#FFF; border:1px #b5c5fd solid; margin-left:5px; float:left;cursor:pointer;" onmouseover="this.style.backgroundColor='#1c8fe7'" onmouseout="this.style.backgroundColor='#369be9'"></div>
				<div id="btnPrint"><img src="${pageContext.request.contextPath}/image/button/print.png" style="float:left;"><input class="input" type="button" value="打印" style="width:55px; height:24px; background-color:#369be9; color:#FFF; border:1px #b5c5fd solid; margin-left:5px; float:left;cursor:pointer;" onmouseover="this.style.backgroundColor='#1c8fe7'" onmouseout="this.style.backgroundColor='#369be9'"></div>
			</div>
		</div>
		<div style=" width:85%; margin:0 auto;">
			<table id="mtable" width="100%" height="202" border="0" cellspacing="1" cellpadding="0" style="background-color:#b3b2b2;">
				<tr style="background-color:#ffffff">
					<td width="15%" style="background-color:#e6e6e7;"><div align="center">事故编号</div></td>
					<td width="18%">&nbsp;<s:property value="#request.result['accidentId']"/></td>
					<td width="15%" style="background-color:#e6e6e7;"><div align="center">用户名称</div></td>
					<td width="18%">&nbsp;<s:property value="#request.result['userName']"/></td>
					<td width="15%" style="background-color:#e6e6e7;"><div align="center">微信昵称</div></td>
					<td width="18%">&nbsp;<s:property value="#request.result['nickName']"/></td>
				</tr>
				<tr style="background-color:#ffffff">
					<td style="background-color:#e6e6e7;"><div align="center">上报人手机</div></td>
					<td>&nbsp;<s:property value="#request.result['reportPhoneNumber']"/></td>
					<td style="background-color:#e6e6e7;"><div align="center">另一方手机</div></td>
					<td>&nbsp;<s:property value="#request.result['partyPhoneNumber']"/></td>
					<td style="background-color:#e6e6e7;"><div align="center">处理状态</div></td>
					<td>&nbsp;<s:property value="#request.result['accidentState']"/></td>
				</tr>
				<tr style="background-color:#ffffff">
					<td style="background-color:#e6e6e7;"><div align="center">上报时间</div></td>
					<td colspan="5">&nbsp;<s:property value="#request.result['reportTime']"/></td>
				</tr>
				<tr style="background-color:#ffffff">
					<td style="background-color:#e6e6e7;"><div align="center">现场地址</div></td>
					<td colspan="5">&nbsp;<s:property value="#request.result['address']"/></td>
				</tr>
				<tr style="background-color:#ffffff">
					<td style="background-color:#e6e6e7;"><div align="center">文本说明</div></td>
					<td colspan="5"> 
						<s:iterator value="#request.result['textProcessRecords']" id="tbInfoText">
						&nbsp;&nbsp; <s:property value='textMessage' /><br>       
						</s:iterator> 
					</td>
				</tr>
			</table>
			<s:if test="!#request.result['accidentState'].equalsIgnoreCase('审核通过')">
			<div class="result">
				<input id="btnAuditFail" type="button" value="审核未通过" style="width:90px; height:28px; background-color:#97d53e; color:#FFF; border:1px #85c926 solid; margin-left:30px; float:right; cursor:pointer;" onmouseover="this.style.backgroundColor='#80cd12'" onmouseout="this.style.backgroundColor='#97d53e'">
				<input id="btnAuditThrough" type="button" value="审核通过" style="width:90px; height:28px; background-color:#97d53e; color:#FFF; border:1px #85c926 solid; margin-left:30px; float:right; cursor:pointer;" onmouseover="this.style.backgroundColor='#80cd12'" onmouseout="this.style.backgroundColor='#97d53e'">
			</div>
			</s:if>
		</div>
	</div>
	<div class="content2">
		<div class="Ctitle">
			<div>图片信息</div>
		</div>
		<div class="images" id="picInfo">
			<s:iterator value="#request.result['picProcessRecords']" id="tbInfoPic">
			<div style="width:100%; overflow:hidden;">
				<input id="<s:property value='id.picIndex' />" checked type="checkbox" name="picIndex" value="<s:property value='id.picIndex' />" style="width:20px; height:20px; border:1px #bfbfbf solid; float:right; margin-right:10px; margin-bottom:5px; margin-top:5px;" />
			</div>
			<div class="img">
				<div style="width:80%; margin:0 auto;">
					<label for="<s:property value='id.picIndex' />"><img name="image" src="${pageContext.request.contextPath}/viewImages.action?picPath=<s:property value='picLocalStore' />" style="width:100%; margin-top:20px; margin-bottom:20px;"></label>
					<%-- <label for="<s:property value='id.picIndex' />"><img name="image" src="${pageContext.request.contextPath}/image/accident/AccidentFront.jpg"></label> --%>
					<span class="rotateTime" style="display: none;">0</span>
				</div>
			</div>
			</s:iterator> 
		</div>
	</div>
	<div class="content2">
		<div class="Ctitle">
			<div>位置信息</div>
		</div>
		<div class="text">地址：<s:property value="#request.result['address']"/></div>
		<div class="map" id="show-part">
		</div>
	</div>
</div>
<script type="text/javascript">
	$(function(){
		$("[name=picIndex]").change(function() {
			$("[name=picIndex]").parent().next().find("[name=image]").css("opacity", "0.5");
			$("[name=picIndex]:checked").parent().next().find("[name=image]").css("opacity", "1.0");
		});
		
		$("[name=image]").dblclick(function() {
			var rotateTime = parseInt($(this).parent().parent().find("span").text());
			rotateTime = (rotateTime + 1) % 4;
			$(this).parent().parent().find("span").text(rotateTime);
			$(this).rotate({
				angle : rotateTime * 90,
			});
			if (rotateTime % 2 == 1) {
				$(this).parent().parent().css("height", $(this).css("width"));
				$(this).css("position", "relative");
				var px = ($(this).css("width").split("px")[0] - $(this).css("height").split("px")[0]) / 2;
				$(this).css("top", px);
			} else {
				$(this).parent().parent().css("height", $(this).css("height"));
				$(this).css("position", "relative");
				$(this).css("top", "0");
			};
		});
		
		$("#btnAuditFail").click(function() {
			$.dialog({
				title :'审核未通过',
				lock : true,
				width : 550,
				height : 'auto',
				max : false,
				min : 350,  
				fixed : true,
				drag : true,
				resize : false,
				close : function() {
					$("#btnReturn").click();
				},
				content : "url:${pageContext.request.contextPath}/getSelectCaseByAccident.action?accidentId=<s:property value='#request.accidentId'/>"
			});
		});
		
		$("#btnAuditThrough").click(function() {
			$.dialog.confirm('您确定要审核通过该事故记录吗？', function() {
				$.ajax({
					url : "${pageContext.request.contextPath}/saveProcedureMessage1.action",
					data : {accidentId:"<s:property value="#request.accidentId"/>"},
					dataType : "json",
					type : "post",
					success : function(data) {
						if (data.respCode == 0) {
							$.dialog.alert(data.respInfo,function() {
								$("#btnReturn").click();
							});
							return false; 
						} else {
							$.dialog.alert(data.respInfo,function() {
								
							});
						}
					}
				}); 
			}, function(){ 
				     
			});
		});
		
		$("#btnReturn").click(function() {
			var jsArrInfoPic=getJsArrInfoPic();
			$.ajax({
				url : "${pageContext.request.contextPath}/rotatePic.action",
				data : {
					jsArrInfoPic : jsArrInfoPic,
					redirectType : "success"
				},
				dataType : "json",
				type : "post",
				success : function(data) {
					/*if (data.respCode == 0) {
						 $.dialog.alert(data.respInfo,function() {
							
						}); 
						return false; 
					} else {
						 $.dialog.alert(data.respInfo,function(){
							
						}); 
					}*/
					history.go(-1);
					
				}
			});  
			
		});
		
		$("#btnPrint").click(function() {
			var jsArrInfoPic=getJsArrInfoPic(); 
			var urlPath = "${pageContext.request.contextPath}/rotatePic.action";
			var arrPicIndex = "";
			$("#picInfo input[name=picIndex][type=checkbox]:checked").each(function() {
				arrPicIndex += $(this).val() + ","; 
			});
			if (arrPicIndex.length>0) {
				arrPicIndex = arrPicIndex.substring(0, arrPicIndex.length-1);
			} else {
				$.dialog.alert('请至少选择一张图片',function() {
					
				});
				return false;
			}    
			urlPath += "?accidentId=<s:property value='#request.accidentId'/>&arrPicIndex=" + arrPicIndex;
			urlPath += "&exportType=pdf";
			urlPath += "&jsArrInfoPic="+jsArrInfoPic;
			urlPath += "&redirectType=accidentPrint";
			self.location.href = urlPath;
		});
		
		$("#btnExport").click(function() {
			var jsArrInfoPic=getJsArrInfoPic();  
			var urlPath="${pageContext.request.contextPath}/rotatePic.action";
			var arrPicIndex = "";
			$("#picInfo input[name=picIndex][type=checkbox]:checked").each(function() {
				arrPicIndex += $(this).val() + ","; 
			});
			if (arrPicIndex.length > 0) {
				arrPicIndex = arrPicIndex.substring(0, arrPicIndex.length-1);
			} else {
				$.dialog.alert('请至少选择一张图片', function() {
				});
				return false;
			}
			urlPath+="?accidentId=<s:property value='#request.accidentId'/>&arrPicIndex="+arrPicIndex;
			urlPath+="&exportType=xls";
			urlPath += "&jsArrInfoPic="+jsArrInfoPic;
			urlPath += "&redirectType=accidentExport";
			window.open( urlPath);
		 });
		
		$(window).toTop({
			showHeight : 100,//设置滚动高度时显示
			speed : 500 //返回顶部的速度以毫秒为单位
		});
	});
	
	function getJsArrInfoPic() {
		var rotateTimeArray = $(".rotateTime").text();
		var jsArrInfoPic = [];
		for (var i = 0; i < rotateTimeArray.length; i++) {
			if (rotateTimeArray[i] != 0) {
				jsInfoPic = {
					processId : "FORMALACCIDENT",
					recordNo : "<s:property value='#request.accidentId'/>",
					picIndex : parseInt($(".rotateTime:eq(" + i + ")").parent().parent().prev().find("[name=picIndex]").val()),
					rotateDegree : (rotateTimeArray[i] * 90)
				}
				jsArrInfoPic.push(JSON.stringify(jsInfoPic));
				rotateTimeArray[i]=0;
			}
		}
		return "[" + jsArrInfoPic.toString() + "]"; 
	}
</script>
</body>
</html>