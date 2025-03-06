<%-- 
    Document   : adminDashboard
    Created on : Mar 2, 2025, 5:09:45 PM
    Author     : TrangTrongKhoi-CE180958
--%>

<%@page import="Model.Product"%>
<%@page import="DAO.ProductDAO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="DAO.OrderDAO" %>
<%@ page import="java.text.DecimalFormat" %>

<%

    ProductDAO productDAO = new ProductDAO();
    List<Product> productList = productDAO.getTop10ProductsByQuanity();

    double totalSales = productDAO.getTotalSales();
    double totalCosts = productDAO.getTotalCosts();
    DecimalFormat df = new DecimalFormat("#,###.00");
%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Admin Dashboard</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
        <style>
            body {
                font-family: 'Arial', sans-serif;
                background-color: #f7f8fc;
                color: #2d3748;
                display: flex;
            }

            .sidebar {
                width: 260px;
                height: 100vh;
                position: fixed;
                background: #1c2b36;
                color: white;
                padding: 15px;
                overflow-y: auto;
            }

            .sidebar h2 {
                font-size: 25px;
                font-weight: bold;
                color: #ff385c;
                margin-bottom: 30px;
            }

            .sidebar a {
                display: flex;
                align-items: center;
                color: white;
                text-decoration: none;
                font-size: 15px;
                padding: 12px;
                margin-bottom: 12px;
                border-radius: 8px;
                transition: background-color 0.3s;
            }

            .sidebar a:hover {
                background-color: #2d3b45;
            }

            .content {
                text-align: center;
                padding: 40px;
                flex-grow: 1;
            }
        </style>
    </head>
    <body>
  <%@ include file="admin-sidebar.jsp" %>
        <div class="content">
            <div class="row">
                <div class="col-md-6">
                    <div class="card bg-light mb-3">
                        <div class="card-header text-white bg-success">Total Revenue</div>
                        <div class="card-body">
                            <h5 class="card-title"><%= df.format(totalSales)%> VND</h5>
                            <p class="card-text">Total revenue from all delivered orders.</p>
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="card bg-light mb-3">
                        <div class="card-header text-white bg-danger">Total Costs</div>
                        <div class="card-body">
                            <h5 class="card-title"><%= df.format(totalCosts)%> VND</h5>
                            <p class="card-text">Cost Profit Orders Delivered Multiplied By 0.7.</p>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row mt-4">
                <div class="col-md-12">
                    <div class="card bg-light mb-3">
                        <div class="card-header text-white bg-primary">Top 10 Best Selling Products</div>
                        <div class="card-body">
                            <div class="table-container">
                                <table class="table table-bordered">
                                    <thead>
                                        <tr>
                                            <th>Product ID</th>
                                            <th>Product Name</th>
                                            <th>Description</th>
                                            <th>Price</th>
                                            <th>Quantity Sold</th>
                                            <th>Image</th>
                                            <th>Expiry Date</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <% for (Product product : productList) { %>
                                        <tr>
                                            <td><%= product.getPro_id() %></td>
                                            <td><%= product.getName() %></td>
                                            <td><%= product.getDescription() %></td>
                                            <td><%= product.getQuantity() %></td>
                                            <td><%= df.format(product.getPrice()) %> VND</td>
                                            <td>
                                                <img src="<%= product.getImg() %>" alt="Product Image" style="max-width: 100px; height: auto;">
                                            </td>
                                            <td><%= product.getExpiry_date() %></td>
                                        </tr>
                                        <% } %>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
