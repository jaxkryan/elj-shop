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
            <form action="${pageContext.request.contextPath}/forgot-password" class="login" method="POST">
                <p class="title">Forgot Password</p>
                <c:if test="${notification != null}">
                    <div class="alert <c:choose><c:when test="${notiType == 'RED'}">alert-danger</c:when><c:otherwise>alert-success</c:otherwise></c:choose>">
                        <strong>${notification}</strong>
                        <%session.removeAttribute("notification");%>
                        <%session.removeAttribute("notiType");%>
                    </div>
                </c:if>
                <p style="font-size: 0.8em;">Please enter your email, we'll send a new random password to your inbox</p>
                <input name="email" type="text" placeholder="Email" autofocus/>
                <i class="fa fa-user"></i>
                <a href="${pageContext.request.contextPath}/login">Log in</a>
                <span style="font-size: 0.8em;">or</span>
                <a href="${pageContext.request.contextPath}/register">Register</a>
                <div class="row text-center">
                    <input name="forgotPasswordSubmit" class="registerSubmit" type="submit" value="Send">
                </div>
            </form>
        </div>
    </body>
</html>
