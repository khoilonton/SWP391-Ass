<%@page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Customer Dashboard</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <div class="container mt-5">
            <h1>Customer Dashboard</h1>
            <hr>
            <div class="row">
                <!-- Sidebar với các menu -->
                <div class="col-md-3">
                    <div class="list-group">
                        <!-- Liên kết đến OrderServlet -->
                        <a href="viewOrderListCustomer" class="list-group-item list-group-item-action">Orders</a>
                        <a href="faq" class="list-group-item list-group-item-action">FAQs</a>
                        <a href="customerPurchasedProduct" class="list-group-item list-group-item-action">My Purchased Products</a>
                        <!-- Các menu khác nếu cần -->
                    </div>
                </div>
                <!-- Nội dung chính -->
                <!-- Nội dung chính -->
                <div class="col-md-9">
                    <div class="d-flex justify-content-between align-items-center mb-3">
                        <h2>Welcome, ${username}!</h2>
                        <a href="logoutServlet" class="btn btn-danger">Logout</a>
                    </div>
                    <p>Chọn một tùy chọn từ bên trái để xem thông tin.</p>
                </div>

            </div>
        </div>
    </body>
</html>
