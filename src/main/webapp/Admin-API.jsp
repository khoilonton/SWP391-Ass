<%-- 
    Document   : Admin-API
    Created on : Feb 2, 2025, 10:42:12 AM
    Author     : TrangTrongKhoi-CE180958
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Admin</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="Public/assets/css/Admin-API.css">
    <script src="Public/assets/js/Admin-API.js"></script>
</head>
<body class="d-flex">
    <div class="d-flex flex-column flex-shrink-0 p-3 bg-light" style="width: 250px; height: 100vh;">
        <h3 class="text-center">Koideptrai</h3>
        <p class="text-center">Chào mừng bạn trở lại</p>
        <ul class="nav nav-pills flex-column mb-auto">
            <li><a href="#" class="nav-link link-dark">Dashboard</a></li>
            <li><a href="#" class="nav-link link-dark">Staff Management</a></li>
            <li><a href="#" class="nav-link link-dark">Product Management</a></li>
            <li><a href="#" class="nav-link link-dark">Category Management</a></li>
            <li><a href="#" class="nav-link link-dark">Order Management</a></li>
            <li>
                <a href="#" class="nav-link link-dark" onclick="toggleSubmenu()">Customer Management</a>
                <ul class="list-unstyled ps-3 submenu" id="customerSubmenu" style="display: none;">
                    <li><a href="CustomerManager" class="nav-link link-secondary">View Detail Customer</a></li>
                    <li><a href="ViewDetailCustomer.jsp" class="nav-link link-secondary">View Customer History</a></li>
                    <li><a href="AccountManager" class="nav-link link-secondary">Lock or Unlock Customer</a></li>
                </ul>
            </li>
            <li><a href="DiscountManager" class="nav-link link-dark">Discount Management</a></li>
            <li><a href="PromotionManager" class="nav-link link-dark">Promotion Management</a></li>
            <li><a href="FeebackManager" class="nav-link link-dark">Review & Comment Management</a></li>
            <li><a href="#" class="nav-link link-dark">Setting</a></li>
        </ul>
    </div>
</body>
</html>
