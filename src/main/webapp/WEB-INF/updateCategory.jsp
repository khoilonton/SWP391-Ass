<%-- 
    Document   : updateCategory
    Created on : Mar 2, 2025, 4:52:09 PM
    Author     : TrangTrongKhoi-CE180958
--%>

<%@page import="Model.Category"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="DAO.CategoryDAO"%>


<%
    int catID = Integer.parseInt(request.getParameter("catID"));
    CategoryDAO dao = new CategoryDAO();
    Category category = dao.getCategoryByID(catID);
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update Product Category</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h2>Edit Product Category</h2>

        <form action="updateCategory" method="post">
            <input type="hidden" name="catID" value="<%= category.getCatID() %>">
            
            <div class="mb-3">
                <label for="catName" class="form-label">Category Name</label>
                <input type="text" class="form-control" id="catName" name="catName" value="<%= category.getCatName() %>" required>
            </div>
            <button type="submit" class="btn btn-primary">Save Changes</button>
            <a href="viewCategory.jsp" class="btn btn-secondary">Cancel</a>
        </form>
    </div>
</body>
</html>