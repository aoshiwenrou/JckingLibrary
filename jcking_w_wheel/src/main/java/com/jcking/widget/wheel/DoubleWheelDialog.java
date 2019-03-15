package com.jcking.widget.wheel;

import android.content.Context;

import com.aigestudio.wheelpicker.WheelPicker;

import java.util.List;

/**
 * 双滚动的dialog
 *
 * @author Jcking
 * @time 2019/3/7 14:41
 */
public class DoubleWheelDialog extends AbsWheelDialog implements WheelPicker.OnItemSelectedListener {

    private WheelPicker mWheelLeft;
    private WheelPicker mWheelRight;
    private OnItemSelectListener mListener;

    public DoubleWheelDialog(Context context) {
        super(context);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.dialog_double_wheel;
    }

    @Override
    protected void initWheel() {
        mWheelLeft = findViewById(R.id.wheel_left);
        mWheelLeft.setOnItemSelectedListener(this);
        setWheelStyle(mWheelLeft);

        mWheelRight = findViewById(R.id.wheel_right);
        mWheelRight.setOnItemSelectedListener(this);
        setWheelStyle(mWheelRight);
    }

    @Override
    protected void onConfirmClick(OnWheelConfirmListener listener) {
        listener.onConfirm(mWheelLeft.getCurrentItemPosition()
                , mWheelRight.getCurrentItemPosition());
    }

    public DoubleWheelDialog setLeftData(List<String> data) {
        mWheelLeft.setData(data);
        return this;
    }

    public DoubleWheelDialog setRightData(List<String> data) {
        mWheelRight.setData(data);
        return this;
    }

    public DoubleWheelDialog setOnItemSelectListener(OnItemSelectListener listener) {
        this.mListener = listener;
        return this;
    }

    @Override
    public void onItemSelected(WheelPicker picker, Object data, int position) {
        if (mListener == null)
            return;
        if (picker == mWheelLeft)
            mListener.onLeftSelected((String) data, position);
        else if (picker == mWheelRight)
            mListener.onRightSelected((String) data, position);
    }

    public interface OnItemSelectListener {
        void onLeftSelected(String data, int position);

        void onRightSelected(String data, int position);
    }
}
