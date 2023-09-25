<%-- 
    Document   : ManagerProduct
    Created on : Dec 28, 2020, 5:19:02 PM
    Author     : trinh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Product, java.util.Vector" %>
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
            <div class="table-wrapper">
                <!--title-->
                <div class="table-title">
                    <div class="row">
                        <div class="col-sm-6">
                            <h2>Manage <b>Product</b></h2>
                        </div>
                        <!--Search bar-->
                        <form action="StorageController?go=Desc" method="post">
                            <div class="text-right" style="margin-top: 0.5%">
                                <input type="text" name = "keyword" class="search-bar" placeholder="Search product...">
                                <input style="color: #000000" type="submit" name = "search" value="Search">
                            </div>
                        </form>
                    </div>
                </div>
                <!--sorting-->
                <form id="sortForm" action="StorageController" method="post">
                    <select name="sort" id="sort" onchange="submitForm()">
                        <option value="Desc">Descending</option>
                        <option value="All">Display All</option>
                        <option value="Asc">Ascending</option>
                    </select>
                </form>

                <script>
                    function submitForm() {
                        document.getElementById("sortForm").action = "StorageController?go=" + document.getElementById("sort").value;
                        document.getElementById("sortForm").submit();
                    }
                </script>
                <!--Table product-->
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
                            <th>Name</th>
                            <th>Image</th>
                            <th>Quantity</th>
                            <th>Edit</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${products}" var="pro">
                            <tr>
                                <td>
                                    <span class="custom-checkbox">
                                        <input type="checkbox" id="checkbox1" name="options[]" value="1">
                                        <label for="checkbox1"></label>
                                    </span>
                                </td>
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
                                    <form action="StorageController?go=Desc" method="post">
                                        <input type="hidden" name="go" value="update">
                                        <input type="hidden" name ="prodId" class="form-control" value ="${pro.id}">
                                        <div class="modal-header">						
                                            <h4 class="modal-title">Edit Quantity</h4>
                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                        </div>
                                        <div class="modal-body">					
                                            <div class="form-group">
                                                <label>Name</label>
                                                <input name="name" type="text" class="form-control" value ="${pro.name}" readonly>
                                            </div>
                                            <div class="form-group">
                                                <label>Old quantity</label>
                                                <input type="email" class="form-control" value ="${pro.quantity}" readonly>
                                            </div>
                                            <div class="form-group">
                                                <label>New quantity</label>
                                                <input name="qty" type="text" class="form-control" required>
                                            </div>					
                                        </div>
                                        <div class="modal-footer">
                                            <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                                            <input type="hidden" action = "StorageController?go=Desc" method="post">

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