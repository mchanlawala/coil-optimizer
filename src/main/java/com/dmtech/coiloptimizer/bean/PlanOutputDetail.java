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
public class PlanOutputDetail<T> {

	private List<SlitSequenceDetail<T>> slitSequenceDetails;
	private List<OrderCoilMappingDetail<T>> orderCoilMappingDetails;

	public PlanOutputDetail(List<SlitSequenceDetail<T>> slitSequenceDetails,
			List<OrderCoilMappingDetail<T>> orderCoilMappingDetails) {
		this.slitSequenceDetails = slitSequenceDetails;
		this.orderCoilMappingDetails = orderCoilMappingDetails;
	}

	public List<OrderCoilMappingDetail<T>> getOrderCoilMappingDetails() {
		return orderCoilMappingDetails;
	}

	public List<SlitSequenceDetail<T>> getSlitSequenceDetails() {
		return slitSequenceDetails;
	}

	@Override
	public String toString() {
		return "PlanOutputDetail [slitSequenceDetails=" + slitSequenceDetails + ", orderCoilMappingDetails="
				+ orderCoilMappingDetails + "]";
	}

}
