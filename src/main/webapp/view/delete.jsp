
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Staff Management Application</title>
</head>
<body>
<center>
    <h1>Delete Staff</h1>
</center>
<div align="center">
    <form method="post">
        <h3>Are you sure?</h3>
        <table>
            <caption>Staff Information</caption>
            <tr>
                <th>ID: </th>
                <td>${staff.getId()}</td>
            </tr>
            <tr>
                <th>Name: </th>
                <td>${staff.getName()}</td>
            </tr>
            <tr>
                <th>Date Of Birth: </th>
                <td>${staff.getDateOfBirth()}</td>
            </tr>
            <tr>
                <th>Address: </th>
                <td>${staff.getAddress()}</td>
            </tr>
            <tr>
                <th>Number Phone: </th>
                <td>${staff.getPhoneNumber()}</td>
            </tr>
            <tr>
                <th>Email: </th>
                <td>${staff.getEmail()}</td>
            </tr>
            <tr>
                <th>Department: </th>
                <td>${staff.department.name}</td>
            </tr>
            <tr>
                <td><button type="submit" class="btn btn-danger">Delete</button></td>
                <td><button type="button" class="btn btn-secondary"><a href="/staff" id="a-cancel">Cancel</a></button> </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
