package com.jcking.lib.pay;

import android.app.Activity;

import com.jcking.lib.pay.factory.PaymentFactory;
import com.jcking.lib.pay.payment.IPayment;

/**
 * @author Jcking
 * @time 2017/7/13 15:30
 */
public class Payer {

    private Activity mActivity;
    private String mPayInfo;
    private int mPayWay;

    public Payer(Activity activity) {
        this.mActivity = activity;
    }

    public void pay(PayWay payWay, String payInfo) {
        pay(payWay.value, payInfo);
    }

    public void pay(int payWay, String payInfo) {
        this.mPayWay = payWay;
        this.mPayInfo = payInfo;
        IPayment payment = createPayment(payWay, payInfo);
        payment.pay();
    }

    public Payer addPayCallback(IPayCallback callback) {
        PayLoop.getIntstance().add(callback);
        return this;
    }

    private IPayment createPayment(PayWay payWay, String payInfo) {
        return createPayment(payWay.value, payInfo);
    }

    private IPayment createPayment(int payWay, String payInfo) {
        PaymentFactory factory = PaySettings.getIntstance().getPaymentFactory(mActivity);
        return factory.createPayment(payWay, payInfo);
    }

    public void continuePay() {
        pay(mPayWay, mPayInfo);
    }

    public enum PayWay {
        ALIPAY(1), WXPAY(3), PINGPP_ALIPAY(7), PINGPP_WX(8);

        int value;

        PayWay(int value) {
            this.value = value;
        }
    }
}
