<%-- 
    Document   : CommentManager
    Created on : Feb 6, 2025, 9:18:11 AM
    Author     : TrangTrongKhoi-CE180958
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Comment Management</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    </head>
    <body>
        <div>
            <header class="text-center mb-4">
                <h2>Comment Management</h2>
            </header>
            <table class="table table-bordered table-striped text-center">
                <thead class="table-dark">
                    <tr>
                        <th>ID</th>
                        <th>Content</th>
                        <th>Customer Name</th>
                        <th>Order ID</th>
                        <th>Status</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="fee" items="${feeList}">
                        <tr>
                            <td>${fee.feeback_id}</td>
                            <td>${fee.content}</td>
                            <td>${fee.cus.name}</td>
                            <td>${fee.order_id}</td>             
                            <td>
                                <c:choose>
                                    <c:when test="${fee.status eq 'Not approved'}">
                                        <span class="badge bg-danger">Not approved</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="badge bg-success">Approved</span>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>                             
                                <form method="POST" action="${pageContext.request.contextPath}/FeebackManager">
                                    <input type="hidden" name="action" value="updateStatus">
                                    <input type="hidden" name="feedbackID" value="${fee.feeback_id}">
                                    <c:choose>
                                        <c:when test="${fee.status eq 'Not approved'}">
                                            <input type="hidden" name="status" value="Approved">
                                            <button type="submit" class="btn btn-success">Approve</button>
                                        </c:when>
                                        <c:otherwise>
                                            <input type="hidden" name="status" value="Not approved">
                                            <button type="submit" class="btn btn-warning">Unapprove</button>
                                        </c:otherwise>
                                    </c:choose>
                                </form>
                                <form method="POST" action="${pageContext.request.contextPath}/FeebackManager">
                                    <input type="hidden" name="action" value="delete">
                                    <input type="hidden" name="feedbackID" value="${fee.feeback_id}">
                                    <button type="submit" class="btn btn-danger" onclick="return confirm('Are you sure you want to delete this feedback?');">
                                        Delete
                                    </button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

    </body>
</html>
