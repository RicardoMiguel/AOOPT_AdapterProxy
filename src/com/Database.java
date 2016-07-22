package com;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Created by Ricardo on 05/04/2016.
 */
public class Database extends AbstractListModel {

    private ArrayList<DataProxy> ar ;

    public Database() {
        ar = new ArrayList<>();

        DataProxy data1 = new DataProxy(5);
        data1.set(0,6);
        data1.set(1,7);
        data1.set(2,8);
        ar.add(data1);

        DataProxy data2 = new DataProxy(5);
        data2.set(1, 9);
        data2.set(2, 8);
        data2.set(2, 1000);
        ar.add(data2);
    }

    public void add(DataProxy tab){
        ar.add(tab);
        fireIntervalAdded(this,ar.size()-1, ar.size());
    }

    public void remove(int idx){
        ar.remove(idx);
        fireIntervalRemoved(this,ar.size()-1 ,ar.size());
    }

    public int getSize() {
        /* ... */
        return ar.size();
    }

    public Object getElementAt(int index) {
        /* ... */
        return ar.get(index);
    }

}
