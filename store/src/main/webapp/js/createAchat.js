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

