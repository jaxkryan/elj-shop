<%-- 
    Document   : updateOrderPage
    Created on : Oct 13, 2023, 2:00:16 PM
    Author     : LENOVO
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initia   l-scale=1">
        <title>Bootstrap CRUD Data Table for Database with Modal Form</title>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <link href="css/manager.css" rel="stylesheet" type="text/css"/>
        <style>
            img{
                width: 200px;
                height: 120px;
            }
        </style>
    <body>
        <div class="container">
            <div class="table-wrapper">
                <div class="table-title">
                    <div class="row">
                        <div class="col-sm-6">
                            <h2>Edit <b>Order</b></h2>
                        </div>
                        <div class="col-sm-6">
                        </div>
                    </div>
                </div>
            </div>
            <div id="editEmployeeModal">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <form action="home" method="POST">
                            <div class="modal-header">						
                                <h4 class="modal-title">Edit Order</h4>
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            </div>
                            <div class="modal-body">					
                                <div class="form-group">
                                    <label>ID</label>
                                    <input value="${updateOrder.id}" name="id" type="text" class="form-control" readonly required>
                                </div>
                                <div class="form-group">
                                    <label>CustomerID</label>
                                    <input value="${updateOrder.customerId}" name="customerId" type="text" class="form-control" readonly required >
                                </div>
                                <div class="form-group">
                                    <label>Receiver</label>
                                    <input value="${updateOrder.receiver}" name="receiver" type="text" class="form-control" readonly required>
                                </div>
                                <div class="form-group">
                                    <label>ShipStreet</label>
                                    <input value="${updateOrder.shipStreet}" name="shipStreet" class="form-control" readonly required>
                                </div>
                                <div class="form-group">
                                    <label>ShipCity</label>
                                    <input value="${updateOrder.shipCity}" name="shipCity" class="form-control" readonly required>
                                </div><!-- comment -->    
                                <div class="form-group">
                                    <label>ShipProvince</label>
                                    <input value="${updateOrder.shipProvince}" name="shipProvince" class="form-control" readonly required>
                                </div>
                                <div class="form-group">
                                    <label>ShipCountry</label>
                                    <input value="${updateOrder.shipCountry}" name="shipCountry" class="form-control" readonly required>
                                </div>
                                <div class="form-group">
                                    <label>ShipEmail</label>
                                    <input value="${updateOrder.shipEmail}" name="shipEmail" class="form-control" readonly required>
                                </div>
                                <div class="form-group">
                                    <label>ShipPhone</label>
                                    <input value="${updateOrder.shipPhone}" name="shipPhone" class="form-control" readonly required>
                                </div>

                                <div class="form-group">
                                    <label>Status</label>
                                    <select name="status" class="form-control" aria-label="Default select example">
                                        <c:forEach items="${status}" var="statust">
                                            <c:choose>
                                                <c:when test="${updateOrder.status eq statust}">
                                                    <option value="${statust}" selected>${statust}</option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option value="${statust}">${statust}</option>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                    </select>
                                </div>

                                <div class="form-group">
                                    <label>CreatedTime</label>
                                    <input value="${updateOrder.createdTime}" name="createdTime" class="form-control" readonly required>
                                </div>
                                <div class="form-group">
                                    <label>TotalPrice</label>
                                    <input value="${updateOrder.totalPrice}" name="totalprice" class="form-control" readonly required>
                                </div>                               
                                <input type="hidden" name="go" value="update">
                            </div>
                            <div class="modal-footer">
                                <input type="submit" class="btn btn-success" value="Edit">
                            </div>
                        </form>
                    </div>
                </div>
            </div>

        </div>


        <script src="js/manager.js" type="text/javascript"></script>
    </body>
</html>
