<%-- 
    Document   : productDetailsPage
    Created on : Jul 7, 2023, 3:11:25 PM
    Author     : Huy Nguyen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="dao.UserDAO" %>
<%@ page import="model.Product" %>
<%@ page import="model.User" %>
<%@ page import="model.Feedback" %>
<%@ page import="java.util.Vector" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>Elj Shop - Online Art Supplies Shop</title>
        <link rel="icon" href="${pageContext.request.contextPath}/img/logo.ico" type="image/icon type">
    </head>

    <body>
        <%@include file="header.jsp" %>

        <!-- Shop Detail Start -->
        <div class="container-fluid pb-5">
            <div class="row px-xl-5">
                <div class="col-lg-5 mb-30">
                    <div class="bg-light">
                        <div class="product-img position-relative overflow-hidden">
                            <img class="w-100 h-100" src="${product.image}" alt="Image">
                        </div>
                    </div>
                </div>

                <div class="col-lg-7 h-auto mb-30">
                    <div class="h-100 bg-light p-30">
                        <h3>${product.name}</h3>
                        <div class="d-flex" style="
                             align-items: center;
                             ">
                            <c:set var="currentPrice" value="${product.price - product.discount}"/>
                            <h3 class="font-weight-semi-bold mb-4"><fmt:formatNumber type="currency" pattern="###,###¤">${currentPrice}</fmt:formatNumber></h3>
                            <c:if test="${currentPrice < product.price}">
                                <h4 class="font-weight-semi-bold text-muted ml-2 mb-4">
                                    <del><fmt:formatNumber type="currency" pattern="###,###¤">${product.price}</fmt:formatNumber></del>
                                    </h4>
                            </c:if>
                        </div>
                        <p class="mb-4">${product.description}</p>
                        <div class="d-flex mb-3">
                            <strong class="text-dark mr-1">Category: </strong>${categoryName}
                        </div>
                        <div class="d-flex mb-4">
                            <strong class="text-dark mr-1">Brand: </strong>${brandName}
                        </div>
                        <div class="d-flex mb-4">
                            <strong class="text-dark mr-1">In Stock: </strong>${product.quantity}
                        </div>
                        <script>
                            document.addEventListener("DOMContentLoaded", function () {
                                var form = document.getElementById("addToCartForm");
                                var fromParam = "${from}";

                                if (fromParam === "home") {
                                    form.action = "add-to-cart";
                                } else if (fromParam === "shop") {
                                    form.action = "add-to-cart-shop-page";
                                } else if (fromParam == "cart") {
                                    form.action = "add-to-cart-cart-page";
                                }
                            });
                        </script>
                        <form id="addToCartForm" class="d-flex align-items-center mb-4 pt-2">
                            <input type="hidden" name="proId" value="${product.id}">
                            <input name="sort" type="hidden" id="sort" value="${sort}">
                            <input name="searchName" type="hidden" id="searchName" value="${searchName}">
                            <input name="products" type="hidden" id="products" value="${products}">
                            <input name="categoryId" type="hidden" id="categoryId" value="${categoryId}">
                            <input name="providerId" type="hidden" id="providerId" value="${providerId}">
                            <input name="price" type="hidden" id="price" value="${price}">
                            <input name="categories" type="hidden" id="categories" value="${categories}">
                            <input name="providers" type="hidden" id="providers" value="${providers}">
                            <div class="input-group quantity mr-3" style="width: 130px;">
                                <div class="input-group-btn">
                                    <button type="button" class="btn btn-primary btn-minus">
                                        <i class="fa fa-minus"></i>
                                    </button>
                                </div>
                                <input name="quantityToBuy" id="quantityToBuy" class="form-control bg-secondary border-0 text-center" value="1" required>
                                <div class="input-group-btn">
                                    <button type="button" class="btn btn-primary btn-plus">
                                        <i class="fa fa-plus"></i>
                                    </button>
                                </div>
                            </div>
                            <button type="submit" class="btn btn-primary px-3"><i class="fa fa-shopping-cart mr-1"></i>Add To Cart</button>
                        </form>
                    </div>
                </div>
            </div>
            <div class="row px-xl-5">
                <div class="col">
                    <div class="bg-light p-30">
                        <% Vector<Feedback> feedbacks = (Vector<Feedback>) request.getAttribute("feedbacks"); %>
                        <div class="nav nav-tabs mb-4">
                            <a class="nav-item nav-link text-dark active" data-toggle="tab" href="#tab-pane-1">Description</a>
                            <a class="nav-item nav-link text-dark" data-toggle="tab" href="#tab-pane-2">Reviews (<%=feedbacks.size()%>)</a>
                        </div>
                        <div class="tab-content">
                            <div class="tab-pane fade show active" id="tab-pane-1">
                                <h4 class="mb-3">Product Description</h4>
                                <p>${product.description}</p>
                            </div>
                            <div class="tab-pane fade" id="tab-pane-2">
                                <div class="row">
                                    <div class="col-md-6">
                                        <h4 class="mb-4"> Review for ${product.name}</h4>
                                        <% UserDAO udao = new UserDAO(); %>
                                        <% for(int i =0 ;i< feedbacks.size() ;i++ ) {%>
                                        <% User user = udao.getById(feedbacks.get(i).getCustomerId()); %>
                                        <div class="media mb-4">
                                            <img src="https://tse1.mm.bing.net/th?id=OIP.4j4jNaPU3bIzDJHBj6HDSwHaHa&pid=Api&rs=1&c=1&qlt=95&w=114&h=114" alt="Image" class="img-fluid mr-3 mt-1" style="width: 45px;">
                                            <div class="media-body">
                                                <%String userName = user.getLastName() +" "+ user.getFirstName(); %>
                                                <h6><%= userName%><small> - <i><%= feedbacks.get(i).getFeedbackDate() %></i></small></h6>
                                                <p><%= feedbacks.get(i).getContent()%></p>
                                            </div>
                                        </div>
                                        <%}%>
                                    </div>
                                    <div class="col-md-6">
                                        <h4 class="mb-4">Leave a review</h4>
                                        <small>(Your email address will not be published)</small>
                                        <form action="add-feedback">
                                            <div class="form-group">
                                                <label for="message"></label>
                                                <textarea name="content" id="message" cols="30" rows="5" class="form-control" required></textarea>
                                            </div>  
                                            <input type="hidden" name="from" value="${from}">
                                            <input type="hidden" name="proId" value="${product.id}">
                                            <input name="sort" type="hidden" id="sort" value="${sort}">
                                            <input name="searchName" type="hidden" id="searchName" value="${searchName}">
                                            <input name="products" type="hidden" id="products" value="${products}">
                                            <input name="categoryId" type="hidden" id="categoryId" value="${categoryId}">
                                            <input name="providerId" type="hidden" id="providerId" value="${providerId}">
                                            <input name="price" type="hidden" id="price" value="${price}">
                                            <input name="categories" type="hidden" id="categories" value="${categories}">
                                            <input name="providers" type="hidden" id="providers" value="${providers}">
                                            <div class="form-group mb-0">
                                                <input type="submit" value="Leave Review" class="btn btn-primary px-3">
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Shop Detail End -->

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
