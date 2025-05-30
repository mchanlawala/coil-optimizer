/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dmtech.coiloptimizer.bean;

import java.util.Date;

/**
 *
 * @author Administrator
 */
public class PlanOrderDetail {

	private int groupId;
	private String orderIndex;
	private String orderLineNumber;
	private double orderWidth;
	private double orderThickness;
	private double minThickness;
	private double maxThickness;
	private double orderQuantity;
	private double orderMinQuantity;
	private double orderMaxQuantity;
	private String orderType;
	private double orderLength;
	private Date deliveryDate;
	private double minCoilWeight;
	private double maxCoilWeight;
	private double minCoilLength;
	private double maxCoilLength;
	private int bucketId;

	public double getOrderThickness() {
		return orderThickness;
	}

	public void setOrderThickness(double orderThickness) {
		this.orderThickness = orderThickness;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public double getMaxCoilWeight() {
		return maxCoilWeight;
	}

	public void setMaxCoilWeight(double maxCoilWeight) {
		this.maxCoilWeight = maxCoilWeight;
	}

	public double getMaxThickness() {
		return maxThickness;
	}

	public void setMaxThickness(double maxThickness) {
		this.maxThickness = maxThickness;
	}

	public double getMinCoilWeight() {
		return minCoilWeight;
	}

	public void setMinCoilWeight(double minCoilWeight) {
		this.minCoilWeight = minCoilWeight;
	}

	public double getMinThickness() {
		return minThickness;
	}

	public void setMinThickness(double minThickness) {
		this.minThickness = minThickness;
	}

	public String getOrderIndex() {
		return orderIndex;
	}

	public void setOrderIndex(String orderIndex) {
		this.orderIndex = orderIndex;
	}

	public double getOrderLength() {
		return orderLength;
	}

	public void setOrderLength(double orderLength) {
		this.orderLength = orderLength;
	}

	public double getOrderMaxQuantity() {
		return orderMaxQuantity;
	}

	public void setOrderMaxQuantity(double orderMaxQuantity) {
		this.orderMaxQuantity = orderMaxQuantity;
	}

	public double getOrderMinQuantity() {
		return orderMinQuantity;
	}

	public void setOrderMinQuantity(double orderMinQuantity) {
		this.orderMinQuantity = orderMinQuantity;
	}

	public double getOrderQuantity() {
		return orderQuantity;
	}

	public void setOrderQuantity(double orderQuantity) {
		this.orderQuantity = orderQuantity;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public double getOrderWidth() {
		return orderWidth;
	}

	public void setOrderWidth(double orderWidth) {
		this.orderWidth = orderWidth;
	}

	public void setBucketId(int bucketId) {
		this.bucketId = bucketId;
	}

	public int getBucketId() {
		return bucketId;
	}

	public String getOrderLineNumber() {
		return orderLineNumber;
	}

	public void setOrderLineNumber(String orderLineNumber) {
		this.orderLineNumber = orderLineNumber;
	}

	public double getMinCoilLength() {
		return minCoilLength;
	}

	public void setMinCoilLength(double minCoilLength) {
		this.minCoilLength = minCoilLength;
	}

	public double getMaxCoilLength() {
		return maxCoilLength;
	}

	public void setMaxCoilLength(double maxCoilLength) {
		this.maxCoilLength = maxCoilLength;
	}

	@Override
	public String toString() {
		return "PlanOrderDetail [groupId=" + groupId + ", orderIndex=" + orderIndex + ", orderLineNumber="
				+ orderLineNumber + ", orderWidth=" + orderWidth + ", orderThickness=" + orderThickness
				+ ", minThickness=" + minThickness + ", maxThickness=" + maxThickness + ", orderQuantity="
				+ orderQuantity + ", orderMinQuantity=" + orderMinQuantity + ", orderMaxQuantity=" + orderMaxQuantity
				+ ", orderType=" + orderType + ", orderLength=" + orderLength + ", deliveryDate=" + deliveryDate
				+ ", minCoilWeight=" + minCoilWeight + ", maxCoilWeight=" + maxCoilWeight + ", minCoilLength="
				+ minCoilLength + ", maxCoilLength=" + maxCoilLength + ", bucketId=" + bucketId
				+ ", getOrderThickness()=" + getOrderThickness() + ", getDeliveryDate()=" + getDeliveryDate()
				+ ", getGroupId()=" + getGroupId() + ", getMaxCoilWeight()=" + getMaxCoilWeight()
				+ ", getMaxThickness()=" + getMaxThickness() + ", getMinCoilWeight()=" + getMinCoilWeight()
				+ ", getMinThickness()=" + getMinThickness() + ", getOrderIndex()=" + getOrderIndex()
				+ ", getOrderLength()=" + getOrderLength() + ", getOrderMaxQuantity()=" + getOrderMaxQuantity()
				+ ", getOrderMinQuantity()=" + getOrderMinQuantity() + ", getOrderQuantity()=" + getOrderQuantity()
				+ ", getOrderType()=" + getOrderType() + ", getOrderWidth()=" + getOrderWidth() + ", getBucketId()="
				+ getBucketId() + ", getOrderLineNumber()=" + getOrderLineNumber() + ", getMinCoilLength()="
				+ getMinCoilLength() + ", getMaxCoilLength()=" + getMaxCoilLength() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

}
