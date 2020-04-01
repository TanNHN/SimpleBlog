<%@include file="header.jsp" %>
<div id="content">
    <form action="MainController" method="post">
        <font color="blue">${requestScope.STATUS}</font><br>
        Email:  &emsp;&emsp;<input type="text" name="txtEmail" value="${param.txtEmail}"><br>
        <font color="red">${requestScope.INVALID.emailError}</font><br>
        Password: <input type="password" name="txtPassword"><br>
        <font color="red"> ${requestScope.INVALID.passwordError}</font><br>
        <input type="submit" name="action" value="Login">
        <a href="register.jsp"><input type="button" value="Register"></a>
        <br>
        <br>
    </form>
</div>
</body>
</html>
