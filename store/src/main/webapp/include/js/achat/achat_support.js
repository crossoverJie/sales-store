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


/**
 * 打开报价窗口
 */
function toSupport(){
	
	var target = $('#achat_list').datagrid('getSelections');
	if (target.length < 1) {
		$.messager.show( {
			msg : '请选择一条数据进行报价!',
			title : '提示'
		});
	}else if(target.length >1){
		$.messager.show( {
			msg : '只能选择一条数据进行报价!',
			title : '提示'
		});
	}else {
		var state = target[0].state ;
		if(state =="会员处理中"){
			$.messager.show( {
				msg : '不能重复报价',
				title : '提示'
			});
			return;
		}
		
		var achat_id = target[0].id ;
		$("#supportWin").window("open") ;
	}
	
}


/**
 * 进行报价
 */
function subSupportPrice(){
	var target = $('#achat_list').datagrid('getSelections');//这是流程列表
	var price = $("#price").numberbox("getValue");
	var json ={
		"support_price":price,
		"id":target[0].id
	};
		
		
		
	$.ajax({            
		type:"POST",   //post提交方式默认是get
		url:"achat/subSupportPrice", 
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
					msg : '报价成功',
					title : '提示'
				});
			}
		}            
	});
		
	
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
	$("#achat_list").datagrid('options').url = 'achat/getAchatList_notAdmin';
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


$(function(){
	$("#addAchatWin").window("close") ;
	$("#modifyAchatWin").window("close") ;
	$("#queryAchatWin").window("close") ;
	$("#supportWin").window("close") ;
	$('#achat_list').datagrid({
		url : 'achat/getAchatList_notAdmin', //查看供应商报价中的
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
