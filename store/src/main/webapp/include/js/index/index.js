$(function(){
	$('#password').window('close');
	var centerTabs = $('#tabs').tabs({
		border : false,
		fit : true
	});
	var url="user/turnToNotification";
	//默认创建一选项卡
	$('#tabs').tabs('add',{
		title: "首页",
		content: '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="border:0;width:100%;height:100%;"></iframe>',
		closable: false
	});
	
	$('#state').combobox({
		onSelect: function(obj){
			var state = obj.value;
			$.ajax({            
		        type:"POST",   //post提交方式默认是get
		        url:"user/turnToNotification?st="+state            
		  });
		}
	});

	
}) ;

function getMain(obj,url){
	
	var name = $(obj).html() ;
	if (!$('#tabs').tabs('exists', name)) {
	$('#tabs').tabs('add',{
		title: name,
		content: '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="border:0;width:100%;height:100%;"></iframe>',
		closable: true
	});
	}else{
		$('#tabs').tabs('select', name);
		// 刷新页面
		var tab = $('#tabs').tabs('getSelected');

		$('#tabs').tabs('update', {
			tab : tab,
			options : {
				title : name,
				content : '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="border:0;width:100%;height:100%;"></iframe>',
				closable : true
			}

		});
	}
}

function loginOut(){
	window.location="login/loginOut?type=0" ;
}

/**
 * 回到首页
 */
function goToIndex(url){
	window.location.href=url+"/index/turnToIndex/1";
}