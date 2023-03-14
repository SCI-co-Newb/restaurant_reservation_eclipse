package com.example.hello.restaurant_reservation_eclipse;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import org.apache.commons.validator.routines.EmailValidator;
/**
 * Servlet implementation class RestaurantSignupServlet
 */
@WebServlet("/restaurantSignupServlet")
public class RestaurantSignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public RestaurantSignupServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// read form fields
		String email = request.getParameter("email");
		String restaurant_name = request.getParameter("restaurant_name");
		String owner_name = request.getParameter("owner_name");
        String password = request.getParameter("password");
        
        PrintWriter writer = response.getWriter();
        
        String htmlRespone = "<html>";
        
		if (!EmailValidator.getInstance().isValid(email)) {
			htmlRespone += "<h2>Email not in valid format</h2>";
			htmlRespone += "<button onclick = \"window.location.href = 'RestaurantSignup.html';\">Back</button>";
		} else {
			try {
				Restaurant dummy = new Restaurant(restaurant_name, owner_name, email, password);
				if (dummy.get_approved()) {
					htmlRespone += "<h2>You have successfuly signed up</h2>";
					htmlRespone += "<button onclick = \"window.location.href = 'Restaurants.html';\">Back</button>";
				} else {
					htmlRespone += "<h2>Email or username already exists</h2>";
					htmlRespone += "<button onclick = \"window.location.href = 'RestaurantSignup.html';\">Back</button>";
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				htmlRespone += "<h2>Something went wrong</h2>";
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				htmlRespone += "<h2>Something went wrong</h2>";
			}
		}
		
		htmlRespone += "</html>";
		
		writer.println(htmlRespone);
	}

}
