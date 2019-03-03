package com.jcking.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.launcher.ARouter;
import com.jcking.common.base.AppActivity;
import com.jcking.lib.router.RouterPath;
import com.jcking.lib.router.provider.ILoginProvider;

public class MainActivity extends AppActivity {

    private Button mLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLogin = (Button) findViewById(R.id.login_btn);
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ILoginProvider loginService = (ILoginProvider) ARouter.getInstance()
                        .build(RouterPath.LOGIN_SERVICE).navigation();
                if(loginService != null){
                    loginService.goToLogin(MainActivity.this);
                }
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
