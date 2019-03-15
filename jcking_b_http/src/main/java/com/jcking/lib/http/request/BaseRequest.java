package com.jcking.lib.http.request;

import com.jcking.lib.http.HttpSettings;

import java.util.Map;

import io.reactivex.Observable;

/**
 * 各模块进行网络请求的基础类
 *
 * @author Jcking
 * @time 2019/3/10 00:15
 */
public class BaseRequest {

    protected String getBaseUrl() {
        return HttpSettings.getInstance().getBaseUrl();
    }

    public Observable<String> get(String url, Map<String,String> params){
        return HttpRequester.get().httpGet(getBaseUrl(), url, params);
    }

    public Observable<String> post(String url, Map<String,String> params){
        return HttpRequester.get().httpPost(getBaseUrl(), url, params);
    }
}
