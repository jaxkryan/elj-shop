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
                            <a href="home" class="table-title-link"><h2>Manage <b>Product</b></h2></a>
                        </div>
                        <div class="manager-nav-item">
                            <a href="provider" class="table-title-link"><h2>Manage <b>Provider</b></h2></a>
                        </div>
                        <div class="manager-nav-item">
                            <a href="order" class="table-title-link"><h2>Manage <b>Order</b></h2></a>
                        </div>
                        <!--Report-->
                        <div class="manager-nav-item">
                            <a href="${pageContext.request.contextPath}/manager/write-report?action=reply" class="table-title-link curent-page"><h2>Manage <b>Report</b></h2></a>
                        </div>
                    </div>
                </div>
                <table class="table table-striped table-hover">
                    <thead>
                        <tr>
                            <th>Title</th>
                            <th>Date</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${reports}" var="reports">
                            <tr>
                                <td>${reports.title}</td>
                                <td>${reports.writeDate}</td>
                                <td>                 
                                    <a href="#viewReport${reports.id}"  class="edit" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="View Report">&#xe8f4;</i></a>
                                    <a href="${pageContext.request.contextPath}/manager/delete-report?reportId=${reports.id}" class="delete" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="Delete">&#xE872;</i></a>
                                </td>
                            </tr>
                            <!--View and reply report-->
                        <div id="viewReport${reports.id}" class="modal fade">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <form action="write-report?action=reply-staff" method="POST">
                                        <div class="modal-header">						
                                            <h4 class="modal-title">View Report</h4>
                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                        </div>
                                        <div class="modal-body">					
                                            <div class="form-group">
                                                <label>Staff Sent</label>                                               
                                                <c:forEach items="${staff}" var="staff">
                                                    <c:if test="${Integer.parseInt(reports.storageStaffId) == Integer.parseInt(staff.id)}">
                                                        <div>${staff.firstName} ${staff.lastName}</div>
                                                        <input type ="hidden" name ="staffId" value="${staff.id}">
                                                    </c:if>
                                                </c:forEach>
                                            </div>
                                            <div class="form-group">
                                                <label>Title</label>
                                                <input name ="title" class="form-control" value ="${reports.title}" readonly>
                                            </div>
                                            <div class="form-group">
                                                <label>Content</label>
                                                <input type="content" class="form-control" value ="${reports.content}" readonly>
                                            </div>	
                                            <input type ="hidden" name="reportId" value ="${reports.id}">
                                            <c:if test="${reports.readStatus != true}">
                                                <div class="form-group">
                                                    <label>Reply</label>
                                                    <textarea type="text" name="reply-content" class="form-control" value="Your reply..." required rows="5" cols="33" maxlength="10000"></textarea>
                                                    <div class="modal-footer">
                                                        <input type="submit" class="btn btn-success" value="Send reply">
                                                        <input type="button" class="btn btn-default" data-dismiss="modal" value="Close">
                                                    </div>
                                                </div>
                                            </c:if>	
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <!-- Add Modal HTML -->
        <div id="addReportModal" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form action="${pageContext.request.contextPath}/manager/write-report?action=manager-add" method="post">
                        <div class="modal-header">						
                            <h4 class="modal-title">Write message</h4>
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        </div>
                        <div class="modal-body">					
                            <div class="form-group">
                                <label>Staff name</label>
                                <select name="name" class="form-select">
                                    <c:forEach items="${staff}" var="staff">
                                        <option value="${staff.id}">${staff.firstName} ${staff.lastName}</option>
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