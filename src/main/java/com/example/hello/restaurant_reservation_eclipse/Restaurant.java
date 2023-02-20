package com.example.hello.restaurant_reservation_eclipse;

import java.sql.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Restaurant {
	
	private String restaurant_name;
	private String owner_name;
	private String email;
	private String pass_word;
	private boolean approved;
	
	public Restaurant (String restaurant_name, String owner_name, String email, String pass_word) throws ClassNotFoundException, SQLException {
		this.restaurant_name = restaurant_name.toLowerCase();
		this.owner_name = owner_name;
		this.email = email.toLowerCase();
		this.pass_word = pass_word;
		if (check_restaurant_name()) {
			restaurant_sign_up();
			this.approved = true;
		} else {
			this.approved = false;
		}
	}
	
	public Restaurant (String restaurant_name, String owner_name, String pass_word) throws ClassNotFoundException, SQLException {
		this.restaurant_name = restaurant_name.toLowerCase();
		this.owner_name = owner_name;
		this.pass_word = pass_word;
		this.email = check_r_name_o_name_p_word();
		if (this.email != null) {
			this.approved = true;
		} else {
			this.approved = false;
		}
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
	
	private void restaurant_sign_up () throws ClassNotFoundException, SQLException {
		String[] info = get_info();
        String url = info[0];
    	String userName = info[1];
    	String passWord = info[2];
        String query = "INSERT INTO restaurants (restaurant_name, owner_name, email, pass_word) VALUES ('"+this.restaurant_name+"', '"+this.owner_name+"', '"+this.email+"', '"+this.pass_word+"');";

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, userName, passWord);
        Statement st = con.createStatement();
        st.executeUpdate(query);
        
        st.close();
        con.close();
	}
	
	private boolean check_restaurant_name () throws SQLException, ClassNotFoundException {
    	String[] info = get_info();
        String url = info[0];
    	String userName = info[1];
    	String passWord = info[2];
        String query = "SELECT * FROM restaurants";

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, userName, passWord);
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);

        while (rs.next()) {
            if (rs.getString(2).equals(this.restaurant_name)) {
            	st.close();
                con.close();
                return false;
            }
        }
        
        st.close();
        con.close();

        return true;
    }
	
	private String check_r_name_o_name_p_word () throws SQLException, ClassNotFoundException {
    	String[] info = get_info();
        String url = info[0];
    	String userName = info[1];
    	String passWord = info[2];
        String query = "SELECT * FROM restaurants";

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, userName, passWord);
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);

        while (rs.next()) {
            if (rs.getString(2).equals(this.restaurant_name) && rs.getString(3).equals(this.owner_name) && rs.getString(5).equals(this.pass_word)) {
            	String e_mail = rs.getString(4);
            	st.close();
                con.close();
                return e_mail;
            }
        }
        
        st.close();
        con.close();

        return null;
    }
	
	private int get_restaurant_id () throws ClassNotFoundException, SQLException {
    	String[] info = get_info();
        String url = info[0];
    	String userName = info[1];
    	String passWord = info[2];
        String query = "SELECT id FROM restaurants WHERE restaurant_name='"+this.restaurant_name+"'";

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, userName, passWord);
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        rs.next();
        
        int output = rs.getInt(1);
        
        st.close();
        con.close();
        
        return output;
	}
	
	public ArrayList<String> get_reserved_customers () throws ClassNotFoundException, SQLException {
		String[] info = get_info();
        String url = info[0];
    	String userName = info[1];
    	String passWord = info[2];
    	String query = "SELECT user_name FROM customers WHERE restaurant_id='"+get_restaurant_id()+"'";
    	
    	Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, userName, passWord);
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        
        ArrayList<String> output = new ArrayList<>();
        
        while (rs.next()) {
        	output.add(rs.getString(1));
        }
        
        st.close();
        con.close();
        
        return output;
		
	}
	
	public boolean delete_this_restaurant () throws ClassNotFoundException, SQLException {
    	if (!this.approved) {
    		return false;
    	}
    	
    	String[] info = get_info();
        String url = info[0];
    	String userName = info[1];
    	String passWord = info[2];
        String query = "DELETE FROM restaurants WHERE restaurant_name='"+this.restaurant_name+"'";

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, userName, passWord);
        Statement st = con.createStatement();
        st.executeUpdate(query);
        
        st.close();
        con.close();
        
        return true;
        
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
	
	public boolean get_approved () {
    	return this.approved;
    }
	
}











