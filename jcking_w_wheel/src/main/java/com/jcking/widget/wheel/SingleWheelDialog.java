package com.jcking.widget.wheel;

import android.content.Context;

import com.aigestudio.wheelpicker.WheelPicker;

import java.util.List;

/**
 * 单滚动的dialog
 *
 * @author Jcking
 * @time 2019/3/7 14:41
 */
public class SingleWheelDialog extends AbsWheelDialog implements WheelPicker.OnItemSelectedListener {

    private WheelPicker mWheelSingle;
    private OnItemSelectListener mListener;

    public SingleWheelDialog(Context context) {
        super(context);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.dialog_single_wheel;
    }

    @Override
    protected void initWheel() {
        mWheelSingle = findViewById(R.id.wheel_single);
        mWheelSingle.setOnItemSelectedListener(this);
        setWheelStyle(mWheelSingle);
    }

    @Override
    protected void onConfirmClick(OnWheelConfirmListener listener) {
        listener.onConfirm(mWheelSingle.getCurrentItemPosition());
    }

    public SingleWheelDialog setData(List<String> data) {
        mWheelSingle.setData(data);
        return this;
    }

    public SingleWheelDialog setOnItemSelectListener(OnItemSelectListener event) {
        this.mListener = event;
        return this;
    }

    @Override
    public void onItemSelected(WheelPicker picker, Object data, int position) {
        if (mListener == null)
            return;
        if (picker == mWheelSingle)
            mListener.onItemSelected((String) data, position);
    }

    public interface OnItemSelectListener {
        void onItemSelected(String data, int position);
    }
}
