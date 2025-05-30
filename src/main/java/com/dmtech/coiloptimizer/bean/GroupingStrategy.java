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
public interface GroupingStrategy {
	GroupIterator group(List<Order> orders, List<Material> materials, int planType, int singleGroup);
}
