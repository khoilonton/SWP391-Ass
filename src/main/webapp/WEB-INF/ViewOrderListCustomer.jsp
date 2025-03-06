<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>My Orders</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <style>
            .order-container {
                margin-top: 30px;
            }
            .order-header {
                background-color: #f8f9fa;
                padding: 15px;
                margin-bottom: 10px;
                border-radius: 5px;
            }
            .order-table {
                margin-top: 20px;
            }
            .order-table th, .order-table td {
                vertical-align: middle;
            }
            .order-img {
                max-width: 100px;
            }
        </style>
    </head>
    <body>

        <div class="container order-container">
            <h2 class="text-center mb-4">Your Order</h2>

            <c:if test="${not empty orders}">
                <c:forEach var="order" items="${orders}">
                    <div class="order-header">
                        <h3>Order ID: ${order.order_id} - Date Order: ${order.oder_date} - Status: ${order.status}</h3>
                        <p>Total: ${order.total_amount}</p>
                    </div>

                    <table class="table table-bordered order-table">
                        <thead class="thead-dark">
                            <tr>
                                <th>Name product</th>
                                <th>Picture</th>
                                <th>Quantity</th>
                                <th>Price</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="detail" items="${order.orderDetails}">
                                <tr>
                                    <td>${detail.product.name}</td>
                                    <td><img src="images/${detail.product.img}" class="order-img" alt="${detail.product.name}"></td>
                                    <td>${detail.quantity}</td>
                                    <td>${detail.unit_price}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:forEach>
            </c:if>

            <c:if test="${empty orders}">
                <p class="alert alert-warning">You have no order.</p>
            </c:if>
            <a href="login" class="btn btn-secondary">Back</a>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
