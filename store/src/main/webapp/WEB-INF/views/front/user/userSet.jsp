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
<title>欢迎来到咨询网</title>
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
    	.col-md-12 img{
    	    height: 150px;
    		width: 150px;
    	}
    </style>
</head>
<body>

	<jsp:include page="../../../../public/nav-top.jsp"></jsp:include>
	
	<hr/>
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12">
				<img src="
				<c:choose>
					<c:when test="${empty headimg}">
						<%=path %>/include/img/person.gif
					</c:when>
					<c:otherwise>
						<%=path %>/${headimg}
					</c:otherwise>
				</c:choose>
				" class="img-responsive center-block img-circle" alt="Responsive image">
			</div>
		</div>
	</div>
	<hr/>
<!-- 栅格系统 -->
<div class="container-fluid">
	<div class="row">
		
		<div class="col-md-12">
			  <ul class="nav nav-tabs" role="tablist" id="tab-list">
			    <li role="presentation" class="active"><a href="#baseSet" aria-controls="home" role="tab" data-toggle="tab">基本设置</a></li>
			    <li role="presentation"><a href="#updatePassword" aria-controls="profile" role="tab" data-toggle="tab">修改密码</a></li>
			  </ul>
			  
			  <div class="tab-content">
		    	<div role="tabpanel" class="tab-pane active" id="baseSet">
		    		<div class="panel panel-info">
		    		<form method="post" action="<%=path%>/index/updateBaseUserInfo">
					<table class="table" id="baseSetTable">
						<tr>
							<td class="td-head"><h4>真实姓名</h4></td>
						 	<td>
								<h4><input type="text" class="form-control" name="realname" id="realname" value="${currentUser.realname }" /></h4>
							</td>
						</tr>
						<tr>
							<td class="td-head"><h4>省份</h4></td>
							<td>
								<select name="province" class="form-control input-sm" style="width: 200px">
									<option value="北京">北京市</option>
                                    <option value="浙江省">浙江省</option>
                                    <option value="天津市">天津市</option>
                                    <option value="安徽省">安徽省</option>
                                    <option value="上海市">上海市</option>
                                    <option value="福建省">福建省</option>
                                    <option value="重庆市">重庆市</option>
                                    <option value="江西省">江西省</option>
                                    <option value="山东省">山东省</option>
                                    <option value="河南省">河南省</option>
                                    <option value="湖北省">湖北省</option>
                                    <option value="湖南省">湖南省</option>
                                    <option value="广东省">广东省</option>
                                    <option value="海南省">海南省</option>
                                    <option value="山西省">山西省</option>
                                    <option value="青海省">青海省</option>
                                    <option value="江苏省">江苏省</option>
                                    <option value="辽宁省">辽宁省</option>
                                    <option value="吉林省">吉林省</option>
                                    <option value="台湾省">台湾省</option>
                                    <option value="河北省">河北省</option>
                                    <option value="贵州省">贵州省</option>
                                    <option value="四川省">四川省</option>
                                    <option value="云南省">云南省</option>
                                    <option value="陕西省">陕西省</option>
                                    <option value="甘肃省">甘肃省</option>
                                    <option value="黑龙江省">黑龙江省</option>
                                    <option value="香港特别行政区">香港特别行政区</option>
                                    <option value="澳门特别行政区">澳门特别行政区</option>
                                    <option value="广西壮族自治区">广西壮族自治区</option>
                                    <option value="宁夏回族自治区">宁夏回族自治区</option>
                                    <option value="新疆维吾尔自治区">新疆维吾尔自治区</option>
                                    <option value="内蒙古自治区">内蒙古自治区</option>
								</select>
							</td>
						</tr>
						<tr>
							<td>
								<input type="submit" class="btn btn-success" value="保存"/>
							</td>
						</tr>
					</table>
					</form>
				</div>
		    </div>
		    
		    
			<div role="tabpanel" class="tab-pane" id="updatePassword">
				<div class="panle panel-info">
					<table class="table" id="baseSetTable">
						<tr>
							<td class="td-head"><h4>当前密码</h4></td>
							<td>
								<h4>
								<div class="form-group" id="div-currentpPwd">
									<input type="password" class="form-control"  id="currentPwd"/>
								</div>
								</h4>
							</td>
						</tr>
						<tr>
							<td class="td-head"><h4>新密码</h4></td>
							<td>
								<h4><input type="password" class="form-control " id="newPwd" /></h4>
							</td>
						</tr>
						<tr>
							<td class="td-head"><h4>确认密码</h4></td>
							<td>
								<h4><input type="password" class="form-control" id="newPwd2" /></h4>
							</td>
						</tr>
						<tr>
							<td>
								<input type="hidden" id="hiddenId" value="${user.id }">
								<button class="btn btn-success" onclick="savePassWord();">保存</button>
							</td>
							<td>
								<blockquote>
									<p class="text-danger">密码修改成功后需要重新登录！</p>
								</blockquote>
							</td>
						</tr>
					</table>
				</div>
			</div>
			
			
  		</div>
  
  
		</div>
	</div>
</div>




<!-- 弹出框 模态框关于 -->
<div class="modal fade" id="about" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">关于</h4>
      </div>
      <div class="modal-body">
      	<p>
      		自由开发者crossoverJie
      	</p>
   		<address>
   			<a href="mailto:731756881@qq.com">731756881@qq.com</a>
   		</address>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
      </div>
    </div>
  </div>
</div>
<!-- 底部导航栏 -->
<jsp:include page="../../../../public/nav-bottom.jsp"></jsp:include>


	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="http://cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="http://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	<script src="<%=path%>/js/userSet.js" type="text/javascript"></script>
	<script src="<%=path%>/include/js/jquery.easyui.min.js"
	type="text/javascript"></script>
	<script src="<%=path%>/include/js/easyui-lang-zh_CN.js"
	type="text/javascript"></script>
</body>
</html>
