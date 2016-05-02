var datagridD;
// 初始化datagrid

datagridD = [{
	field : 'id',
	title : '编号',
	//hidden : true,
	width : 50
}, {
	field : 'name',
	title : '名称',
	width : 100,
	align : 'center'
},{
	field : 'category_name',
	title : '类别',
	width : 100,
	align : 'center'
},{
	field : 'model',
	title : '型号',
	width : 100,
	align : 'center'
},{
	field : 'kucun_number',
	title : '库存数量',
	width : 100,
	align : 'center'
},{
	field : 'url',
	title : '图片',
	width : 180,
	align : 'center'
},{
	field : 'remark',
	title : '描述',
	width : 180,
	align : 'center'
}


];

var tabrs;
tabrs = [ {

	text : '查询',
	iconCls : 'icon-search',
	handler : function() {
		queryProduce();
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
		removeProduce();
	}
}, '-', {
	text : '编辑',
	iconCls : 'icon-edit',
	handler : function() {
		modifyProduce();
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
 * 打开新增商品界面
 */
function turnToAddProduce(){
	$("#addProduceWin").window("open");
	$("#remark_add").val("") ;
	$("#category_one").combobox({
		url:"main/getCategory?level=1",
		valueField:'id', 
		textField:'text',
		onSelect : function(obj){
	    	$("#category_two").combobox({    
	    	    url:"main/getCategoryByParent?parent_id="+obj.id,    
	    	    valueField:'id',    
	    	    textField:'text',
	    	    onSelect : function(obj){
	    	    	$("#category_three").combobox({    
	    	    	    url:"main/getCategoryByParent?parent_id="+obj.id,    
	    	    	    valueField:'id',    
	    	    	    textField:'text'
	    	    	});
	    	    }
	    	});
	    }
	});
}


function queryProduce(){
	$("#queryProduceWin").window("open") ;
}
function add(){
	$("#addProduceWin").window("open") ;
}

function submitQuery(){
	var name = $("#name_query").val() ;
	if(name==""){
		name=undefined;
	}
	var json ={
		"name":name
	};
	$("#produce_list").datagrid('options').url = 'produce/getProduceList';
	$("#produce_list").datagrid('options').queryParams = json;
	$("#produce_list").datagrid('load');
	
}

function modifyProduce(){
	var target = $('#produce_list').datagrid('getSelections');
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
		$("#modifyProduceWin").window("open") ;
		$("#name_edit").val(target[0].name);
		$("#model_edit").val(target[0].model);
		$("#remark_edit").val(target[0].remark) ;
		$("#kucun_number_edit").numberspinner("setValue",target[0].kucun_number);
	}
}

function closeWin(obj){
	$("#"+obj).window('close') ;
}
/**
 * 保存修改
 */
function saveEdit(){
	var target = $('#produce_list').datagrid('getSelections');
	var number = $("#kucun_number_edit").numberspinner("getValue");
	var name =$("#name_edit").val() ;
	var model =$("#model_edit").val() ;
	var remark = $("#remark_edit").val() ;
	if(number ==""|| name =="" || model ==""){
		$("#showMsg_edit").html("请将数据填写完整");
		return ;
	}else{
		$("#showMsg_edit").html("");
	}
	var json ={
		"id":target[0].id,
		"name":name,
		"model":model,
		"kucun_number":number,
		"remark":remark
	}
	
    $.ajax({            
        type:"POST",   //post提交方式默认是get
        url:"produce/edit", 
        data:json, 
        error:function(request) {      // 设置表单提交出错                 
            $("#showMsg").html(request);  //登录错误提示信息
        },
        success:function(data) {
        	  if(data=="false"){
        	  	  $("#showMsg_edit").html("系统错误");
        	  	  return ;
        	  }else{
        		  	$("#produce_list").datagrid('reload');	
        		  	$("#modifyProduceWin").window("close") ;
        			$.messager.show( {
        				msg : '修改成功',
        				title : '提示'
        			});
        	  }
        }            
  });
}

//删除数据
function removeProduce() {

	var list = new Array();
	var rows = $('#produce_list').datagrid('getSelections');
	if (rows.length != 0) {
		$.messager.confirm('询问', '您确定要删除所选中的数据吗?', function(answer) {
			if (answer) {
				for ( var i = 0; i < rows.length; i++) {
					list.push(rows[i].id);
				}
				$.ajax( {
					type:"get", 
					url : 'produce/delete?ids=' + list + '',
					cache : false,
					success : function(r) {
					$("#produce_list").datagrid('clearSelections'); // 清空选择状态
					$("#produce_list").datagrid('reload');
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

/**
 * 提交保存商品
 */
function submitSave(){
	var name = $("#name_add").val() ;
	var model = $("#model_add").val() ;
	var cg1 = $("#category_one").combobox("getValue");
	var cg2 = $("#category_two").combobox("getValue");
	var cg3 = $("#category_three").combobox("getValue");
	var number = $("#kucun_number_add").numberspinner("getValue");
	var json ={
		"name":name,
		"category_id":cg3,
		"kucun_number" : number,
		"model":model
	};
	if(cg1=="" || cg2 =="" || cg3==""|| name == "" ||number=="" || model ==""){
		alert("请把内容填写完整！") ;
		return ;
	}else{
		$.ajax({            
	        type:"POST",   //post提交方式默认是get
	        url:"produce/create", 
	        data:json, 
	        error:function(request) {      // 设置表单提交出错                 
	            $("#showMsg").html(request);  //登录错误提示信息
	        },
	        success:function(data) {
	        	  if(data=="false"){
	        	  	  $("#showMsg_edit").html("系统错误");
	        	  	  return ;
	        	  }else{
	        		  	$("#produce_list").datagrid('reload');	
	        		  	$("#addProduceWin").window("close") ;
	        			$.messager.show( {
	        				msg : '保存成功',
	        				title : '提示'
	        			});
	        	  }
	        }            
	  });
	}
}


$(function(){
	$("#addProduceWin").window("close") ;
	$("#modifyProduceWin").window("close") ;
	$("#queryProduceWin").window("close") ;
	$("#supportWin").window("close") ;
	$('#produce_list').datagrid({
		url : 'produce/getProduceList', // 这里可以是个json文件，也可以是个动态页面，还可以是个返回json串的function
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
	
	
	/**
	 * 提交保存之前的验证
	 */
	$("#addProduceForm").submit(function(){
		
		var name = $("#name_add").val() ;
		var model = $("#model_add").val() ;
		var cg1 = $("#category_one").combobox("getValue");
		var cg2 = $("#category_two").combobox("getValue");
		var cg3 = $("#category_three").combobox("getValue");
		var number = $("#kucun_number_add").numberspinner("getValue");
		var json ={
			"name":name,
			"category_id":cg3,
			"kucun_number" : number,
			"model":model
		};
		if(cg1=="" || cg2 =="" || cg3==""|| name == "" ||number=="" || model ==""){
			alert("请把内容填写完整！") ;
			return ;
		}
		
		
		var file = $("#file").val() ;
		var index = file.lastIndexOf(".");
		file = file.substring(index + 1);
		if( file == "jpg" || file == "gif" || file == "png" ){
			return true;
		}else{
//			alert("只能上传图片") ;
			alert("请上传图片");
			return false ;
		}
	});
	
	
}) ;

function formClear(obj){
	$("#"+obj).form("clear") ;
}



