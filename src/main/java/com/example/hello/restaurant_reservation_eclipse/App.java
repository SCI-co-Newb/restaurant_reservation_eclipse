package com.example.hello.restaurant_reservation_eclipse;

import java.sql.SQLException;
import java.util.Scanner;

public class App {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Scanner myObj = new Scanner(System.in);
		while (true) {
			System.out.println("Enter 1 to sign-up and 2 to log-in (anything else to quit):");
			
			String choice = myObj.nextLine();
			
			if (choice.equals("1")) {
				System.out.println("Enter email:");
				String email = myObj.nextLine();
				
				System.out.println("Enter username:");
				String username = myObj.nextLine();
				
				System.out.println("Enter password:");
				String password = myObj.nextLine();
				
				Customer dummy = new Customer(email, username, password);
				
				if (dummy.get_approved()) {
					System.out.println("You have succesfully signed-up!\n");
				} else {
					System.out.println("Email or username already exist.\n");
				}
				
			} else if (choice.equals("2")) {
				System.out.println("Enter username:");
				String username = myObj.nextLine();
				
				System.out.println("Enter password:");
				String password = myObj.nextLine();
				
				Customer dummy = new Customer(username, password);
				
				if (dummy.get_approved()) {
					System.out.println("You have succesfully logged-in!\n");
					break;
				} else {
					System.out.println("Wrong username or password.\n");
				}
				
			} else {
				break;
				
			}
		}
		System.out.println("This is the end.");
		myObj.close();
	}

}
