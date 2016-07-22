package com;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ricardo on 05/04/2016.
 */
public class RealData implements Data {

    private List<Integer> dataList;

    public RealData(int idx) {
        dataList = new ArrayList<>(idx);

        for(int i = 0; i < idx; i++){
            dataList.add(0);
        }
    }

    @Override
    public int get(int idx) {
        return dataList.get(idx);
}

    @Override
    public void set(int idx, int value) {
        dataList.set(idx,value);
    }

    @Override
    public int size() {
        return dataList.size();
    }
}
