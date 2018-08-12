package com.sasi.spring.api.AmazonDelivaryOrderSystem.serviceimpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.sasi.spring.api.AmazonDelivaryOrderSystem.model.Order;
import com.sasi.spring.api.AmazonDelivaryOrderSystem.service.OrderStorageService;


@Service
@Qualifier("OrderStorageService")
public class OrderStorageServiceImpl implements OrderStorageService {

	private static final Logger logger = LoggerFactory.getLogger(OrderStorageServiceImpl.class);

	@Override
	public void store(Order order) {
		logger.info("OrderStorageService -order stored with descreption " + order.getDescription());
	}

	@Override
	public boolean exists(String description) {
		logger.info("OrderStorageService - order has allreday exist with " + description);
		return false;
	}

	
}

