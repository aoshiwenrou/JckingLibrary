package com.jcking.lib.pay.factory;

import android.app.Activity;

import com.jcking.lib.pay.payment.IPayment;

public class DefaultPaymentFactory extends PaymentFactory  {

    private Activity mActivity;

    public DefaultPaymentFactory(Activity activity){
        this.mActivity = activity;
    }

    @Override
    public IPayment createPayment(int payWay, String payInfo) {
        switch (payWay) {
            // TODO 在这里处理支付事件
//            case PayConstant.ORDER_PAY_ALIPAY:
//                return new AliPayment(mActivity, payInfo);
//            case PayConstant.ORDER_PAY_WECHAT:
//                return new WxPayment(mActivity, payInfo);
        }
        return null;
    }
}
