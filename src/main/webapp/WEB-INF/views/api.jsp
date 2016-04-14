<%@page contentType="text/html; charset=GBK" %>
<!DOCTYPE html>
<html>
<head>
<base href="${baseurl}" />
<title>API for JSON&XML Test</title>
<script type="text/javascript" src="js/jquery-1.11.3.js"></script>
<script type="text/javascript">

	function postJSON() {
		$.ajax({
			cache: true,
			type: "POST",
			dataType: "xml",
			contentType: "application/json",
			url: "api/book",
			data: JSON.stringify({
				"name": "Spring in Action"
			}),
			async: false,
			error: function (request) {
				alert(request.status);
			},
			success: function (data) {
				alert(data);
			}
		});
	}
</script>
</head>
<body>
<p>
	<a href="api/book/Book001">/api/book/Book001 XML</a>
<p>
	<a href="api/user/User001">/api/user/User001 XML</a>
<p>
	<a href="api/books">/api/books</a>
<p>
	<a href="api/users">/api/users</a>
<p>
	<a href="javascript:postJSON()">/api/book POST</a>
</body>
</html>