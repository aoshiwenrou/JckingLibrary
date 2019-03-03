package com.jcking.lib.framework.module.entity;

/**
 * 组件配置信息类
 *
 * @author Jcking
 * @time 2019/3/3 17:23
 */
public class ModuleInfo {
    private String moduleName;
    private String packageName;
    private String delegateName;

    public ModuleInfo() {
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getDelegateName() {
        return delegateName;
    }

    public void setDelegateName(String delegateName) {
        this.delegateName = delegateName;
    }
}
