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
 * Servlet implementation class addremainder
 */
public class addremainder extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public addremainder() {
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
		String title = request.getParameter("title");
		String date = request.getParameter("date");
		String description = request.getParameter("description");

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // MySQL 8+ driver
			Connection conn=DbConnection.connect();

			String sql = "INSERT INTO reminders (title, description, reminder_date,email) VALUES ( ?, ?, ?, ?)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, title);
			stmt.setString(2, description);
			stmt.setString(3, date); // Format: HH:mm:ss
			String email = GetEmail.getEmail();
			stmt.setString(4, email);

			int rows = stmt.executeUpdate();

			if (rows > 0) {
				response.sendRedirect("addSuccesful.html");
				out.println("<h2>Reminder added successfully!</h2>");
			} else {
				out.println("<h2>Failed to add reminder.</h2>");
			}

			stmt.close();
			conn.close();

		} catch (Exception e) {
			out.println("<h2>Error occurred: " + e.getMessage() + "</h2>");
			e.printStackTrace(out);
		}

		out.println("<br><a href='dashboard.html'>← Back to Dashboard</a>");
	}
}
