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
    <title>Admin Comment Page</title>
    <link rel="stylesheet" href="<c:url value='/resources/css/material.css'/>">
    <script src="<c:url value='https://code.jquery.com/jquery-3.3.1.js'/>"></script>
    <script defer src="<c:url value='https://code.getmdl.io/1.3.0/material.min.js'/>"></script>
</head>
<body>
<table class="mdl-data-table mdl-js-data-table mdl-data-table--selectable mdl-shadow--2dp">
    <thead>
    <tr>
        <th class="mdl-data-table__cell--non-numeric">Id</th>
        <th class="mdl-data-table__cell--non-numeric">Author</th>
        <th class="mdl-data-table__cell--non-numeric">Comment</th>
        <th class="mdl-data-table__cell--non-numeric">Created Date</th>
        <th class="mdl-data-table__cell--non-numeric">Post Id</th>
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
