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

        <!-- Cart Start -->
        <div class="container-fluid">
            <c:choose>
                <c:when test="${orders.size() == 0}">
                    <div class="col-12 pb-1">
                        <p style="
                           text-align: center;
                           font-weight: 500;
                           font-size: 24px
                           ">Your History is empty!</p>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="row px-xl-5">    
                        <div class="col-lg-12 table-responsive mb-5">
                            <table class="table table-light table-borderless table-hover text-left mb-0">
                                <thead class="thead-dark text-center">
                                    <tr>
                                        <th>Receiver</th>
                                        <th>Phone</th>
                                        <th>Address</th>
                                        <th>Total Price</th>
                                        <th>Create Date</th>
                                        <th>Status</th>
                                        <th>Action</th>
                                    </tr>
                                </thead>
                                <tbody class="align-middle">
                                    <c:forEach items="${orders}" var="order">
                                        <tr>
                                            <td class="text-left">
                                                <a >${order.receiver}</a>
                                            </td>
                                            <td class="text-center">
                                                <a >${order.shipPhone}</a>
                                            </td>
                                            <td class="text-center">
                                                <a >${order.shipStreet}, ${order.shipCity}, ${order.shipProvince}, ${order.shipCountry}</a>
                                            </td>
                                            <fmt:setLocale value="en_US"/>
                                            <td class="text-center">
                                                <fmt:formatNumber type="currency" pattern="###,###¤">${order.totalPrice}</fmt:formatNumber>
                                                </td>
                                                <td class="text-center"><a >${order.createdTime}</a></td>
                                            <td class="text-center">
                                                <a >${order.status}</a>
                                            </td>
                                            <td class="text-center">
                                                <a href="${pageContext.request.contextPath}/customer-view-history-details?orderId=${order.id}" title="View Order Detail" ><i style="color: green; font-size: 22px" class="fa fa-eye"></i></a>
                                                    <c:if test="${order.status == 'Processing'}">
                                                    <a href="${pageContext.request.contextPath}/customer-delete-history?orderId=${order.id}" title="Delete Order"><i style="color: red; font-size: 22px; margin-left: 10px" class="fa fa-times"></i></a>
                                                    </c:if>
                                                    <c:if test="${order.status == 'Shipping'}">
                                                    <a href="${pageContext.request.contextPath}/customer-changeToFeedback?orderId=${order.id}" title="Confirm received"><i style="color: green; font-size: 22px; margin-left: 10px" class="fa fa-check"></i></a>
                                                    </c:if>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>

        <!-- Cart End -->

        <%@include file="footer.jsp" %>

        <!-- JavaScript Libraries -->
        <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>
        <script src="${pageContext.request.contextPath}/lib/easing/easing.min.js"></script>
        <script src="${pageContext.request.contextPath}/lib/owlcarousel/owl.carousel.min.js"></script>

        <!-- Template Javascript -->
    </body>

</html>