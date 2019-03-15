package com.jcking.widget.wheel;

/**
 * 滚轮选择确认监听
 *
 * @author Jcking
 * @time 2019/3/7 16:27
 */
public interface OnWheelConfirmListener {

    /**
     * 根据wheel个数不同，positon的size也不同
     *
     * @param positions
     */
    void onConfirm(int... positions);
}
