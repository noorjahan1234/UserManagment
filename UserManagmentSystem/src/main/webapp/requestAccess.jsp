<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Request Access</title>
</head>
<body>
    <h2>Request Access to Software</h2>
    <form action="requestAccess" method="post">
        <label for="softwareId">Software:</label>
        <select id="softwareId" name="softwareId" required>
            <!-- Populate dynamically with available software -->
        </select><br><br>
        <label for="accessType">Access Type:</label>
        <select id="accessType" name="accessType" required>
            <option value="Read">Read</option>
            <option value="Write">Write</option>
            <
