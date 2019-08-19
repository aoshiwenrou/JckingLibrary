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

    @GET("{path}")
    Observable<String> get(@Path("path") String path, @QueryMap Map<String, String> pamars);

    @GET("{path}/{path2}")
    Observable<String> get(@Path("path") String path, @Path("path2") String path2, @QueryMap Map<String, String> pamars);

    @GET("{path}/{path2}/{path3}")
    Observable<String> get(@Path("path") String path, @Path("path2") String path2, @Path("path3") String path3, @QueryMap Map<String, String> pamars);

    @FormUrlEncoded
    @POST("{path}")
    Observable<String> post(@Path("path") String path, @FieldMap Map<String, String> pamars);

    @FormUrlEncoded
    @POST("{path}/{path2}")
    Observable<String> post(@Path("path") String path, @Path("path2") String path2, @FieldMap Map<String, String> pamars);

    @FormUrlEncoded
    @POST("{path}/{path2}/{path3}")
    Observable<String> post(@Path("path") String path, @Path("path2") String path2, @Path("path3") String path3, @FieldMap Map<String, String> pamars);

}
