<%-- 
    Document   : shopPage
    Created on : Jul 5, 2023, 5:03:41 PM
    Author     : Huy Nguyen
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <title>Elj Shop - Online Art Supplies Shop</title>
    </head>

    <body>
        <%@include file="header.jsp" %>
        <!-- Shop Start -->
        <div class="container-fluid">
            <div class="row px-xl-5">
                <!-- Shop Sidebar Start -->
                <div class="col-lg-3 col-md-4">
                    <form action="productfilter" method="GET">
                        <!-- Categories Start -->
                        <h5 class="section-title position-relative text-uppercase mb-3"><span class="bg-secondary pr-3">Sort By Price</span></h5>
                        <div class="bg-light p-4 mb-30">
                            <div class="custom-control custom-radio d-flex align-items-center justify-content-between mb-3">
                                <input type="radio" class="custom-control-input" name="sort" id="default" value="" onclick="this.form.submit()" <c:if test="${(param.sort == null || param.sort == '')}">checked</c:if>>
                                    <label class="custom-control-label" for="default">Default</label>
                                </div>
                                <div class="custom-control custom-radio d-flex align-items-center justify-content-between mb-3">
                                    <input type="radio" class="custom-control-input" name="sort" id="sort-ascending" value="ascending" onclick="this.form.submit()" <c:if test="${param.sort == 'ascending'}">checked</c:if>>
                                    <label class="custom-control-label" for="sort-ascending">Ascending</label>
                                </div>
                                <div class="custom-control custom-radio d-flex align-items-center justify-content-between mb-3">
                                    <input type="radio" class="custom-control-input" name="sort" id="sort-descending" value="descending" onclick="this.form.submit()" <c:if test="${param.sort == 'descending'}">checked</c:if>>
                                    <label class="custom-control-label" for="sort-descending">Descending</label>
                                </div>
                            </div>
                            <h5 class="section-title position-relative text-uppercase mb-3"><span class="bg-secondary pr-3">Categories</span></h5>
                            <div class="bg-light p-4 mb-30">
                                <div class="custom-control custom-radio d-flex align-items-center justify-content-between mb-3">
                                    <input type="radio" class="custom-control-input" id="cate-all" name="categoryId" value="-1" onclick="this.form.submit()" <c:if test="${param.categoryId == -1 || param.categoryId == null}">checked</c:if>>
                                    <label class="custom-control-label" for="cate-all">All</label>
                                </div>
                            <c:forEach items="${categories}" var="cate">
                                <div class="custom-control custom-radio d-flex align-items-center justify-content-between mb-3">
                                    <input type="radio" class="custom-control-input" id="category-${cate.id}" name="categoryId" value="${cate.id}" onclick="this.form.submit()" <c:if test="${param.categoryId == cate.id}">checked</c:if>>
                                    <label class="custom-control-label" for="category-${cate.id}">${cate.name}</label>
                                </div>
                            </c:forEach>
                        </div>
                        <!-- Categories End -->

                        <!-- Price Start -->
                        <h5 class="section-title position-relative text-uppercase mb-3"><span class="bg-secondary pr-3">Price</span></h5>
                        <div class="bg-light p-4 mb-30">
                            <div class="custom-control custom-radio d-flex align-items-center justify-content-between mb-3">
                                <input type="radio" class="custom-control-input" name="price" id="priceAll" value="" onclick="this.form.submit()" <c:if test="${(param.price == null || param.price == '')}">checked</c:if>>
                                    <label class="custom-control-label" for="priceAll">All</label>
                                </div>
                                <div class="custom-control custom-radio d-flex align-items-center justify-content-between mb-3">
                                    <input type="radio" class="custom-control-input" name="price" id="price1" value="0-100" onclick="this.form.submit()" <c:if test="${param.price == '0-100'}">checked</c:if>>
                                    <label class="custom-control-label" for="price1">Below 100đ</label>
                                </div>
                                <div class="custom-control custom-radio d-flex align-items-center justify-content-between mb-3">
                                    <input type="radio" class="custom-control-input" name="price" id="price2" value="100-200" onclick="this.form.submit()" <c:if test="${param.price == '100-200'}">checked</c:if>>
                                    <label class="custom-control-label" for="price2">100đ - 200đ</label>
                                </div>
                                <div class="custom-control custom-radio d-flex align-items-center justify-content-between mb-3">
                                    <input type="radio" class="custom-control-input" name="price" id="price3" value="200-500" onclick="this.form.submit()" <c:if test="${param.price == '200-500'}">checked</c:if>>
                                    <label class="custom-control-label" for="price3">200đ - 500đ</label>
                                </div>
                                <div class="custom-control custom-radio d-flex align-items-center justify-content-between mb-3">
                                    <input type="radio" class="custom-control-input" name="price" id="price4" value="500-1000000" onclick="this.form.submit()" <c:if test="${param.price == '500-1000000'}">checked</c:if>>
                                    <label class="custom-control-label" for="price4">Above 500đ</label>
                                </div>
                            </div>
                            <!-- Price End -->

                            <!-- Brand Start -->
                            <h5 class="section-title position-relative text-uppercase mb-3"><span class="bg-secondary pr-3">Brands</span></h5>
                            <div class="bg-light p-4 mb-30">
                                <div class="custom-control custom-radio d-flex align-items-center justify-content-between mb-3">
                                    <input type="radio" class="custom-control-input" id="provider-all" name="providerId" value="-1" onclick="this.form.submit()" <c:if test="${(param.providerId == -1) || (param.providerId == null)}">checked</c:if>>
                                    <label class="custom-control-label" for="provider-all">All</label>
                                </div>
                            <c:forEach items="${providers}" var="provider">
                                <div class="custom-control custom-radio d-flex align-items-center justify-content-between mb-3">
                                    <input type="radio" class="custom-control-input" id="provider-${provider.id}" name="providerId" value="${provider.id}" onclick="this.form.submit()" <c:if test="${param.providerId == provider.id}">checked</c:if>>
                                    <label class="custom-control-label" for="provider-${provider.id}">${provider.companyName}</label>
                                </div>
                            </c:forEach>
                            <input type="hidden" name="searchName" value="${searchName}">
                        </div>
                    </form>
                    <!-- Brand End -->
                </div>
                <!-- Shop Sidebar End -->

                <!-- Shop Product Start -->
                <div class="col-lg-9 col-md-8">
                    <div class="row pb-3">
                        <c:if test="${products.size() == 0}">
                            <div class="col-12 pb-1">
                                <p style="
                                   text-align: center;
                                   font-weight: 500;
                                   font-size: 24px
                                   ">No matching products found</p>
                            </div>
                        </c:if>
                        <c:forEach items="${products}" var="pro">
                            <div class="col-lg-3 col-md-4 col-sm-6 pb-1">
                                <div class="product-item bg-light mb-4">
                                    <div class="product-img position-relative overflow-hidden">
                                        <img class="img-fluid w-100" style="height: 174.08px; width: 174.08px;" src="${pro.image}" alt="">
                                        <div class="product-action">
                                            <a title="Add to cart" class="btn btn-outline-dark btn-square" href="${pageContext.request.contextPath}/add-to-cart-shop-page?proId=${pro.id}&sort=${sort}&categoryId=${categoryId}&price=${price}&providerId=${providerId}&searchName=${searchName}"><i class="fa fa-shopping-cart"></i></a>
                                            <a title="See details" class="btn btn-outline-dark btn-square" href="${pageContext.request.contextPath}/details?proId=${pro.id}&from=shop&sort=${sort}&categoryId=${categoryId}&price=${price}&providerId=${providerId}&searchName=${searchName}"><i class="fa fa-search"></i></a>
                                        </div>
                                    </div>
                                    <div class="text-center py-4">
                                        <a title="${pro.name}" class="h6 text-decoration-none text-truncate" href="${pageContext.request.contextPath}/details?proId=${pro.id}">${pro.name}</a>
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
                <!-- Shop Product End -->
            </div>
        </div>
        <!-- Shop End -->

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
