/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dmtech.coiloptimizer.bean;

import java.util.List;

/**
 *
 * @author Administrator
 */
public class PlanDetail {

	private String planId;
	private int scnerioType;
	private double lastPartWeight;
	private double maxAdditionalTrim;
	private int multiParting;
	private double maxSideTrim;
	private int maxcut;

	private List<PlanCoilDetail> coilDetail;
	private List<PlanOrderDetail> orderDetail;
	private List<PlanParameterDetail> parameterDetail;

	public PlanDetail(String planId, int scnerioType, double lastPartWeight, double maxAdditionalTrim, int multiParting,
			double maxSideTrim, int maxCut) {
		super();
		this.planId = planId;
		this.scnerioType = scnerioType;
		this.lastPartWeight = lastPartWeight;
		this.maxAdditionalTrim = maxAdditionalTrim;
		this.multiParting = multiParting;
		this.maxSideTrim = maxSideTrim;
		this.maxcut = maxCut;
	}

	public int getMaxcut() {
		return maxcut;
	}

	public void setMaxcut(int maxcut) {
		this.maxcut = maxcut;
	}

	public List<PlanCoilDetail> getCoilDetail() {
		return coilDetail;
	}

	public void setCoilDetail(List<PlanCoilDetail> coilDetail) {
		this.coilDetail = coilDetail;
	}

	public double getLastPartWeight() {
		return lastPartWeight;
	}

	public void setLastPartWeight(double lastPartWeight) {
		this.lastPartWeight = lastPartWeight;
	}

	public double getMaxAdditionalTrim() {
		return maxAdditionalTrim;
	}

	public void setMaxAdditionalTrim(double maxAdditionalTrim) {
		this.maxAdditionalTrim = maxAdditionalTrim;
	}

	public double getMaxSideTrim() {
		return maxSideTrim;
	}

	public void setMaxSideTrim(double maxSideTrim) {
		this.maxSideTrim = maxSideTrim;
	}

	public int getMultiParting() {
		return multiParting;
	}

	public void setMultiParting(int multiParting) {
		this.multiParting = multiParting;
	}

	public List<PlanOrderDetail> getOrderDetail() {
		return orderDetail;
	}

	public void setOrderDetail(List<PlanOrderDetail> orderDetail) {
		this.orderDetail = orderDetail;
	}

	public List<PlanParameterDetail> getParameterDetail() {
		return parameterDetail;
	}

	public void setParameterDetail(List<PlanParameterDetail> parameterDetail) {
		this.parameterDetail = parameterDetail;
	}

	public String getPlanId() {
		return planId;
	}

	public void setPlanId(String planId) {
		this.planId = planId;
	}

	public int getScnerioType() {
		return scnerioType;
	}

	public void setScnerioType(int scnerioType) {
		this.scnerioType = scnerioType;
	}

	@Override
	public String toString() {
		return "PlanDetail [planId=" + planId + ", scnerioType=" + scnerioType + ", lastPartWeight=" + lastPartWeight
				+ ", maxAdditionalTrim=" + maxAdditionalTrim + ", multiParting=" + multiParting + ", maxSideTrim="
				+ maxSideTrim + ", maxcut=" + maxcut + ", coilDetail=" + coilDetail + ", orderDetail=" + orderDetail
				+ ", parameterDetail=" + parameterDetail + "]";
	}

}
