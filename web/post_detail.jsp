<%@include file="header.jsp" %>
<body>
    <div id="content">


        <c:if test="${requestScope.POST != null}" var="chk">
            <h1><a href="MainController?action=ShowPostDetail&txtPID=${requestScope.POST.title}">${requestScope.POST.title}</a></h1>
            <h2>Author: ${requestScope.POST.account.name}</h2>
            <p>Date: ${requestScope.POST.date}</p><br>
            <p>${requestScope.POST.shortDescription}</p><br>  
            <p>&ensp; ${requestScope.POST.content}</p><br>

            <c:if test="${requestScope.POST.status eq 2}">
                Comment<br>
                <c:forEach items="${requestScope.POST.comment}" var="comment">
                    <b>${comment.accountID}</b>: ${comment.content}<br>
                </c:forEach>

                    <form action="MainController" method="post">
                        <input type="text" name="txtComment" placeholder="Your comment...">
                        <input type="hidden" name="txtPID" value="${requestScope.POST.id}"> 
                        <input type="hidden" name="txtAID" value="${sessionScope.USER.email}">
                        <input type="submit" name="action" value="Comment">
                        <font color="red">${requestScope.INVALID}</font>
                    </form>


            </c:if>

        </c:if>
    </div>
    <br><br>

    <c:if test="!chk">
        <c:redirect url="index.jsp"/>
    </c:if>
</body>
</html>
