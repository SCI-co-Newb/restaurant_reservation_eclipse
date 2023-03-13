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
 * Servlet implementation class RestaurantLoginServlet
 */
@WebServlet("/restaurantLoginServlet")
public class RestaurantLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public RestaurantLoginServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// read form fields
		String restaurant_name = request.getParameter("restaurant_name");
		String owner_name = request.getParameter("owner_name");
        String password = request.getParameter("password");
        
        PrintWriter writer = response.getWriter();
        
        String htmlRespone = "<html>";
        
        try {
			Restaurant dummy = new Restaurant(restaurant_name, owner_name, password);
			if (dummy.get_approved()) {
				htmlRespone += "<h2>You have successfully logged in</h1>";
				dummy.update_last_login();
				HttpSession session = request.getSession();
				session.setAttribute("email", dummy.get_email());
				htmlRespone += "<button onclick = \"window.location.href = 'RestaurantMenu.html';\">Menu</button>";
			} else {
				htmlRespone += "<h2>Incorrect restaurant name, owner name, and/or password</h1>";
				htmlRespone += "<button onclick = \"window.location.href = 'RestaurantLogin.html';\">Try Again</button>";
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
