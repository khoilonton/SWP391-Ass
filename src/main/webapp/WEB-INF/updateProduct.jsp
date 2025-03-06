<%-- 
    Document   : updateProduct
    Created on : Mar 2, 2025, 4:53:05 PM
    Author     : TrangTrongKhoi-CE180958
--%>

<%@page import="Model.Product"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="DAO.ProductDAO"%>
<%@page import="java.util.Objects"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update Product</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h2 class="mb-4">Update Product Information</h2>

        <%
            String proIDParam = request.getParameter("proID"); // Đảm bảo trùng tên với tham số từ viewProduct.jsp
            int proID = -1;
            Product product = null;
            
            if (proIDParam != null) {
                try {
                    proID = Integer.parseInt(proIDParam);
                    ProductDAO productDAO = new ProductDAO();
                    product = productDAO.getProductByID(proID);
                } catch (NumberFormatException e) {
                    out.println("<div class='alert alert-danger'>Invalid Product ID format.</div>");
                }
            }

            if (product == null) {
        %>
            <div class="alert alert-warning">Product not found or does not exist.</div>
            <a href="viewProductList.jsp" class="btn btn-secondary">Back</a>
        <% } else { %>
        
        <form action="updateProductControl" method="post">
            <input type="hidden" name="proID" value="<%= product.getPro_id()%>">

            <div class="mb-3">
                <label class="form-label">Product Name</label>
                <input type="text" name="proName" class="form-control" value="<%= product.getName() %>" required>
            </div>

            <div class="mb-3">
                <label class="form-label">Expiry Date</label>
                <input type="date" name="expiry_date" class="form-control" value="<%= product.getExpiry_date()%>" required>
            </div>
             <div class="mb-3">
                <label class="form-label">Start Date</label>
                <input type="date" name="start_date" class="form-control" value="<%= product.getStart_end()%>" required>
            </div>

            <div class="mb-3">
                <label class="form-label">Description</label>
                <textarea name="description" class="form-control" required><%= product.getDescription() %></textarea>
            </div>
            <div class="mb-3">
                <label class="form-label">Stock Status</label>
                <select name="stock_status" class="form-control">
                    <option value="Yes" <%= Objects.equals(product.getStock_status(), "Yes") ? "selected" : "" %>>Yes</option>
                    <option value="No" <%= Objects.equals(product.getStock_status(), "No") ? "selected" : "" %>>No</option>
                </select>
            </div>

            <div class="mb-3">
                <label class="form-label">Image URL</label>
                <input type="text" name="img" class="form-control" value="<%= product.getImg() %>" required>
            </div>

            <div class="mb-3">
                <label class="form-label">Quantity</label>
                <input type="number" name="quantity" class="form-control" value="<%= product.getQuantity()%>" required>
            </div>

            <div class="mb-3">
                <label class="form-label">Category ID</label>
                <input type="number" name="catID" class="form-control" value="<%= product.getCat_id()%>" required>
            </div>

            <div class="mb-3">
                <label class="form-label">Price</label>
                <input type="number" step="0.01" name="proPrice" class="form-control" value="<%= product.getPrice()%>" required>
            </div>

            <button type="submit" class="btn btn-success">Update Product</button>
            <a href="ViewProductAdmin" class="btn btn-secondary">Cancel</a>
        </form>

        <% } %>
    </div>
</body>
</html>
