<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create Software</title>
</head>
<body>
    <h2>Create Software</h2>
    <form action="createSoftware" method="post">
        <label for="name">Software Name:</label>
        <input type="text" id="name" name="name" required><br><br>
        <label for="description">Description:</label>
        <textarea id="description" name="description"></textarea><br><br>
        <label for="accessLevels">Access Levels:</label>
        <input type="text" id="accessLevels" name="accessLevels" required><br><br>
        <button type="submit">Create Software</button>
    </form>
</body>
</html>
