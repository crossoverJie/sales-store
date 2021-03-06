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
<link href="<%=path%>/include/css/frontskin.css" type="text/css"
	rel="stylesheet" />
<link href="<%=path%>/include/default/easyui.css" type="text/css"
	rel="stylesheet" />
<link href="<%=path%>/include/icon.css" type="text/css" rel="stylesheet" />
<link href="<%=path%>/include/easyui/themes/icon.css" type="text/css"
	rel="stylesheet" />

<script src="<%=path%>/include/js/jquery/jquery-1.7.1.min.js"
	type="text/javascript"></script>
<script src="<%=path%>/include/js/jquery.easyui.min.js"
	type="text/javascript"></script>
<script src="<%=path%>/include/js/category/category.js" type="text/javascript"></script>
<script src="<%=path%>/include/js/easyui-lang-zh_CN.js"
	type="text/javascript"></script>
<base href="<%=basePath%>">

<title></title>
</head>

<body>
	<table id="category_list"></table>
	
	<div id="queryCategoryWin" class="easyui-window" align="left" title="查询" style="width:460px; height: 200px">
		<form id="queryCategoryForm" method="post">
			<table style="width: 450px; overflow: hidden;">
				<br>
				<tr>
					<td class="thead">
						<div align="center">功能名称:</div>
					</td>
					<td><input  class="easyui-validatebox"
						id="category_name_query"  />
					</td>
				</tr>
				<tr>
					<td class="thead">
						<div align="center">功能地址:</div>
					</td>
					<td><input  class="easyui-validatebox"
						id="category_url_query" />
					</td>
				</tr>
			</table>
			<div style="text-align: center; padding: 8px;margin-right: 130px">
				<a href="javascript:void(0)" onclick="submitQuery()"
					class="easyui-linkbutton" iconCls="icon-ok">查 询</a> <a
					href="javascript:void(0)" onclick="formClear('queryCategoryForm')"
					class="easyui-linkbutton" iconCls="icon-cancel">重 置</a>
			</div>
		</form>
	</div>
	
	<!-- add -->
	<div id="addCategoryWin" class="easyui-window" title="新增数据"
		data-options="iconCls:'icon-save'"
		style="width: 500px; height: 180px; padding: 10px;">
		<form id="addCategoryForm" method="post">
			<table style="width: 350px; overflow: hidden;">
				<tr>
					<th>功能类型</th>
					<td >
						<select id="category_type_add" class="easyui-combobox" style="width:100px;">   
						    <option value="1">一级分类</option>   
						    <option value="2">二级分类</option> 
						    <option value="3">三级分类</option>   
						</select>  
					</td>
					<th>一级分类</th>
					<td ><input type="text" id="funtion_type_one_add" style="width:100px;" /></td>
				</tr>
				<tr>
					<th>二级分类</th>
					<td ><input type="text" id="funtion_type_two_add" style="width:100px;" /></td>
					<th>分类名称</th>
					<td><input type="text" id="category_name_add" style="width:100px;" /></td>
				</tr>
			</table>
			<div style="padding:5px 0;text-align: center;color: red;"
						id="showMsg"></div>
			<div style="text-align: center; padding: 8px;">
				<a href="javascript:void(0)" id="add-btn" class="easyui-linkbutton" onclick="turnToAdd()"
					iconCls="icon-ok">新 增</a> <a href="javascript:void(0)"
					onclick="formClear('addCategoryForm')" class="easyui-linkbutton"
					iconCls="icon-cancel">重 置</a>
			</div>
		</form>
	</div>
	
	<div id="modifyCategoryWin" class="easyui-window" title="编辑数据"
		data-options="iconCls:'icon-edit'">
		<form id="modifyCategoryForm" method="post">
			<table style="width: 450px; overflow: hidden;">
				<tr>
					<th>类别名称</th>
					<td ><input type="text" id="category_name_edit" style="width:200px;" /></td>
				</tr>
			</table>
			<div style="padding:5px 0;text-align: center;color: red;"
						id="showMsg_edit"></div>
			<div style="text-align: center; padding: 8px;">
				<a href="javascript:void(0)" id="edit_btn" class="easyui-linkbutton"
					onclick="saveEdit()"
					iconCls="icon-ok">修 改</a> <a href="javascript:void(0)"
					id="edit_cancel_btn" class="easyui-linkbutton" onclick="closeWin('modifyCategoryWin')"
					iconCls="icon-cancel">取消</a>
			</div>
		</form>
	</div>
	
</body>
</html>
