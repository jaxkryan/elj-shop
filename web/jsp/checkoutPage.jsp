<%-- 
    Document   : checkoutPage
    Created on : Jul 9, 2023, 6:45:32 AM
    Author     : Huy Nguyen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="dao.ProductDAO" %>
<%@ page import="model.Product" %>
<%@ page import="model.CartItem" %>
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

        <!-- Checkout Start -->
        <form action="create-order" method="POST">
            <div class="container-fluid">
                <div class="row px-xl-5">
                    <div class="col-lg-8">
                        <h5 class="section-title position-relative text-uppercase mb-3"><span class="bg-secondary pr-3">Billing Address</span></h5>
                        <div class="bg-light p-30 mb-5">
                            <div class="row">
                                <div class="col-md-6 form-group">
                                    <label>First Name</label>
                                    <input name="firstName" class="form-control" type="text" placeholder="First Name" <c:if test="${user.id != null}">value="${user.firstName}"</c:if> required>
                                    </div>
                                    <div class="col-md-6 form-group">
                                        <label>Last Name</label>
                                        <input name="lastName" class="form-control" type="text" placeholder="Last Name" <c:if test="${user.id != null}">value="${user.lastName}"</c:if> required>
                                    </div>
                                    <div class="col-md-6 form-group">
                                        <label>Street</label>
                                        <input name="street" class="form-control" type="text" placeholder="Street" <c:if test="${user.id != null}">value="${user.street}"</c:if> required>
                                    </div>
                                    <div class="col-md-6 form-group">
                                        <label>City</label>
                                        <input name="city" class="form-control" type="text" placeholder="City" <c:if test="${user.id != null}">value="${user.city}"</c:if> required>
                                    </div>
                                    <div class="col-md-6 form-group">
                                        <label>Province</label>
                                        <input name="province" class="form-control" type="text" placeholder="Province" <c:if test="${user.id != null}">value="${user.province}"</c:if> required>
                                    </div>
                                    <div class="col-md-6 form-group">
                                        <label>Country</label>
                                        <input name="country" class="form-control" type="text" placeholder="Country" <c:if test="${user.id != null}">value="${user.country}"</c:if> required>
                                    </div>
                                    <div class="col-md-6 form-group">
                                        <label>E-mail</label>
                                        <input name="email" class="form-control" type="email" placeholder="example@email.com" <c:if test="${user.id != null}">value="${user.email}"</c:if> required>
                                    </div>
                                    <div class="col-md-6 form-group">
                                        <label>Mobile No</label>
                                        <input name="phone" class="form-control" type="tel" placeholder="123 456 7890" <c:if test="${user.id != null}">value="${user.phone}"</c:if> required>
                                    </div>
                                    <div class="col-md-6 form-group">
                                        <label>Voucher code</label>
                                        <input name="voucherCode" class="form-control" type="text" <c:if test="${user.id != null}">value=""</c:if>>
                                    </div>
                                    <input type="hidden" name="subtotal" class="form-control" value="${subtotal}">
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4">
                        <h5 class="section-title position-relative text-uppercase mb-3"><span class="bg-secondary pr-3">Order Total</span></h5>
                        <div class="bg-light p-30 mb-5">
                            <div class="border-bottom">
                                <h6 class="mb-3">Products</h6>
                                <% ProductDAO pdao = new ProductDAO(); %>
                                <% Vector<CartItem> cartItem = (Vector<CartItem>) session.getAttribute("cartItem"); %>
                                <% for(int i = 0; i < cartItem.size(); i++) { %>
                                <% Product product = pdao.getProductById(cartItem.get(i).getProductId()); %>
                                <div class="d-flex justify-content-between">
                                    <p><%= product.getName() %></p>
                                    <fmt:setLocale value="en_US"/>
                                    <p><fmt:formatNumber type="currency" pattern="###,###¤"><%= product.getPrice() * cartItem.get(i).getQuantity() %></fmt:formatNumber></p>
                                    </div>
                                <% } %>
                            </div>
                            <div class="border-bottom pt-3 pb-2">
                                <div class="d-flex justify-content-between mb-3">
                                    <h6>Subtotal</h6>
                                    <h6>
                                        <fmt:formatNumber type="currency" pattern="###,###¤">
                                            <c:choose>
                                                <c:when test="${subtotal == null || subtotal == 0}">0</c:when>
                                                <c:otherwise>${subtotal}</c:otherwise>
                                            </c:choose>
                                        </fmt:formatNumber>
                                    </h6>
                                </div>
                                <div class="d-flex justify-content-between">
                                    <h6 class="font-weight-medium">Shipping</h6>
                                    <h6 class="font-weight-medium">
                                        <fmt:formatNumber type="currency" pattern="###,###¤">
                                            <c:choose>
                                                <c:when test="${subtotal == null || subtotal == 0}">0</c:when>
                                                <c:otherwise>${subtotal*0.1}</c:otherwise>
                                            </c:choose>
                                        </fmt:formatNumber>
                                    </h6>
                                </div>
                            </div>
                            <div class="pt-2">
                                <div class="d-flex justify-content-between mt-2">
                                    <h5>Total</h5>
                                    <h5>
                                        <fmt:formatNumber type="currency" pattern="###,###¤">
                                            ${subtotal*1.1}
                                        </fmt:formatNumber>
                                    </h5>
                                </div>
                            </div>
                        </div>
                        <div class="mb-5">
                            <h5 class="section-title position-relative text-uppercase mb-3"><span class="bg-secondary pr-3">Payment</span></h5>
                            <div class="bg-light p-30">
                                <div class="form-group">
                                    <div class="custom-control custom-radio" data-toggle="collapse" data-target="#creditCard-info.show">
                                        <input type="radio" class="custom-control-input" name="payment" id="cod" checked>
                                        <label class="custom-control-label" for="cod">Cash On Delivery</label>
                                    </div>
                                </div>
                                <input type="submit" class="btn btn-block btn-primary font-weight-bold py-3" value="Place Order">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </form>
        <!-- Checkout End -->

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
