<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: 54333
  Date: 2016/10/14
  Time: 10:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<SCRIPT type="text/javascript">
    function reg() {
        targetForm = document.forms[0];
        targetForm.action = "addAuthor.action";
        targetForm.submit();
    }
</SCRIPT>
<html>
<head>
    <title>图书管理系统</title>
</head>
<body>
    <div style="text-align: center;">
        <br/><br/><br/><br/><br/><br/><br/><br/>
        <h1>图书管理系统</h1>
        <br/><br/><br/><br/>
        <h2>${book.title}</h2>
        <form action = "submitOldBook" method="GET">
            <p>作者:<select name="newAuthorID" id="submitOldBook_newAuthorID">
                <s:iterator value="allAuthors" status="st">
                    <s:if test="oldAuthorID == authorID">
                        <option value="${authorID}" selected="selected">${name}</option>
                    </s:if>
                    <s:else>
                        <option value="${authorID}">${name}</option>
                    </s:else>
                </s:iterator>
            </select>
            <button onclick="reg()" >新增作者</button></p>
            <p>出版社:<input type="text" name="publisher" value="${book.publisher}"/></p>
            <p>出版日期:<input type="text" name="publishDate" value="${book.publishDate}"/></p>
            <p>价格:<input type="text" name="price" value="${book.price}"></p>
            <input type="hidden" name="url" value="${url}"/>
            <input type="hidden" name="flag" value="${flag}"/>
            <p><button type="submit">修改</button></p>
        </form>
    </div>
</body>
</html>
