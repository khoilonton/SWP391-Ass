<%-- 
    Document   : ViewOrderDetailAdmin
    Created on : Mar 3, 2025, 10:49:27 PM
    Author     : TrangTrongKhoi-CE180958
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Chi tiết đơn hàng</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    </head>
    <body>
        <div class="container mt-4">
            <h2 class="mb-4">Order Detail</h2>
            <table class="table table-bordered">
                <thead class="table-light">
                    <tr>
                        <th>Product</th>
                        <th>Img</th>
                        <th>Quantity</th>
                        <th>Price</th>
                        <th>Customer Name</th>
                        <th>Total Amount</th>
                        <th>Payment</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="order" items="${orderDetails}">
                        <tr>
                            <td>${order.product.name}</td>
                            <td><img src="<c:url value='/img/${order.product.img}' />" width="50" height="50" alt="Product Image"></td>
                            <td>${order.quantity}</td>
                            <td>${order.unit_price}</td>
                            <td>${order.customer.name}</td>
                            <td>${order.total_amount}</td>
                            <td>${order.payment.method}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <a href="ViewOrderListAdmin" class="btn btn-secondary">Back</a>
        </div>
    </body>
</html>