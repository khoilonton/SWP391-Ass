<%-- 
    Document   : viewStaffList
    Created on : Mar 2, 2025, 4:58:07 PM
    Author     : TrangTrongKhoi-CE180958
--%>

<%@page import="Model.Staff"%>
<%@page import="DAO.StaffDAO"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Staff List</title>
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

        <div class="main-content">
            <div class="container mt-4">
                <h2 class="mb-4">Staff List</h2>
                <div class="mb-3">
                    <a href="addStaffControl" class="btn btn-primary">Add Staff</a>
                </div>

                <table class="table table-bordered table-hover">
                    <thead class="table-dark">
                        <tr>
                            <th>Staff ID</th>
                            <th>Full Name</th>
                            <th>Address</th>
                            <th>Phone</th>
                            <th>Date Join</th>
                            <th>Salary</th>
                            <th>UserID</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            StaffDAO staffDAO = new StaffDAO();
                            List<Staff> staffList = staffDAO.getAllStaff();
                            if (staffList.isEmpty()) {
                        %>
                        <tr>
                            <td colspan="8" class="text-center">No staff available.</td>
                        </tr>
                        <%
                            } else {
                                for (Staff staff : staffList) {
                        %>
                        <tr>
                            <td><%= staff.getStaffID() %></td>
                            <td><%= staff.getName()%></td>
                            <td><%= staff.getAddress() %></td>
                            <td><%= staff.getPhone() %></td>
                            <td><%= staff.getDateJoin() %></td>
                            <td><%= staff.getSalary() %></td>
                            <td><%= staff.getUserID() %></td> 
                            <td>
                                <a href="updateStaffControl?staffID=<%= staff.getStaffID() %>" class="btn btn-warning btn-sm">Update</a>
                                <a href="deleteStaffControl?staffID=<%= staff.getStaffID() %>" 
                                   class="btn btn-danger btn-sm" 
                                   onclick="return confirm('Are you sure you want to delete this staff?');">
                                    Delete
                                </a>        
                            </td>   
                        </tr>   
                        <% } } %>
                    </tbody>
                </table>

                <div class="text-center mt-3">
                </div>
            </div>
        </div>
    </div>
</body>
</html>
