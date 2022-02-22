package com.example.project5;

import java.io.Serializable;

/**
 * Class representation of a Rounding off values
 * 
 * @author Dhruvkumar Patel, Oluwadamola Olugboji
 *
 */
public class Round implements Serializable {
	/**
	 * 
	 * @param value value to be round off
	 * @param places number of placs to be round off
	 * @return double value after rounding off
	 */
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}
	
	

}
