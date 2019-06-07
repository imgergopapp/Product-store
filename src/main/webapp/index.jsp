<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Product store</title>

        <c:url value="/style.css" var="styleUrl"/>
        <c:url value="/init.js" var="initScriptUrl"/>
        <c:url value="/register.js" var="registerScriptUrl"/>
        <c:url value="/login.js" var="loginScriptUrl"/>
        <c:url value="/logout.js" var="logoutScriptUrl"/>
        <c:url value="/util.js" var="utilScriptUrl"/>
        <c:url value="/auth.js" var="authScriptUrl"/>

        <link rel="stylesheet" type="text/css" href="${styleUrl}">
        <script src="${registerScriptUrl}"></script>
        <script src="${loginScriptUrl}"></script>
        <script src="${logoutScriptUrl}"></script>
        <script src="${utilScriptUrl}"></script>
        <script src="${initScriptUrl}"></script>
    </head>
    <body>
        <div id="navigation-bar" class="hidden navbar content ">
            <a href="javascript:void(0);" onclick="alert('home');">Home</a>
        </div>
        <div id="login-content" class="hidden content">
            <h1>Login</h1>
            <form id="login-form" onsubmit="return false;">
                <input type="text" name="email" placeholder="Email">
                <input type="password" name="password" placeholder="Password">
                <button class="normalBtn" id="login-button">Login</button>
                <button class="normalBtn" id="register-button">Register</button>
            </form>
        </div>
        <div id="register-content" class="hidden content">
            <h1>Register</h1>
            <form id="register-form" onsubmit="return false;">
                <input type="text" name="name" placeholder="Name"><br>
                <input type="text" name="email" placeholder="Email"><br>
                <input type="password" name="password" placeholder="Password"><br>
                <button id="registration-button">Register Account</button>
                <button id="back-to-login-button">Back to login</button><br>
            </form>
        </div>
        <script src="${authScriptUrl}"></script>
    </body>
</html>
