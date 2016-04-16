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
	
	
	
});

