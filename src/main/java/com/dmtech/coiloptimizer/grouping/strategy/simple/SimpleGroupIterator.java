/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dmtech.coiloptimizer.grouping.strategy.simple;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.dmtech.coiloptimizer.bean.CollectionUtil;
import com.dmtech.coiloptimizer.bean.Group;
import com.dmtech.coiloptimizer.bean.GroupIterator;
import com.dmtech.coiloptimizer.bean.Material;
import com.dmtech.coiloptimizer.bean.Order;
import com.dmtech.coiloptimizer.bean.ParameterGroup;

/**
 *
 * @author Administrator
 */
public class SimpleGroupIterator implements GroupIterator {

	private Set<ParameterGroup> paramGroups;
	private List<Material> materials;
	private List<Order> orders;
	private boolean status;
	private int planningType;
	private int singleGroup;

	public SimpleGroupIterator(Set<ParameterGroup> paramGroups, List<Material> materials, List<Order> orders,
			int planningType, int singleGroup) {
		this.paramGroups = paramGroups;
		this.materials = materials;
		this.orders = orders;
		this.planningType = planningType;
		this.singleGroup = singleGroup;
	}

	public boolean hasNext() {
		if (planningType == 0) {
			return (!materials.isEmpty() && !orders.isEmpty()) && !status;
		} else {
			return (!orders.isEmpty()) && !status;
		}
	}

	public List<Group> next() {
		if (status) {
			return null;
		}

		List<Group> groups = new ArrayList<Group>();

		for (final ParameterGroup paramGroup : paramGroups) {
			SimpleGroup simpleGroup = new SimpleGroup();

			simpleGroup.setMaterials(CollectionUtil.search(materials, new CollectionUtil.ISearch<Material>() {

				public boolean compare(Material material) {
					return paramGroup.equals(new ParameterGroup(material.getGrade(), material.getProductFamily(),
							material.getThickness(), singleGroup));
				}
			}));

			simpleGroup.setOrders(CollectionUtil.search(orders, new CollectionUtil.ISearch<Order>() {

				public boolean compare(Order order) {
					return paramGroup.equals(new ParameterGroup(order.getGrade(), order.getProductFamily(),
							order.getThickness(), singleGroup));
				}
			}));

			simpleGroup.setGroupParams(paramGroup);

			groups.add(simpleGroup);
		}

		status = true;
		return groups;
	}

	public void remove() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

}
