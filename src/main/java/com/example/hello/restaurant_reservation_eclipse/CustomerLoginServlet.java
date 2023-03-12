package com.example.hello.restaurant_reservation_eclipse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CustomerLoginServlet
 */
@WebServlet("/customerLoginServlet")
public class CustomerLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public CustomerLoginServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// read form fields
		String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        PrintWriter writer = response.getWriter();
        
        String htmlRespone = "<html>";
        
        try {
			Customer dummy = new Customer(username, password);
			if (dummy.get_approved()) {
				htmlRespone += "<h2>You have successfully logged in</h1>";
				dummy.update_last_login();
				htmlRespone += "<button onclick = \"window.location.href = 'CustomerMenu.html';\">Menu</button>";
				htmlRespone += "<button onclick = \"window.location.href = 'Customers.html';\">Logout</button>";
			} else {
				htmlRespone += "<h2>Incorrect username and/or password</h1>";
				htmlRespone += "<button onclick = \"window.location.href = 'CustomerLogin.html';\">Try Again</button>";
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
