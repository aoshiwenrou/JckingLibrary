package com.jcking.lib.router.provider;

import android.app.Activity;

import com.alibaba.android.arouter.facade.template.IProvider;

/**
 * @author Jcking
 * @time 2019/3/3 19:08
 */
public interface ILoginProvider extends IProvider {
    void goToLogin(Activity activity);
}
