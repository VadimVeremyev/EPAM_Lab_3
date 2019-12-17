<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <div>
            <h2>Add enrollee</h2>
        </div>

        <form method="post">
            <div>
                <label for="firstName">First Name:</label>
                <input id="firstName" type="text" name="firstName">
            </div>

            <div>
                <label for="middleName">Middle Name:</label>
                <input id="middleName" type="text" name="middleName">
            </div>

            <div>
                <label for="lastName">Last Name:</label>
                <input id="lastName" type="text" name="lastName">
            </div>

            <div>
                <label for="facultyName">Faculty Name:</label>
                <input id="facultyName" type="text" name="facultyName">
            </div>

            <div>
                <button type="submit">Submit</button>
            </div>
        </form>
    </div>
</div>

<div>
    <button onclick="location.href='${pageContext.servletContext.contextPath}/enrollees'">Cancel</button>
</div>
</body>
</html>
