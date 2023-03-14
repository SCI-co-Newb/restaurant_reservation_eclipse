package com.example.hello.restaurant_reservation_eclipse;

import jakarta.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class CustomerDeleteReservationServlet
 */
@WebServlet("/customerDeleteReservationServlet")
public class CustomerDeleteReservationServlet extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerDeleteReservationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String user_name = (String) session.getAttribute("user_name");
		String pass_word = (String) session.getAttribute("pass_word");
		PrintWriter writer = response.getWriter();
		String htmlRespone = "<html>";
		
		try {
			Customer account = new Customer(user_name, pass_word);
			account.reset_restaurant_id();
			htmlRespone += "<h2>Reservation Deleted</h2>";
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		htmlRespone += "<button onclick = \"window.location.href = 'CustomerMenu.html';\">Menu</button>";
		htmlRespone += "</html>";
		
		writer.println(htmlRespone);
		
	}

}
