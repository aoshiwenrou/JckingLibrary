package com.jcking.lib.pay.payment;
// 支付宝接入流程：https://docs.open.alipay.com/204/105296/
//
//import android.app.Activity;
//import android.os.AsyncTask;
//import android.os.Looper;
//import android.text.TextUtils;
//import android.widget.Toast;
//
//import com.alipay.sdk.app.PayTask;
//import com.tencent.mm.sdk.modelpay.PayReq;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.util.Map;
//import java.util.concurrent.Executors;
//
//import com.jcking.lib.pay.PayLoop;
//
///**
// * @author Jcking
// * @time 2017/7/13 15:37
// */
//public class AliPayment implements IPayment {
//
//    private final Activity mActivity;
//    private final String mPayInfo;
//
//    public AliPayment(Activity activity, String payInfo) {
//        this.mActivity = activity;
//        this.mPayInfo = parsePayInfo(payInfo);
//    }
//
//    private String parsePayInfo(String payInfo) {
//        if (TextUtils.isEmpty(payInfo))
//            return payInfo;
//        if (payInfo.startsWith("{") && payInfo.contains("payStr")) {
//            try {
//                JSONObject object = new JSONObject(payInfo);
//                return object.optString("payStr");
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
//        return payInfo;
//    }
//
//    @Override
//    public void pay() {
//        new AliPayTask().executeOnExecutor(Executors.newCachedThreadPool(), mPayInfo);
//    }
//
//    private void explainResult(AliPayResult result) {
//        /**
//         * 同步返回的结果必须放置到服务端进行验证（验证的规则请看https://doc.open.alipay.com/doc2/
//         * detail.htm?spm=0.0.0.0.xdvAU6&treeId=59&articleId=103665&
//         * docType=1) 建议商户依赖异步通知
//         */
//        String resultStatus = result.getResultStatus();
//        // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
//        if (TextUtils.equals(resultStatus, "9000")) {
////            Toast.makeText(mActivity, "支付成功", Toast.LENGTH_SHORT).show();
//            PayLoop.getIntstance().paySuccess();
//        } else {
//            // 判断resultStatus 为非"9000"则代表可能支付失败
//            // "8000"代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
//            if (TextUtils.equals(resultStatus, "8000")) {
//                Toast.makeText(mActivity, "支付结果确认中", Toast.LENGTH_SHORT).show();
//            } else {
//                // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
//                PayLoop.getIntstance().payFailure();
//            }
//        }
//    }
//
//    private boolean isMainThread() {
//        return Looper.myLooper() == Looper.getMainLooper();
//    }
//
//    private class AliPayTask extends AsyncTask<String, Integer, AliPayResult> {
//
//        @Override
//        protected AliPayResult doInBackground(String... params) {
//            PayTask alipay = new PayTask(mActivity);
//            // 调用支付接口，获取支付结果
//            Map<String, String> result = alipay.payV2(params[0], true);
//            return new AliPayResult(result);
//        }
//
//        @Override
//        protected void onPostExecute(final AliPayResult result) {
//            if (isMainThread()) {
//                explainResult(result);
//            } else {
//                mActivity.runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        explainResult(result);
//                    }
//                });
//            }
//        }
//    }
//}
