/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dmtech.coiloptimizer.grouping.strategy.timed;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.dmtech.coiloptimizer.bean.CollectionUtil;
import com.dmtech.coiloptimizer.bean.Group;
import com.dmtech.coiloptimizer.bean.GroupIterator;
import com.dmtech.coiloptimizer.bean.GroupingStrategy;
import com.dmtech.coiloptimizer.bean.Material;
import com.dmtech.coiloptimizer.bean.Order;

/**
 *
 * @author Administrator
 */
public class TimedGroupingStrategy implements GroupingStrategy{

    private GroupingStrategy simpleGroupingStrategy;

    public TimedGroupingStrategy(GroupingStrategy simpleGroupingStrategy) {
        this.simpleGroupingStrategy = simpleGroupingStrategy;
    }

    public GroupIterator group(List<Order> orders, List<Material> materials,int planType,int singleGroup) {
        List<MaterialOrderComposite> compositeList = new ArrayList<MaterialOrderComposite>();

        compositeList.addAll(CollectionUtil.collect(materials, new CollectionUtil.IList<Material>() {

            public Object collect(Material mat) {
                return new MaterialOrderComposite(mat);
            }
        }));

        compositeList.addAll(CollectionUtil.collect(orders, new CollectionUtil.IList<Order>() {

            public Object collect(Order order) {
                return new MaterialOrderComposite(order);
            }
        }));

        Collections.sort(compositeList);

        GroupIterator iterator = new TimedGroupIterator(compositeList);
        while(iterator.hasNext()){
            List<Group> timedGroups = iterator.next();
            if(!timedGroups.isEmpty()){
                Group group = timedGroups.get(0);
                return simpleGroupingStrategy.group(group.getOrders(), group.getMaterials(),planType,singleGroup);
            }
        }

        return null;
//        return new TimedGroupIterator(compositeList);
    }
}
