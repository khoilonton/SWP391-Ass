<%-- 
    Document   : addCategory
    Created on : Mar 2, 2025, 4:46:42 PM
    Author     : TrangTrongKhoi-CE180958
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Add Product Category</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <div class="container mt-5">
            <h2 class="mb-4">Add Product Category</h2>

            <% String error = (String) request.getAttribute("error"); %>
            <% if (error != null) {%>
            <div class="alert alert-danger"><%= error%></div>
            <% }%>

            <form action="addCategory" method="post">
                <div class="mb-3">
                    <label for="catID" class="form-label">Category ID</label>
                    <input type="number" class="form-control" id="catID" name="catID" required>
                </div>
                <div class="mb-3">
                    <label for="catName" class="form-label">Category Name</label>
                    <input type="text" class="form-control" id="catName" name="catName" required>
                </div>
                <button type="submit" class="btn btn-success">Add Category</button>
                <a href="viewCategory" class="btn btn-secondary">Back</a>
            </form>

        </div>
    </body>
</html>