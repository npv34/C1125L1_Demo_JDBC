<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
<div class="container">
  <!--- include header.jsp ---->
  <%@ include file="../layouts/header.jsp" %>
  <div class="row">
    <div class="col-12 col-md-12 pt-2">
    <div class="card">
      <div class="card-header">
        <div class="row">
          <div class="col-12 col-md-4">
            <a href="/users/create" class="btn btn-success">Create</a>
          </div>
          <div class="col-12 col-md-8">
            <form action="/users/search" method="get" class="d-flex">
              <input value="${keyword}" type="text" name="keyword" class="form-control" >
              <button type="submit" class="btn btn-primary">Search</button>
            </form>
          </div>
        </div>


      </div>
      <div class="card-body">
          <table class="table table-striped">
            <tr>
              <th>#</th>
              <th>Username</th>
              <th>Email</th>
              <th></th>
            </tr>
            <c:set var="index" value="1"/>
            <c:forEach var="user" items="${data}">
              <tr>
                <td><c:out value="${index}"/></td>
                <td><c:out value="${user.username}"/></td>
                <td><c:out value="${user.email}"/></td>
                <td>
                  <a onclick="return confirm('Are you sure?')" href="/users/delete?id=<c:out value="${user.id}"/>" class="btn btn-danger">Delete</a>
                  <a href="/users/edit?id=<c:out value="${user.id}"/>" class="btn btn-primary">Edit</a>
                </td>
              </tr>
              <c:set var="index" value="${index + 1}"/>
            </c:forEach>
          </table>
      </div>
    </div>
    </div>
    </div>

  </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
</body>
</html>
