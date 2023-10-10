<%-- 
    Document   : InvalidAccount
    Created on : Jul 11, 2023, 8:39:04 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

        <style>
            body {
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
            }

            .content {
                text-align: center;
            }

            .btn-block {
                width: 200px;
                margin: 20px auto;
            }
        </style>
    </head>
    <body>
        <div class="content">
            <a>Some error occurred</a>
            <a href="${pageContext.request.contextPath}/home" class="btn btn-dark rounded-pill py-2 btn-block"> Comfirm </a>
        </div>
    </body>
</html>
