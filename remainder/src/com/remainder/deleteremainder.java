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
 * Servlet implementation class deleteremainder
 */
public class deleteremainder extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public deleteremainder() {
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
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		try {
			int id = Integer.parseInt(idParam);

			Connection conn = DbConnection.connect();

			String sql = "DELETE FROM reminders WHERE id =? and email=?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.setString(2, GetEmail.getEmail());

			int rows = stmt.executeUpdate();

			if (rows > 0) {
				out.println("<h2>Reminder deleted successfully!</h2>");
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
