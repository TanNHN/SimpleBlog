<%-- 
    Document   : index
    Created on : Jan 6, 2020, 8:04:04 PM
    Author     : Tan
--%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
    <head>
        <title>Simple Blog</title>
        <meta http-equiv="content-type" content="text/html; charset=iso-8859-1" />
        <link rel="stylesheet" href="css/1.css" type="text/css" media="screen,projection" />
    </head>
    <body style="background-color: gray">
        <script> function signOut() {
                document.location.replace("https://www.google.com/accounts/Logout?continue=https://appengine.google.com/_ah/logout?continue=http://localhost:8080/SimpleBlog/LogoutController");
            }</script>
        <div id="container">
            <div id="header">
                <h1><a href="MainController?action=ShowAllPost&txtSkip=0">Simple<strong>BLOG</strong></a></h1>
                <h2>For all your blogging needs.</h2>
                <ul id="nav">
                    <li><a href="main_page.jsp" accesskey="h"><em>H</em>ome</a></li>
                        <c:if test="${sessionScope.USER != null}" var="authenticate">
                            <c:if test="${sessionScope.USER.role eq 1}">
                            <p> ${sessionScope.USER.name}</p>

                            <li><a href="admin.jsp">Admin page</a></li>
                            <li><a href="ShowNewUserController">Active new User</a></li>
                            <li><a href="LogoutController">Logout</a></li>
                            </c:if>

                        <c:if test="${sessionScope.USER.role eq 2}">
                            <li><a href="write_post.jsp">Post</a></li> 
                            <p> ${sessionScope.USER.name}</p>
                            <li><a href="#" onclick="signOut()">Logout</a></li>


                        </c:if>
                    </c:if>
                    <c:if test="${!authenticate}">
                        <li><a href="login.jsp">Login</a></li> 
                        <li><a href="register.jsp">Register</a></li> 
                        </c:if>
                </ul>
            </div>



