package com.jcking.lib.pay.factory;

import android.app.Activity;

import com.jcking.lib.pay.PayConstant;
import com.jcking.lib.pay.payment.IPayment;
import com.jcking.lib.pay.payment.WxPayment;

public class DefaultPaymentFactory extends PaymentFactory  {

    private Activity mActivity;

    public DefaultPaymentFactory(Activity activity){
        this.mActivity = activity;
    }

    @Override
    public IPayment createPayment(int payWay, String payInfo) {
        switch (payWay) {
//            case PayConstant.PAY_WAY_ALIPAY:
//                return new AliPayment(mActivity, payInfo);
            case PayConstant.PAY_WAY_WECHAT:
                return new WxPayment(mActivity, payInfo);
        }
        return null;
    }
}
