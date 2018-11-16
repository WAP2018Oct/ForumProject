package controller;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class login extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            response.sendRedirect("View/Welcome");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            String userName="";
            for(Cookie cookie : request.getCookies()){
                System.out.println(cookie.getName());
            if(cookie.getName().equals("Name")){

                userName = cookie.getValue();

                System.out.println(userName+" WOW");
            }
            else{
                System.out.println("none");
            }
        }
            out.print("<head>" +
                    "<title>Servlet State</title>" +
                    "<style type=text/css>" +
                    "\t\thtml,body{height: 100%; padding:0; margin:0;}\n" +
                    "\t\tform{ width:30em;height:9em; margin:-5em auto 0 auto; position: relative; top:50%; border:1px dotted #ccc; padding:.25em; }\n" +
                    "\t\tfieldset{ margin:0;   border:0;padding:0;}\n" +
                    "\t\tlegend{float:left; font-size: 200%; text-align: center; color:blue; font-weight: bold; border-bottom: 1px solid blue; width:15em;  padding:0; }\n" +
                    "\t\tlabel, label+ input {display:inline; float:left;margin-top:1em;}\n" +
                    "\t\tlabel{text-align: right; width:28%; clear: left; margin-top:.8em; }\n" +
                    "\t\tlabel+ input{ width:60%; padding:.25em; ; margin-left:.5em; border: 1px inset;  margin-left: }\n" +
                    "\t\t#sub{  margin-top:1em; position: relative; float:left;clear: left; margin-left: 29%}\n" +
                    "</style>\n" +
                    "</head>\n" +
                    "<body>\n" +


                    "\t<form action=\"Welcome\" method=\"post\">\n" +
                    "\t\t<fieldset><legend>Login</legend>\n" +
                    "\t\t\t<label for=\"name\">Name: </label><input  type=\"text\" name=\"username\" id=\"username\" value="+userName+">\n" +
                    "\t\t\t<label for=\"password\">Password: </label><input  type=\"password\" name=\"password\" id=\"password\" >\n" +
                    "\t\t\t<label for=\"rememberme\">Remember Me </label><input type=\"checkbox\" name=\"rememberme\" id=\"rememberme\">\n"+
                    "\t\t\t<input type=\"submit\" value=\"Login\" id=\"sub\">\n" +
                    "\t\t</fieldset>\t\n" +
                    "\t</form>\n" +
                    "</body>" +
                    "" +
                    "");
    }
}
