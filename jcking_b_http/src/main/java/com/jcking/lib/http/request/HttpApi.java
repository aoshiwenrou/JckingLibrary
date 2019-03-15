package com.jcking.lib.http.request;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

/**
 * 通用api接口，与RxJava配合使用
 *
 * @author Jcking
 * @time 2019/3/9 22:44
 */
interface HttpApi {

    @GET("{getPath}")
    Observable<String> get(@Path("getPath") String path, @QueryMap Map<String, String> pamars);

    @FormUrlEncoded
    @POST("{postPath}")
    Observable<String> post(@Path("postPath") String path, @FieldMap Map<String, String> pamars);

}
