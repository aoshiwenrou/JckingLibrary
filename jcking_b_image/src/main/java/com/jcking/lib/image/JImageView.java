package com.jcking.lib.image;

import android.content.Context;
import android.util.AttributeSet;

import com.facebook.drawee.view.SimpleDraweeView;

/**
 * 用于加载图片资源的ImageView，负责显示网络图片、本地图片等
 *
 * 目前是用的是Fresco，常用属性如下:  <p>
 * fresco:actualImageScaleType：实际加载的图片的伸缩样式<p>
 * fresco:backgroundImage：底层图片资源<p>
 * fresco:fadeDuration：进度条和占位符图片逐渐消失、加载的图片逐渐显示的时间间隔<p>
 * fresco:failureImage：加载失败时显示的图片资源<p>
 * fresco:failureImageScaleType：加载失败时加载的图片的伸缩样式<p>
 * fresco:overlayImage：在显示的图片表层覆盖一张图片的图片资源<p>
 * fresco:placeholderImage：占位符图片资源<p>
 * fresco:placeholderImageScaleType：占位符图片的伸缩样式<p>
 * fresco:progressBarAutoRotateInterval：进度条图片转动周期<p>
 * fresco:progressBarImage：进度条图片资源<p>
 * fresco:progressBarImageScaleType：进度条图片的伸缩样式<p>
 * fresco:retryImage：提示重新加载的图片资源<p>
 * fresco:retryImageScaleType：提示重新加载的图片的伸缩样式<p>
 * fresco:roundAsCircle：将图片剪切成圆形<p>
 * fresco:viewAspectRatio：图片宽高比<p>
 *
 * @author Jcking
 * @time 2019/3/29 21:32
 */
public class JImageView extends SimpleDraweeView {

    public JImageView(Context context) {
        super(context);
    }

    public JImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public JImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public JImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
}
