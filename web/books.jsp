<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="S" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: 54333
  Date: 2016/10/9
  Time: 19:15
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
        <br/><br/>
        <a href="index.jsp"><button>返回主页</button></a>
        <br/><br/><br/><br/>
        <table border="1" align="center">
            <tr>
                <td align="center">ISBN</td>
                <td align="center">书名</td>
                <td align="center">作者</td>
                <td align="center">出版社</td>
                <td align="center">出版日期</td>
                <td align="center">价格</td>
                <td align="center"></td>
            </tr>
            <s:iterator value="allBooks" status="st" >
                <tr>
                    <td align="center"><s:property value="isbn"/></td>
                    <td align="center"><s:a href="showDetail.action?isbn=%{isbn}">
                        <s:property value="title"/></s:a></td>
                    <td align="center"><s:property value="author"/></td>
                    <td align="center"><s:property value="publisher"/></td>
                    <td align="center"><s:property value="publishDate"/></td>
                    <td align="center"><s:property value="price"/></td>
                    <td align="center">
                        <s:a href="edit.action?isbn=%{isbn}&flag=1">修改</s:a>
                        <s:a href="delete.action?isbn=%{isbn}" onclick="return confirm('您确定要删除这本书吗？');">删除</s:a>
                    </td>
                </tr>
            </s:iterator>
        </table>
        <br/><br/><br/><br/>
        <s:property value="pageNumber"/>/<s:property value="totalPage"/>
        <s:if test="pageNumber>1">
            <s:a href="search.action?pageNumber=1&author=%{author}">首页</s:a>
            <s:a href="search.action?pageNumber=%{pageNumber-1}&author=%{author}">上一页</s:a>
        </s:if>
        <s:if test="pageNumber<totalPage">
            <s:a href="search.action?pageNumber=%{pageNumber+1}&author=%{author}">下一页</s:a>
            <s:a href="search.action?pageNumber=%{totalPage}&author=%{author}">末页</s:a>
        </s:if>
    </div>
</body>
</html>
