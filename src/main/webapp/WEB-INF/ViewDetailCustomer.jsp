<%-- 
    Document   : ViewCustomer
    Created on : Feb 16, 2025, 3:32:30 PM
    Author     : TrangTrongKhoi-CE180958
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Customer Detail List</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body class="container py-4">
        <h1 class="text-center mb-4">Customer Detail List</h1>
        <table class="table table-hover table-bordered">
            <thead class="table-light">
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Phone</th>
                    <th>Address</th>
                    <th>Total Invoice</th>
                    <th>Total Amount</th>
                </tr>
            </thead>
            <tbody>
                     <tr>
                    <td>${customer.cus_id}</td>
                    <td>${customer.name}</td>
                    <td>${customer.phone}</td>
                    <td>${customer.address}</td>
                </tr>                
        </tbody>
        <a href="CustomerManager" class="btn btn-primary">Back</a>
    </table>
</body>
</html>