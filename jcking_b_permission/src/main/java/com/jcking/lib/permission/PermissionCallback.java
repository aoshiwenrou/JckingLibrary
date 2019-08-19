package com.jcking.lib.permission;

import com.jcking.lib.log.JLog;
import com.tbruyelle.rxpermissions2.Permission;

/**
 * 权限请求回调
 *
 * @author Jcking
 * @time 2019/3/20 16:38
 */
public abstract class PermissionCallback {

    /**
     * 权限同意
     *
     * @param permission
     */
    public abstract void onPermissionGranted(Permission permission);

    /**
     * 权限拒绝，但是可以再次申请
     *
     * @param permission
     */
    public void onPermissionDenied(Permission permission) {
        JLog.w("Permission : [" + permission.name + "] is denied");
    }

    /**
     * 权限拒绝，并且拒绝再次申请。此时只能去"设置"中授权
     *
     * @param permission
     */
    public void onNeverAskAgain(Permission permission) {
        JLog.w("Permission : [" + permission.name + "] is neever ask again");
    }
}
