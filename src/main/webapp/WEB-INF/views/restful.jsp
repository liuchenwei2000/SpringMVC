<%@page contentType="text/html; charset=GBK" %>
<!DOCTYPE html>
<html>
<head>
    <%
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    %>
    <base href="<%=basePath%>"/>
    <title>RESTful Test</title>
    <script type="text/javascript" src="js/jquery-1.11.3.js"></script>
    <script type="text/javascript">

        function testPostOrPut(type) {
            $.ajax({
                cache: true,
                type: type,
                url: "book",
                data: {
                    "name": "Hello World"
                },
                async: false,
                error: function (request) {
                    alert("Connection error");
                },
                success: function (data) {
                    $("#showinfo").html(data);
                }
            });
        }

        function testDelete() {
            $.ajax({
                cache: true,
                type: "DELETE",
                url: "book/A123456789",
                async: false,
                error: function (request) {
                    alert("Connection error");
                },
                success: function (data) {
                    $("#showinfo").html(data);
                }
            });
        }
    </script>
</head>
<body>
<p>
    <a href="book">/book GET</a>
<p>
    <a href="javascript:testPostOrPut('POST')">/book POST</a>
<p>
    <a href="javascript:testPostOrPut('PUT')">/book PUT</a>
<p>
    <a href="javascript:testDelete()">/book DELETE</a>
<p>
<div id="showinfo"></div>
</body>
</html>