package com.jcking.lib.pay;

import android.app.Activity;

import com.jcking.lib.pay.factory.DefaultPaymentFactory;
import com.jcking.lib.pay.factory.PaymentFactory;

/**
 * Created by Jcking on 2017/11/21.
 */

public class PaySettings {

    private static volatile PaySettings sSingleton = null;
    private Class factoryClass;

    private PaySettings() {
    }

    public static PaySettings getIntstance() {
        if (sSingleton == null) {
            synchronized (PaySettings.class) {
                if (sSingleton == null)
                    sSingleton = new PaySettings();
            }
        }
        return sSingleton;
    }

    public PaymentFactory getPaymentFactory(Activity activity) {
        PaymentFactory mFactory;

        if (factoryClass == null) {
            mFactory = new DefaultPaymentFactory(activity);
        } else {
            try {
                mFactory = (PaymentFactory) factoryClass.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
                mFactory = new DefaultPaymentFactory(activity);
            }
        }
        return mFactory;
    }

    public Class getFactoryClass() {
        return factoryClass;
    }

    public void setFactoryClass(Class factoryClass) {
        this.factoryClass = factoryClass;
    }
}
