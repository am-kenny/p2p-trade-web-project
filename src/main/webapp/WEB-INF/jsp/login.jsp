<%--
  Created by IntelliJ IDEA.
  User: Andre
  Date: 04.07.2024
  Time: 19:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Title</title>
    </head>
    <body>
        <h3>Login</h3>
        <hr/>
        <form name="loginForm" method="POST" action="${pageContext.request.contextPath}/controller">
            <input type="hidden" name="command" value="login">
            Login: <br>
            <input type="text" name="username" value=""><br>
            Password: <br>
            <input type="password" name="password" value=""><br>
            <input type="submit" value="Login">
        </form>
        <hr/>
        <jsp:expression>
            (request.getAttribute("errorMessage") != null)
            ? (String) request.getAttribute("errorMessage")
            : ""
        </jsp:expression>
    </body>
</html>
