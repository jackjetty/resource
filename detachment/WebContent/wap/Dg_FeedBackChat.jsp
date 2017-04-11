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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>意见建议</title> 
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/chat/css/jscrollpane1.css" />
<script src="${pageContext.request.contextPath}/js/jquery-1.7.2.js" type="text/javascript"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/js/chat/jquery.mousewheel.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/js/chat/jquery.jscrollpane.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/chat/scroll-startstop.events.jquery.js"></script> 
</head>
<body>  
<div class="talk">
	<div class="talk_title"><span>意见建议</span></div>
	<div class="talk_record"> 
	
		<div id="jp-container" class="jp-container">
		  <s:iterator value="#request.result['jsFeedBackList']" id="jsFeedBack">
		     <s:if test="#jsFeedBack.feedOpenId.equalsIgnoreCase('')">
		                     <div class="talk_recordbox">
				                      <div class="user"><img src="${pageContext.request.contextPath}/image/feedBack/police.png"/>交警</div> 
               </s:if>
               <s:else>
                           <div class="talk_recordboxme">
				                      <div class="user"><img src="${pageContext.request.contextPath}/image/feedBack/people.png"/><s:property value='nickname' /></div> 
               </s:else> 
		     
				<div class="talk_recordtextbg">&nbsp;</div>
				<div class="talk_recordtext">
					<h3><s:property value='feedText' /></h3>
					<span class="talk_time"><s:property value='feedTime' /></span>
				</div>
			 </div> 
		  
		   </s:iterator>   
		</div> 
	</div> 
	<div class="talk_word">
		 &nbsp;&nbsp;
		<input class="messages emotion" id="txtMessage" autocomplete="off" value="在这里输入文字" onFocus="if(this.value=='在这里输入文字'){this.value='';}"  onblur="if(this.value==''){this.value='在这里输入文字';}"  />
		<input class="talk_send" id="btnSend" type="button" title="发送" value="发送" />
	 
	</div>
</div> 
</body>
</html>

<script type="text/javascript">
 
$("#btnSend").click(function(){ 
	 var content=$.trim($("#txtMessage").val());
	 if(content==""||content=="在这里输入文字"){
		 $.dialog.alert("请输入回复内容!", function(){});
		 return ;
	 }
	 $.ajax({
			url : "${pageContext.request.contextPath}/web/feedBackCustRes.action",
			data : {
				"content" : content, 
				"feedOpenId" : '<s:property value="#request.result['feedOpenId']"/>'
			},
			dataType : "json",
			type : "post",
			success : function(data) { 
				if (data.respCode == 0) {
					var html='<div class="talk_recordbox">';
					html+='<div class="user"><img src="${pageContext.request.contextPath}/image/feedBack/police.png"/>交警</div>';
					html+='<div class="talk_recordtextbg">&nbsp;</div>';
					html+='<div class="talk_recordtext">';
					html+='<h3>'+content+'</h3>';
					html+='<span class="talk_time">'+data.custResTime+'</span>';
					html+='</div>';
					html+='</div>'; 
					var $pt=$(html);
					
					$('#jp-container').prepend($pt);	 
					
					
				} else {
					$.dialog.alert(data.respInfo, function(){});
				}
			},
			error : function(data) {
				$.dialog.alert("网络繁忙!请稍后再试!", function(){});
			}
		});
	
	
});


$(function(){

	 
	var $el= $('#jp-container').jScrollPane({
		verticalGutter 	: -16
	}),
			
	// the extension functions and options 	
		extensionPlugin 	= {
			
			extPluginOpts	: {
				// speed for the fadeOut animation
				mouseLeaveFadeSpeed	: 500,
				// scrollbar fades out after hovertimeout_t milliseconds
				hovertimeout_t		: 1000,
				// if set to false, the scrollbar will be shown on mouseenter and hidden on mouseleave
				// if set to true, the same will happen, but the scrollbar will be also hidden on mouseenter after "hovertimeout_t" ms
				// also, it will be shown when we start to scroll and hidden when stopping
				useTimeout			: true,
				// the extension only applies for devices with width > deviceWidth
				deviceWidth			: 980
			},
			hovertimeout	: null, // timeout to hide the scrollbar
			isScrollbarHover: false,// true if the mouse is over the scrollbar
			elementtimeout	: null,	// avoids showing the scrollbar when moving from inside the element to outside, passing over the scrollbar
			isScrolling		: false,// true if scrolling
			addHoverFunc	: function() {
				
				// run only if the window has a width bigger than deviceWidth
				if( $(window).width() <= this.extPluginOpts.deviceWidth ) return false;
				
				var instance		= this;
				
				// functions to show / hide the scrollbar
				$.fn.jspmouseenter 	= $.fn.show;
				$.fn.jspmouseleave 	= $.fn.fadeOut;
				
				// hide the jScrollPane vertical bar
				var $vBar			= this.getContentPane().siblings('.jspVerticalBar').hide();
				
				/*
				 * mouseenter / mouseleave events on the main element
				 * also scrollstart / scrollstop - @James Padolsey : http://james.padolsey.com/javascript/special-scroll-events-for-jquery/
				 */
				$el.bind('mouseenter.jsp',function() {
					
					// show the scrollbar
					$vBar.stop( true, true ).jspmouseenter();
					
					if( !instance.extPluginOpts.useTimeout ) return false;
					
					// hide the scrollbar after hovertimeout_t ms
					clearTimeout( instance.hovertimeout );
					instance.hovertimeout 	= setTimeout(function() {
						// if scrolling at the moment don't hide it
						if( !instance.isScrolling )
							$vBar.stop( true, true ).jspmouseleave( instance.extPluginOpts.mouseLeaveFadeSpeed || 0 );
					}, instance.extPluginOpts.hovertimeout_t );
					
					
				}).bind('mouseleave.jsp',function() {
					
					// hide the scrollbar
					if( !instance.extPluginOpts.useTimeout )
						$vBar.stop( true, true ).jspmouseleave( instance.extPluginOpts.mouseLeaveFadeSpeed || 0 );
					else {
					clearTimeout( instance.elementtimeout );
					if( !instance.isScrolling )
							$vBar.stop( true, true ).jspmouseleave( instance.extPluginOpts.mouseLeaveFadeSpeed || 0 );
					}
					
				});
				
				if( this.extPluginOpts.useTimeout ) {
					
					$el.bind('scrollstart.jsp', function() {
					
						// when scrolling show the scrollbar
					clearTimeout( instance.hovertimeout );
					instance.isScrolling	= true;
					$vBar.stop( true, true ).jspmouseenter();
					
				}).bind('scrollstop.jsp', function() {
					
						// when stop scrolling hide the scrollbar (if not hovering it at the moment)
					clearTimeout( instance.hovertimeout );
					instance.isScrolling	= false;
					instance.hovertimeout 	= setTimeout(function() {
						if( !instance.isScrollbarHover )
								$vBar.stop( true, true ).jspmouseleave( instance.extPluginOpts.mouseLeaveFadeSpeed || 0 );
						}, instance.extPluginOpts.hovertimeout_t );
					
				});
				
					// wrap the scrollbar
					// we need this to be able to add the mouseenter / mouseleave events to the scrollbar
				var $vBarWrapper	= $('<div/>').css({
					position	: 'absolute',
					left		: $vBar.css('left'),
					top			: $vBar.css('top'),
					right		: $vBar.css('right'),
					bottom		: $vBar.css('bottom'),
					width		: $vBar.width(),
					height		: $vBar.height()
				}).bind('mouseenter.jsp',function() {
					
					clearTimeout( instance.hovertimeout );
					clearTimeout( instance.elementtimeout );
					
					instance.isScrollbarHover	= true;
					
						// show the scrollbar after 100 ms.
						// avoids showing the scrollbar when moving from inside the element to outside, passing over the scrollbar								
					instance.elementtimeout	= setTimeout(function() {
						$vBar.stop( true, true ).jspmouseenter();
					}, 100 );	
					
				}).bind('mouseleave.jsp',function() {
					
						// hide the scrollbar after hovertimeout_t
					clearTimeout( instance.hovertimeout );
					instance.isScrollbarHover	= false;
					instance.hovertimeout = setTimeout(function() {
							// if scrolling at the moment don't hide it
						if( !instance.isScrolling )
								$vBar.stop( true, true ).jspmouseleave( instance.extPluginOpts.mouseLeaveFadeSpeed || 0 );
						}, instance.extPluginOpts.hovertimeout_t );
					
				});
				
				$vBar.wrap( $vBarWrapper );
				
			}
			
			}
			
		},
		 
		jspapi= $el.data('jsp');
		
	// extend the jScollPane by merging	
	$.extend( true, jspapi, extensionPlugin );
	jspapi.addHoverFunc();

});
</script>
