package com.example.hello.restaurant_reservation_eclipse;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet("/logoutServlet")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public LogoutServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        PrintWriter writer = response.getWriter();
        
        String htmlRespone = "<html>";
          
        HttpSession session=request.getSession();  
        session.invalidate();  
          
        htmlRespone += "<h2>You are successfully logged out!</h2>";
        
        htmlRespone += "<button onclick = \"window.location.href = 'index.html';\">Home</button>";
        
        htmlRespone += "<html>";
          
        writer.println(htmlRespone);
	}

}
