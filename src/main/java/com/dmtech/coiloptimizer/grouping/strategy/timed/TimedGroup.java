/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dmtech.coiloptimizer.grouping.strategy.timed;

import java.util.ArrayList;
import java.util.List;

import com.dmtech.coiloptimizer.bean.Group;
import com.dmtech.coiloptimizer.bean.Material;
import com.dmtech.coiloptimizer.bean.Order;
import com.dmtech.coiloptimizer.bean.ParameterGroup;

/**
 *
 * @author Administrator
 */
public class TimedGroup implements Group {

	private List<Material> materials;
	private List<Order> orders;

	public TimedGroup() {
		materials = new ArrayList<Material>();
		orders = new ArrayList<Order>();
	}

	public TimedGroup(List<Material> materials, List<Order> orders) {
		this.materials = materials;
		this.orders = orders;
	}

	public List<Material> getMaterials() {
		return materials;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public ParameterGroup getGroupParams() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

}
