
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Staff Management Application</title>
</head>
<body class="container">
<center>
    <h1>Staff Management</h1>
</center>

<div>
    <h2>
        <a href="/staff?action=create">Add</a>
    </h2>

    <div class="input-group">

        <form action="/staff">
            <input name="action" value="search" hidden>
            <input name="search" placeholder="Search"><button type="submit">Search</button>
        </form>
    </div>


    <table border="1" cellpadding="5">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Date Of Birth</th>
            <th>Address</th>
            <th>PhoneNumber</th>
            <th>Email</th>
            <th>Department</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="staff" items="${listStaff}">
            <tr>
                <td><c:out value="${staff.id}"/></td>
                <td><c:out value="${staff.name}"/></td>
                <td><c:out value="${staff.dateOfBirth}"/></td>
                <td><c:out value="${staff.address}"/></td>
                <td><c:out value="${staff.phoneNumber}"/></td>
                <td><c:out value="${staff.email}"/> </td>
                <td><c:out value="${staff.department.name}"/></td>
                <td>
                    <a href="/staff?action=edit&id=${staff.id}">Edit</a>
                    <a href="/staff?action=delete&id=${staff.id}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>

</div>
</body>
</html>
