<%-- 
    Document   : addProduct
    Created on : Mar 2, 2025, 4:47:25 PM
    Author     : TrangTrongKhoi-CE180958
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Add Product</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <div class="container mt-5">
            <h2 class="mb-4">Add New Product</h2>
            <% if (request.getAttribute("errorMessage") != null) {%>
            <div class="alert alert-danger">
                <%= request.getAttribute("errorMessage")%>
            </div>
            <% }%>
            <form action="addProductControl" method="post">
                <div class="mb-3">
                    <label class="form-label">Product ID</label>
                    <input type="number" name="proID" class="form-control" required>
                </div>
                <div class="mb-3">
                    <label class="form-label">Product Name</label>
                    <input type="text" name="proName" class="form-control" required>
                </div>
                <div class="mb-3">
                    <label class="form-label">Expiry Date</label>
                    <input type="date" name="expiryDate" class="form-control" required>
                </div>
                 <div class="mb-3">
                    <label class="form-label">Start Date</label>
                    <input type="date" name="start_date" class="form-control" required>
                </div>
                <div class="mb-3">
                    <label class="form-label">Description</label>
                    <textarea name="description" class="form-control"></textarea>
                </div>
                <div class="mb-3">
                    <label class="form-label">Stock Status</label>
                    <select name="stockStatus" class="form-select">
                        <option value="Yes">Yes</option>
                        <option value="No">No</option>
                    </select>
                </div>
                <div class="mb-3">
                    <label class="form-label">Image URL</label>
                    <input type="text" name="img" class="form-control" required>
                </div>
                <div class="mb-3">
                    <label class="form-label">Quantity</label>
                    <input type="number" name="quantity" class="form-control" required>
                </div>
                <div class="mb-3">
                    <label class="form-label">Category ID</label>
                    <select name="catID" class="from-select">
                        <option value="1">1</option>
                        <option value="1">2</option>
                        <option value="1">3</option>
                        <option value="1">4</option>
                        <option value="1">5</option>
                        <option value="1">6</option>
                        <option value="1">7</option>
                        <option value="1">8</option>
                        <option value="1">9</option>
                        <option value="1">10</option>
                        <option value="1">11</option>
                        <option value="1">12</option>
                        <option value="1">13</option>
                    </select>
                </div>
                <div class="mb-3">
                    <label class="form-label">Price</label>
                    <input type="number" name="proPrice" class="form-control" required>
                </div>
                <button type="submit" class="btn btn-primary">Add Product</button>
                <a href="ViewProductAdmin" class="btn btn-secondary">Back</a>
            </form>
        </div>
    </body>
</html>