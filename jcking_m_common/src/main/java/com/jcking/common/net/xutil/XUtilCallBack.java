package com.jcking.common.net.xutil;

import com.jcking.lib.log.JLog;

import org.xutils.common.Callback;
import org.xutils.ex.HttpException;


public class XUtilCallBack<ResultType> implements Callback.CommonCallback<ResultType>{
    private static final String TAG = "XUtilCallBack";

    @Override
    public void onSuccess(ResultType result) {
        //可以根据公司的需求进行统一的请求成功的逻辑处理
    }

    @Override
    public void onError(Throwable ex, boolean isOnCallback) {
        JLog.e(TAG,"Error Message :"+ ex.getMessage());
        //可以根据公司的需求进行统一的请求网络失败的逻辑处理
        if (ex instanceof HttpException) {

        }
    }

    @Override
    public void onCancelled(CancelledException cex) {

    }

    @Override
    public void onFinished() {

    }
}