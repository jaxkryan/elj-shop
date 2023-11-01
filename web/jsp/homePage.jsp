<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>Elj Shop - Online Art Supplies Shop</title>
         <link rel="icon" href="${pageContext.request.contextPath}/img/logo.ico" type="image/icon type">
    </head>

    <body>
        <%@include file="header.jsp" %>

        <!-- Carousel Start -->
        <div class="container-fluid mb-3">
            <div class="row px-xl-5">
                <div class="col-lg-8">
                    <div id="header-carousel" class="carousel slide carousel-fade mb-30 mb-lg-0" data-ride="carousel">
                        <ol class="carousel-indicators">
                            <li data-target="#header-carousel" data-slide-to="0" class="active"></li>
                            <li data-target="#header-carousel" data-slide-to="1"></li>
                            <li data-target="#header-carousel" data-slide-to="2"></li>
                        </ol>
                        <script>
                            $(document).ready(function () {
                                setInterval(function () {
                                    var next = $('.carousel-item.active').next();
                                    if (!next.length) {
                                        next = $('.carousel-item:first');
                                    }
                                    next.addClass('active');
                                    $('.carousel-item.active').first().removeClass('active');
                                }, 5000);
                            });
                        </script>
                        <div class="carousel-inner">
                            <div class="carousel-item position-relative active" style="height: 430px;">
                                <img class="position-absolute w-100 h-100" src="https://shoebakery.com/cdn/shop/files/sneaker_banner_22_1400x.progressive.jpg?v=1656607256" style="object-fit: cover;">
                                <div class="carousel-caption d-flex flex-column align-items-center justify-content-center">
                                    <div class="p-3" style="max-width: 700px;">
                                        <h1 class="display-4 text-white mb-3 animate__animated animate__fadeInDown">Adidas</h1>
                                        <p class="mx-md-5 px-5 animate__animated animate__bounceIn">Make a statement - Stand out from the crowd and express your unique sense of style.</p>
                                        <a class="btn btn-outline-light py-2 px-4 mt-3 animate__animated animate__fadeInUp" href="${pageContext.request.contextPath}/productfilter?providerId=1">Shop Now</a>
                                    </div>
                                </div>
                            </div>
                            <div class="carousel-item position-relative" style="height: 430px;">
                                <img class="position-absolute w-100 h-100" src="https://media.istockphoto.com/id/1350560575/photo/pair-of-blue-running-sneakers-on-white-background-isolated.webp?b=1&s=170667a&w=0&k=20&c=liUSgX6SafJ7HWvefFqR9-pnf3KuH6v1lwQ6iEpePWc=" style="object-fit: cover;">
                                <div class="carousel-caption d-flex flex-column align-items-center justify-content-center">
                                    <div class="p-3" style="max-width: 700px;">
                                        <h1 class="display-4 text-white mb-3 animate__animated animate__fadeInDown">Nike</h1>
                                        <p class="mx-md-5 px-5 animate__animated animate__bounceIn">Style up your game - Boost your confidence and stand out in every activity!</p>
                                        <a class="btn btn-outline-light py-2 px-4 mt-3 animate__animated animate__fadeInUp" href="${pageContext.request.contextPath}/productfilter?providerId=2">Shop Now</a>
                                    </div>
                                </div>
                            </div>
                            <div class="carousel-item position-relative" style="height: 430px;">
                                <img class="position-absolute w-100 h-100" src="https://marvel-b1-cdn.bc0a.com/f00000000114841/www.florsheim.com/shop/index/FW23-FL-FirstRefresh-CasualHeist-Desktop.jpg" style="object-fit: cover;">
                                <div class="carousel-caption d-flex flex-column align-items-center justify-content-center">
                                    <div class="p-3" style="max-width: 700px;">
                                        <h1 class="display-4 text-white mb-3 animate__animated animate__fadeInDown">Puma</h1>
                                        <p class="mx-md-5 px-5 animate__animated animate__bounceIn">Elegance and genteel - Shoes for true gentlemen</p>
                                        <a class="btn btn-outline-light py-2 px-4 mt-3 animate__animated animate__fadeInUp" href="${pageContext.request.contextPath}/productfilter?providerId=3">Shop Now</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4">
                    <div class="product-offer mb-30" style="height: 200px;">
                        <img class="img-fluid" src="https://www.barkershoes.com/cdn/shop/collections/8I5A5326_1024x700_crop_center.jpg?v=1618574799" alt="">
                        <div class="offer-text">
                            <h3 class="text-white mb-3">Style</h3>
                            <h6 class="text-white text-uppercase">Style up yourself</h6>
                            <a href="${pageContext.request.contextPath}/productfilter" class="btn btn-primary">Shop Now</a>
                        </div>
                    </div>
                    <div class="product-offer mb-30" style="height: 200px;">
                        <img class="img-fluid" src="https://www.barkershoes.com/cdn/shop/files/SS20_HOMEPAGE_MCCLEANPAIR_880x550_crop_center.jpg?v=1614334815" alt="">
                        <div class="offer-text">
                            <h3 class="text-white mb-3">Fashion</h3>
                            <h6 class="text-white text-uppercase">Become a gentlemen</h6>
                            <a href="${pageContext.request.contextPath}/productfilter" class="btn btn-primary">Shop Now</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Carousel End -->

        <!-- Categories Start -->
        <div class="container-fluid pt-5">
            <h2 class="section-title position-relative text-uppercase mx-xl-5 mb-4"><span class="bg-secondary pr-3">Categories</span></h2>
            <div class="row px-xl-5 pb-3">
                <c:forEach items="${categories}" var="cate">
                    <div class="col-lg-3 col-md-4 col-sm-6 pb-1">
                        <a class="text-decoration-none" href="${pageContext.request.contextPath}/productfilter?categoryId=${cate.id}">
                            <div class="cat-item d-flex align-items-center mb-4">
                                <div class="overflow-hidden" style="width: 100px; height: 100px; display: flex; justify-content: center;">
                                    <img class="img-category" style="width: 100px; height: 100px" src="${cate.image}" alt="">
                                </div>
                                <div class="flex-fill pl-3">
                                    <h6>${cate.name}</h6>
                                    <small class="text-body">${cate.name} Shoes</small>
                                </div>
                            </div>
                        </a>
                    </div>
                </c:forEach>
            </div>
        </div>
        <!-- Categories End -->

        <!-- Brands Start -->
        <div class="container-fluid pt-5">
            <h2 class="section-title position-relative text-uppercase mx-xl-5 mb-4"><span class="bg-secondary pr-3">Brands</span></h2>
            <div class="row px-xl-5 pb-3">
                <c:forEach items="${providers}" var="provider">
                    <div class="col-lg-3 col-md-4 col-sm-6 pb-1">
                        <a class="text-decoration-none" href="${pageContext.request.contextPath}/productfilter?providerId=${provider.id}">
                            <div class="cat-item d-flex align-items-center mb-4">
                                <div class="overflow-hidden" style="width: 100px; height: 100px; display: flex; justify-content: center;">
                                    <img class="img-provider" style="width: 100px; height: 100px" src="${provider.image}" alt="">
                                </div>
                                <div class="flex-fill pl-3">
                                    <h6>${provider.companyName}</h6>
                                    <small class="text-body">${provider.companyName} Shoes</small>
                                </div>
                            </div>
                        </a>
                    </div>
                </c:forEach>
            </div>
        </div>
        <!-- Brands End -->

        <!-- Products Start -->
        <div class="container-fluid pt-5 pb-3">
            <h2 class="section-title position-relative text-uppercase mx-xl-5 mb-4"><span class="bg-secondary pr-3">Hot Products</span></h2>
            <div class="row px-xl-5">
                <c:forEach items="${products}" var="pro">
                    <div class="col-lg-3 col-md-4 col-sm-6 pb-1">
                        <div class="product-item bg-light mb-4">
                            <div class="product-img position-relative overflow-hidden">
                                <img class="img-fluid w-100" style="width: 276.13px; height: 276.13px" src="${pro.image}" alt="">
                                <div class="product-action">
                                    <a title="Add to cart" class="btn btn-outline-dark btn-square" href="add-to-cart?proId=${pro.id}"><i class="fa fa-shopping-cart"></i></a>
                                    <a title="See details" class="btn btn-outline-dark btn-square" href="details?proId=${pro.id}&from=home"><i class="fa fa-search"></i></a>
                                </div>
                            </div>
                            <div class="text-center py-4">
                                <a title="${pro.name}" class="h6 text-decoration-none text-truncate" href="details?proId=${pro.id}">${pro.name}</a>
                                <div class="d-flex align-items-center justify-content-center mt-2">
                                    <c:set var="currentPrice" value="${pro.price - pro.discount}"/>
                                    <fmt:setLocale value="en_US"/>
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
        <!-- Products End -->

        <div class="container-fluid pt-5">
            <div class="row px-xl-5 pb-3">
                <div class="col-lg-3 col-md-6 col-sm-12 pb-1">
                    <div class="d-flex align-items-center bg-light mb-4" style="padding: 30px;">
                        <h1 class="fa fa-check text-primary m-0 mr-3"></h1>
                        <h5 class="font-weight-semi-bold m-0">Quality Product</h5>
                    </div>
                </div>
                <div class="col-lg-3 col-md-6 col-sm-12 pb-1">
                    <div class="d-flex align-items-center bg-light mb-4" style="padding: 30px;">
                        <h1 class="fa fa-shipping-fast text-primary m-0 mr-2"></h1>
                        <h5 class="font-weight-semi-bold m-0">Fast Shipping</h5>
                    </div>
                </div>
                <div class="col-lg-3 col-md-6 col-sm-12 pb-1">
                    <div class="d-flex align-items-center bg-light mb-4" style="padding: 30px;">
                        <h1 class="fas fa-exchange-alt text-primary m-0 mr-3"></h1>
                        <h5 class="font-weight-semi-bold m-0">14-Day Return</h5>
                    </div>
                </div>
                <div class="col-lg-3 col-md-6 col-sm-12 pb-1">
                    <div class="d-flex align-items-center bg-light mb-4" style="padding: 30px;">
                        <h1 class="fa fa-phone-volume text-primary m-0 mr-3"></h1>
                        <h5 class="font-weight-semi-bold m-0">24/7 Support</h5>
                    </div>
                </div>
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
