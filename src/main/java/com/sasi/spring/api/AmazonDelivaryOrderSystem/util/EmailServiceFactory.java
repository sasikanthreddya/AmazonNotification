package com.sasi.spring.api.AmazonDelivaryOrderSystem.util;

import org.springframework.stereotype.Component;

import com.sasi.spring.api.AmazonDelivaryOrderSystem.model.Order;
import com.sasi.spring.api.AmazonDelivaryOrderSystem.service.EmailService;

@Component
public class EmailServiceFactory {
	
	public EmailService buildEmailService(Order order) {
		return new EmailService(order);
	}
}
