/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dmtech.coiloptimizer.bean;

/**
 *
 * @author Administrator
 */
public class OrderCoilMappingDetail<T> {

	private int groupId;
	private T coilIndex;
	private double allocatedOrderQty;
	private T orderIndex;
	private int cuttingPatternNumber;
	private int noOfCuts;
	private double stockWeight;
	private double stockWidth;
	private double stockLength;
	private double orderWidth;
	private int noOfOrderWidth;
	private double actualOrderWeight;
	private int processId;
	private int lengthWise;
	private int noOfPatiton;
	private double partionWeight;
	private double partionLength;
	private String batchNo;
	private String orderNo;
	private String orderLineItem;
	private String orderScheduleNumber;

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public double getAllocatedOrderQty() {
		return allocatedOrderQty;
	}

	public void setAllocatedOrderQty(double allocatedOrderQty) {
		this.allocatedOrderQty = allocatedOrderQty;
	}

	public int getNoOfCuts() {
		return noOfCuts;
	}

	public void setNoOfCuts(int noOfCuts) {
		this.noOfCuts = noOfCuts;
	}

	public String getOrderLineItem() {
		return orderLineItem;
	}

	public void setOrderLineItem(String orderLineItem) {
		this.orderLineItem = orderLineItem;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getOrderScheduleNumber() {
		return orderScheduleNumber;
	}

	public void setOrderScheduleNumber(String orderScheduleNumber) {
		this.orderScheduleNumber = orderScheduleNumber;
	}

	public double getActualOrderWeight() {
		return actualOrderWeight;
	}

	public void setActualOrderWeight(double actualOrderWeight) {
		this.actualOrderWeight = actualOrderWeight;
	}

	public int getCuttingPatternNumber() {
		return cuttingPatternNumber;
	}

	public void setCuttingPatternNumber(int cuttingPatternNumber) {
		this.cuttingPatternNumber = cuttingPatternNumber;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public int getLengthWise() {
		return lengthWise;
	}

	public void setLengthWise(int lengthWise) {
		this.lengthWise = lengthWise;
	}

	public int getNoOfOrderWidth() {
		return noOfOrderWidth;
	}

	public void setNoOfOrderWidth(int noOfOrderWidth) {
		this.noOfOrderWidth = noOfOrderWidth;
	}

	public int getNoOfPatiton() {
		return noOfPatiton;
	}

	public void setNoOfPatiton(int noOfPatiton) {
		this.noOfPatiton = noOfPatiton;
	}

	public double getOrderWidth() {
		return orderWidth;
	}

	public void setOrderWidth(double orderWidth) {
		this.orderWidth = orderWidth;
	}

	public double getPartionWeight() {
		return partionWeight;
	}

	public void setPartionWeight(double partionWeight) {
		this.partionWeight = partionWeight;
	}

	public int getProcessId() {
		return processId;
	}

	public void setProcessId(int processId) {
		this.processId = processId;
	}

	public double getStockWeight() {
		return stockWeight;
	}

	public void setStockWeight(double stockWeight) {
		this.stockWeight = stockWeight;
	}

	public double getStockWidth() {
		return stockWidth;
	}

	public void setStockWidth(double stockWidth) {
		this.stockWidth = stockWidth;
	}

	public T getCoilIndex() {
		return coilIndex;
	}

	public void setCoilIndex(T coilIndex) {
		this.coilIndex = coilIndex;
	}

	public T getOrderIndex() {
		return orderIndex;
	}

	public void setOrderIndex(T orderIndex) {
		this.orderIndex = orderIndex;
	}

	public double getPartionLength() {
		return partionLength;
	}

	public void setPartionLength(double partionLength) {
		this.partionLength = partionLength;
	}

	public double getStockLength() {
		return stockLength;
	}

	public void setStockLength(double stockLength) {
		this.stockLength = stockLength;
	}

	@Override
	public String toString() {
		return "OrderCoilMappingDetail [groupId=" + groupId + ", coilIndex=" + coilIndex + ", allocatedOrderQty="
				+ allocatedOrderQty + ", orderIndex=" + orderIndex + ", cuttingPatternNumber=" + cuttingPatternNumber
				+ ", noOfCuts=" + noOfCuts + ", stockWeight=" + stockWeight + ", stockWidth=" + stockWidth
				+ ", stockLength=" + stockLength + ", orderWidth=" + orderWidth + ", noOfOrderWidth=" + noOfOrderWidth
				+ ", actualOrderWeight=" + actualOrderWeight + ", processId=" + processId + ", lengthWise=" + lengthWise
				+ ", noOfPatiton=" + noOfPatiton + ", partionWeight=" + partionWeight + ", partionLength="
				+ partionLength + ", batchNo=" + batchNo + ", orderNo=" + orderNo + ", orderLineItem=" + orderLineItem
				+ ", orderScheduleNumber=" + orderScheduleNumber + "]";
	}

}
