<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>add User</title>
</head>
<body>

<!--
当提交带有文件的表单时，要选择的内容类型是 multipart/form-data。
通过将 enctype 设置为 multipart/form-data，每个输入域都将作为 POST 请求的不同部分进行提交。
-->
<form action="${baseurl}/user/savePic" method="POST" enctype="multipart/form-data">
    User Code:<input type="text" name="code"/>
    <p>
       User Name:<input type="text" name="name"/>
    <p>
       User Picture:<input type="file" name="pic"/>
    <p>
        <input type="submit" value="Save"/>
</form>

<a href="${baseurl}">Home Page</a>
</body>
</html>