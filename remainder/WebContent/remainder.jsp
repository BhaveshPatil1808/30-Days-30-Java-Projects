<%@page import="com.remainder.GetEmail"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.remainder.DbConnection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>All Reminders</title>

    <!-- Bootstrap CDN for styling -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    
    <style>
        body {
            background: #f7f9fc;
            font-family: 'Segoe UI', sans-serif;
            padding: 30px;
        }
        h2 {
            text-align: center;
            margin-bottom: 30px;
            color: #2c3e50;
        }
        .table-container {
            box-shadow: 0 0 15px rgba(0,0,0,0.1);
            background: white;
            padding: 20px;
            border-radius: 8px;
        }
        table {
            width: 100%;
        }
        th {
            background-color: #28a745;
            color: white;
        }
        td, th {
            text-align: center;
            vertical-align: middle !important;
        }
    </style>
</head>
<body>

<%
    Connection con = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    try {
        con = DbConnection.connect();
        String email=GetEmail.getEmail();
        pstmt = con.prepareStatement("SELECT * FROM reminders WHERE email = ? ORDER BY reminder_date");
        pstmt.setString(1, email);
        rs = pstmt.executeQuery();
%>

<div class="container table-container">
    <h2>All Reminders</h2>
    <table class="table table-bordered table-hover">
        <thead>
            <tr>
                <th>ID</th>
                <th>Title</th>
                <th>Description</th>
                <th>Date</th>
            </tr>
        </thead>
        <tbody>
            <% while(rs.next()) { %>
            <tr>
                <td><%= rs.getInt("id") %></td>
                <td><%= rs.getString("title") %></td>
                <td><%= rs.getString("description") %></td>
                <td><%= rs.getString("reminder_date") %></td>
            </tr>
            <% } %>
        </tbody>
    </table>
</div>

<%
    } catch(Exception e) {
%>
    <div class="alert alert-danger text-center">Error: <%= e.getMessage() %></div>
<%
    } finally {
        try { if (rs != null) rs.close(); } catch (Exception e) {}
        try { if (pstmt != null) pstmt.close(); } catch (Exception e) {}
        try { if (con != null) con.close(); } catch (Exception e) {}
    }
%>

</body>
</html>
