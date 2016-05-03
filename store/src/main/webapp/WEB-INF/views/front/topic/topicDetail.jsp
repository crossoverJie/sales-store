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
<title>${produce.name }</title>
<!-- Bootstrap -->
<link rel="stylesheet"
	href="http://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">
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
    <style type="text/css">
    	#baseSetTable tr .td-head{
    		width: 100px;
    	}
    	
    	/* 给初始化文章内容的时候为所有照片设置尺寸 */
    	#index-src {
    		width: 100%;
    		height: 100%;
    	}
    	.review-panel{
    		height: 330px;
    	}
    	.hint-p{
    		padding-top: 90px;
    	}
    	#head-topic{
    		width: 75px;
    		height: 75px;
    	}
    	.panel-primary {
		    height: 510px;
		}
    </style>
</head>
<body class="">
<jsp:include page="../../../../public/nav-top.jsp"></jsp:include>

	
	
	<hr/>
		
<!-- 栅格系统 -->
<div class="jumbotron">
	<div class="container">
	  <h1>${produce.name}</h1>
	</div>
</div>

<div class="container-fluid">
	<div class="row">
		<div class="col-md-12">
			<img src="<%=path %>/${produce.path }" class="img-responsive center-block img-circle" alt="Responsive image">
			<blockquote>
				<p class="text-center text-danger">
					${produce.remark }
				</p>
			</blockquote>
		</div>
	</div>
	<hr/>
	<form>
  <div class="form-group">
    <label >名称</label>
    <input type="text" required="required" class="form-control" id="name" placeholder="名称">
  </div>
  <div class="form-group">
    <label >备注</label>
    <textarea rows="" cols="" class="form-control" required="required" placeholder="备注"></textarea>
    <input type="hidden" name="" value="${produce.id }"/>
  </div>
  <p class="text-right">
  <button type="submit" class="btn btn-primary btn-lg">购&nbsp;&nbsp;&nbsp;买</button>
  </p>
</form>
</div>





<!-- 引入底部jsp -->
<jsp:include page="../../../../public/nav-bottom.jsp"></jsp:include>

<!-- 弹出框 模态框关于 -->
<!-- 弹出框 模态框登录 -->
<jsp:include page="../../../../public/login&register.jsp"></jsp:include>
<!-- 弹出框 模态框注册 -->







	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="http://cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="http://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	
	<script src="<%=path%>/include/js/jquery.easyui.min.js"
	type="text/javascript"></script>
	<script src="<%=path%>/include/js/easyui-lang-zh_CN.js"
	type="text/javascript"></script>
	<script src="<%=path%>/js/topDetail.js" type="text/javascript"></script>
</body>
</html>
