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
            .filter-form {
                margin-bottom: 20px;
                padding: 15px;
                background-color: #f8f9fa;
                border-radius: 5px;
            }
            .order-table {
                margin-top: 20px;
            }
            .order-table th, .order-table td {
                vertical-align: middle;
            }
            .order-btn {
                width: 100px;
                height: 35px;
            }
        </style>
    </head>
    <body>
        <div class="container order-container">
            <h2 class="text-center mb-4">List All Orders</h2>

            <form action="viewOrderStaffListServlet" method="GET" class="filter-form">
                <div class="row">
                    <div class="col-md-4">
                        <label for="status" class="form-label">Status</label>
                        <select name="status" id="status" class="form-select">
                            <option value="">All</option>
                            <option value="Pending">Pending</option>
                            <option value="Delivered">Delivered</option>
                            <option value="Cancelled">Cancelled</option>
                        </select>
                    </div>
                    <div class="col-md-4">
                        <label for="date" class="form-label">Date</label>
                        <input type="date" name="date" id="date" class="form-control">
                    </div>
                    <div class="col-md-4">
                        <label for="ordered" class="form-label">Ordered By</label>
                        <input type="text" name="ordered" id="ordered" class="form-control" placeholder="Ordered">
                    </div>
                </div>
                <button type="submit" class="btn btn-primary mt-3">Filter</button>
            </form>

            <c:if test="${not empty orders}">
                <c:forEach var="order" items="${orders}">
                    <table class="table table-bordered order-table">
                        <thead class="thead-dark">
                            <tr>
                                <th>Order ID</th>
                                <th>Order Date</th>
                                <th>Total Amount</th>
                                <th>Cancellation Reason</th>
                                <th>Status</th>
                                <th>Customer ID</th>
                                <th>Discount ID</th>
                                <th>Promo ID</th>
                                <th>Used Discount</th>
                                <th>Save</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                        <form id="updateOrderForm_${order.order_id}" action="./UpdateOrderStaffServlet" method="POST">

                            <td>${order.order_id}</td>
                            <td>${order.oder_date}</td>
                            <td>${order.total_amount}</td>
                            <td>${order.cancell != null ? order.cancell : "N/A"}</td>
                            <td>
                                <select name="status" class="form-control">
                                    <option value="Delivered" <c:if test="${order.status == 'Delivered'}">selected</c:if>>Delivered</option>
                                    <option value="Cancelled" <c:if test="${order.status == 'Cancelled'}">selected</c:if>>Cancelled</option>
                                    <option value="Pending" <c:if test="${order.status == 'Pending'}">selected</c:if>>Pending</option>
                                    </select>
                                </td>
                                <td>${order.cus_id}</td>
                            <td>${order.getDisIdDisplay()}</td>
                            <td>${order.getPromoIdDisplay()}</td>
                            <td>${order.isUsedDiscount() ? "True" : "False"}</td>
                            <td>
                                <input type="hidden" name="orderID" value="${order.order_id}"/>
                                <button type="button" class="btn btn-success order-btn" onclick="confirmUpdate(${order.order_id})">Save</button>
                            </td>
                        </form>
                        </tr>
                        </tbody>
                    </table>
                </c:forEach>
            </c:if>

            <c:if test="${empty orders}">
                <p class="alert alert-warning">Không có đơn hàng nào.</p>
            </c:if>

            <a href="dashBoardStaff" class="btn btn-secondary">Back</a>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
        <script>
                                    function confirmUpdate(orderID) {
                                        if (confirm("Are you sure you want to update the status of this order?")) {
                                            document.getElementById('updateOrderForm_' + orderID).submit();
                                        }
                                    }
        </script>
    </body>
</html>
