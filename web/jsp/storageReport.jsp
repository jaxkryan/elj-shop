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
<!--                <a style="margin-right:81%" href="${pageContext.request.contextPath}/storage-staff/home" class="btn btn-primary">Back</a>-->
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
                        <div class="manager-nav-item" style="width: 21%">
                            <a href="${pageContext.request.contextPath}/storage-staff/home" class="table-title-link "> <h2>Manage <b>Product</b></h2></a>
                        </div>

                        <div class="manager-nav-item" style="width: 21%">
                            <a href="${pageContext.request.contextPath}/storage-staff/update-order-status" class="table-title-link"><h2>Manage <b>Order</b></h2></a>
                        </div>

                        <div class="manager-nav-item" style="width: 16%">
                            <!--Report-->
                            <a href="${pageContext.request.contextPath}/storage-staff/write-report?action=view" class="table-title-link curent-page"><h2><b>Report</h2></b></a>
                        </div>
                        <div class="manager-nav-item" style="width: 42%">
                            <a href="#addReportModal"  class="btn btn-success" data-toggle="modal"><i class="material-icons">&#xE147;</i> <span>Write report</span></a>					
                        </div>
                    </div>
                </div> 
                <table class="table table-striped table-hover">
                    <thead>
                        <tr>
                            <th class="col-xs-7 text-left" style="padding-right: 200px">Title</th>
                            <th class="col-xs-3 text-left" style="padding-right: 500px">Date</th>
                            <th class="col-xs-2 text-left">Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${reports}" var="reports">
                            <tr>
                                <c:if test="${storageStaffId == reports.storageStaffId}">
                                    <td class="text-left">${reports.title}</td>
                                    <td class="text-left">${reports.writeDate}</td>
                                    <td class="text-left">                 
                                        <a href="#viewReport${reports.id}"  class="edit" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="View Report">&#xe8f4;</i></a>
                                        <a href="${pageContext.request.contextPath}/storage-staff/delete-report?reportId=${reports.id}" class="delete" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="Delete">&#xE872;</i></a>
                                    </td>
                                </c:if>
                                <!--View report-->
                        <div id="viewReport${reports.id}" class="modal fade">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <form action="${pageContext.request.contextPath}/storage-staff/write-report?action=view" method="POST">
                                        <div class="modal-header">						
                                            <h4 class="modal-title">View Report</h4>
                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                        </div>
                                        <div class="modal-body">					
                                            <div class="form-group">
                                                <label>Manager Received</label>                                               
                                                <c:forEach items="${managers}" var="managers">
                                                    <c:if test="${Integer.parseInt(reports.managerId) == Integer.parseInt(managers.id)}">
                                                        <div>${managers.firstName} ${managers.lastName}</div>
                                                        <input type ="hidden" name ="managersId" value="${managers.id}">
<!--                                                        <input type ="hidden" name ="managerName" value ="${managers.firstName} ${managers.lastName}">-->
                                                    </c:if>
                                                </c:forEach>

                                            </div>
                                            <div class="form-group">
                                                <label>Title</label>
                                                <input type="email" class="form-control" value ="${reports.title}" readonly>
                                            </div>
                                            <div class="form-group">
                                                <label>Content</label>
                                                <input type="email" class="form-control" value ="${reports.content}" readonly>
                                            </div>					
                                        </div>
                                        <div class="modal-footer">
                                            <input type="button" class="btn btn-default" data-dismiss="modal" value="Close">
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
                                <input name="title" type="text" class="form-control" maxlength="100" required>
                            </div>
                            <div class="form-group">
                                <label>Content</label>
                                <textarea name="content" type="text" class="form-control" required rows="5" cols="33" maxlength="1000"></textarea>
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
