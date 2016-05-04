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
<script src="<%=path%>/include/js/easyui-lang-zh_CN.js" type="text/javascript"></script>
<script src="<%=path%>/include/js/user/user.js" type="text/javascript"></script>
<base href="<%=basePath%>">

<title></title>
</head>

<body>
	<table id="user_list"></table>
	<div id="tabrs">
		用户名:<input  class="easyui-validatebox" id="username_query"  />
		真实姓名:<input  class="easyui-validatebox" id="realname_query" />
		<a href="javascript:void(0)" onclick="submitQuery();"  plain="true" class="easyui-linkbutton" iconCls="icon-search">查询</a>
		<a href="javascript:void(0)" onclick="removeUser();"  plain="true" class="easyui-linkbutton" iconCls="icon-remove">删除</a>
		<a href="javascript:void(0)" onclick="modifyUser();"  plain="true" class="easyui-linkbutton" iconCls="icon-edit">编辑</a>
	</div>
	<input type="hidden" id="type" value="1">
	<div id="queryUserWin" class="easyui-window" align="left" title="查询" style="width:460px; height: 200px">
		<form id="queryUserForm" method="post">
			<table style="width: 450px; overflow: hidden;">
				<br>
				<tr>
					<td class="thead">
						<div align="center">用户名:</div>
					</td>
					<td><input  class="easyui-validatebox"
						id="username_query"  />
					</td>
				</tr>
				<tr>
					<td class="thead">
						<div align="center">真实姓名:</div>
					</td>
					<td><input  class="easyui-validatebox"
						id="realname_query" />
					</td>
				</tr>
			</table>
			<div style="text-align: center; padding: 8px;margin-right: 110px">
				<a href="javascript:void(0)" onclick="submitQuery()"
					class="easyui-linkbutton" iconCls="icon-ok">查 询</a> <a
					href="javascript:void(0)" onclick="formClear('queryUserForm')"
					class="easyui-linkbutton" iconCls="icon-cancel">重 置</a>
			</div>
		</form>
	</div>
	
	<!-- add -->
	<div id="addUserWin" class="easyui-window" title="新增数据"
		data-options="iconCls:'icon-save'"
		style="width: 360px; height: 270px; padding: 10px;">
		<form id="addUserForm" method="post">
			<table style="width: 350px; overflow: hidden;">
				<tr>
					<th>用户名</th>
					<td ><input type="text" id="username_add" style="width:200px;" /></td>
				</tr>
				<tr>
					<th>真实姓名</th>
					<td><input type="text" id="realname_add" style="width:200px;" /></td>
				</tr>
				<tr>
					<th>备注</th>
					<td><input type="text" id="remark_add" style="width:200px;" /></td>
				</tr>
				<tr>
					<th>密码</th>
					<td><input type="password" id="pwd1" style="width:200px;" /></td>
				</tr>
				<tr>
					<th>确认密码</th>
					<td><input type="password" id="pwd2" style="width:200px;" /></td>
				</tr>
				<tr>
					<th>角色</th>
					<td><input type="text" id="role_id_add" style="width:200px;" /></td>
				</tr>
				
			</table>
			<div style="padding:5px 0;text-align: center;color: red;"
						id="showMsg"></div>
			<div style="text-align: center; padding: 8px;">
				<a href="javascript:void(0)" id="add-btn" class="easyui-linkbutton" onclick="turnToAdd()"
					iconCls="icon-ok">新 增</a> <a href="javascript:void(0)"
					onclick="formClear('addUserForm')" class="easyui-linkbutton"
					iconCls="icon-cancel">重 置</a>
			</div>
		</form>
	</div>
	
	<div id="modifyUserWin" class="easyui-window" title="编辑数据"
		data-options="iconCls:'icon-edit'">
		<form id="modifyUserForm" method="post">
			<table style="width: 450px; overflow: hidden;">
				<tr>
					<th>用户名</th>
					<td ><input type="text" id="username_edit" style="width:200px;" /></td>
				</tr>
				<tr>
					<th>真实姓名</th>
					<td><input type="text" id="realname_edit" style="width:200px;" /></td>
				</tr>
				<tr>
					<th>省份</th>
					<td>
					<select id="province_edit" class="easyui-combobox" style="width:200px;">   
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
					<th>角色</th>
					<td><input type="text" id="role_id_edit" style="width:200px;" /></td>
				</tr>
			</table>
			<div style="padding:5px 0;text-align: center;color: red;"
						id="showMsg_edit"></div>
			<div style="text-align: center; padding: 8px;">
				<a href="javascript:void(0)" id="edit_btn" class="easyui-linkbutton"
					onclick="saveEdit()"
					iconCls="icon-ok">修 改</a> <a href="javascript:void(0)"
					id="edit_cancel_btn" class="easyui-linkbutton" onclick="closeWin('modifyUserWin')"
					iconCls="icon-cancel">取消</a>
			</div>
		</form>
	</div>
	
</body>
</html>
