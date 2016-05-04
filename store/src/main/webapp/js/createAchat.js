$(function(){
	$("#content").val("");
	$("#category_one").combobox({
		url:"../main/getCategory?level=1",
		valueField:'id', 
		textField:'text',
		onSelect : function(obj){
	    	$("#category_two").combobox({    
	    	    url:"../main/getCategoryByParent?parent_id="+obj.id,    
	    	    valueField:'id',    
	    	    textField:'text',
	    	    onSelect : function(obj){
	    	    	$("#category_three").combobox({    
	    	    	    url:"../main/getCategoryByParent?parent_id="+obj.id,    
	    	    	    valueField:'id',    
	    	    	    textField:'text'
	    	    	});
	    	    }
	    	});
	    }
	});
	
	/**初始化或者是回显日期数据**/
	var hd_start_date =$("#hd_start_date").val();
	var hd_end_date =$("#hd_end_date").val();
	$("#start_date").datebox("setValue",hd_start_date);
	$("#end_date").datebox("setValue",hd_end_date);
	
	$("#createAchatForm").submit(function(){
		var title = $("#title").val() ;
		var content = $("#content").val();
		var cg1 = $("#category_one").combobox("getValue");
		var cg2 = $("#category_two").combobox("getValue");
		var cg3 = $("#category_three").combobox("getValue");
		if(cg1=="" || cg2 =="" || cg3==""|| title == "" || content ==""){
			alert("请把内容填写完整！") ;
			return false ;
		}else{
			return true ;
		}
	});
	
	
	
});

/**
 * 查询流程列表
 */
function submitQuery(){
	var start_date = $("#start_date").datebox("getValue") ;
	var end_date = $("#end_date").datebox("getValue") ;
	if(start_date !="" && end_date!=""){
		var json={
			"start_date":start_date,
			"end_date":end_date
		};
		window.location.href="../../user/achatDetail/1?start_date="+start_date+"&end_date="+end_date ;
		
//	    $.ajax({            
//	        type:"POST",   //post提交方式默认是get
//	        url:"../../user/achatDetail/1",
//	        async: false,
//	        data:json, 
//	        error:function(request) {      // 设置表单提交出错                 
//	            $("#showMsg").html(request);  //登录错误提示信息
//	        },
//	        success:function(data) {
//	        	
//	        }            
//	  });
		
	}
}

