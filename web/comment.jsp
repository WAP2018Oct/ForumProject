<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 11/16/2018
  Time: 6:01 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Comment Page</title>

</head>
<body>
<table id="tbl_comments">
    <thead>
    <tr>
        <th>Id</th>
        <th>Author</th>
        <th>Comment</th>
        <th>Created Date</th>
        <th>Post Id</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${comments}" var="comment">
        <tr>
            <td><c:out value="${comment.id}" /></td>
            <td><c:out value="${comment.author.username}" /></td>
            <td><c:out value="${comment.comment}" /></td>
            <td><c:out value="${comment.createdDate}" /></td>
            <td><c:out value="${comment.postId}" /></td>
        </tr>
    </c:forEach>
    </tbody>
</table>


</body>
</html>
