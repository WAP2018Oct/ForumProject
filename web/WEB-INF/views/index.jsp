<%--
  Created by IntelliJ IDEA.
  User: bruck
  Date: 11/13/2018
  Time: 6:01 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">

    <title>Forum</title>

    <link rel="stylesheet"
          href="<c:url value="https://fonts.googleapis.com/css?family=Roboto:regular,bold,italic,thin,light,bolditalic,black,medium&amp;lang=en"/>">
    <link rel="stylesheet" href="<c:url value="https://fonts.googleapis.com/icon?family=Material+Icons" />">
    <link rel="stylesheet" href="<c:url value='/resources/css/material.css'/>">
    <script defer src="<c:url value='https://code.getmdl.io/1.3.0/material.min.js'/>"></script>
    <script src="<c:url value='https://code.jquery.com/jquery-3.3.1.js'/>"></script>

    <link rel="stylesheet" href="<c:url value='/resources/css/main.css'/>">
    <link rel="stylesheet" href="<c:url value='/resources/css/index.css'/>">
    <script src="<c:url value='/resources/js/indexScript.js'/>"></script>
</head>
<body>
<%--<%--%>
<%--String loginurl = "http://localhost:8080/login";--%>
<%--response.sendRedirect(loginurl);--%>
<%--%>--%>
<div class="mdl-layout mdl-js-layout mdl-layout--fixed-drawer">

    <div class="left-drawer mdl-layout__drawer mdl-color--blue-grey-900 mdl-color-text--blue-grey-50">
        <c:if test="${requestScope.isLoggedIn}">
            <header class="drawer-header">

                <img
                        <c:if test="${not empty sessionScope.user.avatarLink}">
                            src="${sessionScope.user.avatarLink}"
                        </c:if>
                        <c:if test="${empty sessionScope.user.avatarLink}">
                            src="<c:url value="/resources/images/defaultAvatar.png"/>"
                        </c:if>
                        class="user-avatar"/>

                <div class="demo-avatar-dropdown">
                    <span>${sessionScope.user.firstName} ${sessionScope.user.lastName}</span>
                    <div class="mdl-layout-spacer"></div>
                </div>
            </header>
        </c:if>

        <nav class="left-navigation mdl-navigation mdl-color--blue-grey-800">
            <a class="mdl-navigation__link" href="">
                <i class="mdl-color-text--blue-grey-400 material-icons" role="presentation">help_outline</i>Any
                button ideas?</a>
            <div class="mdl-layout-spacer"></div>
            <c:if test="${!requestScope.isLoggedIn}">
                <a class="mdl-navigation__link login" href="/login">
                    <i class="mdl-color-text--blue-grey-400 material-icons" role="presentation">power_settings_new</i>Login</a>
            </c:if>
            <c:if test="${requestScope.isLoggedIn}">
                <a class="mdl-navigation__link logout" href="/logout">
                    <i class="mdl-color-text--blue-grey-400 material-icons" role="presentation">power_settings_new</i>Logout</a>
            </c:if>
        </nav>
    </div>
    <main class="mdl-layout__content mdl-color--grey-100">
        <div class="temp-space">some content here</div>

        <c:if test="${requestScope.isLoggedIn}">
            <button class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--colored"
                    id="addPost">
                Add New Post
            </button>
        </c:if>

        <ul class="post-list mdl-list">
            <c:forEach items="${requestScope.posts}" var="post">
                <li class="mdl-list__item mdl-list__item--three-line" postId="${post.id}">
                    <span class="mdl-list__item-primary-content">
                        <i class="material-icons mdl-list__item-avatar">person</i>
                        <c:if test="${not empty post.user.avatarLink}">
                            <img class="user-avatar" src="${post.user.avatarLink}" alt="user avatar"/>
                        </c:if>
                        <span>${post.postTitle}</span>
                        <span class="mdl-list__item-text-body">
                                ${post.postContent}
                        </span>
                    </span>
                    <span class="mdl-list__item-secondary-content">
                        <div class="material-icons mdl-badge mdl-badge--overlap"
                             data-badge="${requestScope.commentMap[post.id]}">comment</div>
                    </span>
                    <c:if test="${requestScope.isLoggedIn && sessionScope.user.id == post.user.id}">
                        <button id="post-menu-lower-right${post.id}"
                                class="mdl-button mdl-js-button mdl-button--icon dotMenu">
                            <i class="material-icons">more_vert</i>
                        </button>
                        <ul class="mdl-menu mdl-menu--bottom-right mdl-js-menu mdl-js-ripple-effect"
                            for="post-menu-lower-right${post.id}">
                            <li class="mdl-menu__item editPost editPost">Edit Post</li>
                            <li class="mdl-menu__item deletePost deletePost">Delete Post</li>
                        </ul>
                    </c:if>
                </li>
            </c:forEach>
        </ul>
    </main>

    <div id="bottom-snackbar" class="mdl-js-snackbar mdl-snackbar">
        <div class="mdl-snackbar__text"></div>
        <button class="mdl-snackbar__action" type="button"></button>
    </div>
</div>

</body>
</html>
