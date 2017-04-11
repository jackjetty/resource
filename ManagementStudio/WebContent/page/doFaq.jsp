<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>通用问题答复管理</title>
<link rel="stylesheet" type="text/css" href="themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="themes/icon.css">
<script type="text/javascript" src="js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/easyui-lang-zh_CN.js"></script> 
<style type="text/css">
<!--
 a.pushBtn {
    -moz-user-select: none;
    background: url("images/button/btnout_bg_left.gif") no-repeat scroll left top transparent;
    float: left;
    height: 24px;
    margin: 2px 5px 0 0;
    outline: medium none;
    text-decoration: none;
    
}
 a.pushBtn img {
    float: left;
    margin: 2px 0 4px 6px;
    border:0;
}
 a.pushBtn b {
    background: url("images/button/btnout_bg_right.gif") no-repeat scroll right top transparent;
    color: #666666;
    cursor: pointer;
    display: inline-block;
    font-size: 12px;
    font-weight: normal;
    height: 24px;
    line-height: 24px;
    padding: 0 7px 0 5px;
    white-space: nowrap;
}
 a.pushBtn:hover {
    background: url("images/button/btnover_bg_left.gif") no-repeat scroll left top transparent;
    text-decoration: none;
}
 a.pushBtn:hover b {
    background: url("images/button/btnover_bg_right.gif") no-repeat scroll right top transparent;
    color: #114477;
    font-size: 12px;
}
#div1 td{
	height: 35px;
}

.norA
 {
    text-decoration:underline; color:#000000; 
 }
.abNorA
  {
      text-decoration:underline;color:#EE6353; 
  } 
  
#dd table {
	background-color: #DDDDDD;
	align:"center";
    valign:"middle";
    width:100%;
    border: 0px solid #DFDFDF;
}

#dd table tr {
	background-color: #ffffff;
	line-height: 24px;
}
.left_title {
	background-color: #F5F7F7;
	 
	text-align: left
}
.right_title{
	background-color: #F5F7F7; 
	/*line-height:24px;*/
	text-align: right;
}
.input_text{
    BORDER-BOTTOM: 1px solid #7f9db9;
    BORDER-LEFT: 0px solid #000000;
	BORDER-RIGHT: 0px solid #000000;
	BORDER-TOP: 0px solid #000000;
}
-->
</style>
</head>
<body style="background: #DFE8F6;" >
     <div id="searchpanel"class="easyui-panel panel-margin-buttom" title="问题答复查询"
		style="height: 80px; padding: 10px;">	
		<div style="float:left;vertical-align: center;height:31px;line-height:31px;">
		                            <span style="margin-left:10px;">当前状态:</span> 
				                    <select   id='cdtShow' style="margin-left:10px;width:80px;">
				                              <option value="" >全部</option>
				                              <option id="xianshi" value="显示" selected >显示</option>  
				                              <option value="隐藏" >隐藏</option>   
				                    </select>
									<span style="margin-left:10px;">标题:</span> 
									<input id="cdtTitle" name="title" style="margin-left:10px;width: 100px;" />
									<span style="margin-left:10px;"> 内容:</span>  
								    <input id="cdtContent" name="content" style="margin-left:10px;width:100px;" />
			</div>						
			<div style="height:100%;float:left;margin-left: 15px;">
						<a id="search" class="pushBtn" href="javascript:void(0);" hidefocus="true">
							<img width="20" height="20" src="images/icon/search.gif">
							<b>查询</b>
						</a><a id="clear" class="pushBtn" href="javascript:void(0);" hidefocus="true">
				<b>条件清空</b>
			</a>
			</div>
		</div>
	 <div style="height: 2px; padding: 10px;"></div>
	 <table id="tt" style="align:center" ></table>
	 
	 <div id="dd"   
		        style="width: 420px; height: 270px;  padding:5px  ">
		        <table cellpadding="4" cellspacing="1" align="center"   >
		               <tr>
			               <td class="left_title" width="20%">帮助标题:</td>
			               <td><input type="text" name="title"  class="input_text" style="width:85%"/></td>
			           </tr>
			           <tr>
			               <td class="left_title"  >帮助内容:</td>
			               <td>
			                     <textarea  name="content" style="width:85%;height:100px;font-family:'宋体';font-size: 13px;" wrap="virtual"> </textarea>
			               </td>
			           </tr> 
			           <tr>
			               <td class="left_title"  >当前状态:</td>
			               <td><input type="checkbox" name="show" />显示</td>
			           </tr> 
			           <tr> 
			               <td  class="right_title" colspan='2'>  
			               <a href='#' id='cmdSave'></a>
			               &nbsp;&nbsp; 
			               <a href='#' id='cmdCancel'></a> 
			               <input type="hidden" name="id"   /></td>
			           </tr> 
		        </table>
		   </div>
	</body>
</html>
<script>  
	 $(function(){ 
		 $("#clear").click(function(){
				$("#xianshi").attr('selected',true);
				$("#cdtTitle").val('');
				$("#cdtContent").val('')
				
			});
		  $('#cmdSave').linkbutton({
		        text:'保存',
		        iconCls:"icon-ok",
		        plain:true
		    });
			$('#cmdCancel').linkbutton({
			       text:'取消',
			       iconCls:"icon-cancel",
			       plain:true
			}); 
			$('#cmdCancel').click(function(){
				$('#dd').dialog('close');
			})
			 
			$('#cmdSave').click(function(){
				var id=$('#dd input[name=id]').val();
				var title=$('#dd input[name=title]').val();
				var content=$('#dd textarea[name=content]').val(); 
				var show="隐藏";
				if($('#dd input[name=show]:first').is(":checked"))
					show="显示"; 
				$.ajax({
					url : "doUpdateFaq.action",
					async : true,
					data : {id:id,title:title,content:content,show:show},
					dataType : "json",
					type : "post",
					success : function(data) {
						 if(data.success){
							 alert(data.info); 
							 fnRfTable();
							 $('#dd').dialog('close');
							 return ;
						 }   
					},
					error : function(data) {
						alert("error 后台出现错误！");
					}
				});
			})
		  $('#dd').dialog({   
			    title:"问题答复详情",
			    closed:true, 
			    modal:true,
			    onOpen:function(){
			    	$('#dd input[name=title]').focus();
			    }

			  });
			$('#dd').dialog('close');
		  // open
 
	 }) 
	 
</script>

<script language="javascript">
function RfOp1(value,row,index)
{ 
	var show=row.show;  
	var btn1=""; 
	var btn2="";
	var btn3="";
	btn1="<a href='#' onClick='fnEdit(\""+row.id+"\")' ";
	btn2="<a href='#' onClick='fnUpRank(\""+index+"\")' ";
	btn3="<a href='#' onClick='fnDownRank(\""+index+"\")' ";
	if(show=="显示"){
		btn1+="   class='norA'  ";
		btn2+="   class='norA'  ";
		btn3+="   class='norA'  ";
	}      	
	else {
		btn1+="   class='abNorA'  ";
		btn2+="   class='abNorA'  ";
		btn3+="   class='abNorA'  ";
	}        	
	btn1+=">记录编辑</a>";
	btn2+=">排列向上</a>";
	btn3+=">排列向下</a>"; 
    return  btn2+" | "+btn1+" | "+btn3 ;
} 
function fnUpRank(curRowNum){ 
	curRowNum=parseInt(curRowNum);
	if(curRowNum==0){
		alert("已经排列在顶端了,请确认！！");
		return;
	}
	var rows=$("#tt").datagrid('getRows'); 
	var repRowNum=curRowNum-1;
	var curRowId,repRowId;
	var field=rows[curRowNum];
	curRowId=field.id;
	field=rows[repRowNum];
	repRowId=field.id; 
	fnReplaceRank(curRowId,repRowId);  
}
function fnDownRank(curRowNum){
	curRowNum=parseInt(curRowNum);
	var rows=$("#tt").datagrid('getRows'); 
	if(curRowNum==rows.length-1){
		alert("已经排列在最后了,请确认！！");
		return;
	}
	var repRowNum=curRowNum+1;
	var curRowId,repRowId;
	var field=rows[curRowNum];
	curRowId=field.id;
	field=rows[repRowNum];
	repRowId=field.id; 
	fnReplaceRank(curRowId,repRowId); 
}
function fnReplaceRank(curRowId,repRowId){
	$.ajax({
		url : "doReplaceFaqRand.action",
		async : true,
		data : {curRowId:curRowId,repRowId:repRowId}  ,
		dataType : "json",
		type : "post",
		success : function(data) {
			 if(!data.success){
				 alert(data.info);
				 return ;
			 }else{
				 fnRfTable(); 
			 }  
		},
		error : function(data) {
			alert("error 后台出现错误！");
		}
	});
}
function fnEdit(id){
	//var urlStr='url:<c:out value="${pageContext.request.contextPath}" />/tip/dir/dir.jsp?typeId='+typeId+"&dirId="+dirId+"&showValue="+showValue+"&realValue="+realValue+"&remark="+remark; 
	//urlStr=encodeURI(urlStr);
    //urlStr=encodeURI(urlStr);
    
    $.ajax({
		url : "doFaqById.action",
		async : true,
		data :{id:id}  ,
		dataType : "json",
		type : "post",
		success : function(data) {
			 if(!data.success){
				 alert(data.info);
				 return ;
			 } 
			 var faq=data.faq;
			 $('#dd input[name=id]').val(faq.id);
			 $('#dd input[name=title]').val(faq.title);
			 $('#dd textarea[name=content]').val(faq.content); 
			 if(faq.show=="显示"){
				 $('#dd input[name=show]:first').attr("checked",true);
			 }
			 else{
				 $('#dd input[name=show]:first').attr("checked",false);
			 } 
			 $('#dd').dialog('open');
			  
		},
		error : function(data) {
			alert("error 后台出现错误！");
		}
	});
    
    
    
}
</script> 
<script language="javascript"> 
    $(function(){
       /* $('#search').linkbutton({
            text:'查询',
            iconCls:"icon-search",
            plain:true
        });*/
        $('#search').click(function(){ 
        	 var  cdtShow= $("#cdtShow").val();  
             var  cdtTitle= $.trim($("#cdtTitle").val());
             var  cdtContent=$.trim($("#cdtContent").val());  
             var  query={show:cdtShow,title:cdtTitle,content:cdtContent};  
             $("#tt").datagrid('options').queryParams=query;  
             $("#tt").datagrid('load');
             $('#tt').datagrid('clearSelections'); 
        }); 
    });  
    $(function(){ 
		    $('#cdtShow').change(function(){
		    	$('#search').click();
		    });
    }); 
    $(function(){ 
    	var width = $(document).width();
		var height = $(document).height();
        $('#tt').datagrid({
            title:'常见问题管理', 
            //fit:true, 
            rownumbers:true,
            width:width-18,
			height:height-120, 
            nowrap: true,
            autoRowHeight: false,
            striped: true,
            collapsible:false,
            pageList:[20,50,100,200],
            url:'doFaqPageData.action', 
            queryParams:{show:$("#cdtShow").val()},
            sortName: 'rank',
            sortOrder: 'asc',
            remoteSort: true,
            idField:'id', 
            singleSelect:false, 
            fitColumns:true,
			multiSort:true,
            frozenColumns:[[
                    {title:'编号',field:'id',align:'center',width:100,sortable:false,hidden:true},
                    {title:'标题',field:'title',align:'center',width:fixWidth(0.15),sortable:false,hidden:false}
                ]],
            columns:[[  
					{title:'内容',field:'content',align:'center',width:fixWidth(0.57),sortable:false}, 
					{title:'当前状态',field:'show',align:'center',width:fixWidth(0.09),sortable:false},   
                    {field:'rank',title:'排列序号',align:'center',width:fixWidth(0.09),sortable:true,hidden:true },
                    {field:'remark',title:'备注',align:'center',width:100,hidden:true },
                    {title:'操作',field:'op1',align:'center',width:fixWidth(0.15),formatter:RfOp1}  
                ]], 
            pagination:true,
            toolbar: [ {
    			text : '添加',
    			iconCls : 'icon-add',
    			handler : function() {
    				$('#dd input[name=id]').val("");
    				$('#dd input[name=title]').val("");
    				$('#dd textarea[name=content]').val(""); 
    				$('#dd input[name=show]:first').attr("checked",false);
    				$('#dd').dialog('open');  
    			}
    		}],
            rowStyler:function(index,row){  
            	if (row.show.indexOf("隐藏")>=0){ 
            		return 'color:#EE6353;';
                }
            	 
            },
            onClickRow:function(rowIndex){  
	        },
	        onDblClickRow: function(rowIndex, field, value) { 
	        	fnEdit(field.id);
	        } 
        });
        var p = $('#tt').datagrid('getPager');
        $(p).pagination({
            displayMsg:'当前显示从{from}到{to}共{total}记录',
            onBeforeRefresh:function(pageNumber, pageSize){
                $(this).pagination('loading'); 
                $(this).pagination('loaded');
            } 
        }); 
    });  
	</script>
	
	<script language="javascript"> 
		function fnRfTable(){
			var  cdtShow= $("#cdtShow").val();  
            var  cdtTitle= $.trim($("#cdtTitle").val());
            var  cdtContent=$.trim($("#cdtContent").val());  
            var  query={show:cdtShow,title:cdtTitle,content:cdtContent};  
            $("#tt").datagrid('options').queryParams=query;
	    	$("#tt").datagrid('reload');
	        $('#tt').datagrid('clearSelections'); 
	    } 
		function fixWidth(percent)  
		{    
		    return $(window).width()* percent; //这里你可以自己做调整  
		} 
	</script>
	 
