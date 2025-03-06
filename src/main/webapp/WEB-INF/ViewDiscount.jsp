<%-- 
    Document   : ViewDiscount
    Created on : Feb 5, 2025, 4:10:09 PM
    Author     : TrangTrongKhoi-CE180958
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Discount Manager</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa;
        }
        .wrapper {
            display: flex;
            min-height: 100vh;
        }
        #sidebar {
            width: 250px;
            background: #343a40;
            color: white;
            padding: 20px;
        }
        #main-content {
            flex-grow: 1;
            padding: 30px;
            background-color: white;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            margin: 20px;
        }
        h1 {
            text-align: center;
            margin-bottom: 20px;
        }
        .table-hover tbody tr:hover {
            background-color: #f1f1f1;
        }
        .btn-action {
            display: flex;
            gap: 5px;
            justify-content: center;
        }
    </style>
</head>
<body>

<div class="wrapper">
    <!-- Sidebar -->
    <div id="sidebar">
        <%@ include file="admin-sidebar.jsp" %>
    </div>

    <!-- Nội dung chính -->
    <div id="main-content">
        <h1>Discount Manager</h1>
        
        <div class="d-flex justify-content-between mb-3">
            <a href="AddDiscount" class="btn btn-success">➕ Add Discount</a>
        </div>

        <table class="table table-bordered table-hover">
            <thead class="table-light">
                <tr class="text-center">
                    <th>ID</th>
                    <th>Min Order Value</th>
                    <th>Expiry Date</th>
                    <th>Code</th>
                    <th>Percent</th>
                    <th>Used Count</th>
                    <th>Limit Code</th>
                    <th>Start Date</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="dis" items="${disList}">
                    <tr>
                        <td class="text-center">${dis.dis_id}</td>
                        <td class="text-center">${dis.minOrder}</td>
                        <td class="text-center">${dis.expiry_date}</td>
                        <td class="text-center">${dis.code}</td>
                        <td class="text-center">${dis.percent}%</td>
                        <td class="text-center">${dis.used_count}</td>
                        <td class="text-center">${dis.limit_code}</td>
                        <td class="text-center">${dis.start_date}</td>
                        <td class="btn-action">
                            <a href="EditDiscount?id=${dis.dis_id}" class="btn btn-warning btn-sm">✏️ Edit</a>
                            <a href="DeleteDiscount?id=${dis.dis_id}" class="btn btn-danger btn-sm" onclick="return confirm('Are you sure you want to delete this discount?');">🗑️ Delete</a>
                        </td>
                    </tr>
                </c:forEach>
                <c:if test="${empty disList}">
                    <tr>
                        <td colspan="9" class="text-center text-muted">No discounts available</td>
                    </tr>
                </c:if>
            </tbody>
        </table>
    </div>
</div>

</body>
</html>