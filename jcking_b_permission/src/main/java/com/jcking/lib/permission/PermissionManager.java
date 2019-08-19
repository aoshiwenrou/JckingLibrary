package com.jcking.lib.permission;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import com.jcking.lib.log.JLog;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * 权限相关工具类
 *
 * @author Jcking
 * @time 2019/3/19 23:33
 */
public class PermissionManager {

    /**
     * 请求权限
     *
     * @param activity
     * @param callback
     * @param permissions
     */
    @SuppressLint("CheckResult")
    public static void requestPermissions(FragmentActivity activity, final PermissionCallback callback, final String... permissions) {
        RxPermissions rxPermissions = new RxPermissions(activity);
        rxPermissions.request(permissions).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(@NonNull Boolean granted) throws Exception {
                JLog.d(permissions + " request : " + granted);
                if (callback == null)
                    return;
                if (granted) {
                    callback.onPermissionGranted(null);
                } else {
                    callback.onPermissionDenied(null);
                }
            }
        });
    }

    /**
     * 请求权限
     *
     * @param fragment
     * @param callback
     * @param permissions
     */
    @SuppressLint("CheckResult")
    public static void requestPermissions(Fragment fragment, final PermissionCallback callback, final String... permissions) {
        RxPermissions rxPermissions = new RxPermissions(fragment);
        rxPermissions.request(permissions).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(@NonNull Boolean granted) throws Exception {
                JLog.d(permissions + " request : " + granted);
                if (callback == null)
                    return;
                if (granted) {
                    callback.onPermissionGranted(null);
                } else {
                    callback.onPermissionDenied(null);
                }
            }
        });
    }


    /**
     * 挨个请求权限
     *
     * @param activity
     * @param callback
     * @param permissions
     */
    @SuppressLint("CheckResult")
    public static void requestEachPermission(FragmentActivity activity, final PermissionCallback callback, final String... permissions) {
        RxPermissions rxPermissions = new RxPermissions(activity);
        rxPermissions.requestEach(permissions).subscribe(new Consumer<Permission>() {
            @Override
            public void accept(@NonNull Permission permission) throws Exception {
                if (callback == null)
                    return;

                if (permission.granted) {
                    JLog.d("Permission : [" + permission.name + "] is granted");
                    callback.onPermissionGranted(permission);
                } else if (permission.shouldShowRequestPermissionRationale) {
                    //拒绝权限请求
                    callback.onPermissionDenied(permission);
                } else {
                    // 拒绝权限请求,并不再询问
                    // 需要进入设置界面去设置权限
                    callback.onNeverAskAgain(permission);
                }
            }
        });

    }

    /**
     * 挨个请求权限
     *
     * @param fragment
     * @param callback
     * @param permissions
     */
    @SuppressLint("CheckResult")
    public static void requestEachPermission(Fragment fragment, final PermissionCallback callback, final String... permissions) {
        RxPermissions rxPermissions = new RxPermissions(fragment);
        rxPermissions.requestEach(permissions).subscribe(new Consumer<Permission>() {
            @Override
            public void accept(@NonNull Permission permission) throws Exception {
                if (callback == null)
                    return;

                if (permission.granted) {
                    JLog.d("Permission : [" + permission.name + "] is granted");
                    callback.onPermissionGranted(permission);
                } else if (permission.shouldShowRequestPermissionRationale) {
                    //拒绝权限请求
                    callback.onPermissionDenied(permission);
                } else {
                    // 拒绝权限请求,并不再询问
                    // 需要进入设置界面去设置权限
                    callback.onNeverAskAgain(permission);
                }
            }
        });

    }

    /**
     * 查看权限是否已申请
     *
     * @param context
     * @param permissions
     * @return
     */
    public static boolean checkPermissions(Context context, String... permissions) {
        for (String permission : permissions) {
            if (!isAppliedPermission(context, permission))
                return false;
        }
        return true;
    }

    /**
     * 查看权限是否已申请
     *
     * @param context
     * @param permission
     * @return
     */
    private static boolean isAppliedPermission(Context context, String permission) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return context.checkSelfPermission(permission) ==
                    PackageManager.PERMISSION_GRANTED;
        }
        return true;
    }
}
