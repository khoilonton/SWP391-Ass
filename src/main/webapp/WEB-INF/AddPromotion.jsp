<%-- 
    Document   : AddPromotion
    Created on : Feb 24, 2025
    Author     : TrangTrongKhoi-CE180958
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Promotion</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <style>
            body {
                background-color: #f8f9fa;
            }
            .card {
                border: none;
                border-radius: 15px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
            }
            .form-label {
                font-weight: 600;
            }
            .btn-custom {
                background-color: #28a745;
                color: white;
            }
            .btn-custom:hover {
                background-color: #218838;
            }
        </style>

    </head>
    <body class="container mt-5">
        <h1 class="text-center mb-4">Add Promotion</h1>
        <div class="card shadow p-4 mx-auto" style="max-width: 500px;">
            <form action="AddPromotion" method="POST">
                <div class="mb-3">
                    <label class="form-label">Promotion Name:</label>
                    <input type="text" name="name" class="form-control" placeholder="Enter promotion name" required>
                </div>
                <div class="mb-3">
                    <label class="form-label">Description:</label>
                    <textarea name="description" class="form-control" rows="3" placeholder="Enter description"></textarea>
                </div>
                <div class="mb-3">
                    <label class="form-label">Start Date:</label>
                    <input type="date" name="start_date" class="form-control" required>
                </div>
                <div class="mb-3">
                    <label class="form-label">End Date:</label>
                    <input type="date" name="end_date" class="form-control" required>
                </div>
                <div class="mb-3">
                    <label class="form-label">Discount Percentage:</label>
                    <input type="number" name="percent" class="form-control" min="1" max="100" placeholder="Enter discount percentage" required>
                </div>
                <div class="mb-3">
                    <label for="product" class="form-label">Select applicable products:</label>
                    <select name="productID" class="form-select" multiple required style="height: 200px; overflow-y: auto;">
                        <c:forEach var="product" items="${proList}">
                            <option value="${product.pro_id}">${product.name}</option>
                        </c:forEach>
                    </select>
                    <small class="text-muted">Giữ Ctrl (Windows) hoặc Command (Mac) để chọn nhiều sản phẩm.</small>
                </div>

                <button type="submit" class="btn btn-custom w-100">➕ Add Promotion</button>
            </form>
        </div>
        <div class="text-center mt-3">
            <a href="PromotionManager" class="btn btn-secondary">⬅ Back</a>
        </div>
    </body>
</html>
