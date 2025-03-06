<%-- 
    Document   : updateStaff
    Created on : Mar 2, 2025, 9:30:24 AM
    Author     : TrangTrongKhoi-CE180958
--%>

<%@page import="Model.Account"%>
<%@page import="DAO.AccountDAO"%>
<%@page import="Model.Staff"%>
<%@page import="DAO.StaffDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Update Staff</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h2 class="mb-4">Update Staff Information</h2>

        <%
            int staffID = Integer.parseInt(request.getParameter("staffID"));
            StaffDAO staffDAO = new StaffDAO();
            Staff staff = staffDAO.getStaffByID(staffID);

            // Lấy thông tin tài khoản của staff từ AccountDAO
            AccountDAO accountDAO = new AccountDAO();
            Account account = accountDAO.getAccountByUserID(staff.getUserID());
        %>

        <form action="updateStaffControl" method="post">
            <input type="hidden" name="staffID" value="<%= staff.getStaffID() %>">
            
            <div class="mb-3">
                <label for="fullName" class="form-label">Full Name</label>
                <input type="text" class="form-control" id="fullName" name="fullName" value="<%= staff.getName() %>" required>
            </div>

            <div class="mb-3">
                <label for="address" class="form-label">Address</label>
                <input type="text" class="form-control" id="address" name="address" value="<%= staff.getAddress() %>" required>
            </div>

            <div class="mb-3">
                <label for="phone" class="form-label">Phone</label>
                <input type="text" class="form-control" id="phone" name="phone" value="<%= staff.getPhone() %>" required>
            </div>

            <div class="mb-3">
                <label for="dateJoin" class="form-label">Date Join</label>
                <input type="date" class="form-control" id="dateJoin" name="dateJoin" value="<%= staff.getDateJoin() %>" required>
            </div>

            <div class="mb-3">
                <label for="salary" class="form-label">Salary</label>
                <input type="number" class="form-control" id="salary" name="salary" value="<%= staff.getSalary() %>" required>
            </div>

            <div class="mb-3">
                <label for="userID" class="form-label">User ID</label>
                <input type="number" class="form-control" id="userID" name="userID" value="<%= staff.getUserID() %>" required>
            </div>

            <div class="mb-3">
                <label for="username" class="form-label">Username</label>
                <input type="text" class="form-control" id="username" name="username" value="<%= account.getUserName() %>" required>
            </div>

            <div class="mb-3">
                <label for="email" class="form-label">Email</label>
                <input type="email" class="form-control" id="email" name="email" value="<%= account.getEmail() %>" required>
            </div>

            <div class="mb-3">
                <label for="password" class="form-label">Password</label>
                <input type="password" class="form-control" id="password" name="password" value="<%= account.getPassword() %>" required>
            </div>

            <button type="submit" class="btn btn-success">Update Staff</button>
        </form>
    </div>
</body>
</html>
