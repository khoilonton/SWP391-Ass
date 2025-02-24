<%-- 
    Document   : DeletePromotion
    Created on : Feb 5, 2025, 3:10:22 PM
    Author     : TrangTrongKhoi-CE180958
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Delete Promotion</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="d-flex justify-content-center align-items-center vh-100 bg-light">

<div class="card text-center p-4 shadow-lg" style="max-width: 450px;">
    <h2 class="text-danger">🔔 Confirm Deletion</h2>

    <c:if test="${not empty errorMessage}">
        <div class="alert alert-warning">${errorMessage}</div>
    </c:if>

    <c:choose>
        <c:when test="${not empty promotion}">
            <p>Are you sure you want to delete the promotion:</p>
            <h4 class="text-primary">${promotion.promo_name}</h4>

            <form action="DeletePromotion" method="post">
                <input type="hidden" name="id" value="${promotion.promo_id}">
                <div class="mt-3">
                    <button type="submit" class="btn btn-danger">🗑 Delete</button>
                    <a href="PromotionManager" class="btn btn-secondary">❌ Cancel</a>
                </div>
            </form>
        </c:when>
        <c:otherwise>
            <div class="alert alert-danger">⚠️ Error: Promotion not found.</div>
            <a href="PromotionManager" class="btn btn-primary mt-3">⬅️ Back to List</a>
        </c:otherwise>
    </c:choose>
</div>

</body>
</html>


