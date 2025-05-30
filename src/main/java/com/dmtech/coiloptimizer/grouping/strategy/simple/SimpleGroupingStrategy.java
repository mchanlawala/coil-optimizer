package com.dmtech.coiloptimizer.grouping.strategy.simple;

import com.dmtech.coiloptimizer.bean.ParameterGroup;
import com.dmtech.coiloptimizer.bean.CollectionUtil;
import com.dmtech.coiloptimizer.bean.GroupIterator;
import com.dmtech.coiloptimizer.bean.GroupingStrategy;
import com.dmtech.coiloptimizer.bean.Material;
import com.dmtech.coiloptimizer.bean.Order;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
 *
 * @author Administrator
 */
public class SimpleGroupingStrategy implements GroupingStrategy {

	public GroupIterator group(List<Order> orders, List<Material> materials, int planType, final int singleGroup) {

		Set<ParameterGroup> paramGroup = new HashSet<ParameterGroup>();

		paramGroup.addAll(CollectionUtil.collect(orders, new CollectionUtil.IList<Order>() {

			public Object collect(Order order) {
				return new ParameterGroup(order.getGrade(), order.getProductFamily(), order.getThickness(),
						singleGroup);
			}
		}));

		paramGroup.addAll(CollectionUtil.collect(materials, new CollectionUtil.IList<Material>() {

			public Object collect(Material material) {
				return new ParameterGroup(material.getGrade(), material.getProductFamily(), material.getThickness(),
						singleGroup);
			}
		}));

		return new SimpleGroupIterator(paramGroup, materials, orders, planType, singleGroup);
	}

}
