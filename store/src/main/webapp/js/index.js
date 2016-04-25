$(function(){
//	$("#demo-navbar .dropdown-menu a").click(function(){
//		var href = $(this).attr("href") ;
//		$("#tab-list a[href='"+href+"']").tab("show") ;
//	});
	
	
	
	//首页通知的datagrid
	$('#notification_list').datagrid({
		url : "../../index/getNotification", // 这里可以是个json文件，也可以是个动态页面，还可以是个返回json串的function
		columns : [ datagridD ],
		frozenColumns : [ [ {
			field : 'state'
		} ] ],
		rownumbers : true,
		idField : 'id',
		striped : true,
		pageSize : 25,
		pageList : [ 5,25, 35, 45, 55 ],
		nowrap : true,
		height : 'auto',
		fit : true,
		loadMsg:'',
		border : false,
		pagination : true,
		onDblClickRow : function(rowIndex, rowData) {

		}
	});
	
	
	/**定时刷新通知**/
	 setInterval(showTime, 2000);
	 function showTime(){
		 $("#notification_list").datagrid('reload');
	 }
	
	
	/*检查邮箱是否被注册*/
	
	/*检查用户名是否被注册*/
	$("#register-username").blur(function(){
		var username = $("#register-username").val() ;
		var json ={
			"username":username
		}
		if(username != undefined && username != ""){
			$.ajax( {
				type:"POST", 
				data:json,
				url : '../../index/checkIsRepeat',
				cache : false,
				success : function(r) {
					if(r=="true"){
						$("#div-username").addClass("has-success has-feedback") ;
						$("#register-username").after("<span class='glyphicon glyphicon-ok form-control-feedback' aria-hidden='true'></span>") ;
						/*如果第一次重复了 换了一个账号之后成功了 要把第一次加的class和span取消掉*/
						$("#div-username").removeClass("has-error") ;
						$("#div-username span").remove(".glyphicon-remove");
					}else{
						$("#div-username").addClass("has-error has-feedback") ;
						$("#register-username").after("<span class='glyphicon glyphicon-remove form-control-feedback' aria-hidden='true'></span>") ;
						$("#div-username").removeClass("has-success") ;
						$("#div-username span").remove(".glyphicon-ok");
					}
				}
			});
		}
	});
	
	$("#registerUserForm").submit(function(){
		var pwd1 = $("#password1").val() ;
		var pwd2 = $("#password2").val() ;
		if(pwd1 != pwd2){
			alert("两次输入密码不同") ;
			$("#div-password1").addClass("has-error has-feedback") ;
			$("#div-password2").addClass("has-error has-feedback") ;
			$("#password1").after("<span class='glyphicon glyphicon-remove form-control-feedback' aria-hidden='true'></span>") ;
			$("#password2").after("<span class='glyphicon glyphicon-remove form-control-feedback' aria-hidden='true'></span>") ;
			return false ;
		}
//		判断目前是否重复的用户名或者是邮箱 有的话就不让提交
		var email_class = $("#div-email span").attr("class") ;
		var username_class =$("#div-username span").attr("class") ;
		if(email_class.indexOf("glyphicon-remove") >0 || username_class.indexOf("glyphicon-remove") >0  ){
			return false ;
		}
		
	});
	
	
});

/**
 * 登录的方法
 */
function login(){
	var username = $("#login-name").val() ;
	var password = $("#login-password").val() ;
	if(username =="" || password == ""){
		alert("请将信息填写完整") ;
		return ;
	}
	var json ={
		"username":username,
		"password":password
	}
	
    $.ajax({
		type:"POST", 
		url : '../../index/login',
		data :json,
		success : function(r) {
			if(r=="true"){
				window.location.href="../../index/turnToIndex/1" ;
			}else{
				alert("用户名或密码错误！");
			}
		}
	});
}

document.onkeydown = function(e) {
	var ev = document.all ? window.event : e;
	//监听 Ctrl+enter事件
	if (ev.ctrlKey && ev.keyCode == 13) {
		login();
	}
}


function createTopic(){
	var username = $("#session_username").val() ;
	if(username == ""){
		alert("请登录之后再发起") ;
		$('#login').modal('show') ;
	}else{
		window.location.href= "../../main/turnToCreateAchat" ;
	}
}
/**
 * 打开购物车
 */
function openShoppingCar(){
	var user_id = $("#session_user_id").val() ;
	if(user_id == ""){
		alert("请登录之后再打开") ;
		$('#login').modal('show') ;
	}else{
		window.location.href= "../../user/achatDetail/"+user_id+"?state=5" ;
	}
}

var datagridD = [{
	field : 'title',
	title : '标题',
	width : 200,
	align : 'center'
},{
	field : 'support_price',
	title : '价格',
	width : 100,
	align : 'center'
}
];