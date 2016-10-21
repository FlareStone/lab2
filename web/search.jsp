<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: 54333
  Date: 2016/10/6
  Time: 20:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel='stylesheet' type='text/css' href='/css/search.css' />
    <title>图书管理系统</title>
</head>
<body>
    <div style="text-align: center">
        <br/><br/><br/><br/><br/><br/><br/><br/>
        <h1>图书管理系统</h1>
        <br/><br/>
        <a href="index.jsp"><button>返回主页</button></a>
        <br/><br/><br/><br/>
        <form action="search" method="GET">
            <input type="text" name="author" placeholder="请输入作者名" class="search"/>
            <button type="submit" class="button">搜索</button>
        </form>
    </div>
</body>
</html>