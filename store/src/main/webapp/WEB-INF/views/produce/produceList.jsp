<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
<script src="<%=path%>/include/js/produce/produce.js" type="text/javascript"></script>
<script src="<%=path%>/include/js/easyui-lang-zh_CN.js"
	type="text/javascript"></script>
	
<script src="http://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<base href="<%=basePath%>">

<title></title>
</head>

<body>
	<table id="produce_list"></table>
	<div id="tabrs">
		名称:<input  class="easyui-validatebox" id="name_query"  />
		类别:<input id="category_query" class="easyui-combobox"  
    data-options="valueField:'id',textField:'name',url:'<%=path %>/category/getAllThree'" />
		<a href="javascript:void(0)" onclick="submitQuery();"  plain="true" class="easyui-linkbutton" iconCls="icon-search">查询</a>
		<a href="javascript:void(0)" onclick="turnToAddProduce();"  plain="true" class="easyui-linkbutton" iconCls="icon-add">上架商品</a>
		<a href="javascript:void(0)" onclick="modifyProduce();"  plain="true" class="easyui-linkbutton" iconCls="icon-edit">编辑</a>
		<a href="javascript:void(0)" onclick="removeProduce();"  plain="true" class="easyui-linkbutton" iconCls="icon-remove">删除</a>
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
	
	<div id="addProduceWin" class="easyui-window" title="新增数据"
		data-options="iconCls:'icon-save'"
		style="width: 460px; height: 370px; padding: 10px;">
		<form id="addProduceForm" action="<%=path %>/produce/create" method="post" enctype="multipart/form-data">
			<table style="width: 350px; overflow: hidden;">
				<tr>
					<th>一级类别</th>
					<td ><input type="text" id="category_one" /></td>
				</tr>
				<tr>
					<th>二级类别</th>
					<td><input id="category_two" class="easyui-combobox" />  </td>
				</tr>
				<tr>
					<th>三级类别</th>
					<td><input  id="category_three" name="category_id" class="easyui-combobox" /></td>
				</tr>
				<tr>
					<th>名称</th>
					<td><input type="text" id="name_add" name="name" /></td>
				</tr>
				<tr>
					<th>型号</th>
					<td><input type="text" id="model_add" name="model" /></td>
				</tr>
				<tr>
					<th>数量</th>
					<td><input name="kucun_number" id="kucun_number_add" width="173px" class="easyui-numberspinner" value="1" data-options="min:0" /></td>
				</tr>
				<tr>
					<th>图片</th>
					<td><input type="file" required  id="file" name="file"></td>
				</tr>
				<tr>
					<th>详情</th>
					<td>
						<textarea rows="" cols="" id="remark_add" name="remark">
						</textarea>
					</td>
				</tr>
				
			</table>
			<div style="padding:5px 0;text-align: center;color: red;"
						id="showMsg"></div>
			<div style="text-align: center; padding: 8px;">
				<input type="submit" value="新增" class="btn btn-primary" />
			</div>
			</form>
	</div>
	
	
	<div id="modifyProduceWin" class="easyui-window" title="编辑数据"
		data-options="iconCls:'icon-save'"
		style="width: 460px; height: 200px; padding: 10px;">
			<table style="width: 350px; overflow: hidden;">
				<tr>
					<th>名称</th>
					<td><input type="text" id="name_edit" name="name_add" /></td>
				</tr>
				<tr>
					<th>型号</th>
					<td><input type="text" id="model_edit" name="name_add" /></td>
				</tr>
				<tr>
					<th>数量</th>
					<td><input name="kucun_number" id="kucun_number_edit" width="173px" class="easyui-numberspinner" value="1" data-options="min:0" /></td>
				</tr>
				<tr>
					<th>详情</th>
					<td>
						<textarea rows="" cols="" id="remark_edit">
						</textarea>
					</td>
				</tr>
				
			</table>
			<div style="padding:5px 0;text-align: center;color: red;"
						id="showMsg"></div>
			<div style="text-align: center; padding: 8px;">
				<a href="javascript:void(0)" id="add-btn" class="easyui-linkbutton" onclick="saveEdit()"
					iconCls="icon-ok">保存</a>
			</div>
	</div>
	
	
	
	
	
</body>
</html>
