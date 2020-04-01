<%@include file="header.jsp" %>
<c:if test="${sessionScope.USER.role ne 1}">
    <c:redirect url="main_page.jsp"/>
</c:if>
<div id="content">
    <form action="MainController" method="post">
        <input type="text" name="txtSearch" value="${param.txtSearch}" placeholder="Search title">
        <select name="txtStatus">
            <option value="1" <c:if test="${param.txtStatus == 1}">selected="true"</c:if>>New</option>
            <option value="2" <c:if test="${param.txtStatus == 2}">selected="true"</c:if>>Active</option>
            <option value="3" <c:if test="${param.txtStatus == 3}">selected="true"</c:if>>Delete</option>
            </select>
            <input type="hidden" name="txtSkip" value="0">
            <input type="submit" name="action" value="Search based on Status">
        </form>
    <c:if test="${empty requestScope.SEARCH_RESULT }" var="chk" >
        <br> No result
    </c:if>
    <c:if test="${!chk}">
        <c:if test="${not empty  requestScope.SEARCH_RESULT}" var="chk">
            <c:forEach items="${requestScope.SEARCH_RESULT}" var="post">
                <h2><a href="MainController?action=ShowPostDetail&txtPID=${post.id}">${post.title}</a></h2> 
                Author: ${post.account.name}<br>
                Date: ${post.date}<br>
                <h3>${post.shortDescription}</h3>
                <br>
                <c:if test="${param.txtStatus == 1}">
                    <a href="MainController?action=Approval&txtID=${post.id}"><input type="button" value="Approval"></a>
                    <a href="MainController?action=Delete&txtID=${post.id}"><input type="button" value="Delete"></a>
                    </c:if>
                <hr>
            </c:forEach>

        </c:if>

        <div class="pagination">
            <c:set value="1" var="page"/>
            <c:set value="0" var="skip"/>
            <c:set value="${requestScope.TOTAL}" var="postNum" />
            <a href="MainController?action=Search+based+on+Status&txtSkip=0&txtSearch=${param.txtSearch}&txtStatus=${param.txtStatus}">${page}</a>
            <c:forEach begin="0" end="${postNum}" var="total">
                <c:if test="${(total% 20 == 1)  && (total !=1) }">
                    <c:set value="${skip + 20}" var="skip"/>
                    <c:set value="${page+1}" var="page"/>
                    <a href="MainController?action=Search+based+on+Status&txtSkip=${skip}&txtSearch=${param.txtSearch}&txtStatus=${param.txtStatus}">${page}</a>                
                </c:if>
            </c:forEach>
        </div>
    </c:if><br><br>
</div>


</div>
</body>
</html>
