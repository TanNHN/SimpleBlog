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
        <br>Or
        <br>
    </form>
    <script src="https://apis.google.com/js/platform.js" async defer></script>

    <meta name="google-signin-client_id" content="928438322944-fij2qb8hqeaql8fa89avf86rn1q990m7.apps.googleusercontent.com">
    <div class="g-signin2" data-onsuccess="onSignIn"></div
    <br>
    <br>
    <br>

</div>
<script>
    function onSignIn(googleUser) {
        var profile = googleUser.getBasicProfile();
        var name = profile.getName();
        var email = profile.getEmail();
        var params = 'txtName=' + name + '&txtEmail=' + email;
        window.location.href = "http://localhost:8080/SimpleBlog/GoogleLoginController?" + params;
    }
</script>
</body>
</html>
