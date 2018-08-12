package com.sasi.spring.api.AmazonDelivaryOrderSystem.model;

public class Discount {

	public String description;
	public double percent;
	public Discount(String description, double percent) {
		super();
		this.description = description;
		this.percent = percent;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPercent() {
		return percent;
	}
	public void setPercent(double percent) {
		this.percent = percent;
	}
	@Override
	public String toString() {
		return "Discount [description=" + description + ", percent=" + percent + "]";
	}
	
	
}
