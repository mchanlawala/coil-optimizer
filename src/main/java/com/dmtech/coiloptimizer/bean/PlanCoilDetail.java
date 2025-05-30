/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dmtech.coiloptimizer.bean;

/**
 *
 * @author Administrator
 */
public class PlanCoilDetail {

	private int groupdId;
	private String coilIndex;
	private double usableStockWidth;
	private double finalStockQuantity;
	private int age;
	private String stockType;
	private double length;
	private String coilStatus;
	private double thickness;

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getCoilIndex() {
		return coilIndex;
	}

	public void setCoilIndex(String coilIndex) {
		this.coilIndex = coilIndex;
	}

	public String getCoilStatus() {
		return coilStatus;
	}

	public void setCoilStatus(String coilStatus) {
		this.coilStatus = coilStatus;
	}

	public double getFinalStockQuantity() {
		return finalStockQuantity;
	}

	public void setFinalStockQuantity(double finalStockQuantity) {
		this.finalStockQuantity = finalStockQuantity;
	}

	public int getGroupdId() {
		return groupdId;
	}

	public void setGroupdId(int groupdId) {
		this.groupdId = groupdId;
	}

	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
	}

	public String getStockType() {
		return stockType;
	}

	public void setStockType(String stockType) {
		this.stockType = stockType;
	}

	public double getUsableStockWidth() {
		return usableStockWidth;
	}

	public void setUsableStockWidth(double usableStockWidth) {
		this.usableStockWidth = usableStockWidth;
	}

	public double getThickness() {
		return thickness;
	}

	public void setThickness(double thickness) {
		this.thickness = thickness;
	}

	@Override
	public String toString() {
		return "PlanCoilDetail [groupdId=" + groupdId + ", coilIndex=" + coilIndex + ", usableStockWidth="
				+ usableStockWidth + ", finalStockQuantity=" + finalStockQuantity + ", age=" + age + ", stockType="
				+ stockType + ", length=" + length + ", coilStatus=" + coilStatus + ", thickness=" + thickness + "]";
	}

}
