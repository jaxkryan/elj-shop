<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="dao.ProductDAO" %>
<%@ page import="dao.FeedbackDAO" %>
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
                    <c:if test="${status != 'Processed' && status != 'Received'}">
                        <div class="row px-xl-5">    
                            <div class="col-lg-12 table-responsive mb-5">
                                <table class="table table-light table-borderless table-hover mb-0">
                                    <thead class="thead-dark text-center">
                                        <tr>
                                            <th>Product</th>
                                            <th>Unit Price</th>
                                            <th>Quantity</th>
                                            <th>Subtotal Price</th>
                                            <th>Shipping</th>
                                        </tr>
                                    </thead>
                                    <tbody class="align-middle">
                                        <% for(int i = 0; i < details.size(); i++) {%>
                                        <% Product product = pdao.getProductById(details.get(i).getProductID()); %>
                                        <tr>
                                            <td class="text-left">
                                                <a ><%= product.getName() %></a>
                                            </td>
                                            <fmt:setLocale value="en_US"/>
                                            <td class="text-center">
                                                <fmt:formatNumber type="currency" pattern="###,###¤"><%= details.get(i).getPrice() %></fmt:formatNumber>
                                                </td>
                                                <td class="text-center">
                                                    <a ><%= details.get(i).getQuantity() %></a>
                                            </td>
                                            <td class="text-center">
                                                <fmt:formatNumber type="currency" pattern="###,###¤"><%= details.get(i).getPrice()*details.get(i).getQuantity() %></fmt:formatNumber>
                                                </td>
                                                <td class="text-center">
                                                <fmt:formatNumber type="currency" pattern="###,###¤"><%= (details.get(i).getPrice()*details.get(i).getQuantity())*0.1 %></fmt:formatNumber>
                                                </td>
                                            </tr>
                                        <%}%>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </c:if>
                    <c:if test="${status == 'Processed'}">
                        <div class="row px-xl-5">    
                            <div class="col-lg-12 table-responsive mb-5">
                                <table class="table table-light table-borderless table-hover mb-0">
                                    <thead class="thead-dark text-center">
                                        <tr>
                                            <th>Product</th>
                                            <th>Unit Price</th>
                                            <th>Quantity</th>
                                            <th>Subtotal Price</th>
                                            <th>Shipping</th>
                                            <th>Action</th>
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
                                                <td class="text-center">
                                                <fmt:formatNumber type="currency" pattern="###,###¤"><%= (details.get(i).getPrice()*details.get(i).getQuantity())*0.1 %></fmt:formatNumber>
                                                </td>
                                                <td class="text-center">
                                                    <a href="${pageContext.request.contextPath}/customer-delete-history-detail?status=${status}&orderId=<%= details.get(i).getOrderID() %>&proId=<%= details.get(i).getProductID()%>" title="Delete Order Detail"><i style="color: red; font-size: 22px;" class="fa fa-times"></i></a>
                                            </td>
                                        </tr>
                                        <%}%>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </c:if>
                    <c:if test="${status == 'Received'}">
                        <% ProductDAO dao = new ProductDAO(); %>
                        <div class="row px-xl-5">    
                            <div class="col-lg-12 table-responsive mb-5">
                                <table class="table table-light table-borderless table-hover mb-0">
                                    <thead class="thead-dark text-center">
                                        <tr>
                                            <th>ID</th>
                                            <th>Unit Price</th>
                                            <th>Quantity</th>
                                            <th>Subtotal Price</th>
                                            <th>Shipping</th>
                                            <th>Action</th>
                                        </tr>
                                    </thead>
                                    <tbody class="align-middle">
                                        <% for(int i = 0; i < details.size(); i++) {%>
                                        <% Product product = pdao.getProductById(details.get(i).getProductID()); %>
                                        <% int check = (int)request.getAttribute("check");%>
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
                                                <td class="text-center">
                                                <fmt:formatNumber type="currency" pattern="###,###¤"><%= (details.get(i).getPrice()*details.get(i).getQuantity())*0.1 %></fmt:formatNumber>
                                                </td>
                                            <%if(check == 0){%>
                                            <td class="text-center">
                                                <a href="#addReportModal<%=details.get(i).getProductID()%>" class="add" data-toggle="modal" title="Add Feedback">
                                                    <i style="color: yellow; font-size: 22px;" title="Write feedback" class="fa fa-pencil-alt" data-toggle="tooltip"></i>
                                                </a>
                                            </td>
                                            <%}else{%>
                                            <td class="text-center">
                                                <a href="#viewReportModal<%=details.get(i).getProductID()%>" class="view" data-toggle="modal" title="View Feedback">
                                                    <i style="color: yellow; font-size: 22px;" title="View feedback" class="fa fa-eye" data-toggle="tooltip"></i>
                                                </a>
                                            </td>
                                            <%}%>
                                        </tr>
                                        <%}%>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </c:if>
                    <%for (int i = 0; i < details.size(); i++) {%>
                    <%FeedbackDAO feedbackDAO = new FeedbackDAO();%>
                    <%String content = feedbackDAO.getOrderFeedBack(details.get(i).getProductID(), details.get(i).getOrderID());%>
                    <div id="viewReportModal<%=details.get(i).getProductID()%>" class="modal fade">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <form>
                                    <div class="modal-header">						
                                        <h4 class="modal-title">View Feedback</h4>
                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                    </div>
                                    <div class="modal-body">					
                                        <div class="form-group">
                                            <label>Content</label>
                                            <input type="content" class="form-control" name="response-content" value ="<%=content%>" readonly>
                                        </div>	                                         
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                    <%}%>

                </c:otherwise>
            </c:choose>
        </div>

        <!-- Add Modal HTML -->
        <c:forEach items="${details}" var="d">
            <div id="addReportModal${d.productID}" class="modal fade">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <form action="${pageContext.request.contextPath}/customer-feedbackOrder?proId=${d.productID}" method="post">
                            <div class="modal-header" hidden>						
                                <h4 class="modal-title">ID</h4>
                                <input type="text" name="proId" value="${d.productID}" readonly/>
                            </div>
                            <div class="modal-header" hidden>						
                                <h4 class="modal-title">Status</h4>
                                <input type="text" name="status" value="Received" readonly/>
                            </div>
                            <div class="modal-header" hidden>						
                                <h4 class="modal-title">Order ID</h4>
                                <input type="text" name="orderId" value="${d.orderID}" readonly/>
                            </div>
                            <div class="modal-header">						
                                <h4 class="modal-title">Write feedback</h4>
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            </div>
                            <div class="modal-body">					
                                <div class="form-group">
                                    <label>Content</label>
                                    <textarea name="content" type="text" class="form-control" required rows="5" cols="33" maxlength="10000"></textarea>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                                <input type="submit" class="btn btn-success" value="Send">
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </c:forEach>




        <!-- Cart End -->

        <%@include file="footer.jsp" %>

        <!-- JavaScript Libraries -->
        <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>
        <script src="${pageContext.request.contextPath}/lib/easing/easing.min.js"></script>
        <script src="${pageContext.request.contextPath}/lib/owlcarousel/owl.carousel.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/manager.js" type="text/javascript"></script>
        <!-- Template Javascript -->
    </body>

</html>
