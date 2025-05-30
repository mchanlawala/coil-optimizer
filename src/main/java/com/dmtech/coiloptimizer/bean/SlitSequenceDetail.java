/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dmtech.coiloptimizer.bean;

/**
 *
 * @author Administrator
 */
public class SlitSequenceDetail<T> {

    private T coilIndex;
    private int partIndex;
    private double width;
    private T orderIndex;
    private int cutType;
    private String batchNo;
    private String orderNo;
    private String orderLineItem;
    private String orderScheduleNumber;

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

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public void setCoilIndex(T coilIndex) {
        this.coilIndex = coilIndex;
    }

    public T getCoilIndex() {
        return coilIndex;
    }

    public int getCutType() {
        return cutType;
    }

    public void setCutType(int cutType) {
        this.cutType = cutType;
    }

    public void setOrderIndex(T orderIndex) {
        this.orderIndex = orderIndex;
    }

    public T getOrderIndex() {
        return orderIndex;
    }

    public int getPartIndex() {
        return partIndex;
    }

    public void setPartIndex(int partIndex) {
        this.partIndex = partIndex;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

	@Override
	public String toString() {
		return "SlitSequenceDetail [coilIndex=" + coilIndex + ", partIndex=" + partIndex + ", width=" + width
				+ ", orderIndex=" + orderIndex + ", cutType=" + cutType + ", batchNo=" + batchNo + ", orderNo="
				+ orderNo + ", orderLineItem=" + orderLineItem + ", orderScheduleNumber=" + orderScheduleNumber + "]";
	}
    
    
}
