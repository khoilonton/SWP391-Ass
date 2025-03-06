<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Delete Discount</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="d-flex justify-content-center align-items-center vh-100 bg-light">

<div class="card text-center p-4 shadow-lg" style="max-width: 400px;">
    <h2 class="text-danger">Confirm Deletion</h2>

    <c:if test="${not empty successMessage}">
        <div class="alert alert-success">${successMessage}</div>
    </c:if>

    <c:if test="${not empty errorMessage}">
        <div class="alert alert-danger">${errorMessage}</div>
    </c:if>

    <c:if test="${not empty discount}">
        <p>Are you sure you want to delete the discount <strong>${discount.dis_id}</strong>?</p>
        <form action="DeleteDiscount" method="POST">
            <input type="hidden" name="id" value="${discount.dis_id}">
            <div class="mt-3">
                <button type="submit" class="btn btn-danger">🗑 Delete</button>
                <a href="DiscountManager" class="btn btn-secondary">❌ Cancel</a>
            </div>
        </form>
    </c:if>
</div>

</body>
</html>
