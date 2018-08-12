package com.sasi.spring.api.AmazonDelivaryOrderSystem.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sasi.spring.api.AmazonDelivaryOrderSystem.exception.OrderAlreadyExistsException;
import com.sasi.spring.api.AmazonDelivaryOrderSystem.exception.OrderException;
import com.sasi.spring.api.AmazonDelivaryOrderSystem.model.Discount;
import com.sasi.spring.api.AmazonDelivaryOrderSystem.model.Order;
import com.sasi.spring.api.AmazonDelivaryOrderSystem.service.AmazonDeliveryService;
import com.sasi.spring.api.AmazonDelivaryOrderSystem.service.DeliveryScoreService;

@RestController
public class OrderController {
	@Autowired
	private AmazonDeliveryService amazonDeliveryService;

	@Autowired
	private DeliveryScoreService deliveryScoreService;

	@GetMapping("/{productionDescription}")
	@ResponseBody
	public String home(@PathVariable String productionDescription ) throws OrderException, OrderAlreadyExistsException {

		final Date now = new Date();

		Order order = amazonDeliveryService.initOrder(productionDescription, 152.25, true);
		amazonDeliveryService.addDiscount(order, new Discount(
				"Promotion expanded", 10.0));
		amazonDeliveryService.markSent(order, now);
		amazonDeliveryService.markDelivered(order, now);

		return 
				"The delivery service has " + deliveryScoreService.getCurrentScore() + " points";
	}

}
