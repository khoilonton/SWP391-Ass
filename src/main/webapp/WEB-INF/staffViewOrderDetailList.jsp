<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Order Details</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <style>
            .order-table {
                margin-top: 20px;
            }
            .order-table th, .order-table td {
                vertical-align: middle;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h2 class="text-center mb-4">Order Details List</h2>

            <c:if test="${not empty orderDetails}">
                <table class="table table-bordered order-table">
                    <thead class="thead-dark">
                        <tr>
                            <th>Order Detail ID</th>
                            <th>Order ID</th>
                            <th>Product ID</th>
                            <th>Quantity</th>
                            <th>Unit Price</th>
                            <th>Total Amount</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="orderDetail" items="${orderDetails}">
                            <tr>
                                <td>${orderDetail.order_detail_id}</td>
                                <td>${orderDetail.order_id}</td>
                                <td>${orderDetail.pro_id}</td>
                                <td>${orderDetail.quantity}</td>
                                <td>${orderDetail.unit_price}</td>
                                <td>${orderDetail.total_amount}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>

            <c:if test="${empty orderDetails}">
                <p class="alert alert-warning">No order details available.</p>
            </c:if>

            <a href="dashBoardStaff" class="btn btn-secondary">Back to Dashboard</a>
        </div>

        <!-- Include Bootstrap JS -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
