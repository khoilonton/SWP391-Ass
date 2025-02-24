<%-- 
    Document   : EditPromotion
    Created on : Feb 5, 2025, 3:10:09 PM
    Author     : TrangTrongKhoi-CE180958
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Edit Promotion</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body class="d-flex justify-content-center align-items-center vh-100 bg-light">
        <div class="card p-4 shadow-lg" style="max-width: 500px;">
            <h2 class="text-center text-primary">Edit Promotion</h2>

            <c:if test="${not empty errorMessage}">
                <div class="alert alert-danger">${errorMessage}</div>
            </c:if>

            <form action="EditPromotion" method="POST">
                <input type="text" name="id" value="${promotion.promo_id}" readonly>

                <div class="mb-3">
                    <label class="form-label">Name:</label>
                    <input type="text" class="form-control" name="name" value="${promotion.name}" required>
                </div>
                <div class="mb-3">
                    <label class="form-label">Description:</label>
                    <textarea class="form-control" name="description" required>${promotion.description}</textarea>
                </div>
                <div class="mb-3">
                    <label class="form-label">Start Date:</label>
                    <input type="date" class="form-control" name="start_date" value="${promotion.start_date}" required>
                </div>
                <div class="mb-3">
                    <label class="form-label">End Date:</label>
                    <input type="date" class="form-control" name="end_date" value="${promotion.end_date}" required>
                </div>
                <div class="mb-3">
                    <label class="form-label">Discount Percent:</label>
                    <input type="number" class="form-control" name="percent" value="${promotion.percent}" step="0.01" required>
                </div>

                <div class="mb-3">
                    <label for="product" class="form-label">Select applicable products:</label>
                    <select name="productID" class="form-select" multiple required style="height: 200px; overflow-y: auto;">
                        <c:forEach var="product" items="${productList}">
                            <option value="${product.pro_id}" 
                                    <c:if test="${selectedProducts.contains(product.pro_id)}">selected</c:if>>
                                ${product.name}
                            </option>
                        </c:forEach>
                    </select>
                    <small class="text-muted">Giữ Ctrl (Windows) hoặc Command (Mac) để chọn nhiều sản phẩm.</small>
                </div>

                <div class="d-flex justify-content-between">
                    <button type="submit" class="btn btn-primary">💾 Save</button>
                    <a href="PromotionManager" class="btn btn-secondary">❌ Cancel</a>
                </div>
            </form>
        </div>
    </body>
</html>
