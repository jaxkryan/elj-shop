<%-- 
    Document   : ManagerProduct
    Created on : Dec 28, 2020, 5:19:02 PM
    Author     : trinh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="model.Product, java.util.Vector" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
            <!-- Alert-->
            <div class="row p-3">
                <div class="col-lg-12">
                    <div class="alert alert-danger">

                        <strong> <c:forEach items="${products}" var="pro">
                                <c:if test="${pro.quantity < 50}">
                                    <c:set var="productNames" value="${productNames}, ${pro.name}"/>
                                </c:if>
                            </c:forEach>
                            <c:if test="${!empty(productNames)}">
                                <c:out value="${fn:substring(productNames, 1, fn:length(productNames))} 
                                       is nearly out of stock. Please re-supply it." />
                            </c:if></strong>

                    </div>
                </div>
            </div>
            <!-- Alert end -->
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
                        <div class="manager-nav-item">
                            <a href="${pageContext.request.contextPath}/storage-staff/home" class="table-title-link curent-page"> <h2>Manage <b>Product</b></h2></a>
                        </div>

                        <div class="manager-nav-item">
                            <a href="${pageContext.request.contextPath}/storage-staff/update-order-status" class="table-title-link"><h2>Manage <b>Order</b></h2></a>
                        </div>

                        <div class="manager-nav-item">
                            <!--Report-->
                            <a href="${pageContext.request.contextPath}/storage-staff/write-report?action=view" class="table-title-link"><h2><b>Report</h2></b></a>
                        </div>
                    </div>
                </div>
                <!--sorting-->
                <div style="display: flex">
                    <form id="sortForm" action="storage-manage-product" method="post">
                        <select name="sort" id="sort" onchange="submitForm()">
                            <option value="All">Display All</option>
                            <option value="Asc">Quantity Ascending</option>
                            <option value="Desc">Quantity Descending</option>
                            <input type="hidden" name="keySearch" value="${requestScope.keySearch}">
                        </select>
                    </form>
                    <!--Search bar-->
                    <form action="storage-manage-product?search=All" method="post" style="margin-left: 65%">
                        <div class="text-right" style="margin-top: 0.5%">
                            <input style="color: black" name = "keyword" type="text" class="search-bar" value="${requestScope.keySearch}">
                            <input style="color: #000000" type="submit" name = "searchSubmit" value="Search">
                        </div>
                    </form>
                </div>
                <script>
                    function submitForm() {
                        document.getElementById("sortForm").action = "storage-manage-product?sort=" + document.getElementById("sort").value;
                        document.getElementById("sortForm").submit();
                    }
                </script>
                <!--Table product-->
                <table class="table table-striped table-hover">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Image</th>
                            <th>Quantity</th>
                            <th>Edit</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${products}" var="pro">
                            <tr>

                                <td>${pro.id}</td>
                                <td>${pro.name}</td>
                                <td>
                                    <img src="${pro.image}">
                                </td>
                                <td>${pro.quantity}</td>
                                <td>
                                    <a href="#editProductQuantModal${pro.id}" class="edit" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</i></a>
                                </td>
                                <!--Edit quantity-->
                        <div id="editProductQuantModal${pro.id}" class="modal fade">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <form action="storage-manage-product?sort=All" method="post">
                                        <input type="hidden" name="go" value="update">
                                        <input type="hidden" name ="prodId" class="form-control" value ="${pro.id}">
                                        <div class="modal-header">						
                                            <h4 class="modal-title">Edit Quantity</h4>
                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                        </div>
                                        <div class="modal-body">					
                                            <div class="form-group">
                                                <label>Name</label>
                                                <input name="name" type="text" class="form-control" value="${pro.name}" readonly>
                                            </div>
                                            <div class="form-group">
                                                <label>Old quantity</label>
                                                <input type="email" class="form-control" value="${pro.quantity}" readonly>
                                            </div>
                                            <div class="form-group">
                                                <label>New quantity</label>
                                                <input name="qty" type="number" class="form-control" min="1" required>
                                            </div>		
                                        </div>
                                        <div class="modal-footer">
                                            <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                                            <input type="submit" name = "submit" class="btn btn-info" value="Save">
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

        <script src="${pageContext.request.contextPath}/js/manager.js" type="text/javascript"></script>
    </body>
</html>

