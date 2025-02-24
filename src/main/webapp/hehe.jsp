<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>JSP Page</title>
</head>
<body>

    <c:set var="hehe" value="Xin chào từ JSP!" />
    <h1><c:out value="${hehe}" /></h1>

</body>
</html>
