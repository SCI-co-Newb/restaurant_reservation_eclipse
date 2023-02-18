package com.example.hello.restaurant_reservation_eclipse;

import java.sql.SQLException;
import java.util.Scanner;
// add an email validator later

public class App {
	
	private static Scanner myObj = new Scanner(System.in);

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
					logged_in(dummy);
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
	
	private static void logged_in(Customer account) throws ClassNotFoundException, SQLException {
		while (true) {
			System.out.println("Menu:");
			System.out.println("1 - Your Reservations");
			System.out.println("2 - Settings");
			System.out.println("Anything other key is to go back");
			System.out.println("Enter:");
			
			String choice = myObj.nextLine();
			
			if (choice.equals("1")) {
				reservations(account);
			} else if (choice.equals("2")) {
				if (settings(account)) {
					break;
				}
			} else {
				break;
			}
		}
	}
	
	// TODO will implement later
	private static void reservations(Customer account) {
		while (true) {
			System.out.println("Reservations:");
			System.out.println("Anything other key is to go back");
			System.out.println("Enter:");
			
			/*String choice = */myObj.nextLine();
			
			break;
		}
	}
	
	private static boolean settings(Customer account) throws ClassNotFoundException, SQLException {
		while (true) {
			System.out.println("Settings:");
			System.out.println("1 - Delete Your Account");
			System.out.println("Anything other key is to go back");
			System.out.println("Enter:");
			
			String choice = myObj.nextLine();
			
			if (choice.equals("1")) {
				account.delete_this_customer();
				return true;
			} else {
				break;
			}
		}
		return false;
	}

}
