package com.example.project5;

import java.io.Serializable;

/**
 * Class representation of Topping
 * This class has getter and setter methods implementing the instance variables
 * @author Dhruvkumar Patel, Oluwadamola Olugboji
 *
 */
public class Topping implements Serializable {
	private String toppingName;
	/**
	 * Constructor
	 */
	public Topping()
	{
		toppingName = " ";
	}
	/**
	 * Constructor with topping name as input
	 * @param toppingName input 
	 */
	public Topping(String toppingName)
	{
		this.toppingName = toppingName;
	}
	/**
	 * Method for Setting the topping Name 
	 * @param toppingName input to set
	 */
	public void setToppingName(String toppingName)
	{
		this.toppingName = toppingName;
	}
	/**
	 * Method to get topping Name
	 * @return String  of topping Name
	 */
	public String getToppingName()
	{
		return toppingName;
	}
	

}
