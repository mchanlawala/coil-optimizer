/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dmtech.coiloptimizer.grouping.strategy.simple;


import java.util.List;

import com.dmtech.coiloptimizer.bean.Group;
import com.dmtech.coiloptimizer.bean.Material;
import com.dmtech.coiloptimizer.bean.Order;
import com.dmtech.coiloptimizer.bean.ParameterGroup;


/**
 *
 * @author Administrator
 */
public class SimpleGroup implements Group{

    private List<Material> materials;
    private List<Order> orders;
    private ParameterGroup groupParams;

    public SimpleGroup() {
    }

    public List<Material> getMaterials() {
        return materials;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setMaterials(List<Material> materials) {
        this.materials = materials;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public ParameterGroup getGroupParams() {
        return groupParams;
    }

    public void setGroupParams(ParameterGroup groupParams) {
        this.groupParams = groupParams;
    }

    
}
