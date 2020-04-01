<%@include file="header.jsp" %>
<c:if test="${sessionScope.USER.role ne 1}">
    <c:redirect url="main_page.jsp"/>
</c:if>
<div id="content">
    <h1>Active new User</h1>

    <font color="red"><h3>${requestScope.STATUS}</h3></font>

    <br><br><br>    <c:if test="${not empty requestScope.USER_LIST}" var="chk">
        <table border="1">
            <thead>
                <tr>
                    <th>Email</th>
                    <th>Name</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${requestScope.USER_LIST}" var="account">

                    <tr>
                <form action="MainController" method="post">
                    <td><input type="hidden" name="txtEmail" value="${account.email}">${account.email}</td>
                    <td>${account.name}</td>
                    <td><input type="submit" name="action" value="Active"></td>
                </form>

                </tr>
            </c:forEach>

            </tbody>
        </table>
    </c:if>
    <c:if test="${!chk}">
        no new Account this time
    </c:if>
        <br><br>
</div>


</body>
</html>
