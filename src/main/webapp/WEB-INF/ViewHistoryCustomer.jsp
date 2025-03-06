<%-- 
    Document   : ViewHistoryCustomer
    Created on : Feb 26, 2025, 3:58:49 PM
    Author     : TrangTrongKhoi-CE180958
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
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
            <div id="sidebar">
                <%@ include file="admin-sidebar.jsp" %>
            </div>
            <div id="main-content">
                <h1>View History Customer</h1>
                <table class="table table-bordered table-hover">
                    <thead class="table-light">
                        <tr class="text-center">
                            <th>ID</th>
                            <th>Order_Date</th>
                            <th>Total Amount</th>   
                            <th>Status</th>
                            <th>Products</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="order" items="${orderList}">
                            <tr>
                                <td>${order.order_id}</td>
                                <td>${order.oder_date}</td>
                                <td>${order.total_amount}</td>
                                <td>${order.status}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${empty orderProducts[order.order_id]}">
                                            No products
                                        </c:when>
                                        <c:otherwise>
                                            <c:forEach var="product" items="${productList[order.order_id]}">
                                                ${product.name}
                                            </c:forEach>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>
