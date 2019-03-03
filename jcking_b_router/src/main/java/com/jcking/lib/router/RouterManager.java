package com.jcking.lib.router;

import android.app.Application;
import android.content.Context;

import com.alibaba.android.arouter.launcher.ARouter;

/**
 * Router的管理类
 * @author Jcking
 * @time 2019/3/3 19:20
 */
public class RouterManager {

    /**
     * 初始化
     * @param application
     * @param debug
     */
    public static void init(Application application, boolean debug){
        if (debug) {
            //开启InstantRun之后，一定要在ARouter.init之前调用openDebug
            ARouter.openDebug();
            ARouter.openLog();
        }
        ARouter.init(application);
    }
}
