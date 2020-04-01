<%@include file="header.jsp" %>
<div id="content">
    <h2>
        Search result for '${param.txtSearch}':
    </h2>
    <c:if test="${empty requestScope.POST_LIST }" var="chk" >
        No result
        <br><br>
    </c:if>
    <c:if test="${!chk}">

        <h1>POSTS</h1><br>
        <c:if test="${not empty  requestScope.POST_LIST}" var="chk">
            <c:forEach items="${requestScope.POST_LIST}" var="post">
                <h2><a href="MainController?action=ShowPostDetail&txtPID=${post.id}">${post.title}</a></h2> 
                Author: ${post.account.name}<br>
                Date: ${post.date}<br>
                <h3>${post.shortDescription}</h3>
                <br>
                <hr>
            </c:forEach>

        </c:if>
        <div class="pagination">
            <c:set value="1" var="page"/>
            <c:set value="0" var="skip"/>
            <c:set value="${requestScope.TOTAL}" var="postNum" />
            <a href="MainController?action=Search&txtSkip=0&txtSearch=${param.txtSearch}">${page}</a>
            <c:forEach begin="0" end="${postNum}" var="total">
                <c:if test="${(total% 20 == 1)  && (total !=1) }">
                    <c:set value="${skip + 20}" var="skip"/>
                    <c:set value="${page+1}" var="page"/>
                    <a href="MainController?action=Search&txtSearch=${param.txtSearch}&txtSkip=${skip}">${page}</a>                
                </c:if>
            </c:forEach>
        </div>

    </c:if>
</div>   

</body>
</html>
