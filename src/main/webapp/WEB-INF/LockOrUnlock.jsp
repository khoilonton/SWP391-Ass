<%-- 
    Document   : LockOrUnlock
    Created on : Feb 15, 2025, 11:36:30 AM
    Author     : TrangTrongKhoi-CE180958
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Account Management</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container mt-4">
    <header class="text-center mb-4">
        <h2>Account Management</h2>
    </header>

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
                        <form method="POST" action="${pageContext.request.contextPath}/AccountManager">
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
   

</body>
</html>
