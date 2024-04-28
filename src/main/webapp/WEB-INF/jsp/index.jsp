
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Home</title>
</head>
<body>
<a href="/newStudent">
    <button>Them</button>
</a>
<form action="/hien" method="get">
    <input type="text" name="search" placeholder="Search students..." value="${searchQuery}">
    <button type="submit">Search</button>
</form>
<table>
    <thead>
        <th>STT</th>
        <th>Name</th>
        <th>Age</th>
        <th>Action</th>
    </thead>
    <c:forEach items="${students}" var="st">
        <tr>
            <td>${st.id}</td>
            <td>${st.name}</td>
            <td>${st.age}</td>
            <td>
                <a href="/updateStudent/${st.id}">
                    <button>Sua</button>
                </a>
                <a href="/deleteStudent/${st.id}">
                    <button>Xoa</button>
                </a>
            </td>
        </tr>
    </c:forEach>
</table>
<c:if test="${totalPages > 1}">
    <p>Page: ${currentPage + 1} of ${totalPages}</p>

    <ul class="pagination">
        <c:if test="${currentPage > 0}">
            <li><a href="?page=0">First</a></li>
            <li><a href="?page=${currentPage - 1}">Previous</a></li>
        </c:if>

        <c:forEach var="i" begin="0" end="${totalPages - 1}">
            <li <c:if test="${currentPage == i}">class="active"</c:if>>
                <a href="?page=${i}">${i + 1}</a>
            </li>
        </c:forEach>

        <c:if test="${currentPage < totalPages - 1}">
            <li><a href="?page=${currentPage + 1}">Next</a></li>
            <li><a href="?page=${totalPages - 1}">Last</a></li>
        </c:if>
    </ul>
</c:if>
</body>
</html>
