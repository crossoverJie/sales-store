var treegridD;
// 初始化treegrid

treegridD = [{
	field : 'id',
	title : '编号',
	//hidden : true,
	width : 50
}, {
	field : 'parent_id',
	title : '父节点',
	width : 200,
	align : 'center'
},{
	field : 'category_name',
	title : '功能名称',
	width : 100,
	align : 'center'
},{
	field : 'category_url',
	title : '功能地址',
	width : 180,
	align : 'center'
},{
	field : 'remark',
	title : '备注',
	width : 150,
	align : 'center'
}

];

var tabrs;
tabrs = [ {

	text : '查询',
	iconCls : 'icon-search',
	handler : function() {
		queryCategory();
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
		removeCategory();
	}
}, '-', {
	text : '编辑',
	iconCls : 'icon-edit',
	handler : function() {
		modifyCategory();
	}
}, '-', {
	text : '展开',
	iconCls : 'icon-redo',
	handler : function() {
		var node = $("#category_list").treegrid('getSelected');
		if (node) {
			$("#category_list").treegrid('expandAll', node.id);
		} else {
			$("#category_list").treegrid('expandAll');
		}
	}
}, '-', {
	text : '折叠',
	iconCls : 'icon-undo',
	handler : function() {
		var node = $("#category_list").treegrid('getSelected');
		if (node) {
			$("#category_list").treegrid('collapseAll', node.id);
		} else {
			$("#category_list").treegrid('collapseAll');
		}
	}
}, '-', {
	text : '取消选中',
	iconCls : 'icon-undo',
	handler : function() {
		$("#category_list").treegrid('unselectAll');
	}
}, '-', {
	text : '刷新',
	iconCls : 'icon-reload',
	handler : function() {
		refresh();
	}
} 
];

function queryCategory(){
	$("#queryCategoryWin").window("open") ;
}
function add(){
	$("#addCategoryWin").window("open") ;
	var type = $("#category_type_add").combobox("getValue") ;
	if(type=="1"){
		$("#funtion_type_one_add").combobox({
			disabled:true
		});
		$("#funtion_type_two_add").combobox({
			disabled:true
		})
	}
	//初始化所有一级菜单
	$("#funtion_type_one_add").combobox({
		url:"category/getAllone",
		valueField:'id', 
		textField:'name'   
	});
	
	//初始化所有二级菜单
	$("#funtion_type_two_add").combobox({
		url:"category/getAlltwo",
		valueField:'id', 
		textField:'name'   
	});
}

function submitQuery(){
	var category_name = $("#category_name_query").val() ;
	var category_url = $("#category_url_query").val() ;
	if(category_name==""){
		category_name=undefined;
	}
	if(category_url ==""){
		category_url=undefined;
	}
	var json ={
		"category_name":category_name,
		"category_url":category_url
	};
	$("#category_list").treegrid('options').url = 'function/getCategoryList';
	$("#category_list").treegrid('options').queryParams = json;
	$("#category_list").treegrid('load');
	$('#queryCategoryWin').window("close");
	
}

function modifyCategory(){
	var target = $('#category_list').treegrid('getSelections');
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
		$("#modifyCategoryWin").window("open") ;
		$("#category_name_edit").val(target[0].name);
		$("#category_url_edit").val(target[0].category_url);
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
	var target = $('#category_list').treegrid('getSelections');
	var category_name = $("#category_name_edit").val() ;
	var category_url = $("#category_url_edit").val() ;
	var remark = $("#remark_edit").val() ;
	if(category_name =="" ){
		$("#showMsg_edit").html("请将数据填写完整");
		return ;
	}else{
		$("#showMsg_edit").html("");
	}
	var json ={
		"id":target[0].id,
		"category_name":category_name,
		"category_url":category_url,
		"remark":remark
	}
	
    $.ajax({            
        type:"POST",   //post提交方式默认是get
        url:"category/edit", 
        data:json, 
        error:function(request) {      // 设置表单提交出错                 
            $("#showMsg").html(request);  //登录错误提示信息
        },
        success:function(data) {
        	  if(data=="false"){
        	  	  $("#showMsg_edit").html("系统错误");
        	  	  return ;
        	  }else{
        		  	$("#category_list").treegrid('reload');	
        		  	$("#modifyCategoryWin").window("close") ;
        			$.messager.show( {
        				msg : '修改成功',
        				title : '提示'
        			});
        	  }
        }            
  });
}
/**
 * 新增之前的验证
 */
function turnToAdd(){
	var level = $("#category_type_add").combobox("getValue") ;
	var category_name = $("#category_name_add").val() ;
	var category_type_one_add = $("#funtion_type_one_add").combobox("getValue");
	var category_type_two_add = $("#funtion_type_two_add").combobox("getValue");
	
	//定义一个变量保存功能名称 因为有不同的情况 选择一级功能和二级功能。
	var name="" ;
	if(category_name != ""){
		name= category_name;
	}else if(category_type_one_add != ""){
		name= category_type_one_add;
	}else if(category_type_two_add !=""){
		name = category_type_two_add;
	}
	
	var parent_id  ;
	if(category_type_one_add != ""){
		parent_id = category_type_one_add;
	}else if (category_type_two_add != ""){
		parent_id = category_type_two_add;
	}else {
		parent_id = -1;
	}
	
	if(name =="" ||  level==""){
		$("#showMsg").html("请将数据填写完整");
		return ;
	}else{
		$("#showMsg").html("");
	}
	
	var json = {
		"name": category_name,
		"level":level,
		"parent_id":parent_id
	};

    $.ajax({            
        type:"POST",   //post提交方式默认是get
        url:"category/create", 
        data:json, 
        error:function(request) {      // 设置表单提交出错                 
            $("#showMsg").html(request);  //登录错误提示信息
        },
        success:function(data) {
        	  if(data=="false"){
        	  	  $("#showMsg").html("系统错误");
        	  	  return ;
        	  }else{
        		  	$("#category_list").treegrid('reload');	
        		  	$("#addCategoryWin").window("close") ;
        			$.messager.show( {
        				msg : '新增成功',
        				title : '提示'
        			});
        	  }
        }            
  }); 
}

//删除数据
function removeCategory() {

	var list = new Array();
	var parent_id= "";
	var rows = $('#category_list').treegrid('getSelections');
	if (rows.length != 0) {
		$.messager.confirm('询问', '您确定要删除所选中的数据吗?', function(answer) {
			if (answer) {
				for ( var i = 0; i < rows.length; i++) {
					list.push(rows[i].id);
					parent_id = rows[0].parent_id;
				}
				$.ajax( {
					type:"POST", 
					url : 'category/delete?ids=' + list + '&parent_id='+parent_id,
					cache : false,
					success : function(r) {
					$("#category_list").treegrid('clearSelections'); // 清空选择状态
					$("#category_list").treegrid('reload');
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
	$("#addCategoryWin").window("close") ;
	$("#modifyCategoryWin").window("close") ;
	$("#queryCategoryWin").window("close") ;
	
	$('#category_list').treegrid({    
		url : 'category/getCategoryList', 
	    idField:'id',    
	    treeField:'name',   
		rownumbers : true,
		toolbar : tabrs,
		loadMsg : '数据加载中...请稍等',
		height : 'auto',
		animate:true,
		fit : true,
	    columns:[[
	              {title:'类别名称',field:'name',width:180},  
	          ]]
	});
	
	
	$("#category_type_add").combobox({
		onSelect:function(obj){
			var type = obj.value;
			if(type=='2'){
				$("#funtion_type_one_add").combobox({
					disabled:false
				});
				$("#funtion_type_two_add").combobox({
					disabled:true
				});
			}else if(type='3'){
				$("#funtion_type_two_add").combobox({
					disabled:false
				});
				$("#funtion_type_one_add").combobox({
					disabled:true
				})
			}else if(type='1'){
				$("#funtion_type_one_add").combobox({
					disabled:true
				})
			}
		}
	});
	
}) ;

function formClear(obj){
	$("#"+obj).form("clear") ;
}

function refresh(){
	$("#category_list").treegrid('reload');
}
