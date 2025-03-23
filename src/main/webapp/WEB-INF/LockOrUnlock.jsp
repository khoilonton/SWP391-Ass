<%-- 
    Document   : LockOrUnlock
    Created on : Feb 15, 2025, 11:36:30 AM
    Author     : TrangTrongKhoi-CE180958
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Product List</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <style>
            body {
                background-color: #f8f9fa;
            }
            .wrapper {
                display: flex;
            }
            .sidebar {
                width: 250px;
                background: #343a40;
                min-height: 100vh;
                padding: 20px;
            }
            .main-content {
                flex-grow: 1;
                padding: 20px;
            }
            .container {
                background-color: #ffffff;
                padding: 20px;
                border-radius: 10px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            }
            h2 {
                font-weight: bold;
                color: #343a40;
            }
            .btn {
                border-radius: 5px;
            }
            .table-hover tbody tr:hover {
                background-color: #f1f3f5;
            }



        </style>
    </head>
    <body>
        <div class="wrapper">
            <%@ include file="admin-sidebar.jsp" %>
            <div class="container mt-5">
                <div class="header">
                    <h2 class="mb-4">Account Management</h2>
                </div>

                <c:if test="${not empty message}">
                    <div class="alert alert-info alert-dismissible fade show" role="alert">
                        ${message}
                        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                    </div>
                </c:if>

                <table class="table table-bordered table-striped text-center">
                    <thead class="table-dark">
                        <tr>
                            <th>ID</th>
                            <th>Email</th>
                            <th>Role</th>
                            <th>Name</th>
                            <th>Status</th>
                            <th>Action</th>
                        </tr>      
                    </thead>
                    <tbody>
                        <c:forEach var="acc" items="${accList}">
                            <tr>
                                <td>${acc.userID}</td>
                                <td>${acc.email}</td>
                                <td>${acc.role}</td>
                                <td>${acc.userName}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${acc.status eq 'inactive'}">
                                            <span class="badge bg-danger">Inactive</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="badge bg-success">Active</span>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <form method="POST" action="AccountManager">
                                        <input type="hidden" name="userId" value="${acc.userID}">
                                        <c:choose>
                                            <c:when test="${acc.status eq 'inactive'}">
                                                <input type="hidden" name="status" value="active">
                                                <button type="submit" class="btn btn-success">Unlock</button>
                                            </c:when>
                                            <c:otherwise>
                                                <input type="hidden" name="status" value="inactive">
                                                <button type="submit" class="btn btn-danger">Lock</button>
                                            </c:otherwise>
                                        </c:choose>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

                <div class="d-flex justify-content-between">
                    <ul class="pagination">
                        <!-- Nút Previous -->
                        <li class="page-item <c:if test="${currentPage == 1}">disabled</c:if>">
                            <a class="page-link" href="<c:if test='${currentPage > 1}'>AccountManager?page=${currentPage - 1}</c:if>">Previous</a>
                            </li>

                            <!-- Các số trang -->
                        <c:forEach var="i" begin="1" end="${totalPages}">
                            <li class="page-item <c:if test="${i == currentPage}">active</c:if>">
                                <a class="page-link" href="AccountManager?page=${i}">${i}</a>
                            </li>
                        </c:forEach>

                        <!-- Nút Next -->
                        <li class="page-item <c:if test="${currentPage == totalPages}">disabled</c:if>">
                            <a class="page-link" href="<c:if test='${currentPage < totalPages}'>AccountManager?page=${currentPage + 1}</c:if>">Next</a>
                        </li>
                    </ul>
                </div>



                </body>
                </html>
