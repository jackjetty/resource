$(function() {
	var west = $("#tt").layout('panel', 'west');
	west.panel('setTitle', managerRole+":" + managerName);
	$.ajax({
		url : "web/getMenu.action",
		data : "",
		dataType : "json",
		type : "post",
		success : function(data) {
			menustart(data);
		},
		error : function(data) {
			alert("error 后台出现错误！");
		}
	});
});

function menustart(json) {
	var a = 1;
	var data = json;
	var level2menu = new Array();
	for(var x = 0; x < data.length; x++){
		var menu = data[x];
		if (menu.grade == '2') {
			level2menu.push(menu.menuId);
		}
	}
	level2menu.sort();
	var flag = 0;
	while (data.length > 0) {
		for ( var i = 0; i < data.length;) {
			var menu = data[i];
			if (menu.grade == '1') {
				data = arraydelete(data, i);
			} else if (menu.grade == '2') {
				if(menu.menuId == level2menu[flag]){
					data = arraydelete(data, i);
					var id = menu.menuId;
					var ul = $("<ul></ul>");
					var level3menu = new Array();
					var itemids = new Array();
					for ( var j = 0; j < data.length;) {
						var length = id.length;
						var item = data[j];
						var itemgrade = item.grade;
						var itemid = item.menuId;
						if (itemgrade == '3' && itemid.substring(0, length) == id) {
							itemids.push(itemid);
							var li = $("<li onclick=\"addTab('"
									+ item.text
									+ "','"
									+ item.link
									+ "')\"><span class='"
									+ item.iconCls
									+ "'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span>"
									+ item.text + "</span></li>");
							var lidata = {
									itemid : itemid,
									li : li
							};
							level3menu.push(lidata);
							data = arraydelete(data, j);
						} else {
							j++;
						}
					}
					itemids.sort();
					for(var y=0;y<itemids.length;y++){
						for(var z=0;z<level3menu.length;z++){
							if(itemids[y] == level3menu[z].itemid){
								ul.append(level3menu[z].li);
							}
						}
					}
					if(a==1){
						$(".easyui-accordion").accordion('add', {
							title : menu.text,
							content : ul,
							selected : true,
							iconCls : menu.iconCls
						});
						a++;
					}else{
						$(".easyui-accordion").accordion('add', {
							title : menu.text,
							content : ul,
							selected : false,
							iconCls : menu.iconCls
						});
					}
					flag++;
					i++;
				}else{
					i++;
				}
				
			} else {
				i++;
			}
		}
	}

}
function arraydelete(data, index) {
	var result = new Array();
	for ( var i = 0; i < data.length; i++) {
		if (i != index) {
			result.push(data[i]);
		} else
			continue;
	}
	return result;
}

function addTab(title, src) {
	if ($('#tabs').tabs('exists', title)) {
		$('#tabs').tabs('select', title);// 选中并刷新
		var currTab = $('#tabs').tabs('getSelected');
		var url = $(currTab.panel('options').content).attr('src');
		if (url != undefined && currTab.panel('options').title != '主页' && src==url) {
			$('#tabs').tabs('update', {
				tab : currTab,
				options : {
					content : createFrame(url)
				}
			});
		}else{
			$('#tabs').tabs('update', {
				tab : currTab,
				options : {
					content : createFrame(src)
				}
			});
		}
	} else {
		var content = createFrame(src);
		$('#tabs').tabs('add', {
			title : title,
			content : content,
			closable : true,
			fit:true,
			tools : [ {
				iconCls : 'icon-mini-refresh',
				handler : function() {
					var currTab = self.parent.$('#tabs').tabs('getSelected'); // 获得当前tab
					var url = $(currTab.panel('options').content).attr('src');
					self.parent.$('#tabs').tabs('update', {
						tab : currTab,
						options : {
							content : createFrame(url)
						}
					});
				}
			} ]
		});
	}
}

function createFrame(url) {
	var s = '<iframe scrolling="auto" frameborder="0" border="0" scrolling="no" src="' + url
			+ '" style="width:100%;height:100%;"></iframe>';
	return s;
}




