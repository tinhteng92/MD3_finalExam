
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Staff Management Application</title>
</head>
<body>
<center>
    <h1>Staff Management</h1>
    <h2>
        <a href="/staff">List All Staff</a>
    </h2>
</center>
<div align="center">
    <form method="post">
        <table border="1" cellpadding="5">
            <caption>
                <tr>
                    <th>Staff Name:</th>
                    <td>
                        <input type="text" name="name" id="name" size="45" placeholder="Enter name"/>
                    </td>
                </tr>
                <tr>
                    <th>Date Of Birth:</th>
                    <td>
                        <input type="text" name="dateOfBirth" id="dateOfBirth" size="45" placeholder="Enter date of birth as YYYY-MM-DD"/>
                    </td>
                </tr>
                <tr>
                    <th>Address:</th>
                    <td>
                        <input type="text" name="address" id="address" size="45" placeholder="Enter address"/>
                    </td>
                </tr>
                <tr>
                    <th>Phone Number:</th>
                    <td>
                        <input type="text" name="phoneNumber" id="phoneNumber" size="45" placeholder="Enter phone number"/>
                    </td>
                </tr>
                <tr>
                    <th>Email Address:</th>
                    <td>
                        <input type="email" name="email" id="email" size="45" placeholder="Enter email"/>
                    </td>
                </tr>
                <tr>
                    <th>Department:</th>
                    <td>
                        <select name="department" id="department">
                            <option value="" disabled selected>Open this select menu</option>
                            <c:forEach var="d" items="${department}">
                                <option value="${d.id}"><c:out value="${d.name}"/></option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input type="submit" value="Create"/>
                    </td>
                </tr>

            </caption>
        </table>
    </form>


</div>
</body>
</html>
