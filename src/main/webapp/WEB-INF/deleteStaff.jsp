<%-- 
    Document   : deleteStaff
    Created on : Mar 2, 2025, 9:28:05 AM
    Author     : TrangTrongKhoi-CE180958
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="./resources/css/bootstrap.css"/>
        <script src="./resources/js/boostrap.min.js"></script>
    </head>
    <body>
        <form method="post" action="Delete" >
            <input type="hidden" name="cus_id" value="${data.id}">
            <label for="id">ID:</label>
            <span id="id">${data.id}</span><br><br>
            <label for="fullname">Full Name:</label>
            <span id="fullname">${data.name}</span><br><br>
            <label for="address">Address:</label>
            <span id="address">${data.address}</span><br><br>
            <input type="submit" value="Delete" />
        </form>
    </body>
</html>
