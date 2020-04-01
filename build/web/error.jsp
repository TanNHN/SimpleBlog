<%-- 
    Document   : error
    Created on : Jan 10, 2020, 5:41:36 PM
    Author     : Tan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error Page</title>
    </head>
    <body>
        <h1><font color="red">${requestScope.ERROR}</font></h1>
        <a href="main_page.jsp">Back to mainpage</a>
    </body>
</html>
