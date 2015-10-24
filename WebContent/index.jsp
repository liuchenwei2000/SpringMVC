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
</body>
</html>