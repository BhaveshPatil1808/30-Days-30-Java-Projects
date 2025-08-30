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
 * Servlet implementation class editremainder
 */
public class editremainder extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public editremainder() {
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
		String idParam = request.getParameter("id");
		String title = request.getParameter("title");
		String date = request.getParameter("date");
		String description = request.getParameter("description");

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		try {
			int id = Integer.parseInt(idParam);

			Connection conn = DbConnection.connect();

			String sql = "UPDATE reminders SET title = ?, description = ?, reminder_date = ? WHERE id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, title);
			stmt.setString(2, description);
			stmt.setString(3, date);
			stmt.setInt(4, id);

			int rows = stmt.executeUpdate();

			if (rows > 0) {
				response.sendRedirect("dashboard.html");
				out.println("<h2>Reminder updated successfully!</h2>");
			} else {
				out.println("<h2>No reminder found with ID: " + id + "</h2>");
			}

			stmt.close();
			conn.close();

		} catch (NumberFormatException e) {
			out.println("<h2>Invalid ID format. Please enter a numeric ID.</h2>");
		} catch (Exception e) {
			out.println("<h2>Error occurred: " + e.getMessage() + "</h2>");
			e.printStackTrace(out);
		}

		out.println("<br><a href='dashboard.html'>← Back to Dashboard</a>");
	}
}
