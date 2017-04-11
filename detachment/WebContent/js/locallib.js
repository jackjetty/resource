//使用方法 及注意点
//1、不能在页面尚未完成加载时使用alert和confirm
//2、使用时先调用getLocal()方法获得local对象
//3、配置alert或confirm的所需参数option对象，其必须有text属性，对应要显示给用户的文字。
//4、使用local.alert(option)或local.confirm(option,callback)进行调用，其中local.confirm中的callback为用户点击确认或者取消时调用的处理方法。

function getLocal() {
	if (!this.local) {
		this.local = new Local();
	}
	return this.local;
}

function Local() {

	this.confirmFlag;

	this.confirmFlagCheckIntervalId;

	this.ViewHeight;

	this.ViewWidth;

	this.alertHtml = $('<div id="alert" style="display:block;position:absolute;"></div>');

	this.confirmHtml = $('<div id="confirm" style="display:block;position:absolute;"></div>');

	this.loadingHtml = $('<div id="loading" style="display:block;position:absolute;"></div>');

	this.alertHtmlMaxWidth = 320;

	this.confirmHtmlMaxWidth = 320;

	this.background = $('<div id="background" style="background: black;opacity: 0.3;position: fixed;z-index: 9999;top: 0;left: 0;"></div>');

	this.alertHtmlDefaultStyle = {
		"min-height" : "50px",
		"min-width" : "180px",
		"border-radius" : "8px",
		"padding" : "10px",
		"background" : "white",
		"z-index" : "10000"
	};

	this.confirmHtmlDefaultStyle = {
		"min-height" : "70px",
		"min-width" : "240px",
		"border-radius" : "8px",
		"background" : "white",
		"z-index" : "10000"
	};

	this.alert = function(option) {
		this.ViewHeight = $(document).height();
		this.ViewWidth = $(document).width();
		$("body").append(this.background);
		$("#background").css({
			width : $(document).width(),
			height : $(document).height()
		});
		$("body").append(this.alertHtml);
		$("#alert").html("");
		$("#alert")
				.append(
						'<div style="min-width:140px;max-width:320px;min-height:30px;line-height:40px;margin:10px auto;font-size:14px;text-align:center;vertical-align:center;">'
								+ option.text + '<div>');
		$("#alert").css(this.alertHtmlDefaultStyle);
		$("#alert").css(
				{
					"left" : (this.ViewWidth
							- ($("#alert").width() < 320 ? $("#alert").width()
									: 320) - 20) / 2,
					"top" : (this.ViewHeight - $("#alert").height()) / 2 - 60
				});
		$(window)
				.on(
						"resize",
						function() {
							if ($("#background").length) {
								$("#background").css({
									width : $(document).width(),
									height : $(document).height(),
								});
								this.ViewHeight = $(document).height();
								this.ViewWidth = $(document).width();
								$("#alert")
										.css(
												{
													"left" : (this.ViewWidth
															- ($("#alert")
																	.width() < 320 ? $(
																	"#alert")
																	.width()
																	: 320) - 20) / 2,
													"top" : (this.ViewHeight - $(
															"#alert").height()) / 2 - 60
												});
							}
						});
		confirmFlag = false;
		setTimeout(this.closeAlert, 2000);
	};

	this.confirm = function(option, callback) {
		this.ViewHeight = $(document).height();
		this.ViewWidth = $(document).width();
		$("body").append(this.background);
		$("#background").css({
			width : $(document).width(),
			height : $(document).height()
		});
		$("body").append(this.confirmHtml);
		$("#confirm").html("");
		$("#confirm")
				.append(
						'<div style="min-width:140px;max-width:320px;min-height:58px;line-height:58px;margin:0px auto;font-size:14px;text-align:center;vertical-align:center;padding:10px;">'
								+ option.text + '<div>');
		$("#confirm")
				.append(
						'<div style="min-width:140px;max-width:320px;min-height:45px;line-height:45px;font-size:14px;border-top:#d7d6d6 solid 1px;"><div style="width:50%;text-align:center;vertical-align:center;float:left;border-right:#d7d6d6 solid 1px;" id="confirmAllow">确定</div><div style="text-align:center;" id="confirmNotAllow">取消</div><div>');
		$("#confirm").css(this.confirmHtmlDefaultStyle);
		$("#confirm")
				.css(
						{
							"left" : (this.ViewWidth - ($("#confirm").width() < 320 ? $(
									"#confirm").width()
									: 320)) / 2,
							"top" : (this.ViewHeight - $("#confirm").height()) / 2 - 60
						});
		$(window)
				.on(
						"resize",
						function() {
							if ($("#background").length) {
								$("#background").css({
									width : $(document).width(),
									height : $(document).height(),
								});
								this.ViewHeight = $(document).height();
								this.ViewWidth = $(document).width();
								$("#confirm")
										.css(
												{
													"left" : (this.ViewWidth
															- ($("#confirm")
																	.width() < 320 ? $(
																	"#confirm")
																	.width()
																	: 320) - 20) / 2,
													"top" : (this.ViewHeight - $(
															"#confirm")
															.height()) / 2 - 60
												});
							}
						});
		this.confirmFlagCheckIntervalId = setInterval(this.checkConfirmFlag,
				500);
		$("#confirmAllow").click(function() {
			this.confirmFlag = true;
			clearInterval(this.confirmFlagCheckIntervalId);
			$("#confirm").remove();
			$("#background").remove();
			callback(this.confirmFlag);
		});
		$("#confirmNotAllow").click(function() {
			this.confirmFlag = false;
			clearInterval(this.confirmFlagCheckIntervalId);
			$("#confirm").remove();
			$("#background").remove();
			callback(this.confirmFlag);
		});
	};

	this.loading = function(src) {
		this.ViewHeight = $(document).height();
		this.ViewWidth = $(document).width();
		$("body").append(this.background);
		$("#background").css({
			background : "black",
			width : $(document).width(),
			height : $(document).height(),
			opacity : 0.3
		});
		$("body").append(this.loadingHtml);
		$("#loading").html('<img src="' + src + '">');
		$("#loading").css({
			"left" : (this.ViewWidth - $("#loading").width()) / 2,
			"top" : (this.ViewHeight - $("#loading").height()) / 2 - 60
		});
		$(window).on("resize", function() {
			if ($("#background").length) {
				$("#background").css({
					width : $(document).width(),
					height : $(document).height(),
				});
				this.ViewHeight = $(document).height();
				this.ViewWidth = $(document).width();
				$("#loading").css({
					"left" : (this.ViewWidth - $("#loading").width()) / 2,
					"top" : (this.ViewHeight - $("#loading").height()) / 2 - 60
				});
			}
		});
	};
	
	this.closeLoading = function(){
		$("#loading").remove();
		$("#background").remove();
	};
	this.closeAlert = function() {
		$("#alert").remove();
		$("#background").remove();
	};

	this.checkConfirmFlag = function() {
		if (this.confirmFlag) {
			clearInterval(this.confirmFlagCheckIntervalId);
			this.closeConfirm();
		}
	};

	this.closeConfirm = function() {
		$("#confirm").remove();
		$("#background").remove();
	};
	
	
	this.cookie;
	
	this.getCookie = function(){
		if(navigator.cookieEnabled){
			this.cookie = new Cookie();
			return this.cookie;
		}else{
			return null;
		}
		
	};
	this.getConfirmFlag = function(){
		return this.confirmFlag;
	};
	
	
	
	this.postAjax = function(url,parameter,callback){
		$.ajax({
			url:url,
			type:"post",
			dataType:"text",
			data:parameter,
			success:function(data){
				data = decodeURIComponent(data.replace(/\+/g, '%20'));
				data = jQuery.parseJSON(data);
				callback(data);
			},
			error:function(error){
				var option = {text:"后台出现故障"};
				getLocal().alert(option);
			}
		});
		
	};

};

function Cookie() {
	
	this.get = function(name) {
		return $.cookie(name); 
	};

	this.set = function(name, value) {
		var date = new Date();
        date.setTime(date.getTime() + (30* 60 * 1000));
		$.cookie(name,value,{expires:date.toUTCString(),path:'/',domain: '115.239.134.175',secure: false,raw:false});
	};
	
	this.setLong = function(name, value) {
		$.cookie(name,value,{expires:30,path:'/',domain: '115.239.134.175',secure: false,raw:false});
	};

	this.remove = function(name) {
		$.cookie(name,null,{path:'/'});
	};

}
