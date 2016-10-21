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
    <h2>${book.title}</h2>
    <div style="text-align: center;">
        <br/><br/><br/><br/><br/><br/><br/><br/>
        <h1>图书管理系统</h1>
        <br/><br/><br/><br/>
        <form action = "submitNewBook" method="post">
            <p>ISBN:<input type="text" name="isbn" placeholder="必填，不得超过13位"/></p>
            <p>书名:<input type="text" name="title" placeholder="必填"/></p>
            <p>作者:<select name="authorID" id="submitNewBook_authorID">
                <s:iterator value="allAuthors" status="st">
                    <option value="${authorID}">${name}</option>
                </s:iterator>
            </select><button onclick="reg()" >新增作者</button></p>
            <p>出版社:<input type="text" name="publisher"/></p>
            <p>出版时间:<input type="text" name="publishDate" placeholder="必填，按2000-1-1格式填写"></p>
            <p>价格:<input type="text" name="price"/></p>
            <input type="hidden" name="url" value="${url}"/>
            <button type="submit">新建</button>
        </form>
    </div>
</body>
</html>
