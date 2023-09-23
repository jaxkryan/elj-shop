<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="model.Product" %>
<%@ page import="model.Category" %>
<%@ page import="model.User" %>
<%@ page import="java.util.Vector" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Elj Shop - Online Art Supplies Shop</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <!------ Include the above in your HEAD tag ---------->
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
    </head>

    <body>
        <%@include file="header.jsp" %>
        <div class="container-fluid pt-5 pb-3">
            <h2 class="section-title position-relative text-uppercase mx-xl-5 mb-4"><span class="bg-secondary pr-3">Featured Products</span></h2>
            <div class="row px-xl-5">
                <c:forEach items="${products}" var="pro">
                    <div class="col-lg-3 col-md-4 col-sm-6 pb-1">
                        <div class="product-item bg-light mb-4">
                            <div class="product-img position-relative overflow-hidden">
                                <img class="img-fluid w-100" src="${pageContext.request.contextPath}/img/product-${pro.id}.jpg" alt="">
                                <div class="product-action">
                                    <a title="Add to cart" class="btn btn-outline-dark btn-square" href="add-to-cart?proId=${pro.id}"><i class="fa fa-shopping-cart"></i></a>
                                    <a title="Add to favorite" class="btn btn-outline-dark btn-square" href="add-to-favorite?proId=${pro.id}"><i class="far fa-heart"></i></a>
                                    <a title="See details" class="btn btn-outline-dark btn-square" href="details?proId=${pro.id}"><i class="fa fa-search"></i></a>
                                </div>
                            </div>
                            <div class="text-center py-4">
                                <a title="${pro.name}" class="h6 text-decoration-none text-truncate" href="details?proId=${pro.id}">${pro.name}</a>
                                <div class="d-flex align-items-center justify-content-center mt-2">
                                    <c:set var="currentPrice" value="${pro.price * (1-pro.discount)}"/>
                                    <fmt:setLocale value="vi_VN"/>
                                    <h5 class="text-primary"><fmt:formatNumber type="currency" pattern="###,###¤">${currentPrice}</fmt:formatNumber></h5>
                                    <c:if test="${currentPrice < pro.price}">
                                        <h6 class="text-muted ml-2">
                                            <del><fmt:formatNumber type="currency" pattern="###,###¤">${pro.price}</fmt:formatNumber></del>
                                            </h6>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
        <%@include file="footer.jsp" %>

        <!-- JavaScript Libraries -->
        <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>
        <script src="${pageContext.request.contextPath}/lib/easing/easing.min.js"></script>
        <script src="${pageContext.request.contextPath}/lib/owlcarousel/owl.carousel.min.js"></script>

        <!-- Template Javascript -->
        <script src="${pageContext.request.contextPath}/js/main.js"></script>
    </body>

</html>