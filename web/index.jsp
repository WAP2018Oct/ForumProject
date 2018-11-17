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

    <link rel="stylesheet"
          href="<c:url value="https://fonts.googleapis.com/css?family=Roboto:regular,bold,italic,thin,light,bolditalic,black,medium&amp;lang=en"/>">
    <link rel="stylesheet" href="<c:url value="https://fonts.googleapis.com/icon?family=Material+Icons" />">
    <link rel="stylesheet" href="<c:url value='/resources/css/material.css'/>">
    <script src="<c:url value='https://code.jquery.com/jquery-3.3.1.js'/>"></script>
    <script defer src="<c:url value='https://code.getmdl.io/1.3.0/material.min.js'/>"></script>

    <link rel="stylesheet" href="<c:url value='/resources/css/main.css'/>">
    <link rel="stylesheet" href="<c:url value='/resources/css/index.css'/>">
    <script src="<c:url value='/resources/js/indexScript.js'/>"></script>
    <title>Forum</title>
</head>
<body>
<%--<%--%>
<%--String loginurl = "http://localhost:8080/login";--%>
<%--response.sendRedirect(loginurl);--%>
<%--%>--%>
<div class="mdl-layout mdl-js-layout mdl-layout--fixed-drawer">

    <div class="left-drawer mdl-layout__drawer mdl-color--blue-grey-900 mdl-color-text--blue-grey-50">
        <header class="drawer-header">
            <img src="<c:url value="/resources/images/defaultAvatar.png"/>" class="user-avatar">

            <div class="demo-avatar-dropdown">
                <span>hello@example.com</span>
                <div class="mdl-layout-spacer"></div>
            </div>
        </header>
        <nav class="left-navigation mdl-navigation mdl-color--blue-grey-800">
            <a class="mdl-navigation__link" href="">
                <i class="mdl-color-text--blue-grey-400 material-icons" role="presentation">help_outline</i>Any
                button ideas?</a>
            <div class="mdl-layout-spacer"></div>
            <a class="mdl-navigation__link login" href="">
                <i class="mdl-color-text--blue-grey-400 material-icons" role="presentation">power_settings_new</i>Login</a>
            <a class="mdl-navigation__link logout" href="">
                <i class="mdl-color-text--blue-grey-400 material-icons" role="presentation">power_settings_new</i>Logout</a>
        </nav>
    </div>
    <main class="mdl-layout__content mdl-color--grey-100">
        <div class="temp-space">some content here</div>

        <button class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--colored"
                id="addPost">
            Add New Post
        </button>

        <ul class="post-list mdl-list">
            <li class="mdl-list__item mdl-list__item--three-line">
                <span class="mdl-list__item-primary-content">
                    <i class="material-icons mdl-list__item-avatar">person</i>
                    <span>Post Title</span>
                    <span class="mdl-list__item-text-body">
                        Some long text. Lorem ipsum dolor sit amet consectetur adipisicing elit. Voluptatum facilis
                        nemo libero! Rem fuga ut recusandae corrupti eius nesciunt odio nostrum velit aliquam
                        aperiam officiis illum, tenetur rerum totam nam!
                    </span>
                </span>
                <span class="mdl-list__item-secondary-content">
                    <div class="material-icons mdl-badge mdl-badge--overlap" data-badge="1">comment</div>
                </span>
            </li>
            <li class="mdl-list__item mdl-list__item--three-line">
                <span class="mdl-list__item-primary-content">
                    <i class="material-icons mdl-list__item-avatar">person</i>
                    <span>Bryan Cranston</span>
                    <span class="mdl-list__item-text-body">
                        Bryan Cranston played the role of Walter in Breaking Bad. He is also known
                        for playing Hal in Malcom in the Middle.
                    </span>
                </span>
                <span class="mdl-list__item-secondary-content">
                    <div class="material-icons mdl-badge mdl-badge--overlap" data-badge="5">comment</div>
                </span>
            </li>
            <li class="mdl-list__item mdl-list__item--three-line">
                <span class="mdl-list__item-primary-content">
                    <i class="material-icons mdl-list__item-avatar">person</i>
                    <span>Bryan Cranston</span>
                    <span class="mdl-list__item-text-body">
                        Bryan Cranston played the role of Walter in Breaking Bad. He is also known
                        for playing Hal in Malcom in the Middle.
                    </span>
                </span>
                <span class="mdl-list__item-secondary-content">
                    <div class="material-icons mdl-badge mdl-badge--overlap" data-badge="10">comment</div>
                </span>
            </li>
        </ul>
    </main>
</div>

</body>
</html>
