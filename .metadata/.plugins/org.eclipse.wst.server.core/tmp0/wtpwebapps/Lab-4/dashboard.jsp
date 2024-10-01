<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Dashboard</title>
    <link rel="stylesheet" href="/styles.css">
</head>
<body>
    <div class="container">
        <h1>Welcome to Your Dashboard</h1>

        <!-- Display the logged-in user's username -->
        <h2>
            Hello, 
            <c:if test="${not empty user}">
                ${user.username}
                
            </c:if>
            <c:if test="${not empty user}">
                ${user.password}
                
            </c:if>
        </h2>

        <p>What would you like to do?</p>

        <!-- Dashboard actions -->
        <ul>
            <li><a href="./ApiKey">Manage Your API Keys</a></li>
            <li><a href="logout">Logout</a></li>
        </ul>
    </div>
</body>
</html>
