package com.example.hello.restaurant_reservation_eclipse;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
// add an email validator later

public class App {
	
	private static Scanner myObj = new Scanner(System.in);

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		System.out.println("Welcome to Restaurant Reservation!");
		
		while (true) {
			System.out.println("\nEnter 1 for Customers, and 2 for Restaurants (Any other key to quit):");
			
			String choice = myObj.nextLine();
			
			if (choice.equals("1")) {
				customer_side();
			} else if (choice.equals("2")) {
				restaurant_side();
			} else {
				break;
			}
		}
		
		System.out.println("\nThis is the end.");
		myObj.close();
	}
	
	private static void customer_side() throws ClassNotFoundException, SQLException {	// customer page
		while (true) {
			System.out.println("\nEnter 1 to sign-up and 2 to log-in (anything else to quit):");
			
			String choice = myObj.nextLine();
			
			if (choice.equals("1")) {
				System.out.println("\nEnter email:");
				String email = myObj.nextLine();
				
				System.out.println("Enter username:");
				String username = myObj.nextLine();
				
				System.out.println("Enter password:");
				String password = myObj.nextLine();
				
				Customer dummy = new Customer(email, username, password);
				
				if (dummy.get_approved()) {
					System.out.println("\nYou have succesfully signed-up!\n");
				} else {
					System.out.println("\nEmail or username already exist.\n");
				}
				
			} else if (choice.equals("2")) {
				System.out.println("\nEnter username:");
				String username = myObj.nextLine();
				
				System.out.println("Enter password:");
				String password = myObj.nextLine();
				
				Customer dummy = new Customer(username, password);
				
				if (dummy.get_approved()) {
					System.out.println("\nYou have succesfully logged-in!\n");
					logged_in(dummy);
				} else {
					System.out.println("\nWrong username or password.\n");
				}
				
			} else {
				break;
				
			}
		}
	}
	
	private static void restaurant_side() throws ClassNotFoundException, SQLException {	// restaurant page
		while (true) {
			System.out.println("\nEnter 1 to sign-up and 2 to log-in (anything else to quit):");
			
			String choice = myObj.nextLine();
			
			if (choice.equals("1")) {
				System.out.println("\nEnter restaurant name:");
				String restaurant_name = myObj.nextLine();
				
				System.out.println("Enter owner name:");
				String owner_name = myObj.nextLine();
				
				System.out.println("Enter email:");
				String email = myObj.nextLine();
				
				System.out.println("Enter password:");
				String password = myObj.nextLine();
				
				Restaurant dummy = new Restaurant(restaurant_name, owner_name, email, password);
				
				if (dummy.get_approved()) {
					System.out.println("\nYou have succesfully signed-up!\n");
				} else {
					System.out.println("\nRestaurant name already exist.\n");
				}
				
			} else if (choice.equals("2")) {
				System.out.println("\nEnter restaurant name:");
				String restaurant_name = myObj.nextLine();
				
				System.out.println("Enter owner name:");
				String owner_name = myObj.nextLine();
				
				System.out.println("Enter password:");
				String password = myObj.nextLine();
				
				Restaurant dummy = new Restaurant(restaurant_name, owner_name, password);
				
				if (dummy.get_approved()) {
					System.out.println("\nYou have succesfully logged-in!\n");
					logged_in(dummy);
				} else {
					System.out.println("\nWrong restaurant name, owner name, or password.\n");
				}
				
			} else {
				break;
				
			}
		}
	}
	
	// customer methods start
	
	private static void logged_in(Customer account) throws ClassNotFoundException, SQLException {
		while (true) {
			System.out.println("Menu:");
			System.out.println("1 - Your Reservations");
			System.out.println("2 - Settings");
			System.out.println("(Any other key is to go back)");
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
	
	private static void reservations(Customer account) throws ClassNotFoundException, SQLException {
		String[][] all_restaurants = Restaurant.get_all_restaurants();
		while (true) {
			System.out.println("Current Reservation:");
			int r_id = account.get_reservation_id();
			if (r_id > 0) {
				System.out.println(Restaurant.get_restaurant(r_id) + "\n");
				System.out.println("Type 0 to cancel reservation, anything else to quit:");
				
				String choice = myObj.nextLine();
				
				if (choice.equals("0")) {
					account.reset_restaurant_id();
				} else {
					break;
				}
			} else {
				System.out.println("Currently you have no reservation.\n");
				System.out.println("Restaurants:");
				for (int i = 0; i < all_restaurants.length; i++) {
					System.out.println((i+1)+" - "+all_restaurants[i][1]);
				}
				System.out.println("Enter one of the above numbers to reserve, anything else to quit:");
				
				String choice = myObj.nextLine();
				
				try {
					Integer num = Integer.parseInt(choice);
					if (num > 0 && num <= all_restaurants.length) {
						account.set_restaurant_id(Integer.parseInt(all_restaurants[num-1][0]));
					} else {
						break;
					}
				} catch (NumberFormatException e) {
					break;
				}
			}
			
			break;
		}
	}
	
	private static boolean settings(Customer account) throws ClassNotFoundException, SQLException {
		while (true) {
			System.out.println("Settings:");
			System.out.println("1 - Delete Your Account");
			System.out.println("(Any other key is to go back)");
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
	
	// customer methods end
	
	// restaurant methods start
	
	private static void logged_in(Restaurant account) throws ClassNotFoundException, SQLException {
		while (true) {
			System.out.println("Menu:");
			System.out.println("1 - Your Customers");
			System.out.println("2 - Settings");
			System.out.println("(Any other key is to go back)");
			System.out.println("Enter:");
			
			String choice = myObj.nextLine();
			
			if (choice.equals("1")) {
				reserved_customers(account);
			} else if (choice.equals("2")) {
				if (settings(account)) {
					break;
				}
			} else {
				break;
			}
		}
	}
	
	private static void reserved_customers(Restaurant account) throws ClassNotFoundException, SQLException {
		while (true) {
			System.out.println("Customers:");
			ArrayList<String> customer_usernames = account.get_reserved_customers();
			if (customer_usernames.size() > 0) {
				for (int i = 0; i < customer_usernames.size(); i++) {
					System.out.println((i+1)+" - "+customer_usernames.get(i));
				}
				System.out.println("Enter one of the numbers above if you want to delete that customers reservation.");
			} else {
				System.out.println("There are no customers currently reserved at your restaurant.");
			}
			System.out.println("(Any other key is to go back)");
			System.out.println("Enter:");
			
			String choice = myObj.nextLine();
			
			try {
				Integer num = Integer.parseInt(choice);
				if (num > 0 && num <= customer_usernames.size()) {
					Customer.set_customer_restaurant_id_null(customer_usernames.get(num-1));
				} else {
					break;
				}
			} catch (NumberFormatException e) {
				break;
			}
		}
	}
	
	private static boolean settings(Restaurant account) throws ClassNotFoundException, SQLException {
		while (true) {
			System.out.println("Settings:");
			System.out.println("1 - Delete Your Account");
			System.out.println("(Any other key is to go back)");
			System.out.println("Enter:");
			
			String choice = myObj.nextLine();
			
			if (choice.equals("1")) {
				account.delete_this_restaurant();
				return true;
			} else {
				break;
			}
		}
		return false;
	}

}
