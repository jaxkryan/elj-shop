<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <link href="${pageContext.request.contextPath}/css/style.css?version=1" rel="stylesheet">
    </head>
    <body>
        <!-- Topbar Start -->
        <div class="container-fluid">
            <div class="row align-items-center bg-light py-3 px-xl-5 d-lg-flex">
                <div class="col-lg-4 d-xs-none d-sm-none d-lg-block">
                    <a href="${pageContext.request.contextPath}/home" class="text-decoration-none">
                        <span class="h1 text-primary bg-dark px-2">Elj</span>
                        <span class="h1 text-dark bg-primary px-2 ml-n1">Shop</span>
                    </a>
                </div>
                <div class="col-lg-4 col-sm-6 col-12 text-left">
                    <form action="searchproduct" method="post">
                        <div class="input-group">
                            <input value="${searchName}" name="searchName" type="text" class="form-control" placeholder="Search for products">
                            <div class="input-group-append">
                                <button type="submit" class="input-group-text bg-transparent text-primary" title="Search" name="searchSubmit">
                                    <i class="fa fa-search"></i>
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="col-lg-4 col-sm-6 col-12 text-xs-center text-right pt-xs-3">
                    <c:choose>
                        <c:when test="${userId == null}">
                            <a href="${pageContext.request.contextPath}/login" class="btn btn-primary mx-2">Log In</a>
                            <a href="${pageContext.request.contextPath}/register" class="btn btn-primary">Register</a>
                        </c:when>
                        <c:otherwise>
                            <a href="${pageContext.request.contextPath}/profile" class="fa fa-user-circle btn btn-primary mx-2"></a>
                            <a href="${pageContext.request.contextPath}/logout" class="btn btn-primary">Log Out</a>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
        <!-- Topbar End -->

        <!-- Navbar Start -->
        <div class="container-fluid bg-dark mb-30">
            <div class="row px-xl-5">
                <div class="col-lg-3 d-none d-lg-block">
                    <a class="btn d-flex align-items-center justify-content-between bg-primary w-100" data-toggle="collapse" href="#navbar-vertical" style="height: 65px; padding: 0 30px;">
                        <h6 class="text-dark m-0"><i class="fa fa-bars mr-2"></i>Categories</h6>
                        <i class="fa fa-angle-down text-dark"></i>
                    </a>
                    <nav class="collapse position-absolute navbar navbar-vertical navbar-light align-items-start p-0 bg-light" id="navbar-vertical" style="width: calc(100% - 30px); z-index: 999;">
                        <div class="navbar-nav w-100">
                            <c:forEach items="${categories}" var="cate">
                                <a href="${pageContext.request.contextPath}/productfilter?categoryId=${cate.id}" class="nav-item nav-link">${cate.name}</a>
                            </c:forEach>
                        </div>
                    </nav>
                </div>
                <div class="col-lg-9">
                    <nav class="navbar navbar-expand-lg bg-dark navbar-dark py-3 py-lg-0 px-0">
                        <a href="${pageContext.request.contextPath}/home" class="text-decoration-none d-block d-lg-none">
                            <span class="h1 text-primary bg-dark px-2">Elj</span>
                            <span class="h1 text-dark bg-primary px-2 ml-n1">Shop</span>
                        </a>
                        <button type="button" class="navbar-toggler" data-toggle="collapse" data-target="#navbarCollapse">
                            <span class="navbar-toggler-icon"></span>
                        </button>
                        <div class="collapse navbar-collapse justify-content-between" id="navbarCollapse">
                            <div class="navbar-nav mr-auto py-0">
                                <a href="${pageContext.request.contextPath}/home" class="nav-item nav-link active">Home</a>
                                <a href="${pageContext.request.contextPath}/shop" class="nav-item nav-link">Shop</a>
                                <div class="nav-item dropdown">
                                    <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown">Brands <i class="fa fa-angle-down mt-1"></i></a>
                                    <div class="dropdown-menu bg-primary rounded-0 border-0 m-0">
                                        <c:forEach items="${providers}" var="provider">
                                            <a href="${pageContext.request.contextPath}/productfilter?providerId=${provider.id}" class="dropdown-item">${provider.companyName}</a>
                                        </c:forEach>
                                    </div>
                                </div>
                                <a href="${pageContext.request.contextPath}/customer-order" class="nav-item nav-link">Order History</a>
                            </div>
                            <div class="navbar-nav ml-auto py-0 d-none d-lg-block">
                                <a href="${pageContext.request.contextPath}/cart" class="btn px-0 ml-3">
                                    <i class="fas fa-shopping-cart text-primary"></i>
                                </a>
                                <span class="badge text-secondary border border-secondary rounded-circle" style="padding-bottom: 2px;">
                                    <c:choose>
                                        <c:when test="${userId == null}">0</c:when>
                                        <c:otherwise><c:out value="${sessionScope.cartItem.size()}"></c:out></c:otherwise>
                                    </c:choose>
                                </span>
                            </div>
                        </div>
                    </nav>
                </div>
            </div>
        </div>
        <!-- Navbar End -->

        <!-- Notification Start -->
        <c:if test="${notification != null}">
            <div class="container-fluid mb-3">
                <div class="row px-xl-5">
                    <div class="col-lg-12">
                        <div class="alert <c:choose><c:when test="${notiType == 'RED'}">alert-danger</c:when><c:otherwise>alert-success</c:otherwise></c:choose>">
                                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                                        <strong><%= session.getAttribute("notification")%></strong>
                            <%session.removeAttribute("notification");%>
                            <%session.removeAttribute("notiType");%>
                        </div>
                    </div>
                </div>
            </div>
        </c:if>
        <!-- Notification End -->
    </body>
</html>
