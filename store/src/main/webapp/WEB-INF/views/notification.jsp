<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
<!-- Bootstrap -->
<link rel="stylesheet"
	href="http://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">
<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->


<link href="<%=path%>/include/default/easyui.css" type="text/css"
	rel="stylesheet" />
<link href="<%=path%>/include/icon.css" type="text/css" rel="stylesheet" />
<link href="<%=path%>/include/easyui/themes/icon.css" type="text/css"
	rel="stylesheet" />
<script src="<%=path%>/include/js/jquery/jquery-1.7.1.min.js"
	type="text/javascript"></script>
<script src="<%=path%>/include/js/jquery.easyui.min.js"
	type="text/javascript"></script>
<script src="<%=path%>/include/js/index/index.js" type="text/javascript"></script>
<base href="<%=basePath%>">

<title>通知</title>
</head>

<body style="overflow: hidden;background: #ffffff;">
	<div id="ts">
		<select id="cc" class="easyui-combobox" name="dept">   
		    <option value="aa">请选择</option>   
		    <option>等待报价</option>   
		    <option>等待上架</option>   
		</select>
	</div>
	
	
	<div style="position:absolute;width:100%;height:100%;background: url('<%=basePath%>include/img/homebg.gif');background-repeat:no-repeat;padding-bottom:0px;background-position-x:right;background-position-y: bottom;background-position:right bottom">
		<div id="p" class="easyui-panel" title="通知"     
		        style="width:700px;height:300px;padding:20px;background:#fafafa;"   
		        data-options="iconCls:'icon-tip',  
		        		tools:'#ts',
		                collapsible:true,maximizable:true">
		                
		    <table class="table table-hover "  >
		    	<tr>
					<th>名称</th>
					<th>类别</th>
					<th>创建日期</th>
					<th>状态</th>
				</tr>
		    <c:forEach var="ls" items="${list }" varStatus="status" >
		    	<tr>
					<td>
						${ls.title }
					</td>
					<td>${ls.category_name }</td>
					<td>${ls.create_date }</td>
					<td>${ls.state }</td>
				</tr>
		    </c:forEach>
		    </table>           
		    
		    
		</div>
	</div>
	
	
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="http://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	
</body>
</html>
