package com.dmtech.coiloptimizer.grouping.strategy.timed;

import java.util.Date;

import com.dmtech.coiloptimizer.bean.Material;
import com.dmtech.coiloptimizer.bean.Order;

public class MaterialOrderComposite implements Comparable<MaterialOrderComposite> {

	private Material materialObj;
	private Order orderObj;
	private boolean material;
	private boolean order;

	public MaterialOrderComposite(Material material) {
		super();
		this.materialObj = material;
		this.material = true;
	}

	public MaterialOrderComposite(Order order) {
		super();
		this.orderObj = order;
		this.order = true;
	}

	public boolean isMaterial() {
		return material;
	}

	public boolean isOrder() {
		return order;
	}

	public Date getDate() {
		if (isMaterial()) {
			return materialObj.getDate();
		} else {
			return orderObj.getDate();
		}
	}

	public Material getMaterialObj() {
		return materialObj;
	}

	public Order getOrderObj() {
		return orderObj;
	}

	public int compareTo(MaterialOrderComposite composite) {
		return getDate().compareTo(composite.getDate());
	}
}
