package com.jcking.lib.framework.module.interfaces;

import android.content.res.Configuration;

import java.util.Map;

/**
 * 各个组件的Application接口类，定义了一些生命周期的对应接口
 *
 * @author Jcking
 * @time 2019/3/3 17:24
 */
public interface IApplicationDelegate {

    void onCreate();

    void enterBackground();

    void enterForeground();

    void receiveRemoteNotification(Map<String, String> map);

    void onTerminate();

    void onConfigurationChanged(Configuration configuration);

    void onLowMemory();

    void onTrimMemory(int var1);
}
