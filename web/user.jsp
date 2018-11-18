<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

  <title>Add New Contact</title>
  <link rel="stylesheet" href="resources/css/index.css" type="text/css"/>
  <link rel="stylesheet" href="resources/css/user.css" type="text/css"/>

  <script type="text/javascript"
          src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script type="text/javascript" src="resources/js/script.js"></script>
  <script>
      // Get the modal
      //let modal = document.getElementById('myModal');

      // Get the button that opens the modal
      let btn = document.getElementById("myBtn");

      // Get the <span> element that closes the modal
      let span = document.getElementsByClassName("close")[0];

      // When the user clicks the button, open the modal


      // When the user clicks on <span> (x), close the modal

      // When the user clicks anywhere outside of the modal, close it
      window.onclick = function(event) {

          if (event.target == modal) {
              modal.style.display = "none";
          }
      }

  </script>
  <script>
      function myFunc(usering) {
          //do whatever you want to do with pid
          <c:forEach items="${users}" var="user">
          let checkId = ${user.id};
          if(checkId==usering){
              let userInfoId=${user.id};
             // alert("found "+editFirstName);
              $('#eid').val("${user.id}");
              $('#efname').val("${user.firstName}");
              $('#elname').val("${user.lastName}");
              $('#euname').val("${user.username}");
              $('e#pw').val("${user.password}");
              $('#erole').val("${user.role}");
              $('#myModal').css({"display":"block"});
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
<table id="tbl_users" style="border: 2px solid #0d47a1; border-collapse: collapse">
  <thead>
  <tr>
    <th style="border: 2px solid #0d47a1; border-collapse: collapse">Select</th>
    <th style="border: 2px solid #0d47a1; border-collapse: collapse">Id</th>
    <th style="border: 2px solid #0d47a1; border-collapse: collapse">First Name</th>
    <th style="border: 2px solid #0d47a1; border-collapse: collapse">Last Name</th>
    <th style="border: 2px solid #0d47a1; border-collapse: collapse">User Name</th>
  </tr>
  </thead>
  <tbody>
  <c:forEach items="${users}" var="user">
    <tr>
      <td style="border: 2px solid #0d47a1; border-collapse: collapse"><img  onClick="myFunc(${user.id})"src="http://icons.iconarchive.com/icons/oxygen-icons.org/oxygen/256/Actions-document-edit-icon.png" width="22px" height="26px" name=<c:out value="${user.id}" /> />
        <img class="btn_del" src="http://icons.iconarchive.com/icons/oxygen-icons.org/oxygen/256/Actions-edit-delete-icon.png" width="22px" height="26px" id=<c:out value="${user.id}" /> /></td>
      <td style="border: 2px solid #0d47a1; border-collapse: collapse"><c:out value="${user.id}" /></td>
      <td style="border: 2px solid #0d47a1; border-collapse: collapse"><c:out value="${user.firstName}" /></td>
      <td style="border: 2px solid #0d47a1; border-collapse: collapse"><c:out value="${user.lastName}" /></td>
      <td style="border: 2px solid #0d47a1; border-collapse: collapse"><c:out value="${user.username}" /></td>
    </tr>
  </c:forEach>
  </tbody>
</table>
  </div>
  <div id="myModal" class="modal">

    <!-- Modal content -->
    <div class="modal-content">
      <span class="close">&times;</span>
      <div class="fieldAtributes"><label>Id: </label> </h4><input type="text" id="eid" value="" disabled><br></div>
      <div class="fieldAtributes"><label>First Name: </label> </h4><input type="text" id="efname" value=""><br></div>
      <div class="fieldAtributes"><label>Last Name: </label><input type="text" id="elname" value=""><br></div>
      <div class="fieldAtributes"><label>User Name:</label><input type="text" id="euname" value=""><br></div>
      <div class="fieldAtributes"><label>Password: </label><input type="password" id="epw" value=""><br></div>
      <div class="fieldAtributes"><label>Role:</label>
        <select id="erole" value="">
          <option selected>Contributor</option>
          <option>Administrator</option>
        </select><br></div>
      <input id="btn_upt" type="submit" value="Update" />
    </div>

  </div>


  <div class="separatedivs"></div>

  <div class="accordion">
    <div class="accordion-section">
      <a class="accordion-section-title" href="#accordion-uploaddoc">Add New User</a>

      <div id="accordion-uploaddoc" class="accordion-section-content"><br>
<%--</div>
<div class="modalContent">--%>
<div class="fieldAtributes"><label>First Name: </label> </h4><input type="text" id="fname" value=""><br></div>
<div class="fieldAtributes"><label>Last Name: </label><input type="text" id="lname" value=""><br></div>
<div class="fieldAtributes"><label>User Name:</label><input type="text" id="uname" value=""><br></div>
<div class="fieldAtributes"><label>Password: </label><input type="password" id="pw" value=""><br></div>
 <div class="fieldAtributes"><label>Role:</label>
  <select id="role" value="">
      <option selected>Contributor</option>
      <option>Administrator</option>
</select><br></div>

<input id="btn_add" type="submit" value="Submit" />

      </div><!--end .accordion-section-->
    </div><!--end .accordion--><br>

  </div>
</div>
</body>
</html>
