package com.sasi.spring.api.AmazonDelivaryOrderSystem.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Order {
	private String description;
	private double basePrice;
	private List<Discount> discounts;
	private double finalPrice;

	private boolean sent;
	private Date sendDate;
	private Date estimatedDelivery;
	private boolean delivered;
	private Date realDelivery;
	private boolean premium;
	
	public Order() {
		discounts = new ArrayList<>();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(double basePrice) {
		this.basePrice = basePrice;
	}

	public List<Discount> getDiscounts() {
		return discounts;
	}

	public void setDiscounts(List<Discount> discounts) {
		this.discounts = discounts;
	}

	public double getFinalPrice() {
		return finalPrice;
	}

	public void setFinalPrice(double finalPrice) {
		this.finalPrice = finalPrice;
	}

	public boolean isSent() {
		return sent;
	}

	public void setSent(boolean sent) {
		this.sent = sent;
	}

	public Date getSendDate() {
		return sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	public Date getEstimatedDelivery() {
		return estimatedDelivery;
	}

	public void setEstimatedDelivery(Date estimatedDelivery) {
		this.estimatedDelivery = estimatedDelivery;
	}

	public boolean isDelivered() {
		return delivered;
	}

	public void setDelivered(boolean delivered) {
		this.delivered = delivered;
	}

	public Date getRealDelivery() {
		return realDelivery;
	}

	public void setRealDelivery(Date realDelivery) {
		this.realDelivery = realDelivery;
	}

	public boolean isPremium() {
		return premium;
	}

	public void setPremium(boolean premium) {
		this.premium = premium;
	}
	
}
