package com.remainder;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class dashboard
 */
public class dashboard extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public dashboard() {
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

		String action = request.getParameter("action");
		GetEmail.setEmail(request.getParameter("email"));
		if (action == null || action.trim().isEmpty()) {
			out.println("<h3>No action specified.</h3>");
			return;
		}

		switch (action.toLowerCase()) {
		case "add":
			response.sendRedirect("addremainder.html");
			out.println("<h2>Reminder added successfully!</h2>");
			break;

		case "delete":
			String emailOrId = request.getParameter("id");
			response.sendRedirect("deleteremainder.html");
			out.println("<h2>Reminder deleted successfully!</h2>");
			out.println("<p>Deleted reminder for: " + emailOrId + "</p>");
			break;

		case "viewall":
			out.println("<h2>Deposit recorded successfully!</h2>");
			break;

		case "edit":
			out.println("<h2>Withdrawal recorded successfully!</h2>");
			break;

		case "viewtodays":
			out.println("<h2>Transfer successful!</h2>");
			break;

		default:
			out.println("<h3>Unknown action: " + action + "</h3>");
		}
	}
}
