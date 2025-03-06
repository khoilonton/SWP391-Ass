<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="Model.Order"%>
<%@page import="Model.OrderDetail"%>
<%@page import="Model.Product"%>
<%@page import="Model.Payment"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Sản phẩm đã mua</title>
        <style>
            table {
                width: 100%;
                border-collapse: collapse;
            }
            th, td {
                border: 1px solid black;
                padding: 10px;
                text-align: left;
            }
            img {
                width: 100px;
                height: auto;
            }
        </style>
    </head>
    <body>
        <h2>Danh sách sản phẩm đã mua</h2>
        <table>
            <tr>
                <th>Hình ảnh</th>
                <th>Tên sản phẩm</th>
                <th>Số lượng</th>
                <th>Giá</th>
                <th>Tổng tiền</th>
                <th>Ngày mua</th>
                <th>Phương thức thanh toán</th>
                <th>Trạng thái thanh toán</th>
                <th>Feedback</th>
            </tr>
            <%
                List<Order> orders = (List<Order>) request.getAttribute("orders");
                if (orders != null) {
                    for (Order order : orders) {
                        if ("Delivered".equals(order.getStatus())) {
                            for (OrderDetail detail : order.getOrderDetails()) {
                                Product product = detail.getProduct();
            %>
            <tr>
                <td><img src="<%= product.getImg()%>" alt="<%= product.getName()%>"></td>
                <td><%= product.getName()%></td>
                <td><%= detail.getQuantity()%></td>
                <td><%= product.getPrice()%> VNĐ</td>
                <td><%= detail.getQuantity() * product.getPrice()%> VNĐ</td>
                <td><%= order.getOder_date()%></td>
                <td><%= order.getPayment().getMethod()%></td>
                <td><%= order.getPayment().getStatus()%></td>
                <td><%= order.getFeedbackContent() != null ? order.getFeedbackContent() : "Chưa có feedback"%></td>
            </tr>
            <%
                            }
                        }
                    }
                }
            %>
        </table>
    </body>
</html>
