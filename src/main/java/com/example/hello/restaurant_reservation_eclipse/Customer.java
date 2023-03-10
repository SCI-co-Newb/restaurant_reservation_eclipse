package com.example.hello.restaurant_reservation_eclipse;

import java.sql.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.time.LocalDateTime;

public class Customer {		// decided not to put id and restaurant_id here for security reasons
	
    private String email;
    private String user_name;
    private String pass_word;
    private boolean approved;

    public Customer (String email, String user_name, String pass_word) throws ClassNotFoundException, SQLException { // for sign-up
        this.email = email.toLowerCase();
        this.user_name = user_name;
        this.pass_word = pass_word;
        // first add a method to see if email already exists (remember capitalization doesn't matter)
        // second add a method to see if user_name already exists (capitalization does matter)
        if (check_email_user_name()) {
            customer_sign_up();
            this.approved = true;
        } else {
            this.approved = false;
        }
    }
    
    public Customer (String user_name, String pass_word) throws ClassNotFoundException, SQLException { // for log-in
        this.user_name = user_name;
        this.pass_word = pass_word;
        this.email = check_user_name_pass_word();
        if (this.email != null) {
            this.approved = true;
        } else {
            this.approved = false;
        }
    }
    
    // private methods begin
    
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

    private void customer_sign_up () throws ClassNotFoundException, SQLException {
    	String[] info = get_info();
        String url = info[0];
    	String userName = info[1];
    	String passWord = info[2];
        String query = "INSERT INTO customers (email, user_name, pass_word) VALUES ('"+this.email+"', '"+this.user_name+"', '"+this.pass_word+"');";

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, userName, passWord);
        Statement st = con.createStatement();
        st.executeUpdate(query);
        
        st.close();
        con.close();
    }

    private boolean check_email_user_name () throws SQLException, ClassNotFoundException {
    	String[] info = get_info();
        String url = info[0];
    	String userName = info[1];
    	String passWord = info[2];
        String query = "SELECT * FROM customers";

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, userName, passWord);
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);

        while (rs.next()) {
            if (rs.getString(2).equals(this.email) || rs.getString(3).equals(this.user_name)) {
            	st.close();
                con.close();
                return false;
            }
        }
        
        st.close();
        con.close();

        return true;
    }
    
    private String check_user_name_pass_word () throws SQLException, ClassNotFoundException {
    	String[] info = get_info();
        String url = info[0];
    	String userName = info[1];
    	String passWord = info[2];
        String query = "SELECT * FROM customers";

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, userName, passWord);
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);

        while (rs.next()) {
            if (rs.getString(3).equals(this.user_name) && rs.getString(4).equals(this.pass_word)) {
            	String e_mail = rs.getString(2);
            	st.close();
                con.close();
                return e_mail;
            }
        }
        
        st.close();
        con.close();

        return null;
    }
    
    // private methods end

    public boolean delete_this_customer () throws ClassNotFoundException, SQLException {
    	if (!this.approved) {
    		return false;
    	}
    	
    	String[] info = get_info();
        String url = info[0];
    	String userName = info[1];
    	String passWord = info[2];
        String query = "DELETE FROM customers WHERE email='"+this.email+"'";

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, userName, passWord);
        Statement st = con.createStatement();
        st.executeUpdate(query);
        
        st.close();
        con.close();
        
        return true;
        
    }
    
    public void set_restaurant_id (int id) throws ClassNotFoundException, SQLException {
    	String[] info = get_info();
        String url = info[0];
    	String userName = info[1];
    	String passWord = info[2];
    	String query = "UPDATE customers SET restaurant_id="+id+" WHERE email='"+this.email+"'";
    	
    	Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, userName, passWord);
        Statement st = con.createStatement();
        st.executeUpdate(query);
        
        st.close();
        con.close();
    }
    
    public void reset_restaurant_id () throws ClassNotFoundException, SQLException {
    	String[] info = get_info();
        String url = info[0];
    	String userName = info[1];
    	String passWord = info[2];
    	String query = "UPDATE customers SET restaurant_id=NULL WHERE email='"+this.email+"'";
    	
    	Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, userName, passWord);
        Statement st = con.createStatement();
        st.executeUpdate(query);
        
        st.close();
        con.close();
    	
    }
    
    public int get_reservation_id () throws SQLException, ClassNotFoundException {
    	String[] info = get_info();
        String url = info[0];
    	String userName = info[1];
    	String passWord = info[2];
    	String query = "SELECT restaurant_id FROM customers WHERE email='"+this.email+"'";
    	
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
    
    public void update_last_login () throws ClassNotFoundException, SQLException {
    	String[] info = get_info();
        String url = info[0];
    	String userName = info[1];
    	String passWord = info[2];
        String query = "UPDATE customers SET last_login='"+LocalDateTime.now().toString()+"' WHERE email='"+this.email+"'";
        
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, userName, passWord);
        Statement st = con.createStatement();
        st.executeUpdate(query);
        
        st.close();
        con.close();
    }
    
    public static void set_customer_restaurant_id_null (String user_name) throws ClassNotFoundException, SQLException {
    	String[] info = get_info();
        String url = info[0];
    	String userName = info[1];
    	String passWord = info[2];
    	String query = "UPDATE customers SET restaurant_id=NULL WHERE user_name='"+user_name+"'";
    	
    	Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, userName, passWord);
        Statement st = con.createStatement();
        st.executeUpdate(query);
        
        st.close();
        con.close();
    }
    
    // getter methods begin
    
    public boolean get_approved () {
    	return this.approved;
    }
    
    public String get_email () {
    	return this.email;
    }
    
    public String get_user_name () {
    	return this.user_name;
    }
    
    public String get_pass_word () {
    	return this.pass_word;
    }
    
    // getter methods end
}