package com;

import java.util.ArrayList;
import java.util.List;

public class DataProxy implements Data, NotifyCopyService {

    protected RealData realData;

    protected int idx;

    protected List<CopyProxyService> copyWriteProxies;

    public DataProxy(int idx){
        this.idx = idx;
        copyWriteProxies = new ArrayList<>();
    }

    @Override
    public int get(int idx) {
        return realData == null ? 0 : realData.get(idx);
    }

    @Override
    public void set(int idx, int value) {
        if(realData == null){
            realData = new RealData(this.idx);
        }
        notifyCopies();
        realData.set(idx, value);
    }

    @Override
    public int size() {
        return realData == null ? idx : realData.size();
    }

    @Override
    public void add(CopyProxyService copyProxyService) {
        copyWriteProxies.add(copyProxyService);
    }

    @Override
    public void notifyCopies() {
        for(CopyProxyService copyProxyService : copyWriteProxies){
            copyProxyService.copy();
        }
        copyWriteProxies.clear();
    }
}
