package com.dmtech.coiloptimizer.bean;

import java.util.Date;

public class Order {
	private String orderNo;
	private String orderLineItem;
	private String orderScheduleNumber;
	private String grade;
	private String productFamily;
	private double thickness;
	private Date date;
	private double width;
	private double weight;
	private double weightMin;
	private double weightMax;
	private String orderType;
	private double orderLength;
	private double orderlengthMin;
	private double orderlengthMax;
	private Date orderDeliveryDate;
	private double fgMinWt;
	private double fgMaxWt;
	private double fgMinLength;
	private double fgMaxLength;
	private int bucketId;

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getOrderLineItem() {
		return orderLineItem;
	}

	public void setOrderLineItem(String orderLineItem) {
		this.orderLineItem = orderLineItem;
	}

	public String getOrderScheduleNumber() {
		return orderScheduleNumber;
	}

	public void setOrderScheduleNumber(String orderScheduleNumber) {
		this.orderScheduleNumber = orderScheduleNumber;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getProductFamily() {
		return productFamily;
	}

	public void setProductFamily(String productFamily) {
		this.productFamily = productFamily;
	}

	public double getThickness() {
		return thickness;
	}

	public void setThickness(double thickness) {
		this.thickness = thickness;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public double getWeightMin() {
		return weightMin;
	}

	public void setWeightMin(double weightMin) {
		this.weightMin = weightMin;
	}

	public double getWeightMax() {
		return weightMax;
	}

	public void setWeightMax(double weightMax) {
		this.weightMax = weightMax;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public double getOrderLength() {
		return orderLength;
	}

	public void setOrderLength(double orderLength) {
		this.orderLength = orderLength;
	}

	public Date getOrderDeliveryDate() {
		return orderDeliveryDate;
	}

	public void setOrderDeliveryDate(Date orderDeliveryDate) {
		this.orderDeliveryDate = orderDeliveryDate;
	}

	public double getFgMinWt() {
		return fgMinWt;
	}

	public void setFgMinWt(double fgMinWt) {
		this.fgMinWt = fgMinWt;
	}

	public double getFgMaxWt() {
		return fgMaxWt;
	}

	public void setFgMaxWt(double fgMaxWt) {
		this.fgMaxWt = fgMaxWt;
	}

	public int getBucketId() {
		return bucketId;
	}

	public void setBucketId(int bucketId) {
		this.bucketId = bucketId;
	}

	public double getOrderlengthMin() {
		return orderlengthMin;
	}

	public void setOrderlengthMin(double orderlengthMin) {
		this.orderlengthMin = orderlengthMin;
	}

	public double getOrderlengthMax() {
		return orderlengthMax;
	}

	public void setOrderlengthMax(double orderlengthMax) {
		this.orderlengthMax = orderlengthMax;
	}

	public double getFgMinLength() {
		return fgMinLength;
	}

	public void setFgMinLength(double fgMinLength) {
		this.fgMinLength = fgMinLength;
	}

	public double getFgMaxLength() {
		return fgMaxLength;
	}

	public void setFgMaxLength(double fgMaxLength) {
		this.fgMaxLength = fgMaxLength;
	}

	@Override
	public String toString() {
		return "Order [orderNo=" + orderNo + ", orderLineItem=" + orderLineItem + ", orderScheduleNumber="
				+ orderScheduleNumber + ", grade=" + grade + ", productFamily=" + productFamily + ", thickness="
				+ thickness + ", date=" + date + ", width=" + width + ", weight=" + weight + ", weightMin=" + weightMin
				+ ", weightMax=" + weightMax + ", orderType=" + orderType + ", orderLength=" + orderLength
				+ ", orderlengthMin=" + orderlengthMin + ", orderlengthMax=" + orderlengthMax + ", orderDeliveryDate="
				+ orderDeliveryDate + ", fgMinWt=" + fgMinWt + ", fgMaxWt=" + fgMaxWt + ", fgMinLength=" + fgMinLength
				+ ", fgMaxLength=" + fgMaxLength + ", bucketId=" + bucketId + "]";
	}

}
