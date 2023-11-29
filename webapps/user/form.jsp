<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>form</title>
</head>
<body>
<form method="post" action="/users">
    <div>
        <label for="userId">ID</label>
        <input class="form-control" id="userId" name="userId" placeholder="User ID">
    </div>
    <div>
        <label for="name">Name</label>
        <input class="form-control" id="name" name="name" placeholder="Name">
    </div>
    <button type="submit">Sign up</button>
</form>
</body>
</html>
