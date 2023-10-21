<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Elj Shop - Online Art Supplies Shop</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <meta content="Free HTML Templates" name="keywords">
        <meta content="Free HTML Templates" name="description">

        <!<!-- Favicon -->
        <link rel="icon" href="${pageContext.request.contextPath}/img/logo.ico" type="image/icon type">

        <!-- Google Web Fonts -->
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap" rel="stylesheet">  

        <!-- Font Awesome -->
        <link href="${pageContext.request.contextPath}/css/font-awesome_5.10.0_css_all.min.css?version=1" rel="stylesheet">

        <!-- Libraries Stylesheet -->
        <link href="${pageContext.request.contextPath}/lib/animate/animate.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">

        <!-- Customized Bootstrap Stylesheet -->
        <link href="${pageContext.request.contextPath}/css/loginStyle.css?version=1" rel="stylesheet">
    </head>
    <body>
        <div class="wrapper">
            <form action="${pageContext.request.contextPath}/login" class="login" method="POST">
                <p class="title">Log in</p>
                <c:if test="${notification != null}">
                    <div class="alert <c:choose><c:when test="${notiType == 'RED'}">alert-danger</c:when><c:otherwise>alert-success</c:otherwise></c:choose>">
                        <strong>${notification}</strong>
                        <%session.removeAttribute("notification");%>
                        <%session.removeAttribute("notiType");%>
                    </div>
                </c:if>
                <input name="username" type="text" placeholder="Email" autofocus/>
                <i class="fa fa-user"></i>
                <input name="password" type="password" placeholder="Password" />
                <i class="fa fa-key"></i>
                <a href="${pageContext.request.contextPath}/register">Register</a>
                <span style="font-size: 0.8em;">or</span>
                <a href="${pageContext.request.contextPath}/forgot-password">Forgot password?</a>
                <div class="row text-center">
                    <input name="loginSubmit" class="registerSubmit" type="submit" value="Login">
                </div>
                <a href="https://accounts.google.com/o/oauth2/auth?scope=profile%20email&redirect_uri=http://localhost:8080/Online_Shopping_System/login-google&response_type=code&client_id=114010930889-heqf5hnbf5eo1vfb4p5j02pcr8vl3bfu.apps.googleusercontent.com&approval_prompt=force">Login With Google</a>
            </form>
        </div>
    </body>
</html>
