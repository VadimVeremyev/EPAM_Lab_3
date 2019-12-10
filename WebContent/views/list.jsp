<%@ taglib 
	prefix="c" 
	uri="http://java.sun.com/jsp/jstl/core" 
%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="by.bsuir.committee.entity.Enrollee" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Enrollees list</title>
</head>
<body>
    <h1>Hello</h1>
    <c:if test="${enrollees != null && !enrollees.isEmpty()}">
        <table border="1">
            <caption>Enrollees table</caption>
            <tr>
                <th>FirstName</th>
                <th>MiddleName</th>
                <th>LastName</th>
				<th>FacultyName</th>
				<th>Id</th>
            </tr>

            <c:forEach var="enrollee" items="${enrollees}">
                <tr>
                    <td>${enrollee.getFirstName()}</td>
                    <td>${enrollee.getMiddleName()}</td>
                    <td>${enrollee.getLastName()}</td>
                    <td>${enrollee.getFacultyName()}</td>
                    <td>${enrollee.getId()}</td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
    <c:if test="${enrollees == null || enrollees.isEmpty()}">
        <p>Empty list</p>
    </c:if>
    <c:if test="${pageIndex != 1}">
        <a href="/list?pageIndex=1">Start</a>
        <a href="/list?pageIndex=${pageIndex - 1}"></a>
    </c:if>
    <c:if test="${pageIndex != pageCount}">
        <a href="/list?pageIndex=${pageIndex + 1}"></a>
    </c:if>
</body>
</html>