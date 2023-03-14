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
 * Servlet implementation class DeleteRestaurant
 */
@WebServlet("/deleteRestaurantServlet")
public class DeleteRestaurantServlet extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteRestaurantServlet() {
        super();
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
			account.delete_this_restaurant();
			htmlRespone += "<h2>Successfully Deleted Your Account</h2>";
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		htmlRespone += "<button onclick = \"window.location.href = 'index.html';\">Home</button>";
		htmlRespone += "</html>";
		
		writer.println(htmlRespone);
	}

}
