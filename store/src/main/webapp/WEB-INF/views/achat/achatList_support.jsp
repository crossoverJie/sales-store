<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<link href="<%=path%>/include/default/easyui.css" type="text/css"
	rel="stylesheet" />
<link href="<%=path%>/include/icon.css" type="text/css" rel="stylesheet" />
<link href="<%=path%>/include/easyui/themes/icon.css" type="text/css"
	rel="stylesheet" />
<link href="<%=path%>/include/css/frontskin.css" type="text/css"
	rel="stylesheet" />
<script src="<%=path%>/include/js/jquery/jquery-1.7.1.min.js"
	type="text/javascript"></script>
<script src="<%=path%>/include/js/jquery.easyui.min.js"
	type="text/javascript"></script>
<script src="<%=path%>/include/js/achat/achat_support.js" type="text/javascript"></script>
<script src="<%=path%>/include/js/easyui-lang-zh_CN.js"
	type="text/javascript"></script>
<base href="<%=basePath%>">

<title></title>
</head>

<body>
	<table id="achat_list"></table>
	<div id="tabrs">
		标题:<input  class="easyui-validatebox" id="title_query"  />
		内容:<input  class="easyui-validatebox" id="content_query" />
		开始时间:<input class="easyui-datebox" id="start_date" />
		截止时间:<input class="easyui-datebox" id="end_date" />
		<a href="javascript:void(0)" onclick="submitQuery();"  plain="true" class="easyui-linkbutton" iconCls="icon-search">查询</a>
		<a href="javascript:void(0)" onclick="toSupport();"  plain="true" class="easyui-linkbutton" iconCls="icon-redo">报价</a>
		<a href="javascript:void(0)" onclick="toAddProduce();"  plain="true" class="easyui-linkbutton" iconCls="icon-ok">上架商品</a>
	</div>
	
	
	<div id="supportWin" class="easyui-window" align="left" title="报价" style="width:200px; height: 100px">
		<div class="easyui-layout" fit="true">
			<div region="center" border="true" >
				<input type="text" id="price" class="easyui-numberbox" value="10" data-options="min:0,precision:2"/>
			</div>
			
			<div region="south" border="true" style="height:30px;padding:2px;overflow: hidden;">
				<a class="easyui-linkbutton" iconCls="icon-ok" href="javascript:void(0)" onclick="subSupportPrice();" style="float: right;">确认</a>
			</div>
		</div>
	</div>
	
	
	<div id="produceWin" class="easyui-window" align="left" title="选择上架产品" style="width:600px; height: 460px">
		<div class="easyui-layout" fit="true">
			<div region="center" border="true" >
				<table id="produce_list"></table>
			</div>
			
			<div region="south" border="true" style="height:30px;padding:2px;overflow: hidden;">
				<a class="easyui-linkbutton" iconCls="icon-ok" href="javascript:void(0)" onclick="subAddProduce();" style="float: right;">确认</a>
			</div>
		</div>
	</div>
	
	
</body>
</html>
