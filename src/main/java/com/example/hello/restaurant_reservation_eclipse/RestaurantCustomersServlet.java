package com.example.hello.restaurant_reservation_eclipse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class RestaurantCustomersServlet
 */
@WebServlet("/restaurantCustomersServlet")
public class RestaurantCustomersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public RestaurantCustomersServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String restaurant_name = (String) session.getAttribute("restaurant_name");
		String owner_name = (String) session.getAttribute("owner_name");
		String pass_word = (String) session.getAttribute("pass_word");
		PrintWriter writer = response.getWriter();
		String htmlRespone = "<html>";
		
		try {
			Restaurant account = new Restaurant(restaurant_name, owner_name, pass_word);
			ArrayList<String> customer_usernames = account.get_reserved_customers();
			if (customer_usernames.size() > 0) {
				htmlRespone += "<form name=\"restaurantDeleteReservation\" method=\"post\" action=\"restaurantDeleteReservationServlet\">";
				for (int i = 0; i < customer_usernames.size(); i++) {
					htmlRespone += "<input type=\"radio\" id=\""+customer_usernames.get(i).toLowerCase()+"\" name=\"customer_user_name\" value=\""+customer_usernames.get(i).toUpperCase()+"\">";
					htmlRespone += "<label for=\""+customer_usernames.get(i).toLowerCase()+"\">"+customer_usernames.get(i).toUpperCase()+"</label><br>";
				}
				htmlRespone += "<input type=\"submit\" value=\"Delete\">";
				htmlRespone += "</form>";
			} else {
				htmlRespone += "<h2>There are no customers reserved at your restaurant at this time</h2>";
				htmlRespone += "<button onclick = \"window.location.href = 'RestaurantMenu.html';\">Menu</button>";
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		htmlRespone += "</html>";
		
		writer.println(htmlRespone);
		
	}

}
