package com.sasi.spring.api.AmazonDelivaryOrderSystem.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sasi.spring.api.AmazonDelivaryOrderSystem.model.Order;


public class EmailService {
	
	private Order order;

	private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

	public EmailService(Order order) {
		this.order = order;
	}

	public void sendDeliveryNotification() {
		logger.info("EmailService - Administrative Notifications has send with deliverd product  " + order.getDescription());
	}


}
