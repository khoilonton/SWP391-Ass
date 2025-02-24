<%-- 
    Document   : EditDiscount
    Created on : Feb 5, 2025, 4:09:52 PM
    Author     : TrangTrongKhoi-CE180958
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Discount</title>
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
                background-color: #007bff;
                color: white;
            }
            .btn-custom:hover {
                background-color: #0056b3;
            }
        </style>


    </head>
    <body class="container mt-5">
        <h1 class="text-center mb-4">Edit Discount</h1>
        <div class="card shadow p-4 mx-auto" style="max-width: 500px;">
            <form action="EditDiscount" method="POST">
                <div class="mb-3">
                    <label class="form-label">Discount ID:</label>
                    <input type="text" name="discountID" class="form-control" value="${discount.dis_id}" readonly>
                </div>
                <div class="mb-3">
                    <label class="form-label">Min Order Value:</label>
                    <input type="number" name="minOrderValue" class="form-control" value="${discount.minOrder}" required>
                </div>
                <div class="mb-3">
                    <label class="form-label">Start Date:</label>
                    <input type="date" name="startDate" class="form-control" value="${discount.start_date}" required>
                </div>
                <div class="mb-3">
                    <label class="form-label">Expiry Date:</label>
                    <input type="date" name="expiryDate" class="form-control" value="${discount.expiry_date}" required>
                </div>
                <div class="mb-3">
                    <label class="form-label">Code:</label>
                    <input type="text" name="code" class="form-control" value="${discount.code}" required>
                </div>
                <div class="mb-3">
                    <label class="form-label">Percent:</label>
                    <input type="number" name="percent" class="form-control" value="${discount.percent}" required>
                </div>
                <div class="mb-3">
                    <label class="form-label">Used Count:</label>
                    <input type="number" name="usedCount" class="form-control" value="${discount.used_count}" required>
                </div>
                <div class="mb-3">
                    <label class="form-label">Limit Code:</label>
                    <input type="number" name="limitCode" class="form-control" value="${discount.limit_code}" required>
                </div>
                <button type="submit" class="btn btn-custom w-100">✏️ Update Discount</button>
            </form>
        </div>
        <div class="text-center mt-3">
            <a href="DiscountManager" class="btn btn-secondary">⬅ Back</a>
        </div>
    </body>
</html>
