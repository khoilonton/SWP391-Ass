<%-- 
    Document   : admin-sidebar
    Created on : Feb 26, 2025, 2:31:17 PM
    Author     : TrangTrongKhoi-CE180958
--%>

<style>
    #sidebar {
        width: 250px;
        min-height: 100vh;
        background-color: #343a40;
        color: white;
        padding: 20px;
    }
    #sidebar h3 {
        font-weight: bold;
        text-align: center;
    }
    #sidebar p {
        text-align: center;
        font-size: 14px;
        color: #dcdcdc;
    }
    .nav-link {
        color: #ffffff !important;
        padding: 10px 15px;
        border-radius: 5px;
        transition: all 0.3s;
    }
    .nav-link:hover {
        background-color: #495057;
    }
    .submenu {
        padding-left: 20px;
        list-style: none;
        display: none;
        transition: all 0.3s ease-in-out;
    }
    .submenu .nav-link {
        font-size: 14px;
        padding: 5px 15px;
    }
</style>

<div id="sidebar" class="d-flex flex-column flex-shrink-0">
    <h3>Admin Panel</h3>
    <p>heheh</p>
    <ul class="nav nav-pills flex-column mb-auto">
        <li><a href="DashBoard" class="nav-link"> Dashboard</a></li>
        <li><a href="ViewStaffList" class="nav-link"> Staff Management</a></li>
        <li><a href="ViewProductAdmin" class="nav-link"> Product Management</a></li>
        <li><a href="viewCategory" class="nav-link"> Category Management</a></li>
        <li><a href="#" class="nav-link"onclick="toggleSubmenuu()"> Order Management</a>
        <ul class="submenu" id="orderSubmenu">
                <li><a href="ViewOrderListAdmin" class="nav-link">View Order Customer</a></li>
                <li><a href="ViewCancellOrder" class="nav-link"> View Order Cancellation</a></li>
            </ul>
        
        </li>
        <li>
            <a href="#" class="nav-link" onclick="toggleSubmenu()"> Customer Management</a>
            <ul class="submenu" id="customerSubmenu">
                <li><a href="CustomerManager" class="nav-link">View Detail Customer</a></li>
                
            </ul>
        </li>
        <li><a href="AccountManager" class="nav-link"> Lock/Unlock</a></li>
        <li><a href="DiscountManager" class="nav-link">Discount Management</a></li>
        <li><a href="PromotionManager" class="nav-link">Promotion Management</a></li>
        <li><a href="FeebackManager" class="nav-link"> Review & Comment</a></li>
        <li><a href="#" class="nav-link"> Setting</a></li>
    </ul>
</div>

<script>
    function toggleSubmenu() {
        var submenu = document.getElementById("customerSubmenu");
        if (submenu.style.display === "block") {
            submenu.style.display = "none";
        } else {
            submenu.style.display = "block";
        }
    }
        function toggleSubmenuu() {
        var submenu = document.getElementById("orderSubmenu");
        if (submenu.style.display === "block") {
            submenu.style.display = "none";
        } else {
            submenu.style.display = "block";
        }
    }
</script>
