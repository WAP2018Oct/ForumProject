<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">

    <title>Add New Contact</title>

    <link rel="stylesheet" href="resources/css/material.css" type="text/css"/>
    <link rel="stylesheet" href="resources/css/index.css" type="text/css"/>
    <link rel="stylesheet" href="resources/css/user.css" type="text/css"/>

    <%--<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.98.0/css/materialize.min.css">--%>
    <script type="text/javascript"
            src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script type="text/javascript" src="resources/js/script.js"></script>
    <script>
        function myFunc(usering) {
            //do whatever you want to do with pid
            <c:forEach items="${users}" var="user">

            if (${user.id}==usering
        )
            {
                let userInfoId =${user.id};
                // alert("found "+editFirstName);
                $('#eid').val("${user.id}");
                $('#efname').val("${user.firstName}");
                $('#elname').val("${user.lastName}");
                $('#euname').val("${user.username}");
                $('e#pw').val("${user.password}");
                $('#erole').val("${user.role}");
                $('#myModal').css({"display": "block"});
            }

            </c:forEach>
        }
    </script>
<body>
<div class="temp-space">
    <h1>User Management</h1>
</div>
<br>
<div class="post-list mdl-list">
    <h3>Current List of Users.</h3>

    <div>
        <table id="tbl_users" class="bordered striped centered">
            <thead>
            <tr>
                <td class="tdusers">Select</td>
                <td class="tdusers">Id</td>
                <td class="tdusers">First Name</td>
                <td class="tdusers">Last Name</td>
                <td class="tdusers">User Name</td>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${users}" var="user">
                <tr name=<c:out value="${user.id}"/>>
                    <td class="tdusers"><img onClick="myFunc(${user.id})"
                                             src="http://icons.iconarchive.com/icons/oxygen-icons.org/oxygen/256/Actions-document-edit-icon.png"
                                             width="22px" height="26px" name=
                            <c:out value="${user.id}"/>/>
                        <img class="btn_del"
                             src="http://icons.iconarchive.com/icons/oxygen-icons.org/oxygen/256/Actions-edit-delete-icon.png"
                             width="22px" height="26px" id=
                                <c:out value="${user.id}"/>/></td>
                    <td class="tdusers"><c:out value="${user.id}"/></td>
                    <td class="tdusers"><c:out value="${user.firstName}"/></td>
                    <td class="tdusers"><c:out value="${user.lastName}"/></td>
                    <td class="tdusers"><c:out value="${user.username}"/></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div id="myModal" class="modal">

        <!-- Modal content -->
        <div class="modal-content">
            <span class="close">&times;</span>
            <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label is-upgraded fieldAtributes"><label>Id: </label> </h4>
                <input type="text" id="eid" value="" disabled><br></div>
            <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label is-upgraded fieldAtributes"><label>First
                Name: </label> </h4><input type="text" id="efname" value=""><br></div>
            <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label is-upgraded fieldAtributes"><label>Last
                Name: </label><input type="text" id="elname" value=""><br></div>
            <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label is-upgraded fieldAtributes"><label>User
                Name:</label><input type="text" id="euname" value=""><br></div>
            <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label is-upgraded fieldAtributes"><label>Password: </label><input
                    type="password" id="epw" value=""><br></div>
            <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label is-upgraded fieldAtributes"><label>Role:</label>
                <select id="erole" value="">
                    <option selected>Contributor</option>
                    <option>Admin</option>
                </select><br></div>
            <div><input id="btn_upt" type="submit" value="Update"
                        class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--colored"/>
            </div>
        </div>

    </div>


    <div class="separatedivs"></div>
    <div style="position: relative;">
        <div class="accordion mdl-card mdl-shadow--2dp">
            <div class="accordion-section">
                <a class="accordion-section-title" href="#accordion-uploaddoc">Add New User</a>

                <div id="accordion-uploaddoc" class="accordion-section-content"><br>
                    <%--</div>
                    <div class="modalContent">--%>
                    <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label is-upgraded fieldAtributes">
                        <label>First Name: </label> </h4><input class="mdl-textfield__input" type="text" id="fname"
                                                                value=""><br></div>
                    <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label is-upgraded fieldAtributes">
                        <label>Last Name: </label><input class="mdl-textfield__input" type="text" id="lname"
                                                         value=""><br></div>
                    <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label is-upgraded fieldAtributes">
                        <label>User Name:</label><input class="mdl-textfield__input" type="text" id="uname"
                                                        value=""><br></div>
                    <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label is-upgraded fieldAtributes">
                        <label>Password: </label><input class="mdl-textfield__input" type="password" id="pw"
                                                        value=""><br></div>
                    <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label is-upgraded fieldAtributes">
                        <label>Role:</label>
                        <select id="role" value="" class="mdl-textfield__input">
                            <option selected>Contributor</option>
                            <option>Admin</option>
                        </select><br></div>

                    <input id="btn_add" type="submit" value="Submit"
                           class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--colored"/>

                </div><!--end .accordion-section-->
            </div><!--end .accordion--><br>

        </div>
    </div>
</div>
</body>
</html>
