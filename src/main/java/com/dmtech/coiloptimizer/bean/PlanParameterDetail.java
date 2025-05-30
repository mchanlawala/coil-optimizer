/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dmtech.coiloptimizer.bean;

/**
 *
 * @author Administrator
 */
public class PlanParameterDetail {

	private int groupId;
	private String grade;
	private String productFamily;
	private double thickness;
	private double minCoilWeight;
	private double maxCoilWeight;
	private double minWidth;
	private double maxWidth;
	private double optimumWidth;
	private double maxEndTrim;
	private double maxEndPiece;
	private double maxDensity;
	private int maxNoOfCut;
	private int maxBucketNumber;
	private double minFGLength;
	private double maxFGLength;
	private double minLength;
	private double maxLength;

	public double getMinLength() {
		return minLength;
	}

	public void setMinLength(double minLength) {
		this.minLength = minLength;
	}

	public double getMaxLength() {
		return maxLength;
	}

	public void setMaxLength(double maxLength) {
		this.maxLength = maxLength;
	}

	public double getMinFGLength() {
		return minFGLength;
	}

	public void setMinFGLength(double minFGLength) {
		this.minFGLength = minFGLength;
	}

	public double getMaxFGLength() {
		return maxFGLength;
	}

	public void setMaxFGLength(double maxFGLength) {
		this.maxFGLength = maxFGLength;
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

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public int getMaxBucketNumber() {
		return maxBucketNumber;
	}

	public void setMaxBucketNumber(int maxBucketNumber) {
		this.maxBucketNumber = maxBucketNumber;
	}

	public double getMaxCoilWeight() {
		return maxCoilWeight;
	}

	public void setMaxCoilWeight(double maxCoilWeight) {
		this.maxCoilWeight = maxCoilWeight;
	}

	public double getMaxDensity() {
		return maxDensity;
	}

	public void setMaxDensity(double maxDensity) {
		this.maxDensity = maxDensity;
	}

	public double getMaxEndPiece() {
		return maxEndPiece;
	}

	public void setMaxEndPiece(double maxEndPiece) {
		this.maxEndPiece = maxEndPiece;
	}

	public double getMaxEndTrim() {
		return maxEndTrim;
	}

	public void setMaxEndTrim(double maxEndTrim) {
		this.maxEndTrim = maxEndTrim;
	}

	public int getMaxNoOfCut() {
		return maxNoOfCut;
	}

	public void setMaxNoOfCut(int maxNoOfCut) {
		this.maxNoOfCut = maxNoOfCut;
	}

	public double getMaxWidth() {
		return maxWidth;
	}

	public void setMaxWidth(double maxWidth) {
		this.maxWidth = maxWidth;
	}

	public double getMinCoilWeight() {
		return minCoilWeight;
	}

	public void setMinCoilWeight(double minCoilWeight) {
		this.minCoilWeight = minCoilWeight;
	}

	public double getMinWidth() {
		return minWidth;
	}

	public void setMinWidth(double minWidth) {
		this.minWidth = minWidth;
	}

	public double getOptimumWidth() {
		return optimumWidth;
	}

	public void setOptimumWidth(double optimumWidth) {
		this.optimumWidth = optimumWidth;
	}

	@Override
	public String toString() {
		return "PlanParameterDetail [groupId=" + groupId + ", grade=" + grade + ", productFamily=" + productFamily
				+ ", thickness=" + thickness + ", minCoilWeight=" + minCoilWeight + ", maxCoilWeight=" + maxCoilWeight
				+ ", minWidth=" + minWidth + ", maxWidth=" + maxWidth + ", optimumWidth=" + optimumWidth
				+ ", maxEndTrim=" + maxEndTrim + ", maxEndPiece=" + maxEndPiece + ", maxDensity=" + maxDensity
				+ ", maxNoOfCut=" + maxNoOfCut + ", maxBucketNumber=" + maxBucketNumber + ", minFGLength=" + minFGLength
				+ ", maxFGLength=" + maxFGLength + ", minLength=" + minLength + ", maxLength=" + maxLength + "]";
	}

}
