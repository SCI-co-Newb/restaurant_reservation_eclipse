package com.example.hello.restaurant_reservation_eclipse;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

public class RestaurantTest {
	
	@Test
	public void firstRestaurant() throws ClassNotFoundException, SQLException {
		Restaurant first = new Restaurant("t", "t", "t", "t");
		assertTrue(first.get_approved());
		first.delete_this_restaurant();
	}

}
