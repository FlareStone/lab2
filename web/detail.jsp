<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: 54333
  Date: 2016/10/13
  Time: 13:27
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
        <h4>${book.title}</h4>
        <p>ISBN：${book.isbn}</p>
        <p>作者：${author.name}</p>
        <p>出版社：${book.publisher}</p>
        <p>出版日期：${book.publishDate}</p>
        <p>价格：${book.price}</p>
        <br/><br/><br/>
        <h4>作者信息</h4>
        <p>姓名：${author.name}</p>
        <p><s:if test="author.age <1">
            年龄：未知
            </s:if>
            <s:else>
                年龄：${author.age}
            </s:else></p>
        <p>国家：${author.country}</p>
        <br/>
        <p><a href="${url}"><button>返回</button></a></p>
    </div>
</body>
</html>
