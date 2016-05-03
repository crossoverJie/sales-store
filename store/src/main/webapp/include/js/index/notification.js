
 var sv1 ;
$(function(){
	$('#notification_list').datagrid({
		url : "user/getNotification", // 这里可以是个json文件，也可以是个动态页面，还可以是个返回json串的function
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
		border : false,
		onDblClickRow : function(rowIndex, rowData) {

		}
	});
	
	
	 /**定时刷新通知**/
	 sv1 = setInterval(showTime, 2000);
	 function showTime(){
		 $("#notification_list").datagrid('reload');
	 }
	
}) ;

/**
 * 停止刷新
 */
function stopRefresh(){
	clearInterval(sv1);
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