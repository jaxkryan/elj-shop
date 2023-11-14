<%-- 
    Document   : manageOrderDetailPage
    Created on : Oct 26, 2023, 1:35:26 PM
    Author     : LENOVO
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="dao.ProductDAO" %>
<%@ page import="dao.FeedbackDAO" %>
<%@ page import="model.Product" %>
<%@ page import="model.OrderDetail" %>
<%@ page import="model.Order" %>
<%@ page import="java.util.Vector" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Elj Shop - Online Art Supplies Shop</title>
        <!<!-- Favicon -->
        <link rel="icon" href="${pageContext.request.contextPath}/img/logo.ico" type="image/icon type">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <link href="${pageContext.request.contextPath}/css/manager.css?version=1" rel="stylesheet"/>
        <style>
            img{
                width: 200px;
                height: 120px;
            }
        </style>
    <body>
        <div class="container">

            <div class="row p-3 text-right">
                <a href="${pageContext.request.contextPath}/storage-staff/update-order-status?go=filter&sortType=${param.sortType}&statusFilter=${param.statusFilter}&keyword=${param.keyword}" style="margin-right:81%" class="btn btn-primary">Back</a>
                <a href="profile" class="btn btn-primary">Profile</a>
                <a href="${pageContext.request.contextPath}/logout" class="btn btn-primary">Log Out</a>
            </div>
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
            <% ProductDAO pdao = new ProductDAO(); %>
            <% Vector<OrderDetail> details = (Vector<OrderDetail>) request.getAttribute("details"); %>
            <% Order od = (Order) request.getAttribute("order");%>
            <style>
                .vertical-line {
                    border-right: 1px solid #ccc;
                }
            </style>
            <% double oldTotal=0; %>
            <% for(int i = 0; i < details.size(); i++) {%>
            <% oldTotal += details.get(i).getPrice()*details.get(i).getQuantity(); %>
            <% } %>
            <div class="table-wrapper">
                <div class="table-title">
                    <div class="row">
                        <div class="col-sm-3">
                            <h2><b>Order Detail</b></h2>
                        </div>
                    </div>
                </div>              
                <div class="details">
                    <div class="row px-xl-5">
                        <div class="col-md-6 ">
                            <h4>Customer's Order Information</h4>
                            <label>Name</label>
                            <input class="form-control" type="text" placeholder="Receiver Name" value="${order.receiver}" readonly>
                            <label>Phone</label> 
                            <input class="form-control" type="text" placeholder="Phone" value="${order.shipPhone}" readonly>
                            <label>Address</label>
                            <input class="form-control" type="text" placeholder="Address" value="${order.shipStreet}, ${order.shipCity}, ${order.shipProvince}, ${order.shipCountry}" readonly>
                            <label>E-mail</label>
                            <input class="form-control" type="email" placeholder="example@email.com" value="${order.shipEmail}" readonly>
                            <div style="padding-top: 2%">
                                <c:choose>
                                    <c:when test="${order.status == 'Packing'}">
                                        <a href="${pageContext.request.contextPath}/storage-staff/update-order-status?go=changeOrderStatus&newStatus=Shipping&id=${order.id}&cusId=${order.customerId}" class="btn btn-primary">Shipping</a>
                                        <a href="${pageContext.request.contextPath}/storage-staff/update-order-status?go=changeOrderStatus&newStatus=Cancelled&id=${order.id}&cusId=${order.customerId}" class="btn btn-primary">Cancel</a>
                                    </c:when>
                                    <c:otherwise>
                                        <c:if test="${order.status != 'Cancelled'}">
                                            <a href="${pageContext.request.contextPath}/storage-staff/update-order-status?go=changeOrderStatus&newStatus=Packing&id=${order.id}&cusId=${order.customerId}" class="btn btn-primary">Packing</a>
                                            <a href="${pageContext.request.contextPath}/storage-staff/update-order-status?go=changeOrderStatus&newStatus=Cancelled&id=${order.id}&cusId=${order.customerId}" class="btn btn-primary">Cancel</a>
                                        </c:if>
                                    </c:otherwise>
                                </c:choose>

                            </div>
                        </div>



                        <div class="col-md-6 form-group">
                            <h4>Order Information</h4>
                            <label>Created Date</label>
                            <input class="form-control" type="text" placeholder="Created Time" value="${order.createdTime}" readonly>
                            <label>Status</label>
                            <input class="form-control" type="text" placeholder="Status" value="${order.status}" readonly>
                            <label>Subtotal</label>
                            <input class="form-control" type="text" placeholder="Subtotal" value="<fmt:formatNumber type="currency" pattern="###,###¤"><%= oldTotal %></fmt:formatNumber>" readonly>
                                <label>Shipping</label>
                                <input class="form-control" type="text" placeholder="Shipping" value="<fmt:formatNumber type="currency" pattern="###,###¤"><%= oldTotal*0.1 %></fmt:formatNumber>" readonly>
                                <label>Discount</label>
                            <% if((oldTotal*1.1 - od.getTotalPrice())==0) {%>
                            <input class="form-control" type="text" placeholder="Discount" value="<fmt:formatNumber type="currency" pattern="###,###¤">0</fmt:formatNumber>" readonly>
                            <% } else { %>
                            <input class="form-control" type="text" placeholder="Discount" value="<fmt:formatNumber type="currency" pattern="###,###¤"><%=(oldTotal*1.1 - od.getTotalPrice()) %></fmt:formatNumber>" readonly>
                            <% } %>
                            <label>Total</label>
                            <input class="form-control" type="text" placeholder="Total" value="<fmt:formatNumber type="currency" pattern="###,###¤">${order.totalPrice}</fmt:formatNumber>" readonly>
                            </div>
                        </div>
                    </div>





                    <table class="table table-striped table-hover">
                        <thead>
                            <tr>
                                <th>ProductID</th>
                                <th>Product Name</th>
                                <th>OrderId</th>
                                <th>Price</th>
                                <th>Quantity</th
                            </tr>
                        </thead>

                        <tbody>

                        <c:forEach items="${details}" var="orderdetail">         
                        <td class="align-middle">${orderdetail.productID}</td>
                        <c:forEach items="${product}" var="product">
                            <c:if test="${Integer.parseInt(orderdetail.productID) == Integer.parseInt(product.id)}">
                                <td class="align-middle">${product.name}</td>
                            </c:if>
                        </c:forEach>
                        <td class="align-middle">${orderdetail.orderID}</td>
                        <td class="align-middle">${orderdetail.price}</td>
                        <td class="align-middle">${orderdetail.quantity}</td>  
                        </tr>
                    </c:forEach>

                    </tbody>
                </table>
            </div>
        </div>
        <script src="${pageContext.request.contextPath}/js/manager.js" type="text/javascript"></script>
    </body>
</html>