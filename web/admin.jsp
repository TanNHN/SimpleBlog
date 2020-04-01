<%@include file="header.jsp" %>
<c:if test="${sessionScope.USER==null && sessionScope.USER.role eq '2' }">
    <c:redirect url="login.jsp"/>
</c:if>
<div id="content">
    <h1>Recent article posted by user</h1>

    <form action="MainController" method="post">
        <input type="text" name="txtSearch" placeholder="Search title">
        <select name="txtStatus">
            <option value="1">New</option>
            <option value="2">Active</option>
            <option value="3">Delete</option>
        </select>
        <input type="hidden" name="txtSkip" value="0">
        <input type="submit" name="action" value="Search based on Status">
    </form>
    <c:if test="${requestScope.NEW_POST_LIST == null}" var="rdr">
        <c:redirect url="MainController?action=ShowNewPost">
            <c:param name="txtSkip" value="0"/>
        </c:redirect>
    </c:if>
    <c:if test="${!rdr}">
        <c:if test="${not empty requestScope.NEW_POST_LIST}" var="chk">
            <div>
                <c:if test="${not empty  requestScope.NEW_POST_LIST}" var="chk">
                    <c:forEach items="${requestScope.NEW_POST_LIST}" var="post">
                        <h2><a href="MainController?action=ShowPostDetail&txtPID=${post.id}">${post.title}</a></h2> 
                        Author: ${post.account.name}<br>
                        Date: ${post.date}<br>
                        <h3>${post.shortDescription}</h3>
                        <form action="MainController" method="post">
                            <input type="hidden" name="txtID" value="${post.id}">
                            <input type="submit" name="action" value="Approval">
                            <input type="submit" name="action" value="Delete">
                        </form>
                        <br>
                        <hr>
                    </c:forEach>

                </c:if>
            </div>
        </c:if>
        <c:if test="${!chk}">
            User has not post any article recently
        </c:if>
        <div class="pagination">
            <c:set value="1" var="page"/>
            <c:set value="0" var="skip"/>
            <c:set value="${requestScope.TOTAL}" var="postNum" />
            <a href="MainController?action=ShowNewPost&txtSkip=0">${page}</a>
            <c:forEach begin="0" end="${postNum}" var="total">
                <c:if test="${(total% 20 == 1)  && (total !=1) }">
                    <c:set value="${skip + 20}" var="skip"/>
                    <c:set value="${page+1}" var="page"/>
                    <a href="MainController?action=ShowNewPost&txtSkip=${skip}">${page}</a>                
                </c:if>
            </c:forEach>
        </div>
    </c:if>
    <br><br>
</div>

</body>
</html>
