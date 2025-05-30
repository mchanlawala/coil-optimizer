/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dmtech.coiloptimizer.bean;

/**
 *
 * @author Administrator
 */
public class ParameterGroup {

	private String grade;
	private String productFamily;
	private double thickness;
	private int singleGroup;

	public ParameterGroup(String grade, String productFamily, double thickness, int singleGroup) {
		this.grade = grade;
		this.productFamily = productFamily;
		this.thickness = thickness;
		this.singleGroup = singleGroup;
	}

	public String getGrade() {
		return grade;
	}

	public String getProductFamily() {
		return productFamily;
	}

	public double getThickness() {
		return thickness;
	}

	public int getSingleGroup() {
		return singleGroup;
	}

	public void setSingleGroup(int singleGroup) {
		this.singleGroup = singleGroup;
	}

	@Override
	public int hashCode() {
		return 7 * (grade.hashCode() * ((int) thickness) * productFamily.hashCode());
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof ParameterGroup)) {
			return false;
		}

		ParameterGroup paramGroup = (ParameterGroup) obj;

		if (singleGroup == 1) {
			return true;
		} else {
			return grade.equalsIgnoreCase(paramGroup.grade) && productFamily.equalsIgnoreCase(paramGroup.productFamily)
					&& thickness == paramGroup.thickness;
		}
	}

	@Override
	public String toString() {
		return "ParameterGroup [grade=" + grade + ", productFamily=" + productFamily + ", thickness=" + thickness
				+ ", singleGroup=" + singleGroup + "]";
	}

}
