package com.jcking.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jcking.lib.router.RouterPath;
import com.jcking.lib.router.provider.ILoginProvider;

/**
 * Created by chenran3 on 2017/11/21.
 */

@Route(path = RouterPath.LOGIN_SERVICE, name = "登陆页面")
public class LoginService implements ILoginProvider {

    @Override
    public void init(Context context) {
    }

    @Override
    public void goToLogin(Activity activity) {
        Intent loginIntent = new Intent(activity, LoginActivity.class);
        activity.startActivity(loginIntent);
    }
}
