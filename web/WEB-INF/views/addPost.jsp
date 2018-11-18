<%--
  Created by IntelliJ IDEA.
  User: purve
  Date: 11/16/2018
  Time: 15:17
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
    <script src="<c:url value='/resources/js/indexScript.js'/>"></script>
    <title>Forum</title>
</head>
<body>

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
        <h2>Create New Post</h2>
        <form action="<c:url value='/API/post/'/>" method="post">
            <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                <input class="mdl-textfield__input" type="text" id="title">
                <label class="mdl-textfield__label" for="title">Post Title</label>
            </div>
            <br/>

            <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                <textarea class="mdl-textfield__input" type="text" rows="10" id="description"
                          name="description "></textarea>
                <label class="mdl-textfield__label" for="description">Post Description</label>
            </div>
            <br/>

            <input type="hidden" name="fromForm" value="true"/>

            <button type="submit"
                    class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--colored"
                    id="addPost">
                Add New Post
            </button>
        </form>
    </main>
</div>

</body>
</html>
