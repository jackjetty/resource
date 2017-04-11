<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<title>意见建议</title>  
<link href="${pageContext.request.contextPath}/js/artDialog/skins/chrome.css" rel="stylesheet" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap/bootstrap-responsive.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap/style.css" />
<link rel="stylesheet" type="text/css" media="all" href="${pageContext.request.contextPath}/css/daterangepicker/daterangepicker-bs3.css" />
<script src="${pageContext.request.contextPath}/js/jquery-1.9.0.min.js" type="text/javascript"></script>  
<script src="${pageContext.request.contextPath}/js/lhgdialog/lhgdialog.min.js?skin=iblue" type="text/javascript"></script> 
<script src="${pageContext.request.contextPath}/js/artDialog/artDialog.js"></script>
<script src="${pageContext.request.contextPath}/js/artDialog/plugins/iframeTools.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap/ckform.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap/common.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/js/daterangepicker/moment.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/daterangepicker/daterangepicker.js"></script>
<style type="text/css">
        body {
            padding-bottom: 40px;
        }
        .sidebar-nav {
            padding: 9px 0;
        }

        @media (max-width: 980px) {
            /* Enable use of floated navbar text */
            .navbar-text.pull-right {
                float: none;
                padding-left: 5px;
                padding-right: 5px;
            }
        } 
        a{
cursor:hand;}
 </style>
</head>
<body>
<form id="myForm" class="form-inline definewidth m20" action="${pageContext.request.contextPath}/web/feedBackPage.action" method="post"> 
<fieldset>  
   
      日期:   <input type="text" name="dateFrom"  readonly="readonly" style="width: 100px"  id="edit_dateFrom" class="form-control" value="<s:property value="#request.result['dateFrom']"/>" />
        
           ～      <input type="text" name="dateEnd"  readonly="readonly" style="width: 100px"  id="edit_dateEnd" class="form-control" value="<s:property value="#request.result['dateEnd']"/>" />    
                 
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <input type='hidden' name='pageIndex' value="<s:property value="#request.result['pageIndex']"/>" />
    <button type="submit" class="btn btn-primary">查询</button> 
     
 </fieldset>
</form>
<table class="table table-bordered table-hover definewidth m10" >
    <thead>
    <tr>
        <th>编号</th>
        <th>微信昵称</th>
        <th>时间</th>
        <th>意见建议</th> 
        <th>操作</th>
    </tr>
    </thead>
    
    <s:iterator value="#request.result['jsFeedBackList']" id="jsFeedBack">
        <tr>
            <td><s:property value='feedBackId' /></td>
            <td><s:property value='nickname' /></td> 
            <td><s:property value='feedTime' /></td>
            <td><s:property value='feedText' /></td> 
            <td>
               <s:if test="#jsFeedBack.feedBackState.equalsIgnoreCase('已回复')">
                           <a onclick="fnFeedBackChatPage(<s:property value='feedBackId' />)">查看</a>   
               </s:if>
               <s:else>
                           <a onclick="fnFeedBackChatPage(<s:property value='feedBackId' />)" >回复</a>   
              </s:else>
            </td>
        </tr>
                    
                    
		              
	 </s:iterator> 
    
	     
</table>
  <div class="inline pull-right page">
         <s:property value="#request.result['recordSum']"/> 条记录     <s:property value="#request.result['pageIndex']"/>/<s:property value="#request.result['pageSum']"/> 页  
         
          <s:if test="#request.result['pageIndex']>1">
             <a class="btn btn-default disabled" onclick="fnForwardPage(<s:property value="#request.result['pageIndex']"/>)" >&lt; </a>
                  
          </s:if>
          <s:if test="#request.result['pageIndex']<#request.result['pageSum']">
               <a class="btn btn-default disabled" onclick="fnAfterPage(<s:property value="#request.result['pageIndex']"/>)" >&gt;</a>
                 
          </s:if>
          
          <s:if test="#request.result['pageIndex']!=#request.result['pageSum']">
           <a class="btn btn-default disabled" onclick="fnGoPage(<s:property value="#request.result['pageSum']"/>)">》</a>
                 
          </s:if>                    
  </div>
</body>
</html>
<script>
    $(function () {
        
		 


    }); 
    
    
    function fnFeedBackChatPage(feedBackId){
    	
    	
    	 art.dialog.open("${pageContext.request.contextPath}/web/feedBackChatPage.action?feedBackId="+feedBackId, {width: 340, height: 480});
    	 
    }
    
    function fnForwardPage(currPage){
    	 
    	fnGoPage(currPage-1);
    	
    	
    }
    function fnAfterPage(currPage){
    	fnGoPage(currPage+1);
    	
    }
    function fnGoPage(indexPage){
    	
    	
    	 
    	$("#myForm input[name=pageIndex]:first").val(indexPage);
    	
    	$("#myForm").submit();
    }
    
    $(document).ready(function() {
       $('#edit_dateFrom,#edit_dateEnd').daterangepicker({ singleDatePicker: true }, function(start, end, label) {
         console.log(start.toISOString(), end.toISOString(), label);
       });
    }); 

	function del(id)
	{ 
		if(confirm("确定要删除吗？"))
		{ 
			var url = "index.html"; 
			window.location.href=url;		
		
		} 
	
	}
</script>

