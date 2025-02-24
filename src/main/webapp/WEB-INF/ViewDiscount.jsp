<%-- 
    Document   : ViewDiscount
    Created on : Feb 5, 2025, 4:10:09 PM
    Author     : TrangTrongKhoi-CE180958
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Discount Manager</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body class="container mt-4">
        <h1 class="text-center">Discount Manager</h1>
        <a href="AddDiscount" class="btn btn-success mb-3">➕ Add discount</a>
        <table class="table table-bordered table-hover">
            <thead class="table-light">
                <tr>
                    <th>ID</th>
                    <th>Min Order Value</th>
                    <th>Expiry date</th>
                    <th>Code</th>
                    <th>Percent</th>
                    <th>Used Count</th>
                    <th>Limit code</th>
                    <th>Start date</th>
                    <th>Action</th>

                </tr>
            </thead>
            <tbody>
 
                <c:forEach var="dis" items="${disList}">
                    <tr>
                        <td> ${dis.dis_id}</td>
                        <td> ${dis.minOrder}</td>
                        <td> ${dis.expiry_date}</td>
                        <td> ${dis.percent}</td>
                        <td> ${dis.code}</td>
                        <td> ${dis.used_count}</td>
                        <td> ${dis.limit_code}</td>
                        <td> ${dis.start_date}</td>
                        <td> 
                            <a href="EditDiscount?id=${dis.dis_id}" class="btn btn-warning btn-sm">✏ Edit</a>
                            <a href="DeleteDiscount?id=${dis.dis_id}" class="btn btn-danger btn-sm" onclick="return confirm('Are you sure you want to delete ?');">🗑 Delete</a>
                        </td>

                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </body>
</html>