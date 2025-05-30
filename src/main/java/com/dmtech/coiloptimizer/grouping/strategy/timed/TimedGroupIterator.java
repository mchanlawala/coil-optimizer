/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dmtech.coiloptimizer.grouping.strategy.timed;

import java.util.Arrays;
import java.util.List;

import com.dmtech.coiloptimizer.bean.Group;
import com.dmtech.coiloptimizer.bean.GroupIterator;

/**
 *
 * @author Administrator
 */
public class TimedGroupIterator implements GroupIterator{

    private List<MaterialOrderComposite> compositeList;
    private int counter;

    public TimedGroupIterator(List<MaterialOrderComposite> compositeList) {
        this.compositeList = compositeList;
    }

    public boolean hasNext() {
        return counter < compositeList.size()-1;
    }

    public List<Group> next() {
        if(!hasNext()){
            return null;
        }
        
        Group timeGroup = new TimedGroup();
        for (int i = counter, j = i+1; i < compositeList.size() - 1 && j < compositeList.size(); i++, j++) {
            addGroupMember(i, timeGroup);

            counter = i+1;
            if (compositeList.get(i).isOrder() && compositeList.get(j).isMaterial()) {
                break;
            }else if(j == compositeList.size()-1){
                addGroupMember(j, timeGroup);
            }
        }

        return Arrays.asList(timeGroup);
    }

    public void remove() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private void addGroupMember(int i, Group timeGroup) {
        if (compositeList.get(i).isOrder()) {
            timeGroup.getOrders().add(compositeList.get(i).getOrderObj());
        } else {
            timeGroup.getMaterials().add(compositeList.get(i).getMaterialObj());
        }
    }

}
