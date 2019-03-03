package com.jcking.main;

import android.content.res.Configuration;

import com.jcking.lib.annotation.EFModuleAnnotation;
import com.jcking.lib.framework.module.interfaces.IApplicationDelegate;
import com.jcking.lib.log.JLog;

import java.util.Map;

/**
 * Main组件全局应用配置
 */
@EFModuleAnnotation(moduleName = "jcking_m_main",delegateName = "com.jcking.main.MainApplicationDelegate")
public class MainApplicationDelegate implements IApplicationDelegate {

    private static final String TAG = "MainApplicationDelegate";


    @Override
    public void onCreate() {
        JLog.d(TAG, "*------------------onCreate()---------------->");
    }

    @Override
    public void enterBackground() {
        JLog.d(TAG, "*------------------enterBackground()---------------->");
    }

    @Override
    public void enterForeground() {
        JLog.d(TAG, "*------------------enterForeground()---------------->");
    }

    @Override
    public void receiveRemoteNotification(Map<String, String> message) {
        JLog.d(TAG, "receiveRemoteNotification msg = " + message);
    }

    @Override
    public void onTerminate() {
        JLog.d(TAG, "*------------------onTerminate()---------------->");
    }

    @Override
    public void onConfigurationChanged(Configuration configuration) {
        JLog.d(TAG, "*------------------onConfigurationChanged()---------------->");
    }

    @Override
    public void onLowMemory() {
        JLog.d(TAG, "*------------------onLowMemory()---------------->");
    }

    @Override
    public void onTrimMemory(int var1) {
        JLog.d(TAG, "*------------------onTrimMemory()---------------->");
    }
}
