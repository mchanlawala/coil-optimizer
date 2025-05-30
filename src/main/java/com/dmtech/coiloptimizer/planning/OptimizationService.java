/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dmtech.coiloptimizer.planning;

import com.dmtech.coiloptimizer.bean.PlanDetail;
import com.dmtech.coiloptimizer.bean.PlanOutputDetail;

/**
 *
 * @author Administrator
 */
public interface OptimizationService<T> {

	PlanOutputDetail<T> callEngine(PlanDetail planDetail);
}
