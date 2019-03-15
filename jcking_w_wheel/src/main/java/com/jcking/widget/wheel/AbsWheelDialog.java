package com.jcking.widget.wheel;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.aigestudio.wheelpicker.WheelPicker;

/**
 * 滚轮dialog的抽象类
 *
 * @author Jcking
 * @time 2019/3/7 14:41
 */
public abstract class AbsWheelDialog extends Dialog implements View.OnClickListener {

    protected Context mContext;
    protected TextView mTvTitle;
    protected OnWheelConfirmListener mConfirmListenr;

    public AbsWheelDialog(Context context) {
        super(context);
        init(context);
    }

    protected abstract int getLayoutRes();

    protected abstract void initWheel();

    protected abstract void onConfirmClick(OnWheelConfirmListener listener);

    protected void init(Context context) {
        setContentView(getLayoutRes());
        DialogUtil.windowDeploy(this, 0.8f);

        mContext = context;
        mTvTitle = findViewById(R.id.tv_title);

        findViewById(R.id.tv_cancel).setOnClickListener(this);
        findViewById(R.id.tv_confirm).setOnClickListener(this);

        initWheel();
    }


    public AbsWheelDialog setTitle(String title) {
        mTvTitle.setText(title);
        return this;
    }

    public AbsWheelDialog setOnWheelConfirmListener(OnWheelConfirmListener listener) {
        this.mConfirmListenr = listener;
        return this;
    }

    /**
     * 设置滚轮风格
     *
     * @param wheel
     */
    protected void setWheelStyle(WheelPicker wheel) {
        wheel.setAtmospheric(true);  //设置雾化效果
        wheel.setCurved(true);       //滚轮效果
        wheel.setIndicator(false);   //不显示指示器
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tv_confirm && mConfirmListenr != null)
            onConfirmClick(mConfirmListenr);
        dismiss();
    }
}
