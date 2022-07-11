
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Staff Management Application</title>
</head>
<body>
<h1>Edit Staff Information</h1>
<p>
    <a href="/staff">Back to staff list</a>
</p>
<form method="post">
    <fieldset>
        <legend>Staff information</legend>
        <table>
            <c:if test="${staff != null}">
                <input type="hidden" name="id" value="${requestScope["staff"].id}' />"/>
            </c:if>
            <tr>
                <td>Name: </td>
                <td><input type="text" name="name" id="name" value="${requestScope["staff"].name}"></td>
            </tr>
            <tr>
                <td>Date Of Birth: </td>
                <td><input type="date" name="dateOfBirth" id="dateOfBirth" value="${requestScope["staff"].dateOfBirth}"></td>
            </tr>
            <tr>
                <td>Address: </td>
                <td><input type="text" name="address" id="address" value="${requestScope["staff"].address}"></td>
            </tr>
            <tr>
                <td>Phone Number: </td>
                <td><input type="text" name="phoneNumber" id="phoneNumber" value="${requestScope["staff"].phoneNumber}"></td>
            </tr>
            <tr>
                <td>Email Address: </td>
                <td><input ype="email" name="email" id="email" value="${requestScope["staff"].email}"></td>
            </tr>
            <tr>
                <td>Department: </td>
                <td><input type="text" name="department" id="department" value="${requestScope["staff"].department.name}"></td>
            </tr>

            <tr>
                <td></td>
                <td><input type="submit" value="Update information"></td>
            </tr>

        </table>
    </fieldset>
</form>
</body>
</html>