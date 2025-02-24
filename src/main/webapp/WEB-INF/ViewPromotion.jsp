<%-- 
    Document   : ViewPromotion
    Created on : Feb 5, 2025, 3:09:39 PM
    Author     : TrangTrongKhoi-CE180958
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<!DOCTYPE html>
<html>
     <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Promotion Manager</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    </head>
    <body class="container mt-4">
        <h1 class="text-center">Promotion Manager</h1>

        <a href="AddPromotion" class="btn btn-success mb-3">➕ Thêm Khuyến Mãi</a>
        <table class="table table-bordered table-striped">
            <thead class="table-light">
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Start Date</th>
                    <th>End Date</th>
                    <th>Description</th>
                    <th>Products</th>
                    <th>Percent</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="proList" items="${promoList}">
                    <tr>
                        <td>${proList.promo_id}</td>
                        <td>${proList.name}</td>
                        <td>${proList.start_date}</td>
                        <td>${proList.end_date}</td>
                        <td>${proList.description}</td>
                        <td>
                            <c:forEach var="product" items="${promoProduct[proList.promo_id]}">
                                ${product.name}<br>
                            </c:forEach>
                        </td>
                        <td>${proList.percent}</td>
                        <td>
                            <a href="EditPromotion?id=${proList.promo_id}" class="btn btn-primary">✏️ Edit</a>
                            <a href="DeletePromotion?id=${proList.promo_id}" class="btn btn-danger">🗑️ Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </body>
</html>


