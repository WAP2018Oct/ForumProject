<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">

    <title>Login</title>

    <link rel="stylesheet"
          href="<c:url value="https://fonts.googleapis.com/css?family=Roboto:regular,bold,italic,thin,light,bolditalic,black,medium&amp;lang=en"/>">
    <link rel="stylesheet" href="<c:url value="https://fonts.googleapis.com/icon?family=Material+Icons" />">
    <link rel="stylesheet" href="<c:url value='/resources/css/material.css'/>">
    <script defer src="<c:url value='https://code.getmdl.io/1.3.0/material.min.js'/>"></script>

    <link rel="stylesheet" href="<c:url value='/resources/css/main.css'/>">
    <link rel="stylesheet" href="<c:url value='/resources/css/login.css'/>">
</head>
<body>
<div class="mdl-card mdl-shadow--2dp">
    <form action="login" method="post">
        <fieldset>
            <legend class="typo-styles__demo mdl-typography--display-2">Login</legend>

            <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                <input class="mdl-textfield__input" name="username" type="text" id="username"
                       value="${cookie.username.value}">
                <label class="mdl-textfield__label" for="username">Name</label>
            </div>

            <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                <input class="mdl-textfield__input" name="password" type="password" id="password">
                <label class="mdl-textfield__label" for="password">Password</label>
            </div>

            <div class="rememberCheck">
                <label class="mdl-checkbox mdl-js-checkbox mdl-js-ripple-effect" for="rememberme">
                    <input type="checkbox" id="rememberme" class="mdl-checkbox__input"
                           <c:if test="${cookie.containsKey('username')}">checked</c:if>>
                    <span class="mdl-checkbox__label">Remember Me</span>
                </label>
            </div>

            <button type="submit" id="sub"
                    class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--colored">
                Login
            </button>
        </fieldset>
        <div class="error-msg"> ${err_msg}</div>
    </form>
</div>


</body>
</html>