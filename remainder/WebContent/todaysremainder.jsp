<%@page import="com.remainder.GetEmail"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.remainder.DbConnection"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Today's Reminders</title>

    <!-- Bootstrap CDN -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

    <style>
        body {
            background: #eef2f5;
            font-family: 'Segoe UI', sans-serif;
            padding: 30px;
        }
        h2 {
            text-align: center;
            margin-bottom: 30px;
            color: #34495e;
        }
        .table-container {
            background: #fff;
            padding: 25px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        th {
            background-color: #007bff;
            color: #fff;
        }
        td, th {
            text-align: center;
            vertical-align: middle;
        }
    </style>
</head>
<body>

<%
    Connection con = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    // Get today's date in yyyy-MM-dd format
    String today = new SimpleDateFormat("dd-MM-yyyy").format(new Date());

    try {
        con = DbConnection.connect();
        pstmt = con.prepareStatement("SELECT * FROM reminders WHERE email=? and reminder_date = ? ORDER BY id");
        pstmt.setString(1, GetEmail.getEmail());
        pstmt.setString(2, today);  // Pass today's date as a string
        rs = pstmt.executeQuery();
%>

<div class="container table-container">
    <h2>Today's Reminders</h2>

    <%
        if (!rs.isBeforeFirst()) {
    %>
        <div class="alert alert-info text-center">No reminders for today.</div>
    <%
        } else {
    %>
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
                    <td><%= rs.getString("reminder_date") %></td> <!-- Assuming reminder_time is also a string -->
                </tr>
                <% } %>
            </tbody>
        </table>
    <%
        }
    %>
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
