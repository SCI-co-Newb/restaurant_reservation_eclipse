package com.example.hello.restaurant_reservation_eclipse;

import java.sql.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Restaurant {
	
	public Restaurant () { // do later, include all parameters
		
	}
	
	private static String[] get_info () {
    	try {
    		// make sure to change the file location to where you actually stored your file
    		File myObj = new File("/Users/sahajdeep/projects/restaurant_reservation_eclipse/src/main/java/com/example/hello/restaurant_reservation_eclipse/mysql_info.txt");
    		Scanner myReader = new Scanner(myObj);
    		String[] mysql_info = new String[3];
    		for (int i = 0; myReader.hasNextLine(); i++) {
    			mysql_info[i] = myReader.nextLine();
    		}
    		myReader.close();
    		return mysql_info;
    	} catch (FileNotFoundException e) {
    		System.out.println("An error occurred.");
    	     e.printStackTrace();
    	     return null;
    	}
    }
	
	private static int get_restaurant_count () throws ClassNotFoundException, SQLException {
		String[] info = get_info();
        String url = info[0];
    	String userName = info[1];
    	String passWord = info[2];
    	String query = "SELECT id FROM restaurants";
    	
    	Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, userName, passWord);
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        
        int count = 0;
        while (rs.next()) {
        	count++;
        }
        
        st.close();
        con.close();
        
        return count;
	}
	
	public static String[][] get_all_restaurants () throws ClassNotFoundException, SQLException {
		String[] info = get_info();
        String url = info[0];
    	String userName = info[1];
    	String passWord = info[2];
        String query = "SELECT id, restaurant_name FROM restaurants";

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, userName, passWord);
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        
        String[][] output = new String[get_restaurant_count()][2];
        
        for (int i = 0; rs.next(); i++) {
        	output[i][0] = String.valueOf(rs.getInt(1));
        	output[i][1] = rs.getString(2);
        }
        
        st.close();
        con.close();

        return output;
	}
	
	public static String get_restaurant (int id) throws ClassNotFoundException, SQLException {
		String[] info = get_info();
        String url = info[0];
    	String userName = info[1];
    	String passWord = info[2];
        String query = "SELECT restaurant_name FROM restaurants WHERE id='"+id+"'";
        
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, userName, passWord);
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        rs.next();
        
        String output = rs.getString(1);
        
        st.close();
        con.close();
        
        return output;
	}
}











