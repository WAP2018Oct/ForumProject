<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" href="resources/css/material.css" type="text/css"/>
	<link rel="stylesheet" href="resources/css/index.css" type="text/css"/>

	<title>Insert title here</title>
	<style type=text/css>
		html,body{height: 100%; padding:0; margin:0;}
		form{ width:30em;height:9em; margin:-5em auto 0 auto; position: relative; top:50%; border:1px dotted #ccc; padding:.25em; }
		fieldset{ margin:0;   border:0;padding:0;}
		legend{float:left; font-size: 200%; text-align: center; color:blue; font-weight: bold; border-bottom: 1px solid blue; width:15em;  padding:0; }
		label, label+ input {display:inline; float:left;margin-top:1em;}
		label{text-align: right; width:28%; clear: left; margin-top:.8em; }
		label+ input{ width:60%; padding:.25em; ; margin-left:.5em; border: 1px inset;  }
		#sub{  margin-top:1em; position: relative; float:left;clear: left; margin-left: 29%}
	</style>
</head>
<body>
<form action="login" method="post">
	<fieldset><legend>WAP Forum Login</legend>
		<label for="username">Name: </label><input  type="text" value="${cookie.username.value}" name="username" id="username" >
		<label for="password">Password: </label><input  type="password" name="password" id="password" >
		<label for="rememberme">Remember Me </label><input type="checkbox" name="rememberme"
														   <c:if test="${cookie.containsKey('username')}">checked</c:if>
														   id="rememberme">
		<input type="submit" value="Login" id="sub" class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--colored">
	</fieldset>
</form>

	<span style="color: red">${err_msg}</span>
</body>
</html>