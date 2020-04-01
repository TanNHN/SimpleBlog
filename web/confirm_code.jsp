<%@include file="header.jsp" %>
<body>
    <br><br>
    We have sent a verify code to ${requestScope.EMAIL} 
    <br><br>
    <form action="MainController" method="post">
        <input type="hidden" name="txtCode" value="${requestScope.CODE}">
        <input type="hidden" name="txtEmail" value="${requestScope.EMAIL}">
        Verify Code:
        <input type="text" name="txtInput">
        <font color="red">${requestScope.WRONG}</font>
        <br><input type="submit" name="action" value="Verify Account">
    </form>
</body>
</html>
