<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//Dth HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dth">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加功能菜单</title>
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
		title="充值信息统计" style="height: 80px; padding: 10px;overflow: hidden;">
		<form action="" method="post" id="form1" style="height: 100%;">
		<table height="100%">
			<tr	style="Valign: center; text-decoration: none; line-heigth: 30px; height: 30px; margin-left: 50px;">
				<td style="font-size: 12px;">展现方式：</td>
				<td><select id="toTime" style="width: 95px;" ><option value="0">请选择</option><option value="1">按年</option>				
				<option value="2">按月</option><option value="3">按周</option><option value="4">按日</option><option value="5">按时</option></select></td>
				<td style="font-size: 12px;">开始时间：</td>
				<td><input type="text" id="startTime" name="startTime" class="easyui-datebox" editable="false"
					style="width: 95px;" /></td>
				<td style="font-size: 12px;">结束时间：</td>
				<td><input type="text" id="endTime" name="endTime" class="easyui-datebox" editable="false"
					style="width: 95px;" /></td>
					<td style="font-size:12px;">城市名：</td>
				<td><select id="placeName" name="placeName" style="width: 95px; height: 23px;">				
				</select></td>
				<td style="font-size:12px;">图形：</td>
				<td><select id="type"  style="width: 95px; height: 23px;"><option value="column">柱状型</option>				
				<option value="line">折线型</option></select></td>
				<td><a id="search" class="pushBtn" href="javascript:void(0);" hidefocus="true">
				<img width="20" height="20" src="images/icon/search.gif">
				<b>查询</b>
			</a></td><td><a id="clear" class="pushBtn" href="javascript:void(0);" hidefocus="true">
				<b>条件清空</b>
			</a></td>
			</tr>
		</table>
		</form>
	</div>
	<div id="container" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
	
<script type="text/javascript">
	var sTime = '${startTime}';
	var eTime = '${endTime}';
	$("#startTime").val(sTime);
	$("#endTime").val(eTime);
	var toTime = '${toTime}';
	$("#toTime").val(toTime);
	$(function() {
		$.ajax({
			url : "getPlaceInfo.action",
			async : false,
			type : "post",
			data : "",
			dataType : "json",
			success : function(data) {
				var option = $("<option value='' id='qsoption' >全省</option>");
				$("#placeName").append(option);
				for(var i=0;i<data.length;i++){
					option = $("<option value='"+data[i].name+"'>"+data[i].name+"</option>");
					$("#placeName").append(option);
				}
				$("#image").attr("src","<s:url action='getImageToJsp.action'></s:url>");
				
			},
			error : function(data) {
				alert("error 后台出现错误！");
			}
		});
		var startTime = $("#startTime").datebox('getValue');
		var endTime = $("#endTime").datebox('getValue');
		tubiao('','column','0',startTime,endTime);
		$("#search").click(function(){
			var placeName=$("#placeName").val();
			var typeLeiXing=$("#type").val();
			var toTime=$("#toTime").val();
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
			if(toTime=='1'){
				if(differYear<1){
					alert("请保证时间相隔一年以上");
					return false;
				}
				if(differYear>10){
					alert("请保证时间相隔十年以下");
					return false;
				}
			}
			if(toTime=='2'){
				if(differYear!=0&&differMonth>0){
					alert("请保证时间相隔十二个月以下");
					return false;
				}
				if(differYear==0&&differMonth<1){
					alert("请保证时间相隔一个月以上");
					return false;
				}
			}
			if(toTime=='3'){
				if(differ<7){
					alert("请保证时间相隔一周以上");
					return false;
				}
				if(differ>70){
					alert("请保证时间相隔十周以下");
					return false;
				}
			}
			if(toTime=='4'){
				if(differ<1){
					alert("按日查询的话请在一天以上");
					return false;
				}
				if(differ>60){
					alert("按日查询的话请在60以下");
					return false;
				}
			}
			if(toTime=='5'){
				if(differ!=0){
					alert("按时查询的话请在一天以内");
					return false;
				}
			}
			
			tubiao(placeName,typeLeiXing,toTime,startTime,endTime);
			
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
			$("#toTime").val('0');
			$("#placeName").val('');
			$("#type").val('column');
			
		});
		
		
	});
	
	function tubiao(placeName,typeLeiXing,toTime,startTime,endTime){
		var titleText=startTime+'至'+endTime+''+placeName+'充值统计信息';
		$.ajax({
			url : "getStatisticsByRecharge.action",
			async : false,
			type : "post",
			data : "placeName="+placeName+"&startTime="+startTime+"&endTime="+endTime+"&toTime="+toTime,
			dataType : "json",
			success : function(data) {
				var xAxis1=data.xAxis.split(",");
				var newshuju1=eval(data.newshuju);
				$(document).ready(function() {
					new Highcharts.Chart({
						chart: {
							renderTo: 'container',
							defaultSeriesType: typeLeiXing
						},
						title: {
							text: titleText,
							style:{color: '#4876FF',
									fontSize: '18px',
									fontWeight: 'bold'}
						},
						colors:['#CD0000','#0000EE'],
						subtitle: {
							text: '168充值宝',
							style:{color: '#4876FF',
								fontSize: '12px'}
						},
						xAxis: {
							categories: xAxis1,
							labels:{style: {
								 font: 'normal 11px Verdana'
							}}
						},
						yAxis: {
							min: 0,
							title: {
								text: '充值金额( 数  量 )'
							}
						},exporting:{  
			                filename:'chart',  
			                url:'toDownloadByImage.action'
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
									this.x +': '+ this.y +' 元 ';
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
					        series: [{
							name: '充值金额',
							data: newshuju1
						}]
					});
					
					
				});
				
			},
			error : function(data) {
				alert("error 后台出现错误！");
			}
		});
	}
	</script>
	


	
</body>
</html>