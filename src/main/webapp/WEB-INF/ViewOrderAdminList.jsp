<%-- 
    Document   : ViewOrderAdminList
    Created on : Mar 3, 2025, 8:37:13 PM
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
                <h1>View Order Customer</h1>
                <table class="table table-bordered table-hover">
                    <thead class="table-light">
                        <tr class="text-center">
                            <th>ID</th>
                            <th>Order_Date</th>
                            <th>Total Amount</th>
                            <th>Status</th>
                            <th>Products</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="order" items="${orderList}">
                            <tr>
                                <td>
                                    <a href="OrderDetailAdmin?orderID=${order.order_id}" class="btn btn-link">${order.order_id}</a>
                                </td>
                                <td>${order.oder_date}</td>
                                <td>${order.total_amount}</td>
                                <td>${order.status}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${empty productList[order.order_id]}">
                                            No products
                                        </c:when>
                                        <c:otherwise>
                                            <c:forEach var="product" items="${productList[order.order_id]}">
                                                ${product.name}
                                            </c:forEach>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <form method="POST" action="ViewOrderListAdmin">
                                        <input type="hidden" name="orderID" value="${order.order_id}">
                                        <select name="status" class="form-select">
                                            <option value="Preparing" ${order.status eq 'Preparing' ? 'selected' : ''}>Preparing</option>
                                            <option value="Delivered" ${order.status eq 'Delivered' ? 'selected' : ''}>Delivered</option>
                                            <option value="Cancelled" ${order.status eq 'Cancelled' ? 'selected' : ''}>Cancelled</option>
                                        </select>
                                        <button type="submit" class="btn btn-primary mt-2">Update</button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <div class="d-flex justify-content-between mt-4">
    <nav>
        <ul class="pagination">
            <c:if test="${currentPage > 1}">
                <li class="page-item">
                    <a class="page-link" href="ViewOrderListAdmin?page=${currentPage - 1}">Previous</a>
                </li>
            </c:if>  
            <c:forEach var="i" begin="1" end="${totalPages}">
                <li class="page-item ${i == currentPage ? 'active' : ''}">
                    <a class="page-link" href="ViewOrderListAdmin?page=${i}">${i}</a>
                </li>
            </c:forEach>
            <c:if test="${currentPage < totalPages}">
                <li class="page-item">
                    <a class="page-link" href="ViewOrderListAdmin?page=${currentPage + 1}">Next</a>
                </li>
            </c:if>
        </ul>
    </nav>
</div>
            </div>
        </div>
            

    </body>
</html>