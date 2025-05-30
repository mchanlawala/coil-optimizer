/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dmtech.coiloptimizer.bean;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Administrator
 */
public class CollectionUtil {

    public static <T> List<T> search(List<T> list, ISearch<T> searchInterface){
        List<T> newList = new ArrayList<T>();
        for(T t : list){
            if(searchInterface.compare(t)){
                newList.add(t);
            }
        }
        return newList;
    }

    public static interface ISearch<T>{
        public boolean compare(T t);
    }

    public static interface IList<T>{
        public Object collect(T t);
    }

    public static interface IIterate<T>{
        void process(T t);
    }

    public static <T> List collect(List<T> list, IList<T> collectInterface){
        List newList= new ArrayList();
        for(T t : list){
            System.out.println("###################" + collectInterface.collect(t));
            newList.add(collectInterface.collect(t));
        }
        return newList;
    }

    public static <T> Set collectUnique(List<T> list, IList<T> collectInterface){
        Set newSet= new HashSet();
        for(T t : list){
            newSet.add(collectInterface.collect(t));
        }
        return newSet;
    }

    public static <T> void each(List<T> list, IIterate<T> processInterface){
        for(T t : list){
            processInterface.process(t);
        }
    }

    public static <T> void each(Iterator<T> iterator, IIterate<T> processInterface){
        while(iterator.hasNext()){
            processInterface.process(iterator.next());
        }
    }
}
