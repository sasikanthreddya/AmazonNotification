package com.sasi.spring.api.AmazonDelivaryOrderSystem.service;

import java.util.Date;

import com.sasi.spring.api.AmazonDelivaryOrderSystem.exception.OrderAlreadyExistsException;
import com.sasi.spring.api.AmazonDelivaryOrderSystem.exception.OrderException;
import com.sasi.spring.api.AmazonDelivaryOrderSystem.model.Discount;
import com.sasi.spring.api.AmazonDelivaryOrderSystem.model.Order;



public interface AmazonDeliveryService {

	public static final int ESTIMATED_DAYS_TO_DELIVER_PREMIUM = 2;
	public static final int ESTIMATED_DAYS_TO_DELIVER_REGULAR = 5;

	Order initOrder(String description, double basePrice, boolean premiumCustomer) throws OrderAlreadyExistsException;

	void addDiscount(Order order, Discount discount);

	void markSent(Order order, Date sendDate) throws OrderException;

	
	void markDelivered(Order order, Date deliverDate) throws OrderException;

}
