<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Committee</title>
</head>

<body>
<div>
    <h1>Committee</h1>
</div>
<div>
    <div>
        <table border="1">
            <caption>Enrollee info</caption>
            <tr>
                <th>First Name</th>
                <th>Middle Name</th>
                <th>Last Name</th>
                <th>Faculty Name</th>
            </tr>
            <c:forEach var="enrollee" items="${enrollees}">
                <tr>
                    <td><c:out value="${enrollee.getFirstName()}"/></td>
                    <td><c:out value="${enrollee.getMiddleName()}"/></td>
                    <td><c:out value="${enrollee.getLastName()}"/></td>
                    <td><c:out value="${enrollee.getFacultyName()}"/></td>

                    <td>
                        <button onclick="location.href='${pageContext.servletContext.contextPath}/enrollees/edit?id=${client.getId()}'">
                            Edit
                        </button>
                        <form method="post" action='<c:url value="/enrollees/delete" />' style="display:inline;">
                            <input type="hidden" name="id" value="${enrollee.getId()}">
                            <button type="submit">Delete</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>

    </div>
</div>


<div>
    <button onclick="location.href='${pageContext.servletContext.contextPath}/enrollees/add'">Add enrollee</button>
</div>

<div>
    <button onclick="location.href='${pageContext.servletContext.contextPath}/'">Log out</button>
</div>
</body>
</html>
