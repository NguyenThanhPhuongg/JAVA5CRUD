<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Home</title>
</head>
<body>
<form action="/editStudent/${student.id}" method="post">
    <label>Name</label>
    <input type="text" name="name" value="${student.name}">
    <c:if test="${not empty errors['name']}">
        <span style="color: red">${errors['name']}</span>
    </c:if>
    <label>Age</label>
    <input type="text" name="age" value="${student.age}">
    <c:if test="${not empty errors['age']}">
        <span style="color: red">${errors['age']}</span>
    </c:if>
    <button type="submit">Sua</button>
</form>
</body>
</html>