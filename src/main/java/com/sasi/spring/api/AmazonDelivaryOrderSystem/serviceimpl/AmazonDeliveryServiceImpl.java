package com.sasi.spring.api.AmazonDelivaryOrderSystem.serviceimpl;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.sasi.spring.api.AmazonDelivaryOrderSystem.exception.OrderAlreadyExistsException;
import com.sasi.spring.api.AmazonDelivaryOrderSystem.exception.OrderException;
import com.sasi.spring.api.AmazonDelivaryOrderSystem.model.Discount;
import com.sasi.spring.api.AmazonDelivaryOrderSystem.model.Order;
import com.sasi.spring.api.AmazonDelivaryOrderSystem.service.AmazonDeliveryService;
import com.sasi.spring.api.AmazonDelivaryOrderSystem.service.DeliveryScoreService;
import com.sasi.spring.api.AmazonDelivaryOrderSystem.service.EmailService;
import com.sasi.spring.api.AmazonDelivaryOrderSystem.service.OrderStorageService;
import com.sasi.spring.api.AmazonDelivaryOrderSystem.util.EmailServiceFactory;



@Service
@Qualifier("AmazonDeliveryService")
public class AmazonDeliveryServiceImpl implements AmazonDeliveryService {
	private static final Log logger=LogFactory.getLog(AmazonDeliveryServiceImpl.class);
	
	@Autowired
	private DeliveryScoreService deliveryScoreService;

	@Autowired
	private OrderStorageService orderStorageService;

	@Autowired
	private EmailServiceFactory emailServiceFactory;
	@Override
	public Order initOrder(String description, double basePrice, boolean premiumCustomer)
			throws OrderAlreadyExistsException {
		
		Order order = new Order();
		order.setDescription(description);
		order.setBasePrice(basePrice);
		order.setDelivered(false);
		order.setSent(false);
		order.setPremium(premiumCustomer);
		
		return order;
	}

	@Override
	public void addDiscount(Order order, Discount discount) {
		if (discount != null) {
			order.getDiscounts().add(discount);
		}
		calcFinalPrice(order);
		orderStorageService.store(order);
		
		
	}
	private void calcFinalPrice(Order order) {
		double totalDiscount = 0;

		for (Discount discount : order.getDiscounts()) {
			totalDiscount += discount.getPercent();
		}

		//calc discount
		double finalPrice = order.getBasePrice() - (order.getBasePrice() * totalDiscount / 100.0);

		//round 2 decimals chapucer
		finalPrice *= 100;
		finalPrice = Math.round(finalPrice);
		finalPrice /= 100;

		//set final price
		order.setFinalPrice(finalPrice);
		logger.info("final  product price after discount"+finalPrice);
	}


	@Override
	public void markSent(Order order, Date sendDate) throws OrderException {
		order.setSendDate(sendDate);
		Date estimatedDelivery = calcEstimatedDeliveryDate(order.isPremium());

		order.setEstimatedDelivery(estimatedDelivery);
		order.setSent(true);

		orderStorageService.store(order);
		
	}

	private Date calcEstimatedDeliveryDate(boolean premiumCustomer) {
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());

		if (premiumCustomer) {
			calendar.add(Calendar.DAY_OF_MONTH,ESTIMATED_DAYS_TO_DELIVER_PREMIUM);
		}else 
			calendar.add(Calendar.DAY_OF_MONTH,ESTIMATED_DAYS_TO_DELIVER_REGULAR);

		return calendar.getTime();
	}

	@Override
	public void markDelivered(Order order, Date deliverDate) throws OrderException {
		order.setDelivered(true);
		order.setRealDelivery(deliverDate);

		orderStorageService.store(order);

		long deliveryScore = calcDeliveryDateScore(order);

		//Submit score
		deliveryScoreService.submitDeliveryPoints(deliveryScore);

		//Send email notification
		EmailService emailService = emailServiceFactory.buildEmailService(order);

		emailService.sendDeliveryNotification();

		
	}

	private long calcDeliveryDateScore(Order order) {
		final long diff = order.getEstimatedDelivery().getTime() - order.getRealDelivery().getTime();
	      logger.info("estimated delivared is "+order.getEstimatedDelivery().getTime());
		return diff / (60 * 60 * 1000);
	}

}
