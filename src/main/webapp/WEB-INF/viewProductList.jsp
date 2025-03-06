<%-- 
    Document   : viewProductList
    Created on : Mar 2, 2025, 4:56:21 PM
    Author     : TrangTrongKhoi-CE180958
--%>

<%@page import="java.util.List"%>
<%@page import="Model.Product"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

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
                <h2 class="mb-4">Product List</h2>
                <div class="mb-3">
                    <a href="addProductControl" class="btn btn-primary">Add Product</a>
                </div>

                <table class="table table-bordered table-hover">
                    <thead class="table-dark">
                        <tr>
                            <th>Product ID</th>
                            <th>Name</th>
                            <th>Price</th>
                            <th>Quantity</th>
                            <th>Start Date</th>
                            <th>Expiry Date</th>
                            <th>Stock Status</th>
                            <th>Image</th>
                            <th>Description</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            List<Product> productList = (List<Product>) request.getAttribute("productList");
                            int currentPage = (Integer) request.getAttribute("currentPage");
                            int totalPages = (Integer) request.getAttribute("totalPages");

                            if (productList == null || productList.isEmpty()) {
                        %>
                        <tr>
                            <td colspan="10" class="text-center">No products available.</td>
                        </tr>
                        <%
                        } else {
                            for (Product product : productList) {
                        %>
                        <tr>
                            <td><%= product.getPro_id()%></td>
                            <td><%= product.getName()%></td>
                            <td><%= product.getPrice()%></td>
                            <td><%= product.getQuantity()%></td>
                            <td><%= product.getStart_end()%></td>
                            <td><%= product.getExpiry_date()%></td>
                            <td><%= product.getStock_status()%></td>
                            <td><img src="images/<%= product.getImg()%>" alt="Product Image" class="product-img"></td>
                            <td><%= product.getDescription()%></td>
                            <td>
                                <a href="updateProductControl?proID=<%= product.getPro_id()%>" class="btn btn-warning btn-sm">Update</a>
                                <a href="deleteProductControl?proID=<%= product.getPro_id()%>" class="btn btn-danger btn-sm" onclick="return confirm('Are you sure?');">Delete</a>
                            </td>
                        </tr>   
                        <% }
                            }%>
                    </tbody>
                </table>

                <!-- Phân trang -->
                <nav>
                    <ul class="pagination justify-content-center">
                        <li class="page-item <%= (currentPage == 1) ? "disabled" : ""%>">
                            <a class="page-link" href="ViewProductAdmin?page=<%= currentPage - 1%>">Previous</a>
                        </li>

                        <% for (int i = 1; i <= totalPages; i++) {%>
                        <li class="page-item <%= (currentPage == i) ? "active" : ""%>">
                            <a class="page-link" href="ViewProductAdmin?page=<%= i%>"><%= i%></a>
                        </li>
                        <% }%>

                        <li class="page-item <%= (currentPage == totalPages) ? "disabled" : ""%>">
                            <a class="page-link" href="ViewProductAdmin?page=<%= currentPage + 1%>">Next</a>
                        </li>
                    </ul>
                </nav>

            </div>
    </body>
</html>
