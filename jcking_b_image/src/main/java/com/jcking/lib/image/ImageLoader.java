package com.jcking.lib.image;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.drawable.ProgressBarDrawable;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

/**
 * 图片加载器
 *
 * @author Jcking
 * @time 2019/3/29 21:46
 */
public class ImageLoader {

    /**
     * 初始化，尽量在Application中调用
     *
     * @param context
     */
    public static void init(Context context) {
        // 初始化Fresco
        Fresco.initialize(context.getApplicationContext());
    }

    /**
     * 加载图片。渐进式图片。
     *
     * @param view
     * @param url
     */
    public static void load(JImageView view, String url) {
        if (TextUtils.isEmpty(url))
            return;
        ImageRequest request = ImageRequestBuilder
                .newBuilderWithSource(Uri.parse(url))
                .setProgressiveRenderingEnabled(true)
                .build();

        DraweeController controller = Fresco
                .newDraweeControllerBuilder()
                .setImageRequest(request)
                .setOldController(view.getController())
                .build();

        view.setController(controller);

//        // 直接加载图片
//        view.setImageURI(url);
    }

    /**
     * 加载图片。会覆盖XML设置的所有属性，即在XML中有在这里没有的属性都会失效
     *
     * @param view
     * @param url
     * @param placeHolder 占位图
     */
    public static void load(JImageView view, String url, int placeHolder) {
        GenericDraweeHierarchy hierarchy = view.getHierarchy();
        hierarchy.setFadeDuration(3000);
        // 占位图
        hierarchy.setPlaceholderImage(placeHolder);
        // 显示进度条（Fresco自带的进度条）
        hierarchy.setProgressBarImage(new ProgressBarDrawable());
        // 为SimpleDraweeView设置属性
        view.setHierarchy(hierarchy);
        load(view, url);
    }

    /**
     * 加载图片。如果isCircle=true，则图片将被裁减为圆形
     *
     * @param view
     * @param url
     * @param placeHolder
     * @param isCircle
     */
    public static void load(JImageView view, String url, int placeHolder, boolean isCircle) {
        GenericDraweeHierarchy hierarchy = view.getHierarchy();
        if (isCircle) {
            // 图片剪切成圆形
            RoundingParams roundingParams = new RoundingParams();
            roundingParams.setRoundAsCircle(true);
            hierarchy.setRoundingParams(roundingParams);
            view.setHierarchy(hierarchy);
        }
        load(view, url, placeHolder);
    }

    /**
     * 加载图片。会有值为corner的圆角
     *
     * @param view
     * @param url
     * @param placeHolder
     * @param corner
     */
    public static void load(JImageView view, String url, int placeHolder, int corner) {
        GenericDraweeHierarchy hierarchy = view.getHierarchy();
        RoundingParams roundingParams = new RoundingParams();
        roundingParams.setRoundAsCircle(false); // 不将图片剪切成圆形
        roundingParams.setCornersRadius(corner);
        hierarchy.setRoundingParams(roundingParams);
        view.setHierarchy(hierarchy);
        load(view, url, placeHolder);
    }

    //设置下载事件
//    public static void load(JImageView view, String url, boolean a) {
//        BaseControllerListener listener = new BaseControllerListener<ImageInfo>() {
//            @Override
//            public void onFinalImageSet(String id, ImageInfo imageInfo, Animatable animatable) {
//                // 所有图片都加载成功时触发的方法
//                int width = imageInfo.getWidth();
//                int height = imageInfo.getHeight();
//                QualityInfo qualityInfo = imageInfo.getQualityInfo();
//                int quality = qualityInfo.getQuality();
//                boolean isOfFullQuality = qualityInfo.isOfFullQuality();
//                boolean isOfGoodEnoughQuality = qualityInfo.isOfGoodEnoughQuality();
//            }
//
//            @Override
//            public void onIntermediateImageSet(String id, ImageInfo imageInfo) {
//                // 加载渐进式图片时回调的方法
//            }
//
//            @Override
//            public void onFailure(String id, Throwable throwable) {
//                // 加载图片失败时回调的方法
//            }
//        };
//
//        DraweeController controller = Fresco.newDraweeControllerBuilder()
//                .setControllerListener(listener)
//                .setUri(url)
//                .build();
//
//        view.setController(controller);
//    }
}
