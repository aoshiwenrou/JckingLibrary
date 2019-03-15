package com.jcking.lib.pay.factory;

import com.jcking.lib.pay.payment.IPayment;

public abstract class PaymentFactory {
    public abstract IPayment createPayment(int payWay, String payInfo);
}
