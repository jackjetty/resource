(function(){
	var iClientWidth;
	var iClientHeight;
	var iDivLeft;
	var iDivTop;
	var iScrollTop;
	var iScrollLeft;
	var iScrollHeight;
	var iImgOldWidth;
	var iImgOldHeight;
	var aToBigImg = [ ];
	
	//存储图片放大是的状态
	var iBeBigWidth;
	var iBeBigHeight;
	var iBeBigLeft;
	var iBeBigTop;
	window.fnToBig = function(animate){
		var added=false;
		//引入样式
		if(added)return;
		added = true;
		var oLink = document.createElement('link');
		oLink.href = '../toBig/toBigCss.css';
		oLink.type = 'text/css';
		oLink.rel = 'stylesheet'
		var oHeader = document.getElementsByTagName('head')[0];
		oHeader.appendChild(oLink);
		//引入运动框架
		var oScript = document.createElement('script');
		oScript.type = 'text/javascript';
		oScript.src  = '../toBig/move.js';
		oHeader.appendChild(oScript);
		
		
		//搜索img
		var aImg = document.getElementsByTagName('img');
		var oDivShow;
		var oMask = document.createElement('div');
		var oCanMoveImg = document.createElement('img');
		for(var i=0; i<aImg.length; i++){
			if(aImg[i].getAttribute('to_big')=='true'){
				aToBigImg.push(aImg[i]);
			};
		};
		//document.title = aToBigImg.length;
		//Download by http://www.codefans.net	
		
		var iNow = 0;
		for(var i=0; i<aToBigImg.length; i++){
			aToBigImg[i].index = i;
			aToBigImg[i].style.cursor = 'pointer';
			aToBigImg[i].onclick = function(){
				iNow = this.index;
				var oBox = fnGetByClass(document,'show_box')
				if(oBox.length == 0){
					oDivShow = document.createElement('div');
				};
				
				
				iClientWidth = document.documentElement.clientWidth;
				iClientHeight = document.documentElement.clientHeight;

				iImgOldWidth = this.offsetWidth;
				iImgOldHeight = this.offsetHeight;

				//创建mask
				
				oMask.className = 'mask';
				iScrollHeight = document.body.scrollHeight;
				iScrollTop = document.documentElement.scrollTop || document.body.scrollTop;
				oMask.style.cssText = 'width:'+iClientWidth+'px;height:'+(iClientHeight+iScrollTop)+'px;';
				miaovStartMove(oMask, {opacity:50},5, MIAOV_MOVE_TYPE.BUFFER);
				document.body.appendChild(oMask);
				addWheel([oMask]);
				
				//DIV淡入
				oDivShow.className = 'show_box';
				miaovStartMove(oDivShow, {opacity:100},5, MIAOV_MOVE_TYPE.BUFFER);
				oDivShow.innerHTML = '';
				
				//创建关闭按钮
				var oClose = document.createElement('div');
				oClose.className = 'close';
				oDivShow.appendChild(oClose);

				//创建图片
				var oImg = document.createElement('img');
				oImg.src = this.src;
				oImg.style.cssText = 'height:'+iClientHeight/1.1+'px;width:'+(iImgOldWidth*iClientHeight/1.1)/iImgOldHeight+'px;margin:0px;padding:0px;border:none;';
				oDivShow.appendChild(oImg);
				
				//创建左按钮
				var oLeftBtn = document.createElement('div');
				oLeftBtn.className = 'leftBtn';
				oLeftBtn.style.cssText = 'height:'+iClientHeight/1.6+'px;width:'+(iImgOldWidth*iClientHeight/3.2)/iImgOldHeight+'px;';
				var oPre = document.createElement('div');
				oPre.className = 'pre';
				oDivShow.appendChild(oPre);
				//图片切换
				
				
				//创建右按钮
				var oRightBtn = document.createElement('div');
				oRightBtn.className = 'rightBtn';
				oRightBtn.style.cssText = 'height:'+iClientHeight/1.6+'px;width:'+(iImgOldWidth*iClientHeight/3.2)/iImgOldHeight+'px;';
				var oNext = document.createElement('div');
				oNext.className = 'next';
				oDivShow.appendChild(oNext);
				
				oDivShow.appendChild(oLeftBtn);
				oDivShow.appendChild(oRightBtn);
				
				
				oNext.onmousedown = oPre.onmousedown = function(ev){
					var oEvent = ev || event;
					oEvent.cancelBubble = true;
					
				};
				oPre.onclick = function(){
					iNow--;
					if(iNow<0){
						iNow =aToBigImg.length-1;
						
					};
					//document.title = iNow;
					oImg.src = aToBigImg[iNow].src;
					iImgOldWidth = aToBigImg[iNow].offsetWidth;
					iImgOldHeight = aToBigImg[iNow].offsetHeight;
					oImg.style.cssText = 'width:'+(iImgOldWidth*iClientHeight/1.1)/iImgOldHeight+'px;height:'+(iClientHeight/1.1)+'px';
					fnGetImgPos();
					oRightBtn.style.cssText=oLeftBtn.style.cssText = 'height:'+iBeBigHeight+'px;width:'+iBeBigWidth/2+'px;';
					
					fnBoxPos();
					
				};
				oNext.onclick = function(){
					iNow++;
					if(iNow>aToBigImg.length-1){
						iNow = 0;
						
					};
					//document.title = iNow;
					oImg.src = aToBigImg[iNow].src;
					iImgOldWidth = aToBigImg[iNow].offsetWidth;
					iImgOldHeight = aToBigImg[iNow].offsetHeight;
					
					oImg.style.cssText = 'width:'+(iImgOldWidth*iClientHeight/1.1)/iImgOldHeight+'px;height:'+(iClientHeight/1.1)+'px';
					fnGetImgPos();
					oRightBtn.style.cssText=oLeftBtn.style.cssText = 'height:'+iBeBigHeight+'px;width:'+iBeBigWidth/2+'px;';
					fnBoxPos();
				};
				
				
				
				//添加大DIV到页面
				document.body.appendChild(oDivShow);
				
				
				//左右切换按钮效果
				oPre.onmouseover = oLeftBtn.onmouseover = function(){
					miaovStartMove(oPre, {opacity:100},3, MIAOV_MOVE_TYPE.BUFFER);
				};
				oPre.onmouseout = oLeftBtn.onmouseout = function(){
					miaovStartMove(oPre, {opacity:0},3, MIAOV_MOVE_TYPE.BUFFER);
				};
				oNext.onmouseover = oRightBtn.onmouseover = function(){
					miaovStartMove(oNext, {opacity:100},3, MIAOV_MOVE_TYPE.BUFFER);
				};
				oNext.onmouseout = oRightBtn.onmouseout = function(){
					miaovStartMove(oNext, {opacity:0},3, MIAOV_MOVE_TYPE.BUFFER);
				};
				
				var iDivWidth;
				var iDivHeight;
				var iImgWidth;
				var iImgHeight;
				fnBoxPos();
				function fnBoxPos(){
					iClientWidth = document.documentElement.clientWidth;
					iClientHeight = document.documentElement.clientHeight;
					iDivWidth = oDivShow.offsetWidth;
					iDivHeight = oDivShow.offsetHeight;
					iScrollTop = document.documentElement.scrollTop || document.body.scrollTop;
				    oDivShow.style.cssText = 'left:'+(iClientWidth-iDivWidth)/2+'px;top:'+((iClientHeight-iDivHeight)/2+iScrollTop)+'px;padding:5px;cursor:pointer;filter:alpha(opacity:100);opacity:1;';
					iDivLeft = oDivShow.offsetLeft;
					iDivTop = oDivShow.offsetTop;
					iImgWidth = oImg.offsetWidth;
					iImgHeight = oImg.offsetHeight;
				};
				
				
				
				
				
				
				var oReturn = document.createElement('div') ;
				
				//var bToSmall = false;
				
				function fnSweel(down,oXy){
					var iClientX = oXy.left;
					var iClientY = oXy.top+iScrollTop;
					var iImgLeft = fnPos(oDivShow).left;
					var iImgTop = fnPos(oDivShow).top;
					var iProX = (iClientX - iImgLeft)/oImg.offsetWidth;
					var iProY = (iClientY - iImgTop)/oImg.offsetHeight;
					var oSmallBox = fnGetByClass(oDivShow,'returnBg');
					
					if(oSmallBox.length == 0){
						//创建返回按钮提示
						
						oReturn.className = 'returnBg';
						oDivShow.appendChild(oReturn);
						
					};
					
					if(down)
					{   //DIV变小
						//限制最小宽度
						//bToSmall = true;
						if(oImg.offsetWidth<300){
							return;
						}else{
							oImg.style.width=oImg.offsetWidth-20+'px';
						};
						
						oRightBtn.style.height=oLeftBtn.style.height=oImg.style.height=oImg.offsetWidth*iImgHeight/iImgWidth+'px';
						oRightBtn.style.width=oLeftBtn.style.width = (oImg.offsetWidth)/2+'px';
						
						oDivShow.style.left =oDivShow.offsetLeft + 20*(iProX) +  'px';
						oDivShow.style.top = oDivShow.offsetTop+(20*iImgHeight/iImgWidth)*(iProY) + 'px';
						fnGetImgPos();
						 

					}
					else
					{
						

						
						
						//DIV变大
						//bToSmall = false;
						if ((navigator.userAgent.indexOf('MSIE 6.0') >= 0) && (navigator.userAgent.indexOf('Opera') < 0)  && oImg.offsetHeight>=iClientHeight-50){
							return;
						}else if ((navigator.userAgent.indexOf('MSIE 7.0') >= 0) && (navigator.userAgent.indexOf('Opera') < 0)  && oImg.offsetHeight>=iClientHeight-50){
							return;
						}else if ((navigator.userAgent.indexOf('MSIE 8.0') >= 0) && (navigator.userAgent.indexOf('Opera') < 0)  && oImg.offsetHeight>=iClientHeight-50){
							return;
						}else{
						oImg.style.width=oImg.offsetWidth+20+'px';
						oRightBtn.style.height=oLeftBtn.style.height = oImg.style.height=oImg.offsetWidth*iImgHeight/iImgWidth+'px';
						
						
						oRightBtn.style.width=oLeftBtn.style.width = (oImg.offsetWidth)/2+'px';

						oDivShow.style.left =oDivShow.offsetLeft -20*(iProX) + 'px';
						oDivShow.style.top = oDivShow.offsetTop-(20*iImgHeight/iImgWidth)*(iProY) +  'px';
						
						fnGetImgPos();
						}
						
					};
					miaovStartMove(oReturn, {opacity:100},5, MIAOV_MOVE_TYPE.BUFFER,function(){
						miaovStartMove(oReturn, {opacity:0},5, MIAOV_MOVE_TYPE.BUFFER,function(){
							oDivShow.removeChild(oReturn);
						});
					});
				}
				
				//获取Img的位置；
				function fnGetImgPos(){
					iBeBigWidth = oImg.offsetWidth;
					iBeBigHeight = oImg.offsetHeight;
					iBeBigLeft = oDivShow.offsetLeft;
					iBeBigTop = oDivShow.offsetTop;
				};
				
				addWheel([oImg,oReturn,oLeftBtn,oRightBtn],fnSweel);
				
				//关闭div
				oMask.onclick = oClose.onclick = function(ev){
					var oEvent = ev || event;
					oEvent.cancelBubble = true;
					miaovStartMove(oDivShow, {opacity:0},5, MIAOV_MOVE_TYPE.BUFFER,function(){
						document.body.removeChild(oDivShow);
					});
					miaovStartMove(oMask, {opacity:0},5, MIAOV_MOVE_TYPE.BUFFER,function(){
						document.body.removeChild(oMask);
					});
					
				};
				oClose.onmousedown = function(ev){
					var oEvent = ev || event;
					oEvent.cancelBubble = true;
					return false;
				};
				//回到初始位置初始大小
				//判断是否两个运动都已完成
				var aComplete = [ ];
				var Ticker = null;
				//判断是放大状态还是缩小状态
				var bBig = true;
				var bAnimateTwo = false;
				function fnGoBack(){
					if(animate=='animateOne'){
						fnAnimateOne();
					}else if(animate=='animateTwo'){
						
						if(oImg.offsetWidth > iImgWidth){
							bAnimateTwo = false;
							//document.title = 'a';
							fnToOut2();
						}
						else if(oImg.offsetWidth < iImgWidth){
							bAnimateTwo = true;
						};
					};
					
					function fnAnimateOne(){
						if(oImg.offsetWidth > iImgWidth){
							bBig = true;
							//fnFadeIn();
						}
						else if(oImg.offsetWidth < iImgWidth){
							bBig = false;
							//fnFadeIn();
						};
					};
					
				
					
					miaovStartMove(oDivShow, {left:iDivLeft,top:iDivTop},1.2, MIAOV_MOVE_TYPE.BUFFER,function(){aComplete.push(1);});
					miaovStartMove(oImg, {width:iImgWidth,height:iImgHeight},1.2, MIAOV_MOVE_TYPE.BUFFER,function(){aComplete.push(1);});
					oLeftBtn.style.width = oRightBtn.style.width = iImgWidth/2 + 'px';
					oLeftBtn.style.height = oRightBtn.style.height = iImgHeight + 'px';
					Ticker = setInterval(function(){
						if(aComplete.length==2){
							if(bAnimateTwo){
								fnToOut();
							};
							aComplete = [ ];
							clearInterval(Ticker);
						};
					},1);
				};
				
				//图片放大效果
				function fnToOut(){
					oCanMoveImg.src = oImg.src;
					oCanMoveImg.className = 'moveImg';
					oCanMoveImg.style.position = 'absolute';
					oCanMoveImg.style.width = iImgWidth + 'px';
					oCanMoveImg.style.height = iImgHeight + 'px';
					oCanMoveImg.style.left = iDivLeft+5 + 'px';
					oCanMoveImg.style.top = iDivTop + 5+'px';	
					document.body.appendChild(oCanMoveImg);
					addWheel([oCanMoveImg]);
					miaovStartMove(oCanMoveImg, {left:(iDivLeft-2000/2),top:(iDivTop-2000/2),opacity:0,width:(iImgWidth+2000),height:(iImgHeight+2000)},7, MIAOV_MOVE_TYPE.BUFFER,function(){
						document.body.removeChild(oCanMoveImg);
						oCanMoveImg.style.filter = 'alpha(opacity:100)';
						oCanMoveImg.style.opacity = 1;
						bBig = true;
					});
				};
				function fnToOut2(){
					oCanMoveImg.src = oImg.src;
					oCanMoveImg.className = 'moveImg';
					oCanMoveImg.style.position = 'absolute';
					oCanMoveImg.style.width = iBeBigWidth + 'px';
					oCanMoveImg.style.height = iBeBigHeight + 'px';
					oCanMoveImg.style.left = iBeBigLeft+5 + 'px';
					oCanMoveImg.style.top = iBeBigTop + 5+'px';	
					document.body.appendChild(oCanMoveImg);
					addWheel([oCanMoveImg]);
					miaovStartMove(oCanMoveImg, {opacity:0},10, MIAOV_MOVE_TYPE.BUFFER,function(){
						document.body.removeChild(oCanMoveImg);
						oCanMoveImg.style.filter = 'alpha(opacity:100)';
						oCanMoveImg.style.opacity = 1;
						bBig = true;
					});
				};
				
				//图片缩小效果
				function fnFadeIn(){
					//记录图片最大状态
					var iFirtImgWidth = iBeBigWidth;
					var iFirtImgHeight = iBeBigHeight;
					var iFirtImgLeft = iBeBigLeft;
					var iFirtImgTop = iBeBigTop;
					var aToSmallMove = [ ];
					var aToSmallMoveImgValue = [ ];
					var iWidthChange = Math.abs(iFirtImgWidth - iImgWidth);
					var iHeightChange = Math.abs(iFirtImgHeight - iImgHeight);
					var iLeftChange = iFirtImgLeft - iDivLeft;
					var iTopChange = iFirtImgTop - iDivTop;
					//10张图片的宽高差值;
					var iSpeedWidth = Math.floor(iWidthChange/10);
					var iSpeedHeight = Math.floor(iHeightChange/10);
					var iSpeedLeft = Math.floor(iLeftChange/10);
					var iSpeedTop = Math.floor(iTopChange/10);
					var oBigMoveImg;
					for(var i=0; i<10; i++){
						oBigMoveImg = document.createElement('img');
						oBigMoveImg.src = oImg.src;
						oBigMoveImg.className = 'moveImg';
						aToSmallMoveImgValue.push({width:iFirtImgWidth,height:iFirtImgHeight,left:iFirtImgLeft,top:iFirtImgTop});
						if(bBig){
							iFirtImgWidth -= iSpeedWidth;
							iFirtImgHeight -= iSpeedHeight;
						}else{
							iFirtImgWidth += iSpeedWidth;
							iFirtImgHeight += iSpeedHeight;
						};
						iFirtImgLeft -= iSpeedLeft;
						iFirtImgTop -= iSpeedTop;
						aToSmallMove.push(oBigMoveImg);
						
						
					};
					
					var k=0;
					var oNewObj;
					var Timer = null;
					fnStarMove();
					function fnStarMove(){
						if(k<10){
							oNewObj = aToSmallMove[k];
							oNewObj.style.width = aToSmallMoveImgValue[k].width + 'px';
							oNewObj.style.height = aToSmallMoveImgValue[k].height + 'px';
							oNewObj.style.left = aToSmallMoveImgValue[k].left + 'px';
							oNewObj.style.top = aToSmallMoveImgValue[k].top + 'px';
							document.body.appendChild(oNewObj);
							addWheel([oNewObj]);
							miaovStartMove(oNewObj, {opacity:0},5, MIAOV_MOVE_TYPE.BUFFER,function(){
								var aRemoveImg = fnGetByClass(document,'moveImg');
								for(var i=0; i<aRemoveImg.length; i++){
									document.body.removeChild(aRemoveImg[i]);
								};
								
							});
							Timer = setTimeout(fnStarMove,1);
						}else{
							clearTimeout(Timer);
						};
						k++;
					};
					
				};
				
				
				
				//判断是否拖动了div
				var bMove = false;
				//拖动div
				oDivShow.onmousedown = function(ev){
					var oEvent = ev || event;
					var iClientX = oEvent.clientX - oDivShow.offsetLeft;
					var iClientY = oEvent.clientY - oDivShow.offsetTop;
					function fnMove(ev){
						fnGetImgPos();
						var oEvent = ev || event;
						bMove = true;
						var l = oEvent.clientX - iClientX;
						var t = oEvent.clientY - iClientY;
						oDivShow.style.left = l + 'px';
						oDivShow.style.top = t + 'px';
						var oSmallBox = fnGetByClass(oDivShow,'returnBg');
						if(oSmallBox.length == 0){
							//创建返回按钮提示
							oReturn.className = 'returnBg';
							oDivShow.appendChild(oReturn);
							miaovStartMove(oReturn, {opacity:100},5, MIAOV_MOVE_TYPE.BUFFER,function(){
								miaovStartMove(oReturn, {opacity:0},5, MIAOV_MOVE_TYPE.BUFFER,function(){
									oDivShow.removeChild(oReturn);
								});
								
							});
						}else{
							miaovStartMove(oReturn, {opacity:100},5, MIAOV_MOVE_TYPE.BUFFER,function(){
								miaovStartMove(oReturn, {opacity:0},5, MIAOV_MOVE_TYPE.BUFFER,function(){
									oDivShow.removeChild(oReturn);
								});								
							});
						};
						
						
						
					};
					function fnUp(){
						if(!bMove){
							fnGoBack();
							bMove = false;
						};
						this.onmouseup = null;
						this.onmousemove = null;
						if(oDivShow.releaseCapture){
							oDivShow.releaseCapture();
						};
						bMove = false;
					};
					if(oDivShow.setCapture){
						oDivShow.onmousemove = fnMove;
						oDivShow.onmouseup = fnUp;
						oDivShow.setCapture();
					}else{
						document.onmousemove = fnMove;
						document.onmouseup = fnUp;
					};
					return false;
				};
				
				
			};
		};
	};
	
	window.onresize = function(){
		var oDivShow = fnGetByClass(document,'show_box')[0];
		var oMask = fnGetByClass(document,'mask')[0];
		//document.title = oDivShow;
		iClientWidth = document.documentElement.clientWidth;
		iClientHeight = document.documentElement.clientHeight;
		
		if(oDivShow){
			iDivLeft = oDivShow.offsetLeft;
			iDivTop = oDivShow.offsetTop;
			var iW = oDivShow.offsetWidth;
			var iH = oDivShow.offsetHeight;
			oMask.style.width = iClientWidth +'px';
			oMask.style.height = iClientHeight +'px';
			
			
			oDivShow.style.left = (iClientWidth - iW)/2 + 'px';
			oDivShow.style.top = (iClientHeight - iH)/2 + 'px';
		};
	};
	
	addEvent(window, 'scroll', fnDivPos);
	
	function fnDivPos(){
		
		var oDivShow = fnGetByClass(document,'show_box')[0];
		//document.title = oDivShow;
		iClientWidth = document.documentElement.clientWidth;
		iClientHeight = document.documentElement.clientHeight;
		iScrollTop = document.documentElement.scrollTop || document.body.scrollTop;
		//iScrollLeft = document.documentElement.scrollLeft || document.body.scrollLeft;
		if(oDivShow){
			
			var iW = oDivShow.offsetWidth;
			var iH = oDivShow.offsetHeight;
			var iTop =(iClientHeight-iH)/2 + iScrollTop;
			oDivShow.style.top = iTop +'px';
			
			//oDivShow.style.left = (iClientWidth-iW)/2 + iScrollLeft +'px';
			//iDivLeft = oDivShow.offsetLeft;
			iDivTop = oDivShow.offsetTop;
		};
	};

	
	function fnGetByClass(oParent,sName){
		var aElements = document.getElementsByTagName('*');
		var result = [ ];
		for(var i=0; i<aElements.length; i++){
			if(aElements[i].className == sName){
				result.push(aElements[i]);
			};
		};
		return result;
	};
	
	function fnPos(obj){
		var iLeft = 0;
		var iTop = 0;
		while(obj){
			iLeft += obj.offsetLeft;
			iTop += obj.offsetTop;
			obj = obj.offsetParent;
		};
		return {left:iLeft,top:iTop}
	};
	
	function addEvent(obj, sEv, fn)
	{
		if(obj.addEventListener)
		{
			obj.addEventListener(sEv, fn, false);
		}
		else
		{
			obj.attachEvent('on'+sEv, fn);
		}
	}

	function addWheel(oDiv, fnMouseWheel)
	{
		function fnWheel(ev)
		{
			var oEvent=ev||event;
			var oXy = {left:oEvent.clientX,top:oEvent.clientY};
			var down=true;
			
			if(oEvent.detail)
			{
				if(oEvent.detail<0)
				{
					down=false;
				}
				else
				{
					down=true;
				}
			}
			else
			{
				if(oEvent.wheelDelta<0)
				{
					down=true;
				}
				else
				{
					down=false;
				}
			}
			
			fnMouseWheel && fnMouseWheel(down,oXy);
			
			if(oEvent.preventDefault)
			{
				oEvent.preventDefault();
			}
			return false;
		}
		
		if(window.navigator.userAgent.toLowerCase().indexOf('firefox')!=-1)
		{
			for(var i=0; i<oDiv.length; i++){
				addEvent(oDiv[i], 'DOMMouseScroll', fnWheel);
			};
		}
		else
		{
			for(var i=0; i<oDiv.length; i++){
				addEvent(oDiv[i], 'mousewheel', fnWheel);
			};
		}
	}
	
	
})();