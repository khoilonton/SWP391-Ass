<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Lịch sử đơn hàng</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body class="container mt-4">
        <%
            String customerId = request.getParameter("customerId");
            String customerName = request.getParameter("name");
            
            Map<String, List<String[]>> orders = new HashMap<>();
            orders.put("KH001", new ArrayList<>(Arrays.asList(
                new String[]{"DH001", "2025-01-15", "1.500.000 VND", "Hoàn thành"},
                new String[]{"DH002", "2025-01-20", "2.200.000 VND", "Chờ xử lý"}
            )));
 List<String[]> orderList = new ArrayList<>();
orderList.add(new String[]{"DH003", "2025-01-22", "3,000,000 VND", "Hoàn thành"});
orders.put("KH002", orderList);

        %>

        <h1 class="text-center">Lịch sử đơn hàng của <%= (customerName != null) ? customerName : "Khách hàng" %></h1>
        <a href="ViewDetailCustomer.jsp" class="btn btn-primary mb-3">⬅ Quay lại danh sách khách hàng</a>

        <table class="table table-bordered table-striped">
            <thead class="table-dark">
                <tr>
                    <th>Mã đơn hàng</th>
                    <th>Ngày đặt hàng</th>
                    <th>Tổng tiền</th>
                    <th>Trạng thái</th>
                </tr>
            </thead>
            <tbody>
                <%
                    if (customerId != null && orders.containsKey(customerId)) {
                        for (String[] order : orders.get(customerId)) {
                            String statusClass = "";
                            if ("Hoàn thành".equals(order[3])) statusClass = "text-success";
                            if ("Chờ xử lý".equals(order[3])) statusClass = "text-warning";
                            if ("Đã hủy".equals(order[3])) statusClass = "text-danger";
                %>
                <tr>
                    <td><%= order[0] %></td>
                    <td><%= order[1] %></td>
                    <td><%= order[2] %></td>
                    <td class="fw-bold <%= statusClass %>"><%= order[3] %></td>
                </tr>
                <%
                        }
                    } else {
                %>
                <tr>
                    <td colspan="4" class="text-center">Không có đơn hàng nào</td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
    </body>
</html>
