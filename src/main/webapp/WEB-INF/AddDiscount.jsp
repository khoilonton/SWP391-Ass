<%-- 
    Document   : AddDiscount
    Created on : Feb 5, 2025, 3:09:52 PM
    Author     : TrangTrongKhoi-CE180958
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Discount</title>
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
        <h1 class="text-center mb-4">Add Discount</h1>
        <div class="card shadow p-4 mx-auto" style="max-width: 500px;">
            <form action="AddDiscount" method="POST">
                <div class="mb-3">
                    <label class="form-label">Min Order Value:</label>
                    <input type="number" name="minOrderValue" class="form-control" placeholder="Enter minimum order value" required>
                </div>
                <div class="mb-3">
                    <label class="form-label">Start Date:</label>
                    <input type="date" name="startDate" class="form-control" required>
                </div>
                <div class="mb-3">
                    <label class="form-label">Expiry Date:</label>
                    <input type="date" name="expiryDate" class="form-control" required>
                </div>
                <div class="mb-3">
                    <label class="form-label">Code:</label>
                    <input type="text" name="code" class="form-control" placeholder="Enter discount code" required>
                </div>
                <div class="mb-3">
                    <label class="form-label">Percent:</label>
                    <input type="number" name="percent" class="form-control" placeholder="Enter discount percentage" required>
                </div>
                <div class="mb-3">
                    <label class="form-label">Used Count:</label>
                    <input type="number" name="usedCount" class="form-control" placeholder="Number of times used" required>
                </div>
                <div class="mb-3">
                    <label class="form-label">Limit Code:</label>
                    <input type="number" name="limitCode" class="form-control" placeholder="Maximum usage limit" required>
                </div>
                <button type="submit" class="btn btn-custom w-100">➕ Add Discount</button>
            </form>
        </div>
        <div class="text-center mt-3">
            <a href="DiscountManager" class="btn btn-secondary">⬅ Back</a>
        </div>
    </body>
</html>
