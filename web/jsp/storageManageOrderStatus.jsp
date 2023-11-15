<%-- 
    Document   : ManagerManageOrderPage
    Created on : Oct 17, 2023, 10:53:16 PM
    Author     : admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
            <div class="table-wrapper">
                <div class="table-title">
                    <div class="row">
                        <div class="manager-nav-item">
                            <a href="${pageContext.request.contextPath}/storage-staff/home" class="table-title-link "> <h2>Manage <b>Product</b></h2></a>
                        </div>

                        <div class="manager-nav-item">
                            <a href="${pageContext.request.contextPath}/storage-staff/update-order-status" class="table-title-link curent-page"><h2>Manage <b>Order</b></h2></a>
                        </div>

                        <div class="manager-nav-item">
                            <!--Report-->
                            <a href="${pageContext.request.contextPath}/storage-staff/write-report?action=view" class="table-title-link"><h2><b>Report</h2></b></a>
                        </div>
                    </div>
                </div>
                        
                <form id="sortForm" action="${pageContext.request.contextPath}/storage-staff/update-order-status?go=filter" method="get">
                    <input type="hidden" name="go" value="filter">
                    <div class="row">
                        <div class="col-md-6 text-left">
                            <label>Sort by: </label>
                            <select name="sortType" id="sort" onchange="this.form.submit()">
                                <option value="Default" <c:if test="${param.sortType == 'Default'}">selected</c:if>>Default</option>
                                <option value="ASC" <c:if test="${param.sortType == 'ASC'}">selected</c:if>>Ascending</option>
                                <option value="DESC" <c:if test="${param.sortType == 'DESC'}">selected</c:if>>Descending</option>
                                </select>

                                <label class="ml-3">Status: </label>
                                <select name="statusFilter" id="filter" onchange="this.form.submit()">
                                    <option value="All" <c:if test="${param.statusFilter == 'All'}">selected</c:if>>All</option>
                                <option value="Processing" <c:if test="${param.statusFilter == 'Processing'}">selected</c:if>>Processing</option>
                                <option value="Packing" <c:if test="${param.statusFilter == 'Packing'}">selected</c:if>>Packing</option>
                                </select>
                            </div>
                            <div class="col-md-6 text-right">
                                <input style="color: black" name = "keyword" type="text" class="search-bar ml-3" placeholder="Enter customer's name" value="${param.keyword}">
                            <input style="color: #000000" type="submit" name = "searchOrderSubmit" value="Search">
                        </div>
                    </div>
                </form> 
                
                        <table class="table table-striped table-hover">
                            <thead>
                                <tr>

                                    <th>ID</th>
                                    <th>CustomerID</th>
                                    <th>Receiver</th>
                                    <th>Address</th>
                                    <th>Contact</th>
                                    <th>Status</th>
                                    <th>CreatedTime</th>
                                    <th>TotalPrice</th>
                                    <th>Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${orders}" var="order">
                                    <c:if test="${order.status eq 'Packing' or order.status eq 'Processing'}">
                                    <tr>
                                        <td class="align-middle">${order.id}</td>
                                        <td class="align-middle">${order.customerId}</td>
                                        <td class="align-middle text-left" style="text-wrap: nowrap;">${order.receiver}</td>
                                        <td class="align-middle text-left">${order.shipStreet}, ${order.shipCity}, ${order.shipProvince}, ${order.shipCountry}</td>
                                        <td class="align-middle">${order.shipEmail}, ${order.shipPhone}</td>
                                        <td class="align-middle">${order.status}</td>
                                        <td class="align-middle">${order.createdTime}</td>
                                        <td class="align-middle">${order.totalPrice}</td>
                                        <td>
                                            <a href="${pageContext.request.contextPath}/storage-staff/update-order-status?go=viewDetail&id=${order.id}&cusId=${order.customerId}&sortType=${param.sortType}&statusFilter=${param.statusFilter}&keyword=${param.keyword}"  
                                               class="edit" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="View Detail">&#xe8f4;</i></a>
                                        </td>
                                        <!-- Delete Modal HTML -->
                                <div id="deleteEmployeeModal${order.id}" class="modal fade">
                                    <div class="modal-dialog">
                                        <div class="modal-content">
                                            <form action="${pageContext.request.contextPath}/storage-staff/update-order-status?go">
                                                <div class="modal-header">						
                                                    <h4 class="modal-title">Cancel order</h4>
                                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                                </div>
                                                <div class="modal-body">					
                                                    <p>Are you sure you want to cancel this order?</p>
                                                    <p class="text-warning"><small>This action cannot be undone.</small></p>
                                                </div>
                                                <div class="modal-footer">
                                                    <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                                                    <input id="deleteButton" type="submit" class="btn btn-danger" value="Delete">
                                                </div>
                                                <input type="hidden" name ="go" value="changeOrderStatus" >
                                                <input type="hidden" name ="newStatus" value="Cancelled" >
                                                <input type="hidden" name ="id" value="${order.id}" >
                                                <input type="hidden" name ="cusId" value="${order.customerId}" >
                                            </form>
                                        </div>
                                    </div>
                                </div>


                                </tr>
                                </c:if>
                            </c:forEach>
                            </tbody>
                        </table>
            </div>
        </div>
        <!-- Edit Modal HTML -->
        <div id="editEmployeeModal" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form>
                        <div class="modal-header">						
                            <h4 class="modal-title">Edit Employee</h4>
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        </div>
                        <div class="modal-body">					
                            <div class="form-group">
                                <label>Name</label>
                                <input type="text" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>Email</label>
                                <input type="email" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>Address</label>
                                <textarea class="form-control" required></textarea>
                            </div>
                            <div class="form-group">
                                <label>Phone</label>
                                <input type="text" class="form-control" required>
                            </div>					
                        </div>
                        <div class="modal-footer">
                            <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                            <input type="submit" class="btn btn-info" value="Save">
                        </div>
                    </form>
                </div>
            </div>
        </div>


    </a>
    <script src="${pageContext.request.contextPath}/js/manager.js" type="text/javascript"></script>
</body>
</html>
