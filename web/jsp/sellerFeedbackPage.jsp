<%-- 
    Document   : sellerFeedbackPage
    Created on : Oct 19, 2023, 4:35:06 PM
    Author     : LENOVO
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                <a href="profile" class="btn btn-primary">Profile</a>
                <a href="${pageContext.request.contextPath}/logout" class="btn btn-primary">Log Out</a>
            </div>

            <!-- Notification Start -->
            <c:if test="${notification != null}">
                <div class="row p-3">
                    <div class="col-lg-12">
                        <div class="alert <c:choose><c:when test="${notiType == 'RED'}">alert-danger</c:when><c:otherwise>alert-success</c:otherwise></c:choose>">
                                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                                        <strong><%= session.getAttribute("notification")%></strong>
                            <%session.removeAttribute("notification");%>
                            <%session.removeAttribute("notiType");%>
                        </div>
                    </div>
                </div>
            </c:if>
            <!-- Notification End -->

            <div class="table-wrapper">
                <div class="table-title">
                    <div class="row">
                        <div class="col-sm-3">
                            <a href="home"> <h2>Manage <b>Order</b></h2></a>
                        </div>
                        <div class="col-sm-3">
                            <h2>Manage <b>FeedBack</b></h2>
                        </div>
                    </div>
                </div>
                <table class="table table-striped table-hover">
                    <thead>
                        <tr>
                            <th>Customer Name</th>
                            <th>OrderID</th>
                            <th>Product Name</th>
                            <th>Content</th>
                            <th>FeedbackDate</th>
                            <th>Reply</th>
                            <th>Status</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${feedbacks}" var="feedback">
                            <tr>
                                <td>
                                    <c:forEach items="${customers}" var="customer">
                                        <c:if test="${Integer.parseInt(feedback.customerId) == Integer.parseInt(customer.id)}">
                                            <div>${customer.firstName} ${customer.lastName}</div>
                                            <input type ="hidden" name ="customerId" value="${customer.id}">
                                        </c:if>
                                    </c:forEach></td>
                                <td>${feedback.orderId}</td>
                                <td> <c:forEach items="${products}" var="product">
                                        <c:if test="${Integer.parseInt(feedback.productId) == Integer.parseInt(product.id)}">
                                            <div>${product.name}</div>
                                            <input type ="hidden" name ="customerId" value="">
                                        </c:if>
                                    </c:forEach></td>
                                <td>${feedback.content}</td>
                                <td>${feedback.feedbackDate}</td>
                                <td>                 
                                    <a href="#viewReport${feedback.id}"  class="edit" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="View Report">&#xe8f4;</i></a>
                                </td>
                                <td>
                                    <c:if test="${feedback.check == true}">
                                        Replied
                                    </c:if>
                                    <c:if test="${feedback.check != true}">
                                        Not Replied Yet
                                    </c:if>
                                </td>
                                <!--View and reply report-->
                        <div id="viewReport${feedback.id}" class="modal fade">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <form action="write-feedback?go=response-customer" method="POST">
                                        <div class="modal-header">						
                                            <h4 class="modal-title">View Feedback</h4>
                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                        </div>
                                        <div class="modal-body">					
                                            <div class="form-group">
                                                <label>Customer Sent</label>                                               
                                                <c:forEach items="${customers}" var="customer">
                                                    <c:if test="${Integer.parseInt(feedback.customerId) == Integer.parseInt(customer.id)}">
                                                        <div>${customer.firstName} ${customer.lastName}</div>
                                                        <input type ="hidden" name ="customerId" value="${customer.id}">
                                                    </c:if>
                                                </c:forEach>
                                            </div>
                                            
                                            <input type="hidden" name ="orderId" class="form-control" value ="${feedback.orderId}" readonly>
                                            <input type="hidden" name ="productId" class="form-control" value ="${feedback.productId}" readonly>
                                            <input type="hidden" name ="feedbackDate" class="form-control" value ="${feedback.feedbackDate}" readonly>
                                            <input type="hidden" name ="feedbackId" class="form-control" value ="${feedback.id}" readonly>
                                            <div class="form-group">
                                                <label>Content</label>
                                                <input type="content" class="form-control" name="response-content" value ="${feedback.content}" readonly>
                                            </div>	                                         
                                            <div class="form-group">
                                                <label>Reply</label>
                                                <c:choose>
                                                    <c:when test="${feedback.check == true}">
                                                        <textarea type="content" name="reply" class="form-control" required rows="5" cols="33" maxlength="1000" readonly>${feedback.reply}</textarea>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <textarea type="text" name="reply" class="form-control" value="Your reply..." required rows="5" cols="33" maxlength="1000"></textarea>
                                                        <div class="modal-footer">
                                                            <input type="submit" class="btn btn-success" value="Send reply" >
                                                            <input type="button" class="btn btn-default" data-dismiss="modal" value="Close">
                                                        </div>
                                                    </c:otherwise>
                                                </c:choose>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <!-- Add Modal HTML -->
        <div id="addReportModal" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form action="write-report?action=add" method="post">
                        <div class="modal-header">						
                            <h4 class="modal-title">Write report</h4>
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        </div>
                        <div class="modal-body">					
                            <div class="form-group">
                                <label>Manager name</label>
                                <select name="name" class="form-select">
                                    <c:forEach items="${managers}" var="managers">
                                        <option value="${managers.id}">${managers.firstName} ${managers.lastName}</option>
                                    </c:forEach>
                                </select>
                            </div>

                            <div class="form-group">
                                <label>Title</label>
                                <input name="title" type="text" class="form-control" required>
                            </div>
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
        <!-- Delete Modal HTML -->
        <div id="deleteEmployeeModal" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form>
                        <div class="modal-header">						
                            <h4 class="modal-title">Delete Report</h4>
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        </div>
                        <div class="modal-body">					
                            <p>Are you sure you want to delete this Report?</p>
                            <p class="text-warning"><small>This action cannot be reverse.</small></p>
                        </div>
                        <div class="modal-footer">
                            <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                            <input type="submit" class="btn btn-danger" value="Delete">
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </a>
    <script src="${pageContext.request.contextPath}/js/manager.js" type="text/javascript"></script>
</body>
</html>
