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
public interface Group {

    List<Material> getMaterials();
    List<Order> getOrders();
    ParameterGroup getGroupParams();
    
    
}
