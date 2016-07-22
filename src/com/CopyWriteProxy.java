package com;

/**
 * Created by Ricardo on 16/04/2016.
 */
public class CopyWriteProxy extends DataProxy implements CopyProxyService{

    private DataProxy proxy;

    public CopyWriteProxy(DataProxy dataProxy) {
        super(dataProxy.size());
        this.proxy = dataProxy;
    }

    @Override
    public int get(int idx) {
        return realData == null ? proxy.get(idx) : super.get(idx);
    }

    @Override
    public void set(int idx, int value) {
        copy();
        realData.set(idx,value);
    }

    public void copy(){
        if(realData == null) {
            realData = new RealData(idx);
            for (int i = 0; i < idx; i++) {
                realData.set(i, proxy.get(i));
            }
        }
    }
}
