<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: 54333
  Date: 2016/10/14
  Time: 12:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>图书管理系统</title>
</head>
<body>
    <div style="text-align: center;">
        <br/><br/><br/><br/><br/><br/><br/><br/>
        <h1>图书管理系统</h1>
        <br/><br/><br/><br/>
        <form action="submitAuthor" method="GET">
            <p>姓名:<input type="text" name="name"/></p>
            <p>年龄:<input type="text" name="age"/></p>
            <p>国家:<input type="text" name="国家"/></p>
            <input type="hidden" name="url" value="${url}"/>
            <input type="hidden" name="url2" value="${url2}"/>
            <p><button type="submit">新建</button></p>
        </form>
    </div>
</body>
</html>
