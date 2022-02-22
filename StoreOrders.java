package com.example.project5;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;


/**
 * Class representation of a Store Orders
 * Contains methods for cancelling the order and export the order
 * @author Dhruvkumar Patel, Oluwadamola Olugboji
 */
public class StoreOrders implements Serializable {
	private ArrayList<Order> orderList = new ArrayList<Order>();
	/**
	 * Method to add the order into the store Order list
	 * @param order input of a order of a customer
	 */
	public void placeOrder(Order order)
	{
		orderList.add(order);
	}
	/**
	 * Method to remove the order or cancel the order from the store order.
	 * @param order input the order which need to be cancelled
	 */
	public void cancelOrder(Order order)
	{
		orderList.remove(order);
	}
	/**
	 * Method to get the whole list of store orders
	 * @return orderlist of the store orders
	 */
	public ArrayList<Order> getOrderList(){
		return orderList;
	}
	/**
	 * Method to export the store orders to the local location.
	 */
}
