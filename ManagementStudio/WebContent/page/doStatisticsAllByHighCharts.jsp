<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link rel="stylesheet" type="text/css" href="themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="themes/icon.css">
<script type="text/javascript" src="js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="js/highcharts.js"></script>
<script type="text/javascript" src="js/exporting.js"></script>

<style type="text/css">
<!--
.panel-margin-buttom {
	margin-bottom: 20px;
}

#searchpanel a.pushBtn {
    -moz-user-select: none;
    background: url("images/button/btnout_bg_left.gif") no-repeat scroll left top transparent;
    float: left;
    height: 24px;
    margin: 4px 5px 0 0;
    outline: medium none;
    text-decoration: none;
    
}
#searchpanel a.pushBtn img {
    float: left;
    margin: 2px 0 4px 6px;
    border:0;
}
#searchpanel a.pushBtn b {
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
#searchpanel a.pushBtn:hover {
    background: url("images/button/btnover_bg_left.gif") no-repeat scroll left top transparent;
    text-decoration: none;
}
#searchpanel a.pushBtn:hover b {
    background: url("images/button/btnover_bg_right.gif") no-repeat scroll right top transparent;
    color: #114477;
    font-size: 12px;
}
-->
</style>
</head>
<body style="background: #DFE8F6;">
<div id="searchpanel" class="easyui-panel panel-margin-buttom"
		title="交易信息统计" style="height: 120px; padding: 5px;overflow: hidden;">
		<form action="getStatisticsAll.action" method="post" id="form1" style="height:100%;">
		<table height="100%">
			<tr	style="Valign: center; text-decoration: none; line-heigth: 30px; height: 30px; margin-left: 50px;">
			<td style="font-size:12px;">展现方式：</td>
				<td><select id="ways" name="ways" style="width: 95px; height: 23px;">
				<option value="0">请选择</option>
				<option value="1">按年</option>
				<option value="2">按月</option>
				<option value="3">按周</option>
				<option value="4">按日</option>
								
				</select></td>
				<td style="font-size: 12px;">开始时间：</td>
				<td><input type="text" id="startTime" name="startTime" class="easyui-datebox" editable="false"
					style="width: 95px;" /></td>
				<td style="font-size: 12px;">结束时间：</td>
				<td><input type="text" id="endTime" name="endTime" class="easyui-datebox" editable="false"
					style="width: 95px;" /></td></tr>
				<tr>
				<td style="font-size:12px;">业务种类：</td>
				<td><select id="busId" name="busId" style="width: 95px; height: 23px;">				
				</select></td>
				<td >产品种类：</td>
				<td><select id="productId" name="productId"style="width: 150px; height: 23px;">
				</select></td>
				<td style="font-size:12px;">图表样式：</td>
				<td><select id="figure" name="figure" style="width: 95px; height: 23px;">
				<option value="条形图">条形图</option>
				<option value="折线图">折线图</option>				
				</select></td>
				<td><a id="search" class="pushBtn" href="javascript:void(0);" hidefocus="true">
				<img width="20" height="20" src="images/icon/search.gif">
				<b>查询</b>
			</a></td>
			<td><a id="exchange" class="pushBtn" href="javascript:void(0);" hidefocus="true">
				<b>切换至全省模式</b>
			</a></td>
			<td><a id="clear" class="pushBtn" href="javascript:void(0);" hidefocus="true">
				<b>条件清空</b>
			</a></td>
			</tr>
		</table>
		</form>
	</div>
	<div id="container" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
</body>
<script type="text/javascript">
	String.prototype.trim = function() {
		  return this.replace(/^\s\s*/, '').replace(/\s\s*$/, '');
		};
		var sTime = '${startTime}';
		var eTime = '${endTime}';
		$("#startTime").val(sTime);
		$("#endTime").val(eTime);
		var busId = '${busId}';
		$(function() {
			$.ajax({
				url : "getBusinessType.action",
				type : "post",
				async : false,
				data : "",
				dataType : "json",
				success : function(data) {
					for(var i=0;i<data.length;i++){
						if(data[i].busId == busId){
							option = $("<option value='"+data[i].busId+"' selected='true'>"+data[i].btype+"</option>");
						}else{
							option = $("<option value='"+data[i].busId+"'>"+data[i].btype+"</option>");
							busId = data[0].busId;
						}
						$("#busId").append(option);
					}
				},
				error : function(data) {
					alert("error 后台出现错误！");
				}
			});
			$.ajax({
				url : "getProduct.action",
				type : "post",
				async : false,
				data : "busId="+busId,
				dataType : "json",
				success : function(data) {
					$("#productId").html("");
					for(var i=0;i<data.length;i++){
						option = $("<option value='"+data[i].productId+"' selected='true'>"+data[i].productDescribe+"</option>");
						$("#productId").append(option);
					}
				},
				error : function(data) {
					alert("error 后台出现错误！");
				}
			});
			var width = $(document).width();
			var height = $(document).height();
			
			$("#display").datagrid({
				width:width-20,
				height:height-120
			});
			$("#searchpanel").panel({
				width : width - 20
			});
			$("#exchange").click(function() {
				$("#form1").attr('action','doStatistics.action');
				$("#form1").submit();
			});
			var pbusId = "null";
			$("#busId").change(function(){
				var id = $(this).val();
				if(pbusId == id || id=='null' ){
					if(id=='null'){
						$("#productId").html("");
					}
					pbusId = id;
					return false;
				}else{
					pbusId = id;
					$.ajax({
						url : "getProduct.action",
						type : "post",
						async : false,
						data : "busId="+id,
						dataType : "json",
						success : function(data) {
							$("#productId").html("");
							for(var i=0;i<data.length;i++){
								option = $("<option value='"+data[i].productId+"' selected='true'>"+data[i].productDescribe+"</option>");
								$("#productId").append(option);
							}
						},
						error : function(data) {
							alert("error 后台出现错误！");
						}
					});
				}
			});
			$("#search").click(function() {
				var ways = $("#ways").val();
				var endTime = $("#endTime").datebox('getValue');
				var startTime = $("#startTime").datebox('getValue');
				var startYear = startTime.substring(0,startTime.indexOf ('-'));
				var endYear = endTime.substring(0,endTime.indexOf ('-'));
				var startMonth = startTime.substring(5,startTime.lastIndexOf ('-'));
				var endMonth = endTime.substring(5,endTime.lastIndexOf ('-'));
				var startDay = startTime.substring(startTime.length,startTime.lastIndexOf ('-')+1); 
				var endDay = endTime.substring(endTime.length,endTime.lastIndexOf ('-')+1); 
				var differYear=parseInt(endYear)-parseInt(startYear);
				var differMonth=parseInt(endMonth)-parseInt(startMonth);
				var differ=((Date.parse(endMonth+'/'+endDay+'/'+endYear)- Date.parse(startMonth+'/'+startDay+'/'+startYear))/86400000);
				if(ways=='1'){
					if(differYear<1){
						alert("请保证时间相隔一年以上");
						return false;
					}
					if(differYear>10){
						alert("请保证时间相隔十年以下");
						return false;
					}
				}
				if(ways=='2'){
					if(differYear!=0&&differMonth>0){
						alert("请保证时间相隔十二个月以下");
						return false;
					}
					if(differYear==0&&differMonth<1){
						alert("请保证时间相隔一个月以上");
						return false;
					}
				}
				if(ways=='3'){
					if(differ<7){
						alert("请保证时间相隔一周以上");
						return false;
					}
					if(differ>35){
						alert("请保证时间相隔五周以下");
						return false;
					}
				}
				if(ways=='4'){
					if(differ>10){
						alert("按日查询的话请在10日之内");
						return false;
					}
					if(differ<2){
						alert("按日查询的话请在一日以上");
						return false;
					}
				}
				$("#startTime").val(startTime);
				$("#endTime").val(endTime);
				var ways=$("#ways").find("option:selected").text();
				var figure=$("#figure").find("option:selected").text();
				var rows = getJSON();
				var productDescribe = "";
		    	var allPay = new Array();
		    	var paySuccess = new Array();
		    	var payFailed = new Array();
		    	var allPayMoney = new Array();
		    	var allPayPhoneNumbers = new Array();
		    	var paySuccessPhoneNumbers = new Array();
		    	var payFailedPhoneNumbers = new Array();
		    	var year = new Array();
		    	for(var i=0;i<rows.length;i++){
		    		productDescribe = rows[0].productDescribe;
		    		allPay[i] = rows[i].allPay;
		    		paySuccess[i] = rows[i].paySuccess;
		    		payFailed[i] = rows[i].payFailed;
		    		allPayMoney[i]=rows[i].allPayMoney;
		    		allPayPhoneNumbers[i] = rows[i].allPayPhoneNumbers;
		    		paySuccessPhoneNumbers[i] = rows[i].paySuccessPhoneNumbers;
		    		payFailedPhoneNumbers[i] = rows[i].payFailedPhoneNumbers;
		    		year[i]=rows[i].year;
					
				} 
		    	
		    	if(ways=="请选择"){
		    		var allPay1 = allPay[0];
					var paySuccess1 = paySuccess[0];
					var payFailed1 = payFailed[0];
					var allPayMoney1 = allPayMoney[0];
					var allPayPhoneNumbers1 = allPayPhoneNumbers[0];
					var paySuccessPhoneNumbers1 = paySuccessPhoneNumbers[0];
					var payFailedPhoneNumbers1 = payFailedPhoneNumbers[0];
					if(figure=="条形图"){
				            $(document).ready(function() {
							new Highcharts.Chart({
							chart: {
								renderTo: 'container',
								defaultSeriesType: 'column',
										},
								lang:{ 
									           downloadJPEG: "下载JPEG 图片",  
								               downloadPDF: "下载PDF文档",
									           downloadPNG: "下载PNG 图片",  
									           downloadSVG: "下载SVG 矢量图" , 
									           exportButtonTitle: "导出图片",
									           printButtonTitle:"打印图表"
									},
							credits: {             
											enabled: false,
											}, 
				             title: {
				                 text: '交易信息统计',
				             },
				             subtitle: {
				                 text: '168充值宝',
				                 style:{color: '#4876FF',
										fontSize: '12px'}
				             },
				             xAxis: {
				                 categories: 
				                      [productDescribe],
				                      
				                 
				             },
				             yAxis: {
				                 min: 0,
				                 title: {
				                     text: '交易信息数据量',
				                 }
				             },exporting:{  
					                filename:'chart',  
					                url:'toDownloadImage.action'
					            },  
				             tooltip: {
				            	 formatter: function() {
										return '<b>'+ this.series.name +'</b><br/>'+
											this.x +': '+ this.y ;
									}
				             },
				             plotOptions: {
				                 column: {
				                     pointPadding: 0.1,
				                     borderWidth: 0
				                 }
				             },
				             series: [{
				                 name: '总计支付笔数',
				                 data: allPay,
				                 visible:false
				     
				             }, {
				                 name: '支付成功笔数',
				                 data: paySuccess,
				                 visible:false
				     
				             }, {
				                 name: '支付失败笔数',
				                 data: payFailed,
				                 visible:false
				             }, {
				                 name: '总计支付金额',
				                 data: allPayMoney,
				     
				             }, {
				                 name: '总计支付号码数',
				                 data: allPayPhoneNumbers,
				                 visible:false
				             }, {
				                 name: '成功支付号码数',
				                 data: paySuccessPhoneNumbers,
				                 visible:false
				             }, {
				                 name: '支付失败号码数',
				                 data: payFailedPhoneNumbers,
				                 visible:false
				             }]
				             
				         });
							
							
				        });
					}
					
					
					if(figure=="折线图"){
							chart = new Highcharts.Chart({
								chart: {
									renderTo: 'container',
									defaultSeriesType: 'line',
									zoomType:'x',
								},
								lang:{ 
							           downloadJPEG: "下载JPEG 图片",  
						               downloadPDF: "下载PDF文档",
							           downloadPNG: "下载PNG 图片",  
							           downloadSVG: "下载SVG 矢量图" , 
							           exportButtonTitle: "导出图片",
							           printButtonTitle:"打印图表"
							},
							credits: {             
								enabled: false,
								}, 
				             title: {
				                 text: '交易信息统计',
				             },
				             subtitle: {
				                 text:  '168充值宝',
				                 style:{color: '#4876FF',
										fontSize: '12px'}
				             },
				             xAxis: {
				                 categories: ['总计支付笔数','支付成功笔数','支付失败笔数','总计支付金额','总计支付号码数','成功支付号码数','支付失败号码数'],
				                 tickmarkPlacement: 'on',
				                
				             },
				             yAxis: {
				                 title: {
				                     text: '交易信息数据量',
				                 },exporting:{  
						                filename:'chart',  
						                url:'toDownloadImage.action',
						            } 
				             },
				             plotOptions:{ 
           				        line:{                 
           				        	dataLabels:{                     
           				        		enabled:true,  
           				        		},                 
           				        		enableMouseTracking: false ,
           				        		}        
				             }, 
				             legend: {
									layout: 'horizontal',
									backgroundColor: '#FFFFFF',
									style: {
										left: 'auto',
										top: 'auto',
										bottom: '10px'
									},
									shadow:true
					            	
								},
				             tooltip: {
				            	 formatter: function() {
										return '<b>'+ this.series.name +'</b><br/>'+
											this.x +': '+ this.y ;
									}
				             },
				             series: [{
				                 name: productDescribe,
				                 data: [allPay1,paySuccess1,payFailed1,allPayMoney1,allPayPhoneNumbers1,paySuccessPhoneNumbers1,payFailedPhoneNumbers1],
				             }]
				         });
					
					}
						
					
				
		    	}else{
						if(figure=="条形图"){
					             $(document).ready(function() {
										new Highcharts.Chart({
											chart: {
												renderTo: 'container',
												defaultSeriesType: 'column',
											},
											lang:{ 
										           downloadJPEG: "下载JPEG 图片",  
									               downloadPDF: "下载PDF文档",
										           downloadPNG: "下载PNG 图片",  
										           downloadSVG: "下载SVG 矢量图" , 
										           exportButtonTitle: "导出图片",
										           printButtonTitle:"打印图表"
										},
											credits: {             
												enabled: false,
												}, 
											title: {
					                 text: '交易信息统计'
					             },
					             subtitle: {
					                 text: '168充值宝',
					                 style:{color: '#4876FF',
											fontSize: '12px'}
					             },
					             xAxis: {
					                 categories: year,
					                 tickmarkPlacement: 'on',
					                 
					             },
					             yAxis: {
					                 min: 0,
					                 title: {
					                     text: '交易信息数据',
					                 }
					             },exporting:{  
						                filename:'chart',  
						                url:'toDownloadImage.action'
						            },  
					             tooltip: {
					            	 formatter: function() {
											return '<b>'+ this.series.name +'</b><br/>'+
												this.x +': '+ this.y ;
										}
					             },
					             plotOptions: {
					                 column: {
					                     pointPadding: 0.2,
					                     borderWidth: 0
					                 }
					             },
					             series: [{
					                 name: '总计支付笔数',
					                 data: allPay,
					                 visible:false
					     
					             }, {
					                 name: '支付成功笔数',
					                 data: paySuccess,
					                 visible:false
					     
					             }, {
					                 name: '支付失败笔数',
					                 data: payFailed,
					                 visible:false
					     
					             }, {
					                 name: '总计支付金额',
					                 data: allPayMoney,
					     
					             }, {
					                 name: '总计支付号码数',
					                 data: allPayPhoneNumbers,
					                 visible:false
					             }, {
					                 name: '成功支付号码数',
					                 data: paySuccessPhoneNumbers,
					                 visible:false
					             }, {
					                 name: '支付失败号码数',
					                 data: payFailedPhoneNumbers,
					                 visible:false
					             }]
					         });
					             });
								}
								if(figure=="折线图"){
									$(document).ready(function() {
										new Highcharts.Chart({
											chart: {
												renderTo: 'container',
											},
										lang:{ 
										           downloadJPEG: "下载JPEG 图片",  
									               downloadPDF: "下载PDF文档",
										           downloadPNG: "下载PNG 图片",  
										           downloadSVG: "下载SVG 矢量图" , 
										           exportButtonTitle: "导出图片",
										           printButtonTitle:"打印图表"
										},
											credits: {             
												enabled: false,
												}, 
											title: {
							                 text: '交易信息统计',
							             },
							             subtitle: {
							                 text: '168充值宝',
							                 style:{color: '#4876FF',
													fontSize: '12px'}
							             },
							             xAxis: { categories: year,
							            	 tickmarkPlacement: 'on',
							            	 
							             },
							             yAxis: {
							                 title: {
							                     text: '交易信息数据',
							                     style:{color: '#4876FF',
														fontSize: '15px'}
							                 },
							                 plotLines: [{
							                     value: 0,
							                     width: 1,
							                     color: '#808080'
							                 }]
							             },
							             exporting:{  
								                filename:'chart',  
								                url:'toDownloadImage.action'
								            },  
							             tooltip: {
							                 valueSuffix: ''
							             },
							             legend: {
												layout: 'horizontal',
												backgroundColor: '#FFFFFF',
												style: {
													left: 'auto',
													top: 'auto',
													bottom: '10px'
												},
												shadow:true,
								            	
											},
							             series: [{
							                 name: '总计支付笔数', 
							                 data: allPay,
							                 visible:false
							             },{
							            	 name:'支付成功笔数',
							            	 data:paySuccess,
							            	 visible:false
							             },{
							            	 name:'支付失败笔数',
							            	 data:payFailed,
							            	 visible:false
							             },{
							            	 name:'总计支付金额',
							            	 data:allPayMoney,
							             },{
							            	 name:'总计支付号码数',
							            	 data:allPayPhoneNumbers,
							            	 visible:false
							             },{
							            	 name:'成功支付号码数',
							            	 data:paySuccessPhoneNumbers,
							            	 visible:false
							             },{
							            	 name:'支付失败号码数',
							            	 data:payFailedPhoneNumbers,
							            	 visible:false
							             }]
							         });
									 });
								}
					}
			 
			});
			
			$("#clear").click(function(){
				var today = new Date();
				var year = today.getFullYear();
				var month = today.getMonth() + 1;
				var day = today.getDate();
				if (month<10){month="0"+month;}
				if (day<10){day="0"+day;}
				var startdate = year+"-"+month+"-01";
				var enddate = year+"-"+month+"-"+day;
				$("#startTime").datebox('setValue', startdate);
				$("#endTime").datebox('setValue', enddate);
				$("#time").val("");
				$("#busId").val("null");
				$("#figure").val("条形图");
				$("#ways").val("0");
			});
			
		});
		function getJSON(){
		var rows = null;
		var ways=$("#ways").find("option:selected").text();
    	var startTime = $("#startTime").datebox('getValue');
    	var endTime = $("#endTime").datebox('getValue');
    	var busId = $("#busId").val();
    	var productId = $("#productId").val();
    	var figure=$("#figure").find("option:selected").text();
		$.ajax({
			url : "getStatisticsByHighCharts.action",
			type : "post",
			async : false,
			data : "ways="+ways+"&startTime="+startTime+"&endTime="+endTime+
			"&busId="+busId+"&productId="+productId+"&figure="+figure,
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
	    $(function(){
	    	var rows = null;
	    	rows = getJSON();
	    	var productDescribe = "";
	    	var allPay = null;
	    	var paySuccess = null;
	    	var payFailed = null;
	    	var allPayMoney = null;
	    	var allPayPhoneNumbers = null;
	    	var paySuccessPhoneNumbers = null;
	    	var payFailedPhoneNumbers = null;
	    	for(var i=0;i<rows.length;i++){
	    		productDescribe = rows[0].productDescribe;
	    		allPay = rows[i].allPay;
	    		paySuccess = rows[i].paySuccess;
	    		payFailed = rows[i].payFailed;
	    		allPayMoney=rows[i].allPayMoney;
	    		allPayPhoneNumbers = rows[i].allPayPhoneNumbers;
	    		paySuccessPhoneNumbers = rows[i].paySuccessPhoneNumbers;
	    		payFailedPhoneNumbers = rows[i].payFailedPhoneNumbers;
			}
	    	 
	             $(document).ready(function() {
						new Highcharts.Chart({
							chart: {
								renderTo: 'container',
								type: 'column',
							},
							lang:{ 
							           downloadJPEG: "下载JPEG 图片",  
						               downloadPDF: "下载PDF文档",
							           downloadPNG: "下载PNG 图片",  
							           downloadSVG: "下载SVG 矢量图" , 
							           exportButtonTitle: "导出图片",
							           printButtonTitle:"打印图表"
							},

							credits: {             
								enabled: false,
								}, 
							title: {
	                 text: '交易信息统计',
	             },
	             subtitle: {
	                 text: '168充值宝',
	                 style:{color: '#4876FF',
							fontSize: '12px'}
	             },
	             xAxis: {
	                 categories: [productDescribe],
	                 tickmarkPlacement: 'on',
	                 
	             },
	             yAxis: {
	                 min: 0,
	                 title: {
	                     text: '交易信息数据',
	                     style:{color: '#4876FF',
								fontSize: '12px',
								
	                     }
	                 }
	             },exporting:{  
		                filename:'chart',  
		                url:'toDownloadImage.action'
		            },  
	             tooltip: {
	            	 formatter: function() {
							return '<b>'+ this.series.name +'</b><br/>'+
								this.x +': '+ this.y ;
						}
	             },
	             plotOptions: {
						line: {
					        dataLabels: {
					            enabled: true
					        },

					    },
						column: {
							pointPadding: 0.1,
							borderWidth: 0,
							 dataLabels: {
						            enabled: true
						        },
						}
					},
	             legend: {
						layout: 'horizontal',
						backgroundColor: '#FFFFFF',
						style: {
							left: 'auto',
							top: 'auto',
							bottom: '10px'
						},
						shadow:true,
						
						
		            	
					},
	             series: [{
	                 name: '总计支付笔数',
	                 data: [allPay],
	                 visible:false
	     
	             }, {
	                 name: '支付成功笔数',
	                 data: [paySuccess],
	                 visible:false
	     
	             }, {
	                 name: '支付失败笔数',
	                 data: [payFailed],
	                 visible:false
	     
	             }, {
	                 name: '总计支付金额',
	                 data: [allPayMoney]
	     
	             }, {
	                 name: '总计支付号码数',
	                 data: [allPayPhoneNumbers],
	                 visible:false
	             }, {
	                 name: '成功支付号码数',
	                 data: [paySuccessPhoneNumbers],
	                 visible:false
	             }, {
	                 name: '支付失败号码数',
	                 data: [payFailedPhoneNumbers],
	                 visible:false,
	                 
	             }]
	         });
						
	     });
	            


	    });
		
	</script>
	
</html>