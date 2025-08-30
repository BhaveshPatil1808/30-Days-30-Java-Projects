package com.remainder;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class register
 */
public class register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public register() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Get parameters from form
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String cpassword = request.getParameter("cpassword");
        System.out.println(email+" "+password);
        // Check password match
        if (!password.equals(cpassword)) {
            out.println("<script type='text/javascript'>");
            out.println("alert('Passwords do not match!');");
            out.println("location='register.html';");
            out.println("</script>");
            return;
        }

        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = DbConnection.connect(); // Your existing DB connection utility

            // SQL insert for only email and password
            String sql = "INSERT INTO userdetail (email, password) VALUES (?, ?)";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, email);
            pstmt.setString(2, password); // You should hash this in production

            int rows = pstmt.executeUpdate();

            if (rows > 0) {
                out.println("<script type='text/javascript'>");
                out.println("alert('Registration successful!');");
                out.println("location='index.html';");
                out.println("</script>");
            } else {
                out.println("<script type='text/javascript'>");
                out.println("alert('Registration failed!');");
                out.println("location='register.html';");
                out.println("</script>");
            }

        } catch (Exception e) {
            out.println("<script type='text/javascript'>");
            out.println("alert('Error: " + e.getMessage() + "');");
            out.println("location='register.html';");
            out.println("</script>");
        } finally {
            try { if (pstmt != null) pstmt.close(); } catch (Exception e) {}
            try { if (con != null) con.close(); } catch (Exception e) {}
        }
	}
}
