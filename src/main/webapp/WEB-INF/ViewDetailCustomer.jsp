<%-- 
    Document   : ViewCustomer
    Created on : Feb 16, 2025, 3:32:30 PM
    Author     : TrangTrongKhoi-CE180958
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Customer Detail List</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <style>
            #main-content {
                flex-grow: 1;
                padding: 20px;
            }
        </style>
    </head>
    <body>
        <div class="container-fluid d-flex">
            <%@ include file="admin-sidebar.jsp" %>
            <div id="main-content">
                <h1 class="text-center mb-4">Customer Detail List</h1>
                <table class="table table-hover table-bordered">
                    <thead class="table-light">
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Phone</th>
                            <th>Address</th>
                            <th>Total Invoice</th>
                            <th>Total Amount</th>
                            <th>Account Status</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="customer" items="${cusList}">
                            <tr>
                                <td>
                                    <a href="ViewHistoryCustomer?cus_id=${customer.cus_id}" class="text-decoration-none">
                                        ${customer.cus_id}
                                    </a>
                                </td>

                                <td>${customer.name}</td>
                                <td>${customer.phone}</td>
                                <td>${customer.address}</td>
                                <td>${customer.totalInvoices}</td>
                                <td>${customer.totalAmount}</td>
                                <td>
                                    <c:forEach var="account" items="${accList[customer.cus_id]}">
                                        <c:choose>
                                            <c:when test="${account.status eq 'inactive'}">
                                                <span class="badge bg-danger">Inactive</span>
                                            </c:when>
                                            <c:otherwise>
                                                <span class="badge bg-success">Active</span>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </td>
                                <td>
                                    <c:forEach var="account" items="${accList[customer.cus_id]}">
                                        <form method="POST" action="CustomerManager">
                                            <input type="hidden" name="userId" value="${account.userID}">
                                            <c:choose>
                                                <c:when test="${account.status eq 'inactive'}">
                                                    <input type="hidden" name="status" value="active">
                                                    <button type="submit" class="btn btn-success">Unlock</button>
                                                </c:when>
                                                <c:otherwise>
                                                    <input type="hidden" name="status" value="inactive">
                                                    <button type="submit" class="btn btn-danger">Lock</button>
                                                </c:otherwise>
                                            </c:choose>
                                        </form>
                                    </c:forEach>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>