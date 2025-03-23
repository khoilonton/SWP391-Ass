<%-- 
    Document   : ViewCancellOrder
    Created on : Mar 3, 2025, 9:53:26 PM
    Author     : TrangTrongKhoi-CE180958
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>View Order Cancellation</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa;
        }
        .wrapper {
            display: flex;
            min-height: 100vh;
        }
        #sidebar {
            width: 250px;
            background: #343a40;
            color: white;
            padding: 20px;
            min-height: 100vh;
        }
        #main-content {
            flex-grow: 1;
            padding: 30px;
            background-color: white;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            margin: 20px;
        }
        h1 {
            text-align: center;
            margin-bottom: 20px;
            font-size: 28px;
            font-weight: bold;
            color: #343a40;
        }
        .table-hover tbody tr:hover {
            background-color: #f1f1f1;
        }
        .table thead {
            background-color: #343a40;
            color: white;
        }
   .pagination .page-item.active .page-link {
    background-color: #007bff; /* Màu xanh */
    border-color: #007bff;
    color: white;
}

.pagination .page-link {
    color: #007bff; 
    border-radius: 5px;
    padding: 8px 12px;
}

.pagination .page-link:hover {
    background-color: #e9ecef;
}

    </style>
</head>
<body>

    <div class="wrapper">
        <div id="sidebar">
            <%@ include file="admin-sidebar.jsp" %>
        </div>
        <div id="main-content">
            <h1>View Order Cancellation</h1>
            <table class="table table-bordered table-hover">
                <thead class="table-light">
                    <tr class="text-center">
                        <th>ID</th>
                        <th>Cancel Date</th>
                        <th>Reason</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="cancell" items="${cancellList}">
                        <tr class="text-center">
                            <td>${cancell.cancell_id}</td>
                            <td>${cancell.date}</td>
                            <td>${cancell.reason}</td>   
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

             <div class="d-flex justify-content-between">
                    <div class="text-center">
                        <ul class="pagination">
                            <li class="page-item <c:if test="${currentPage == 1}">disabled</c:if>">
                                <a class="page-link" href="ViewCancellOrder?page=${currentPage - 1}">Previous</a>
                            </li>

                            <c:forEach var="i" begin="1" end="${totalPages}">
                                <li class="page-item <c:if test="${i == currentPage}">active</c:if>">
                                    <a class="page-link" href="ViewCancellOrder?page=${i}">${i}</a>
                                </li>
                            </c:forEach>
                            <li class="page-item <c:if test="${currentPage == totalPages}">disabled</c:if>">
                                <a class="page-link" href="ViewCancellOrder?page=${currentPage + 1}">Next</a>
                            </li>
                        </ul>
                    </div>
                </div>
        </div>
    </div>

</body>
</html>
