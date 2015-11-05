<%@page contentType="text/html; charset=GBK"%>
<!DOCTYPE html>
<html>
<head>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<base href="<%=basePath%>" />
<title>Spring MVC demo.</title>
<script type="text/javascript" src="js/jquery-1.11.3.js"></script>
<script type="text/javascript">

	function postJson() {
		$.ajax({
			cache : true,
			type : "POST",
			dataType : "json",
			contentType : "application/json",
			url : "api/user/add",
			data : JSON.stringify({
				"code" : "code0001",
				"name" : "name0001"
			}),
			async : false,
			error: function(request) {
                alert(request.status);
            },
			success : function(data) {
				alert(data);
			}
		});
	}
</script>
</head>
<body>
	<img src="resources/image/cross.png">
	<a href="hello">/hello</a>
	<a href="today">/today</a>
	<a href="restful">/restful</a>
	<a href="api/users">/api/users</a>
	<a href="javascript:postJson()">/api/user/add</a>
	<p>
	<fieldset>
		<legend>UserController</legend>
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
		<p>
	</fieldset>
	<fieldset>
		<legend>ParamController</legend>
		<p>
			<a href="params/request">/params/request</a>
		<p>
			<a href="params/response">/params/response</a>
		<p>
			<a href="params/session">/params/session</a>
		<p>
			<a href="params/commond?code=Bill&name=Gates">/params/commond</a>
		<p>
			<a href="params/model">/params/model</a>
		<p>
			<a href="params/single?uname=BillGates">/params/single</a>
		<p>
			<a href="params/single2?name=BillGates">/params/single2</a>
		<p>
			<a href="params/multi?role=admin&role=manager">/params/multi</a>
		<p>
			<a href="params/multi2?role=admin&role=manager">/params/multi2</a>
		<p>
			<a href="params/load/a12345">/params/load/userid</a>
		<p>
			<a href="params/cookie">/params/cookie</a>
		<p>
			<a href="params/cookie2">/params/cookie2</a>
		<p>
			<a href="params/header">/params/header</a>
		<p>
	</fieldset>
</body>
</html>