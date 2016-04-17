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
<script src="<%=path%>/include/js/achat/achat.js" type="text/javascript"></script>
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
		<a href="javascript:void(0)" onclick="submitQuery();"  plain="true" class="easyui-linkbutton" iconCls="icon-search">查询</a>
		<a href="javascript:void(0)" onclick="toSupport();"  plain="true" class="easyui-linkbutton" iconCls="icon-redo">分发供应商</a>
		<a href="javascript:void(0)" onclick="removeAchat();"  plain="true" class="easyui-linkbutton" iconCls="icon-remove">删除</a>
	</div>
	
	
	<div id="supportWin" class="easyui-window" align="left" title="分发至供应商" style="width:700px; height: 460px">
		<div class="easyui-layout" fit="true">
			<div region="center" border="true" >
				<table id="support_list"></table>
			</div>
			
			<div region="south" border="true" style="height:30px;padding:2px;overflow: hidden;">
				<a class="easyui-linkbutton" iconCls="icon-ok" href="javascript:void(0)" onclick="subSupport();" style="float: right;">确认</a>
			</div>
		</div>
	</div>
	<!-- add -->
	
	<div id="modifyAchatWin" class="easyui-window" title="编辑数据"
		data-options="iconCls:'icon-edit'">
		<form id="modifyAchatForm" method="post">
			<table style="width: 450px; overflow: hidden;">
				<tr>
					<th>角色名</th>
					<td ><input type="text" id="achat_name_edit" style="width:200px;" /></td>
				</tr>
				<tr>
					<th>备注</th>
					<td><input type="text" id="remark_edit" style="width:200px;" /></td>
				</tr>
			</table>
			<div style="padding:5px 0;text-align: center;color: red;"
						id="showMsg_edit"></div>
			<div style="text-align: center; padding: 8px;">
				<a href="javascript:void(0)" id="edit_btn" class="easyui-linkbutton"
					onclick="saveEdit()"
					iconCls="icon-ok">修 改</a> <a href="javascript:void(0)"
					id="edit_cancel_btn" class="easyui-linkbutton" onclick="closeWin('modifyAchatWin')"
					iconCls="icon-cancel">取消</a>
			</div>
		</form>
	</div>
	
</body>
</html>
