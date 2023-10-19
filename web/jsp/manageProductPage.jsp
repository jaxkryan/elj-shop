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
        <!-- Shop Sidebar Start -->
        <div class="col-lg-1 col-md-4"> <!-- goc: col-lg-3 col-md-4 -->
            <form action="home" method="GET">
                <!-- Categories Start -->
                <h5 class="section-title position-relative text-uppercase mb-3"><span class="bg-secondary pr-3">Sort By Price</span></h5>
                <div class="bg-light p-4 mb-30">
                    <div class="custom-control custom-radio d-flex align-items-center justify-content-between mb-3">
                        <input type="radio" class="custom-control-input" name="sort" id="default" value="" onclick="this.form.submit()" <c:if test="${(param.sort == null || param.sort == '')}">checked</c:if>>
                            <label class="custom-control-label" for="default">Default</label>
                        </div>
                        <div class="custom-control custom-radio d-flex align-items-center justify-content-between mb-3">
                            <input type="radio" class="custom-control-input" name="sort" id="sort-ascending" value="ascending" onclick="this.form.submit()" <c:if test="${param.sort == 'ascending'}">checked</c:if>>
                            <label class="custom-control-label" for="sort-ascending">Ascending</label>
                        </div>
                        <div class="custom-control custom-radio d-flex align-items-center justify-content-between mb-3">
                            <input type="radio" class="custom-control-input" name="sort" id="sort-descending" value="descending" onclick="this.form.submit()" <c:if test="${param.sort == 'descending'}">checked</c:if>>
                            <label class="custom-control-label" for="sort-descending">Descending</label>
                        </div>
                    </div>
                    <h5 class="section-title position-relative text-uppercase mb-3"><span class="bg-secondary pr-3">Categories</span></h5>
                    <div class="bg-light p-4 mb-30">
                        <div class="custom-control custom-radio d-flex align-items-center justify-content-between mb-3">
                            <input type="radio" class="custom-control-input" id="cate-all" name="categoryId" value="-1" onclick="this.form.submit()" <c:if test="${param.categoryId == -1 || param.categoryId == null}">checked</c:if>>
                            <label class="custom-control-label" for="cate-all">All</label>
                        </div>
                    <c:forEach items="${categories}" var="cate">
                        <div class="custom-control custom-radio d-flex align-items-center justify-content-between mb-3">
                            <input type="radio" class="custom-control-input" id="category-${cate.id}" name="categoryId" value="${cate.id}" onclick="this.form.submit()" <c:if test="${param.categoryId == cate.id}">checked</c:if>>
                            <label class="custom-control-label" for="category-${cate.id}">${cate.name}</label>
                        </div>
                    </c:forEach>
                </div>
                <!-- Categories End -->

                <!-- Price Start -->
                <h5 class="section-title position-relative text-uppercase mb-3"><span class="bg-secondary pr-3">Price</span></h5>
                <div class="bg-light p-4 mb-30">
                    <div class="custom-control custom-radio d-flex align-items-center justify-content-between mb-3">
                        <input type="radio" class="custom-control-input" name="price" id="priceAll" value="" onclick="this.form.submit()" <c:if test="${(param.price == null || param.price == '')}">checked</c:if>>
                            <label class="custom-control-label" for="priceAll">All</label>
                        </div>
                        <div class="custom-control custom-radio d-flex align-items-center justify-content-between mb-3">
                            <input type="radio" class="custom-control-input" name="price" id="price1" value="0-100" onclick="this.form.submit()" <c:if test="${param.price == '0-100'}">checked</c:if>>
                            <label class="custom-control-label" for="price1">Below 100$</label>
                        </div>
                        <div class="custom-control custom-radio d-flex align-items-center justify-content-between mb-3">
                            <input type="radio" class="custom-control-input" name="price" id="price2" value="100-200" onclick="this.form.submit()" <c:if test="${param.price == '100-200'}">checked</c:if>>
                            <label class="custom-control-label" for="price2">100$ - 200$</label>
                        </div>
                        <div class="custom-control custom-radio d-flex align-items-center justify-content-between mb-3">
                            <input type="radio" class="custom-control-input" name="price" id="price3" value="200-500" onclick="this.form.submit()" <c:if test="${param.price == '200-500'}">checked</c:if>>
                            <label class="custom-control-label" for="price3">200$ - 500$</label>
                        </div>
                        <div class="custom-control custom-radio d-flex align-items-center justify-content-between mb-3">
                            <input type="radio" class="custom-control-input" name="price" id="price4" value="500-1000000" onclick="this.form.submit()" <c:if test="${param.price == '500-1000000'}">checked</c:if>>
                            <label class="custom-control-label" for="price4">Above 500$</label>
                        </div>
                    </div>
                    <!-- Price End -->

                    <!-- Brand Start -->
                    <h5 class="section-title position-relative text-uppercase mb-3"><span class="bg-secondary pr-3">Brands</span></h5>
                    <div class="bg-light p-4 mb-30">
                        <div class="custom-control custom-radio d-flex align-items-center justify-content-between mb-3">
                            <input type="radio" class="custom-control-input" id="provider-all" name="providerId" value="-1" onclick="this.form.submit()" <c:if test="${(param.providerId == -1) || (param.providerId == null)}">checked</c:if>>
                            <label class="custom-control-label" for="provider-all">All</label>
                        </div>
                    <c:forEach items="${providers}" var="provider">
                        <div class="custom-control custom-radio d-flex align-items-center justify-content-between mb-3">
                            <input type="radio" class="custom-control-input" id="provider-${provider.id}" name="providerId" value="${provider.id}" onclick="this.form.submit()" <c:if test="${param.providerId == provider.id}">checked</c:if>>
                            <label class="custom-control-label" for="provider-${provider.id}">${provider.companyName}</label>
                        </div>
                    </c:forEach>
                    <input type="hidden" name="searchName" value="${searchName}">
                    <input type="hidden" name="go" value="filter">
                </div>
            </form>
            <!-- Brand End -->
        </div>
        <!-- Shop Sidebar End -->
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
                        <div class="col-sm-2">
                            <a href="order"><h2> <b>Manage order</b></h2></a>
                        </div>
                        <!--Report-->
                        <div class="col-sm-2">
                            <a href="${pageContext.request.contextPath}/manager/write-report?action=reply"><h2><b>Report</b></h2></a>
                        </div>

                        <div class="col-sm-6">
                            <a href="#addEmployeeModal"  class="btn btn-success" data-toggle="modal"><i class="material-icons">&#xE147;</i> <span>Add</span></a>
                            <a href="#deleteEmployeeModal" class="btn btn-danger" data-toggle="modal"><i class="material-icons">&#xE15C;</i> <span>Delete</span></a>						
                        </div>
                    </div>
                </div> 
                <!--Search bar-->
                <div class="row text-right"> 

                    <form action="home?go=search" method="post">
                        <div class="text-right" style="margin-top: 0.5%">
                            <input style="color: black" name = "searchName" value="${searchName}" type="text" class="search-bar" placeholder="Search product...">
                            <input style="color: #000000" type="submit" name = "searchSubmit" value="Search">

                        </div>
                    </form>
                </div>
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
                            <th>Category</th>
                            <th>Provider</th>
                            <th>description</th>
                            <th>price</th>
                            <th>discount</th>
                            <th>quantity</th>
                            <th>image</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${products}" var="product">
                            <tr>
                                <td>
                                    <span class="custom-checkbox">
                                        <input type="checkbox" id="checkbox1" name="options[]" value="1">
                                        <label for="checkbox1"></label>
                                    </span>
                                </td>
                                <td>${product.id}</td>
                                <td>${product.name}</td>
                                <td>${categories.get(product.categoryId - 1).name}</td>
                                <c:if test="${empty providers[product.providerId - 1].companyName}">
                                    <td>Not Available</td>
                                </c:if>
                                <c:if test="${not empty providers[product.providerId - 1].companyName}">
                                    <td>${providers[product.providerId - 1].companyName}</td>
                                </c:if>   
                                <td>${product.description}</td>
                                <td>${product.price}$</td>
                                <td>${product.discount}$</td>
                                <td>${product.quantity}</td>
                                <td>
                                    <img src="${product.image}">
                                </td>
                                <td>
                                    <a href="#editEmployeeModal${product.id}"  class="edit" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</i></a>
                                    <a href="home?go=delete&pid=${product.id}" class="delete" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="Delete">&#xE872;</i></a>
                                </td>
                                <!-- Edit Modal HTML -->
                        <div id="editEmployeeModal${product.id}" class="modal fade">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <!-- Edit Form -->
                                    <form action="home" method="post">
                                        <div class="modal-header">						
                                            <h4 class="modal-title">Add Product</h4>
                                            <a type="button" class="close" href="home?"  data-dismiss="modal" aria-hidden="true">&times;</a>
                                        </div>
                                        <div class="modal-body">					
                                            <div class="form-group">
                                                <label>ID</label>
                                                <input value="${product.id}" name="id" type="text" class="form-control" readonly required>
                                            </div>
                                            <div class="form-group">
                                                <label>Name</label>
                                                <input value="${product.name}" name="name" type="text" class="form-control" maxlength="64" pattern="[A-Za-z0-9 ]{1,64}" title="Name can only contain letters, numbers, and spaces, with a maximum length of 64 characters" required required>
                                            </div>
                                            <div class="form-group">
                                                <label>Price</label>
                                                <input value="${product.price}" name="price" type="text" class="form-control" maxlength="64" pattern="^\d{1,10}(\.\d{1,2})?$" title="Price can only contain numbers with a maximum of 10 digits and up to 2 decimal places" required>
                                            </div>
                                            <div class="form-group">
                                                <label>Provider</label>
                                                <select name="provider" class="form-control" aria-label="Default select example">
                                                    <c:forEach items="${providers}" var="provider">
                                                        <c:choose>
                                                            <c:when test="${provider.id eq product.providerId}">
                                                                <option value="${provider.id}" selected>${provider.companyName}</option>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <option value="${provider.id}">${provider.companyName}</option>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </c:forEach>
                                                </select>
                                            </div>


                                            <div class="form-group">
                                                <label>Category</label>
                                                <select name="category" class="form-control" aria-label="Default select example">
                                                    <c:forEach items="${categories}" var="category">
                                                        <c:choose>
                                                            <c:when test="${category.id eq product.categoryId}">
                                                                <option value="${category.id}" selected>${category.name}</option>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <option value="${category.id}">${category.name}</option>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </c:forEach>
                                                </select>
                                            </div>

                                            <div class="form-group">
                                                <label>Quantity</label>
                                                <input value="${product.quantity}" name="quantity" class="form-control" maxlength="64" pattern="^[1-9]\d*$" title="Positive integer greater than 0, with a maximum length of 64 characters"  required>
                                            </div>
                                            <div class="form-group">
                                                <label>Image</label>
                                                <input value="${product.image}" name="image" class="form-control" pattern="^(http(s?):\/\/|www.)+[a-zA-Z0-9-]+\.[a-zA-Z0-9]+\/?[a-zA-Z0-9-]*\.(png|jpg|jpeg|gif|bmp|svg)$" title="Please enter a valid image link" required>
                                            </div><!-- comment -->
                                            <div class="form-group">
                                                <label>Discount</label>
                                                <input value="${product.discount}" name="discount" class="form-control" pattern="^\d{1,10}(\.\d{1,2})?$" title="Discount can only contain numbers with a maximum of 10 digits and up to 2 decimal places. Must be greater than or equal to 0 and less than Price" required>
                                            </div>
                                            <div class="form-group">
                                                <label>Description</label>
                                                <textarea name="description" class="form-control" required>${product.description}</textarea>

                                            </div>                               
                                            <input type="hidden" name="go" value="UpdateProduct">
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
            </div>
        </div>
        <!-- Edit Modal HTML -->
        <div id="addEmployeeModal" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form action="home" method="POST">
                        <div class="modal-header">						
                            <h4 class="modal-title">Add Product</h4>
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        </div>
                        <div class="modal-body">					
                            <div class="form-group">
                                <label>Name</label>
                                <input name="name" type="text" class="form-control" maxlength="64" pattern="[A-Za-z0-9 ]{1,64}" title="Name can only contain letters, numbers, and spaces, with a maximum length of 64 characters" required>
                            </div>

                            <div class="form-group">
                                <label>Price</label>
                                <input name="price" type="text" class="form-control" maxlength="64" pattern="^\d{1,10}(\.\d{1,2})?$" title="Price can only contain numbers with a maximum of 10 digits and up to 2 decimal places" required>
                            </div>
                            <div class="form-group">
                                <label>Provider</label>
                                <select name="provider" class="form-control" aria-label="Default select example">
                                    <c:forEach items="${providers}"  var="provider">
                                        <option value="${provider.id}">${provider.companyName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group">
                                <label>Category</label>
                                <select name="category" class="form-control" aria-label="Default select example">
                                    <c:forEach items="${categories}"  var="category">
                                        <option value="${category.id}">${category.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group">
                                <label>Quantity</label>
                                <input name="quantity" type="text" class="form-control" maxlength="64" pattern="^[1-9]\d*$" title="Positive integer greater than 0, with a maximum length of 64 characters" required>
                            </div>

                            <div class="form-group">
                                <label>Image</label>
                                <input name="image" type="text" class="form-control" pattern="^(http(s?):\/\/|www.)+[a-zA-Z0-9-]+\.[a-zA-Z0-9]+\/?[a-zA-Z0-9-]*\.(png|jpg|jpeg|gif|bmp|svg)$" title="Please enter a valid image link" required>
                            </div>

                            <div class="form-group">
                                <label>Discount</label>
                                <input name="discount" type="text" class="form-control" pattern="^\d{1,10}(\.\d{1,2})?$" title="Discount can only contain numbers with a maximum of 10 digits and up to 2 decimal places. Must be greater than or equal to 0 and less than Price" required>
                            </div>
                            <div class="form-group">
                                <label>Description</label>
                                <textarea name="description" class="form-control" required></textarea>
                            </div>    
                            <input type="hidden" name="go" value="AddProduct">
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
    <script>
                    document.addEventListener("DOMContentLoaded", function () {
                        var priceInput = document.getElementsByName("price")[0];
                        var discountInput = document.getElementsByName("discount")[0];

                        // Add input event listener to validate Discount
                        discountInput.addEventListener("input", function () {
                            var priceValue = parseFloat(priceInput.value);
                            var discountValue = parseFloat(discountInput.value);

                            if (isNaN(priceValue) || isNaN(discountValue)) {
                                // Invalid numeric input, reset Discount value
                                discountInput.setCustomValidity("");
                            } else {
                                // Check Discount validity based on the conditions
                                if (discountValue >= 0 && discountValue <= priceValue) {
                                    // Valid Discount
                                    discountInput.setCustomValidity("");
                                } else {
                                    // Invalid Discount
                                    discountInput.setCustomValidity("Discount must be greater than or equal to 0 and less than or equal to Price");
                                }
                            }
                        });
                    });
    </script>
</body>
</html>