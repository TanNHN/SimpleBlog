<%@include file="header.jsp" %>
<div id="content">
    <form action="MainController" method="post">
        
        Email: <input type="text" name="txtEmail" value="${param.txtEmail}"><br>

        <input type="checkbox" value="yes" name="avaiChk">This email is available<br>
        <c:if test="${avaiChk ne 'yes'}">
            <input type="hidden" value="no" name="avaiChk">
        </c:if>
        <font color="red"> ${requestScope.INVALID.emailError} </font><br>
        Your name: <input type="text" name="txtUsername" value="${param.txtUsername}"><br>
        <font color="red"> ${requestScope.INVALID.nameError} </font><br>
        Password: <input type="password" name="txtPassword"><br>
        <font color="red"> ${requestScope.INVALID.passwordError} </font><br>

        <input type="submit" name="action" value="Create Account">
        <br><br>
    </form>
</div> 

</body>
</html>
