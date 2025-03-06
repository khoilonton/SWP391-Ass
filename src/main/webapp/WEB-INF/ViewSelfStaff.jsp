<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Staff Profile</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h2>Staff Profile</h2>
        <c:if test="${not empty errorMessage}">
            <div class="alert alert-danger">${errorMessage}</div>
        </c:if>
        <c:if test="${not empty message}">
            <div class="alert alert-success">${message}</div>
        </c:if>
        <form action="UpdateStaffServlet" method="POST" enctype="multipart/form-data">
            <div class="mb-3">
                <label class="form-label">Full Name</label>
                <input type="text" class="form-control" name="name" value="${staff.name}" disabled>
            </div>
            <div class="mb-3">
                <label class="form-label">Address</label>
                <input type="text" class="form-control" name="address" value="${staff.address}" required>
            </div>
            <div class="mb-3">
                <label class="form-label">Phone</label>
                <input type="number" class="form-control" name="phone" value="${staff.phone}" required>
            </div>
            <div class="mb-3">
                <label class="form-label">Date Join</label>
                <input type="date" class="form-control" name="dateJoin" value="${staff.dateJoin}" disabled>
            </div>
            <div class="mb-3">
                <label class="form-label">Salary</label>
                <input type="number" class="form-control" name="salary" value="${staff.salary}" disabled>
            </div>
            <div class="mb-3">
                <label class="form-label">Profile Image</label>
                <input type="file" class="form-control" name="img">
                <c:if test="${not empty staff.img}">
                    <div class="mt-2">
                        <img src="public/assets/img/${staff.img}" alt="Staff Image" width="150">
                    </div>
                </c:if>
            </div>
            <button type="submit" class="btn btn-primary">Update</button>
        </form>
        <a href="dashBoardStaff" class="btn btn-secondary">Back</a>
    </div>
</body>
</html>
