<%@page contentType="text/html; charset=GBK" %>
<!DOCTYPE html>
<html>
<head>
<%
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%>
<base href="<%=basePath%>" />
<title>Spring MVC demo.</title>
</head>
<body>
	<a href="hello">/hello</a>
	<p>
	<a href="today">/today</a>
	<p>
	<a href="user/firstname">/user/firstname</a>
	<p>
	<a href="user/lastname">/user/lastname</a>
	<p>
	<a href="user/all">/user/all</a>
	<p>
	<a href="user?query=abcd">/user?query=aaa</a>
	<p>
	<a href="user/delete/pk123456">/user/delete/111</a>
</body>
</html>