var datagridD;
// 初始化datagrid

datagridD = [{
	field : 'id',
	title : '编号',
	//hidden : true,
	width : 50
}, {
	field : 'title',
	title : '名称',
	width : 100,
	align : 'center'
},{
	field : 'category_name',
	title : '类别',
	width : 100,
	align : 'center'
},{
	field : 'content',
	title : '内容',
	width : 200,
	align : 'center'
},{
	field : 'parseDate',
	title : '创建日期',
	width : 100,
	align : 'center'
},{
	field : 'create_username',
	title : '发起人',
	width : 100,
	align : 'center'
},{
	field : 'support_name',
	title : '报价供应商',
	width : 100,
	align : 'center'
},{
	field : 'support_price',
	title : '供应商报价',
	width : 100,
	align : 'center'
},{
	field : 'produce_name',
	title : '上架产品名称',
	width : 100,
	align : 'center'
},{
	field : 'produce_model',
	title : '产品型号',
	width : 100,
	align : 'center'
},{
	field : 'state',
	title : '状态',
	width : 100,
	align : 'center'
}


];

var tabrs;
tabrs = [ {

	text : '查询',
	iconCls : 'icon-search',
	handler : function() {
		queryAchat();
	}

}, '-', {
	text : '新增',
	iconCls : 'icon-add',
	handler : function() {
		add();
	}
}, '-', {
	text : '删除',
	iconCls : 'icon-remove',
	handler : function() {
		removeAchat();
	}
}, '-', {
	text : '编辑',
	iconCls : 'icon-edit',
	handler : function() {
		modifyAchat();
	}
}, '-', {
	text : '授权',
	iconCls : 'icon-set',
	handler : function() {
		accredit();
	}
} 

];

/**用于弹出供应商的DT**/
var datagridS = [{
	field : 'id',
	title : '编号',
	//hidden : true,
	width : 50
}, {
	field : 'username',
	title : '用户名',
	width : 200,
	align : 'center'
},{
	field : 'realname',
	title : '真实姓名',
	width : 100,
	align : 'center'
},{
	field : 'province',
	title : '省份',
	width : 100,
	align : 'center'
},{
	field : 'role_name',
	title : '角色名称',
	width : 100,
	align : 'center'
},{
	field : 'parseDate',
	title : '最后登录日期',
	width : 140,
	align : 'center'
}

];

/**
 * 打开分发至供应商窗口
 */
function toSupport(){
	
	var target = $('#achat_list').datagrid('getSelections');
	var user_id = target[0].create_user ;//发起流程用户ID
	if (target.length < 1) {
		$.messager.show( {
			msg : '请选择一条数据进行修改!',
			title : '提示'
		});
	}else if(target.length >1){
		$.messager.show( {
			msg : '只能选择一条数据进行修改!',
			title : '提示'
		});
	}else {
		var achat_id = target[0].id ;
		$("#supportWin").window("open") ;
		
		$('#support_list').datagrid({
			url : "user/getUserList?type=2&id="+user_id, // 这里可以是个json文件，也可以是个动态页面，还可以是个返回json串的function
			frozenColumns : [ [ {
				field : 'ck',
				checkbox : true
			} ] ],
			columns : [ datagridS ],
			rownumbers : true,
			idField : 'id',
			striped : true,
			pageSize : 25,
			pageList : [ 5,25, 35, 45, 55 ],
			nowrap : true,
			loadMsg : '数据加载中...请稍等',
			pagination : true,
			height : 'auto',
			fit : true,
			border : false,
			onDblClickRow : function(rowIndex, rowData) {

			}
		});
	}
	
}


/**
 * 确认选择的供应商
 */
function subSupport(){
	var target2 = $('#achat_list').datagrid('getSelections');//这是流程列表
	var target = $('#support_list').datagrid('getSelections');//这是供应商列表
	var json ={
		"support_id":target[0].id,
		"id":target2[0].id
	};
	
	if (target.length < 1) {
		$.messager.show( {
			msg : '请选择一条数据!',
			title : '提示'
		});
	}else if(target.length >1){
		$.messager.show( {
			msg : '只能选择一条数据进行!',
			title : '提示'
		});
	}else{
	    $.ajax({            
	        type:"POST",   //post提交方式默认是get
	        url:"achat/subSupport", 
	        data:json, 
	        error:function(request) {      // 设置表单提交出错                 
	            $("#showMsg").html(request);  //登录错误提示信息
	        },
	        success:function(data) {
	        	  if(data=="false"){
	        	  	  $("#showMsg_edit").html("系统错误");
	        	  	  return ;
	        	  }else{
	        		  	$("#achat_list").datagrid('reload');	
	        		  	$("#supportWin").window("close") ;
	        			$.messager.show( {
	        				msg : '分发成功',
	        				title : '提示'
	        			});
	        	  }
	        }            
	  });
	}
}

function queryAchat(){
	$("#queryAchatWin").window("open") ;
}
function add(){
	$("#addAchatWin").window("open") ;
}

function submitQuery(){
	var title = $("#title_query").val() ;
	var content = $("#content_query").val();
	if(title==""){
		title=undefined;
	}
	if(content==""){
		content=undefined;
	}
	var json ={
		"title":title,
		"content":content
	};
	$("#achat_list").datagrid('options').url = 'achat/getAchatList';
	$("#achat_list").datagrid('options').queryParams = json;
	$("#achat_list").datagrid('load');
	$('#queryAchatWin').window("close");
	
}

function modifyAchat(){
	var target = $('#achat_list').datagrid('getSelections');
	if (target.length < 1) {
		$.messager.show( {
			msg : '请选择一条数据进行修改!',
			title : '提示'
		});
	}else if(target.length >1){
		$.messager.show( {
			msg : '只能选择一条数据进行修改!',
			title : '提示'
		});
	}else{
		$("#modifyAchatWin").window("open") ;
		$("#achat_name_edit").val(target[0].achat_name);
		$("#remark_edit").val(target[0].remark) ;
	}
}

function closeWin(obj){
	$("#"+obj).window('close') ;
}
/**
 * 保存修改
 */
function saveEdit(){
	var target = $('#achat_list').datagrid('getSelections');
	var achat_name = $("#achat_name_edit").val() ;
	var realname = $("#realname_edit").val() ;
	var remark = $("#remark_edit").val() ;
	if(achat_name ==""|| remark ==""){
		$("#showMsg_edit").html("请将数据填写完整");
		return ;
	}else{
		$("#showMsg_edit").html("");
	}
	var json ={
		"id":target[0].id,
		"achat_name":achat_name,
		"remark":remark
	}
	
    $.ajax({            
        type:"POST",   //post提交方式默认是get
        url:"achat/edit", 
        data:json, 
        error:function(request) {      // 设置表单提交出错                 
            $("#showMsg").html(request);  //登录错误提示信息
        },
        success:function(data) {
        	  if(data=="false"){
        	  	  $("#showMsg_edit").html("系统错误");
        	  	  return ;
        	  }else{
        		  	$("#achat_list").datagrid('reload');	
        		  	$("#modifyAchatWin").window("close") ;
        			$.messager.show( {
        				msg : '修改成功',
        				title : '提示'
        			});
        	  }
        }            
  });
}
//删除数据
function removeAchat() {

	var list = new Array();
	var rows = $('#achat_list').datagrid('getSelections');
	if (rows.length != 0) {
		$.messager.confirm('询问', '您确定要删除所选中的数据吗?', function(answer) {
			if (answer) {
				for ( var i = 0; i < rows.length; i++) {
					list.push(rows[i].id);
				}
				$.ajax( {
					type:"get", 
					url : 'achat/delete?ids=' + list + '',
					cache : false,
					success : function(r) {
					$("#achat_list").datagrid('clearSelections'); // 清空选择状态
					$("#achat_list").datagrid('reload');
					$.messager.show( {
						msg : "删除成功！",
						title : '提示'
					});
				}
				});
			}
		});
	} else {
		$.messager.show( {
			msg : '请至少选中一行!',
			title : '提示'
		});
	}

}

$(function(){
	$("#addAchatWin").window("close") ;
	$("#modifyAchatWin").window("close") ;
	$("#queryAchatWin").window("close") ;
	$("#supportWin").window("close") ;
	$('#achat_list').datagrid({
		url : 'achat/getAchatList', // 这里可以是个json文件，也可以是个动态页面，还可以是个返回json串的function
		frozenColumns : [ [ {
			field : 'ck',
			checkbox : true
		} ] ],
		columns : [ datagridD ],
		rownumbers : true,
		idField : 'id',
		striped : true,
		pageSize : 25,
		pageList : [ 5,25, 35, 45, 55 ],
		nowrap : false,
		loadMsg : '数据加载中...请稍等',
		pagination : true,
		height : 'auto',
		fit : true,
		toolbar : "#tabrs",
		border : false,
		onDblClickRow : function(rowIndex, rowData) {

		}
	});
}) ;

function formClear(obj){
	$("#"+obj).form("clear") ;
}
