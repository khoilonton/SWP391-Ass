<%-- 
    Document   : ViewPromotion
    Created on : Feb 5, 2025, 3:09:39 PM
    Author     : TrangTrongKhoi-CE180958
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import="java.util.*" %>

<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <title>Promotion Manager</title>
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
            .badge {
                margin-right: 3px;
            }
            .btn-action {
                display: flex;
                gap: 5px;
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
                <h1>Promotion Manager</h1>

                <div class="d-flex justify-content-between mb-3">
                    <a href="AddPromotion" class="btn btn-success">➕ Add Promotion</a>
                </div>

                <table class="table table-bordered table-hover">
                    <thead class="table-light">
                        <tr class="text-center">
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
                                <td class="text-center">${proList.promo_id}</td>
                                <td>${proList.name}</td>
                                <td class="text-center">${proList.start_date}</td>
                                <td class="text-center">${proList.end_date}</td>
                                <td>${proList.description}</td>
                                <td>

                                    <c:forEach var="product" items="${promoProduct[proList.promo_id]}">
                                        <span class="badge bg-primary">${product.name}</span>
                                    </c:forEach>
                                </td>
                                <td class="text-center">${proList.percent}%</td>
                                <td class="text-center btn-action">
                                    <a href="EditPromotion?id=${proList.promo_id}" class="btn btn-warning btn-sm">✏️ Edit</a>
                                    <a href="DeletePromotion?id=${proList.promo_id}" class="btn btn-danger btn-sm" onclick="return confirm('Are you sure you want to delete this promotion?');">🗑️ Delete</a>
                                </td>
                            </tr>
                        </c:forEach>
                        <c:if test="${empty promoList}">
                            <tr>
                                <td colspan="8" class="text-center text-muted">No promotions available</td>
                            </tr>
                        </c:if>
                    </tbody>
                </table>

                <div class="d-flex justify-content-between">
                    <div class="text-center">
                        <ul class="pagination">
                            <li class="page-item <c:if test="${currentPage == 1}">disabled</c:if>">
                                <a class="page-link" href="PromotionManager?page=${currentPage - 1}">Previous</a>
                            </li>

                            <c:forEach var="i" begin="1" end="${totalPages}">
                                <li class="page-item <c:if test="${i == currentPage}">active</c:if>">
                                    <a class="page-link" href="PromotionManager?page=${i}">${i}</a>
                                </li>
                            </c:forEach>
                            <li class="page-item <c:if test="${currentPage == totalPages}">disabled</c:if>">
                                <a class="page-link" href="PromotionManager?page=${currentPage + 1}">Next</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>

    </body>
</html>
