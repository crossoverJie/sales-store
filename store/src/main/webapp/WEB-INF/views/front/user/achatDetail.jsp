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
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<title>采购信息列表</title>
<!-- Bootstrap -->
<link rel="stylesheet"
	href="http://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link href="<%=path%>/include/icon.css" type="text/css" rel="stylesheet" />
<link href="<%=path%>/include/easyui/themes/icon.css" type="text/css"
	rel="stylesheet" />
<link href="<%=path%>/include/default/easyui.css" type="text/css"
	rel="stylesheet" />
<link href="<%=path%>/include/icon.css" type="text/css" rel="stylesheet" />
<link href="<%=path%>/include/easyui/themes/icon.css" type="text/css"
	rel="stylesheet" />

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
<link href="<%=path%>/css/index.css" type="text/css"
	rel="stylesheet" />
</head>
<body>
<jsp:include page="../../../../public/nav-top.jsp"></jsp:include>

	<hr/>
<!-- 栅格系统 -->
<div class="container-fluid">
	<div class="row">
		
		<div class="col-md-12">
			<div class="panel panel-primary">
				
				<div class="panel-heading">
					<div class="row">
						<div class="col-md-4">
							<h4>流程列表</h4>
						</div>
						 <div class="col-md-4 col-md-offset-4">
							<div class="dropdown">
							  <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
							    	请选择
							    <span class="caret"></span>
							  </button>
							  <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
							    <li><a href="#">管理员处理中</a></li>
							    <li><a href="#">供应商处理中</a></li>
							    <li><a href="#">个人处理中</a></li>
							    <li><a href="#">商品上架中</a></li>
							  </ul>
							</div>
						</div>
					</div>
					
					
				</div>

			
				<div class="panel-body">
					<table class="table table-hover "  >
						<tr>
							<th>名称</th>
							<th>类别</th>
							<th>内容</th>
							<th>创建日期</th>
							<th>状态</th>
						</tr>
						
						<c:forEach var="al" items="${achatlist }" varStatus="status" >
							<tr>
								<td>${al.title }</td>
								<td>${al.category_name }</td>
								<td>${al.content }</td>
								<td>${al.create_date }</td>
								<td>${al.state }</td>
							</tr>
						</c:forEach>
						
					</table>
				</div>
			</div>
			
		</div>
		
  
  
	</div>
</div>





<jsp:include page="../../../../public/nav-bottom.jsp"></jsp:include>

<!-- 弹出框 模态框关于 -->
<jsp:include page="../../../../public/login&register.jsp"></jsp:include>


	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="http://cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="http://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	
	<script src="<%=path%>/include/js/jquery.easyui.min.js"
	type="text/javascript"></script>
	<script src="<%=path%>/include/js/easyui-lang-zh_CN.js"
	type="text/javascript"></script>
	<script src="<%=path%>/js/createAchat.js" type="text/javascript"></script>
	
</body>
</html>
