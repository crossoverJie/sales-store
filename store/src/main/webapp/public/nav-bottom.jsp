<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
  <div class="container">
  	<footer>
		<p class="text-center">© 2016 crossoverJie</p>
		<p class="text-center">
			<a href="<%=path%>/login.jsp">后台登录</a>
		</p>
		<p class="text-center">

		<script language="javascript" type="text/javascript" src="http://js.users.51.la/18800358.js"></script>
		<noscript><a href="http://www.51.la/?18800358" target="_blank"><img alt="&#x6211;&#x8981;&#x5566;&#x514D;&#x8D39;&#x7EDF;&#x8BA1;" src="http://img.users.51.la/18800358.asp" style="border:none" /></a></noscript>
			
		</p>
	</footer>
  </div>
