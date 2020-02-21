package com.yunbiao.yunbiaobasedemo.ui.adapter;



import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yunbiao.yunbiaobasedemo.R;


import java.util.List;

/**
 * Created by Administrator on 2019/5/30.
 */

public class CityAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public CityAdapter(@LayoutRes int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_addName,item);
    }
}
