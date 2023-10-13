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
            <form action="${pageContext.request.contextPath}/update-cartitem" method="GET">
                <c:choose>
                    <c:when test="${cartItem.size() == 0}">
                        <div class="col-12 pb-1">
                            <p style="
                               text-align: center;
                               font-weight: 500;
                               font-size: 24px
                               ">Your cart is empty!</p>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <% ProductDAO pdao = new ProductDAO(); %>
                        <% Vector<CartItem> cartItem = (Vector<CartItem>) session.getAttribute("cartItem"); %>
                        <div class="row px-xl-5">                    
                            <div class="col-lg-8 table-responsive mb-5">
                                <table class="table table-light table-borderless table-hover text-center mb-0">
                                    <thead class="thead-dark">
                                        <tr>
                                            <th>Products</th>
                                            <th>Price</th>
                                            <th>Quantity</th>
                                            <th>Total</th>
                                            <th>Remove</th>
                                        </tr>
                                    </thead>
                                    <script>
                                        function addQuantity(index) {
                                            var quantityInput = document.getElementById("quantity-" + index);
                                            var quantityValue = parseInt(quantityInput.value);
                                            quantityInput.value = quantityValue + 1;
                                        }

                                        function subtractQuantity(index) {
                                            var quantityInput = document.getElementById("quantity-" + index);
                                            var quantityValue = parseInt(quantityInput.value);
                                            if (quantityValue > 1) {
                                                quantityInput.value = quantityValue - 1;
                                            }
                                        }
                                    </script>
                                    <tbody class="align-middle">
                                        <% float subtotal = 0; %>
                                        <% for(int i=0; i < cartItem.size(); i++) {%>
                                        <% Product product = pdao.getProductById(cartItem.get(i).getProductId()); %>
                                        <tr>
                                            <td class="align-middle container-fluid" style="display: flex; align-items: center;">
                                                <img src="<%= product.getImage() %>" alt="" style="width: 50px;">
                                                <a href="${pageContext.request.contextPath}/details?proId=<%= cartItem.get(i).getProductId() %>&from=cart" title="<%= product.getName() %>" class="product-name-in-cart text-truncate ml-2"> <%= product.getName() %> </a>
                                            </td>
                                            <td class="align-middle">
                                                <fmt:formatNumber type="currency" pattern="###,###¤"><%= cartItem.get(i).getPrice() %></fmt:formatNumber>
                                                <input type="hidden" id="price-<%= i %>" value="<%= cartItem.get(i).getPrice() %>">
                                            </td>
                                            <td class="align-middle">
                                                <div class="input-group quantity mx-auto" style="width: 100px;">
                                                    <div class="input-group-btn">
                                                        <button type="button" class="btn btn-sm btn-primary btn-minus" onclick="subtractQuantity(<%= i %>)">
                                                            <i class="fa fa-minus"></i>
                                                        </button>
                                                    </div>
                                                    <input name="quantity-<%= i %>" id="quantity-<%= i %>" type="text" class="form-control form-control-sm bg-secondary border-0 text-center" value="<%= cartItem.get(i).getQuantity() %>" required>
                                                    <div class="input-group-btn">
                                                        <button type="button" class="btn btn-sm btn-primary btn-plus" onclick="addQuantity(<%= i %>)">
                                                            <i class="fa fa-plus"></i>
                                                        </button>
                                                    </div>
                                                </div>
                                            </td>
                                            <td class="align-middle">
                                                <fmt:formatNumber type="currency" pattern="###,###¤"><%= cartItem.get(i).getPrice() * cartItem.get(i).getQuantity() %></fmt:formatNumber>
                                                <input type="hidden" id="total-price-<%= i %>" value="<%= cartItem.get(i).getPrice() * cartItem.get(i).getQuantity() %>">
                                            </td>
                                            <td class="align-middle">
                                                <a href="${pageContext.request.contextPath}/delete-cartitem?productId=<%= cartItem.get(i).getProductId() %>&cartId=<%= cartItem.get(i).getCartId() %>" class="btn btn-sm btn-danger"><i class="fa fa-times"></i></a>
                                            </td>
                                        </tr>
                                        <% subtotal += cartItem.get(i).getPrice() * cartItem.get(i).getQuantity(); %>
                                        <% } %>
                                    </tbody>
                                </table>
                                <button type="submit" class="btn btn-block btn-primary font-weight-bold my-3 py-3" onclick="home">Save Quantity Change</button>
                            </div>
                            <div class="col-lg-4">
                                <h5 class="section-title position-relative text-uppercase mb-3"><span class="bg-secondary pr-3">Cart Summary</span></h5>
                                <div class="bg-light p-30 mb-5">
                                    <div class="border-bottom pb-2">
                                        <div class="d-flex justify-content-between mb-3">
                                            <h6>Subtotal</h6>
                                            <h6 id="subtotalText">
                                                <fmt:formatNumber type="currency" pattern="###,###¤">
                                                    <%= subtotal %>
                                                </fmt:formatNumber>
                                            </h6>
                                        </div>
                                        <div class="d-flex justify-content-between">
                                            <h6 class="font-weight-medium">Shipping</h6>
                                            <h6 class="font-weight-medium" id="shippingText">
                                                <fmt:formatNumber type="currency" pattern="###,###¤">
                                                    <%= subtotal*0.1 %>
                                                </fmt:formatNumber>
                                            </h6>
                                        </div>
                                    </div>
                                    <div class="pt-2">
                                        <div class="d-flex justify-content-between mt-2">
                                            <h5>Total</h5>
                                            <h5 id="totalText">
                                                <fmt:formatNumber type="currency" pattern="###,###¤">
                                                    <%= subtotal*1.1 %>
                                                </fmt:formatNumber>
                                            </h5>
                                        </div>
                                        <a href="${pageContext.request.contextPath}/checkout?subtotal=<%= subtotal %>" class="btn btn-block btn-primary font-weight-bold my-3 py-3">Proceed To Checkout</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:otherwise>
                </c:choose>
            </form>
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