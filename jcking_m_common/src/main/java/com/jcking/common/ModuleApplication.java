package com.jcking.common;

import android.app.Application;

import com.jcking.lib.router.RouterManager;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import org.xutils.x;

/**
 * 运行module模块的application，可以直接使用，也可以作为父类
 *
 * @author Jcking
 * @time 2019/3/3 21:44
 */
public class ModuleApplication extends Application {
    public static ModuleApplication mainApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        mainApplication = this;
        init();
    }

    public void init() {
        Logger.addLogAdapter(new AndroidLogAdapter() {
            @Override
            public boolean isLoggable(int priority, String tag) {
                return true;
            }
        });
        x.Ext.init(mainApplication);
        // 设置是否输出debug
        x.Ext.setDebug(false);
        RouterManager.init(this, true);
    }
}

