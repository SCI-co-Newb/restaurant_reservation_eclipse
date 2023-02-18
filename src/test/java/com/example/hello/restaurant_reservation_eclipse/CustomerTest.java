package com.example.hello.restaurant_reservation_eclipse;

import static org.junit.Assert.assertTrue;

import java.sql.SQLException;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class CustomerTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }
    
    @Test
    public void firstCustomer() throws ClassNotFoundException, SQLException
    {
    	Customer first = new Customer("another@hotmail.com", "another", "another");
    	assertTrue(first.get_approved());
    	first.delete_this_customer();
    }
}
