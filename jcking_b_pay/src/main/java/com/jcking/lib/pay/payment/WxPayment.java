package com.jcking.lib.pay.payment;
// 接入流程：https://pay.weixin.qq.com/wiki/doc/api/app/app.php?chapter=8_5

import android.app.Activity;
import android.app.AlertDialog;

import com.jcking.lib.pay.R;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author Jcking
 * @time 2017/7/13 15:38
 */
public class WxPayment implements IPayment {

    private final Activity mActivity;
    private final String mPayInfo;
    private final IWXAPI mWXAPI;


    public WxPayment(Activity activity, String payInfo) {
        this.mActivity = activity;
        this.mPayInfo = payInfo;
        this.mWXAPI = WXAPIFactory.createWXAPI(activity, null);
    }

    @Override
    public void pay() {

        if (!mWXAPI.isWXAppInstalled()) {
            showSureDialog(mActivity.getString(R.string.order_pay_wx_not));
            return;
        }

        try {
            JSONObject json = new JSONObject(mPayInfo);
            PayReq req = new PayReq();
            req.appId = json.optString("appid");
            req.partnerId = json.optString("partnerid");
            req.prepayId = json.optString("prepayid");
            req.nonceStr = json.optString("noncestr");
            req.timeStamp = json.optString("timestamp");
            req.packageValue = json.optString("package");
            req.sign = json.optString("sign");
            // 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
            mWXAPI.registerApp(req.appId);
            mWXAPI.sendReq(req);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    void showSureDialog(final String msg) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(mActivity);
        alertDialog.setTitle(R.string.order_pay_failure);
        alertDialog.setMessage(msg);
        alertDialog.setNegativeButton(R.string.order_pay_failure_order_list, null);
        alertDialog.setCancelable(false);
        alertDialog.create().show();
    }
}
