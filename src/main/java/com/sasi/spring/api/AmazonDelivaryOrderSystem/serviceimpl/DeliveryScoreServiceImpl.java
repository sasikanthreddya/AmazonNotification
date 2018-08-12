package com.sasi.spring.api.AmazonDelivaryOrderSystem.serviceimpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.sasi.spring.api.AmazonDelivaryOrderSystem.service.DeliveryScoreService;


@Service
@Qualifier("DeliveryScoreService")
public class DeliveryScoreServiceImpl implements DeliveryScoreService {

	private long totalPoints;

	private static final Logger logger = LoggerFactory.getLogger(DeliveryScoreServiceImpl.class);

	public void submitDeliveryPoints(long points) {
		logger.info("DeliveryScoreService - total " + points + " points!");
		totalPoints += points;
	}

	public long getCurrentScore() {
		return totalPoints;
	}
}
