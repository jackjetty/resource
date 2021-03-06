<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
<title>添加信息</title>
<link rel="stylesheet" type="text/css" href="css/style.css"/>
<link rel="stylesheet" type="text/css" href="css/information.css"/>
<link rel="stylesheet" href="themes/default/default.css" />
<script charset="utf-8" src="js/kindeditor-min.js"></script>
<script charset="utf-8" src="js/zh_CN.js"></script>
<script type="text/javascript" src="js/jquery-1.7.2.js"></script>
<script type="text/javascript">
var editor1;
var editor2;
String.prototype.trim = function() {
  return this.replace(/^\s\s*/, '').replace(/\s\s*$/, '');
};
KindEditor.ready(function(K) {
	editor1 = K.create('#add_htmlContent', {
		resizeType : 2,
		minWidth:'350px',
		width : '350px',
		
		height:'400px;',
		uploadJson : 'fileUpload.action', 
		allowFileManager : true,
		items:[
		        'source', '|', 'undo', 'redo', '|', 'justifyleft', 'justifycenter', 'justifyright',
		        'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 
		         'selectall', '/','|','fullscreen', 
		        'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
		        'italic', 'underline', 'strikethrough', 'image',
		        
		],
		afterBlur:function(){
			this.sync();
		},
		
	//取编辑器的值之前的操作
    });
	
})

$(function() {
	$.ajax({
		url : "getHtmlTypeIdName.action",
		type : "post",
		data : "",
		dataType : "json",
		success : function(data) {
			var option = $("<option value=''>请选择类型</option>");
			$("#add_htmlType").append(option);
			var typeIds=data.htmlTypeId.split(",");
			var typeNames=data.htmlTypeName.split(",");
			for(var i=0;i<typeIds.length-1;i++){
				option = $("<option value='"+typeIds[i]+"'>"+typeNames[i]+"</option>");
				$("#add_htmlType").append(option);
			}
		},
		error : function(data) {
			alert("error 后台出现错误！");
		}
	});
	
	
	$("#addHtml").click(function() {
		var htmlType=$("#add_htmlType").val();
		if(htmlType==''){
			alert("请选择信息类型");
			return false;
		}
		var htmlContent = editor1.html();
		if (editor1.isEmpty()) {
	        alert('公告内容不能为空！');
	        return false;
	    }
		htmlContent=encodeURIComponent(htmlContent);
		var htmlTitle = $("#add_htmlTitle").val().trim();
		if (htmlTitle == '') {
			alert("公告标题不能为空！");
			return false;
		}
		var htmlDes=$("#add_htmlDes").val();
		$.ajax({
			url : "addHtml.action",
			data : "htmlType=" + htmlType + "&htmlContent=" + htmlContent +"&htmlTitle="+htmlTitle
					+"&htmlDes="+htmlDes,
			dataType : "json",
			type : "post",
			success : function(data) {
				if(data.respCode==0){
					alert(data.respInfo);
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

</script>
</head>
<body>
<div class="list_h">
<div class="list_m">
        <div class="content" style="text-align:center;">
                <div class="top_bd">  <h2>添加信息页面</h2>
        <p class="news_times">绍兴交警 </p></div>
        <table style="margin-left:60px;">
	<tbody>
		<tr>
			<td width="156">
				信息类型：
			</td>
			<td width="354">
				<select id="add_htmlType" style="width: 100%;">
					</select>
			</td>
			<td width="156">
				<font color="red">&nbsp;*&nbsp;必填</font>
			</td>
		</tr>
		
		
		<tr>
			<td width="156">
				信息标题：
			</td>
			<td width="354">
				<input type="text" id="add_htmlTitle" name="htmlTitle" style="width: 99%;" />
			</td>
			<td width="156">
				<font color="red">&nbsp;*&nbsp;必填</font>
			</td>
		</tr>
		
		
		
		<tr>
			<td width="156">
				信息描述：
			</td>
			<td width="354">
				<input type="text" id="add_htmlDes" name="htmlDes" style="width: 99%;" />
			</td>
			<td width="156">
				<font color="red">&nbsp;*&nbsp;必填</font>
			</td>
		</tr>
		
		<tr>
			<td width="156">
				信息内容：
			</td>
			<td width="354" style="text-align:center;vertical-align:middle; ">
				<textarea  id="add_htmlContent" name="htmlContent" style=""></textarea>
			</td>
			<td width="156">
				<font color="red">&nbsp;*&nbsp;必填</font>
			</td>
		</tr>
		
		
		
	</tbody>
</table>
<div align="center" style="margin-top:20px;">
			<input type="button" value="完成" id="addHtml" />
		</div>
            
        </div>
    </div>

  
  
</div>
</body>
</html>

