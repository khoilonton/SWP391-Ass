<%-- 
    Document   : viewCategory
    Created on : Mar 2, 2025, 4:55:46 PM
    Author     : TrangTrongKhoi-CE180958
--%>

<%@page import="Model.Category"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="DAO.CategoryDAO"%>
<%@page import="java.util.List"%>

<%
    CategoryDAO dao = new CategoryDAO();
    List<Category> categoryList = dao.getAllCategories(); // Lấy danh mục từ database
%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Product Categories</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <style>
            body {
                display: flex;
            }
            #sidebar {
                width: 250px;
                min-height: 100vh;
                background-color: #343a40;
                color: white;
                padding: 20px;
                position: fixed;
                left: 0;
                top: 0;
                bottom: 0;
            }
            .main-content {
                margin-left: 270px; /* Để tránh bị chồng lên sidebar */
                padding: 20px;
                width: calc(100% - 270px);
            }
        </style>
    </head>
    <body>
        <%@ include file="admin-sidebar.jsp" %>
        <div class="main-content">
            <h2 class="mb-4">Product Categories</h2>
            <a href="addCategory" class="btn btn-primary mb-3">Add Category</a>
            <% if (categoryList.isEmpty()) { %>
            <p class="text-muted">No categories available.</p>
            <% } else { %>
            <table class="table table-bordered">
                <thead class="table-dark">
                    <tr>
                        <th>Category ID</th>
                        <th>Category Name</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (Category cat : categoryList) {%>
                    <tr>
                        <td><%= cat.getCatID()%></td>
                        <td><%= cat.getCatName()%></td>
                        <td>
                            <a href="updateCategory?catID=<%= cat.getCatID()%>" class="btn btn-warning btn-sm">Edit</a>
                            <a href="deleteCategory?catID=<%= cat.getCatID()%>" class="btn btn-danger btn-sm"
                               onclick="return confirm('Are you sure you want to delete this category?');">Delete</a>
                        </td>
                    </tr>
                    <% } %>
                </tbody>
            </table>
            <% }%>
        </div>
    </body>
</html>
