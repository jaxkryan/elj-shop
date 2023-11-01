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
            <div class="row p-3">
                <div class="col-sm-6 text-left">
                    <a href="home" class="btn btn-primary">Back</a>
                </div>
                <div class="col-sm-6 text-right">
                    <a href="profile" class="btn btn-primary">Profile</a>
                    <a href="${pageContext.request.contextPath}/logout" class="btn btn-primary">Log Out</a>
                </div>
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

            <div class="container" style="width: 85%;">
                <!-- Personal Info -->
                <div class="table-wrapper">
                    <div class="table-title">
                        <div class="row">
                            <div class="col-sm-6">
                                <h2>Personal <b>Infomation</b></h2>
                            </div>
                        </div>
                    </div>
                    <form class="row" action="update-user" method="POST">
                        <div class="col-md-6 form-group">
                            <label>ID</label>
                            <input name="id" class="form-control" type="text" value="${user.id}" readonly>
                        </div>
                        <div class="col-md-6 form-group">
                            <label>Role</label>
                            <!--<input name="role" class="form-control" type="text" value="${user.role}">-->
                            <select name="role" class="form-control">
                                <option value="Admin" <c:if test="${user.role == 'Admin'}">selected</c:if>>Admin</option>
                                <option value="Customer" <c:if test="${user.role == 'Customer'}">selected</c:if>>Customer</option>
                                <option value="Manager" <c:if test="${user.role == 'Manager'}">selected</c:if>>Manager</option>
                                <option value="Seller" <c:if test="${user.role == 'Seller'}">selected</c:if>>Seller</option>
                                <option value="Storage Staff" <c:if test="${user.role == 'Storage Staff'}">selected</c:if>>Storage Staff</option>
                                <option value="Marketing Staff" <c:if test="${user.role == 'Marketing Staff'}">selected</c:if>>Marketing Staff</option>
                                </select>
                            </div>
                            <div class="col-md-6 form-group">
                                <label>First Name</label>
                                <input name="firstName" class="form-control" type="text" value="${user.getFirstName()}" required>
                        </div>
                        <div class="col-md-6 form-group">
                            <label>Last Name</label>
                            <input name="lastName" class="form-control" type="text" value="${user.getLastName()}" required>
                        </div>
                        <div class="col-md-6 form-group">
                            <label>Date Of Birth</label>
                            <input name="dateOfBirth" class="form-control" type="date" value="${user.getDateOfBirth()}" <c:if test="${user.role != 'Customer'}">required</c:if>>
                            </div>
                            <div class="col-md-6 form-group">
                                <label>Street</label>
                                <input name="street" class="form-control" type="text" value="${user.getStreet()}" <c:if test="${user.role != 'Customer'}">required</c:if>>
                            </div>
                            <div class="col-md-6 form-group">
                                <label>City</label>
                                <input name="city" class="form-control" type="text" value="${user.getCity()}" <c:if test="${user.role != 'Customer'}">required</c:if>>
                            </div>
                            <div class="col-md-6 form-group">
                                <label>Province</label>
                                <input name="province" class="form-control" type="text" value="${user.getProvince()}" <c:if test="${user.role != 'Customer'}">required</c:if>>
                            </div>
                            <div class="col-md-6 form-group">
                                <label>Country</label>
                                <input name="country" class="form-control" type="text" value="${user.getCountry()}" <c:if test="${user.role != 'Customer'}">required</c:if>>
                            </div>
                            <div class="col-md-6 form-group">
                                <label>Mobile No</label>
                                <input name="phone" class="form-control" type="tel" pattern="[0-9]{3}[0-9]{3}[0-9]{4}" title="Ten digits code" value="${user.getPhone()}" <c:if test="${user.role != 'Customer'}">required</c:if>>
                            </div>
                            <div class="col-md-6 form-group">
                            </div>
                            <div class="col-md-6 form-group">
                            </div>
                            <div class="col-md-12 form-group">
                                <div class="row">
                                    <div class="col-md-4 form-group">
                                    </div>
                                    <div class="col-md-2 form-group">
                                        <input name="UpdateUserEditPersonalInfoSubmit" class="form-control btn btn-primary" type="submit" value="Update">
                                    </div>
                                    <div class="col-md-2 form-group">
                                        <a class="form-control btn btn-primary" href="${pageContext.request.contextPath}/home">Cancel</a>
                                </div>
                                <div class="col-md-4 form-group">
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
                <c:if test="${not empty user.password}">
                    <!-- Update Email -->
                    <div class="table-wrapper">
                        <div class="table-title">
                            <div class="row">
                                <div class="col-sm-6">
                                    <h2>Update <b>Email</b></h2>
                                </div>
                            </div>
                        </div>
                        <form class="row" action="update-user" method="POST" style="width: 100%">
                            <input name="id" type="hidden" value="${user.getId()}">
                            <div class="col-md-6 form-group">
                                <label>E-mail</label>
                                <input name="email" class="form-control" type="email" value="${user.getEmail()}" required>
                            </div>

                            <div class="col-md-2 form-group" style="margin-top: 23px "> 
                                <input name="UpdateUserUpdateEmailSubmit" class="form-control btn btn-primary" type="submit" value="Update email">
                            </div>
                        </form>
                    </div>

                    <!-- Change Password -->
                    <div class="table-wrapper">
                        <div class="table-title">
                            <div class="row">
                                <div class="col-sm-6">
                                    <h2>Change <b>Password</b></h2>
                                </div>
                            </div>
                        </div>
                        <form class="row" action="update-user" method="POST">
                            <input name="id" type="hidden" value="${user.getId()}">
                            <div class="col-md-6 form-group">
                                <label>New Password</label>
                                <input name="password" class="form-control" type="password" minlength="8" required>
                            </div>
                            <div class="col-md-6 form-group">
                            </div>
                            <div class="col-md-12 form-group">
                            </div>
                            <div class="col-md-12 form-group">
                                <div class="row">
                                    <div class="col-md-4 form-group">
                                    </div>
                                    <div class="col-md-2 form-group">
                                        <input name="UpdateUserChangePasswordSubmit" class="form-control btn btn-primary" type="submit" value="Update">
                                    </div>
                                    <div class="col-md-2 form-group">
                                        <a class="form-control btn btn-primary" href="${pageContext.request.contextPath}/home">Cancel</a>
                                    </div>
                                    <div class="col-md-4 form-group">
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </c:if>
            </div>
        </div>
    </div>
</body>
</html>