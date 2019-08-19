package com.jcking.common.utils;

import android.app.Activity;
import android.os.Build;
import android.view.WindowManager;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebSettings;
import android.webkit.WebView;

/**
 * 加载web相关工具
 *
 * @author Jcking
 * @time 2019/4/29 22:26
 */
public class WebUtil {

    public static void initWebView(WebView webView) {
        WebSettings webSettings = webView.getSettings();
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        // setAcceptThirdPartyCookies 5.0以后默认为false 不能跨域访问cookie
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            cookieManager.setAcceptThirdPartyCookies(webView, true);
            cookieManager.flush();
        } else {
            CookieSyncManager.getInstance().sync();
        }
        webSettings.setJavaScriptEnabled(true);
        //自动打开窗搜索口
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
//            webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setSupportMultipleWindows(false);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setDisplayZoomControls(false);
        webSettings.setDomStorageEnabled(true);
        webSettings.setAllowContentAccess(true);
        webSettings.setTextSize(WebSettings.TextSize.NORMAL);
        webSettings.setSavePassword(false);
        webSettings.setGeolocationDatabasePath(webView.getContext().getFilesDir().getPath());
        webSettings.setGeolocationEnabled(true);
//        webSettings.setUserAgentString((String) TextUtils.concat(webSettings.getUserAgentString()
//                , userAgent, AppUtils.getVersionName(webView.getContext())));
        /**
         *应尽量避免使用,API 17中用@JavascriptInterface 代替addjavascriptInterface;
         *移除系统webkit内置的危险接口searchBoxJavaBridge_,accessibility,accessibilityTraversa
         */
        webView.removeJavascriptInterface("searchBoxJavaBridge_");
        webView.removeJavascriptInterface("accessibility");
        webView.removeJavascriptInterface("accessibilityTraversal");
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            WebView.setWebContentsDebuggingEnabled(debug);
//        }
        // https http 混合加载
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }

        if (webView.getContext() instanceof Activity) {
            Activity activity = (Activity) webView.getContext();
            activity.getWindow().setFlags(
                    WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                    WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
        }
    }
}
