
<%-- 
    Document   : ViewCustomer
    Created on : Feb 16, 2025, 11:36:30 AM
    Author     : TrangTrongKhoi-CE180958
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Customer List</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body class="container py-4">
        <h1 class="text-center">Customer List</h1>
        <table class="table table-bordered table-striped text-center">
            <thead class="table-dark">
                <tr>
                    <th>ID</th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="cus" items="${cusList}">
                    <tr>
                        <td class="text-center"> ${cus.cus_id}</td>
                        <td class="text-dark">
                            <a href="CustomerDetail?id=${cus.cus_id}" class="text-decoration-none text-dark">
                                ${cus.name}
                            </a>
                        </td>  
                    </tr>       
                </c:forEach>
            </tbody>
        </table>
    </body>
</html>

