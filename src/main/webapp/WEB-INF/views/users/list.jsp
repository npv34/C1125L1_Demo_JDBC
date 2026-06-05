<%@ page import="java.util.List" %>
<%@ page import="com.codegym.demomvc.entities.User" %><%--
  Created by IntelliJ IDEA.
  User: luanpv
  Date: 6/5/26
  Time: 19:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  List<User> data = (List<User>) request.getAttribute("users");
  %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table>
  <tr>
    <th>ID</th>
    <th>Username</th>
  </tr>
  <% for (User user : data) { %>
    <tr>
      <td><%= user.getId() %></td>
      <td><%= user.getUsername() %></td>
    </tr>
  <% } %>
</table>
</body>
</html>
