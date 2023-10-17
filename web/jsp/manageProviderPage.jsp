<%-- 
    Document   : ManagerProduct
    Created on : Dec 28, 2020, 5:19:02 PM
    Author     : trinh
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
                        <div class="col-sm-2">
                            <a href="home"><h2> <b>Manage Product</b></h2></a>
                        </div>
                        <div class="col-sm-2">
                            <a href="provider"><h2> <b>Manage provider</b></h2></a>
                        </div>
                        <!--Report-->
                        <div class="col-sm-2">
                            <a href="${pageContext.request.contextPath}/write-report?action=reply"><h2><b>Report</b></h2></a>
                        </div>

                        <div class="col-sm-6">
                            <a href="#addEmployeeModal"  class="btn btn-success" data-toggle="modal"><i class="material-icons">&#xE147;</i> <span>Add</span></a>
                            <a href="#deleteEmployeeModal" class="btn btn-danger" data-toggle="modal"><i class="material-icons">&#xE15C;</i> <span>Delete</span></a>						
                        </div>
                    </div>
                </div>
                <div class="row text-right"> 
                    <!--Search bar-->
                    <form action="provider?search=All" method="post">
                        <div class="text-right" style="margin-top: 0.5%">
                            <input style="color: black" name = "keyword" type="text" class="search-bar" placeholder="Search product...">
                            <input style="color: #000000" type="submit" name = "search" value="Search">
                        </div>
                    </form>
                </div>        
            </div>
            <c:choose>
                <c:when test="${param.go == null || param.go == 'displayAll'}">
                    <table class="table table-striped table-hover">
                        <thead>
                            <tr>
                                <th>
                                    <span class="custom-checkbox">
                                        <input type="checkbox" id="selectAll">
                                        <label for="selectAll"></label>
                                    </span>
                                </th>
                                <th>ID</th>
                                <th>Company</th>
                                <th>Image</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${providers}" var="provider">
                                <tr>
                                    <td>
                                        <span class="custom-checkbox">
                                            <input type="checkbox" id="checkbox1" name="options[]" value="1">
                                            <label for="checkbox1"></label>
                                        </span>
                                    </td>
                                    <td>${provider.id}</td>
                                    <td>${provider.companyName}</td>
                                    <td>
                                        <img src="${provider.image}">
                                    </td>
                                    <td>
                                        <a href="#editEmployeeModal${provider.id}"  class="edit" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</i></a>
                                        <a href="provider?go=delete&pid=${provider.id}" class="delete" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="Delete">&#xE872;</i></a>
                                    </td>
                                    <!-- Edit Modal HTML -->
                            <div id="editEmployeeModal${provider.id}" class="modal fade">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <form action="provider" method="post">
                                            <div class="modal-header">						
                                                <h4 class="modal-title">Add Product</h4>
                                                <a type="button" class="close" href="provider?"  data-dismiss="modal" aria-hidden="true">&times;</a>

                                            </div>
                                            <div class="modal-body">					
                                                <div class="form-group">
                                                    <label>ID</label>
                                                    <input value="${provider.id}" name="id" type="text" class="form-control" readonly required>
                                                </div>
                                                <div class="form-group">
                                                    <label>Name</label>
                                                    <input value="${provider.companyName}" name="companyName" type="text" class="form-control" maxlength="64" pattern="^[A-Za-z0-9\s]+$" title="Company name can only contain letters and numbers, with a maximum length of 64 characters" required>
                                                </div>
                                                <div class="form-group">
                                                    <label>Image</label>
                                                    <textarea name="image" type="text" class="form-control" pattern="^(http(s?):\/\/|www.)+[a-zA-Z0-9-]+\.[a-zA-Z0-9]+\/?[a-zA-Z0-9-]*\.(png|jpg|jpeg|gif|bmp|svg)$" title="Please enter a valid image link" required>${provider.image}</textarea>
                                                </div>

                                                <input type="hidden" name="go" value="UpdateProvider">
                                            </div>
                                            <div class="modal-footer">
                                                <input type="submit" class="btn btn-success" value="Edit">
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </c:when>
            </c:choose>
            <!-- Edit Modal HTML -->
            <div id="addEmployeeModal" class="modal fade">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <!<!-- add form -->
                        <form action="provider" method="POST">
                            <div class="modal-header">						
                                <h4 class="modal-title">Add Provider</h4>
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            </div>
                            <div class="modal-body">					
                                <div class="form-group">
                                    <label>Company Name</label>
                                    <input name="companyName" type="text" class="form-control" maxlength="64" pattern="^[A-Za-z0-9\s]+$" title="Company name can only contain letters and numbers, with a maximum length of 64 characters" required>
                                </div>

                                <div class="form-group">
                                    <label>Image</label>
                                    <input name="image" type="text" class="form-control" pattern="^(http(s?):\/\/|www.)+[a-zA-Z0-9-]+\.[a-zA-Z0-9]+\/?[a-zA-Z0-9-]*\.(png|jpg|jpeg|gif|bmp|svg)$" title="Please enter a valid image link" required>
                                </div>
                                <input type="hidden" name="go" value="AddProvider">
                            </div>

                            <div class="modal-footer">
                                <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                                <input type="submit" class="btn btn-success" value="Add">
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
                                <h4 class="modal-title">Delete Product</h4>
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            </div>
                            <div class="modal-body">					
                                <p>Are you sure you want to delete these Records?</p>
                                <p class="text-warning"><small>This action cannot be undone.</small></p>
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