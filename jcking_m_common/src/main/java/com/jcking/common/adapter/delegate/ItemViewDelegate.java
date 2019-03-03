package com.jcking.common.adapter.delegate;

import com.jcking.common.adapter.common.ViewHolder;
import com.jcking.common.adapter.recycler.RecyclerViewHolder;

/**
 * Created by chenran3 on 2017/12/18.
 */

public interface ItemViewDelegate<T> {

    int getItemViewLayoutId();

    boolean isForViewType(T item, int position);

    void convert(ViewHolder holder, T t, int position);

    void recyclerConvert(RecyclerViewHolder holder, T t, int position);
}
