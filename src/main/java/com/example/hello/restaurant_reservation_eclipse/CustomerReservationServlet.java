package com.example.hello.restaurant_reservation_eclipse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class CustomerReservationServlet
 */
@WebServlet("/customerReservationServlet")
public class CustomerReservationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public CustomerReservationServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
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
			int r_id = account.get_reservation_id();
			if (r_id > 0) {
				htmlRespone += "<h2>Current Reservation:</h2><br>";
				htmlRespone += "<h2>"+Restaurant.get_restaurant(r_id)+"</h2>";
				htmlRespone += "<form name=\"customerDeleteReservation\" method=\"get\" action=\"customerDeleteReservationServlet\">";
				htmlRespone += "<input type=\"submit\" value=\"Delete\" />";
				htmlRespone += "</form>";
			} else {
				String[][] all_restaurants = Restaurant.get_all_restaurants();
				
				htmlRespone += "<h2>Currently you don't have a reservation</h2><br>";
				htmlRespone += "<h2>Restaurants:</h2><br>";
				htmlRespone += "<form name=\"customerReserveRestaurant\" method=\"get\" action=\"customerReserveRestaurantServlet\">";
				for (int i = 0; i < all_restaurants.length; i++) {
					htmlRespone += "<input type=\"radio\" id=\""+all_restaurants[i][1]+"\" name=\"restaurant_id\" value=\""+all_restaurants[i][0]+"\">";
					htmlRespone += "<label for=\""+all_restaurants[i][1]+"\">"+all_restaurants[i][1]+"</label><br>";
				}
				htmlRespone += "<input type=\"submit\" value=\"Reserve\">";
				htmlRespone += "</form>";
			}
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
