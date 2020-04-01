<%@include file="header.jsp" %>
<c:if test="${requestScope.POST_LIST == null}" >
    <c:redirect url="MainController?action=ShowAllPost">
        <c:param name="txtSkip" value="0"/>
    </c:redirect>
</c:if>
<div id="content">
    <center>
        <form action="MainController" method="post">
            <input type="text" name="txtSearch" value="${param.txtSearch}">
            <input type="hidden" name="txtSkip" value="0">
            <input type="submit" name="action" value="Search">
        </form>
    </center>

    <c:if test="${not empty  requestScope.POST_LIST}" var="chk">

        <c:forEach items="${requestScope.POST_LIST}" var="post">
            <h1><a href="MainController?action=ShowPostDetail&txtPID=${post.id}">${post.title}</a></h1>
            <p>Author: ${post.account.name}</p>
            <h2>Date: ${post.date}</h2>
            <h3>&emsp;${post.shortDescription}</h3>           

        </c:forEach>
    </div>
    <div class="pagination">
        <c:set value="1" var="page"/>
        <c:set value="0" var="skip"/>
        <c:set value="${requestScope.TOTAL}" var="postNum" scope="session"/>
        <a href="MainController?action=ShowAllPost&txtSkip=0">${page}</a>
        <c:forEach begin="0" end="${postNum}" var="total">
            <c:if test="${(total% 20 == 1)  && (total !=1) }">
                <c:set value="${skip + 20}" var="skip"/>
                <c:set value="${page+1}" var="page"/>
                <a href="MainController?action=ShowAllPost&txtSkip=${skip}">${page}</a>                
            </c:if>
        </c:forEach>
    </div>
</c:if>
<c:if test="${!chk}">
    No Post Available
</c:if>
</html>
