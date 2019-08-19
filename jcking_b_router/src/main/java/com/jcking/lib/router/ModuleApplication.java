package com.jcking.lib.router;

import android.app.Application;

/**
 * 运行module模块的application，可以直接使用，也可以作为父类
 * TODO 这个类应该放在业务模块的common部分
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
//        x.Ext.init(mainApplication);
//        // 设置是否输出debug
//        x.Ext.setDebug(false);
        RouterManager.init(this, true);
    }
}

