package com.jcking.lib.pay;

import java.util.ArrayList;

/**
 * @author Jcking
 * @time 2017/7/13 16:00
 */
public class PayLoop implements IPayCallback {

    private ArrayList<IPayCallback> mList;

    private static volatile PayLoop sSingleton = null;

    private PayLoop() {
        mList = new ArrayList<>();
    }

    public static PayLoop getIntstance() {
        if (sSingleton == null) {
            synchronized (PayLoop.class) {
                if (sSingleton == null)
                    sSingleton = new PayLoop();
            }
        }
        return sSingleton;
    }

    public void add(IPayCallback callback) {
        mList.add(callback);
    }

    public void remove(IPayCallback callback) {
        mList.remove(callback);
    }

    public void clear() {
        mList.clear();
    }

    @Override
    public void paySuccess() {
        for (IPayCallback callback : mList)
            callback.paySuccess();
        clear();
    }

    @Override
    public void payFailure() {
        for (IPayCallback callback : mList)
            callback.payFailure();
        clear();
    }
}
