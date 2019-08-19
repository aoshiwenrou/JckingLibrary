package com.jcking.lib.http.request;

import android.text.TextUtils;

import com.jcking.lib.http.HttpSettings;
import com.jcking.lib.http.interceptor.LogInterceptor;
import com.jcking.lib.http.interceptor.ToStringConverterFactory;
import com.jcking.lib.log.JLog;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Http请求器，用来调用{@link HttpApi}进行get和Post操作
 *
 * @author Jcking
 * @time 2019/3/9 23:09
 */
public class HttpRequester {

    private static final String TAG = HttpRequester.class.getSimpleName();
    private HashMap<String, HttpApi> mRetrofitMap = new HashMap<>();

    private static class Holder {
        private static HttpRequester singleton = new HttpRequester();
    }

    private HttpRequester() {
    }

    public static HttpRequester get() {
        return Holder.singleton;
    }

    /**
     * 重新创建已有的各Api
     */
    public synchronized void rebuild() {
        if (mRetrofitMap.size() == 0)
            return;
        HashMap<String, HttpApi> temp = new HashMap<>();
        for (String s : mRetrofitMap.keySet())
            temp.put(s, createRetrofit(s).create(HttpApi.class));
        mRetrofitMap.clear();
        mRetrofitMap = temp;
    }

    /**
     * 根据baseUrl，在{@link #mRetrofitMap}中获取一个{@link HttpApi}。
     * 如果没有，则创建一个对应的Retrofit，并生成HttpApi
     *
     * @param baseUrl
     * @return
     */
    private synchronized HttpApi getHttpApi(String baseUrl) {
        if (TextUtils.isEmpty(baseUrl))
            return null;
        if (mRetrofitMap.containsKey(baseUrl))
            return mRetrofitMap.get(baseUrl);
        HttpApi api = createRetrofit(baseUrl).create(HttpApi.class);
        mRetrofitMap.put(baseUrl, api);
        return api;
    }

    private Retrofit createRetrofit(String baseUrl) {
        return new Retrofit.Builder().baseUrl(baseUrl)
                //使用String适配器
                .addConverterFactory(new ToStringConverterFactory())
                //使用Gson
                .addConverterFactory(GsonConverterFactory.create())
                //使用RxJava作为回调适配器
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(getOkHttpsClient())
                .build();
    }

    /**
     * 生成 OkHttpClient
     *
     * @return
     */
    @NonNull
    private OkHttpClient getOkHttpsClient() {
        OkHttpClient.Builder builder = createBuilderByInterceptors()
                //加入一个请求日志输出的拦截器
                .addNetworkInterceptor(new LogInterceptor())
                //设置超时
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS);
        if (HttpSettings.getInstance().isUseSSL()) {
            try {
                useSSL(builder);
            } catch (Exception e) {
                e.printStackTrace();
                JLog.e(TAG, e);
            }
        }
        return builder.build();
    }

    /**
     * 设置https 访问的时候对所有证书都进行信任
     *
     * @throws Exception
     */
    private void useSSL(OkHttpClient.Builder builder) throws Exception {
        final X509TrustManager trustManager = new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        };

        SSLContext sslContext = SSLContext.getInstance("SSL");
        sslContext.init(null, new TrustManager[]{trustManager}, new SecureRandom());
        SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

        builder.sslSocketFactory(sslSocketFactory, trustManager).hostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        });
    }

    /**
     * 读取HttpSettings中的拦截器设置，添加到OkHttpClient中
     *
     * @return
     */
    private OkHttpClient.Builder createBuilderByInterceptors() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        List<Interceptor> interceptors = HttpSettings.getInstance().interceptors();
        if (interceptors != null && interceptors.size() > 0)
            for (Interceptor i : interceptors)
                builder.addInterceptor(i);
        return builder;
    }

    /**
     * 获取一个GET方法的接口方法，使用在{@link com.jcking.lib.http.HttpSettings}中预设的baseUrl
     *
     * @param url
     * @param params
     */
    public Observable<String> httpGet(String url, Map<String, String> params) {
        return httpGet(HttpSettings.getInstance().getBaseUrl(), url, params);
    }

    /**
     * 获取一个get方法的接口方法。例如完整请求为：http:localhost/user/info，
     * 其中baseUrl为http:localhost/，则url为user/info。
     * 如果baseUrl为http:localhost，则url为/user/info。
     *
     * @param baseUrl
     * @param url
     * @param params
     */
    public Observable<String> httpGet(String baseUrl, String url, Map<String, String> params) {
        HttpApi api = getHttpApi(baseUrl);
        if(api == null)
        excuteParams(params);
        String[] urlPath = url.split("/");
        switch (urlPath.length) {
            case 1:
                return api.get(urlPath[0], params);
            case 2:
                return api.get(urlPath[0], urlPath[1], params);
            default:
                return api.get(urlPath[0], urlPath[1], urlPath[2], params);
        }
    }

    /**
     * 获取一个post方法的接口方法，使用在{@link com.jcking.lib.http.HttpSettings}中预设的baseUrl
     *
     * @param url
     * @param params
     */
    public Observable<String> httpPost(String url, Map<String, String> params) {
        return httpPost(HttpSettings.getInstance().getBaseUrl(), url, params);
    }

    /**
     * 获取一个post方法的接口方法。例如完整请求为：http:localhost/user/info，
     * 其中baseUrl为http:localhost/，则url为user/info。
     * 如果baseUrl为http:localhost，则url为/user/info。
     *
     * @param baseUrl
     * @param url
     * @param params
     */
    public Observable<String> httpPost(String baseUrl, String url, Map<String, String> params) {
        HttpApi api = getHttpApi(baseUrl);
        excuteParams(params);
        String[] urlPath = url.split("/");
        switch (urlPath.length) {
            case 1:
                return api.post(urlPath[0], params);
            case 2:
                return api.post(urlPath[0], urlPath[1], params);
            default:
                return api.post(urlPath[0], urlPath[1], urlPath[2], params);
        }
    }

    /**
     * 对参数进行再加工。如果有值为null，则默认为空字符串
     *
     * @param params
     */
    private void excuteParams(Map<String, String> params) {
        if (params == null || params.isEmpty())
            return;
        for (String key : params.keySet())
            if (TextUtils.isEmpty(params.get(key)))
                params.put(key, "");
    }

    /**
     * 清空数据
     */
    public void clear() {
        mRetrofitMap.clear();
    }
}
