<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <title>Elj Shop - Online Art Supplies Shop</title>
        <link rel="icon" href="${pageContext.request.contextPath}/img/logo.ico" type="image/icon type">
    </head>

    <body>
        <%@include file="header.jsp" %>

        <div class="container-fluid">
            <div class="row px-xl-5">
                <div class="col-lg-2">
                </div>
                <div class="col-lg-8">
                    <h5 class="section-title position-relative text-uppercase mb-3"><span class="bg-secondary pr-3">Profile</span></h5>
                    <div class="bg-light p-30 mb-5">
                        <div class="row">
                            <form class="row" action="profile" method="POST">
                                <input name="id" type="hidden" value="${user.getId()}">
                                <div class="col-md-6 form-group">
                                    <label>First Name</label>
                                    <input name="firstName" class="form-control" type="text" value="${user.getFirstName()}" required>
                                </div>
                                <div class="col-md-6 form-group">
                                    <label>Last Name</label>
                                    <input name="lastName" class="form-control" type="text" value="${user.getLastName()}" required>
                                </div>
                                <div class="col-md-6 form-group">
                                    <label>Street</label>
                                    <input name="street" class="form-control" type="text" value="${user.getStreet()}" required>
                                </div>
                                <div class="col-md-6 form-group">
                                    <label>Date Of Birth</label>
                                    <input name="dateOfBirth" class="form-control" type="date" value="${user.getDateOfBirth()}" required>
                                </div>
                                <div class="col-md-6 form-group">
                                    <label>City</label>
                                    <input name="city" class="form-control" type="text" value="${user.getCity()}" required>
                                </div>
                                <div class="col-md-6 form-group">
                                    <label>Province</label>
                                    <input name="province" class="form-control" type="text" value="${user.getProvince()}" required>
                                </div>
                                <div class="col-md-6 form-group">
                                    <label>Country</label>
                                    <input name="country" class="form-control" type="text" value="${user.getCountry()}" required>
                                </div>
                                <div class="col-md-6 form-group">
                                    <label>Mobile No</label>
                                    <input name="phone" class="form-control" type="tel" pattern="[0-9]{3}[0-9]{3}[0-9]{4}" title="Ten digits code" value="${user.getPhone()}" required>
                                </div>
                                <div class="col-md-6 form-group">
                                    <label>E-mail</label>
                                    <input name="email" class="form-control" type="email" value="${user.getEmail()}" required>
                                </div>
                                <div class="col-md-6 form-group">
                                </div>
<!--                                <div class="col-md-6 form-group">
                                    <label>Password</label>
                                    <input name="password" class="form-control" type="password" minlength="8" required>
                                </div>-->
                                <div class="col-md-12 form-group">
                                    <div class="row">
                                        <div class="col-md-3 form-group">
                                        </div>
                                        <div class="col-md-3 form-group">
                                            <input name="reset" class="form-control btn btn-primary" type="reset" value="Reset">
                                        </div>
                                        <div class="col-md-3 form-group">
                                            <input name="CustomerEditProfileSubmit" class="form-control btn btn-primary" type="submit" value="Update">
                                        </div>
                                        <div class="col-md-3 form-group">
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <div class="col-lg-2">
                </div>
            </div>

            <%@include file="footer.jsp" %>

            <!-- JavaScript Libraries -->
            <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
            <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>
            <script src="${pageContext.request.contextPath}/lib/easing/easing.min.js"></script>
            <script src="${pageContext.request.contextPath}/lib/owlcarousel/owl.carousel.min.js"></script>

            <!-- Template Javascript -->
            <script src="${pageContext.request.contextPath}/js/main.js"></script>
    </body>

</html>