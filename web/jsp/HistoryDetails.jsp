<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="dao.ProductDAO" %>
<%@ page import="model.Product" %>
<%@ page import="model.OrderDetail" %>
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
                <c:when test="${details.size() == 0}">
                    <div class="col-12 pb-1">
                        <p style="
                           text-align: center;
                           font-weight: 500;
                           font-size: 24px
                           ">Your History is empty!</p>
                    </div>
                </c:when>
                <c:otherwise>
                    <% ProductDAO pdao = new ProductDAO(); %>
                    <% Vector<OrderDetail> details = (Vector<OrderDetail>) request.getAttribute("details"); %>
                    <div class="row px-xl-5">    
                        <div class="col-lg-12 table-responsive mb-5">
                            <table class="table table-light table-borderless table-hover mb-0">
                                <thead class="thead-dark text-center">
                                    <tr>
                                        <th>Product</th>
                                        <th>Unit Price</th>
                                        <th>Quantity</th>
                                        <th>Subtotal Price</th>
                                    </tr>
                                </thead>
                                <tbody class="align-middle">
                                    <% for(int i = 0; i < details.size(); i++) {%>
                                    <% Product product = pdao.getProductById(details.get(i).getProductID()); %>
                                    <tr>
                                        <td class="text-left">
                                            <a ><%= product.getName() %></a>
                                        </td>
                                        <td class="text-center">
                                            <fmt:formatNumber type="currency" pattern="###,###¤"><%= details.get(i).getPrice() %></fmt:formatNumber>
                                            </td>
                                            <td class="text-center">
                                                <a ><%= details.get(i).getQuantity() %></a>
                                        </td>
                                        <td class="text-center">
                                            <fmt:formatNumber type="currency" pattern="###,###¤"><%= details.get(i).getPrice()*details.get(i).getQuantity() %></fmt:formatNumber>
                                            </td>
                                        </tr>
                                    <%}%>
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