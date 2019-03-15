package com.jcking.library;


import com.jcking.lib.framework.base.BaseApplication;
import com.jcking.lib.log.JLog;
import com.jcking.lib.router.RouterManager;

public class FrameApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init(){
        //日志显示开关
        JLog.init(BuildConfig.DEBUG);
        //初始化路由
        RouterManager.init(this, BuildConfig.DEBUG);
    }
}
