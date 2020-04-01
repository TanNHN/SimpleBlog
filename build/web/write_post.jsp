<%@include file="header.jsp"%>
<c:if test="${sessionScope.USER.role != 2   } ">
    <c:redirect url="mainpage.jsp"/>
</c:if>
<h2><font color='blue'>${requestScope.POSTED}</font></h2>
<div id="content">
    <h1>Write a new article</h1>

    <form action="MainController" method="post">
        <br>Title: <textarea lang="vi" name="txtTitle" style="width:200px; font-family: sans-serif">${param.txtTitle}</textarea><br>
        <font color="red">${requestScope.INVALID.titleError}</font><br>
        Short Description:<br> <textarea name="txtShortDescription" style="width:400px; height:100px; font-family: sans-serif">${param.txtShortDescription}</textarea><br>
        <font color="red">${requestScope.INVALID.shortDescriptionError}</font><br>
        Content:<br> <textarea lang="vi" name="txtContent" style="width:400px; height:200px; font-family: sans-serif">${param.txtContent}</textarea><br>
        <font color="red">${requestScope.INVALID.contentError}</font><br>
       <input type="submit" name="action" value="Post"><br><br>
    </form>
</div>
</div>
</body>
</html>
