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
<title>审核失败</title> 
<script src="${pageContext.request.contextPath}/js/jquery-1.7.2.js" type="text/javascript"></script> 
<script src="${pageContext.request.contextPath}/js/lhgdialog/lhgdialog.min.js?skin=iblue" type="text/javascript"></script> 
<link rel="stylesheet"  href="${pageContext.request.contextPath}/css/common.css"  />
<link rel="stylesheet"  href="${pageContext.request.contextPath}/css/tip.css"  /> 
<link rel="stylesheet"  href="${pageContext.request.contextPath}/css/public.css"  /> 
<style type="text/css">
   #myForm{
   text-align:center;
   }
</style>
</head>
<body>
<div id="dialog-detail" style="padding:15px 5px 15px 5px;*padding:13px 3px 13px 3px; ">
	<form action="#" id="myForm">
		<table width="500px" style="margin:auto;" cellpadding="4" cellspacing="1" align="center" valign="middle"> 
		<s:iterator value="#request.atp" id="tbProcedure">      
			<tr>
				<td width="10%">
         				<input type="checkbox" name="procedureId" value="<s:property value='id.procedureId' />"   >  
				</td>
				<td class='right_content'>
					<span name="procedureName" style="font-size: 14px"><s:property value='procedureName' /></span>
    				</td>
			</tr>       
			</s:iterator> 
			<tr>
				<td colspan='2'>
					<div class="form-textarea-box">  
						<textarea id="custResText" class="form-textarea textStorage" cols="" rows="40" placeholder="请选择类型" style="height:100px;" name="content"></textarea> 
					</div>
				</td>
			</tr>
			
			
			<tr> 
				<td colspan='2' class="button_right_title" style="padding: 6px 5;height:45px;*height:42px" >
					<div align='left' style="float:left;width:100%;margin-right:-130px;" ></div>
					<div align="right" style="float: right; width: 130px;">
          				<a style="margin-right:20px" id='cmdPublish' class="pushBtn" hideFocus="true" href="javascript:void(0);" >
          					<img height="20" width="20" src="${pageContext.request.contextPath}/image/button/ok.gif" />
          					<b>确认</b>
          				</a>
						<a id='cmdCancel' class="pushBtn" hideFocus="true" href="javascript:void(0);" >
							<img height="20" width="20" src="${pageContext.request.contextPath}/image/button/delete.gif" />
							<b>取消</b>
						</a>
					</div>   
				</td> 
      			 </tr>
		</table>
	</form>
</div>
<script type="text/javascript">
	$(function() {
		$("#myForm [name=procedureId]:checkbox").change(function() {
			var str = "";
			var i = 0;
			$("#myForm [name=procedureId][checked]:checkbox").each(function() {
				if (i == 0) {
					str += $(this).parent().parent().find("[name=procedureName]").html();
				} else {
					str += ";" + $(this).parent().parent().find("[name=procedureName]").html();
				}
				i++;
			});
			if(str!=''){
				str ='您提交的信息有误：'+ str + "，请根据提示再次提交。";
				$("#custResText").val(str);
			}else{
				$("#custResText").val("");
			} 
		});
		
		$("#cmdPublish").click(function() {
			var procedureIds= '';
			var i = 0;
			$("#myForm [name=procedureId][checked]:checkbox").each(function() {
				if (i == 0) {
					procedureIds += $(this).val();
				} else {
					procedureIds += "," + $(this).val();
				}
				i++;
			});
			if(procedureIds==''){
				$.dialog.alert("请选择审核不通过原因!", function(){});
				return false;
			}
			var custResText = $.trim($("#custResText").val());
			$.ajax({
				url : "${pageContext.request.contextPath}/responseWei.action",
				data : {
					"procedureIds" : procedureIds,
					"custResText" : custResText,
					"accidentId" : '<s:property value="#request.accidentId"/>'
				},
				dataType : "json",
				type : "post",
				success : function(data) {
					if (data.respCode == 0) {
						$.dialog.alert(data.respInfo, function(){});
					} else {
						$.dialog.alert(data.respInfo, function(){});
					}
				},
				error : function(data) {
					$.dialog.alert("网络繁忙!请稍后再试!", function(){});
				}
			});
		});
		
		$("#cmdCancel").click(function() {
			var api = frameElement.api;  
    	    api.close();
		});
	});
</script>
</body>
</html>