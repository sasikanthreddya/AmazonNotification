package com.sasi.spring.api.AmazonDelivaryOrderSystem.service;

public interface DeliveryScoreService {

void submitDeliveryPoints(long points);

	
	long getCurrentScore();
}
