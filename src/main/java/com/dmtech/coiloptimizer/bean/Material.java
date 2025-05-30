package com.dmtech.coiloptimizer.bean;

import java.util.Date;

public class Material {

	private String batchNo;
	private String grade;
	private String productFamily;
	private double thickness;
	private double width;
	private double weight;
	private double length;
	private int age;
	private Date date;
	private String stockType;
	private String coliStatus;
	private String parentBatchNo;
	private String motherBatchNo;

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getStockType() {
		return stockType;
	}

	public void setStockType(String stockType) {
		this.stockType = stockType;
	}

	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
	}

	public String getColiStatus() {
		return coliStatus;
	}

	public void setColiStatus(String coliStatus) {
		this.coliStatus = coliStatus;
	}

	public String getParentBatchNo() {
		return parentBatchNo;
	}

	public void setParentBatchNo(String parentBatchNo) {
		this.parentBatchNo = parentBatchNo;
	}

	public String getMotherBatchNo() {
		return motherBatchNo;
	}

	public void setMotherBatchNo(String motherBatchNo) {
		this.motherBatchNo = motherBatchNo;
	}

	@Override
	public String toString() {
		return "Material [batchNo=" + batchNo + ", grade=" + grade + ", productFamily=" + productFamily + ", thickness="
				+ thickness + ", date=" + date + ", width=" + width + ", weight=" + weight + ", age=" + age
				+ ", stockType=" + stockType + ", length=" + length + ", coliStatus=" + coliStatus + ", parentBatchNo="
				+ parentBatchNo + ", motherBatchNo=" + motherBatchNo + "]";
	}

}
