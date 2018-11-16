<%--
  Created by IntelliJ IDEA.
  User: bruck
  Date: 11/13/2018
  Time: 6:01 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Servlet State</title>
  <script type="text/javascript" src="resources/js/jquery-2.1.1.min.js"></script>
  <script type="text/javascript" src="resources/js/buttonEventsInit.js"></script>
  <script type="text/javascript" src="resources/js/resultsPrinter.js"></script>
  <script type="text/javascript" src="resources/js/insertBandInfo.js"></script>
</head>
<body>
<h1>User Management</h1>
<p>This is an example of how to use Ajax with a servlet backend.</p></br>

<h3>Select a button to get the relevant information.</h3>

<!-- Buttons that will call the servlet to retrieve the information. -->
<button id="show-users" type="button">Show Users</button>
<button id="bands-albums" type="button">Show bands and albums!</button>

<!-- We need to have some empty divs in order to add the retrieved information to them. -->
<div id="band-results"></div></br></br>
<div id="bands-albums-results"></div></br></br>


<h3>Add the band information and press submit!</h3>
<h4>Band name: </h4><input type="text" id="band-name-input" value=""><br>
<h4>Albums: </h4><input type="text" id="album-input" value="">(Separated by commas)<br>
<input type="submit" id="submit-band-info" value="Submit">
</body>
</html>
