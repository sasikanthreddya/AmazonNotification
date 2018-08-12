package com.sasi.spring.api.AmazonDelivaryOrderSystem.service;

import com.sasi.spring.api.AmazonDelivaryOrderSystem.model.Order;

public interface OrderStorageService {
	void store(Order order);

	boolean exists(String description);
}
