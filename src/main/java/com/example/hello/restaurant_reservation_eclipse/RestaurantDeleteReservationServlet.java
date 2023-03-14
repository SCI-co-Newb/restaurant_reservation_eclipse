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

/**
 * Servlet implementation class RestaurantDeleteReservationServlet
 */
@WebServlet("/restaurantDeleteReservationServlet")
public class RestaurantDeleteReservationServlet extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RestaurantDeleteReservationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String reservationDeletion = request.getParameter("customer_user_name");
		
		try {
			Customer.set_customer_restaurant_id_null(reservationDeletion);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		PrintWriter writer = response.getWriter();
		String htmlRespone = "<html>";
		
		htmlRespone += "<button onclick = \"window.location.href = 'RestaurantMenu.html';\">Menu</button>";
		
		htmlRespone += "<html>";
		
		writer.println(htmlRespone);
	}

}
