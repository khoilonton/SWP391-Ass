<%-- 
    Document   : addStaff
    Created on : Mar 2, 2025, 4:48:26 PM
    Author     : TrangTrongKhoi-CE180958
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Add Staff</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <div class="container mt-5">
            <h2>Add New Staff</h2>
            <form action="addStaffControl" method="post">
                <div class="mb-3">
                    <label for="staffID" class="form-label">Staff ID</label>
                    <input type="number" name="staffID" id="staffID" class="form-control" required>
                </div>
                <div class="mb-3">
                    <label for="fullName" class="form-label">Full Name</label>
                    <input type="text" name="fullName" id="fullName" class="form-control" required>
                </div>
                <div class="mb-3">
                    <label for="address" class="form-label">Address</label>
                    <input type="text" name="address" id="address" class="form-control" required>
                </div>
                <div class="mb-3">
                    <label for="phone" class="form-label">Phone</label>
                    <input type="text" name="phone" id="phone" class="form-control" required>
                </div>
                <div class="mb-3">
                    <label for="dateJoin" class="form-label">Date Join</label>
                    <input type="date" name="dateJoin" id="dateJoin" class="form-control" required>
                </div>
                <div class="mb-3">
                    <label for="salary" class="form-label">Salary</label>
                    <input type="number" name="salary" id="salary" class="form-control" required>
                </div>
                <div class="mb-3">
                    <label for="username" class="form-label">Username</label>
                    <input type="text" name="username" id="username" class="form-control" required>
                </div>
                <div class="mb-3">
                    <label for="password" class="form-label">Password</label>
                    <input type="password" name="password" id="password" class="form-control" required>
                </div>

                <div class="mb-3">
                    <label for="userID" class="form-label">User ID</label>
                    <input type="number" name="userID" id="userID" class="form-control" required>
                </div>
                <button type="submit" class="btn btn-success">Add Staff</button>
                <a href="ViewStaffList" class="btn btn-secondary">Cancel</a>
            </form>
        </div>
    </body>
</html>
